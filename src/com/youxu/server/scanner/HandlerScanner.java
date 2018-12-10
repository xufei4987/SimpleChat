package com.youxu.server.scanner;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.youxu.common.annotation.SocketCommand;
import com.youxu.common.annotation.SocketModule;

@Component
public class HandlerScanner implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Class<? extends Object> clazz = bean.getClass();
		Class<?>[] interfaces = clazz.getInterfaces();
		if(interfaces != null && interfaces.length > 0) {
			for (Class<?> interFace : interfaces) {
				SocketModule SocketModuleAnno = interFace.getAnnotation(SocketModule.class);
				if(SocketModuleAnno == null) {
					continue;
				}
				Method[] methods = interFace.getMethods();
				if(methods != null && methods.length > 0) {
					for (Method method : methods) {
						SocketCommand socketCommandAnno = method.getAnnotation(SocketCommand.class);
						if(socketCommandAnno == null) {
							continue;
						}
						short module = SocketModuleAnno.module();
						short cmd = socketCommandAnno.cmd();
						
						Invoker invoker = Invoker.valueOf(bean, method);
						
						if(InvokerHolder.getInvoker(module, cmd) == null) {
							InvokerHolder.addInvoker(module, cmd, invoker);
						}else {
							System.out.println("ÖØ¸´×¢²áÖ´ÐÐÆ÷----module£º"+module+" cmd:"+cmd);
						}
					}
				}
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	

}
