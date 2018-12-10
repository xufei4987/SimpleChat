package com.youxu.server.module.player.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.youxu.common.exception.ErrorCodeException;
import com.youxu.common.model.Result;
import com.youxu.common.model.ResultCode;
import com.youxu.common.player.request.LoginRequest;
import com.youxu.common.player.request.RegisterRequest;
import com.youxu.common.player.response.PlayerResponse;
import com.youxu.common.session.Session;
import com.youxu.server.module.player.service.PlayerService;


@Component
public class PlayerHandlerImpl implements PlayerHandler{
	
	@Autowired
	private PlayerService playerService;

	@Override
	public Result<PlayerResponse> registerAndLogin(Session session, byte[] data) {
		
		PlayerResponse result = null;
		try {
			RegisterRequest registerRequest = new RegisterRequest();
			registerRequest.readFromBytes(data);
			
			if(StringUtils.isEmpty(registerRequest.getPlayerName()) || StringUtils.isEmpty(registerRequest.getPassward())){
				return Result.ERROR(ResultCode.PLAYERNAME_NULL);
			}
			
			result = playerService.registerAndLogin(session, registerRequest.getPlayerName(), registerRequest.getPassward());
		} catch (ErrorCodeException e) {
			return Result.ERROR(e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
		}
		return Result.SUCCESS(result);
	}

	@Override
	public Result<PlayerResponse> login(Session session, byte[] data) {
		PlayerResponse result = null;
		try {
			LoginRequest loginRequest = new LoginRequest();
			loginRequest.readFromBytes(data);
			
			if(StringUtils.isEmpty(loginRequest.getPlayerName()) || StringUtils.isEmpty(loginRequest.getPassward())){
				return Result.ERROR(ResultCode.PLAYERNAME_NULL);
			}
			
			result = playerService.login(session, loginRequest.getPlayerName(), loginRequest.getPassward());
		} catch (ErrorCodeException e) {
			return Result.ERROR(e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
		}
		return Result.SUCCESS(result);
	}

}
