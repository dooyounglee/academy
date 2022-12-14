package com.doo.academy.dy.pc.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.doo.academy.dy.model.pk.ContestPK;
import com.doo.academy.dy.model.ps.Contest;
import com.doo.academy.dy.model.us.User;
import com.doo.academy.dy.pc.service.DyPc001Service;
import com.doo.academy.dy.us.repository.DyUs001Repository;
import com.doo.academy.dy.us.service.DyUs001Service;

@ExtendWith(SpringExtension.class)
@SpringBootTest// (classes = TestSecurityConfig.class)
// @Rollback(value = true)
@TestPropertySource("classpath:application-test.properties")
//@WebMvcTest(DyPc001Controller.class)
@AutoConfigureMockMvc// (addFilters = false, webClientEnabled = false, webDriverEnabled = false)
// @AutoConfigureMockMvc
// @ActiveProfiles(value = "test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DyPc001ControllerTest {

	@Autowired private MockMvc mockMvc;
	
	@MockBean
	private DyPc001Service dyPc001Service;
	
	@MockBean
	private DyUs001Repository dyUs001Repository;
	
	@Test
	@DisplayName(value = "findAll")
	// @WithUserDetails(value = "admin@doo.com")
	@WithMockUser(username = "admin@doo.com", password = "admin", roles = {"ADMIN"})
	// @WithAnonymousUser
	@Order(1)
	public void findAllTest() throws Exception {
		
		//given
		Contest pc = new Contest(2022, 1, "title", "description");
		
		//when
		when(dyPc001Service.findAll()).thenReturn(List.of(pc));
		
		// andExpect
		
		MockHttpServletResponse response = mockMvc.perform(
				post("/v1/api/dypc/list")
				//.content(content)
				// .contentType(MediaType.APPLICATION_JSON))
				// .header("X-AUTH-TOKEN", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBkb28uY29tIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpYXQiOjE2Njg5NTA5NzIsImV4cCI6MTY2ODk1NDU3Mn0.ZOkOQEZE8W2dVTAqcZM_TIhM9DPeNEFl705cvtPZE9o")
				// .accept(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isOk())
		//.andExpect(content().string(gson.toJson(data))
		.andExpect(jsonPath("$[0].yr", is(2022)))
		.andExpect(jsonPath("$.length()", is(1)))
		.andReturn().getResponse();
		;
		
		// verify(dyPc001Service).findAll();
		
		ResultActions resultActions = mockMvc.perform(
				post("/v1/api/dypc/list")
				//.param("key", "value")
				//.content(content)
				// .contentType(MediaType.APPLICATION_JSON))
				// .header("X-AUTH-TOKEN", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBkb28uY29tIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpYXQiOjE2Njg5NTA5NzIsImV4cCI6MTY2ODk1NDU3Mn0.ZOkOQEZE8W2dVTAqcZM_TIhM9DPeNEFl705cvtPZE9o")
				// .accept(MediaType.APPLICATION_JSON)
				)
		//.andDo(print())
		//.andReturn().getRequest()
		;
		//resultActions
		//.andExpect(status().isOk());
		
		//assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
		//assertThat(response.getContentLength()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	@DisplayName(value = "getOK")
	@WithMockUser(username = "admin@doo.com", password = "admin", roles = {"ADMIN"})
	@Order(2)
	public void getOKTest() throws Exception {
		
		// given
		Contest pc = new Contest(2022, 1, "title", "description");
		
		// when
		when(dyPc001Service.findById(new ContestPK(2022, 1))).thenReturn(Optional.of(pc));
		
		// then
		mockMvc.perform(
				post("/v1/api/dypc/get")
				.content("{\"yr\": 2022, \"sn\": 1}")
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.yr",is(2022)))
		.andExpect(jsonPath("$.sn", is(1)))
		.andExpect(jsonPath("$.title", is("title")))
		.andExpect(jsonPath("$.description", is("description")))
		;
	}
	
	@Test
	@DisplayName(value = "getBadRequest")
	@WithMockUser(username = "admin@doo.com", password = "admin", roles = {"ADMIN"})
	@Order(3)
	public void getBadRequestTest() throws Exception {
		
		// given
		Contest pc = new Contest(2022, 1, "title", "description");
		
		// when
		when(dyPc001Service.findById(new ContestPK(2022, 2))).thenReturn(Optional.empty());
		
		// then
		mockMvc.perform(
				post("/v1/api/dypc/get")
				.content("{\"yr\": 2022, \"sn\": 2}")
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isBadRequest())
		;
	}
	
	@Test
	@DisplayName(value = "save")
	@WithMockUser(username = "admin@doo.com", password = "admin", roles = {"ADMIN"})
	@Order(4)
	public void saveTest() throws Exception {
		
		// given
		Contest pc = new Contest(2022, 1, "title", "description");
		
		// when
		when(dyPc001Service.save(pc)).thenReturn(pc);
		
		// then
		mockMvc.perform(
				post("/v1/api/dypc/save")
				.content("{\"yr\": 2022, \"sn\": 2, \"title\": \"title\", \"description\": \"description\"}")
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk())
		;
	}
	
	@Test
	@DisplayName(value = "delete")
	@WithMockUser(username = "admin@doo.com", password = "admin", roles = {"ADMIN"})
	@Order(5)
	public void deleteTest() throws Exception {
		
		// given
		
		// when
		
		// then
		mockMvc.perform(
				post("/v1/api/dypc/delete")
				.content("{\"yr\": 2022, \"sn\": 2}")
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk())
		;
	}
}
