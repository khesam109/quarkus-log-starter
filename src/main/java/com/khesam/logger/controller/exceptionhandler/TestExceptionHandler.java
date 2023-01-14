package com.khesam.logger.controller.exceptionhandler;


import com.khesam.logger.common.exception.TestException;
import com.khesam.logger.common.logging.LogWriter;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TestExceptionHandler implements ExceptionMapper<TestException> {

    @Inject
    LogWriter logger;



    @Override
    public Response toResponse(TestException exception) {
        return Response.status(
                exception.httpCode()
        ).entity(
                exception.errorResponse()
        ).build();
    }
}
