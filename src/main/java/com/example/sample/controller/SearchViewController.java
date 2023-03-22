package com.example.sample.controller;

import com.example.sample.common.service.ConcurrentService;
import com.example.sample.dto.SearchParamDto;
import com.example.sample.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class SearchViewController {

    private final SearchService searchService;
    private final ConcurrentService concurrentService;

    public SearchViewController(SearchService searchService, ConcurrentService concurrentService) {
        this.searchService = searchService;
        this.concurrentService = concurrentService;
    }

    @GetMapping({"", "/", "/search/main"})
    public String mainView(Model model, HttpServletRequest request,
                           @ModelAttribute SearchParamDto reqDto) throws Exception {

        model.addAttribute("popularMap", concurrentService.getPopularList());

        if (reqDto.getTerm() != null && !reqDto.getTerm().isEmpty()) {
            model.addAttribute("keyword", reqDto.getTerm());
        }

        return "main";
    }



}