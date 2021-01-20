package com.leliva.model.helper;

public class Message {

	public String content;

	public MessageType type;

	public Message() {
	}

	public Message(String content, MessageType type) {
		this.content = content;
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

}
