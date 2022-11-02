package com.doo.academy.dy.model.pk;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicContestPK implements Serializable {

	private int yr;
	private int sn;

	
	private static final long serialVersionUID = 1L;
}
