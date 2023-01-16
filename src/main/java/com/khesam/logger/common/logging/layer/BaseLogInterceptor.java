package com.khesam.logger.common.logging.layer;


import com.khesam.logger.common.logging.EnableLogging;
import com.khesam.logger.common.logging.LogHandler;
import com.khesam.logger.common.logging.LogMe;
import com.khesam.logger.common.util.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.interceptor.InvocationContext;

public abstract class BaseLogInterceptor {

    @ConfigProperty(name = "log.profile")
    String logProfile;
    private final LogHandler logHandler;

    public BaseLogInterceptor(LogHandler logHandler) {
        this.logHandler = logHandler;
    }

    protected Object handle(InvocationContext context) throws Exception {
        EnableLogging enableLogging = getEnableLoggingInfo(context);
        if (StringUtils.notEmpty(enableLogging.profile()) && !enableLogging.profile().equalsIgnoreCase(logProfile)) {
            return context.proceed();
        }
        LogMe logMe = getLogMeInfo(context);
        if (logMe == null) {
            return context.proceed();
        }
        return logHandler.handle(
                context,
                enableLogging.value(),
                logMe.incomeMessage(),
                logMe.outcomeMessage(),
                logMe.failureMessage(),
                logMe.withMeta(),
                logMe.withInput(),
                logMe.withOutput(),
                logMe.withException()
        );
    }

    private EnableLogging getEnableLoggingInfo(InvocationContext context) {
        return context.getTarget().getClass().getSuperclass().getAnnotation(EnableLogging.class);
    }

    private LogMe getLogMeInfo(InvocationContext context) {
        return context.getMethod().getAnnotation(LogMe.class);
    }
}
