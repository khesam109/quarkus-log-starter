package com.khesam.logger.common.exception;


import com.khesam.logger.controller.dto.ErrorResponse;

import java.util.Optional;

public abstract class BaseException extends RuntimeException {

    private final Throwable throwable;

    public BaseException() {
        throwable = null;
    }

    public BaseException(Throwable throwable) {
        this.throwable = throwable;
    }

    public abstract int httpCode();
    public abstract ErrorResponse errorResponse();

    public final Optional<Throwable> getThrowable() {
        return Optional.ofNullable(this.throwable);
    }
}
