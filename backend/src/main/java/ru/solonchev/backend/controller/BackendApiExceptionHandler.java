package ru.solonchev.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.solonchev.backend.dto.exception.ApiErrorResponse;
import ru.solonchev.backend.exception.pizza.PizzaAlreadyExistAtUserException;
import ru.solonchev.backend.exception.pizza.PizzaAlreadyExistException;
import ru.solonchev.backend.exception.pizza.PizzaNotFoundAtUserException;
import ru.solonchev.backend.exception.pizza.PizzaNotFoundException;
import ru.solonchev.backend.exception.user.UserIsAlreadyExistException;
import ru.solonchev.backend.exception.user.UserNotFoundException;

@RestControllerAdvice
public class BackendApiExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> userAlreadyExist(UserIsAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toApiErrorResponse());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> userNotFound(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.toApiErrorResponse());
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> pizzaAlreadyExist(PizzaAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toApiErrorResponse());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> pizzaNotFound(PizzaNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.toApiErrorResponse());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> pizzaAlreadyExistsAtUser(PizzaAlreadyExistAtUserException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toApiErrorResponse());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> pizzaNotFoundAtUser(PizzaNotFoundAtUserException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toApiErrorResponse());
    }
}
