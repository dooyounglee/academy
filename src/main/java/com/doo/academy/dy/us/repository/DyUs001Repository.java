package com.doo.academy.dy.us.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doo.academy.dy.model.us.User;

@Repository
public interface DyUs001Repository extends JpaRepository<User, String> {

	User findByEmail(String email);
}
