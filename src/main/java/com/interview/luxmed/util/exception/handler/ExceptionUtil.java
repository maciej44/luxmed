package com.interview.luxmed.util.exception.handler;

import com.interview.luxmed.util.exception.EntityAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;

public final class ExceptionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);
    public static Exception handleDbActionExecutionException(DbActionExecutionException e, Object obj) {
        // handle potential SQL errors
        logger.error("Error while persisting: " + obj + ", MESSAGE: " + e.getCause().getMessage());
        if (e.getCause().getMessage().contains("duplicate key value violates unique constraint")) {
            return new EntityAlreadyExistsException("Object already exists in database.");
        }
        return new Exception(e);
    }
}
