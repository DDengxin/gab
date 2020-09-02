package com.tengzhi.business.sc.pd.scrw.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.da.sysCustomer.service.SysCustomerService;
import com.tengzhi.business.sc.pd.scrw.service.ScrwpdService;
import com.tengzhi.business.sc.task.sctack.dao.ScTackDao;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sale.processProduct.processContract.dao.ProcessContractDao;
import com.tengzhi.business.sale.processProduct.processContract.dao.ProcessContractDetailedDao;
import com.tengzhi.business.sale.processProduct.processContract.dao.ProcessContractDetailedWlDao;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContract;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContractDetailed;
//import com.tengzhi.business.sale.processProduct.processContract.model.EXsContractDetailedWl;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.service.SysParamService;

import cn.hutool.core.util.ObjectUtil;

@Service
@Transactional

public class ScrwpdServiceImpl implements ScrwpdService {

	@Autowired
	private ProcessContractDao eXsContractDao;

	@Autowired
	private ScTackDao scTackDao;
	
	@Autowired
	private ProcessContractDetailedDao eXsContractDetailedDao;

	@Autowired
	private ProcessContractDetailedWlDao eXsContractDetailedWlDao;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;
	
	@Autowired
	private  SysParamService sysParamService;


	public String getSqlScdd(Map<String, String> map) {
		SessionUser ssionuser=SessionUser.SessionUser();
		String where = " and a.data_corp = '"+ssionuser.getCorpId()+"'";

		if (ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			where += " and c.ht_date >='" + map.get("srchRq1") + "' ";
		}
		if (ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			where += " and c.ht_date <='" + map.get("srchRq2") + "' ";
		}
		if (ObjectUtil.isNotEmpty(map.get("htNo"))) {
			where += " and c.ht_no like '%" + map.get("htNo") + "%'";
		}
		if (ObjectUtil.isNotEmpty(map.get("htCustomer"))) {
			where += " and c.ht_customer = '" + map.get("htCustomer") + "'";
		}
		if (ObjectUtil.isNotEmpty(map.get("htType"))) {
			where += " and c.ht_type = '" + map.get("htType") + "'";
		}
		if (ObjectUtil.isNotEmpty(map.get("htItemType"))) {
			where += " and c.ht_item_type = '" + map.get("htItemType") + "'";
		}
		if (ObjectUtil.isNotEmpty(map.get("htStype"))) {
			where += " and c.ht_stype = '" + map.get("htStype") + "'";
		}
		if (ObjectUtil.isNotEmpty(map.get("htCode"))) {
			where += " and a.ht_code like '%" + map.get("htCode") + "%'";
		}
		if (ObjectUtil.isNotEmpty(map.get("cpcodeFl"))) {
			//  where+=" and b.cpcode_fl = '"+map.get("cpcodeFl")+"'";
			where += " and  position(',' || b.cpcode_fl || ',' IN (',') || ('" + map.get("cpcodeFl") + "' || ','))>0";
		}
		if (ObjectUtil.isNotEmpty(map.get("cpcodeName"))) {
			//where+=" and b.cpcode_name = '"+map.get("cpcodeName")+"'";
			where += " and  position(',' || b.cpcode_name || ',' IN (',') || ('" + map.get("cpcodeName") + "' || ','))>0";
		}
		if (ObjectUtil.isNotEmpty(map.get("cpcodeSize"))) {
			where += " and b.cpcode_size = '" + map.get("cpcodeSize") + "'";
		}
		if (ObjectUtil.isNotEmpty(map.get("srchPdflag"))) {
			if ("未排".equals(map.get("srchPdflag"))) {
				where += " and (COALESCE((select sum(scsl) from  m_sc_scrw where ht_mo=a.ht_mo and data_corp=a.data_corp),0))=0";
			} else {
				where += " and (COALESCE((select sum(scsl) from  m_sc_scrw where ht_mo=a.ht_mo and data_corp=a.data_corp),0))<>0";
			}

		}
		String sql = " select c.ht_no,to_char(c.ht_date,'yyyy-MM-dd') ht_date,f_get_param_name('订单类型',c.ht_type,'"+   SessionUser.getCurrentCorpId()   +"','cn')ht_type,f_get_param_name('订单类型',c.ht_item_type,'"+   SessionUser.getCurrentCorpId()   +"','cn')ht_item_type,f_getname('GETDWEXP',c.ht_customer,'',c.data_corp)ht_customer,f_get_param_name('业务员',d.customer_buyer,'"+   SessionUser.getCurrentCorpId()   +"','')data_man,c.ht_flag,a.ht_mo,a.ht_id,a.ht_code,a.ht_sl,a.ht_price,a.ht_je,to_char(a.ht_jq,'yyyy-MM-dd') ht_jq,a.ht_sm,a.data_rq,a.data_corp,a.ht_requirements,(select string_agg( param_name,',') from sys_param c "
				+ "where strpos(a.ht_standard,c.param_id)>0 and  c.param_mod='技术'  )ht_standard_name,ht_standard  ,"
				+ "f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,"
				+ "b.cpcode_size,b.cpcode_size_en,"
				+ "f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_fl,"
				+ "f_getparamname('GETCPCODEXP',cpcode_xp,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name,"
				+ "f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name, 	"
				+ "f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,"
				+ "b.cpcode_bz,b.cpcode_xp,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03 \r\n" +
				",(a.ht_sl-COALESCE((select sum(scsl) from  m_sc_scrw where ht_mo=a.ht_mo and data_corp=a.data_corp),0) )  pdsl,COALESCE((select sum(scsl) from  m_sc_scrw where ht_mo=a.ht_mo and data_corp=a.data_corp),0) scsl " +
				",case when COALESCE((select sum(scsl) from  m_sc_scrw where ht_mo=a.ht_mo and data_corp=a.data_corp),0)=0 then '未排' else '已排' end pdflag ,COALESCE((select sum(in_sl) from  e_ck_in where a.ht_no=in_contract and data_corp=a.data_corp and ht_code=in_code),0) kcfs " +
				"from e_xs_contract_detailed a,e_js_cpcode b,e_xs_contract c , sys_customer d  where a.ht_no=c.ht_no and a.data_corp=c.data_corp and a.ht_code=b.cpcode_id and  c.ht_customer= d.customer_id and c.ht_flag='核准' and a.ht_flag='登记' " + where;
		return sql;
	}

