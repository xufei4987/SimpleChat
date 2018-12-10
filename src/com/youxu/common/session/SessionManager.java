package com.youxu.common.session;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.google.protobuf.GeneratedMessage;
import com.youxu.common.model.Response;
import com.youxu.common.model.ResultCode;
import com.youxu.common.serial.Serializer;

public class SessionManager {
	/**
	 * ���߻Ự
	 */
	private static final ConcurrentHashMap<Long, Session> onlineSessions = new ConcurrentHashMap<>();
	
	/**
	 * ����
	 * @param playerId
	 * @param channel
	 * @return
	 */
	public static boolean putSession(long playerId, Session session){
		if(!onlineSessions.containsKey(playerId)){
			boolean success = onlineSessions.putIfAbsent(playerId, session)== null? true : false;
			return success;
		}
		return false;
	}
	
	/**
	 * �Ƴ�
	 * @param playerId
	 */
	public static Session removeSession(long playerId){
		return onlineSessions.remove(playerId);
	}
	
	/**
	 * ������Ϣ[�Զ���Э��]
	 * @param <T>
	 * @param playerId
	 * @param message
	 */
	public static <T extends Serializer> void sendMessage(long playerId, short module, short cmd, T message){
		Session session = onlineSessions.get(playerId);
		if (session != null && session.isConnected()) {
			Response response = Response.valueOf(module, cmd, ResultCode.SUCCESS, message.getBytes());
			session.write(response);
		}
	}
	
	/**
	 * ������Ϣ[protoBufЭ��]
	 * @param <T>
	 * @param playerId
	 * @param message
	 */
	public static <T extends GeneratedMessage> void sendMessage(long playerId, short module, short cmd, T message){
		Session session = onlineSessions.get(playerId);
		if (session != null && session.isConnected()) {
			Response response = Response.valueOf(module, cmd, ResultCode.SUCCESS, message.toByteArray());
			session.write(response);
		}
	}
	
	/**
	 * �Ƿ�����
	 * @param playerId
	 * @return
	 */
	public static boolean isOnlinePlayer(long playerId){
		return onlineSessions.containsKey(playerId);
	}
	
	/**
	 * ��ȡ�����������
	 * @return
	 */
	public static Set<Long> getOnlinePlayers() {
		return Collections.unmodifiableSet(onlineSessions.keySet());
	}
}
