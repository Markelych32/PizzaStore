package ru.solonchev.backend.exception;

import ru.solonchev.backend.dto.exception.ApiErrorResponse;

public class ApiError extends RuntimeException {
    private final int code;
    private final String message;
    private final String description;

    public ApiError(int code, String message, String description) {
        super(message);
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public ApiErrorResponse toApiErrorResponse() {
        return ApiErrorResponse.builder()
                .code(code)
                .message(message)
                .description(description)
                .build();
    }
}
