package com.tengzhi.base.tools.UUID;

import java.util.UUID;

public class UUIDUtil {
	/**
	 * 获取生成的uuid
	 * 
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
