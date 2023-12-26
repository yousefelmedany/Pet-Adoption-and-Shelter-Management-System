package com.example.pets.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue
    private Long staffId;
    private String name;
    private String phone;
    private String address;
    private Date birthDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "managedShelterId", referencedColumnName = "shelterId")
    private Shelter shelter;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workAtShelterId", referencedColumnName = "shelterId")
    private Shelter staff;

    @OneToOne(mappedBy = "staff", cascade = CascadeType.ALL)
    private User user;
}
