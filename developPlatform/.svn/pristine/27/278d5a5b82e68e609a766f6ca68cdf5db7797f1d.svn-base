package com.tengzhi.business.mesGz.gzda.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.js.product.model.Jscpcode;

public interface GzmjDao extends BasedaoRepository<Jscpcode, String>{

	@Query(value="select param_id id,param_name texts from sys_param where parent_id='JLDW' ",nativeQuery =true)
	List<Map<Object,String>> selectDept();
	
	@Query(value="select param_id id,param_name texts,parent_id pid from  sys_param  where parent_id='GZMJ' or param_id='GZMJ' ",nativeQuery =true)
	List<Map<Object,String>> selectFl();
	
	
	
}
