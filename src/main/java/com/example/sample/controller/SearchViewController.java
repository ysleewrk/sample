package com.example.sample.controller;

import com.example.sample.common.CustomException;
import com.example.sample.dto.HelloDto;
import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;
import com.example.sample.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

@Slf4j
@Controller
public class SearchViewController {

    private final SearchService searchService;

    public SearchViewController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search/main")
    public String mainView(Model model, HttpServletRequest request) {
        return "main";
    }



}