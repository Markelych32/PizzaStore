package ru.solonchev.backend.exception.pizza;

import ru.solonchev.backend.exception.ApiError;

public class PizzaNotFoundException extends ApiError {
    private static final int code = 404;
    private static final String message = "Pizza Not Found";
    private static final String description = "Pizza with such id was not found";

    public PizzaNotFoundException() {
        super(
                PizzaNotFoundException.code,
                PizzaNotFoundException.message,
                PizzaNotFoundException.description
        );
    }
}
