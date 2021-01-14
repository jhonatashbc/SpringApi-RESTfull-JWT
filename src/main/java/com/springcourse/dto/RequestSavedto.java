package com.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springcourse.domain.Client;
import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSavedto {

	@NotBlank(message = "Subject required")
	private String subject;

	private String description;

	@NotNull(message = "Owner required")
	private User owner;

	@NotNull(message = "Client required")
	private Client client;

	private List<RequestStage> stages = new ArrayList<>();

	public Request transformToRequest() {
		Request request = new Request(null, this.subject, this.description, null, null, this.owner, this.stages, this.client);
		return request;
	}
}
