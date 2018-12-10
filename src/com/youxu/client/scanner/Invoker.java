package com.youxu.client.scanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invoker {

	private Object target;
	
	private Method method;

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
	
	public Object invoke(Object... paramValues) {
		try {
			return method.invoke(target, paramValues);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Invoker valueOf(Object target, Method method) {
		Invoker invoker = new Invoker();
		invoker.setTarget(target);
		invoker.setMethod(method);
		return invoker;
	}
}
 