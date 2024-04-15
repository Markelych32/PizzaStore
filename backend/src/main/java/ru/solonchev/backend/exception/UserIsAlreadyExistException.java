package ru.solonchev.backend.exception;

public class UserIsAlreadyExistException extends Error {
    private static final int code = 400;
    private static final String message = "User is already exist";
    private static final String description = "You are trying to add User with already existing Id";


    public UserIsAlreadyExistException() {
        super(
                UserIsAlreadyExistException.code,
                UserIsAlreadyExistException.message,
                UserIsAlreadyExistException.description
        );
    }

    public UserIsAlreadyExistException(int code, String message, String description) {
        super(code, message, description);
    }
}
