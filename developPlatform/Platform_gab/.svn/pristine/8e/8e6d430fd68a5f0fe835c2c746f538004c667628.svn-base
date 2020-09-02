package com.tengzhi.business.platform.enroll.dao;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.enroll.model.G_Supply;

import java.util.List;
import java.util.Map;

public interface SupplySqlDao extends BasicsDao<G_Supply, String> {
	//根据专家的单号获取一条记录
	 List<Map<String,Object>>  GetSingleExpert(String expert_note);
	 //根据用户id获取发表的文章
	 List<Map<String,Object>>  GetAdvisory(String id);
	 
	 List<Map<String,Object>>  getRandomExpert(String expert_note);
	 
	 Result  getExpertTech();
	 
	
}
