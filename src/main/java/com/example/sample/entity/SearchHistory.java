package com.example.sample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "TB_SEARCH_HISTORY")
public class SearchHistory {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String platform;
}


