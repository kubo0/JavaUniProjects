package org.example.repositories;

import org.example.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findAllByAge(int age);
    List<Staff> findAllByAgeBetween(int ageFrom, int ageTo);
    Staff findByEmail(String email);
}
