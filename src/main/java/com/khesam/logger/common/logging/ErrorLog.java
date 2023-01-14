package com.khesam.logger.common.logging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorLog {
    @JsonProperty("Cause") private String cause;
    @JsonProperty("CauseException") private String causeException;
    @JsonProperty("CauseExceptionStack") @JsonIgnore private String causeExceptionStack;

    @JsonProperty("OriginCause") private String originCause;
    @JsonProperty("OriginCauseException") private String originCauseException;
    @JsonProperty("OriginCauseExceptionStack") @JsonIgnore private String originCauseExceptionStack;


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

    public static final class ErrorLogBuilder {
        private String cause;
        private String causeException;
        private String causeExceptionStack;
        private String originCause;
        private String originCauseException;
        private String originCauseExceptionStack;

        private ErrorLogBuilder() {
        }

        public static ErrorLogBuilder anErrorLog() {
            return new ErrorLogBuilder();
        }

        public ErrorLogBuilder withCause(String cause) {
            this.cause = cause;
            return this;
        }

        public ErrorLogBuilder withCauseException(String causeException) {
            this.causeException = causeException;
            return this;
        }

        public ErrorLogBuilder withCauseExceptionStack(String causeExceptionStack) {
            this.causeExceptionStack = causeExceptionStack;
            return this;
        }

        public ErrorLogBuilder withOriginCause(String originCause) {
            this.originCause = originCause;
            return this;
        }

        public ErrorLogBuilder withOriginCauseException(String originCauseException) {
            this.originCauseException = originCauseException;
            return this;
        }

        public ErrorLogBuilder withOriginCauseExceptionStack(String originCauseExceptionStack) {
            this.originCauseExceptionStack = originCauseExceptionStack;
            return this;
        }

        public ErrorLog build() {
            ErrorLog errorLog = new ErrorLog();
            errorLog.originCauseException = this.originCauseException;
            errorLog.causeExceptionStack = this.causeExceptionStack;
            errorLog.originCause = this.originCause;
            errorLog.cause = this.cause;
            errorLog.originCauseExceptionStack = this.originCauseExceptionStack;
            errorLog.causeException = this.causeException;
            return errorLog;
        }
    }
}
