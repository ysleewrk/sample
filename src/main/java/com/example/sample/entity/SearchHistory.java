package com.example.sample.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "TB_SEARCH_HISTORY")
public class SearchHistory extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String platform;

    @Column
    private String keyword;

    @Column
    private String deviceId;


}


