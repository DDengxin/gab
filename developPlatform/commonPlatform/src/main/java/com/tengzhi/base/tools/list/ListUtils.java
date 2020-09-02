package com.tengzhi.base.tools.list;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ListUtils {
	/**集合转换成指定类型的List
	 * @param <T>
	 * @param source
	 * @param clazz
	 * @return
	 */
	//public static <T> List<T> cast(Collection<?> source, Class<T> clazz) {
	//	return JSON.parseArray(JSON.toJSONString(source), clazz);
	//}

	public static Map<String, Object> castToMap(Object object) {
		try {
			Map<String, Object> map = new HashMap();
			for (Field field : object.getClass().getDeclaredFields()) {
				boolean flag = field.isAccessible();
				field.setAccessible(true);
				Object o = field.get(object);
				map.put(field.getName(), o);
				field.setAccessible(flag);
			}
			return map;
		} catch (Exception e) {
		}
		return null;
	}

	public static Map<Field, Object> notNullCastToMap(Object object) {
		try {
			Map<Field, Object> map = new HashMap();
			for (Field field : object.getClass().getDeclaredFields()) {
				boolean flag = field.isAccessible();
				field.setAccessible(true);
				Object o = field.get(object);
				if (o != null) {
					if (o instanceof String && !StringUtils.hasText((String) o)) {
						continue;
					}
					map.put(field, o);
				}
				field.setAccessible(flag);
			}
			return map;
		} catch (Exception e) {
		}
		return null;
	}
}
