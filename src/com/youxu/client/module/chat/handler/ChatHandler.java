package com.youxu.client.module.chat.handler;
import com.youxu.common.ModuleId;
import com.youxu.common.annotation.SocketCommand;
import com.youxu.common.annotation.SocketModule;
import com.youxu.common.chat.ChatCmd;
import com.youxu.common.chat.response.ChatResponse;

@SocketModule(module = ModuleId.CHAT)
public interface ChatHandler {
	
	/**
	 * @param resultCode
	 * @param data {@link null}
	 * @return
	 */
	@SocketCommand(cmd = ChatCmd.PUBLIC_CHAT)
	public void publicChat(int resultCode, byte[] data);
	
	/**
	 * @param resultCode
	 * @param data {@link null}
	 * @return
	 */
	@SocketCommand(cmd = ChatCmd.PRIVATE_CHAT)
	public void privateChat(int resultCode, byte[] data);
	
	/**
	 * @param resultCode
	 * @param data {@link ChatResponse}
	 * @return
	 */
	@SocketCommand(cmd = ChatCmd.PUSHCHAT)
	public void receieveMessage(int resultCode, byte[] data);
}
