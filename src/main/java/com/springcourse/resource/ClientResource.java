package com.springcourse.resource;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Client;
import com.springcourse.domain.Request;
import com.springcourse.dto.ClientSavedto;
import com.springcourse.dto.ClientUpdatedto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.ClientService;
import com.springcourse.service.RequestService;

@RestController
@RequestMapping(value = "client")
public class ClientResource {

	@Autowired
	private ClientService clientService;

	@Autowired
	private RequestService requestService;

	@PostMapping
	public ResponseEntity<Client> save(@RequestBody @Valid ClientSavedto clientdto) {
		Client createdClient = clientService.save(clientdto.transformToClient());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
	}

	@PreAuthorize("@accessManager.isOwner(#id)")
	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable("id") Long id, @RequestBody @Valid ClientUpdatedto clientdto) {
		Client client = clientdto.transformToClient();
		client.setId(id);
		Client updatedClient = clientService.update(client);
		return ResponseEntity.ok(updatedClient);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getById(@PathVariable("id") Long id) {
		Client client = clientService.getById(id);
		return ResponseEntity.ok(client);
	}

	@GetMapping
	public ResponseEntity<PageModel<Client>> listAll(@RequestParam Map<String, String> params) {
		PageRequestModel pr = new PageRequestModel(params);
		PageModel<Client> pm = clientService.listAllOnLazyModel(pr);
		return ResponseEntity.ok(pm);
	}

	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllRequestsByClientId(@PathVariable("id") Long id,
			@RequestParam Map<String, String> params) {
		PageRequestModel pr = new PageRequestModel(params);
		PageModel<Request> pm = requestService.listAllByClientIdOnLazyModel(id, pr);

		return ResponseEntity.ok(pm);
	}

}
