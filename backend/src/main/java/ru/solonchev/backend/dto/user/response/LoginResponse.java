package ru.solonchev.backend.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("email")
    private String email;
}
