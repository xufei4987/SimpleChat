package com.youxu.server.module.chat.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.youxu.common.chat.request.PrivateChatRequest;
import com.youxu.common.chat.request.PublicChatRequest;
import com.youxu.common.exception.ErrorCodeException;
import com.youxu.common.model.Result;
import com.youxu.common.model.ResultCode;
import com.youxu.server.module.chat.service.ChatService;



@Component
public class ChatHandlerImpl implements ChatHandler{
	
	@Autowired
	private ChatService chatService;

	@Override
	public Result<?> publicChat(long playerId, byte[] data) {
		try {
			PublicChatRequest request = new PublicChatRequest();
			request.readFromBytes(data);
			
			if(StringUtils.isEmpty(request.getContext())){
				return Result.ERROR(ResultCode.AGRUMENT_ERROR);
			}
			
			chatService.publicChat(playerId, request.getContext());
		} catch (ErrorCodeException e) {
			return Result.ERROR(e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
		}
		return Result.SUCCESS();
	}

	@Override
	public Result<?> privateChat(long playerId, byte[] data) {
		try {
			PrivateChatRequest request = new PrivateChatRequest();
			request.readFromBytes(data);
			
			if(StringUtils.isEmpty(request.getContext()) || request.getTargetPlayerId() <= 0){
				return Result.ERROR(ResultCode.AGRUMENT_ERROR);
			}
			
			chatService.privateChat(playerId, request.getTargetPlayerId(), request.getContext());
		} catch (ErrorCodeException e) {
			return Result.ERROR(e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
		}
		return Result.SUCCESS();
	}
}
