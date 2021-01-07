package com.springcourse.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogindto {

	@Email(message = "Invalid email address")
	private String email;
	
	@NotBlank(message = "Password required")
	private String password;
	
}
