package com.ozcanmuhammet.polling.common.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class PollingExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PollingException.class)
	protected ResponseEntity<Object> handlePollingException(PollingException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getErrorDesc(), new HttpHeaders(), ex.getStatus(), request);
	}

}
