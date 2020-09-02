package com.tengzhi.business.system.initdata.dao;

import org.springframework.data.repository.CrudRepository;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.business.system.initdata.model.InitEJsCpcode;


public interface InitEJsCpcodeDao extends CrudRepository<InitEJsCpcode, Long> {
	
}
