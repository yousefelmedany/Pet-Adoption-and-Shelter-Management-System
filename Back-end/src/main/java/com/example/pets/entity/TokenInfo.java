package com.example.pets.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfo {
    private String accessToken;
    private String refreshToken;
}
