package com.tengzhi.business.mesGz.gzda.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.business.mesGz.gzda.dao.GzcodeDao;
import com.tengzhi.business.mesGz.gzda.dao.GzmjDao;
import com.tengzhi.business.mesGz.gzda.model.Gzda;
import com.tengzhi.business.mesGz.gzda.model.GzdaVo;
import com.tengzhi.business.mesGz.gzda.service.GzcodeService;

@Service
@Transactional
public class GzcodeServiceImpl implements GzcodeService {
	@Autowired
	private GzcodeDao gzcodeDao;

	@Autowired
	private GzmjDao gzmjDao;

	@Override
	public BasePage<Gzda> findAll(BaseDto baseDto) throws IOException {

		Map<String, String> map = baseDto.getParamsMap();
		String where = "where 1=1 ";
		/*
		 * if (StringUtils.isNotBlank(map.get("cpcodeId"))) where =
		 * " and cpcodeId like '%" + map.get("cpcodeId") + "%'"; if
		 * (StringUtils.isNotBlank(map.get("cpcodeName"))) where =
		 * " and  cpcodeName like '%" + map.get("cpcodeName") + "%'"; if
		 * (StringUtils.isNotBlank(map.get("cpcodeNameEn"))) where =
		 * " and  cpcodeNameEn like '%" + map.get("cpcodeNameEn") + "%'";
		 */
		String sql = "select e.gm_code,e.gm_cpcode,e.gm_status,e.gm_cl,e.gm_sl,e.gm_sc,c.cpcode_size,c.cpcode_name_en,c.cpcode_name,c.cpcode_bz,c.cpcode_fl  from (select * from e_gm_gzda )e left join e_js_cpcode c on e.gm_cpcode=c.cpcode_id  ";
		String count = "select count(*) cn from (" + sql + ")t";
		
		if (StringUtils.isNotBlank(map.get("cpcodeId"))) {
            where = " and e.cpcodeId like '%" + map.get("cpcodeId") + "%'";
        }
		if (StringUtils.isNotBlank(map.get("cpcodeName"))) {
            where = " and  e.cpcodeName like '%" + map.get("cpcodeName") + "%'";
        }
		if (StringUtils.isNotBlank(map.get("cpcodeNameEn"))) {
            where = " and  e.cpcodeNameEn like '%" + map.get("cpcodeNameEn") + "%'";
        }
		return gzcodeDao.QueryPageLists(sql, count, baseDto);
	}





	@Override
	public  List<Map> findGzmj(BaseDto baseDto) throws IOException {
		// TODO Auto-generated method stub
		Map<String, String> map = baseDto.getParamsMap();
		String where = "";
		String wheres="";
		if (StringUtils.isNotBlank(map.get("code"))) {
            where = " where 1=1 and code like '%" + map.get("code") + "%'";
        }
		if (StringUtils.isNotBlank(map.get("cpcodeName"))) {
            wheres = " where 1=1  and  cpcode_name like '%" + map.get("cpcodeName") + "%'";
        }
		String sqlString = "select v.code,v.sl,e.cpcode_name,e.cpcode_size,e.cpcode_fl,e.cpcode_bz from (select * from v_kc "+where+") v left join (select * from e_js_cpcode "+wheres+") e on v.code=e.cpcode_id ";
		return gzcodeDao.QueryListMap(sqlString, "");
	}





	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @Override public Result save(GzdaVo vo) throws IOException, Exception {
	 * List<Gzda> addECkIns=new ArrayList<Gzda>(); List<Gzda> modifyedECkIns=new
	 * ArrayList<Gzda>(); SecurityUser securityUser = (SecurityUser)
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * vo.getE_gm_gzda().setGmStatus("入库");
	 * vo.getE_gm_gzda().setMan(securityUser.getUsername()); //业务逻辑判断start //新增
	 * for(int i=0;i<vo.getAddedList().size();i++) { E_gm_gzda
	 * model=vo.getAddedList().get(i);
	 * model.setGmStatus(vo.getE_gm_gzda().getGmStatus());
	 * model.setGmCl(vo.getE_gm_gzda().getGmCl());
	 * model.setGmCode(vo.getE_gm_gzda().getGmCode());
	 * model.setGmSc(vo.getE_gm_gzda().getGmSc()); addECkIns.add(model); } //修改 if
	 * (!vo.getModifyedList().isEmpty()) { for(int
	 * i=0;i<vo.getModifyedList().size();i++) { E_gm_gzda
	 * item=vo.getModifyedList().get(i);
	 * item.setGmStatus(vo.getE_gm_gzda().getGmStatus());
	 * item.setGmCl(vo.getE_gm_gzda().getGmCl());
	 * item.setGmCode(vo.getE_gm_gzda().getGmCode());
	 * item.setGmSc(vo.getE_gm_gzda().getGmSc()); modifyedECkIns.add(item); } }
	 * //开始保存到数据库 if (!addECkIns.isEmpty()) { addECkIns.forEach(item -> {
	 * gzcodeDao.save(item); }); } return Result.resultOk(vo.getE_gm_gzda()); }
	 */



}
