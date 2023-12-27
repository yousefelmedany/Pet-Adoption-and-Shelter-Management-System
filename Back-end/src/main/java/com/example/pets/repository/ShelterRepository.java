package com.example.pets.repository;

import com.example.pets.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {

}
