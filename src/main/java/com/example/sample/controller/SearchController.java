package com.example.sample.controller;

import com.example.sample.common.exception.CustomException;
import com.example.sample.common.service.ConcurrentService;
import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;
import com.example.sample.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

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

        // 인기검색어 1 추가
        concurrentService.increment(reqDto.getTerm());

        SearchResultDto result = new SearchResultDto();
        try {
            result = searchService.searchWord(reqDto);

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

}