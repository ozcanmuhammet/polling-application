package com.ozcanmuhammet.polling.common.enums;

import org.springframework.http.HttpStatus;

import com.ozcanmuhammet.polling.common.exception.PollingException;
import com.ozcanmuhammet.polling.common.exception.ServiceException;

public enum Exception implements ServiceException<PollingException> {
	USER_NOT_FOUND(1L, "Kullanıcı bulunamadı.", HttpStatus.NOT_FOUND),
	ROLE_NOT_FOUND(2L, "Rol bulunamadı.", HttpStatus.NOT_FOUND),
	POLL_NOT_FOUND(3L, "Anket bulunamadı", HttpStatus.NOT_FOUND),
	QUESTION_NOT_FOUND(4L, "Soru bulunamadı", HttpStatus.NOT_FOUND),
	OPTION_NOT_FOUND(5L, "Seçenek bulunamadı", HttpStatus.NOT_FOUND),
	ANSWER_NOT_FOUND(6L, "Cevap bulunamadı", HttpStatus.NOT_FOUND);

	private final Long errorCode;
	private final String errorDesc;
	private final HttpStatus status;

	private Exception(Long errorCode, String errorDesc, HttpStatus status) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
		this.status = status;
	}

	@Override
	public String errorCode() {
		return String.valueOf(errorCode);
	}

	@Override
	public String errorDesc() {
		return errorDesc;
	}

	@Override
	public HttpStatus status() {
		return status;
	}

	@Override
	public void raise() {
		throw new PollingException(this);
	}

	@Override
	public PollingException exception() {
		return new PollingException(this);
	}
}
