package ru.solonchev.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solonchev.backend.domain.User;
import ru.solonchev.backend.exception.UserIsAlreadyExistException;
import ru.solonchev.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User addUser(User user) throws UserIsAlreadyExistException {
        if (userRepository.existsById(user.getId())) {
            throw new UserIsAlreadyExistException();
        }
        return userRepository.save(user);
    }
}
