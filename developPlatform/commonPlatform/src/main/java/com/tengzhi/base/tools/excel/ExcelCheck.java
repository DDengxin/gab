package com.tengzhi.base.tools.excel;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author lqy excel转换为bean时，一行数据填充到bean时可以对数据进行校验
 * @param <T>
 */
public interface ExcelCheck<T> {

	T Check(T t);
}
