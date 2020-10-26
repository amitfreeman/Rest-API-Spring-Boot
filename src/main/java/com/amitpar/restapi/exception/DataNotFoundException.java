package com.amitpar.restapi.exception;

public class DataNotFoundException extends 	RuntimeException{

	private static final long serialVersionUID = -6850136318650578699L;

	public DataNotFoundException(String message) {
		super(message);
	}

}
