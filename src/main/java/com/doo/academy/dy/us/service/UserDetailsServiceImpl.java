package com.doo.academy.dy.us.service;

import com.doo.academy.dy.us.repository.DyUs001Repository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

// 예제 13.8
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final DyUs001Repository dyUs001Repository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        LOGGER.info("[loadUserByUsername] loadUserByUsername 수행. email : {}", email);
        return dyUs001Repository.findByEmail(email);
    }

}