package com.khesam.logger.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.khesam.logger.common.logging.complementary.MaskMe;

import java.io.Serializable;

public class TestResponseData implements Serializable {

    @MaskMe
    @JsonProperty("greeting")
    private final String greeting;

    @JsonCreator
    public TestResponseData(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }
}
