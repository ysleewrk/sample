package com.example.sample.service.impl;

import com.example.sample.common.service.PropertiesService;
import com.example.sample.dto.KakaoErrorDto;
import com.example.sample.dto.KakaoSearchResultDto;
import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;
import com.example.sample.service.PlatformService;
import com.example.sample.util.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class KakaoPlatformServiceImpl implements PlatformService {

    private final String apiUrl;
    private final String apiKey;

    public KakaoPlatformServiceImpl(PropertiesService propertiesService) {
        this.apiUrl = propertiesService.getProperty("search.kakao.url");
        this.apiKey = propertiesService.getProperty("search.kakao.key");
    }

    @Override
    public SearchResultDto searchByPlatform(SearchParamDto reqDto) {

        List<String> allowedSortValues = List.of("accurancy", "recency");
        if (!allowedSortValues.contains(reqDto.getSort())) {
            reqDto.setSort("accurancy");
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
                .header("Authorization", "KakaoAK " + apiKey)
                .contentType("application", "x-www-form-urlencoded", "UTF-8")
                .queryString("query", reqDto.getTerm())
                .queryString("sort", reqDto.getSort())
                .queryString("size", String.valueOf(reqDto.getSize()))
                .queryString("page", String.valueOf(reqDto.getPage()));



        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());


        SearchResultDto kakaoResultDto = new KakaoSearchResultDto();
        try {
            result = temp.build();
            log.info(result.toString());
             kakaoResultDto = objectMapper.readValue((String)result.get("body"), new TypeReference<KakaoSearchResultDto>() { });
//
//            kakaoResultDto.getDocuments().stream().map






        } catch (HttpClientErrorException e) {
            KakaoErrorDto errorDto = objectMapper.convertValue(e.getResponseBodyAsString(), new TypeReference<>() {
            });

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return kakaoResultDto;
    }
}
