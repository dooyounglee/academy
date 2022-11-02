package com.doo.academy.dy.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.doo.academy.dy.pc.repository.DyPc00Repository;

@SpringBootTest
@Rollback(value = true)
@TestPropertySource("classpath:application-test.properties")
public class PublicContestTest {

	@Autowired
	private DyPc00Repository dyPc00Repository;
	
	@Test
	@DisplayName(value = "save")
	@Order(1)
	public void saveTest() {
		
		//given
		PublicContest pc = new PublicContest(2022, 1, "title", "description", "N");
		
		//when
		dyPc00Repository.save(pc);
		
		//Then
		assertThat(dyPc00Repository.findAll()).hasSize(1);
		assertThat(dyPc00Repository.findAll().get(0)).isEqualTo(pc);
	}
}
