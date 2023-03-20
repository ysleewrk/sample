package com.example.sample.dto;

import lombok.Data;

@Data
public class KakaoErrorDto {
    private String errorType;
    private String message;

    public KakaoErrorDto() {}
}
