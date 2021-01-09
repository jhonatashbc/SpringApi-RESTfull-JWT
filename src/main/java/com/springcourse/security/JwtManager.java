package com.springcourse.security;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springcourse.constant.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {

	public String createToken(String email, List<String> roles) {
		LocalDate date = LocalDate.now();
		date.plusDays(SecurityConstants.JTW_EX_DAYS);
		
		String jwt = Jwts.builder()
				.setSubject(email)
				.setExpiration(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()))
				.claim(SecurityConstants.JWT_ROLE_KEY, roles)
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.API_KEY.getBytes())
				.compact();
		
		return jwt;
	}
}
