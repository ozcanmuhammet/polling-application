package com.ozcanmuhammet.polling.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class PollingException extends RuntimeException {

	private static final long serialVersionUID = 1632701772202546427L;

	private final String errorCode;

	private final String errorDesc;

	private final HttpStatus status;

	public PollingException(ServiceException<?> exception) {
		super(exception.errorDesc());
		this.errorCode = exception.errorCode();
		this.errorDesc = exception.errorDesc();
		this.status = exception.status();
	}

}
