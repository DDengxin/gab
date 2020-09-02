package com.tengzhi.business.xt.telbook.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.telbook.model.TelBook;

public interface TelBookDao extends BasedaoRepository<TelBook, String> {

	@Query(value="select dept_id id,dept_name texts from sys_dept",nativeQuery =true)
	List<Map<Object,String>> selectDept();
	
	@Query(value = "select f_getname('GETDEPTNAME',:workerdept,'',:workercorpid) ",nativeQuery = true)
	String getDeptName(@Param("workerdept") String workerdept,@Param("workercorpid") String workercorpid);
	
}
