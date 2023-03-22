package com.example.sample.service;

import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;

public interface PlatformService {

    SearchResultDto searchByPlatform(SearchParamDto reqDto);
}
