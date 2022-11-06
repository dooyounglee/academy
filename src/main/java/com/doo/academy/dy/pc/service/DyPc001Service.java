package com.doo.academy.dy.pc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
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
		return dyPc001Repository.findAll(Sort.by("yr").descending().and(Sort.by("sn").descending()));
	}
	
	public Optional<Contest> findById(ContestPK contestPK) {
		return dyPc001Repository.findById(contestPK);
	}

	public Contest save(Contest contest) {
		
		// sn없으면 max+1로 넣어서 저장
		if (contest.getSn() == 0) {
			contest.setSn(dyPc001Repository.getSnMax(contest.getYr()) + 1);
		}
		return dyPc001Repository.save(contest);
		
	}

	public void delete(ContestPK contestPK) {
		dyPc001Repository.deleteById(contestPK);
	}
}
