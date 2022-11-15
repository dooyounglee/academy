package com.doo.academy.dy.us.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.doo.academy.cm.security.JwtTokenProvider;
import com.doo.academy.cm.util.CommonResponse;
import com.doo.academy.dy.model.dto.SignDto;
import com.doo.academy.dy.model.us.User;
import com.doo.academy.dy.us.repository.DyUs001Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DyUs001Service {

	public final DyUs001Repository dyUs001Repository;
    public final JwtTokenProvider jwtTokenProvider;
    public final PasswordEncoder passwordEncoder;
    
    public SignDto signUp(User user) {
        log.info("[getSignUpResult] 회원 가입 정보 전달");
        
        // 패스워드
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // role
        if (user.getRole().equalsIgnoreCase("admin")) {
            user.setRoles(Collections.singletonList("ROLE_ADMIN"));
        } else {
            user.setRoles(Collections.singletonList("ROLE_USER"));
        }
        
        // 유저 고유상수 부여
        user.setVid((int)Math.round(Math.random()*100));

        User savedUser = dyUs001Repository.save(user);
        SignDto signDto = new SignDto();

        log.info("[getSignUpResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");
        if (!savedUser.getName().isEmpty()) {
            log.info("[getSignUpResult] 정상 처리 완료");
            setSuccessResult(signDto);
        } else {
            log.info("[getSignUpResult] 실패 처리 완료");
            setFailResult(signDto);
        }
        return signDto;
    }

    public SignDto signIn(String email, String password) throws RuntimeException {
        log.info("[getSignInResult] signDataHandler 로 회원 정보 요청");
        
        Optional<User> oUser = dyUs001Repository.findById(email);
        if (oUser.isEmpty()) throw new RuntimeException("not exist email");
        
        User user = oUser.get();
        log.info("[getSignInResult] 패스워드 비교 수행");
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }
        log.info("[getSignInResult] 패스워드 일치");

        log.info("[getSignInResult] SignDto 객체 생성");
        SignDto signDto = SignDto.builder()
            .token(jwtTokenProvider.createToken(String.valueOf(user.getEmail()),
                user.getRoles()))
            .build();

        log.info("[getSignInResult] SignDto 객체에 값 주입");
        setSuccessResult(signDto);

        return signDto;
    }

    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(SignDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    // 결과 모델에 api 요청 실패 데이터를 세팅해주는 메소드
    private void setFailResult(SignDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
