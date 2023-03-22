package com.example.sample.controller;

import com.example.sample.common.exception.CustomException;
import com.example.sample.common.service.ConcurrentService;
import com.example.sample.dto.HelloDto;
import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;
import com.example.sample.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Slf4j
@RestController
public class SearchController {

    private final SearchService searchService;
    private final ConcurrentService concurrentService;

    public SearchController(SearchService searchService, ConcurrentService concurrentService) {
        this.searchService = searchService;
        this.concurrentService = concurrentService;
    }

    @GetMapping("/search")
    public ResponseEntity<SearchResultDto> searchQuery(@Validated @ModelAttribute SearchParamDto reqDto
            , BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
        }


        concurrentService.increment(reqDto.getTerm());

        SearchResultDto result = new SearchResultDto();

        try {
            result = searchService.searchWord(reqDto);





//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//
//
//            return "Thread: " + Thread.currentThread().getName();
//        }).thenApply(s -> {
//            System.out.println("thenApply = ");
//            return s.toUpperCase();
//        });
//        future.join();


        } catch (CustomException e) {
            result.setResponseCode("9999");
            result.setResponseMessage(e.getErrorMessage());
            return ResponseEntity.badRequest().body(result);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().body(result);
    }



    @GetMapping("/popular")
    public ResponseEntity<LinkedHashMap<String, Long>> getPopular() {

       return ResponseEntity.ok().body(concurrentService.getPopularList());
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