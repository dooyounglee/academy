package com.doo.academy.dy.pb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.doo.academy.dy.model.pb.Problem;
import com.doo.academy.dy.pb.repository.DyPb001Repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DyPb001Service {

	private final DyPb001Repository dyPb001Repository;
	
	public List<Problem> findAll() {
		return dyPb001Repository.findAll(Sort.by("problemNo"));
	}
	
	public Optional<Problem> findById(Long problemNo) {
		return dyPb001Repository.findById(problemNo);
	}

	public Problem save(Problem problem) {
		
		return dyPb001Repository.save(problem);
		
	}

	public void delete(Long problemNo) {
		dyPb001Repository.deleteById(problemNo);
	}
}
