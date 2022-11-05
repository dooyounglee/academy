package com.doo.academy.dy.pc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doo.academy.dy.model.pk.ContestPK;
import com.doo.academy.dy.model.ps.Contest;

@Repository
public interface DyPc001Repository extends JpaRepository<Contest, ContestPK>{

}
