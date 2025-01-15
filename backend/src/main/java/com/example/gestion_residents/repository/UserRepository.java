package com.example.gestion_residents.repository;

import com.example.gestion_residents.model.Role;
import com.example.gestion_residents.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Ajoutez des méthodes spécifiques si nécessaire
    Optional<User> findFirstByEmail(String email);
    Optional<List<User>> findAllByRole(Role role);
    Optional<User> findByRole(Role role);
}
