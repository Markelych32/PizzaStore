package ru.solonchev.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String description;
    @Column(name = "img_link", nullable = false)
    private String imgLink;
    @ManyToMany(mappedBy = "pizzas")
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        user.getPizzas().add(this);
    }

    public void removeUser(User user) {
        user.getPizzas().remove(this);
        users.remove(user);
    }
}
