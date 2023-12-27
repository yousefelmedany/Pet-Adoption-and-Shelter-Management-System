package com.example.pets.entity;

import javax.persistence.*;

import lombok.*;

@Entity()
@Table(name = "roles_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String name;

    public Role(String name) {
        this.name = name;
    }
}