package com.khesam.logger.common.logging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorLog {
    @JsonProperty("Cause") private final String cause;
    @JsonProperty("CauseException") private final String causeException;
    @JsonIgnore private final String causeExceptionStack;
    @JsonProperty("OriginCause") private final String originCause;
    @JsonProperty("OriginCauseException") private final String originCauseException;
    @JsonIgnore private final String originCauseExceptionStack;

    private ErrorLog(Builder builder) {
        cause = builder.cause;
        causeException = builder.causeException;
        causeExceptionStack = builder.causeExceptionStack;
        originCause = builder.originCause;
        originCauseException = builder.originCauseException;
        originCauseExceptionStack = builder.originCauseExceptionStack;
    }

    public String getCause() {
        return cause;
    }

    public String getCauseException() {
        return causeException;
    }

    public String getCauseExceptionStack() {
        return causeExceptionStack;
    }

    public String getOriginCause() {
        return originCause;
    }

    public String getOriginCauseException() {
        return originCauseException;
    }

    public String getOriginCauseExceptionStack() {
        return originCauseExceptionStack;
    }

    public static final class Builder {
        private String cause;
        private String causeException;
        private String causeExceptionStack;
        private String originCause;
        private String originCauseException;
        private String originCauseExceptionStack;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder cause(String val) {
            cause = val;
            return this;
        }

        public Builder causeException(String val) {
            causeException = val;
            return this;
        }

        public Builder causeExceptionStack(String val) {
            causeExceptionStack = val;
            return this;
        }

        public Builder originCause(String val) {
            originCause = val;
            return this;
        }

        public Builder originCauseException(String val) {
            originCauseException = val;
            return this;
        }

        public Builder originCauseExceptionStack(String val) {
            originCauseExceptionStack = val;
            return this;
        }

        public ErrorLog build() {
            return new ErrorLog(this);
        }
    }
}
