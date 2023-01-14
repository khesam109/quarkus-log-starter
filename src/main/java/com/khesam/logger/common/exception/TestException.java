package com.khesam.logger.common.exception;


import com.khesam.logger.controller.dto.ErrorResponse;

public class TestException extends BaseException {

    private final TestResponseStatus responseStatus;

    public TestException(TestResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public TestException(Throwable throwable, TestResponseStatus responseStatus) {
        super(throwable);
        this.responseStatus = responseStatus;
    }

    @Override
    public int httpCode() {
        return responseStatus.getHttpCode();
    }

    @Override
    public ErrorResponse errorResponse() {
        return responseStatus.getErrorResponse();
    }
}
