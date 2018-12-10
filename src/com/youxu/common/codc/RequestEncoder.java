package com.youxu.common.codc;

import com.youxu.common.model.Request;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <pre>
 * ���ݰ���ʽ
 * +����----����+����-----����+����----����+����----����+����-----����+
 * |  ��ͷ	|  ģ���      |  �����    |   ����     |   ����       |
 * +����----����+����-----����+����----����+����----����+����-----����+
 * </pre>
 *
 */
public class RequestEncoder extends MessageToByteEncoder<Request>{

	@Override
	protected void encode(ChannelHandlerContext ctx, Request message, ByteBuf buffer) throws Exception {

		//��ͷ
		buffer.writeInt(ConstantValue.HEADER_FLAG);
		//module
		buffer.writeShort(message.getModule());
		//cmd
		buffer.writeShort(message.getCmd());
		//����
		int lenth = message.getData()==null? 0 : message.getData().length;
		if(lenth <= 0){
			buffer.writeInt(lenth);
		}else{
			buffer.writeInt(lenth);
			buffer.writeBytes(message.getData());
		}
	}
}
