package com.doo.academy.dy.pc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doo.academy.dy.model.PublicContest;
import com.doo.academy.dy.model.pk.PublicContestPK;

public interface DyPc00Repository extends JpaRepository<PublicContest, PublicContestPK>{

}
