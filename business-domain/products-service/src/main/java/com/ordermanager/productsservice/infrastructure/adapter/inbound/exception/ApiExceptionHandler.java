package com.ordermanager.productsservice.infrastructure.adapter.inbound.exception;


import com.paymentchain.exception.model.ApiExceptionResponse;
import com.paymentchain.exception.model.ExceptionInfo;
import com.paymentchain.exception.type.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNullElse;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ApiExceptionResponse> handleBusinessRuleException(CommonException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getErrorType().body(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("Error validating request {}", getExceptionInfo(ex));
        ApiExceptionResponse response = new ApiExceptionResponse(
            ex.getMessage(),
            "1025",
            "Error validating request",
            "VALIDATION"
        );
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> handleUnknownHostException(Exception ex) {
        log.error("Uncaught exception {}", getExceptionInfo(ex));
        ApiExceptionResponse response = new ApiExceptionResponse(
            ex.getMessage(),
            "1024",
            "Input Output error",
            "TECHNICAL"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public ExceptionInfo getExceptionInfo(Exception exception) {
        StackTraceElement origin = exception.getStackTrace()[0];

        ExceptionInfo exceptionInfo = ExceptionInfo.builder()
            .exceptionMessage(exception.getMessage())
            .causeMessage(Optional.ofNullable(exception.getCause()).map(Throwable::getMessage).orElse(""))
            .exceptionType(exception.getClass().getName())
            .build();

        if (!isNull(origin)) {
            exceptionInfo = exceptionInfo.toBuilder()
                .originClassName(origin.getClassName())
                .originMethodName(origin.getMethodName())
                .build();
        }
        return exceptionInfo;
    }
}
