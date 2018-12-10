package com.youxu.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.youxu.common.config.MainConfig;


public class ServerMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		
		Server server = applicationContext.getBean(Server.class);
		
		server.start();
	}

}
