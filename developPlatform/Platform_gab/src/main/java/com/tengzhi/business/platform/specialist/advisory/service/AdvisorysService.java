package com.tengzhi.business.platform.specialist.advisory.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.specialist.advisory.model.Advisorys;

import java.util.List;
import java.util.Map;


public interface AdvisorysService {


    BasePage<Advisorys> findAll(BaseDto baseDto) throws Exception;


    Advisorys findById(String Id);


    Advisorys save(Advisorys advisory);


    void update(Advisorys advisory);


    void deleteById(String Id);


    boolean judgeUnique(Advisorys advisory);

	/**
	 * 获取首页资讯信息
	 * @param limit
	 * @return
	 */
	Map<String, Object> getAdvisory(int limit);


	Map<String, Object> getAdvisoryPage(String classify, String pageSize, String pageIndex);


	Map<String,Object> getOne(String article_id);


	List<Map<String, Object>> getRecommend(String classify);



	List<Map<String, Object>> getAdvisoryClassify(String classify, String limit, String corp);


	Result expertCaseDetail(String id);

	List<Map<String, Object>> getAnnexName(String annex);
}
