package com.doo.academy.dy.pb.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.doo.academy.dy.model.pb.Problem;
import com.doo.academy.dy.model.pb.Variables;
import com.doo.academy.dy.model.pk.VariablesPK;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DyPb002RepositoryTest {

	@Autowired
	private DyPb001Repository dyPb001Repository;
	
	@Autowired
	private DyPb002Repository dyPb002Repository;
	
	private long problemNo;
	private int sn;
	
	@BeforeAll
	public void setup() {
		Problem problem = new Problem();
		problem.setQuestion("question101");
		problem.setAnswer("answer101");
		problem.setSolution("solution101");
		problem.setVariables("variables101");
		problem.setDelYn("N");
		Problem result1 = dyPb001Repository.save(problem);
		problemNo = result1.getProblemNo();
		
		Variables variables = new Variables();
		variables.setProblemNo(problemNo);
		variables.setVariable("variables99");
		Variables result2 = dyPb002Repository.save(variables);
		sn = result2.getSn();
	}
	
	@Test
	public void findByIdTest() {
		Optional<Variables> oVariables = dyPb002Repository.findById(new VariablesPK(problemNo, sn));
		Assertions.assertTrue(oVariables.isPresent());
		Variables variables = oVariables.get();
		Assertions.assertEquals(problemNo, variables.getProblemNo());
		Assertions.assertEquals(sn, variables.getSn());
		Assertions.assertEquals("variables99", variables.getVariable());
	}
	
	@Test
	public void findByIdNotFoundTest() {
		Optional<Variables> oVariables = dyPb002Repository.findById(new VariablesPK(-1L, -1));
		Assertions.assertTrue(oVariables.isEmpty());
	}
}
