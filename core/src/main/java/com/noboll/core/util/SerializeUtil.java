package com.noboll.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.noboll.core.exception.BusinessException;

/**
 * 序列化工具类
 * @author 00705
 *
 */
public class SerializeUtil {
	/**
	 * 将对象转为字节数组
	 * @param obj
	 * @return
	 */
	public static byte[] serialize(Object value) {
		if (value == null) {
			throw new BusinessException("序列化对象不能为空");
		}
		byte[] result = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(value);
			os.close();
			bos.close();
			result = bos.toByteArray();
		} catch (IOException e) {
			throw new BusinessException("序列化失败");
		} finally {
			try {
				if(null!=os)
					os.close();
				if(null!=bos)
					bos.close();
			} catch (IOException e) {
				throw new BusinessException("序列化流关闭失败");
			}
		}
		return result;
	}


	public static Object deserialize(byte[] in) {
		Object result = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				result = is.readObject();
				is.close();
				bis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("反序列化失败");
		} finally {
			try {
				if(null!=is)
					is.close();
				if(null!=bis)
					bis.close();
			} catch (IOException e) {
				throw new BusinessException("反序列化流关闭失败");
			}
		}
		return result;
	}
}
