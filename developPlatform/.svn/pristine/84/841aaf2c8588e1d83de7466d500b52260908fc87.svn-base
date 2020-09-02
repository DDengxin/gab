package com.tengzhi.business.sc.pd.gcrw.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.js.cpgy.scgy.dao.MScScgymxDao;
import com.tengzhi.business.sale.saleProduct.saleContract.dao.EXsContractDetailedDao;
import com.tengzhi.business.sc.pd.gcrw.service.GcrwkService;
import com.tengzhi.business.sc.task.sctack.dao.MScScrwGxDao;
import com.tengzhi.business.sc.task.sctack.dao.MScScrwWlDao;
import com.tengzhi.business.sc.task.sctack.dao.ScTackDao;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwGxVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class GcrwServiceImpl implements GcrwkService {

	
	@Autowired
	private SysGenNoteService sysGenNoteService;
	
	@Autowired
	private ScTackDao scTackDao;
	
	
	@Autowired
	private MScScrwGxDao mScScrwGxDao;
	
	
	@Autowired
	private MScScgymxDao mScScgymxDao;
	
	@Autowired
	private MScScrwWlDao mScScrwWlDao;
	
	@Autowired
	private EXsContractDetailedDao eXsContractDetailedDao;


	@Override
	public Result getSrchScpdList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlWhere=" where 1=1 ";
		if(ObjectUtil.isNotEmpty(map.get("srchType"))) {
			if("END".equals(map.get("srchType"))) {
				if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
					sqlWhere+=" and to_char(plan_end_date, 'YYYY-MM-DD') >='"+map.get("srchRq1")+"'";
				}
				if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
					sqlWhere+=" and to_char(plan_end_date, 'YYYY-MM-DD') <='"+map.get("srchRq2")+"'";
				}
			}else {
				if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
					sqlWhere+=" and to_char(plan_start_date, 'YYYY-MM-DD') >='"+map.get("srchRq1")+"'";
				}
				if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
					sqlWhere+=" and to_char(plan_start_date, 'YYYY-MM-DD') <='"+map.get("srchRq2")+"'";
				}
			}
		}
		if(ObjectUtil.isNotEmpty(map.get("srchScmo"))) {
			sqlWhere+=" and sc_mo like '%"+map.get("srchScmo")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchGx"))) {
			sqlWhere+=" and  gx_id = '"+map.get("srchGx")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
			sqlWhere+=" and tcode like '%"+map.get("srchCode")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
			sqlWhere+=" and f_getparamname('GETBCPCODENAME',tname,'','技术','ZZ','"+   SessionUser.getCurrentCorpId()   +"') like '%"+map.get("srchName")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchSize"))) {
			sqlWhere+=" and tsize like '%"+map.get("srchSize")+"%'";
		}
		String sqlString=" select tcode,tname,f_getparamname('GETBCPCODENAME',tname,'','技术','ZZ','"+   SessionUser.getCurrentCorpId()   +"') zzptname,tsize,sc_mo \"scMo\",gx_ord as \"gxOrd\",gx_id as \"gxId\",gx_name as \"gxName\" ,gx_js as \"gxJs\",gx_sl as \"gxSl\",gx_zl as \"gxZl\",gx_cj as \"gxCj\",gx_ct as \"gxCt\",gx_jsyq \"gxJsyq\",gy_jysx \"gyJysx\", gx_sxjj \"gxSxjj\",gx_xxjj as \"gxXxjj\",gx_dojj \"gxDojj\",gx_bq \"gxBq\",gx_stype \"bqStype\",to_char(plan_start_date, 'YYYY-MM-DD') \"planStartDate\",to_char(plan_end_date, 'YYYY-MM-DD') \"planEndDate\"  "
					+ ",f_get_param_name('生产方式',gx_stype,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"stypeName\" ,f_get_param_name('半成品标签',gx_bq,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"bqName\""
					+",f_getparamname('GETTYPEBYRAMNAME',gx_cj,'生产车间','','','"+   SessionUser.getCurrentCorpId()   +"') \"cjName\",f_getname('GETJTNAMES', gx_ct, '', '') \"ctName\",f_get_param_name('半成品检验',gx_sxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"sxjjName\",f_get_param_name('半成品检验',gx_xxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"xxjjName\",f_get_param_name('半成品检验',gx_dojj,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"dojjName\" "
					+ " from m_sc_scrw_gx "+sqlWhere;
		
		return Result.resultOk(mScScrwGxDao.QueryListMap(sqlString));
	}


	@Override
	public Result updateRwGx(MScScrwGxVo vo) throws  IOException {
		if (!vo.getmScScrwGxList().isEmpty()){
			vo.getmScScrwGxList().forEach( item -> {
				mScScrwGxDao.dynamicSave(item,mScScrwGxDao.findById(item.getGxNote()).orElse(null));
			});
		}
		return  Result.resultOkMsg("修改成功！");
	}
	

}
