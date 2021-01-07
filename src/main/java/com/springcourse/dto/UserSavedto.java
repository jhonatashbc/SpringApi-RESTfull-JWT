package com.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSavedto {

	@NotBlank(message = "Name required")
	private String name;
	
	@Email(message = "Email required")
	private String email;
	
	@Size(min = 7, max = 99, message = "Password must between 7 and 99")
	private String password;
	
	@NotNull
	private Role role;
	
	private List<Request> requests = new ArrayList<>();
	private List<RequestStage> stages = new ArrayList<>();
	
	public User transformToUser() {
		User user = new User(null, this.name, this.email, this.password, this.role, this.requests, this.stages);
		return user;
	}
}
