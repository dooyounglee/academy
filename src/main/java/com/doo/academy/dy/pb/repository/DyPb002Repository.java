package com.doo.academy.dy.pb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doo.academy.dy.model.pb.Variables;
import com.doo.academy.dy.model.pk.VariablesPK;

public interface DyPb002Repository extends JpaRepository<Variables, VariablesPK> {

	List<Variables> findByProblemNo(Long problemNo);

	@Modifying
	@Query("delete from Variables u where u.problemNo = :problemNo")
	int deleteByProblemNo(@Param("problemNo") Long problemNo);
	
	@Query("select coalesce(max(u.sn), 0) from Variables u where u.problemNo = :problemNo")
	int getSnMax(@Param("problemNo") Long problemNo);

}
