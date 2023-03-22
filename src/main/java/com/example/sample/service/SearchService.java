package com.example.sample.service;

import com.example.sample.common.service.PropertiesService;
import com.example.sample.dto.KakaoSearchResultDto;
import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;
import com.example.sample.dto.enums.PlatformEnum;
import com.example.sample.repository.SearchHistoryRepository;
import com.example.sample.service.impl.KakaoPlatformServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SearchService {

    private final SearchHistoryRepository searchHistoryRepository;
    private final PropertiesService propertiesService;

    public SearchService(SearchHistoryRepository searchHistoryRepository, PropertiesService propertiesService) {
        this.searchHistoryRepository = searchHistoryRepository;
        this.propertiesService = propertiesService;
    }


    public SearchResultDto searchWord(SearchParamDto reqDto) throws Exception {

        PlatformEnum platform = PlatformEnum.valueOf(reqDto.getPlatform().toUpperCase());

        PlatformService platformService = null;
        switch (platform) {
            case NAVER:
                // 네이버 연동
                //platformService = new NaverPlatformServiceImpl();
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
                platformService = new KakaoPlatformServiceImpl(propertiesService);
        }

        SearchResultDto searchResult = platformService.searchByPlatform(reqDto);

        return searchResult;
    }

    private void insertSearchHistory(SearchParamDto reqDto) {

        //searchHistoryRepository.save(reqDto);

    }


}
