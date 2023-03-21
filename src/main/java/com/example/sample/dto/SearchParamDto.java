package com.example.sample.dto;

import com.example.sample.dto.enums.PlatformEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
public class SearchParamDto {

    private String platform = PlatformEnum.KAKAO.getValue();

    @NotEmpty(message = "검색어는 필수로 입력해야합니다.")
    private String term;

    private String sort = "accuracy";

    @Positive(message = "페이지는 양수이어야 합니다.")
    private int page = 1;

    @Range(min = 1, max = 50, message = "최대 50건 까지 조회가능합니다.")
    private int size = 10;
}
