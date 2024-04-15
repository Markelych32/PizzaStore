package ru.solonchev.backend.data;

import ru.solonchev.backend.domain.User;

public final class TestUserData {
    private TestUserData() {

    }

    public static User getUser() {
        return User.builder()
                .id(13L)
                .email("test32@gmail.com")
                .firstName("Mark")
                .secondName("Solonchev")
                .password("123")
                .build();
    }
}
