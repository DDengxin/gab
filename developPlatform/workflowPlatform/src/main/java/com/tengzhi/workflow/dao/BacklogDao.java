package com.tengzhi.workflow.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.workflow.model.Backlog;

public interface BacklogDao extends BasedaoRepository<Backlog, String> {
	
	Backlog findByBusinessId(String id);

}
