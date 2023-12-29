package com.example.pets.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShelterDto {
    Long shelterId;
    String shelterName;
}
