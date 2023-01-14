package com.khesam.logger.common.logging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogDetails {

    @JsonIgnore private final String incomeMessage;
    @JsonIgnore private final String outcomeMessage;
    @JsonIgnore private final String failureMessage;

    @JsonProperty("Layer") private final String layer;
    @JsonProperty("Class") private final String clazz;
    @JsonProperty("Method") private final String methodName;
    @JsonProperty("Input") private final Map<String, Object> inputParams;
    @JsonProperty("Output") private final Object outputParams;
    @JsonProperty("Error") private final ErrorLog error;
    @JsonProperty("StartTime") private final Date startTime;
    @JsonProperty("SpentTime") private final long spentTime;

    private LogDetails(Builder builder) {
        incomeMessage = builder.incomeMessage;
        outcomeMessage = builder.outcomeMessage;
        failureMessage = builder.failureMessage;
        layer = builder.layer;
        clazz = builder.clazz;
        methodName = builder.methodName;
        inputParams = builder.inputParams;
        outputParams = builder.outputParams;
        error = builder.error;
        startTime = builder.startTime;
        spentTime = builder.spentTime;
    }

    public String getIncomeMessage() {
        return incomeMessage;
    }

    public String getOutcomeMessage() {
        return outcomeMessage;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public String getLayer() {
        return layer;
    }

    public String getClazz() {
        return clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public Map<String, Object> getInputParams() {
        return inputParams;
    }

    public Object getOutputParams() {
        return outputParams;
    }

    public ErrorLog getError() {
        return error;
    }

    public Date getStartTime() {
        return startTime;
    }

    public long getSpentTime() {
        return spentTime;
    }


    public static final class Builder {

        private String incomeMessage;
        private String outcomeMessage;
        private String failureMessage;
        private String layer;
        private String clazz;
        private String methodName;
        private Map<String, Object> inputParams;
        private Object outputParams;
        private ErrorLog error;
        private Date startTime;
        private long spentTime;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder incomeMessage(String val) {
            incomeMessage = val;
            return this;
        }

        public Builder outcomeMessage(String val) {
            outcomeMessage = val;
            return this;
        }

        public Builder failureMessage(String val) {
            failureMessage = val;
            return this;
        }

        public Builder layer(String val) {
            layer = val;
            return this;
        }

        public Builder className(String val) {
            clazz = val;
            return this;
        }

        public Builder methodName(String val) {
            methodName = val;
            return this;
        }

        public Builder inputParams(Map<String, Object> val) {
            inputParams = val;
            return this;
        }

        public Builder outputParams(Object val) {
            outputParams = val;
            return this;
        }

        public Builder error(ErrorLog val) {
            error = val;
            return this;
        }

        public Builder startTime(Date val) {
            startTime = val;
            return this;
        }

        public Builder spentTime(long val) {
            spentTime = val;
            return this;
        }

        public LogDetails build() {
            return new LogDetails(this);
        }
    }


}
