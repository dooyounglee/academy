package com.doo.academy.cm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ResponseDto {

	private boolean success;
	private int code;
	private String msg;
	private Object returnObject;
}
