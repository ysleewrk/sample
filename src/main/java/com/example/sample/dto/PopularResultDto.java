package com.example.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class PopularResultDto implements Serializable {

    @JsonProperty("documents")
    private List<PopularResultDto.Document> documents;

    @Data
    public static class Document {

        @JsonProperty("query")
        private String query;

        @JsonProperty("count")
        private Long count;
    }

}
