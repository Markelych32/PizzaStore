package ru.solonchev.backend.data;

import ru.solonchev.backend.domain.User;

public final class TestUserData {
    private TestUserData() {

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
}
