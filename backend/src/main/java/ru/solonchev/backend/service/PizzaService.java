package ru.solonchev.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solonchev.backend.domain.Pizza;
import ru.solonchev.backend.exception.pizza.PizzaAlreadyExistException;
import ru.solonchev.backend.repository.PizzaRepository;
import ru.solonchev.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final UserRepository userRepository;

    public Pizza createPizza(Pizza pizza) throws PizzaAlreadyExistException {
        if (pizzaRepository.existsById(pizza.getId())) {
            throw new PizzaAlreadyExistException();
        }
        return pizzaRepository.save(pizza);
    }
}
