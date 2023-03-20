package com.example.sample.service;

import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;
import com.example.sample.dto.enums.PlatformEnum;
import com.example.sample.service.impl.KakaoPlatformServiceImpl;
import com.example.sample.service.impl.NaverPlatformServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HelloService {


    public List<SearchResultDto> searchWord(SearchParamDto reqDto) throws Exception {

        PlatformEnum platform = PlatformEnum.valueOf(reqDto.getPlatform());

        PlatformService platformService;
        switch (platform) {
            case NAVER:
                // 네이버 연동
                platformService = new NaverPlatformServiceImpl();
                break;
            case GOOGLE:
                // 구글 연동
                throw new Exception("지원하지 않습니다.");
                //break;
            case BING:
                // bing 연동
                throw new Exception("지원하지 않습니다.");
                //break;
            case KAKAO:
                // 카카오연동
            default:
                platformService = new KakaoPlatformServiceImpl();
        }

        List<SearchResultDto> searchResultList = platformService.searchByPlatform(reqDto);

        return searchResultList;
    }


}