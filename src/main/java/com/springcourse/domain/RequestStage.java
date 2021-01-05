package com.springcourse.domain;

import java.util.Date;

import com.springcourse.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unused")
public class RequestStage {
	
	private Long id;
	private String description;
	private Date realizationnDate;
	private RequestState state;
	private Request request;
	private User user;

}
