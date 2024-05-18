package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.GoodsMapper;
import com.autonetics.autonetics.api.mapper.WeatherMapper;
import com.autonetics.autonetics.api.model.entity.Goods;
import com.autonetics.autonetics.api.model.entity.Inventory;
import com.autonetics.autonetics.api.model.prompts.AiPrompts;
import com.autonetics.autonetics.api.model.request.RequestGoodsAi;
import com.autonetics.autonetics.api.model.response.WeatherDto;
import com.autonetics.autonetics.api.service.GoodsService;
import com.autonetics.autonetics.api.service.InventoryService;
import com.autonetics.autonetics.api.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {
    private final ChatClient chatClient;
    private final WeatherService weatherService;
    private final WeatherMapper weatherMapper;
    private final GoodsService goodsService;
    private final InventoryService inventoryService;
    private final GoodsMapper goodsMapper;


    @GetMapping("/hello")
    public String sayHello() {
        String prompt = AiPrompts.GREETING;
        return chatClient.call(prompt);
    }


    @GetMapping("/best-product-for-weather/{city}")
    public ResponseEntity<String> getBestProductForWeatherInCity(@PathVariable String city, @RequestHeader long shopId) {
        WeatherDto weather = weatherMapper.toDto(weatherService.getWeatherByCity(city));
        List<Inventory> allGoodsFromShop = inventoryService.findAllGoodsByShopID_Id(shopId);
        List<Goods> allGoods = allGoodsFromShop.stream().map(Inventory::getGoodsID).toList();
        List<RequestGoodsAi> requestList = allGoods.stream().map(goodsMapper::toAiDto).toList();

        String prompt = AiPrompts.FOR_WEATHER;
        prompt+= weather + "\n" + requestList;
//        chatClient.call(prompt)
        return ResponseEntity.ok(prompt);
    }

    @GetMapping("/best-product-for-weather/")
    public ResponseEntity<String> getBestProductForWeatherByCoordinates(@RequestHeader BigDecimal latitude,
                                                                        @RequestHeader BigDecimal longitude,
                                                                        @RequestHeader long shopId) {
        WeatherDto weather = weatherMapper.toDto(weatherService.getWeatherByCoordinates(latitude, longitude));
        List<RequestGoodsAi> requestList = getGoodsFromShop(shopId);

        String prompt = AiPrompts.FOR_WEATHER;
        prompt+= weather + "\n" + requestList;
//        chatClient.call(prompt)
        return ResponseEntity.ok(prompt);
    }

    private List<RequestGoodsAi> getGoodsFromShop(long shopId) {
        List<Inventory> allGoodsFromShop = inventoryService.findAllGoodsByShopID_Id(shopId);
        List<Goods> allGoods = allGoodsFromShop.stream().map(Inventory::getGoodsID).toList();
        return allGoods.stream().map(goodsMapper::toAiDto).toList();
    }
}
