package com.doo.academy.dy.pc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doo.academy.dy.model.pk.ContestPK;
import com.doo.academy.dy.model.ps.Contest;
import com.doo.academy.dy.pc.repository.DyPc001Repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DyPc001Service {

	private final DyPc001Repository dyPc001Repository;
	
	public List<Contest> findAll() {
		return dyPc001Repository.findAll();
	}
	
	public Optional<Contest> findById(ContestPK contestPK) {
		return dyPc001Repository.findById(contestPK);
	}

	public Contest save(Contest contest) {
		return dyPc001Repository.save(contest);
		
	}

	public void delete(int yr, int sn) {
		dyPc001Repository.deleteById(new ContestPK(yr, sn));
	}
}
