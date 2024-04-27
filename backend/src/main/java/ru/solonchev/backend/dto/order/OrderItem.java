package ru.solonchev.backend.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.solonchev.backend.domain.Pizza;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    private String name;
    private double price;

    public static List<OrderItem> createListOfPizzas(List<Pizza> pizzas) {
        List<OrderItem> result = new ArrayList<>();
        pizzas.forEach(pizza -> result.add(OrderItem.builder()
                .name(pizza.getName())
                .price(pizza.getPrice())
                .build()));
        return result;
    }
}
