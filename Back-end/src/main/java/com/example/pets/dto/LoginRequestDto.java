package com.example.pets.dto;



import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class LoginRequestDto {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
