package com.khesam.logger.common.logging.layer;

import com.khesam.logger.common.logging.EnableLogging;
import com.khesam.logger.common.logging.LogHandler;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Priority(10)
@Interceptor
@EnableLogging(EnableLogging.Layer.UTILITY)
public class UtilityLogInterceptor extends BaseLogInterceptor {

    @ConfigProperty(name = "log.profile")
    String logProfile;

    @Inject
    public UtilityLogInterceptor(LogHandler logHandler) {
        super(logHandler);
    }

    @AroundInvoke
    public Object aroundInvoke(InvocationContext context) throws Exception {
        return handle(context, logProfile);
    }
}
