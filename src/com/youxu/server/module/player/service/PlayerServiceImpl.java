package com.youxu.server.module.player.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.youxu.common.exception.ErrorCodeException;
import com.youxu.common.model.ResultCode;
import com.youxu.common.player.response.PlayerResponse;
import com.youxu.common.session.Session;
import com.youxu.common.session.SessionManager;
import com.youxu.server.module.player.dao.PlayerDao;
import com.youxu.server.module.player.dao.entity.Player;

@Component
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerDao playerDao;

	@Override
	public PlayerResponse registerAndLogin(Session session, String playerName, String passward) {

		Player existplayer = playerDao.getPlayerByName(playerName);

		if (existplayer != null) {
			throw new ErrorCodeException(ResultCode.PLAYER_EXIST);
		}

		Player player = new Player();
		player.setPlayerName(playerName);
		player.setPassward(passward);
		player.setLevel(0);
		player.setExp(0);
		playerDao.createPlayer(player);
		System.out.println("新用户ID："+player.getPlayerId());
		return login(session, playerName, passward);
	}

	@Override
	public PlayerResponse login(Session session, String playerName, String passward) {

		if (session.getAttachment() != null) {
			throw new ErrorCodeException(ResultCode.HAS_LOGIN);
		}

		Player player = playerDao.getPlayerByName(playerName);
		if (player == null) {
			throw new ErrorCodeException(ResultCode.PLAYER_NO_EXIST);
		}

		if (!player.getPassward().equals(passward)) {
			throw new ErrorCodeException(ResultCode.PASSWARD_ERROR);
		}

		boolean onlinePlayer = SessionManager.isOnlinePlayer(player.getPlayerId());
		if (onlinePlayer) {
			Session oldSession = SessionManager.removeSession(player.getPlayerId());
			oldSession.removeAttachment();
			oldSession.close();
		}

		if (SessionManager.putSession(player.getPlayerId(), session)) {
			session.setAttachment(player);
		} else {
			throw new ErrorCodeException(ResultCode.LOGIN_FAIL);
		}

		PlayerResponse playerResponse = new PlayerResponse();
		playerResponse.setPlayerId(player.getPlayerId());
		playerResponse.setPlayerName(player.getPlayerName());
		playerResponse.setLevel(player.getLevel());
		playerResponse.setExp(player.getExp());
		return playerResponse;
	}
}
