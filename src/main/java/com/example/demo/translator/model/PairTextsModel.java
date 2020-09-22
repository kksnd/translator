package com.example.demo.translator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

    private boolean isToJa;
    private boolean isToEn;

    private Date createdOn;
    private Date updatedOn;

    @PrePersist
    protected void onCreate() {
        setCreatedOn(new Date());
    }

    @PreUpdate
    protected void onUpdate() {
        setUpdatedOn(new Date());
    }
}
