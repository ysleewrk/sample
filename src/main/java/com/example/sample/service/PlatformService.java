package com.example.sample.service;

import com.example.sample.dto.KakaoSearchResultDto;
import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;

import java.util.List;

public interface PlatformService {

    public KakaoSearchResultDto searchByPlatform(SearchParamDto reqDto);
}
