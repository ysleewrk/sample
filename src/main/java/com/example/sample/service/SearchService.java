package com.example.sample.service;

import com.example.sample.common.service.PropertiesService;
import com.example.sample.dto.SearchParamDto;
import com.example.sample.dto.SearchResultDto;
import com.example.sample.dto.enums.PlatformEnum;
import com.example.sample.entity.SearchHistory;
import com.example.sample.repository.SearchHistoryRepository;
import com.example.sample.service.impl.KakaoPlatformServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

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


        // 검색과 별도로 비동기 DB적재
        // 인기검색어는 별도 Bean - ConcurrentHashMap 으로 관리되기에 무관
        CompletableFuture.runAsync(() -> {
            this.insertSearchHistory(reqDto);
        }).join();

        return searchResult;
    }

    @Transactional
    public void insertSearchHistory(SearchParamDto reqDto) {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setQuery(reqDto.getTerm());
        searchHistoryRepository.save(searchHistory);
    }


}
