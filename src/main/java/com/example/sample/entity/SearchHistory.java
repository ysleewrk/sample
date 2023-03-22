package com.example.sample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "TB_SEARCH_HISTORY")
public class SearchHistory extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "search_date", nullable = false)
    private String searchDate;

    @Column(name = "query", nullable = false)
    private String query;

}


