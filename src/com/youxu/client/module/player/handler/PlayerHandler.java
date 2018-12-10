package com.youxu.client.module.player.handler;
import com.youxu.common.ModuleId;
import com.youxu.common.annotation.SocketCommand;
import com.youxu.common.annotation.SocketModule;
import com.youxu.common.player.PlayerCmd;
@SocketModule(module = ModuleId.PLAYER)
public interface PlayerHandler {
	
	
	/**
	 * @param resultCode
	 * @param data {@link null}
	 */
	@SocketCommand(cmd = PlayerCmd.REGISTER_AND_LOGIN)
	public void registerAndLogin(int resultCode, byte[] data);
	

	/**
	 * @param resultCode
	 * @param data {@link null}
	 */
	@SocketCommand(cmd = PlayerCmd.LOGIN)
	public void login(int resultCode, byte[] data);

}
