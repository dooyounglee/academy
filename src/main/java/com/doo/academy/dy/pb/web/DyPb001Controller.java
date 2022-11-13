package com.doo.academy.dy.pb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.doo.academy.dy.model.pb.Problem;
import com.doo.academy.dy.pb.service.DyPb001Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class DyPb001Controller {
	
	private final DyPb001Service dyPb001Service;
	
	@RequestMapping(value = "/v1/api/dypb001/list", method = RequestMethod.POST)
	public ResponseEntity<List<Problem>> list() {
		
		return new ResponseEntity<>(dyPb001Service.findAll(), HttpStatus.OK);
	}
	
	/**
	 * @param problem
	 * @return
	 */
	@RequestMapping(value = "/v1/api/dypb001/get", method = RequestMethod.POST)
	public ResponseEntity<Problem> get(@RequestBody Problem problem) {
		log.debug("com.doo.academy.dy.pb.web.DyPb001Controller.get.problem : {}", problem);
		
		Optional<Problem> oProblem = dyPb001Service.findById(problem.getProblemNo());
		if (oProblem.isEmpty()) {
			return new ResponseEntity<Problem>(HttpStatus.BAD_REQUEST);
		}
		log.debug("oContest.get() : {}", oProblem.get());
		return new ResponseEntity<>(oProblem.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/api/dypb001/save", method = RequestMethod.POST)
	public ResponseEntity<Problem> save(@RequestBody Problem problem) {
		log.debug("com.doo.academy.dy.pb.web.DyPb001Controller.save.problem : {}", problem);
		
		//dyPb001Service.save(contest);
		
		return new ResponseEntity<>(dyPb001Service.save(problem), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/api/dypb001/delete", method = RequestMethod.POST)
	public ResponseEntity<Problem> delete(@RequestBody Problem problem) {
		log.debug("com.doo.academy.dy.pb.web.DyPb001Controller.delete.problem : {}", problem);
		
		dyPb001Service.delete(problem.getProblemNo());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
