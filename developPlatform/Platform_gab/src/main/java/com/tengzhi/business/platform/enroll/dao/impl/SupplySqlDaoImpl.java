package com.tengzhi.business.platform.enroll.dao.impl;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.enroll.dao.SupplyDao;
import com.tengzhi.business.platform.enroll.dao.SupplySqlDao;
import com.tengzhi.business.platform.enroll.model.G_Supply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SupplySqlDaoImpl  extends BasicsDaoImpl<G_Supply, String> implements SupplySqlDao   {
	@Autowired
	private SupplyDao supplyDao;

	@Override
	public  List<Map<String,Object>> GetSingleExpert(String expert_note) {
		String sql = "SELECT ('/system/upload/getImage?line_primary='||head_img) headImgSrc,  (select supply_name from g_supply where supply_note=g_expert.supply_name limit 1 ) as supplyname ,f_gab_getname ( 'GUSERTOERPUSER',expert_note, NULL,'' ) as expert, "
				+ "  * from  g_expert   where  expert_note = ?1 limit 1";
		return supplyDao.SelectListMap(sql, expert_note);
	}

	@Override
	public List<Map<String, Object>> GetAdvisory(String founder_id) {		
		String sql = "SELECT  title,article_id ,classify,whether_to_pay from  g_advisory  where  founder_id = ?1 ";
		return supplyDao.SelectListMap(sql,founder_id);
	}

	@Override
	public List<Map<String, Object>> getRandomExpert(String expert_note) {
		String sql = "SELECT   (SELECT user_name from g_userinfo  where user_id = g_expert.expert_note limit 1 ) nick_name,"
				+ " ('/system/upload/getImage?line_primary='||head_img) headImgSrc,(select supply_name from g_supply where supply_note=g_expert.supply_name limit 1 ) supply_name_x,"
				+ "  * from  g_expert   where  status ='核准' and  expert_type =(SELECT  expert_type from g_expert  where expert_note =?1 ) and expert_note!=?1  limit 2";

		return supplyDao.SelectListMap(sql, expert_note);
	}

	@Override
	public Result getExpertTech() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
