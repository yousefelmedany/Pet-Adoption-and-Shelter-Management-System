package com.example.pets.repository;

import com.example.pets.entity.Application;
import com.example.pets.entity.ApplicationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationId> {
    public List<Application> findByAdopterAdopterId(Long adopterId);
}