package ru.solonchev.backend.exception.pizza;

import ru.solonchev.backend.exception.ApiError;

public class PizzaAlreadyExistAtUserException extends ApiError {
    private static final int code = 400;
    private static final String message = "Pizza already exist at user";
    private static final String description = "Pizza already exist at user";

    public PizzaAlreadyExistAtUserException() {
        super(code, message, description);
    }
}
