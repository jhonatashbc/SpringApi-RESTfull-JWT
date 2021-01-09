package com.springcourse.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponsedto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String token;
	private Date expireDate;
	private String tokenProvider;
}
