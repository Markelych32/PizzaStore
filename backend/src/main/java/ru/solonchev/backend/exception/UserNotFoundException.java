package ru.solonchev.backend.exception;

public class UserNotFoundException extends Error {
    private static final int code = 404;
    private static final String message = "User was not found";
    private static final String description = "User with such id was not found";

    public UserNotFoundException() {
        super(
                UserNotFoundException.code,
                UserNotFoundException.message,
                UserNotFoundException.description
        );
    }
}
