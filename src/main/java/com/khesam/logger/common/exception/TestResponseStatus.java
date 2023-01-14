package com.khesam.logger.common.exception;


import com.khesam.logger.controller.dto.ErrorResponse;

public enum TestResponseStatus {

    USER_NOT_FOUND ("User not found", 1000, 404),
    BAD_REQUEST ("Bas request", 1001, 400),
    UNKNOWN("unknown", -1, 500);

    private final String message;
    private final int internalCode;
    private final int httpCode;

    TestResponseStatus(String message, int internalCode, int httpCode) {
        this.message = message;
        this.internalCode = internalCode;
        this.httpCode = httpCode;
    }

    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(internalCode, message);
    }

    public int getHttpCode() {
        return httpCode;
    }
}
