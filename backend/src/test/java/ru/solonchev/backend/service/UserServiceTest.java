package ru.solonchev.backend.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.solonchev.backend.data.TestUserData;
import ru.solonchev.backend.domain.User;
import ru.solonchev.backend.exception.UserIsAlreadyExistException;
import ru.solonchev.backend.exception.UserNotFoundException;
import ru.solonchev.backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
        final User user = TestUserData.getUser1();
        assertThrows(UserIsAlreadyExistException.class, () -> underTest.addUser(user));
    }

    @Test
    @SneakyThrows
    void addNewUserShouldReturnUser() {
        final User user = TestUserData.getUser1();
        when(userRepository.existsById(anyLong())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        final User actualUser = underTest.addUser(user);

        assertEquals(user, actualUser);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @SneakyThrows
    void deleteAbsentUserShouldThrowException() {
        final Long userId = 1L;
        final User user = TestUserData.getUser1();

        when(userRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> underTest.deleteUser(userId));
        verify(userRepository, times(0)).deleteById(anyLong());
    }

    @Test
    @SneakyThrows
    void deleteExistingUserShouldDeleteUser() {
        final Long userId = 1L;

        when(userRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(userRepository).deleteById(anyLong());

        underTest.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getAllUsersShouldReturnEmptyListWhenNoUserExists() {
        when(userRepository.findAll()).thenReturn(List.of());

        final List<User> expectedUsers = List.of();
        final List<User> actualUsers = underTest.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void getAllUsersShouldReturnUsers() {
        List<User> users = List.of(TestUserData.getUser1(), TestUserData.getUser2());
        when(userRepository.findAll()).thenReturn(users);
        final List<User> actualUsers = underTest.getAllUsers();
        assertEquals(users, actualUsers);
    }

    @Test
    @SneakyThrows
    void findUserByIdAbsentUserShouldThrowException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> underTest.findUserById(1L));
    }

    @Test
    @SneakyThrows
    void findUserByIdShouldReturnUser() {
        final User user = TestUserData.getUser1();
        final Long userId = TestUserData.user1Id();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        final User actualUser = underTest.findUserById(userId);
        assertEquals(user, actualUser);
        verify(userRepository, times(1)).findById(anyLong());
    }
}
