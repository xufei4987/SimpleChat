package com.youxu.server.module.chat.service;
public interface ChatService {
	
	
	public void publicChat(long playerId, String content);
	
	
	public void privateChat(long playerId, long targetPlayerId, String content);
	
}
