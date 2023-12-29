package com.example.pets.repository;

import com.example.pets.entity.Application;
import com.example.pets.entity.ApplicationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationId> {
    public List<Application> findByAdopterAdopterId(Long adopterId);

    public List<Application> findByStatus(String status);

    @Query("SELECT a FROM Application a WHERE a.pet.id = :petId AND a.status = 'Pending'")
    public List<Application> getremainingpendingrequests(@Param("petId") long petId);


}
