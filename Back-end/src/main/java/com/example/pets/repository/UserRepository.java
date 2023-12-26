package com.example.pets.repository;

import com.example.pets.entity.Role;
import com.example.pets.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(@Param("email") String email);
    Optional<User> findByEmail(@Param("email") String email);
}
