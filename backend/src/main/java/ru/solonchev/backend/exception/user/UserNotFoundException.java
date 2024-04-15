package ru.solonchev.backend.exception.user;

import ru.solonchev.backend.exception.ApiError;

public class UserNotFoundException extends ApiError {
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
