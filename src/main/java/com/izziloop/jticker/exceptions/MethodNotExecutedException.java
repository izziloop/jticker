package com.izziloop.jticker.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MethodNotExecutedException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(MethodNotExecutedException.class.getSimpleName());

    public MethodNotExecutedException(String message) {
        super(message);
    }

}
