package com.example.nikola.exception;

import java.util.Optional;

public class MyException extends Throwable {
    private final String message;

    public MyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
