package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.GoodsMapper;
import com.autonetics.autonetics.api.mapper.WeatherMapper;
import com.autonetics.autonetics.api.model.entity.Goods;
import com.autonetics.autonetics.api.model.prompts.AiPrompts;
import com.autonetics.autonetics.api.model.request.RequestGoodsAi;
import com.autonetics.autonetics.api.model.response.WeatherDto;
import com.autonetics.autonetics.api.service.GoodsService;
import com.autonetics.autonetics.api.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {
    private final ChatClient chatClient;
    private final WeatherService weatherService;
    private final WeatherMapper weatherMapper;
    private final GoodsService goodsService;
    private final GoodsMapper goodsMapper;


    @GetMapping("/hello")
    public String sayHello() {
        String prompt = AiPrompts.GREETING;
        return chatClient.call(prompt);
    }

    @GetMapping("/best-product-for-weather/{city}")
    public ResponseEntity<String> getBestProductForWeather(@PathVariable String city) {
        WeatherDto weather = weatherMapper.toDto(weatherService.getWeatherByCity(city));
        List<Goods> allGoods = goodsService.getAll();
        List<RequestGoodsAi> requestList = allGoods.stream().map(goodsMapper::toAiDto).toList();

        String prompt = AiPrompts.ALL_PRODUCTS;
        prompt+= weather + "\n" + requestList;
        System.out.println(prompt);

        return ResponseEntity.ok(chatClient.call(prompt));
    }
}
