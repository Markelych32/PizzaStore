package ru.solonchev.backend.data;

import ru.solonchev.backend.domain.Pizza;
import ru.solonchev.backend.domain.User;

import java.util.ArrayList;

public final class TestData {
    private TestData() {

    }

    public static Long user1Id() {
        return 13L;
    }

    public static User getUser1() {
        return User.builder()
                .id(13L)
                .email("test32@gmail.com")
                .firstName("Mark")
                .secondName("Solonchev")
                .password("123")
                .build();
    }

    public static User getUser2() {
        return User.builder()
                .id(19L)
                .email("test52@gmail.com")
                .firstName("Artem")
                .secondName("Loving")
                .password("321")
                .build();
    }

    public static Pizza getPizza1() {
        return Pizza.builder()
                .id(1L)
                .name("Four Cheese")
                .price(15.0)
                .description("Tasty pizza")
                .imgLink("test_link")
                .build();
    }
    public static Pizza getPizza2() {
        return Pizza.builder()
                .id(2L)
                .name("Margarita")
                .price(18.50)
                .description("Cool && tasty")
                .imgLink("another_test_link")
                .build();
    }
}
