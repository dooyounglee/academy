package com.doo.academy.dy.model.pk;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariablesPK implements Serializable {

	private Long problemNo;
	private int sn;
	
	
	private static final long serialVersionUID = 1L;
}
