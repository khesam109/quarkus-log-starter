package com.khesam.logger.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TestResponseData implements Serializable {

    @JsonProperty("greeting") private final String greeting;

    @JsonCreator
    public TestResponseData(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }
}
