package ru.solonchev.backend.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.solonchev.backend.data.TestData;
import ru.solonchev.backend.domain.Pizza;
import ru.solonchev.backend.exception.pizza.PizzaAlreadyExistException;
import ru.solonchev.backend.exception.pizza.PizzaNotFoundException;
import ru.solonchev.backend.repository.PizzaRepository;
import ru.solonchev.backend.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
        when(pizzaRepository.existsById(anyLong())).thenReturn(true);
        assertThrows(PizzaAlreadyExistException.class, () -> underTest.createPizza(pizza));
    }

    @Test
    @SneakyThrows
    void creatingNewPizzaShouldCreateNewPizza() {
        final Pizza pizza = TestData.getPizza1();
        when(pizzaRepository.existsById(anyLong())).thenReturn(false);
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(pizza);

        Pizza actualPizza = underTest.createPizza(pizza);
        assertEquals(pizza, actualPizza);
        verify(pizzaRepository, times(1)).save(any(Pizza.class));
    }

    @Test
    @SneakyThrows
    void changingAbsentPizzaShouldThrowException() {
        final Pizza pizza = TestData.getPizza1();
        when(pizzaRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(PizzaNotFoundException.class, () -> underTest.changePizza(pizza));
    }

    @Test
    @SneakyThrows
    void changingExistingPizzaShouldChangePizza() {
        final Pizza pizza = TestData.getPizza1();
        when(pizzaRepository.existsById(anyLong())).thenReturn(true);
        pizza.setName("Three Cheese");
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(pizza);
        Pizza actualPizza = underTest.changePizza(pizza);
        assertEquals("Three Cheese", actualPizza.getName());
    }

    @Test
    void getAllPizzasShouldReturnEmptyListWhenNoPizza() {
        final List<Pizza> pizzas = List.of();
        when(pizzaRepository.findAll()).thenReturn(pizzas);
        final List<Pizza> actualPizzas = underTest.getAllPizzas();
        assertEquals(pizzas, actualPizzas);
    }

    @Test
    void getAllPizzasShouldReturnPizzaList() {
        final List<Pizza> pizzas = List.of(TestData.getPizza1(), TestData.getPizza2());
        when(pizzaRepository.findAll()).thenReturn(pizzas);
        final List<Pizza> actualPizzas = underTest.getAllPizzas();
        assertEquals(pizzas, actualPizzas);
    }
}
