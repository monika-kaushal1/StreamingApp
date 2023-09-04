package com.hellofresh.StreamingApplication.exceptions;

import lombok.Getter;

/**
 * Wrapper Exception to all Validation Exceptions.
 */
@Getter
public class ValidationException extends RuntimeException {
    private final Integer errorCode;
    private final String errorMessage;

    public ValidationException(ErrorCodes errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public ValidationException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
