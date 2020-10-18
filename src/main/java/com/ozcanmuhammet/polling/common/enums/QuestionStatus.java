package com.ozcanmuhammet.polling.common.enums;

public enum QuestionStatus {
	PENDING("0"), ACTIVE("1");

	private String value;

	QuestionStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
