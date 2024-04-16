package ru.solonchev.backend.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @ManyToMany
    @JoinTable(
            name = "user_pizza",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id")
    )
    private List<Pizza> pizzas = new ArrayList<>();
}