	@Override
	public BasePage<Map<String, Object>> getScddList(BaseDto baseDto) throws IOException {

		Map<String, String> map = baseDto.getParamsMap();
		String sql = getSqlScdd(map);
		//return eXsContractDetailedDao.QueryPageLists(baseDto,sql);
		return eXsContractDetailedDao.QueryMapPageList(baseDto, sql, true);

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
	public Map<Object, String> getBySplitId(String htMo) {
		String sql="select a.ht_no,a.ht_customer,b.ht_mo htmo,b.ht_code code,(b.ht_sl-COALESCE((select sum(scsl) from  m_sc_scrw where ht_mo=b.ht_mo and data_corp=b.data_corp),0) )  htsl,b.ht_jq khjq,f_getparamname('GETBCPCODENAME',c.cpcode_name,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') sname,c.cpcode_size size,c.cpcode_fl cpcodefl ,(select string_agg( param_name,',') from sys_param d where strpos(b.ht_standard,d.param_id)>0 and  d.param_mod='技术'  ) ht_standard_name,ht_standard  from e_xs_contract a,e_xs_contract_detailed b,e_js_cpcode c where a.ht_no=b.ht_no and a.data_corp=b.data_corp  and b.ht_code=c.cpcode_id and b.ht_mo='"+htMo+"' ";
		List<Map> listMaps=eXsContractDetailedDao.QueryListMap(sql);
		return listMaps.get(0);
	}

	@Override
	public Map<Object, String> getByHpId(String htMo) {
		String sql="select '"+htMo+"' htmo,b.ht_code code,sum(b.ht_sl) htsl,min(b.ht_jq) scjq,f_getparamname('GETBCPCODENAME',c.cpcode_name,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') sname,c.cpcode_size size,c.cpcode_fl cpcodefl,(select string_agg( param_name,',') from sys_param d where strpos(b.ht_standard,d.param_id)>0 and  d.param_mod='技术'  )ht_standard_name,ht_standard   from e_xs_contract a,e_xs_contract_detailed b,e_js_cpcode c where a.ht_no=b.ht_no and a.data_corp=b.data_corp  and b.ht_code=c.cpcode_id and b.ht_mo in ('"+htMo.replace(",", "','")+"') group by b.ht_code,a.ht_stype,c.cpcode_name,c.cpcode_size ";
		List<Map> listMaps=eXsContractDetailedDao.QueryListMap(sql);
		return listMaps.get(0);
	}

	@Override
	public Result xsddSplit(MScScrw model) throws  Exception {
		//拆分数不能超过可拆分数
		String htsl=scTackDao.getSingleResult("select (b.ht_sl-COALESCE((select sum(scsl) from  m_sc_scrw where ht_mo=b.ht_mo and data_corp=b.data_corp),0) ) as ht_sl from e_xs_contract_detailed b where b.ht_mo=? ",model.getHtMo());
		if(Float.parseFloat(htsl)<model.getHtsl()) {
			return  Result.error("生产数量不能超过可拆分数！");
		}
		//end
		String note  = sysGenNoteService.getNote(com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract.class, "RW", "yyMM", 4);
		SessionUser securityUser=SessionUser.SessionUser();
		model.setScMo(note);
		//获取种类对应的分类
		String count=scTackDao.getSingleResult("select  count(*)  from  sys_param where param_mod='生产' and param_type='种类分类' and param_name=:1  and param_value1=:2 ",model.getStandard(),model.getScCj());
		if("0".equals(count)){
			return  Result.error("未获取到产品分类，请检查数据！");
		}
		String stantrd=scTackDao.getSingleResult("select  param_value2  from  sys_param where param_mod='生产' and param_type='种类分类' and param_name=:1  and param_value1=:2 ",model.getStandard(),model.getScCj());

		double size=Double.parseDouble(model.getSize());
		//获取日产能 向上取整
		Map<String,Object> rstmap=scTackDao.QueryToFirstMap("select ceil("+	model.getScsl()+"/cl_rcl) zqctn from m_sc_ggcl where cl_cj='"+model.getScCj()+"' and cl_code='"+stantrd+"' and "+size+" between size_min AND size_max and data_corp='"+securityUser.getCorpId()+"'");
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
		model.setRq(new Date());
		model.setPdrq(model.getScjq());
		//model.setDataMan(securityUser.getUserId());
		model.setDataCorp(securityUser.getCorpId());
		//model.setDataRq(new Date());
		scTackDao.save(model);
		return  Result.resultOkMsg("拆分成功！");
	}
	/**
	 * 利用正则表达式判断字符串是否是数字
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}

	@Override
	public Result xsddHp(MScScrw model) throws  Exception {
		String note  = sysGenNoteService.getNote(com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract.class, "RW", "yyMM", 4);
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
	public Result getFlag(String htNo, String flag) {
		String flagString = eXsContractDao.getFlag(htNo);
		if (flag.equals(flagString)) {
			return Result.resultOk("操作成功！");
		}
		return Result.error("该单不是“" + flag + "”状态,不能操作！");
	}
}
