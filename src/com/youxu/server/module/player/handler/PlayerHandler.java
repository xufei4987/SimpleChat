package com.youxu.server.module.player.handler;

import com.youxu.common.ModuleId;
import com.youxu.common.annotation.SocketCommand;
import com.youxu.common.annotation.SocketModule;
import com.youxu.common.model.Result;
import com.youxu.common.player.PlayerCmd;
import com.youxu.common.player.response.PlayerResponse;
import com.youxu.common.session.Session;

@SocketModule(module = ModuleId.PLAYER)
public interface PlayerHandler {
	
	
	@SocketCommand(cmd = PlayerCmd.REGISTER_AND_LOGIN)
	public Result<PlayerResponse> registerAndLogin(Session session, byte[] data);
	

	@SocketCommand(cmd = PlayerCmd.LOGIN)
	public Result<PlayerResponse> login(Session session, byte[] data);

}
