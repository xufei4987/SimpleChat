package com.youxu.client;

import java.net.InetSocketAddress;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.youxu.client.swing.Swingclient;
import com.youxu.common.codc.RequestEncoder;
import com.youxu.common.codc.ResponseDecoder;
import com.youxu.common.model.Request;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * netty�ͻ�������
 * 
 * 
 */
@Component
public class Client {
	
	/**
	 * ����
	 */
	@Autowired
	private Swingclient swingclient;

	/**
	 * ������
	 */
	Bootstrap bootstrap = new Bootstrap();

	/**
	 * �Ự
	 */
	private Channel channel;

	/**
	 * �̳߳�
	 */
	private EventLoopGroup workerGroup = new NioEventLoopGroup();

	/**
	 * ��ʼ��
	 */
	@PostConstruct
	public void init() {
		
		// ����ѭ���߳�������
		bootstrap.group(workerGroup);

		// ����channel����
		bootstrap.channel(NioSocketChannel.class);

		// ���ùܵ�
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ResponseDecoder());
				ch.pipeline().addLast(new RequestEncoder());
				ch.pipeline().addLast(new ClientHandler(swingclient));
			}
		});
	}

	/**
	 * ����
	 * 
	 * @param ip
	 * @param port
	 * @throws InterruptedException
	 */
	public void connect() throws InterruptedException {

		// ���ӷ����
		ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1", 10102));
		connect.sync();
		channel = connect.channel();
	}

	/**
	 * �ر�
	 */
	public void shutdown() {
		workerGroup.shutdownGracefully();
	}

	/**
	 * ��ȡ�Ự
	 * 
	 * @return
	 */
	public Channel getChannel() {
		return channel;
	}
	
	/**
	 * ������Ϣ
	 * @param request
	 * @throws InterruptedException 
	 */
	public void sendRequest(Request request) throws InterruptedException{
		if(channel == null || !channel.isActive()){
			connect();
		}
		channel.writeAndFlush(request);
	}
	
}

