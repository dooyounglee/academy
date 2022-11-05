package com.doo.academy.dy.model.base;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true) // 부모 클래스에 필드를 포함하는 역할
@EqualsAndHashCode(callSuper = true) // 부모 클래스에 필드를 포함하는 역할
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class MasterEntity extends BaseEntity {

	@Column(columnDefinition = "VARCHAR(1) DEFAULT 'N'")
	private String delYn;
}
