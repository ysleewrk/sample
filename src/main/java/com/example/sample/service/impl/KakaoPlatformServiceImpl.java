package com.example.sample.service.impl;

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

    @Value("${search.kakao.url}")
    private String apiUrl = "https://dapi.kakao.com/v2/search/blog";

    @Value("${search.kakao.key}")
    private String apiKey = "f9c3bc515657b7b45cec4516bb761643";

    @Override
    public KakaoSearchResultDto searchByPlatform(SearchParamDto reqDto) {

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


        SearchResultDto resultDto = new SearchResultDto();
        KakaoSearchResultDto kakaoResultDto = new KakaoSearchResultDto();
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
