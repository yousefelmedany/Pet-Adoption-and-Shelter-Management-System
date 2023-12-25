package com.example.pets.security;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtUnAuthResponse implements AuthenticationEntryPoint , Serializable  {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 2848589597094595376L;

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex)
            throws IOException {
        ex.printStackTrace();
        final String expired = (String) req.getAttribute("expired");
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, Objects.requireNonNullElse(expired, "You would need to provide the Jwt token to access this resource"));
    }

}
