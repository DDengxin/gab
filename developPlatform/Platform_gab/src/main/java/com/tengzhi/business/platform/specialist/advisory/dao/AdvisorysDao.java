package com.tengzhi.business.platform.specialist.advisory.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.specialist.advisory.model.Advisorys;

import java.util.List;
import java.util.Map;

public interface AdvisorysDao extends BasedaoRepository<Advisorys,String> {

	Advisorys findByArticleId(String articleId);
	default List<Map<String, Object>> queryFile(String annex){
		String sql = "select * from com_file where line_primary=?1";
         return this.QueryhumpMap(sql,annex);
	}

}
