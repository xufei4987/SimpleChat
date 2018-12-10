package com.youxu.server.module.chat.handler;

import com.youxu.common.model.Result;
import com.youxu.common.ModuleId;
import com.youxu.common.annotation.SocketCommand;
import com.youxu.common.annotation.SocketModule;
import com.youxu.common.chat.ChatCmd;
import com.youxu.common.chat.request.PrivateChatRequest;
import com.youxu.common.chat.request.PublicChatRequest;
@SocketModule(module = ModuleId.CHAT)
public interface ChatHandler {
	
	
	/**
	 * @param playerId
	 * @param data {@link PublicChatRequest}
	 * @return
	 */
	@SocketCommand(cmd = ChatCmd.PUBLIC_CHAT)
	public Result<?> publicChat(long playerId, byte[] data);
	
	
	
	/**
	 * @param playerId
	 * @param data {@link PrivateChatRequest}
	 * @return
	 */
	@SocketCommand(cmd = ChatCmd.PRIVATE_CHAT)
	public Result<?> privateChat(long playerId, byte[] data);
}
