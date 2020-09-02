package com.tengzhi.business.base.page.dao;

import com.tengzhi.business.base.page.model.SysGrid;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut.ECkOut_PK;


public interface SysGridDao extends BasedaoRepository<SysGrid,String> {


	 
}
