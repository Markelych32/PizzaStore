package ru.solonchev.backend.exception.pizza;

import ru.solonchev.backend.exception.ApiError;

public class PizzaNotFoundAtUserException extends ApiError {
    private static final int code = 400;
    private static final String message = "Pizza not found at User";
    private static final String description = "Pizza not found at User";

    public PizzaNotFoundAtUserException() {
        super(code, message, description);
    }
}
