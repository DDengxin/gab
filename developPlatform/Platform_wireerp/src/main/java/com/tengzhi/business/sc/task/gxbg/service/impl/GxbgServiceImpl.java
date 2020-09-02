package com.tengzhi.business.sc.task.gxbg.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.js.cpgy.scgx.dao.MScScgxDao;
import com.tengzhi.business.production.productionSite.siteTask.Vo.MScGclistVo;
import com.tengzhi.business.production.productionSite.siteTask.dao.MScGclistDao;
import com.tengzhi.business.production.productionSite.siteTask.model.MScGclist;
import com.tengzhi.business.sc.task.gxbg.service.GxbgService;
import com.tengzhi.business.sc.task.sctack.dao.ScTackDao;
import com.tengzhi.business.system.core.service.SysGenNoteService;

import cn.hutool.core.util.ObjectUtil;

@Service
@Transactional
public class GxbgServiceImpl implements GxbgService {

	@Autowired
	private ScTackDao scTackDao;
	
	@Autowired
	private MScScgxDao  mScScgxDao;

	@Autowired
	private MScGclistDao mScGclistDao;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;
	
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
			where+=" and code like '%"+map.get("srchCode")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
			where+=" and f_getparamname('GETBCPCODENAME',tname,'','技术',wl_type,'"+   SessionUser.getCurrentCorpId()   +"') like '%"+map.get("srchName")+"%' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchSize"))) {
			where+=" and tsize like '%"+map.get("srchSize")+"%'";
		}
		String sql="select *,f_getparamname('GETBCPCODENAME',tname,'','技术',wl_type,'"+   SessionUser.getCurrentCorpId()   +"') zzpname,f_getname('GETGXNAME', gx, '', '') gxname,f_getparamname('GETTYPEBYRAMNAME',tcj,'生产车间','','','"+   SessionUser.getCurrentCorpId()   +"') cjname,f_getname('GETJTNAMES', tct, '', '') ctname"
				+ ",f_getname('GETUSERNAME',do_man,'',data_corp) manname,f_get_param_name('生产班次',tbc,'"+   SessionUser.getCurrentCorpId()   +"','cn',false) tbcname "
				+ " from  v_sc_gxbg "+where ;
		String countsql="select count(*) cn from ("+sql+") t";
		baseDto.setSortField("rq");
		baseDto.setSortOrder("desc");
		return scTackDao.QueryPageList(sql,countsql,baseDto);
	}

	@Override
	public Result getByScmo(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		Object scMo=map.get("scMo");
		String sql="select sc_gylx   from m_sc_scrw where sc_mo=?";
		Object[] val = {scMo};
		List<Map> gclsit=scTackDao.QueryListMap(sql,val);
		return Result.resultOk(gclsit);
	}
	
	@Override
	public  Result getByScgx(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		Object scMo=map.get("scMo");
		Object gxId=map.get("gxId");
		String sql=" select COALESCE(gx_sl,0) sl ,COALESCE(gx_cj,'') tcj,COALESCE(gx_ct,'') tct,COALESCE(tcode,'') tcode,COALESCE(tname,'') tname,COALESCE(tsize,'') tsize from  m_sc_scrw_gx where sc_mo=?1 and gx_id=?2 ";
		Object[] val = { scMo,gxId };
		List<Map> gclsit=mScScgxDao.QueryListMap(sql,val);
		return Result.resultOk(gclsit.get(0));
	}
	
	@Override
	public Result gxxl(MScGclistVo vo) throws Exception {
		SessionUser securityUser=SessionUser.SessionUser();
		//自动生成本工程 编码规则：工序+班别+YYMMDD+0001
		String prefix=vo.getmScGclist().getGx()+vo.getmScGclist().getTbc();
		String bgc=sysGenNoteService.getyyMMNote4(MScGclist.class,prefix);
		vo.getmScGclist().setBgc(bgc);
		vo.getmScGclist().setDoDate(new Date());
		vo.getmScGclist().setDataCorp(securityUser.getCorpId());
		vo.getmScGclist().setGcCl("SC");
		mScGclistDao.save(vo.getmScGclist());
		return Result.resultOk("下料成功！");
	}
	
}
