package com.example.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class KakaoSearchResultDto extends SearchResultDto {

    @JsonProperty("documents")
    private List<Document> documents;

    @JsonProperty("meta")
    private Meta meta;

    @Getter
    @Setter
    @NoArgsConstructor
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

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Meta {

        @JsonProperty("is_end")
        private boolean is_end;

        @JsonProperty("pageable_count")
        private int pageable_count;

        @JsonProperty("total_count")
        private int total_count;
    }
}
