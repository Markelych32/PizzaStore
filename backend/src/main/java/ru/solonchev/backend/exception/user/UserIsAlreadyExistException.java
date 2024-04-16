package ru.solonchev.backend.exception.user;

import ru.solonchev.backend.exception.ApiError;

public class UserIsAlreadyExistException extends ApiError {
    private static final int code = 400;
    private static final String message = "User is already exist";
    private static final String description = "You are trying to add User with already existing email";


    public UserIsAlreadyExistException() {
        super(
                UserIsAlreadyExistException.code,
                UserIsAlreadyExistException.message,
                UserIsAlreadyExistException.description
        );
    }
}
