package ru.solonchev.backend.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.solonchev.backend.domain.Pizza;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullOrder {
    private List<OrderItem> pizzas;
    private double resultPrice;

    public static FullOrder createFullOrder(List<Pizza> pizzas) {
        double resultPrice = 0;
        for (Pizza pizza : pizzas)
            resultPrice += pizza.getPrice();
        return FullOrder.builder()
                .pizzas(OrderItem.createListOfPizzas(pizzas))
                .resultPrice(resultPrice)
                .build();
    }
}
