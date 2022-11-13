package com.doo.academy.dy.model.pb;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.doo.academy.dy.model.base.BaseEntity;
import com.doo.academy.dy.model.pk.VariablesPK;

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
@Entity
@Table(name = "DYPB002D")
@IdClass(VariablesPK.class)
public class Variables extends BaseEntity implements Serializable {

	@Id private Long problemNo;
	@Id private int sn;
	
	private String variable;
	
	
	private static final long serialVersionUID = 1L;
}
