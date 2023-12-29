package com.example.pets.security;

import com.example.pets.service.UserService;
import com.example.pets.security.config.SecurityConfig;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Log4j2
public class AuthFilter extends OncePerRequestFilter implements Filter {


	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtils tokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		String requestURI = request.getRequestURI();

		log.info("Request URI: " + requestURI);
		String [] arr = requestURI.split("/");
		boolean isPublicEndpoint = Arrays.stream(SecurityConfig.PUBLIC_END_POINTS).anyMatch((s) -> s.toLowerCase().contains(arr[1].toLowerCase())
				&& s.toLowerCase().contains(arr[2].toLowerCase()));
		if (arr[1].contains("auth")){
			isPublicEndpoint = true;
		}
		if (true) {
			filterChain.doFilter(request, response);
			return;
		}
		String jwtTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		String jwtToken;
		if (jwtTokenHeader == null) {
			jwtTokenHeader =tokenUtil.getJwtFromCookies(request);
			jwtToken = jwtTokenHeader;
		}
		else {
			jwtToken = jwtTokenHeader.substring("Bearer ".length());
		}
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		if (jwtTokenHeader != null && securityContext.getAuthentication() == null) {
			if (tokenUtil.validateToken(jwtToken, request)) {
				String username = tokenUtil.getUserNameFromToken(jwtToken);
				Set<GrantedAuthority> authorities = tokenUtil.getAuthorities(jwtToken);
				if (username != null) {
					AppUserDetails userDetails =new AppUserDetails(" ", username, authorities,null, true);
					if (tokenUtil.isTokenValid(jwtToken)) {
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
					else {
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
					}
				}
			}
		}

		try {
			filterChain.doFilter(request, response);
		}
		catch (Throwable throwable) {
			throw new InsufficientAuthenticationException("Invalid Token");
		}
	}
}
