package com.example.sample.service.impl;

import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;
import com.example.sample.service.PlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KakaoPlatformServiceImpl implements PlatformService {

    @Override
    public List<SearchResultDto> searchByPlatform(SearchParamDto reqDto) {
        return null;
    }
}
