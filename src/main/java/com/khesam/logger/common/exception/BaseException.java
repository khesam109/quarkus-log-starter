package com.khesam.logger.common.exception;


import com.khesam.logger.controller.dto.ErrorResponse;

import java.util.Optional;

public abstract class BaseException extends RuntimeException {

    private final Optional<Throwable> throwable;

    public BaseException() {
        this.throwable = Optional.empty();
    }

    public BaseException(Throwable throwable) {
        this.throwable = Optional.of(throwable);
    }

    public abstract int httpCode();
    public abstract ErrorResponse errorResponse();

    public Optional<Throwable> getThrowable() {
        return throwable;
    }
}
