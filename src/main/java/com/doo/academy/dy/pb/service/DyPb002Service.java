package com.doo.academy.dy.pb.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.doo.academy.dy.model.pb.Variables;
import com.doo.academy.dy.model.pk.VariablesPK;
import com.doo.academy.dy.pb.repository.DyPb002Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DyPb002Service {

	private final DyPb002Repository dyPb002Repository;
	
	public List<Variables> findAll(Long problemNo) {
		return dyPb002Repository.findByProblemNo(problemNo);
	}
	
	public Optional<Variables> findById(VariablesPK variablesPK) {
		return dyPb002Repository.findById(variablesPK);
	}

	public List<Variables> save(List<Variables> variables) {
		
		// variables없으면 넘어감
		if (variables.size() == 0) return variables;
		
		Long problemNo = variables.get(0).getProblemNo();
		log.debug("problemNo: {}", problemNo);
		dyPb002Repository.deleteByProblemNo(problemNo);
		
		variables.forEach(each -> {
			each.setSn(dyPb002Repository.getSnMax(problemNo) + 1);
			dyPb002Repository.save(each);
		});
		
		return variables;
		
	}

	public void deleteById(VariablesPK variablesPK) {
		dyPb002Repository.deleteById(variablesPK);
	}
}
