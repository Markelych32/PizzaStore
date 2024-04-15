package ru.solonchev.backend.exception.pizza;

import ru.solonchev.backend.exception.Error;

public class PizzaAlreadyExistException extends Error {
    private static final int code = 400;
    private static final String message = "Pizza already exists";
    private static final String description = "You are trying to add Pizza with already existing Id";

    public PizzaAlreadyExistException() {
        super(
                PizzaAlreadyExistException.code,
                PizzaAlreadyExistException.message,
                PizzaAlreadyExistException.description
        );
    }
}
