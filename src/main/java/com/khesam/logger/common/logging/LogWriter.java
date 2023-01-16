package com.khesam.logger.common.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khesam.logger.common.di.LoggerObjectMapperQualifier;
import com.khesam.logger.common.util.StringUtils;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


@ApplicationScoped
public class LogWriter {

    private final Logger logger;
    private final ObjectMapper objectMapper;

    @Inject
    public LogWriter(Logger logger, @LoggerObjectMapperQualifier ObjectMapper objectMapper) {
        this.logger = logger;
        this.objectMapper = objectMapper;
    }

    public void write(LogDetails logDetails, boolean isHappy, boolean withMeta, boolean withException) {
        try {
            if (isHappy) {
                logger.info(buildLogMessage(logDetails, true, withMeta, withException));
            } else {
                logger.error(buildLogMessage(logDetails, false, withMeta, withException));
            }
        } catch (JsonProcessingException ex) {
            logger.error(String.format("%s: %s", "Json parser has been failed due to", ex.getMessage()));
        }

    }

    private String buildLogMessage(LogDetails logDetails, boolean isHappy, boolean withMeta, boolean withException) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("\tLayer: ").append(logDetails.getLayer()).append("\n");
        sb.append("\tClass: ").append(logDetails.getClazz()).append("\n");
        sb.append("\tMethod: ").append(logDetails.getMethodName()).append("\n");
        sb.append("\tSpentTime: ").append(logDetails.getSpentTime()).append("\n");
        sb.append("\n").append("[LOG DETAIL]").append("\n");

        if (StringUtils.notEmpty(logDetails.getIncomeMessage())) {
            sb.append(">>>").append(logDetails.getIncomeMessage()).append("\n");
        }

        if (isHappy && StringUtils.notEmpty(logDetails.getOutcomeMessage())) {
            sb.append("<<<").append(logDetails.getOutcomeMessage()).append("\n");
        }
        if (!isHappy && StringUtils.notEmpty(logDetails.getFailureMessage())) {
            sb.append("###").append(logDetails.getFailureMessage()).append("\n");
        }

        if (withMeta) {
            sb.append(objectMapper.writeValueAsString(logDetails));
        }

        if (withException && logDetails.getError() != null) {

            String originStack = logDetails.getError().getOriginCauseExceptionStack();
            String causeStack = logDetails.getError().getCauseExceptionStack();

            if (originStack != null) {
                sb.append("\n\n").append("[ORIGIN STACK]");
                sb.append(logDetails.getError().getOriginCauseExceptionStack());
            }

            if (causeStack != null) {
                sb.append("\n").append("[CAUSE STACK]");
                sb.append(logDetails.getError().getCauseExceptionStack());
            }
        }

        return sb.toString();
    }
}
