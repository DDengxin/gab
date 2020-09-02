package com.tengzhi.business.sc.task.zzlz.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.sc.task.sctack.dao.ScTackDao;
import com.tengzhi.business.sc.task.zzlz.service.ZzlzService;

import cn.hutool.core.util.ObjectUtil;

@Service
@Transactional
public class ZzlzServiceImpl implements ZzlzService {

	@Autowired
	private ScTackDao scTackDao;
	

	
	
	@Override
	public BasePage<Map<String, Object>> getSrchGridList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String where=" where 1=1 ";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
            where+=" and rq >='"+map.get("srchRq1")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
            where+=" and rq <='"+map.get("srchRq2")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchBgc"))) {
			where+=" and bgc like '%"+map.get("srchBgc")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
			where+=" and wlcode like '%"+map.get("srchCode")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
			where+=" and f_getparamname('GETBCPCODENAME',wl_name,'','技术',wl_type,'"+   SessionUser.getCurrentCorpId()   +"') like '%"+map.get("srchName")+"%' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchSize"))) {
			where+=" and wl_size like '%"+map.get("srchSize")+"%'";
		}
		String sql="select *,f_getparamname('GETBCPCODENAME',wl_name,'','技术',wl_type,'"+   SessionUser.getCurrentCorpId()   +"') zzpname,f_getname('GETGXNAME', gx, '', '') gxname,f_getname('GETUSERNAME',czman,'',data_corp) manname"
				+ " from  v_sc_gclistview "+where ;
		String countsql="select count(*) cn from ("+sql+") t";
		baseDto.setSortField("rq");
		baseDto.setSortOrder("desc");
		return scTackDao.QueryPageList(sql,countsql,baseDto);
	}

	
}
