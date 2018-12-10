package com.youxu.server.module.player.dao;

import org.springframework.stereotype.Repository;

import com.youxu.server.module.player.dao.entity.Player;

@Repository
public interface PlayerDao {
	

	public Player getPlayerById(long playerId);
	
	

	public Player getPlayerByName(final String playerName);
	
	

	public int createPlayer(Player player);

}
