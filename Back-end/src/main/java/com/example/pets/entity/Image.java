package com.example.pets.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "images_table")
public class Image {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column(length = 16777215)
    private String img;

    public Image(String name, String img) {
        this.name = name;
        this.img = img;
    }
}
