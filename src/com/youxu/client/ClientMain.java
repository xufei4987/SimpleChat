package com.youxu.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.youxu.client.config.ClientMainConfig;
import com.youxu.client.swing.Swingclient;

/**
 * Æô¶¯º¯Êý
 * @author admin
 *
 */
public class ClientMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ClientMainConfig.class);
		Swingclient swing = applicationContext.getBean(Swingclient.class);
		swing.setVisible(true);
	}

}