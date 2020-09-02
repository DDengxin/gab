package com.tengzhi.base.security.core;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Ceshi {
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < 2000; i++) {
			map.put("/system/.*/user"+i, 10);
		}
		long time=System.currentTimeMillis();
	    Map<String, Object> a = parseMapForFilter(map, "/system/123/user1999");
		long end=System.currentTimeMillis();
		System.err.println(time-end);
		System.err.println(a.get("/system/.*/user1999"));
	}

	/**
	 * 从map中查询想要的map项，根据key
	 */
	public static Map<String, Object> parseMapForFilter(Map<String, Object> map, String filters) {
		if (map == null) {
			return null;
		} else {
			map = map.entrySet().stream().filter((e) -> checkKey(e.getKey(), filters)).collect(Collectors.toMap((e) -> (String) e.getKey(), (e) -> e.getValue()));
		}
		return map;
	}

	/**
	 * 通过indexof匹配想要查询的字符
	 */
	private static boolean checkKey(String key, String filters) {
        return filters.matches(key);
	}

}
