package com.example.sample.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;


@Data
public class SearchResultDto {

    @JsonProperty("documents")
    private List<Document> documents;

    @JsonProperty("meta")
    private Meta meta;

    @Data
    public static class Document {

        @JsonProperty("blogname")
        private String blogname;

        @JsonProperty("contents")
        private String contents;

        @JsonProperty("datetime")
        private ZonedDateTime dateTime;

        @JsonProperty("thumbnail")
        private String thumbnail;

        @JsonProperty("title")
        private String title;

        @JsonProperty("url")
        private String url;
    }

    @Data
    public static class Meta {

        @JsonProperty("is_end")
        private boolean is_end;

        @JsonProperty("pageable_count")
        private int pageable_count;

        @JsonProperty("total_count")
        private int total_count;
    }
}
