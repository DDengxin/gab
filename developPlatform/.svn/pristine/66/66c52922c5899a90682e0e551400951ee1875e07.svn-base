package com.tengzhi.business.cg.da.materialArchives_无处引用等待删除.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.da.materialArchives_无处引用等待删除.dao.MaterialArchivesDao;
import com.tengzhi.business.cg.da.materialArchives_无处引用等待删除.service.MaterialArchivesService;
import com.tengzhi.business.js.product.model.Jscpcode;
@Service
@Transactional
@Deprecated
public class MaterialArchivesServiceImpl implements MaterialArchivesService{

	@Autowired
	private MaterialArchivesDao materialArchivesDao;

	@Override
	public BasePage<Jscpcode> findAll(BaseDto baseDto) throws IOException {
		SessionUser sessionUser=SessionUser.SessionUser();
		Map<String, String> map = baseDto.getParamsMap();
	    String where = "where 1=1 and e.cpcode_fl in (SELECT param_id FROM sys_param WHERE parent_id IN (( select param_id from v_sysparamtree where parent_id='CPDL' and root_param_id='CPDL')) ) ";
	    if (StringUtils.isNotBlank(map.get("cpcodeId"))) {
            where = " and e.cpcodeId like '%" + map.get("cpcodeId") + "%'";
        }
	    if (StringUtils.isNotBlank(map.get("cpcodeName"))) {
            where = " and  e.cpcodeName like '%" + map.get("cpcodeName") + "%'";
        }
	    if (StringUtils.isNotBlank(map.get("cpcodeNameEn"))) {
            where = " and  e.cpcodeNameEn like '%" + map.get("cpcodeNameEn") + "%'";
        }
	    String sql = "select * from   e_js_cpcode e " + where + " ";
	    String count = "select count(1) from  e_js_cpcode e " + where;
	    return materialArchivesDao.QueryPageLists(sql, count, baseDto);
	}

	@Override
	public Jscpcode findById(String Id) {
		return materialArchivesDao.findById(Id).orElse(null);
	}

	@Override
	public Jscpcode save(Jscpcode jscpcode) throws Exception {
		SessionUser sessionUser=SessionUser.SessionUser();
		jscpcode.setDataRq(new Date());
		jscpcode.setDataMan(sessionUser.getUserId());
		jscpcode.setDataCorp(sessionUser.getCorpId());
		//jscpcode.setCpcodeFl();
		return materialArchivesDao.save(jscpcode);
	}

	@Override
	public void update(Jscpcode jscpcode) {
		materialArchivesDao.dynamicSave(jscpcode, materialArchivesDao.findById(jscpcode.getCpcodeId()).orElse(null));

	}

	@Override
	public void deleteById(String Id) {
		materialArchivesDao.deleteById(Id);

	}

	@Override
	public List<Map> getTreeList(String mod, String type) {
		String sqlString="select param_id combid,param_name combtext,parent_id pid from  sys_param  where param_mod='"+mod+"' and param_type='"+type+"' and param_status='true' and parent_id='WL' ";
		return materialArchivesDao.QueryListMap(sqlString);
	}

	@Override
	public List<Map> getCheckList(String mod, String type) {
		String sqlString="select param_id combid,param_name combtext,parent_id pid from  sys_param  where param_mod='"+mod+"' and param_type='"+type+"' and param_status='true'";
		return materialArchivesDao.QueryListMap(sqlString);
	}
	
	
	
	
	
	
}
