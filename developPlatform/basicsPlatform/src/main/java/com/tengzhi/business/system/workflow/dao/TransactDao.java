package com.tengzhi.business.system.workflow.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.workflow.model.Backlog;
import com.tengzhi.business.system.workflow.model.Link;
import com.tengzhi.business.system.workflow.model.Transact;

public interface TransactDao extends BasedaoRepository<Transact, String> {
	@Query(value = "select link_id as linkId from sys_workflow_transact where process_id=:processid and business_id =:businessid  group by link_id,link_name order by max(end_time) desc ", nativeQuery = true)
	List<String> getLink(@Param(value = "processid") String processid, @Param(value = "businessid") String businessid);
}
