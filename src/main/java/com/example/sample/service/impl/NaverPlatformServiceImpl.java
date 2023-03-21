package com.example.sample.service.impl;

import com.example.sample.dto.KakaoErrorDto;
import com.example.sample.dto.KakaoSearchResultDto;
import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;
import com.example.sample.service.PlatformService;
import com.example.sample.util.HttpUtil;
import com.example.sample.util.WebUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class NaverPlatformServiceImpl implements PlatformService {

    @Value("${search.naver.url}")
    private String apiUrl;

    @Value("${search.naver.id}")
    private String clientId;

    @Value("${search.naver.key}")
    private String clientSecret;

    @Override
    public KakaoSearchResultDto searchByPlatform(SearchParamDto reqDto) {

        List<String> allowedSortValues = List.of("sim", "date");
        if (!allowedSortValues.contains(reqDto.getSort())) {
            reqDto.setSort("sim");
        }

         HashMap<String, Object> result;

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
                .queryString("query", WebUtil.encode(reqDto.getTerm()))

            .queryString("sort", reqDto.getSort())


            .queryString("display", String.valueOf(reqDto.getSize()))


            .queryString("start", String.valueOf(reqDto.getPage()));



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
