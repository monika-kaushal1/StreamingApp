package com.hellofresh.StreamingApplication.exceptions;

import lombok.Getter;

/**
 * Wrapper Exception to all types of exceptions.
 */
@Getter
public class InternalServerException extends RuntimeException {
    private final Integer errorCode;
    private final String errorMessage;

    public InternalServerException(ErrorCodes errorCode, Throwable e) {
        super(errorCode.getErrorMessage(), e);
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public InternalServerException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
