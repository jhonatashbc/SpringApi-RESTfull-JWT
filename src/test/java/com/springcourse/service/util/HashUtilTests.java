package com.springcourse.service.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HashUtilTests {

	@Test
	public void getSecureHashTests() {
		String  hash = HashUtil.getSecureHash("123");
		System.out.println(hash);
		
		assertThat(hash.length()).isEqualTo(64);
	}
}
