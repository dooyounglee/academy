package com.doo.academy.dy.pc.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.doo.academy.cm.exception.CustomException;
import com.doo.academy.dy.model.pk.ContestPK;
import com.doo.academy.dy.model.ps.Contest;
import com.doo.academy.dy.pc.service.DyPc001Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class DyPc001Controller {
	
	private final DyPc001Service dyPc001Service;
	
	@RequestMapping(value = "/v1/api/dypc/list", method = RequestMethod.POST)
	public ResponseEntity<List<Contest>> list() {
		
		return new ResponseEntity<>(dyPc001Service.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/api/dypc/get", method = RequestMethod.POST)
	public ResponseEntity<Contest> get(@RequestBody Contest contest) throws CustomException {
		log.debug("com.doo.academy.dy.pc.web.DyPc001Controller.get.contest : {}", contest);
		
		Optional<Contest> oContest = dyPc001Service.findById(new ContestPK(contest.getYr(), contest.getSn()));
		if (oContest.isEmpty()) {
			return new ResponseEntity<Contest>(HttpStatus.BAD_REQUEST);
		}
		log.debug("oContest.get() : {}", oContest.get());
		return new ResponseEntity<>(oContest.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/api/dypc/save", method = RequestMethod.POST)
	public ResponseEntity<Contest> save(@RequestBody Contest contest) throws CustomException {
		log.debug("com.doo.academy.dy.pc.web.DyPc001Controller.save.contest : {}", contest);
		
		return new ResponseEntity<>(dyPc001Service.save(contest), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/api/dypc/delete", method = RequestMethod.POST)
	public ResponseEntity<Contest> delete(@RequestBody Contest contest) throws CustomException {
		log.debug("com.doo.academy.dy.pc.web.DyPc001Controller.delete.contest : {}", contest);
		
		dyPc001Service.delete(new ContestPK(contest.getYr(), contest.getSn()));
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
