package com.example.sample.service;

import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;

import java.util.List;

public interface PlatformService {

    public List<SearchResultDto> searchByPlatform(SearchParamDto reqDto);
}