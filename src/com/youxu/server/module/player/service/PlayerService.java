package com.youxu.server.module.player.service;

import com.youxu.common.player.response.PlayerResponse;
import com.youxu.common.session.Session;

public interface PlayerService {
	
	

	public PlayerResponse registerAndLogin(Session session, String playerName, String passward);
	
	

	public PlayerResponse login(Session session, String playerName, String passward);

}
