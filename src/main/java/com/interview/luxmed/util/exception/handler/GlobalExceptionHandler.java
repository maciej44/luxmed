package com.interview.luxmed.util.exception.handler;

import com.interview.luxmed.util.exception.EntityAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public final class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<String> handleEntityAlreadyExistsException(
            EntityAlreadyExistsException e, WebRequest request) {
        logException(e, request);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e, WebRequest request) {
        logException(e, request);
        return new ResponseEntity<>("An unexpected error occurred.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logException(Exception e, WebRequest request) {
        logger.error("Exception occurred: {}, Request Details: {}",
                e.getMessage(), request.getDescription(false),
                e);
    }
}