package com.doo.academy.dy.pc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doo.academy.dy.model.pk.PublicContestPK;
import com.doo.academy.dy.model.ps.PublicContest;

public interface DyPc00Repository extends JpaRepository<PublicContest, PublicContestPK>{

}
