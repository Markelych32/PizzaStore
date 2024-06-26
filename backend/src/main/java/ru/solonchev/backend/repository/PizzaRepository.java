package ru.solonchev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.solonchev.backend.domain.Pizza;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza,Long> {
    boolean existsByName(String name);
}
