package com.doo.academy.dy.us.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doo.academy.cm.exception.CustomException;
import com.doo.academy.cm.model.dto.ResponseDto;
import com.doo.academy.dy.model.dto.SignDto;
import com.doo.academy.dy.model.us.User;
import com.doo.academy.dy.us.service.DyUs001Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class DyUs001Controller {

	private final DyUs001Service dyUs001Service;
	
	@PostMapping(value = "/v1/api/dyus001/signin")
    public ResponseEntity<ResponseDto> signIn(@RequestBody User user)
        throws RuntimeException {
        log.info("[signIn] 로그인을 시도하고 있습니다. user : {}", user);
        
        SignDto signDto = dyUs001Service.signIn(user.getEmail(), user.getPassword());

        if (signDto.getCode() == 0) {
            log.info("[signIn] 정상적으로 로그인되었습니다. email : {}, token : {}", user.getEmail(),
            		signDto.getToken());
        }
        
        ResponseDto responseDto = ResponseDto.builder()
        		.code(0)
        		.success(true)
        		.returnObject(signDto.getToken())
        		.build();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PostMapping(value = "/v1/api/dyus001/signup")
    public ResponseEntity<ResponseDto> signUp(@RequestBody User user) throws CustomException {
        log.info("[signUp] 회원가입을 수행합니다. user : {}", user);
        SignDto signDto = dyUs001Service.signUp(user);

        log.info("[signUp] 회원가입을 완료했습니다. email : {}", user.getEmail());
        
        ResponseDto responseDto = ResponseDto.builder()
        		.code(signDto.getCode())
        		.success(true)
        		.build();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
    
    @PostMapping(value = "/v1/api/dyus001/myinfo")
    public ResponseEntity<ResponseDto> myinfo(@RequestBody User user) throws CustomException {
    	log.debug("myinfo");
    	Collection<? extends GrantedAuthority> list = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    	log.debug("list : {}", list);
    	
    	Map<String, Object> map = new HashMap<>();
    	map.put("role", list);
    	
    	ResponseDto responseDto = ResponseDto.builder()
        		.code(0)
        		.success(true)
        		.returnObject(list)
        		.build();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/api/dyus001/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생: " + e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
