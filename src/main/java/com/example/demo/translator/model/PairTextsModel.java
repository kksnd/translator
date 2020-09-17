package com.example.demo.translator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="texts")
@Getter
@Setter
public class PairTextsModel {
    @Id
    @GeneratedValue
    private Integer id;
    private String source;
    private String target;
    private String sourceHead;
    private String targetHead;
}
