package com.axon.axondemo.exception;

import org.axonframework.common.AxonException;

public class OrderCreateFailedException extends AxonException {
    public OrderCreateFailedException(String message) {
        super(message);
    }
}
