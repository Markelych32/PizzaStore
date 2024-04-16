package ru.solonchev.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.solonchev.backend.domain.User;
import ru.solonchev.backend.dto.user.request.UserAddRequest;
import ru.solonchev.backend.service.PizzaService;
import ru.solonchev.backend.service.UserService;

@RestController
@RequestMapping("/pizza-store")
@RequiredArgsConstructor
@Slf4j
public class BackendController {

    private final UserService userService;
    private final PizzaService pizzaService;

    private User userAddRequestToUser(UserAddRequest userAddRequest) {
        return User.builder()
                .email(userAddRequest.getEmail())
                .password(userAddRequest.getPassword())
                .firstName(userAddRequest.getFirstName())
                .lastName(userAddRequest.getLastName())
                .build();
    }

    @PostMapping("/users")
    public ResponseEntity<Void> addUser(
            @RequestBody UserAddRequest user
    ) {
        userService.addUser(userAddRequestToUser(user));
        return ResponseEntity.ok().build();
    }

}
