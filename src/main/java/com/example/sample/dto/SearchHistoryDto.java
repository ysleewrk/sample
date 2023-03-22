package com.example.sample.dto;

import lombok.Data;

@Data
public class SearchHistoryDto {
    private String query;
    private Long searchCount;
}
