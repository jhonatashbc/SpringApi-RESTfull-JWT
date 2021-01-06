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
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RequestRepositoryTests {

	@Autowired
	private RequestRepository requestRepository;
	
	@Test
	@Order(1)
	public void saveTest() {
		User owner = new User();
		owner.setId(1L);
		Request request = new Request(null, "Novo Laptop HP", "Pretendo obter um laptop HP", new Date(), RequestState.OPEN, owner, null);
		
		Request createdRequest = requestRepository.save(request);
		
		assertThat(createdRequest.getId()).isEqualTo(1L);
	}
	
	@Test
	public void updateTest() {
		User owner = new User();
		owner.setId(1L);
		Request request = new Request(1L, "Novo Laptop HP", "Pretendo obter um laptop HP, de RAM 16 GB", null, RequestState.OPEN, owner, null);
		
		Request updateRequest = requestRepository.save(request);
		
		assertThat(updateRequest.getDescription()).isEqualTo("Pretendo obter um laptop HP, de RAM 16 GB");
	}
	
	@Test
	public void getByIdTest() {
		Optional<Request> result = requestRepository.findById(1L);
		Request request = result.get();
		
		assertThat(request.getSuject()).isEqualTo("Novo Laptop HP");
		
	}
	
	@Test
	public void listTest() {
		List<Request> requests = requestRepository.findAll();
		
		assertThat(requests.size()).isEqualTo(1);
	}
	
	@Test
	public void listByOwnerIdTest() {
		List<Request> requests = requestRepository.findAllByOwnerId(1L);
		
		assertThat(requests.size()).isEqualTo(1);
	}
	
	@Test
	public void updateStatusTest() {
		int affctedRows = requestRepository.updateStatus(1L, RequestState.IN_PROGRESS);
		
		assertThat(affctedRows).isEqualTo(1);
	}
}
