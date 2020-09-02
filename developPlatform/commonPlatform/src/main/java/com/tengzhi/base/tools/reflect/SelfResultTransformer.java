package com.tengzhi.base.tools.reflect;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.property.access.internal.PropertyAccessStrategyBasicImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyChainedImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyFieldImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyMapImpl;
import org.hibernate.property.access.spi.Setter;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

import cn.hutool.json.JSON;

public class SelfResultTransformer extends AliasedTupleSubsetResultTransformer {

	// IMPL NOTE : due to the delayed population of setters (setters cached
	// for performance), we really cannot properly define equality for
	// this transformer

	private final Class resultClass;
	private boolean isInitialized;
	private String[] aliases;
	private Setter[] setters;

	public SelfResultTransformer(Class resultClass) {
		this.resultClass = resultClass;
		isInitialized = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
		return false;
	}

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Object result;
		try {
			if (!isInitialized) {
				initialize(aliases);
			} else {
				check(aliases);
			}
			result = resultClass.newInstance();
			for (int i = 0; i < aliases.length; i++) {
				try {
					if (setters[i] != null && isExistFieldName(lineToHump(aliases[i]), result)) {
						setters[i].set(result, tuple[i], null);
					}
				} catch (NoSuchFieldException e) {
					throw new HibernateException(aliases[i]+"有问题！");
				}
			}
		} catch (InstantiationException e) {
			throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
		} catch (IllegalAccessException e) {
			throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
		}

		return result;
	}

	private void initialize(String[] aliases) {
		PropertyAccessStrategyChainedImpl propertyAccessStrategy = new PropertyAccessStrategyChainedImpl(PropertyAccessStrategyBasicImpl.INSTANCE,
				PropertyAccessStrategyFieldImpl.INSTANCE, PropertyAccessStrategyMapImpl.INSTANCE);
		this.aliases = new String[aliases.length];
		setters = new Setter[aliases.length];
		for (int i = 0; i < aliases.length; i++) {
			String alias = aliases[i];
			if (alias != null) {
				this.aliases[i] = alias;
				setters[i] = propertyAccessStrategy.buildPropertyAccess(resultClass, lineToHump(alias)).getSetter();
			}
		}
		isInitialized = true;
	}

	private void check(String[] aliases) {
		if (!Arrays.equals(aliases, this.aliases)) {
			throw new IllegalStateException("aliases are different from what is cached; aliases=" + Arrays.asList(aliases) + " cached=" + Arrays.asList(this.aliases));
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SelfResultTransformer that = (SelfResultTransformer) o;
		if (!resultClass.equals(that.resultClass)) {
			return false;
		}
        return Arrays.equals(aliases, that.aliases);
    }

	@Override
	public int hashCode() {
		int result = resultClass.hashCode();
		result = 31 * result + (aliases != null ? Arrays.hashCode(aliases) : 0);
		return result;
	}

//下划线转驼峰
	private Pattern linePattern = Pattern.compile("_(\\w)");

	public String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	// 判断类是否存在某个属性（字段）
	public static Boolean isExistFieldName(String fieldName, Object obj) throws NoSuchFieldException {
		if (obj == null || StringUtils.isEmpty(fieldName)) {
			return null;
		}
		// 获取这个类的所有属性
		Field[] fields = obj.getClass().getDeclaredFields();
		boolean flag = false;
		// 循环遍历所有的fields
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equals(fieldName)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

}
