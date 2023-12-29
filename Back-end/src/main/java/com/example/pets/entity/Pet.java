package com.example.pets.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int petId;
    private String petName;
    private String species;
    private String breed;
    private String color;
    private String gender;
    private String age;
    private String healthStatus;
    private String training;
    private String vaccination;
    private String spayNeuter;
    @Lob
    @Column(name = "image", columnDefinition = "longblob")
    private byte[] image;
    private String behavior;
    @OneToMany(mappedBy = "pet"
            , cascade = CascadeType.ALL
            , orphanRemoval = true
            , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Document> documents;

    @ManyToOne
    @JoinColumn(name = "adopterId")
    private Adopter adopter;

    @ManyToOne
    @JoinColumn(name = "shelterId")
    private Shelter shelter;
    private String status;
    private LocalDate date;
}

