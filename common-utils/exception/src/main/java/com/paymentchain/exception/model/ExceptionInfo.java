package com.paymentchain.exception.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record ExceptionInfo(
    String originClassName,
    String originMethodName,
    String exceptionMessage,
    String causeMessage,
    String exceptionType
) {
}
