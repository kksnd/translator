package com.example.demo.translator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TranslatedJsonResponseModel {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public class Response {
        private String text;
        private String error_message;
        private int status;
    }

    private Response response;
}