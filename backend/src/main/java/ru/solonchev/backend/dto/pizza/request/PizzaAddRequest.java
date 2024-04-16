package ru.solonchev.backend.dto.pizza.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PizzaAddRequest {
    private String name;
    private double price;
    private String description;
    @JsonProperty("img_link")
    private String imgLink;
}
