package ru.solonchev.backend.exception.pizza;

import ru.solonchev.backend.exception.ApiError;

public class PizzaAlreadyExistException extends ApiError {
    private static final int code = 400;
    private static final String message = "Pizza already exists";
    private static final String description = "You are trying to add Pizza with already existing name";

    public PizzaAlreadyExistException() {
        super(code, message, description);
    }
}
