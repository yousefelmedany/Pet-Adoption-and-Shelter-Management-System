package com.example.pets.config;

import com.example.pets.exceptions.CustomAccessDeniedHandler;
import com.example.pets.security.AuthFilter;
import com.example.pets.security.JwtUnAuthResponse;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static String[] PUBLIC_END_POINTS = {"/auth/**","/sendMail","/news/allnews", "/sector/all","/image/getimgbyname/**",};

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUnAuthResponse jwtUnAuthResponse;

    @Autowired
    private CustomAccessDeniedHandler CustomAccessDeniedHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(CustomAccessDeniedHandler)
                .and()
                .httpBasic()
                .authenticationEntryPoint(jwtUnAuthResponse)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(PUBLIC_END_POINTS).permitAll() // Public endpoints
//                .antMatchers("/admin/**").hasAuthority("ADMIN") // Allow USER access to /galaxy/**
//                .antMatchers("/galaxy/user/**").hasAuthority("ADMIN") // Allow ADMIN access to /galaxy/**
//                .antMatchers("/galaxy/**").hasAnyAuthority("admin","EMPLOYEE","MANAGER") // Allow ADMIN access to /galaxy/**

                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public Filter authFilter() {
        return new AuthFilter();
    }

}
