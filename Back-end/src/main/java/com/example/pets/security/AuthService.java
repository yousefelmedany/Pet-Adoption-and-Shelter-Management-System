package com.example.pets.security;

import com.example.pets.dto.JWTResponseDto;
import com.example.pets.entity.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtTokenUtils jwtTokenUtils;
    private AppUserDetails appUserDetails;

    public ResponseCookie login(String login, String password) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password));
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        this.appUserDetails = userDetails;
        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenInfo tokenInfo = createLoginToken(login, userDetails.getEmail());

        JWTResponseDto jwtResponseDto = JWTResponseDto.builder()
                .accessToken(tokenInfo.getAccessToken())
                .refreshToken(tokenInfo.getRefreshToken())
                .build();
        return jwtTokenUtils.generateJwtCookie(jwtResponseDto.getAccessToken());
    }

    public AppUserDetails getUser() {
        return appUserDetails;
    }

    public TokenInfo createLoginToken(String userName, String userId) {
        String accessTokenId = UUID.randomUUID().toString();
        String accessToken = JwtTokenUtils.generateToken(userName, userId, false);
        String refreshTokenId = UUID.randomUUID().toString();
        String refreshToken = JwtTokenUtils.generateToken(userName, userId, true);
        return new TokenInfo(accessToken, refreshToken);
    }


    public ResponseCookie logoutUser() {
        return jwtTokenUtils.getCleanJwtCookie();
    }

}
