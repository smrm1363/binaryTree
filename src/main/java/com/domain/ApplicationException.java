package com.domain;

/**
 * This is an exception will throw with the application
 */
public class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }
}
