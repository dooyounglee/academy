package com.doo.academy.dy.pb.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.doo.academy.dy.model.pb.Problem;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DyPb001RepositoryTest {

	@Autowired
	private DyPb001Repository dyPb001Repository;
	
	@BeforeAll
	public void setup() {
		Problem problem = new Problem();
		problem.setProblemNo(101L);
		problem.setQuestion("question101");
		problem.setAnswer("answer101");
		problem.setSolution("solution101");
		problem.setVariables("variables101");
		problem.setDelYn("N");
		dyPb001Repository.save(problem);
	}
	
	@Test
	public void findByIdTest() {
		Optional<Problem> oProblem = dyPb001Repository.findById(1L);
		Problem problem = oProblem.get();
		Assertions.assertEquals("question101", problem.getQuestion());
	}
	
	@Test
	public void findByIdNotFoundTest() {
		Optional<Problem> oProblem = dyPb001Repository.findById(101L);
		Assertions.assertTrue(oProblem.isEmpty());
	}
}
