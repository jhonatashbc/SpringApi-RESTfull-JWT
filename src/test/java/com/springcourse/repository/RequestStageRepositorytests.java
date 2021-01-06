package com.springcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RequestStageRepositorytests {

	@Autowired
	private RequestStageRepository requestStageRepository;
	
	@Test
	@Order(1)
	public void saveTest() {
		Request request = new Request();
		request.setId(1L);
		User owner = new User();
		owner.setId(1L);
		
		RequestStage requestStage = new RequestStage(null, "O pedido esta sendo processado", new Date(), RequestState.OPEN, request, owner);
		
		RequestStage createdRequestStage = requestStageRepository.save(requestStage);
		
		assertThat(createdRequestStage.getId()).isEqualTo(1L);
	}
	
	@Test
	public void getByIdTëst() {
		Optional<RequestStage> result = requestStageRepository.findById(1L);
		RequestStage requestStage = result.get();
		
		assertThat(requestStage.getDescription()).isEqualTo("O pedido esta sendo processado");
		
	}
	
	@Test
	public void listByRequestIdTest() {
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(1L);
		
		assertThat(stages.size()).isEqualTo(1);
		
	}
}
