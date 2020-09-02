package com.tengzhi.business.system.workflow.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.workflow.model.Backlog;

public interface BacklogsDao extends BasedaoRepository<Backlog, String> {
	
	/**根据业务id删除
	 * @param businessId 业务id
	 */
	@Modifying
	@Query(value = "delete  from  Backlog where businessId=:businessId ")
	void delete(@Param("businessId") String businessId);
}
