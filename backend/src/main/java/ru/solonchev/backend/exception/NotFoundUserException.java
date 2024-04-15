package ru.solonchev.backend.exception;

public class NotFoundUserException extends Error {
    private static final int code = 404;
    private static final String message = "User was not found";
    private static final String description = "User with such id was not found";

    public NotFoundUserException() {
        super(
                NotFoundUserException.code,
                NotFoundUserException.message,
                NotFoundUserException.description
        );
    }
}
