package ru.solonchev.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solonchev.backend.domain.Pizza;
import ru.solonchev.backend.exception.ApiError;
import ru.solonchev.backend.exception.pizza.PizzaAlreadyExistException;
import ru.solonchev.backend.exception.pizza.PizzaNotFoundException;
import ru.solonchev.backend.exception.user.UserNotFoundException;
import ru.solonchev.backend.repository.PizzaRepository;
import ru.solonchev.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final UserRepository userRepository;

    public Pizza createPizza(Pizza pizza) throws PizzaAlreadyExistException {
        if (pizzaRepository.existsById(pizza.getId())) {
            throw new PizzaAlreadyExistException();
        }
        pizza.setUsers(new ArrayList<>());
        return pizzaRepository.save(pizza);
    }

    public Pizza changePizza(Pizza pizza) throws PizzaNotFoundException {
        if (!pizzaRepository.existsById(pizza.getId())) {
            throw new PizzaNotFoundException();
        }
        return pizzaRepository.save(pizza);
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public void deletePizzaById(Long id) throws PizzaNotFoundException {
        if (!pizzaRepository.existsById(id)) {
            throw new PizzaNotFoundException();
        }
        pizzaRepository.deleteById(id);
    }

    public List<Pizza> getPizzasOfUser(Long userId) throws UserNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }
        return userRepository.findById(userId).get().getPizzas();
    }

    public void addPizzaToUser(Long userId, Long pizzaId, int amount) throws ApiError {
        if (!pizzaRepository.existsById(pizzaId)) {
            throw new PizzaNotFoundException();
        }
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }
        pizzaRepository.findById(pizzaId).get().addUser(
                userRepository.findById(userId).get(),
                amount
        );
    }
}
