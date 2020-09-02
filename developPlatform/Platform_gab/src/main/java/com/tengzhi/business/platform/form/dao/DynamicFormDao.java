package com.tengzhi.business.platform.form.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.form.model.DynamicForm;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DynamicFormDao extends BasedaoRepository<DynamicForm, String> {

	@Modifying
	@Query(value = "update DynamicForm set isDelete = 'false' where Id = :Id")
	int delete(@Param(value = "Id") String Id);

	@Query(value = "SELECT a.attname AS name FROM pg_class AS c,pg_attribute AS a WHERE c.relname = 'g_dynamic_form_detail' AND a.attrelid = c.oid AND a.attnum>0 AND a.attname like '%field%'", nativeQuery = true)
	List<String> getField();

	@Query(value = "select count(*) from g_dynamic_form where classify = :classify", nativeQuery = true)
	int getFieldByClassify(@Param(value = "classify") String classify);

}
