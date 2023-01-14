package com.khesam.logger.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khesam.logger.common.logging.complementary.MaskMe;

import java.io.Serializable;

public class TestHappyRequestData implements Serializable {

    @JsonProperty("firstname")
    @MaskMe
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
