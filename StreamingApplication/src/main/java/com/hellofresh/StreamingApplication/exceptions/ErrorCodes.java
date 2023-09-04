package com.hellofresh.StreamingApplication.exceptions;

import lombok.Getter;

/**
 * ENUM to hold various internal error codes and messages for Exceptions.
 */
@Getter
public enum ErrorCodes {
    EMPTY_EVENT(400, "Empty event message"),
    EVENT_FORMAT_NOT_PROPER(400, "Event format not proper"),
    MISSING_PARAMETER_X(400, "Parameter missing - x"),
    MISSING_PARAMETER_TIMESTAMP(400, "Parameter missing - timestamp"),
    MISSING_PARAMETER_Y(400, "Parameter missing - y"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final Integer errorCode;
    private final String errorMessage;

    ErrorCodes(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
