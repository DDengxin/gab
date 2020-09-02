package com.tengzhi.business.system.workflow.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.workflow.model.Link;

public interface LinkDao extends BasedaoRepository<Link, String> {
	@Modifying
	@Query(value = "delete FROM  sys_workflow_link where process_id=:processid", nativeQuery = true)
	void delete(@Param(value = "processid") String processid);
}
