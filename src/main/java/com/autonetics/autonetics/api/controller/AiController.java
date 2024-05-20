package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.GoodsMapper;
import com.autonetics.autonetics.api.mapper.OrderDetailMapper;
import com.autonetics.autonetics.api.mapper.WeatherMapper;
import com.autonetics.autonetics.api.model.entity.*;
import com.autonetics.autonetics.api.model.prompts.AiPrompts;
import com.autonetics.autonetics.api.model.request.RequestGoodsAi;
import com.autonetics.autonetics.api.model.response.GoodsDto;
import com.autonetics.autonetics.api.model.response.OrderDetailAiDto;
import com.autonetics.autonetics.api.model.response.WeatherDto;
import com.autonetics.autonetics.api.service.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.ai.chat.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {
    private final ChatClient chatClient;
    private final WeatherService weatherService;
    private final ShopService shopService;
    private final WeatherMapper weatherMapper;
    private final OrderDetailService orderService;
    private final OrderDetailMapper orderDetailMapper;
    private final GoodsService goodsService;
    private final InventoryService inventoryService;
    private final GoodsMapper goodsMapper;


    @GetMapping("/hello")
    public String sayHello() {
        String prompt = AiPrompts.GREETING;
        return chatClient.call(prompt);
    }


    @GetMapping("/best-product-for-weather/{city}")
    public ResponseEntity<List<GoodsDto>> getBestProductForWeatherInCity(@PathVariable String city, @RequestHeader long shopId) {
        WeatherDto weather = weatherMapper.toDto(weatherService.getWeatherByCity(city));
        List<Inventory> allGoodsFromShop = inventoryService.findAllGoodsByShopID_Id(shopId);
        List<Goods> allGoods = allGoodsFromShop.stream().map(Inventory::getGoodsID).toList();
        List<RequestGoodsAi> requestList = allGoods.stream().map(goodsMapper::toAiDto).toList();

        String prompt = AiPrompts.FOR_WEATHER;
        prompt += "\nWeather:" + weather + "\nAll shop goods:" + requestList + "\nTemplate:" + AiPrompts.RESPONSE_TEMPLATE;

        return getListResponseEntity(prompt);
    }

    @GetMapping("/best-product-for-weather")
    public ResponseEntity<List<GoodsDto>> getBestProductForWeatherByCoordinates(@RequestHeader long shopId) {
        Shop requestedShop = shopService.readById(shopId);
        BigDecimal latitude = requestedShop.getAddress().getLatitude();
        BigDecimal longitude = requestedShop.getAddress().getLongitude();

        List<RequestGoodsAi> requestList = getGoodsFromShop(shopId);

        String prompt = AiPrompts.FOR_WEATHER;
        prompt += "\nWeather:" + loadWeatherInfo(latitude, longitude) + "\nAll shop goods:" + requestList + "\nTemplate:" + AiPrompts.RESPONSE_TEMPLATE;

        return getListResponseEntity(prompt);
    }

    @GetMapping("/best-product-for-client")
    public ResponseEntity<List<GoodsDto>> getBestProductForClient(
            @RequestHeader long shopId,
            @RequestHeader String email
    ) {
        List<OrderDetail> orderDetailList = orderService.findByClientEmail(email);
        List<OrderDetailAiDto> orderDtoList = orderDetailList.stream().map(orderDetailMapper::toAiDto).toList();

        Shop requestedShop = shopService.readById(shopId);
        BigDecimal latitude = requestedShop.getAddress().getLatitude();
        BigDecimal longitude = requestedShop.getAddress().getLongitude();

        List<RequestGoodsAi> requestList = getGoodsFromShop(shopId);

        String prompt = AiPrompts.FOR_CLIENT;

        prompt += "\nWeather:" + loadWeatherInfo(latitude, longitude) + "\nAll shop goods:" + requestList + "\nPurchase history:" + orderDtoList + "\nTemplate:" + AiPrompts.RESPONSE_TEMPLATE;

        return getListResponseEntity(prompt);
    }

    private List<RequestGoodsAi> getGoodsFromShop(long shopId) {
        List<Inventory> allGoodsFromShop = inventoryService.findAllGoodsByShopID_Id(shopId);
        List<Goods> allGoods = allGoodsFromShop.stream().map(Inventory::getGoodsID).toList();
        return allGoods.stream().map(goodsMapper::toAiDto).toList();
    }

    private Long[] parseGoodsIds(String response) {
        return Arrays.stream(response.split("\\D+"))
                .filter(s -> !s.isBlank())
                .map(Long::parseLong)
                .toArray(Long[]::new);
    }


    @NotNull
    private ResponseEntity<List<GoodsDto>> getListResponseEntity(String prompt) {
        String response = chatClient.call(prompt);

        Long[] goodsIds = parseGoodsIds(response);
        List<GoodsDto> goodsList = new ArrayList<>();

        for (Long goodsId : goodsIds) {
            Goods goods = goodsService.readById(goodsId);
            goodsList.add(goodsMapper.toDto(goods));
        }

        return ResponseEntity.ok(goodsList);
    }

    private WeatherDto loadWeatherInfo(BigDecimal latitude, BigDecimal longitude) {
        return weatherMapper.toDto(weatherService.getWeatherByCoordinates(latitude, longitude));
    }
}
