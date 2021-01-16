package com.springcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;
import com.springcourse.service.util.HashUtil;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	@Order(1)
	public void savetest() {
		String hash = HashUtil.getSecureHash("senha123");
		
		User user = new  User(null, "Jhonatas", "jhonatashbc@hotmail.com", hash, Role.ADMIN, null, null);
		User createdUser = userRepository.save(user);
		
		assertThat(createdUser.getId()).isEqualTo(1L);
	}
	
	@Test
	public void updateTest() {
		String hash = HashUtil.getSecureHash("senha123");
		User user = new  User(1L, "Jhonatas henrique", "jhonatashbc@hotmail.com", hash, Role.ADMIN, null, null);
		User updatedUser = userRepository.save(user);
		
		assertThat(updatedUser.getName()).isEqualTo("Jhonatas henrique");
	}
	
	@Test
	public void getByIdTest() {
		Optional<User> result = userRepository.findById(1L);
		User user = result.get();
		
		assertThat(user.getPassword()).isEqualTo("senha123");
	}
	
	@Test
	public void listTest() {
		List<User> users = userRepository.findAll();
		
		assertThat(users.size()).isEqualTo(1);
	}
	
	@Test
	public void loginTest() {
		Optional<User> result = userRepository.login("jhonatashbc@hotmail.com", "senha123");
		User loggedUser = result.get();
		
		assertThat(loggedUser.getId()).isEqualTo(1L);
	}
	
	public void updateRoleTest() {
		int affectedRows  = userRepository.updateRole(2L, Role.ADMIN);
		
		assertThat(affectedRows).isEqualTo(1);
	}

}
