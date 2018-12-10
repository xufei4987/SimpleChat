package com.youxu.common.codc;

import java.util.List;

import com.youxu.common.model.Response;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * <pre>
 * ���ݰ���ʽ
 * +����----����+����-----����+����----����+����----����+����----����+����----����+
 * |  ��ͷ	|  ģ���         |  �����      |  �����      |  ����          |   ����       |  
 * +����----����+����-----����+����----����+����----����+����----����+����----����+
 * </pre>
 *
 */
public class ResponseDecoder extends ByteToMessageDecoder{
	
	/**
	 * ���ݰ���������
	 */
	public static int BASE_LENTH = 4 + 2 + 2 + 4 + 4;

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
		
		while(true){
			if(buffer.readableBytes() >= BASE_LENTH){
				//��һ���ɶ����ݰ�����ʼλ��
				int beginIndex;
				
				while(true) {
					//��ͷ��ʼ�α��
					beginIndex = buffer.readerIndex();
					//��ǳ�ʼ���α�λ��
					buffer.markReaderIndex();
					if (buffer.readInt() == ConstantValue.HEADER_FLAG) {
						break;
					}
					//δ������ͷ��ʶ�Թ�һ���ֽ�
					buffer.resetReaderIndex();
					buffer.readByte();
					
					//������
					if(buffer.readableBytes() < BASE_LENTH){
						return ;
					}
				}
				//��ȡģ��������
				short module = buffer.readShort();
				short cmd = buffer.readShort();
				
				int stateCode = buffer.readInt();
				
				//��ȡ���ݳ��� 
				int lenth = buffer.readInt();
				if(lenth < 0 ){
					ctx.channel().close();
				}
				
				//���ݰ���û����
				if(buffer.readableBytes() < lenth){
					buffer.readerIndex(beginIndex);
					return ;
				}
				
				//�����ݲ���
				byte[] data = new byte[lenth];
				buffer.readBytes(data);
				
				Response response = new Response();
				response.setModule(module);
				response.setCmd(cmd);
				response.setStateCode(stateCode);
				response.setData(data);
				//��������Ϣ���󣬼����������handler����
				out.add(response);
			}else{
				break;
			}
		}
		//���ݲ��������ȴ����������ݰ�
		return ;
	}

}