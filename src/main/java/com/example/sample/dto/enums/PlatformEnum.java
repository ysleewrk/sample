package com.example.sample.dto.enums;

import lombok.Getter;

@Getter
public enum PlatformEnum {

    KAKAO("kakao"),
    NAVER("naver"),
    GOOGLE("google"),
    BING("bing");

    private String value;

    PlatformEnum(String value) {
        this.value = value;
    }

}
