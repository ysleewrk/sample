package com.example.sample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "TB_HELLO")
public class Hello {
    @Id
    @GeneratedValue
    private Long id;
}
