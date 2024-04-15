package ru.solonchev.backend.exception;

public class ApiError extends Exception {
    private final int code;
    private final String message;
    private final String description;

    public ApiError(int code, String message, String description) {
        super(message);
        this.code = code;
        this.message = message;
        this.description = description;
    }

}
