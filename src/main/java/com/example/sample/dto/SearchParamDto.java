package com.example.sample.dto;

import lombok.Data;

@Data
public class SearchParamDto {

    private String platform;
    private String keyword;
    private String sort;
}
