package br.com.brunosantos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MathException extends RuntimeException{

	public MathException(String ex) {
		super(ex);
	}

	private static final long serialVersionUID = 1L;

}
