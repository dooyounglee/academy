package com.doo.academy.dy.pb.web;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.doo.academy.dy.model.pb.Problem;
import com.doo.academy.dy.pb.service.DyPb001Service;

// @WebMvcTest(controllers = DyPb001Controller.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class DyPb001ControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private DyPb001Service dyPb001Service;
	
	@Test
	@WithMockUser(username = "admin@doo.com", password = "admin", roles = {"ADMIN"})
	void listTest() throws Exception {

		Problem problem1 = new Problem(1L, "question1", "solution1", "answer1", "variables1");
		Problem problem2 = new Problem(2L, "question2", "solution2", "answer2", "variables2");
		
		List<Problem> list = Arrays.asList(problem1, problem2);
		BDDMockito.given(dyPb001Service.findAll()).willReturn(list);
		
		this.mvc.perform(MockMvcRequestBuilders.post("/v1/api/dypb001/list")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].problemNo", Matchers.is(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].question", Matchers.is("question1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].solution", Matchers.is("solution1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].answer", Matchers.is("answer1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].variables", Matchers.is("variables1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].problemNo", Matchers.is(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].question", Matchers.is("question2")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].solution", Matchers.is("solution2")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].answer", Matchers.is("answer2")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].variables", Matchers.is("variables2")))
		;
	}
}
