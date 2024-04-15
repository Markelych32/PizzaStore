package ru.solonchev.backend.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.solonchev.backend.data.TestUserData;
import ru.solonchev.backend.domain.User;
import ru.solonchev.backend.exception.UserIsAlreadyExistException;
import ru.solonchev.backend.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService underTest;

    @Test
    @SneakyThrows
    void addExistingUserShouldThrowException() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        final User user = TestUserData.getUser();
        assertThrows(UserIsAlreadyExistException.class, () -> underTest.addUser(user));
    }

    @Test
    @SneakyThrows
    void addNewUserShouldReturnUser() {
        final User user = TestUserData.getUser();
        when(userRepository.existsById(anyLong())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        final User actualUser = underTest.addUser(user);

        assertEquals(user, actualUser);
        Mockito.verify(userRepository, times(1)).save(any(User.class));
    }
}