package com.example.sample.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class SearchParamDto {

    private String platform;
    private String keyword;
    private String sort;
    private String page;
    private String size;
}
