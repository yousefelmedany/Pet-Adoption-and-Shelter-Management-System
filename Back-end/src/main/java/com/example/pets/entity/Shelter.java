package com.example.pets.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne(mappedBy = "shelter", cascade = CascadeType.ALL)
    @JsonIgnore
    private Staff shelterAdmin;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Staff> staffs;
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pet> pets;
}
