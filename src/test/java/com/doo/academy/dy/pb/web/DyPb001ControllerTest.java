package com.doo.academy.dy.pb.web;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
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
	@DisplayName("listTest")
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
	
	@Test
	@DisplayName("getNotExistTest")
	@WithMockUser(username = "admin@doo.com", password = "admin", roles = {"ADMIN"})
	void getNotExistTest() throws Exception {

		Problem problem1 = new Problem(1L, "question1", "solution1", "answer1", "variables1");
		
		BDDMockito.given(dyPb001Service.findById(2L)).willReturn(Optional.empty());
		
		this.mvc.perform(MockMvcRequestBuilders.post("/v1/api/dypb001/get")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"problemNo\": 2}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		;
	}
	
	@Test
	@DisplayName("getTest")
	@WithMockUser(username = "admin@doo.com", password = "admin", roles = {"ADMIN"})
	void getTest() throws Exception {

		Problem problem1 = new Problem(1L, "question1", "solution1", "answer1", "variables1");
		
		BDDMockito.given(dyPb001Service.findById(1L)).willReturn(Optional.of(problem1));
		
		this.mvc.perform(MockMvcRequestBuilders.post("/v1/api/dypb001/get")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"problemNo\": 1}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.problemNo", Matchers.is(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.question", Matchers.is("question1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.solution", Matchers.is("solution1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.answer", Matchers.is("answer1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.variables", Matchers.is("variables1")))
		;
	}
	
	@Test
	@DisplayName("saveTest")
	@WithMockUser(username = "admin@doo.com", password = "admin", roles = {"ADMIN"})
	void saveTest() throws Exception {

		Problem problem1 = new Problem(1L, "question1", "solution1", "answer1", "variables1");
		
		BDDMockito.given(dyPb001Service.save(problem1)).willReturn(problem1);
		
		this.mvc.perform(MockMvcRequestBuilders.post("/v1/api/dypb001/save")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"problemNo\": 1, \"question\": \"question1\", \"solution\": \"solution1\", \"answer\": \"answer1\", \"variables\": \"variables1\"}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.problemNo", Matchers.is(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.question", Matchers.is("question1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.solution", Matchers.is("solution1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.answer", Matchers.is("answer1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.variables", Matchers.is("variables1")))
		;
	}
}
