package com.khesam.logger.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TestRequestData implements Serializable {

    @JsonProperty("id") private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
