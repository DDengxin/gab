package com.tengzhi.base.jpa.extension;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.util.StrUtil;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

public class Transformers extends AliasedTupleSubsetResultTransformer {

	
	private static final long serialVersionUID = 1L;
	public static final Transformers ALIAS_TO_CAMEL_ENTITY_MAP = new Transformers();

	private Transformers() {
	}

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map result = new HashMap(tuple.length);
		for (int i = 0; i < tuple.length; i++) {
			String alias = aliases[i];
			if (alias != null) {
				result.put(StrUtil.toCamelCase(alias), tuple[i]);
			}
		}
		return result;
	}

	@Override
	public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
		return false;
	}

	private Object readResolve() {
		return ALIAS_TO_CAMEL_ENTITY_MAP;
	}
}
