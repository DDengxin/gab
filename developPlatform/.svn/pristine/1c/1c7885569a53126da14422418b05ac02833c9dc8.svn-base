package com.tengzhi.business.js.product.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.js.product.model.Jscpcode;

import javax.transaction.Transactional;

@SuppressWarnings("SqlResolve")
public interface JscpcodeDao extends BasedaoRepository<Jscpcode, String> {
	@Query(value=" SELECT * from sys_param  where param_mod='技术' and param_type='计量单位' and param_id!='JLDW' ",nativeQuery = true)
	List<Map<Object, String>> parameType();

	/**
	* Description:只用于修改cpcode_pb字段null时被忽略问题
	* @author: huangbiao
	* @date: 2020/8/11 0011 18:31
	*/
	@Transactional
	@Modifying
	@Query(value="update e_js_cpcode set cpcode_pb = null where cpcode_id= ?1",nativeQuery=true)
	Integer updateCpcodePb(String cpcodeId);

}
