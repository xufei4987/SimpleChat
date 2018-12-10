package com.youxu.common.serial;

import java.nio.ByteOrder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;

public class BufferFactory {
	public static ByteOrder BYTE_ORDER = ByteOrder.BIG_ENDIAN;
	
	private static ByteBufAllocator bufAllocator = PooledByteBufAllocator.DEFAULT;

	/**
	 * 获取一个buffer
	 * 
	 * @return
	 */
	public static ByteBuf getBuffer() {
		ByteBuf buffer = bufAllocator.heapBuffer();
		return buffer;
	}

	/**
	 * 将数据写入buffer
	 * @param bytes
	 * @return
	 */
	public static ByteBuf getBuffer(byte[] bytes) {
		ByteBuf buffer = bufAllocator.heapBuffer();
		buffer.writeBytes(bytes);
		return buffer;
	}
}
