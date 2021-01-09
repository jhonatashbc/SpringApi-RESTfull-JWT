package com.springcourse.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.RequestStage;
import com.springcourse.dto.RequestStageSavedto;
import com.springcourse.service.RequestStageService;

@RestController
@RequestMapping(value = "stages")
public class RequestStageResource {

	@Autowired
	private RequestStageService requestStageService;
	
	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageSavedto stagedto){
		RequestStage createdStage = requestStageService.save(stagedto.transformToRequestStage());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStage);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id){
		RequestStage stage = requestStageService.getById(id);
		return ResponseEntity.ok(stage);
	}
	
	
}
