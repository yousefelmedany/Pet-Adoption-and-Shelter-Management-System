package com.example.pets.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@IdClass(ApplicationId.class)
@Table(name = "application")
public class Application {
    @Id
    @ManyToOne
    @JoinColumn(name = "adopterId")
    private Adopter adopter;

    @Id
    @ManyToOne
    @JoinColumn(name = "petId")
    private Pet pet;
    @Column(length = 1000)
    private String description;
    @Column
    private String status;
}
