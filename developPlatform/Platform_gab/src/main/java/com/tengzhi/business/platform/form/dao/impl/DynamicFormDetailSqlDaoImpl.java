package com.tengzhi.business.platform.form.dao.impl;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.business.platform.form.dao.DynamicFormDetailSqlDao;
import com.tengzhi.business.platform.form.model.DynamicForm;
import com.tengzhi.business.platform.form.model.DynamicFormDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DynamicFormDetailSqlDaoImpl extends BasicsDaoImpl<DynamicFormDetail, String>
		implements DynamicFormDetailSqlDao {

	@Override
	public void saveDynamicFormDetail(String associatedKey, String associatedFormKey, String tableName,
			Map<String, Object> map) {
		if (!map.isEmpty()) {
			StringBuffer str = new StringBuffer(), value = new StringBuffer();
			str.append("INSERT INTO \"g_dynamic_form_detail\" (");
			// 拿到所有配置
			@SuppressWarnings("unchecked")
			List<DynamicForm> list = super.query(
					"select * from g_dynamic_form " + SqlJoint.where(e -> {
						e.eq("classify", associatedKey);
					}, true), DynamicForm.class);
			list.forEach(e -> {
				str.append("\"" + e.getAssociatedFields() + "\",");
				if (map.containsKey(e.getParameter())) {
					if (map.get(e.getParameter()) != null) {
						value.append("'" + map.get(e.getParameter()) + "',");
					} else {
						value.append("null,");
					}
				} else {
					value.append("null,");
				}
			});
			str.append("\"id\",\"associated_key\",\"associated_form_key\",\"associated_table_name\") VALUES (");
			str.append(value);
			str.append("'" + UUIDUtil.uuid() + "','" + associatedKey + "','" + associatedFormKey + "','" + tableName
					+ "')");
			super.executeUpdateBysql(str.toString());
		}
	}

	@Override
	public void updataDynamicFormDetail(String associatedKey, String associatedFormKey, String tableName,
			Map<String, Object> map) {
		if (!map.isEmpty()) {
			StringBuffer str = new StringBuffer(), sql = new StringBuffer();
			str.append("UPDATE   \"g_dynamic_form_detail\" set ");
			// 拿到所有配置
			@SuppressWarnings("unchecked")
			List<DynamicForm> list = super.query(
					"select * from g_dynamic_form " + SqlJoint.where(e -> {
						e.eq("classify", associatedKey);
					}, true), DynamicForm.class);
			list.forEach(e -> {
				if (map.containsKey(e.getParameter())) {
					str.append(e.getAssociatedFields() + "= '" + map.get(e.getParameter()) + "',");
				}
			});
			if (str.length() > 0) {
				sql = new StringBuffer(str.substring(0, str.length() - 1));
				sql.append(" where  associated_form_key='" + associatedFormKey + "' and associated_table_name='"
						+ tableName + "'");
				super.executeUpdateBysql(sql.toString());
			}
		}
	}
}
