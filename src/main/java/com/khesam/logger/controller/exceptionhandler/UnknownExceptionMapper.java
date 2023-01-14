package com.khesam.logger.controller.exceptionhandler;


import com.khesam.logger.common.exception.TestResponseStatus;
import com.khesam.logger.common.logging.EnableLogging;
import com.khesam.logger.common.logging.LogMe;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@EnableLogging(value = EnableLogging.Layer.CONTROLLER)
@Provider
public class UnknownExceptionMapper implements ExceptionMapper<RuntimeException> {

    @LogMe
    @Override
    public Response toResponse(RuntimeException exception) {

        return Response.status(
                TestResponseStatus.UNKNOWN.getHttpCode()
        ).entity(
                TestResponseStatus.UNKNOWN.getErrorResponse()
        ).build();
    }
}
