package ru.solonchev.backend.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        String email,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName
) {

}
