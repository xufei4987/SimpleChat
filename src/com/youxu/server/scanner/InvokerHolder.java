package com.youxu.server.scanner;

import java.util.HashMap;
import java.util.Map;

public class InvokerHolder {
	public static Map<Short, Map<Short,Invoker>> invokers = new HashMap<Short, Map<Short,Invoker>>();
	
	public static void addInvoker(short module, short cmd, Invoker invoker) {
		Map<Short, Invoker> map = invokers.get(module);
		if(map == null) {
			map = new HashMap<Short, Invoker>();
			invokers.put(module, map);
		}
		map.put(cmd, invoker);
	}
	
	public static Invoker getInvoker(short module, short cmd) {
		Map<Short, Invoker> map = invokers.get(module);
		if(map == null) {
			return null;
		}
		Invoker invoker = map.get(cmd);
		if(invoker == null) {
			return null;
		}
		return invoker;
	}
	
}
