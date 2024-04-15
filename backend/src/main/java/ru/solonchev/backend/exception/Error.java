package ru.solonchev.backend.exception;

public class Error extends Exception {
    private final int code;
    private final String message;
    private final String description;

    public Error(int code, String message, String description) {
        super(message);
        this.code = code;
        this.message = message;
        this.description = description;
    }

}
