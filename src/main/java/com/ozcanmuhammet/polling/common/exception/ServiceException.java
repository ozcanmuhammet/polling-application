package com.ozcanmuhammet.polling.common.exception;

import org.springframework.http.HttpStatus;

public interface ServiceException<T extends RuntimeException> {

	String errorCode();

	String errorDesc();

	HttpStatus status();

	T exception();

	default void raise() {
		throw exception();
	}

}
