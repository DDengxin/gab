package com.tengzhi.business.system.workflow.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.workflow.model.Line;

public interface LineDao extends BasedaoRepository<Line, String> {
	@Query(value = "select * from sys_workflow_line where  link_strat_id=(SELECT associated_id FROM \"sys_workflow_link\" where id=:id) and process_id=:processid", nativeQuery = true)
	List<Line> getLines(@Param(value = "processid") String processid,@Param(value = "id") String id);
	
	@Modifying
	@Query(value = "delete FROM  sys_workflow_line where process_id=:processid", nativeQuery = true)
	void delete(@Param(value = "processid") String processid);
}
