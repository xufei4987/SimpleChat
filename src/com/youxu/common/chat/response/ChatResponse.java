package com.youxu.common.chat.response;

import com.youxu.common.serial.Serializer;
public class ChatResponse extends Serializer{
	
	private long sendPlayerId;
	
	private String sendPlayerName;
	
	private String tartgetPlayerName;
	
	private byte chatType;
	
	private String message;

	
	public String getTartgetPlayerName() {
		return tartgetPlayerName;
	}

	public void setTartgetPlayerName(String tartgetPlayerName) {
		this.tartgetPlayerName = tartgetPlayerName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getSendPlayerId() {
		return sendPlayerId;
	}

	public void setSendPlayerId(long sendPlayerId) {
		this.sendPlayerId = sendPlayerId;
	}

	public String getSendPlayerName() {
		return sendPlayerName;
	}

	public void setSendPlayerName(String sendPlayerName) {
		this.sendPlayerName = sendPlayerName;
	}

	public byte getChatType() {
		return chatType;
	}

	public void setChatType(byte chatType) {
		this.chatType = chatType;
	}

	@Override
	protected void read() {
		this.sendPlayerId = readLong();
		this.sendPlayerName = readString();
		this.tartgetPlayerName = readString();
		this.chatType = readByte();
		this.message = readString();
	}

	@Override
	protected void write() {
		writeLong(this.sendPlayerId);
		writeString(this.sendPlayerName);
		writeString(this.tartgetPlayerName);
		writeByte(this.chatType);
		writeString(this.message);
	}
}
                                                  