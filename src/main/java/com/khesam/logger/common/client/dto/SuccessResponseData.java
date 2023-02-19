package com.khesam.logger.common.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SuccessResponseData implements Serializable {

    @JsonProperty("message") private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
