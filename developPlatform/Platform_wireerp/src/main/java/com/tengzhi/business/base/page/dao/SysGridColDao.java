package com.tengzhi.business.base.page.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.base.page.model.SysGrid;
import com.tengzhi.business.base.page.model.SysGridCol;

import java.util.List;


public interface SysGridColDao extends BasedaoRepository<SysGridCol, SysGridCol.SysGridCol_PK> {

	/**
	 * 根据gridId查询详表信
	 * @param gridId
	 * @return
	 */
	List<SysGridCol> findByGridIdOrderByGridOrdAsc(String gridId);
}
