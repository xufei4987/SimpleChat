package com.youxu.server.module.chat.service;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.youxu.common.ModuleId;
import com.youxu.common.chat.ChatCmd;
import com.youxu.common.chat.response.ChatResponse;
import com.youxu.common.chat.response.ChatType;
import com.youxu.common.exception.ErrorCodeException;
import com.youxu.common.model.ResultCode;
import com.youxu.common.session.SessionManager;
import com.youxu.server.module.player.dao.PlayerDao;
import com.youxu.server.module.player.dao.entity.Player;


@Component
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private PlayerDao playerDao;

	@Override
	public void publicChat(long playerId, String content) {
		
		Player player = playerDao.getPlayerById(playerId);
		
		
		Set<Long> onlinePlayers = SessionManager.getOnlinePlayers();
		
		ChatResponse chatResponse = new ChatResponse();
		chatResponse.setChatType(ChatType.PUBLIC_CHAT);
		chatResponse.setSendPlayerId(player.getPlayerId());
		chatResponse.setSendPlayerName(player.getPlayerName());
		chatResponse.setMessage(content);
		
		for(long targetPlayerId : onlinePlayers){
			SessionManager.sendMessage(targetPlayerId, ModuleId.CHAT, ChatCmd.PUSHCHAT, chatResponse);
		}
		
	}

	@Override
	public void privateChat(long playerId, long targetPlayerId, String content) {
		if(playerId == targetPlayerId){
			throw new ErrorCodeException(ResultCode.CAN_CHAT_YOUSELF);
		}
		
		Player player = playerDao.getPlayerById(playerId);
		
		Player targetPlayer = playerDao.getPlayerById(targetPlayerId);
		if(targetPlayer == null){
			throw new ErrorCodeException(ResultCode.PLAYER_NO_EXIST);
		}
		
		if(!SessionManager.isOnlinePlayer(targetPlayerId)){
			throw new ErrorCodeException(ResultCode.PLAYER_NO_ONLINE);
		}
		
		ChatResponse chatResponse = new ChatResponse();
		chatResponse.setChatType(ChatType.PRIVATE_CHAT);
		chatResponse.setSendPlayerId(player.getPlayerId());
		chatResponse.setSendPlayerName(player.getPlayerName());
		chatResponse.setTartgetPlayerName(targetPlayer.getPlayerName());
		chatResponse.setMessage(content);
		
		SessionManager.sendMessage(targetPlayerId, ModuleId.CHAT, ChatCmd.PUSHCHAT, chatResponse);
		SessionManager.sendMessage(playerId, ModuleId.CHAT, ChatCmd.PUSHCHAT, chatResponse);
	}
}
