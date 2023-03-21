package com.example.sample.dto;

import com.example.sample.dto.enums.PlatformEnum;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class SearchParamDto {

    private String platform = PlatformEnum.KAKAO.getValue();

    @NotEmpty(message = "검색어는 필수로 입력해야합니다.")
    private String keyword;

    @NotEmpty()
    private String sort;
    private String page;
    private String size;
}
