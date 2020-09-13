package com.example.demo.translator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "response")
@Getter
@Setter
public class TranslatedResponseModel {
    @XmlElement(name = "text")
    private String text;
    @XmlElement(name = "error_message")
    private String error_message;
    @XmlElement(name = "status")
    private int status;
}