package com.springcourse.security;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springcourse.constant.SecurityConstants;
import com.springcourse.dto.UserLoginResponsedto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {

	public UserLoginResponsedto createToken(String email, List<String> roles) {
		LocalDate date = LocalDate.now();
		date = date.plusDays(SecurityConstants.JTW_EX_DAYS);
		
		String jwt = Jwts.builder()
				.setSubject(email)
				.setExpiration(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()))
				.claim(SecurityConstants.JWT_ROLE_KEY, roles)
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.API_KEY.getBytes())
				.compact();
		
		UserLoginResponsedto response = new UserLoginResponsedto(jwt, Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()), SecurityConstants.JWT_PROVIDER);
		
		return response;
	}
	
	public Claims parseToken(String jwt) throws JwtException{
		Claims claims = Jwts.parser()
				.setSigningKey(SecurityConstants.API_KEY.getBytes())
				.parseClaimsJws(jwt)
				.getBody();
		
		return claims;
	}
}
