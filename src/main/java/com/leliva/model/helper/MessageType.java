package com.leliva.model.helper;

public enum MessageType {
	SUCCESS("message-success"), //
	FAILURE("message-failure"), //
	INFO("message-info");

	public final String cssClass;

	private MessageType(String cssClass) {
		this.cssClass = cssClass;
	}

	public String geCssClass() {
		return cssClass;
	}

}