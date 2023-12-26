package com.example.pets.security;

import com.example.pets.service.IUserService;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

@Log4j2
@Service
public class JwtTokenUtils {
	
	private static String TOKEN_SECRET;
	private static Long ACCESS_TOKEN_VALIDITY;
	private static Long REFRESH_TOKEN_VALIDITY;

	private final String jwtCookie;

	@Autowired
	private IUserService service;

	public JwtTokenUtils(@Value("${auth.secret}") String secret,@Value("${auth.cookies.jwtCookieName}") String jwtCookie, @Value("${auth.access.expiration}") Long accessValidity
			, @Value("${auth.refresh.expiration}") Long refreshValidity) {
		Assert.notNull(accessValidity, "Validity must not be null");
		Assert.hasText(secret, "Validity must not be null or empty");
		TOKEN_SECRET = secret;
		this.jwtCookie = jwtCookie;
		ACCESS_TOKEN_VALIDITY = accessValidity;
		REFRESH_TOKEN_VALIDITY = refreshValidity ;

	}



	public String getJwtFromCookies(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, jwtCookie);
		if (cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}

	public ResponseCookie generateJwtCookie(String userPrincipal) {
		return ResponseCookie.from(jwtCookie, userPrincipal)
				.httpOnly(true)
				.secure(true) // If you're using HTTPS
				.path("/") // Set the path to root to make it available across the entire application
				.sameSite("None") // Adjust this based on your security requirements
				.maxAge(24 * 60 * 60)
				.build();
	}

	public ResponseCookie getCleanJwtCookie() {
		return ResponseCookie.from(jwtCookie, null)
				.httpOnly(true)
				.secure(true) // If you're using HTTPS
				.path("/") // Set the path to root to make it available across the entire application
				.sameSite("None") // Adjust this based on your security requirements
				.build();

	}
	public static String generateToken(final String username, final String tokenId , boolean isRefresh) {
		return Jwts.builder()
				.setId(tokenId)
				.setSubject(username)
				.setIssuedAt(new Date())
				.setIssuer("app-Service")
				.setExpiration(calcTokenExpirationDate(isRefresh))
				.claim("created", Calendar.getInstance().getTime())
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET).compact();
	}

	
	private static Date calcTokenExpirationDate(boolean isRefresh) {
		return new Date(System.currentTimeMillis() + (isRefresh ? REFRESH_TOKEN_VALIDITY : ACCESS_TOKEN_VALIDITY) * 1000);
	}

	public String getUserNameFromToken(String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}

	public boolean isTokenValid(String token, AppUserDetails userDetails) {
		String username = getUserNameFromToken(token);
		boolean isUserNameEqualMail = username.equalsIgnoreCase(userDetails.getEmail()) ;
		return ((isUserNameEqualMail) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		Date expiration = getClaims(token).getExpiration();
		return expiration.before(new Date());
	}

	private Claims getClaims(String token) {
 
         return Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
	}
	
	
	public boolean validateToken(String token , HttpServletRequest httpServletRequest){
	
        try {
            Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
			httpServletRequest.setAttribute("Invalid JWT Signature",ex.getMessage());
          //  throw new SecurityException("Invalid JWT Signature");
        }catch (MalformedJwtException ex){
        	 httpServletRequest.setAttribute("Invalid JWT token",ex.getMessage());
          //  throw new SecurityException("Invalid JWT token");
        }catch (ExpiredJwtException ex){
        	log.info("Expired JWT token");
            httpServletRequest.setAttribute("Expired JWT token",ex.getMessage());
          //  throw new SecurityException("security.token_expired");
        }catch (UnsupportedJwtException ex){
            httpServletRequest.setAttribute("Unsupported JWT exception",ex.getMessage());
         //   throw new SecurityException("Unsupported JWT exception");
        }catch (IllegalArgumentException ex){
            httpServletRequest.setAttribute("Jwt claims string is empty",ex.getMessage());
         //   throw new SecurityException("Jwt claims string is empty");
        }
        return false;
}

}
