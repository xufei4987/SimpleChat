package com.youxu.common.session;
/**
 * �Ự����ӿ�
 *
 */
public interface Session {
	/**
	 * �Ự�󶨶���
	 * @return
	 */
	Object getAttachment();
	
	/**
	 * �󶨶���
	 * @return
	 */
	void setAttachment(Object attachment);
	
	/**
	 * �Ƴ��󶨶���
	 * @return
	 */
	void removeAttachment();
	
	/**
	 * ��Ự��д����Ϣ
	 * @param message
	 */
	void write(Object message);
	
	/**
	 * �жϻỰ�Ƿ���������
	 * @return
	 */
	boolean isConnected();
	
	/**
	 * �ر�
	 * @return
	 */
	void close();
}
