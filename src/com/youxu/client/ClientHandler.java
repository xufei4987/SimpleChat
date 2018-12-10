package com.youxu.client;

import com.youxu.client.scanner.Invoker;
import com.youxu.client.scanner.InvokerHolder;
import com.youxu.client.swing.Swingclient;
import com.youxu.common.model.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**
 * ��Ϣ���ܴ�����
 *
 */
public class ClientHandler extends SimpleChannelInboundHandler<Response> {
	
	/**
	 * ����
	 */
	private Swingclient swingclient;
	
	public ClientHandler(Swingclient swingclient) {
		this.swingclient = swingclient;
	}

	/**
	 * ������Ϣ
	 */
	@Override
	public void channelRead0(ChannelHandlerContext ctx, Response response) throws Exception {

		handlerResponse(response);
	}
	
	
	/**
	 * ��Ϣ����
	 * @param channelId
	 * @param request
	 */
	private void handlerResponse(Response response){
		
		//��ȡ����ִ����
		Invoker invoker = InvokerHolder.getInvoker(response.getModule(), response.getCmd());
		if(invoker != null){
			try {
				invoker.invoke(response.getStateCode(), response.getData());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			//�Ҳ���ִ����
			System.out.println(String.format("module:%s  cmd:%s �Ҳ�������ִ����", response.getModule(), response.getCmd()));
		}
	}

	/**
	 * �Ͽ�����
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		swingclient.getTips().setText("��������Ͽ�����~~~");
	}
}
