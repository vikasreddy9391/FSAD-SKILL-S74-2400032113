package com.klu.skillab9.exception;

@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }
}