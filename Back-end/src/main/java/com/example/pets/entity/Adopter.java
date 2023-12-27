package com.example.pets.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "adopter")
@Data
public class Adopter {
    @Id
    @GeneratedValue
    private Long adopterId;
    private String name;
    private String phone;
    private String address;
    @OneToMany(mappedBy = "adopter"
            , cascade = CascadeType.ALL
            , orphanRemoval = true
            , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Pet> pets;
}
