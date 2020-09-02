package com.tengzhi.base.jpa.extension;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Consumer;

/**
 * @author lqy sql 拼接
 */
public class SqlJoint {

	static SqlJoint sqlJoint;

	static {
		sqlJoint = new SqlJoint();
	}

	/**
	 * 生成where语句
	 * @param sqlUtils
	 * @return
	 */
	public static StringBuffer where(Consumer<SqlUtils> sqlUtils) {
		SqlUtils sql = new SqlUtils();
		sqlUtils.accept(sql);
		return sql.getSqlStr();
	}

	/**
	 * 生成where语句
	 * @param sqlUtils
	 * @return 如果语句片段不为空则在最前面增加“ and ”
	 */
	public static String whereSuffixAnd(Consumer<SqlUtils> sqlUtils) {
		SqlUtils sql = new SqlUtils();
		sqlUtils.accept(sql);
		return sql.getAndSuffixSqlStr();
	}


	public static String whereSuffixWhere(Consumer<SqlUtils> sqlUtils) {
		SqlUtils sql = new SqlUtils();
		sqlUtils.accept(sql);
		return sql.getWhereSuffixSqlStr();
	}


	public static String where(Consumer<SqlUtils> sqlUtils, boolean flag) {
		String where = where(sqlUtils).toString();
		if (flag) {
			if (StringUtils.isNotBlank(where)) {
				return " where " + where;
			}
			return "";
		} else {
			return where;
		}
	}

	public static SqlUtils start() {
		SqlUtils sql = new SqlUtils();
		return sql;
	}

	public static void main(String[] args) {
		StringBuffer where = SqlJoint.where(e -> {
			e.eq("ceshi", "value");
			e.eq("ceshi", "value");
			e.between("张三", "211", "2121");
			e.and(e1 -> {
				e1.isNull("測試1");
				e1.or(e2 -> {
					e2.isNull("測試1");
				});
			});
			e.in("ceshi1", "value1", "value2", "value3", "value4");
		});

		System.out.println(where);
		StringBuffer where1 = SqlJoint.start().eq("ceshi", "value").eq("ceshi", "value")
				.in("ceshi1", "value1", "value2", "value3", "value4").and(e1 -> {
					e1.isNull("測試1");
					e1.or(e2 -> {
						e2.isNull("測試1");
					});
				}).getSqlStr();
		System.out.println(where1);

	}

}
