package com.doo.academy.dy.pc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.doo.academy.dy.model.ps.Contest;
import com.doo.academy.dy.pc.repository.DyPc001Repository;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DyPc001ServiceTest {

	@MockBean
	private DyPc001Repository dyPc001Repository;
	
	@Autowired
	private DyPc001Service dyPc001Service;
	
	@Test
	@DisplayName(value = "save")
	@Order(1)
	public void saveTest() {
		
		// given
		Contest contest = new Contest(2022, 0, "title", "description");
		Contest newContest = new Contest(2022, 5, "title", "description");
		
		// when
		when(dyPc001Repository.getSnMax(contest.getYr())).thenReturn(4);
		when(dyPc001Repository.save(contest)).thenReturn(newContest);
		
		// then
		Contest result = dyPc001Service.save(contest);
		//verify(dyPc001Repository, times(1)).save(contest);
		assertThat(result.getSn()).isEqualTo(5);
	}
}
