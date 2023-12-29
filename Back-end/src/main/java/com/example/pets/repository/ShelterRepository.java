package com.example.pets.repository;

import com.example.pets.dto.ShelterDto;
import com.example.pets.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    @Query("SELECT new com.example.pets.dto.ShelterDto(s.shelterId, s.shelterName) FROM Shelter s")
    List<ShelterDto> findAllShelters();

}
