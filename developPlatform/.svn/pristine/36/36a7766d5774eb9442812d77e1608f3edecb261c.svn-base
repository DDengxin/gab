package com.tengzhi.business.system.setting.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.setting.model.SysSet;

public interface SysSetDao extends BasedaoRepository<SysSet, String>{
	
	    @Modifying
	    @Query("delete from SysSet where sysCorp=:sysCorp and sysSecode=:sysSecode")
	    void deleteByCorp(@Param("sysCorp") String sysCorp,@Param("sysSecode") String sysSecode);
	
	    SysSet findBySysSecodeAndSysCorp(@Param("sysSecode") String sysSecode,@Param("sysCorp") String sysCorp);
}
