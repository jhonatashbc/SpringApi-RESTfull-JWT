package com.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.springcourse.domain.Client;
import com.springcourse.domain.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientSavedto {
	
	@NotBlank(message = "Name required")
	private String name;
	
	@NotBlank(message = "Identification Number required")
	private String identificationNumber;
	
	@NotBlank(message = "Address required")
	private String address;
	
	@NotBlank(message = "Address Number required")
	private String addressNumber;
	
	@NotBlank(message = "CEP required")
	private String cep;
	
	private String complemento;
	
	private String phoneNumber;
	
	private List<Request> requests = new ArrayList<Request>();
	
	public Client transformToClient() {
		return new Client(null, this.name, this.identificationNumber, this.address, this.addressNumber, this.cep, this.complemento, this.phoneNumber, this.requests);
	}

}
