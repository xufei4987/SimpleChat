package com.youxu.common.model;

public interface ResultCode {
	/**
	 * �ɹ�
	 */
	int SUCCESS = 0;
	
	/**
	 * �Ҳ�������
	 */
	int NO_INVOKER = 1;
	
	/**
	 * �����쳣
	 */
	int AGRUMENT_ERROR = 2;
	
	/**
	 * δ֪�쳣
	 */
	int UNKOWN_EXCEPTION = 3;
	
	/**
	 * ����������벻��Ϊ��
	 */
	int PLAYERNAME_NULL = 4;
	
	/**
	 * �������ʹ��
	 */
	int PLAYER_EXIST = 5;
	
	/**
	 * ��Ҳ�����
	 */
	int PLAYER_NO_EXIST = 6;
	
	/**
	 * �������
	 */
	int PASSWARD_ERROR = 7;
	
	/**
	 * ���ѵ�¼
	 */
	int HAS_LOGIN = 8;
	
	/**
	 * ��¼ʧ��
	 */
	int LOGIN_FAIL = 9;
	
	/**
	 * ��Ҳ�����
	 */
	int PLAYER_NO_ONLINE = 10;
	
	/**
	 * ���ȵ�¼
	 */
	int LOGIN_PLEASE = 11;
	
	/**
	 * ����˽���Լ�
	 */
	int CAN_CHAT_YOUSELF = 12;
}
