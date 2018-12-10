package com.youxu.common.codc;

import com.youxu.common.model.Response;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <pre>
 * ���ݰ���ʽ
 * +����----����+����-----����+����----����+����----����+����----����+����----����+
 * |  ��ͷ	|  ģ���         |  �����      |  �����      |  ����          |   ����       |  
 * +����----����+����-----����+����----����+����----����+����----����+����----����+
 * </pre>
 *
 */
public class ResponseEncoder extends MessageToByteEncoder<Response>{

	@Override
	protected void encode(ChannelHandlerContext ctx, Response response, ByteBuf buffer) throws Exception {
		
		System.out.println("��������:" + "module:" +response.getModule() +" cmd:" + response.getCmd() + " resultCode:" + response.getStateCode());
		
		//��ͷ
		buffer.writeInt(ConstantValue.HEADER_FLAG);
		//module��cmd
		buffer.writeShort(response.getModule());
		buffer.writeShort(response.getCmd());
		//�����
		buffer.writeInt(response.getStateCode());
		//����
		int lenth = response.getData()==null? 0 : response.getData().length;
		if(lenth <= 0){
			buffer.writeInt(lenth);
		}else{
			buffer.writeInt(lenth);
			buffer.writeBytes(response.getData());
		}
	}
}
