package com.doo.academy.dy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.doo.academy.dy.model.base.BaseEntity;
import com.doo.academy.dy.model.pk.PublicContestPK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true) // 부모 클래스에 필드를 포함하는 역할
@EqualsAndHashCode(callSuper = true) // 부모 클래스에 필드를 포함하는 역할
@Entity(name = "DYPC002M")
@Table(name = "DYPC001M")
@IdClass(PublicContestPK.class)
public class PublicContest extends BaseEntity implements Serializable {

	@Id private int yr; /*공모년도*/
	@Id private int sn;    /*공모회차*/
	
	private String title;
	private String description;
	
	@Column(columnDefinition = "VARCHAR(1) DEFAULT 'N'")
	private String delYn;
	
	
	private static final long serialVersionUID = 1L;
}
