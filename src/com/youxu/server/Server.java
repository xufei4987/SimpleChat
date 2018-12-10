package com.youxu.server;

import org.springframework.stereotype.Component;

import com.youxu.common.codc.RequestDecoder;
import com.youxu.common.codc.ResponseEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Component
public class Server {

	public void start() {

		ServerBootstrap b = new ServerBootstrap();

		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			b.group(bossGroup, workerGroup);

			b.channel(NioServerSocketChannel.class);

			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new RequestDecoder());
					ch.pipeline().addLast(new ResponseEncoder());
					ch.pipeline().addLast(new ServerHandler());
				}
			});

			b.option(ChannelOption.SO_BACKLOG, 2048);

			b.bind(10102).sync();

			System.out.println("start!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
