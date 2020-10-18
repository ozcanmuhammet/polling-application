package com.ozcanmuhammet.polling.common.enums;

public enum Roles {
	ADMIN_USER(1L), END_USER(2L);

	private Long value;

	Roles(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

}
