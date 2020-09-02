package com.tengzhi.business.sc.task.sctack.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.transaction.Transactional;

import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.security.common.model.SessionUser;

import com.tengzhi.business.sc.task.sctack.model.MScScrwGx;
import com.tengzhi.business.sc.task.sctack.model.MScScrwWl.MScScrwWl_PK;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.js.cpgy.scgy.dao.MScScgymxDao;
import com.tengzhi.business.sale.saleProduct.saleContract.dao.EXsContractDetailedDao;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import com.tengzhi.business.sc.task.sctack.dao.MScScrwGxDao;
import com.tengzhi.business.sc.task.sctack.dao.MScScrwWlDao;
import com.tengzhi.business.sc.task.sctack.dao.ScTackDao;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import com.tengzhi.business.sc.task.sctack.model.MScScrw.MScScrw_PK;
import com.tengzhi.business.sc.task.sctack.service.ScTackService;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwGxVo;
import com.tengzhi.business.sc.task.sctack.vo.MScScrwWlVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.web.servlet.ModelAndView;

@Service
@Transactional
public class ScTackServiceImpl implements ScTackService {

	
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
	public BasePage<Map<String, Object>> getSrchGridList(BaseDto baseDto) throws IOException {
		SessionUser ssionuser=SessionUser.SessionUser();
		Map<String, String> map = baseDto.getParamsMap();
		String where=" where 1=1 and data_corp='"+ssionuser.getCorpId()+"' ";//and cpcode_fl in (select param_id from sys_param where param_name like '%在制品%')
		if(ObjectUtil.isNotEmpty(map.get("key"))) {
			where+=" and cpcode_size like '%"+map.get("key")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("cptype"))) {
			where+=" and cpcode_type ='"+map.get("cptype")+"'";
		}
		String sql="select cpcode_id code ,cpcode_name tname,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') zzptname ,cpcode_size tsize from e_js_cpcode "+where ;
		String countsql="select count(*) cn from ("+sql+") t";
		baseDto.setSortField("cpcode_id");
		baseDto.setSortOrder("desc");
		return scTackDao.QueryPageList(sql,countsql,baseDto);
	}


	@Override
	public List<MScScrwGx> findByGxId(String gxId) {
		List<MScScrwGx> mScScrwGxList = mScScrwGxDao.findByGxId(gxId);
		return mScScrwGxList;
	}

	@Override
	public BasePage<MScScrw> getSrchTopLeftList(BaseDto baseDto) throws IOException {
		SessionUser ssionuser=SessionUser.SessionUser();
		String where=" and a.data_corp='"+ssionuser.getCorpId()+"'";
		Map<String, String> map = baseDto.getParamsMap();
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
            where+=" and a.rq >='"+map.get("srchRq1")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
            where+=" and a.rq <='"+map.get("srchRq2")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchScmo"))) {
            where+=" and  a.sc_mo like '%"+map.get("srchScmo")+"%' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchHtmo"))) {
            where+=" and a.ht_mo like  '%"+map.get("srchHtmo")+"%' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchScgy"))) {
			where+=" and a.sc_gylx =  '"+map.get("srchScgy")+"' ";
		}

		if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
			where+=" and b.cpcode_type =  '"+map.get("htStype")+"' ";
		}
		String sqlString=" select a.rq,a.sc_mo,a.ht_mo,code,a.scjs,a.sczq,a.sctqq,a.scsl,a.sczl,a.scjq,a.scyq,a.scflag,a.data_rq, a.sc_gylx,a.sc_stype,f_getname('GETGYNAME',a.sc_gylx,'','') gylxname, " +
				" f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,b.cpcode_size,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl"
				+ ",f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz,f_getname('GETUSERNAME',a.data_man,'',a.data_corp) data_man,f_get_param_name('生产方式',sc_stype,'"+   SessionUser.getCurrentCorpId()   +"','',false) stypename "
				+ " from m_sc_scrw a,e_js_cpcode b where a.code=b.cpcode_id  " + where ;
		return scTackDao.QueryPageLists(baseDto,sqlString+" order by a.rq desc,a.sc_mo desc ");
	}
	
	
	@Override
	public BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String scMo=scTackDao.getSingleResult("select sc_mo from e_xs_contract_detailed where ht_mo='"+map.get("htMo")+"' ");
		String sqlString="";
		if(StringUtils.isNotBlank(scMo)) {
			sqlString=" select a.rq,a.sc_mo,a.ht_mo,code,a.scjs,a.scsl,a.sczl,to_char(a.scjq,'yyyy-MM-dd')  scjq,a.scyq,a.scflag,a.data_man,to_char(a.data_rq,'yyyy-MM-dd') data_rq " +
					" ,f_get_param_name('产品大类',sc_stype,'"+   SessionUser.getCurrentCorpId()   +"') typename,"
					+ "f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,"
					+ "b.cpcode_size,"
					+ " f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name, "
					+ "f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,\r\n" + 
					" f_getparamname('GETCPCODEXP',cpcode_xp,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name, "
					+ "f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl,"
					+ "f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz"
					+ " from m_sc_scrw a,e_js_cpcode b where a.code=b.cpcode_id and a.sc_mo= '"+scMo+"'";
		}else {
			sqlString=" select a.rq,a.sc_mo,a.ht_mo,code,a.scjs,a.scsl,a.sczl, to_char(a.scjq,'yyyy-MM-dd') scjq,a.scyq,a.scflag,a.data_man,to_char(a.data_rq,'yyyy-MM-dd') data_rq " +
					" ,f_get_param_name('产品大类',sc_stype,'"+   SessionUser.getCurrentCorpId()   +"') typename,"
					+ "f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,"
					+ "b.cpcode_size,"
					+ "f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name,"
					+ "f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,"
					+ "f_getparamname('GETCPCODEXP',cpcode_xp,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name,  "
					+ "f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl,"
					+ "f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz"
					+ " from m_sc_scrw a,e_js_cpcode b where a.code=b.cpcode_id and a.ht_mo= '"+map.get("htMo")+"'";
		}
		
		//return scTackDao.QueryPageLists(baseDto,sqlString);
		return scTackDao.QueryMapPageList(baseDto, sqlString,true);
	}
	
	@Override
	public BasePage<Map<String, Object>> getSrchBottomLeftList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlString=" select 'T' as sign, a.*,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',a.wl_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,b.cpcode_size,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',a.wl_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_fl"
				+ " ,f_getname('GETGXNAME', scrw_gx, '', '') gxname,f_getname('GETGXANDLIBNAME',wl_lib,a.wl_type,'') libname,f_get_param_name('产品大类',wl_type,'"+   SessionUser.getCurrentCorpId()   +"') typename,f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz,b.cpcode_xp,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03,b.cpcode_size_en "
				+ " from m_sc_scrw_wl a,e_js_cpcode b where a.wl_code=b.cpcode_id and a.wl_gxnote='"+map.get("scrwGxnote")+"' and scrw_mo='"+map.get("scrwMo")+"'";
		return mScScrwWlDao.QueryMapPageList(baseDto,sqlString+" order by wl_code desc ",true);
	}

	@Override
	public BasePage<Map<String,Object>> getProductionTaskSelectList(BaseDto dto) throws IOException {
		Map<String, String> map = dto.getParamsMap();
		//通过
		String where = SqlJoint.start()
				.eq("wl_lib",map.get("wlLib"))
				.eq("wl_type",map.get("wlType")).getAndSuffixSqlStr();
        String sql = " select tmp.*, "
				+" f_getparamname('GETBCPCODENAME',cpcode.cpcode_name,'','技术',tmp.wl_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name, "
				+" cpcode.cpcode_size, "
				+" f_getparamname('GETBYCPCODEFL',cpcode.cpcode_fl,'','技术',tmp.wl_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl, "
				+" f_getname ( 'GETGXANDLIBNAME', wl_lib, tmp.wl_type, '' ) wl_lib_name, "
				+" f_get_param_name('产品大类',wl_type,'"+   SessionUser.getCurrentCorpId()   +"') wl_type_name, "
				+" f_getparamname('GETBYPARENTID',cpcode.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"') cpcode_bz, "
				+" cpcode.cpcode_xp, cpcode.cpcode_check, cpcode.cpcode01, cpcode.cpcode02, cpcode.cpcode03, cpcode.cpcode_size_en "
				+" from ( "
				+" select scrw_wl.wl_type,scrw_wl.wl_code,scrw_wl.wl_lib, "
				//合计数量
				+" sum(coalesce(scrw_wl.wl_sl,0)) wl_sl, "
				//合计重量
				+" sum(coalesce(scrw_wl.wl_zl,0)) wl_zl "
				+" from m_sc_scrw_wl scrw_wl "
				+" where 1=1 " + where
				+" group by scrw_wl.wl_type,scrw_wl.wl_code,scrw_wl.wl_lib "
				+" ) tmp "
				+" left join e_js_cpcode cpcode on tmp.wl_code = cpcode.cpcode_id ";
		return mScScrwWlDao.QueryMapPageList(dto,sql,true);
	}
	
	@Override
	public Result xsddSplit(MScScrw model) throws  Exception {
		//拆分数不能超过可拆分数
		String htsl=scTackDao.getSingleResult("select (b.ht_sl-COALESCE((select sum(scsl) from  m_sc_scrw where ht_mo=b.ht_mo and data_corp=b.data_corp),0) ) as ht_sl from e_xs_contract_detailed b where b.ht_mo=? ",model.getHtMo());
		if(Float.parseFloat(htsl)<model.getHtsl()) {
			return  Result.error("生产数量不能超过可拆分数！");
		}
		//end
		String note  = sysGenNoteService.getNote(EXsContract.class, "RW", "yyMM", 4);
		SessionUser securityUser=SessionUser.SessionUser();
		model.setScMo(note);
		//获取日产能 向上取整
		Map<String,Object> rstmap=scTackDao.QueryToFirstMap("select ceil("+	model.getScsl()+"/cl_rcl) zqctn from m_sc_ggcl where cl_code='"+model.getCode()+"' ");
		if(ObjectUtil.isNotEmpty(rstmap.get("zqctn"))) {
			String zqctn =rstmap.get("zqctn").toString();
			model.setSczq(new BigDecimal(zqctn));
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(model.getScjq());
			calendar.add(calendar.DATE,Integer.parseInt(zqctn)*-1); //把日期往后增加一天,整数  往后推,负数往前移动
			calendar.getTime(); //这个时间就是日期往后推一天的结果
			model.setSctqq(calendar.getTime());
		}else{
			model.setSczq(new BigDecimal("0"));
			model.setSctqq(model.getScjq());
		}
		model.setScflag("登记");
		//model.setDataMan(securityUser.getUserId());
		model.setDataCorp(securityUser.getCorpId());
		//model.setDataRq(new Date());
		scTackDao.save(model);
		return  Result.resultOkMsg("拆分成功！");
	}

	@Override
	public Result xsddHp(MScScrw model) throws  Exception {
		String note  = sysGenNoteService.getNote(EXsContract.class, "RW", "yyMM", 4);
		SessionUser securityUser=SessionUser.SessionUser();
		String htMos=model.getHtMo();
		model.setScMo(note);
		model.setScflag("登记");
		//获取日产能 向上取整
		Map<String,Object> rstmap=scTackDao.QueryToFirstMap("select ceil("+	model.getScsl()+"/cl_rcl) zqctn from m_sc_ggcl where cl_code='"+model.getCode()+"' ");
		if(ObjectUtil.isNotEmpty(rstmap.get("zqctn"))) {
			String zqctn =rstmap.get("zqctn").toString();
			model.setSczq(new BigDecimal(zqctn));
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(model.getScjq());
			calendar.add(calendar.DATE,Integer.parseInt(zqctn)*-1); //把日期往后增加一天,整数  往后推,负数往前移动
			calendar.getTime(); //这个时间就是日期往后推一天的结果
			model.setSctqq(calendar.getTime());
		}else{
			model.setSczq(new BigDecimal("0"));
			model.setSctqq(model.getScjq());
		}
		//model.setDataMan(securityUser.getUserId());
		model.setDataCorp(securityUser.getCorpId());
		//model.setDataRq(new Date());
		scTackDao.save(model);
		//合批需要将任务单更新到订单明细表
		eXsContractDetailedDao.executeUpdate("update EXsContractDetailed set scMo='"+note+"' where htMo in ('"+htMos.replace(",", "','")+"')");
		return  Result.resultOkMsg("合并成功！");
	}
	
	@Override
	public Result getSrchTopRightList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		Object scmo=map.get("scMo");
		Object gyid=map.get("gyId");
		String cn=mScScrwGxDao.getSingleResult("select count(*) cn from m_sc_scrw_gx where sc_mo=? ", scmo.toString());
		String sqlString="";
		if(Integer.parseInt(cn)==0) {
			List<Map> 	listMap=mScScrwGxDao.QueryListMap("select COALESCE(scjs,0) scjs,COALESCE(scsl,0) scsl,COALESCE(sczl,0) sczl from m_sc_scrw where sc_mo=?", scmo);
			Map rwMap =listMap.get(0);
			String scjs=rwMap.get("scjs").toString();
			String scsl=rwMap.get("scsl").toString();
			String sczl=rwMap.get("sczl").toString();
			String jq=mScScgymxDao.getSingleResult("select to_char(scjq, 'YYYY-MM-DD') jq from m_sc_scrw where sc_mo=? ", scmo);
			String now=mScScgymxDao.getSingleResult("select to_char(now(), 'YYYY-MM-DD') now");
			sqlString="select gy_id \"gyId\",'"+scmo+"' \"scMo\",a.gx_ord as \"gxOrd\",a.gx_id as \"gxId\",b.gx_name as \"gxName\",'"+scjs+"' as \"gxJs\",'"+scsl+"' as \"gxSl\",'"+sczl+"' as \"gxZl\",b.gx_cj as \"gxCj\",b.gx_ct as \"gxCt\",gx_jsyq as \"gxJsyq\",gy_jysx as \"gyJysx\",b.gx_sxjj  \"gxSxjj\",b.gx_xxjj as \"gxXxjj\",gx_dojj \"gxDojj\",b.gx_bq \"gxBq\",b.gx_stype \"gxStype\",'"+now+"' \"planStartDate\",'"+jq+"' \"planEndDate\" "
					+",f_getparamname('GETTYPEBYRAMNAME',gx_cj,'生产车间','','','"+   SessionUser.getCurrentCorpId()   +"') \"cjName\",f_getname('GETJTNAMES', gx_ct, '', '') \"ctName\",f_get_param_name('生产方式',gx_stype,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"stypeName\" ,f_get_param_name('半成品标签',gx_bq,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"bqName\" "
					+",f_get_param_name('半成品检验',gx_sxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"sxjjName\",f_get_param_name('半成品检验',gx_xxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"xxjjName\",f_get_param_name('半成品检验',gx_dojj,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"dojjName\",'added' _state,'' \"gxNote\" "
					+ " from m_sc_scgymx a ,m_sc_scgx b  where a.gx_id=b.gx_id and  gy_id='"+gyid+"' order by b.gx_ord asc ";
		}else {

			sqlString=" select tcode,tname,f_getparamname('GETBCPCODENAME',tname,'','技术','ZZ','"+   SessionUser.getCurrentCorpId()   +"') zzptname,tsize,'"+gyid+"' \"gyId\",sc_mo \"scMo\",gx_ord as \"gxOrd\",gx_id as \"gxId\",gx_name as \"gxName\" ,gx_js as \"gxJs\",gx_sl as \"gxSl\",gx_zl as \"gxZl\",gx_cj as \"gxCj\",gx_ct as \"gxCt\",gx_jsyq \"gxJsyq\",gy_jysx \"gyJysx\", gx_sxjj \"gxSxjj\",gx_xxjj as \"gxXxjj\",gx_dojj \"gxDojj\",gx_bq \"gxBq\",gx_stype \"bqStype\",to_char(plan_start_date, 'YYYY-MM-DD') \"planStartDate\",to_char(plan_end_date, 'YYYY-MM-DD') \"planEndDate\"  "
					+ ",f_get_param_name('生产方式',gx_stype,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"stypeName\" ,f_get_param_name('半成品标签',gx_bq,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"bqName\""
					+",f_getparamname('GETTYPEBYRAMNAME',gx_cj,'生产车间','','','"+   SessionUser.getCurrentCorpId()   +"') \"cjName\",f_getname('GETJTNAMES', gx_ct, '', '') \"ctName\",f_get_param_name('半成品检验',gx_sxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"sxjjName\",f_get_param_name('半成品检验',gx_xxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"xxjjName\",f_get_param_name('半成品检验',gx_dojj,'"+   SessionUser.getCurrentCorpId()   +"','',false) \"dojjName\" ,gx_note \"gxNote\""
					+ ",(select case when count(1)>0 then '已上' else '未上' end isytc from m_sc_gclist_sl gc where gc.scrw_no=m_sc_scrw_gx.sc_mo and gc.gx_note=m_sc_scrw_gx.gx_note) isytc from m_sc_scrw_gx where sc_mo='"+scmo+"' order by gx_ord asc ";
		}
		return Result.resultOk(mScScrwGxDao.QueryListMap(sqlString));
	}
	
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

	//废弃方法
	@Override
	public Result saveRwGx(MScScrwGxVo vo) throws  IOException {
		String scMo=vo.getmScScrw().getScMo();
		String htMo=vo.getmScScrw().getHtMo();
		//先删后增
		MScScrw model=vo.getmScScrw();
		model.setScflag("排单");
		if (!vo.getmScScrwGxList().isEmpty()){
			deleteRwgx(scMo);
			vo.getmScScrwGxList().forEach( item -> {

				item.setScMo(scMo);
				mScScrwGxDao.save(item);
			});
		}
		
		MScScrw_PK pk=new MScScrw_PK(scMo,htMo);
		scTackDao.dynamicSave(model,scTackDao.findById(pk).orElse(null));
		return  Result.resultOkMsg("保存成功！");
	}

	@Override
	public Result saveGx(MScScrwGxVo gxVo) {
		MScScrw model=gxVo.getmScScrw();
		SessionUser securityUser=SessionUser.SessionUser();
		String scMo=model.getScMo();
		String htMo=model.getHtMo();
		model.setScflag("排单");
		model.setDataMan(securityUser.getUserId());
		model.setDataRq(new Date());
			if (null != gxVo.getAddedList() && !gxVo.getAddedList().isEmpty()) {
				gxVo.getAddedList().forEach(item -> {
					item.setScMo(scMo);
					//生成工序唯一单号
					item.setGxNote(sysGenNoteService.getNote(MScScrw.class, "", "yyMMdd", 6));
					mScScrwGxDao.save(item);
				});
			}
			if (!gxVo.getDeletedList().isEmpty()) {
				mScScrwGxDao.deleteAll(gxVo.getDeletedList());
			}
			if (!gxVo.getModifyedList().isEmpty()) {
				gxVo.getModifyedList().forEach(item -> {
					mScScrwGxDao.dynamicSave(item,mScScrwGxDao.findById(item.getGxNote()).orElse(null));
				});
			}
			MScScrw_PK pk=new MScScrw_PK(scMo,htMo);
			scTackDao.dynamicSave(model,scTackDao.findById(pk).orElse(null));
			return Result.resultOkMsg("保存成功！");
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
	


	@Override
	public void deleteRwgx(String scMo) {
		//删除m_sc_scrw_gx表
		mScScrwGxDao.deleteRwgx(scMo);
		//清空任务单m_sc_scrw表的sc_gylx
		scTackDao.clearGylx(scMo);
	}
	
	@Override
	public Result saveRwWl(MScScrwWlVo vo) throws  IOException {
		String scrwMo=vo.getmScScrwGx().getScMo();
		String scrwGxnote=vo.getmScScrwGx().getGxNote();
		//保存之前先判断工艺有没有保存
		int cn=Integer.parseInt(mScScrwGxDao.getSingleResult("select count(*) as cn from m_sc_scrw_gx where sc_mo=?1 and wl_gxnote=?2 ",scrwMo,scrwGxnote));
		if(cn==0) {
			return  Result.error("请先保存该任务单的生产工艺路线！");
		}
		//先删后增
		if (!vo.getmScScrwWlList().isEmpty()){
			deleteRwWl(scrwMo,scrwGxnote);
			vo.getmScScrwWlList().forEach( item -> {
				mScScrwWlDao.save(item);
			});
		}
		return  Result.resultOkMsg("保存成功！");
	}

	@Override
	public Result saveWl(MScScrwWlVo wlVo) throws Exception {
		String scrwMo=wlVo.getmScScrwGx().getScMo();
		String scrwGxnote=wlVo.getmScScrwGx().getGxNote();
		//保存之前先判断工艺有没有保存
		int cn=Integer.parseInt(mScScrwGxDao.getSingleResult("select count(*) as cn from m_sc_scrw_gx where sc_mo=?1 and gx_note=?2 ",scrwMo,scrwGxnote));
		if(cn==0) {
			return  Result.error("请先保存该任务单的生产工艺路线！");
		}
		if (null != wlVo.getAddedList() && !wlVo.getAddedList().isEmpty()) {
			wlVo.getAddedList().forEach(item -> {
				System.out.println("scrwGxnote add= " + scrwGxnote);
				item.setWlGxnote(scrwGxnote);
				mScScrwWlDao.save(item);
			});
		}
		if (!wlVo.getDeletedList().isEmpty()) {
				mScScrwWlDao.deleteAll(wlVo.getDeletedList());
		}
		if (!wlVo.getModifyedList().isEmpty()) {
			wlVo.getModifyedList().forEach(item -> {
				System.out.println("scrwGxnote eidt= " + scrwGxnote);
				item.setWlGxnote(scrwGxnote);
				MScScrwWl_PK pk=new MScScrwWl_PK(scrwGxnote,scrwMo,item.getWlCode());
				mScScrwWlDao.dynamicSave(item,mScScrwWlDao.findById(pk).orElse(null));
			});
		}
		Double sbl =Double.parseDouble(mScScrwWlDao.getSingleResult("select sum(bl) as sbl from m_sc_scrw_wl where scrw_mo=?1 and wl_gxnote=?2 ",scrwMo,scrwGxnote));
		if(sbl!=1){
			throw new RuntimeException("物料比例合计必须为1 ");
		}else {
			return Result.resultOkMsg("保存成功！");
		}
	}


	@Override
	public void deleteRwWl(String scrwMo,String wlNote) {
		//删除m_sc_scrw_wl表
		mScScrwWlDao.deleteByScrwMo(scrwMo,wlNote);
	}
	
	@Override
	public Result deleteByScMo(String scMo){
		int count=Integer.parseInt(scTackDao.getSingleResult("select count(*) cn from m_sc_gclist_sl where scrw_no=? ", scMo));
		if(count>0) {
			return Result.error("该任务单已经投料，不能取消！");
		}
		//删除物料
		mScScrwWlDao.deleteByScrwMoAll(scMo);
		//删除工序
		mScScrwGxDao.deleteRwgx(scMo);
		//删除任务
		scTackDao.deleteByScMo(scMo);
		return	Result.resultOkMsg("取消成功！");
	}

	@Override
	public Map<String,Object> veriFication(String scmo,String scgxnote){
		SessionUser securityUser=SessionUser.SessionUser();
		String sql="",where="where data_corp='"+securityUser.getCorpId()+"' and  scrw_no='"+scmo+"'";
		String gcwhere=" where data_corp='"+securityUser.getCorpId()+"' and  sc_mo='"+scmo+"'";
		Map<String,Object> rstmap = new HashMap<String,Object>();
		rstmap.put("status","N");
		if(ObjectUtil.isNotEmpty(scgxnote)){//工序信息
			where += " and gx_note='"+scgxnote+"'";
		}
		//sql="select count(1) from m_sc_gclist_sl "+where;
		sql="select sum(ctn) from (\n" +
			 " select count(1) ctn from m_sc_gclist_sl " +where +
			 " union all \n" +
			 " select count(1) ctn from m_sc_gclist " +  gcwhere+
			 ") t ";
		String ctn =scTackDao.QueryFirstString(sql);
		if(!"0".equals(ctn)){
			rstmap.put("status","Y");
			rstmap.put("message","该单已被使用,不能操作");
		}
		return rstmap;
	}

	@Override
	public Result gykview(String scMo) {
		ModelAndView mv= new ModelAndView();
		String sql = "select a.sc_mo,to_char(a.rq,'yyyy-MM-dd') rq,a.code,a.scsl, to_char(a.scjq,'yyyy-MM-dd') scjq,b.cpcode_name,b.cpcode_size from m_sc_scrw a,e_js_cpcode b where a.code=b.cpcode_id and a.sc_mo='"+scMo+"'";
		List<Map> table = scTackDao.QueryListMap(sql);
		mv.addObject("scMo", table.get(0).get("sc_mo"));
		mv.addObject("apRq", table.get(0).get("rq"));
		mv.addObject("cj","待定");
		mv.addObject("cpcodeName", table.get(0).get("cpcode_name"));
		mv.addObject("cpcodeSize", table.get(0).get("cpcode_size"));
		mv.addObject("kh", "测试");
		mv.addObject("scSl", table.get(0).get("scsl"));
		mv.addObject("scJq", table.get(0).get("scjq"));

		//sql = "select to_char(ht.ht_jq,'yyyy-MM-dd') htjq,ht.ht_code htcode,f_getparamname('GETBCPCODENAME',cp.cpcode_name,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')||'   '||cp.cpcode_size \"NameAndSize\",f_getparamname('GETBYPARENTID',cp.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"') cpcodebz,round(ht.ht_sl ,2) htsl,round(ht.ht_price ,4) htprice,round(ht.ht_je,2) htje,ht.ht_requirements htrequirements from e_js_cpcode cp,e_xs_contract_detailed ht,e_xs_contract c where c.ht_no=ht.ht_no and c.data_corp=ht.data_corp and  ht.ht_no = '" + htNo + "' and ht.ht_code = cp.cpcode_id";
		sql="select gx_id,gx_name,gx_sl,gx_jsyq from m_sc_scrw_gx where sc_mo='"+scMo+"' ORDER BY gx_ord  ";
		List<Map> tableMx = scTackDao.QueryListMap(sql);
		mv.addObject("mx", tableMx);
		mv.addObject("mxCount", tableMx.size());
		System.out.println("tableMx = " + tableMx);
		return Result.resultOk(mv);
	}


	@Override
	public BasePage<Map<String, Object>> getSrchBottomprintList(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlString=" select 'T' as sign, a.*,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',a.wl_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,b.cpcode_size,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',a.wl_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_fl"
				+ " ,f_getname('GETGXNAME', scrw_gx, '', '') gxname,f_getname('GETGXANDLIBNAME',wl_lib,a.wl_type,'') libname,f_get_param_name('产品大类',wl_type,'"+   SessionUser.getCurrentCorpId()   +"') typename,f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz,b.cpcode_xp,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03,b.cpcode_size_en "
				+ " from m_sc_scrw_wl a,e_js_cpcode b where a.wl_code=b.cpcode_id  and scrw_mo='"+map.get("scrwMo")+"'";
		return mScScrwWlDao.QueryMapPageList(baseDto,sqlString+" order by wl_code desc ",true);
	}

	@Override
	public BasePage<Map<String, Object>> getSrchBottomRightprintList(BaseDto baseDto){
		Map<String, String> map = baseDto.getParamsMap();
		String sqlString="select '外观' xm ,1 ordid,'测试数据' yq\n" +
				"union all\n" +
				"select '尺寸' xm,2 ordid,'测试数据' yq\n" +
				"union all\n" +
				"select '成份' xm,3 ordid,'测试数据' yq\n" +
				"union all\n" +
				"select '屈服' xm,4 ordid,'测试数据' yq\n" +
				"union all\n" +
				"select '盐雾' xm,5 ordid,'测试数据' yq\n" +
				"union all\n" +
				"select '强度' xm,6 ordid,'测试数据' yq ";//产品检验待定
		return mScScrwWlDao.QueryMapPageList(baseDto,sqlString,true);
	}
}
