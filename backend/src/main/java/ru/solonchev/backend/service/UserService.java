package ru.solonchev.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solonchev.backend.domain.Pizza;
import ru.solonchev.backend.domain.User;
import ru.solonchev.backend.exception.user.UserIsAlreadyExistException;
import ru.solonchev.backend.exception.user.UserNotFoundException;
import ru.solonchev.backend.repository.PizzaRepository;
import ru.solonchev.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PizzaRepository pizzaRepository;


    public User addUser(User user) throws UserIsAlreadyExistException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserIsAlreadyExistException();
        }
        user.setPizzas(new ArrayList<>());
        return userRepository.save(user);
    }

    public User findUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User findUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public boolean existUserByEmail(String email) {
        return userRepository.existsByEmail(email);
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

    public void deleteAllPizzasAtUser(Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        for (Pizza pizza : user.getPizzas()) {
            pizza.getUsers().remove(user);
        }
        user.setPizzas(new ArrayList<>());
        userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
