package com.example.sample.controller;

import com.example.sample.common.CustomException;
import com.example.sample.dto.HelloDto;
import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;
import com.example.sample.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.SearchResult;
import javax.validation.Valid;
import java.util.function.Function;

@Slf4j
@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<SearchResultDto> searchQuery(@Validated @ModelAttribute SearchParamDto reqDto, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
        }




        SearchResultDto result = searchService.searchWord(reqDto);

//        HelloDto helloDto = new HelloDto();
//        Function<HelloDto, SearchFunction> executeIpay = dto -> null;
//
//
//        CompletableFuture
//                .supplyAsync(executeIpay.apply(helloDto))
//                .thenApply(restAPICallback(helloDto))
//                .exceptionally(exceptionHandler(helloDto))
//                .join();

        return ResponseEntity.ok().body(result);
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