package com.example.sample.service.impl;

import com.example.sample.dto.KakaoErrorDto;
import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;
import com.example.sample.service.PlatformService;
import com.example.sample.util.HttpUtil;
import com.example.sample.util.WebUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class NaverPlatformServiceImpl implements PlatformService {

    @Override
    public List<SearchResultDto> searchByPlatform(SearchParamDto reqDto) {
        HashMap<String, Object> result;

        String apiUrl = "https://openapi.naver.com/v1/search/blog.json";
        String clientId = "Nph3XegwIY6kjvoo1mRC";
        String clientSecret = "ybkMKGoO52";

//        HashMap<String, Object> result = new HttpUtil()
//                .url(apiUrl)
//                .method("get")
//                .header("Authorization", "KakaoAK " + apiKey)
//                .contentType("application", "x-www-form-urlencoded", "UTF-8")
//                .queryString("query", reqDto.getKeyword())
//                .queryString("sort", reqDto.getSort())
//                .build();


        HttpUtil temp = new HttpUtil()
                .url(apiUrl)
                .method("get")
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .contentType("application", "json", "UTF-8")
                .queryString("query", WebUtil.encode(reqDto.getKeyword()));

        if (StringUtils.hasText(reqDto.getSort())) {
            temp.queryString("sort", reqDto.getSort());
        }
        if (StringUtils.hasText(reqDto.getSize())) {
            temp.queryString("display", reqDto.getSize());
        }
        if (StringUtils.hasText(reqDto.getPage())) {
            temp.queryString("start", reqDto.getPage());
        }


        ObjectMapper objectMapper = new ObjectMapper();

        try {
            result = temp.build();
        } catch (HttpClientErrorException e) {
            KakaoErrorDto errorDto = objectMapper.convertValue(e.getResponseBodyAsString(), new TypeReference<>() {
            });
            log.info(errorDto.toString());
        }

        return null;
    }
}
