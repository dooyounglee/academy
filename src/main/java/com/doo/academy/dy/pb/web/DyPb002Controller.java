package com.doo.academy.dy.pb.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.doo.academy.cm.model.dto.ResponseDto;
import com.doo.academy.dy.model.pb.Problem;
import com.doo.academy.dy.model.pb.Variables;
import com.doo.academy.dy.model.pk.VariablesPK;
import com.doo.academy.dy.pb.service.DyPb002Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class DyPb002Controller {
	
	private final DyPb002Service dyPb002Service;
	
	/**
	 * @param variables
	 * @return
	 */
	@RequestMapping(value = "/v1/api/dypb002/get", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> get(@RequestBody Problem problem) {
		log.debug("com.doo.academy.dy.pb.web.DyPb002Controller.get.problem : {}", problem);
		
//		Optional<Variables> oVariables = dyPb002Service.findById(new VariablesPK(variables.getProblemNo(), variables.getSn()));
//		if (oVariables.isEmpty()) {
//			return new ResponseEntity<Variables>(HttpStatus.BAD_REQUEST);
//		}
//		log.debug("oVariables.get() : {}", oVariables.get());
//		return new ResponseEntity<>(oVariables.get(), HttpStatus.OK);
		ResponseDto responseDto = ResponseDto.builder()
				.code(0)
				.success(true)
				.returnObject(dyPb002Service.findAll(problem.getProblemNo()))
				.build();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/api/dypb002/save", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> save(@RequestBody List<Variables> variables) {
		log.debug("com.doo.academy.dy.pb.web.DyPb002Controller.save.variables : {}", variables);
		
		ResponseDto responseDto = ResponseDto.builder()
				.code(0)
				.success(true)
				.returnObject(dyPb002Service.save(variables))
				.build();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/api/dypb002/delete", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> delete(@RequestBody Variables variables) {
		log.debug("com.doo.academy.dy.pb.web.DyPb002Controller.delete.variables : {}", variables);
		
		dyPb002Service.deleteById(new VariablesPK(variables.getProblemNo(), variables.getSn()));
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
