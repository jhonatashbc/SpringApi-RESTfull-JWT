package com.springcourse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Client;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.ClientRepository;
import com.springcourse.specification.ClientSpecification;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public Client save(Client client) {
		Client createdClent = clientRepository.save(client);
		return createdClent;
	}
	
	public Client update(Client client) {
		Client updatedClient = clientRepository.save(client);
		return updatedClient;
	}
	
	public Client getById(Long id) {
		Optional<Client> result = clientRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("There are no client with the id = "+ id));
	}
	
	public List<Client> listAll(){
		List<Client> clients = clientRepository.findAll();
		return clients;
	}
	
	public PageModel<Client> listAllOnLazyModel(PageRequestModel pr){
		Pageable pageable = pr.toSpringPageRequest();
		Specification<Client> spec = ClientSpecification.search(pr.getSearch());
		
		Page<Client> page =  clientRepository.findAll(spec, pageable);
		
		PageModel<Client> pm = new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}
	
	public Client findClientByIdentificationNumber(String identificationNumber) {
		Optional<Client> result = clientRepository.findByIdentificationNumber(identificationNumber);
		return result.orElseThrow(() -> new NotFoundException("There are no client with the identificationNumber = "+ identificationNumber));
	}
}
