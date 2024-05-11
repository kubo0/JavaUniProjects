package org.example.repositories;

import org.example.model.Characters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharactersRepository extends JpaRepository<Characters, Long> {
    List<Characters> findAllByAge(int age);
    List<Characters> findAllByAgeBetween(int ageFrom, int ageTo);
}
