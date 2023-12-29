package com.example.pets.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shelter")
@Data
@NoArgsConstructor
public class Shelter {

    @Id
    @GeneratedValue
    private Long shelterId;
    private String shelterName;
    private String shelterAddress;
    private String shelterPhone;

    @OneToOne(mappedBy = "shelter")
    @JsonIgnoreProperties("shelter")
    private Staff shelterAdmin;
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore

    private List<Staff> staffs;
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Pet> pets;
}
