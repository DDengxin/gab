package com.tengzhi.business.sc.pd.scpd.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.js.cpgy.scgy.dao.MScScgymxDao;
import com.tengzhi.business.sale.saleProduct.saleContract.dao.EXsContractDetailedDao;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import com.tengzhi.business.sc.pd.scpd.service.ScpdService;
import com.tengzhi.business.sc.task.sctack.dao.MScScrwGxDao;
import com.tengzhi.business.sc.task.sctack.dao.MScScrwWlDao;
import com.tengzhi.business.sc.task.sctack.dao.ScTackDao;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctack.model.MScScrw.MScScrw_PK;
import com.tengzhi.business.sc.task.sctack.model.MScScrwGx;
import com.tengzhi.business.sc.task.sctack.model.MScScrwWl.MScScrwWl_PK;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwGxVo;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwWlVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class ScpdServiceImpl implements ScpdService {

	
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
	public BasePage<Map<String, Object>> getSrchTopLeftList(BaseDto baseDto) throws IOException {
		SessionUser ssionuser=SessionUser.SessionUser();
		String where=" and a.data_corp='"+ssionuser.getCorpId()+"'";
		Map<String, String> map = baseDto.getParamsMap();
		if(ObjectUtil.isNotEmpty(map.get("srchRqType"))) {
			if("SC".equals(map.get("srchRqType").toString())){
				if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
					where+=" and a.scjq >='"+map.get("srchRq1")+"' ";
				}
				if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
					where+=" and a.scjq <='"+map.get("srchRq2")+"' ";
				}
			}else{
				if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
					where+=" and a.rq >='"+map.get("srchRq1")+"' ";
				}
				if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
					where+=" and a.rq <='"+map.get("srchRq2")+"' ";
				}
			}
		}

		if(ObjectUtil.isNotEmpty(map.get("srchScCj"))) {
			where+=" and a.sc_cj =  '"+map.get("srchScCj")+"' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchStandard"))) {
			where+=" and  a.standard like '%"+map.get("srchStandard")+"%' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchSize"))) {
			where+=" and  a.cpcode_size like '%"+map.get("srchSize")+"%' ";
		}

		if(ObjectUtil.isNotEmpty(map.get("srchCd"))) {
			where+=" and  b.cpcode_xp like '%"+map.get("srchCd")+"%' ";
		}

		if(ObjectUtil.isNotEmpty(map.get("srchCz"))) {
			where+=" and  a.b.cpcode_name like '%"+map.get("srchCz")+"%' ";
		}

		if(ObjectUtil.isNotEmpty(map.get("srchCustomer"))) {
			where+=" and  a.ht_customer like '%"+map.get("srchCustomer")+"%' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchHtNo"))) {
			where+=" and  a.ht_no like '%"+map.get("srchHtNo")+"%' ";
		}
		String sql1=" select to_char(a.rq,'yyyy-MM-dd') rq,to_char(a.khjq,'yyyy-MM-dd') khjq,to_char(a.xtjq,'yyyy-MM-dd') xtjq,to_char(a.tzjq,'yyyy-MM-dd') tzjq,to_char(a.pdrq,'yyyy-MM-dd') pdrq,to_char(a.scjq,'yyyy-MM-dd') scjq,to_char(a.pdrq+ cast( (sczq ||' day') as interval ),'yyyy-MM-dd') sctqq,a.sc_mo,a.ht_mo,a.ht_no,code,a.scjs,a.sczq,a.scsl,a.sczl,a.scyq,a.scflag,a.data_rq, a.sc_gylx,a.sc_stype,f_getname('GETGYNAME',a.sc_gylx,'','') gylxname, " +
				" a.data_corp,a.standard,f_getparamname('GETBYPARENTID',a.standard,'线材种类','技术','','"+   SessionUser.getCurrentCorpId()   +"','') standard_name "
				+",a.bzyl,f_getname('GETCPNAMEANDSIZE',a.bzyl,'','"+   SessionUser.getCurrentCorpId() +"') bzyl_name "
				+",a.tdyl,f_getname('GETCPNAMEANDSIZE',a.tdyl,'','"+   SessionUser.getCurrentCorpId() +"') tdyl_name "
				+",a.qtyl,f_getname('GETCPNAMEANDSIZE',a.qtyl,'','"+   SessionUser.getCurrentCorpId() +"') qtyl_name "
				+",a.lsyl,f_getname('GETCPNAMEANDSIZE',a.lsyl,'','"+   SessionUser.getCurrentCorpId()  +"') lsyl_name "
				+",f_getname('GETDWEXP',a.ht_customer,'',a.data_corp) ht_customer_name,a.ht_customer "
				+ ",sc_cj,f_getparamname('GETTYPEBYRAMNAME',sc_cj,'生产车间','','','"+SessionUser.getCurrentCorpId()+"') sc_cj_name"
				+" ,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,b.cpcode_size,b.cpcode_size_en,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl"
				+",b.cpcode_xp,f_getparamname('GETCPCODEXP',b.cpcode_xp,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name "
				+ ",f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name "
				+ ",f_getparamname('GETCPCODESIZEEN',b.cpcode_size_en,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name "
				+ ",f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz,f_getname('GETUSERNAME',a.data_man,'',a.data_corp) data_man,f_get_param_name('生产方式',sc_stype,'"+   SessionUser.getCurrentCorpId()   +"','',false) stypename "
				+ ",(case when f_getparamname('GETCPCODESIZEEN',b.cpcode_size_en,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')='0' then f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') else concat_ws('*',f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"'),f_getparamname('GETCPCODESIZEEN',b.cpcode_size_en,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')) end ) size_and_size_en "
				+ " from m_sc_scrw a,e_js_cpcode b where a.code=b.cpcode_id  " + where ;
		String sqlString="select a.*,xcxm01,xcxm02 ,xcxm03 ,xcxm04 ,xcxm05 ,xcxm06 ,xcxm07 ,xcxm08 ,xcxm09 ,xcxm10 ,xcxm11 ,xcxm12 ,xcxm13 ,xcxm14 ,xcxm15 ,xcxm16 ,xcxm17 ,xcxm18 ,xcxm19 ,xcxm20 ,xcxm21 ,xcxm22 ,xcxm23  from ("+sql1+") a left join v_ddhf b on (a.ht_mo=b.ht_mo and a.data_corp=b.data_corp ) ";
		return scTackDao.QueryMapPageList(baseDto,sqlString,true);
	}



}
