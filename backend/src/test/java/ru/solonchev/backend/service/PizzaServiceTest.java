package ru.solonchev.backend.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.solonchev.backend.data.TestData;
import ru.solonchev.backend.domain.Pizza;
import ru.solonchev.backend.exception.pizza.PizzaAlreadyExistException;
import ru.solonchev.backend.repository.PizzaRepository;
import ru.solonchev.backend.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PizzaRepository pizzaRepository;
    @InjectMocks
    PizzaService underTest;

    @Test
    @SneakyThrows
    void creatingExistingPizzaShouldThrowException() {
        final Pizza pizza = TestData.getPizza1();
        Mockito.when(pizzaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Assertions.assertThrows(PizzaAlreadyExistException.class, () -> underTest.createPizza(pizza));
    }
}
