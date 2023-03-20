package com.example.sample.controller;

import com.example.sample.dto.HelloDto;
import com.example.sample.service.HelloService;
import com.example.sample.service.function.SearchFunction;
import com.example.sample.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() {

        HashMap<String, Object> result = new HttpUtil()
                .url("https://www.naver.com")
                .method("get")
                .queryString("파라미터명", "값")
                .queryString("aaa", "bbb")
                .build();


        HelloDto helloDto = new HelloDto();
        Function<HelloDto, SearchFunction> executeIpay = dto -> null;


        CompletableFuture
                .supplyAsync(executeIpay.apply(helloDto))
                .thenApply(restAPICallback(helloDto))
                .exceptionally(exceptionHandler(helloDto))
                .join();

        return "hello!";
    }

    private Function<Object, HelloDto> restAPICallback(HelloDto vo) {
        return o -> {
            log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

            return vo;
        };
    }


    private Function<Throwable,HelloDto> exceptionHandler(HelloDto vo) {
        return o -> {
            //통신중 에러가 날 경우 에러 정보를 결과로 저장

            return vo;
        };
    }
}