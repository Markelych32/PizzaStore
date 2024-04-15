package ru.solonchev.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solonchev.backend.domain.Pizza;
import ru.solonchev.backend.domain.User;
import ru.solonchev.backend.exception.UserIsAlreadyExistException;
import ru.solonchev.backend.exception.UserNotFoundException;
import ru.solonchev.backend.repository.PizzaRepository;
import ru.solonchev.backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PizzaRepository pizzaRepository;

    public User addUser(User user) throws UserIsAlreadyExistException {
        if (userRepository.existsById(user.getId())) {
            throw new UserIsAlreadyExistException();
        }
        return userRepository.save(user);
    }

    public User findUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        for (Pizza pizza : user.get().getPizzas()) {
            pizza.removeUser(user.get());
            if (pizza.getUsers().isEmpty()) {
                pizzaRepository.delete(pizza);
            }
        }
        userRepository.deleteById(id);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
