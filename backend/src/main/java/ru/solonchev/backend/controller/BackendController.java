package ru.solonchev.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import ru.solonchev.backend.domain.Pizza;
import ru.solonchev.backend.domain.User;
import ru.solonchev.backend.dto.pizza.PizzaAddRequest;
import ru.solonchev.backend.dto.user.request.UserAddRequest;
import ru.solonchev.backend.dto.user.response.UserResponse;
import ru.solonchev.backend.service.EmailService;
import ru.solonchev.backend.service.PizzaService;
import ru.solonchev.backend.service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/pizza-store")
@RequiredArgsConstructor
public class BackendController {

    private final UserService userService;
    private final PizzaService pizzaService;
    private final EmailService emailService;

    private User userAddRequestToUser(UserAddRequest userAddRequest) {
        return User.builder()
                .email(userAddRequest.getEmail())
                .password(userAddRequest.getPassword())
                .firstName(userAddRequest.getFirstName())
                .lastName(userAddRequest.getLastName())
                .build();
    }

    private Pizza pizzaAddRequestToPizza(PizzaAddRequest pizzaAddRequest) {
        return Pizza.builder()
                .name(pizzaAddRequest.getName())
                .price(pizzaAddRequest.getPrice())
                .description(pizzaAddRequest.getDescription())
                .imgLink(pizzaAddRequest.getImgLink())
                .build();
    }

    @PostMapping("/users")
    public ResponseEntity<Void> addUser(
            @RequestBody UserAddRequest user
    ) {
        userService.addUser(userAddRequestToUser(user));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/{user_id}/email")
    public ResponseEntity<Void> sendEmail(
            @PathVariable("user_id") Long userId,
            @RequestHeader("Receiver") String receiver
    ) {
        emailService.sendEmail(userId, receiver);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pizzas")
    public ResponseEntity<Void> addPizza(
            @RequestBody PizzaAddRequest pizzaAddRequest
    ) {
        pizzaService.createPizza(pizzaAddRequestToPizza(pizzaAddRequest));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/{userId}/pizza/{pizzaId}")
    public ResponseEntity<Void> addPizzaToUser(
            @PathVariable("userId") Long userId,
            @PathVariable("pizzaId") Long pizzaId
    ) {
        pizzaService.addPizzaToUser(userId, pizzaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        return ResponseEntity.ok().body(new UserResponse(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        ));
    }

    @GetMapping("/pizzas")
    public ResponseEntity<List<Pizza>> getAllPizzas() {
        return ResponseEntity.ok(
                pizzaService.getAllPizzas()
        );
    }

    @GetMapping("/pizzas/order/name")
    public ResponseEntity<List<Pizza>> getAllPizzasOrderedByName() {
        return ResponseEntity.ok().body(pizzaService.getPizzasOrderedByName());
    }

    @GetMapping("/pizzas/order/price")
    public ResponseEntity<List<Pizza>> getAllPizzasOrderedByPrice() {
        return ResponseEntity.ok().body(pizzaService.getPizzasOrderedByPrice());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(
                userService.getAllUsers()
        );
    }

    @GetMapping("/users/{userId}/pizzas")
    public ResponseEntity<List<Pizza>> getPizzasOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(pizzaService.getPizzasOfUser(userId));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/pizzas/{pizzaId}")
    public ResponseEntity<Void> deletePizzaById(@PathVariable Long pizzaId) {
        pizzaService.deletePizzaById(pizzaId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{user_id}/pizza/{pizza_id}")
    public ResponseEntity<Void> deletePizzaOfUser(
            @PathVariable("user_id") Long userId,
            @PathVariable("pizza_id") Long pizzaId
    ) {
        pizzaService.deletePizzaAtUser(userId, pizzaId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{user_id}/pizza")
    public ResponseEntity<Void> deleteAllPizzasOfUser(@PathVariable("user_id") Long userId) {
        userService.deleteAllPizzasAtUser(userId);
        return ResponseEntity.ok().build();
    }
}
