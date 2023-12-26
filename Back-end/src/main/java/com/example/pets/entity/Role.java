package com.example.pets.entity;

import javax.persistence.*;

import lombok.*;

@Entity()
@Table(name = "roles_table")
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public Role(String name) {
        this.name = Roles.valueOf(name);
    }

    @Column
    private Roles name;
}

enum Roles {
    STAFF("STAFF"),
    MANAGER("MANAGER"),
    ADOPTER("ADOPTER"),
    ;

    Roles(String staff) {

    }
}