package com.khesam.logger.common.logging;

import com.khesam.logger.common.exception.BaseException;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;
import java.lang.reflect.Parameter;
import java.util.*;

@ApplicationScoped
public class LogHandler {

    private final String[] INTERESTED_PACKAGE = new String[] {"com.khesam."};


    //https://www.eclipse.org/lists/aspectj-users/msg05677.html
    private final Set<Throwable> throwableRecord = Collections.newSetFromMap(new WeakHashMap<>());

    private final LogWriter logWriter;



    @Inject
    public LogHandler(LogWriter logWriter) {
        this.logWriter = logWriter;
    }

    public Object handle(
            InvocationContext context,
            EnableLogging.Layer layer,
            String incomeMessage,
            String outcomeMessage,
            String failureMessage,
            boolean withMeta,
            boolean withInput,
            boolean withOutput,
            boolean withException
    ) throws Exception {

        Object returnValue = null;
        Throwable throwable = null;

        long stopWatch = System.currentTimeMillis();
        final Date startTime = new Date();

        try {
            returnValue = context.proceed();
        } catch (Exception t) {
            throwable = t;
            throw t;
        } finally {
            long duration = System.currentTimeMillis() - stopWatch;
            LogDetails logDetails;

            logDetails = buildLogDetails(
                    layer, incomeMessage, outcomeMessage, failureMessage,
                    context.getTarget().getClass().getSuperclass().getName(),
                    context.getMethod().getName(),
                    withInput ? buildInputParams(context.getMethod().getParameters(), context.getParameters()) : null,
                    withOutput ? buildOutput(layer, returnValue) : null,
                    withException && throwable != null ? buildError(throwable) : null,
                    startTime,
                    duration
            );
            logWriter.write(logDetails, throwable == null, withMeta, withException);
        }

        return returnValue;
    }

    private LogDetails buildLogDetails(
            EnableLogging.Layer layer, String incomeMessage, String outcomeMessage, String failureMessage,
            String clazz, String method, Map<String, Object> input,
            Object output, ErrorLog error, Date startTime, long duration
    ) {
        return LogDetails.Builder.builder()
                .incomeMessage(incomeMessage)
                .outcomeMessage(outcomeMessage)
                .failureMessage(failureMessage)
                .layer(layer.name())
                .className(clazz)
                .methodName(method)
                .inputParams(input)
                .outputParams(output)
                .error(error)
                .startTime(startTime)
                .spentTime(duration)
                .build();
    }

    private Map<String, Object> buildInputParams(Parameter[] parameters, Object[] args) {
        final Map<String, Object> inputParams = new HashMap<>();

        for (int i = 0; i < parameters.length; i++) {
            inputParams.put(parameters[i].getName(), args[i]);
        }

        return inputParams;
    }

    private Object buildOutput(EnableLogging.Layer layer, Object returnValue) {
        if (layer == EnableLogging.Layer.CONTROLLER && returnValue instanceof Response) {
            return new SimpleControllerOutput((Response) returnValue);
        }
        return returnValue;
    }

    private ErrorLog buildError(Throwable throwable) {
        if (throwableRecord.contains(throwable)) {
            return null;
        }
        throwableRecord.add(throwable);

        ErrorLog.Builder errorLogBuilder = ErrorLog.Builder.builder()
                .causeException(throwable.getClass().getName())
                .causeExceptionStack(StackTraceUtil.getStackTrace(throwable, INTERESTED_PACKAGE));

        if (throwable instanceof BaseException) {
            BaseException exception = (BaseException) throwable;
            errorLogBuilder.cause(exception.errorResponse().getMessage());

            exception.getThrowable().ifPresent(e ->
                    errorLogBuilder
                            .originCause(e.getMessage())
                            .originCauseException(e.getClass().getName())
                            .originCauseExceptionStack(StackTraceUtil.getStackTrace(e, INTERESTED_PACKAGE)));

        } else if (throwable instanceof ResteasyWebApplicationException) {
            try {
                String errorResponse = ((ResteasyWebApplicationException) throwable).unwrap()
                        .getResponse().readEntity(String.class);
                errorLogBuilder.externalErrorBody(errorResponse);
            } catch (Exception ex) {
                errorLogBuilder.cause(throwable.getMessage());
            }
        } else if (throwable.getMessage() != null) {
            errorLogBuilder.cause(throwable.getMessage());
        }

        return errorLogBuilder.build();
    }


    private static class SimpleControllerOutput {
        private final int httpStatus;
        private final Object entity;

        public SimpleControllerOutput(Response response) {
            this.httpStatus = response.getStatus();
            this.entity = response.getEntity();
        }

        public int getHttpStatus() {
            return httpStatus;
        }

        public Object getEntity() {
            return entity;
        }
    }
}
