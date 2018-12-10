package com.youxu.common.model;

public class Response {
	
	private short module;
	
	private short cmd;
	
	private int stateCode;
	
	private byte[] data;
	
	public static Response valueOf(short module, short cmd, int stateCode, byte[] data) {
		Response response = new Response();
		response.setModule(module);
		response.setCmd(cmd);
		response.setStateCode(stateCode);
		response.setData(data);
		return response;
	}
	
	public Response() {}
	
	public Response(Request message) {
		this.module = message.getModule();
		this.cmd = message.getCmd();
	}
	
	public Response(short module, short cmd, byte[] data){
		this.module = module;
		this.cmd = cmd;
		this.data = data;
	}

	public short getModule() {
		return module;
	}

	public void setModule(short module) {
		this.module = module;
	}

	public short getCmd() {
		return cmd;
	}

	public void setCmd(short cmd) {
		this.cmd = cmd;
	}
		
	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
