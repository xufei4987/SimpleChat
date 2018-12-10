package com.youxu.common.player.request;

import com.youxu.common.serial.Serializer;

public class RegisterRequest extends Serializer{
	
	private String playerName;
	
	private String passward;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}
	
	@Override
	protected void read() {
		this.playerName = readString();
		this.passward = readString();
	}

	@Override
	protected void write() {
		writeString(playerName);
		writeString(passward);
	}
}
