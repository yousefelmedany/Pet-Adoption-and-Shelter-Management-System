package com.example.pets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class GlobalHandling {


    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<?> handleDisabledException() {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("This Account is Disabled");
    }


    @ExceptionHandler(LockedException.class)
    public ResponseEntity<?> handleLockedException() {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("This Account is Locked");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException() {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong" + ex.getMessage());
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<?> handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Insufficient Authentication" + ex.getMessage());
    }

    @ExceptionHandler(ProviderNotFoundException.class)
    public ResponseEntity<?> handleProviderNotFoundException(ProviderNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Provider Not Found" + ex.getMessage());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<?> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Internal Authentication Service Exception" + ex.getMessage());
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<?> handleAuthenticationServiceException(AuthenticationServiceException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Service Exception" + ex.getMessage());
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<?> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Credentials Not Found Exception" + ex.getMessage());
    }

    @ExceptionHandler(AccountExpiredException.class)
    public ResponseEntity<?> handleAccountExpiredException(AccountExpiredException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account Expired Exception" + ex.getMessage());
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<?> handleCredentialsExpiredException(CredentialsExpiredException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials Expired Exception" + ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Exception" + ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Illegal Argument Exception   " + ex.getMessage() + "   " + Arrays.toString(ex.getStackTrace()) + "   " + ex.getCause() + "  : " + ex.getLocalizedMessage());
    }
}

