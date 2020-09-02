package com.tengzhi.business.production.subcontract.wwht.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.production.subcontract.wwht.dao.WwhtDao;
import com.tengzhi.business.production.subcontract.wwht.dao.WwhtDetailedDao;
import com.tengzhi.business.production.subcontract.wwht.service.WwhtService;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContract;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContractDetailed;
import com.tengzhi.business.sale.processProduct.processContract.vo.ProcessContractVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.service.SysParamService;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;

@Service
@Transactional

public class WwhtServiceImpl implements WwhtService {

	@Autowired
	private WwhtDao wwhtDao;
	
	@Autowired
	private WwhtDetailedDao eXsContractDetailedDao;

	@Autowired
	private SysGenNoteService sysGenNoteService;
	
	@Autowired
	private  SysParamService sysParamService;

	@Override
	public BasePage<EXsContract> findAll(BaseDto baseDto) throws IOException {
		String where="";
		Map<String, String> map = baseDto.getParamsMap();
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
            where+=" and a.ht_date >='"+map.get("srchRq1")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
            where+=" and a.ht_date <='"+map.get("srchRq2")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
            where+=" and a.ht_no like '%"+map.get("srchNo")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchCustomer"))) {
            where+=" and a.ht_customer = '"+map.get("srchCustomer")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchHtType"))) {
            where+=" and a.ht_type = '"+map.get("srchHtType")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchHtItemType"))) {
            where+=" and a.ht_item_type = '"+map.get("srchHtItemType")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htCustomerAssociated"))) {
            where+=" and b.ht_customer_associated = '"+map.get("htCustomerAssociated")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htCustomerAssociatedRemarks"))) {
            where+=" and b.ht_customer_associated_remarks = '"+map.get("htCustomerAssociatedRemarks")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
            where+=" and a.ht_stype = '"+map.get("htStype")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("cgStype"))) {
			where+=" and a.ht_stype = '"+map.get("cgStype")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchHtFlag"))) {
			if("审核中".equals(map.get("srchHtFlag"))) {
				where += " and a.ht_flag not in  ('登记','确认','核准','核销')";
			}else {
				where+=" and a.ht_flag = '"+map.get("srchHtFlag")+"'";
			}
			
		}
		
		String sqlString=" select a.ht_no,a.ht_date,f_get_param_name('订单类型',a.ht_type,'"+   SessionUser.getCurrentCorpId()   +"','cn')ht_type,f_get_param_name('订单类型',a.ht_item_type,'"+   SessionUser.getCurrentCorpId()   +"','cn')ht_item_type,f_getname('GETDWEXP',a.ht_customer,'',a.data_corp)ht_customer,f_getname('GETUSERNAME',a.data_man,'',a.data_corp) data_man,sum(b.ht_sl)ht_sl,sum(b.ht_je)ht_je,a.ht_flag,a.ht_stype,f_get_param_name('产品大类',a.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') ht_stype_name from e_xs_contract a ,e_xs_contract_detailed b, sys_customer c where a.ht_type='JG' and a.ht_customer= c.customer_id and a.ht_no=b.ht_no "+ where 
				+ " group by a.ht_no,a.ht_date,a.ht_type,a.ht_item_type,a.ht_customer,c.customer_buyer,a.ht_flag,a.data_corp order by ht_date desc,ht_no desc";
		return wwhtDao.QueryPageLists(baseDto,sqlString);
	}
	
	@Override
	public BasePage<EXsContractDetailed> getScddList(BaseDto baseDto) throws IOException {
		String where="";
		Map<String, String> map = baseDto.getParamsMap();
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
            where+=" and c.ht_date >='"+map.get("srchRq1")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
            where+=" and c.ht_date <='"+map.get("srchRq2")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
            where+=" and c.ht_no like '%"+map.get("srchNo")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchCustromer"))) {
            where+=" and c.ht_customer = '"+map.get("srchCustromer")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchHtType"))) {
            where+=" and c.ht_type = '"+map.get("srchHtType")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchHtItemType"))) {
            where+=" and c.ht_item_type = '"+map.get("srchHtItemType")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
            where+=" and c.ht_stype = '"+map.get("htStype")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
            where+=" and a.ht_code like '%"+map.get("srchCode")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchFl"))) {
            where+=" and b.cpcode_fl = '"+map.get("srchFl")+"'";
        }
		
		String sqlString=" select c.ht_no,c.ht_date,f_get_param_name('订单类型',c.ht_type,'"+   SessionUser.getCurrentCorpId()   +"','cn')ht_type,f_get_param_name('订单类型',c.ht_item_type,'"+   SessionUser.getCurrentCorpId()   +"','cn')ht_item_type,f_getname('GETDWEXP',c.ht_customer,'',c.data_corp)ht_customer,f_get_param_name('业务员',d.customer_buyer,'"+   SessionUser.getCurrentCorpId()   +"','')data_man,c.ht_flag,a.ht_mo,a.ht_id,a.ht_code,a.ht_sl,a.ht_price,a.ht_je,a.ht_jq,a.ht_sm,a.data_rq,a.data_corp,a.ht_requirements,(select string_agg( param_name,',') from sys_param c where strpos(a.ht_standard,c.param_id)>0 and  c.param_mod='技术'  )ht_standard_name,ht_standard  ,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,f_get_param_name('产品大类',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') htstypename,b.cpcode_size,b.cpcode_size_en,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_fl,b.cpcode_bz,b.cpcode_xp,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03 \r\n" + 
				",(a.ht_sl-COALESCE((select sum(scsl) from  m_sc_scrw where ht_mo=a.ht_mo and data_corp=a.data_corp),0) )  kcfs,COALESCE((select sum(scsl) from  m_sc_scrw where ht_mo=a.ht_mo and data_corp=a.data_corp),0) scsl "+
				"from e_xs_contract_detailed a,e_js_cpcode b,e_xs_contract c , sys_customer d  where a.ht_no=c.ht_no and a.data_corp=c.data_corp and a.ht_code=b.cpcode_id and  c.ht_customer= d.customer_id " +where;
		System.err.println(sqlString);
		return eXsContractDetailedDao.QueryPageLists(baseDto,sqlString);
	}
	
	@Override
	public EXsContract findByNote(String htNote) {
		return wwhtDao.QueryListModel(EXsContract.class, "select a.*,f_getname('GETDWEXP',a.ht_customer,'',a.data_corp)ht_customer_name from e_xs_contract a where ht_no= :1 ", htNote).get(0);
	}
	
	@Override
	public BasePage<EXsContractDetailed> getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String where ="and a.ht_no= '"+map.get("htNo")+"'";
		if(ObjectUtil.isNotEmpty(map.get("htFlag"))) {
            where+=" and a.ht_flag = '"+map.get("htFlag")+"'";
        }
		String sqlString=" select a.ht_no,a.ht_mo,a.ht_id,a.ht_code,a.ht_sl,a.ht_price,a.ht_je,a.ht_jq,a.ht_sm,a.ht_flag,a.data_man,a.data_rq,a.data_corp,a.ht_requirements,(select string_agg( param_name,',') from sys_param c where strpos(a.ht_standard,c.param_id)>0 and  c.param_mod='技术'  )ht_standard_name,ht_standard ,ht_customer_associated_remarks,ht_customer_associated " + 
				" ,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,  b.cpcode_size ,b.cpcode_size_en,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_fl,b.cpcode_bz,b.cpcode_xp,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03 from e_xs_contract_detailed a,e_js_cpcode b,e_xs_contract c "
				+ "  where a.ht_no=c.ht_no and a.data_corp=c.data_corp and a.ht_code=b.cpcode_id "+where ;
		return eXsContractDetailedDao.QueryPageLists(baseDto,sqlString);
	}
	
	@Override
	public EXsContract save(ProcessContractVo eXsContractVo) throws Exception {
		String prefix=sysParamService.getParamValue1("订单类型", eXsContractVo.geteXsContract().getHtItemType());
		String note  = sysGenNoteService.getNote(EXsContract.class, prefix, "yyMM", 4);//sysGenNoteService.getyyMMNote4(EXsContract.class,prefix );
		SessionUser securityUser=SessionUser.SessionUser();
		eXsContractVo.geteXsContract().setHtNo(note);
		eXsContractVo.geteXsContract().setDataMan(securityUser.getUsername());
		eXsContractVo.geteXsContract().setDataRq(new Date());
		eXsContractVo.geteXsContract().setDataCorp(securityUser.getCorpId());
		eXsContractVo.geteXsContract().setHtFlag("登记");
		if(!eXsContractVo.getAddedList().isEmpty()) {
			eXsContractVo.getAddedList().forEach( item -> {
				item.setHtNo(note);
				item.setDataMan(securityUser.getUserId());
				item.setDataRq(new Date());
				item.setDataCorp(securityUser.getCorpId());
				item.setHtMo(note+"-"+item.getHtId());
				item.setHtFlag("登记");
				eXsContractDetailedDao.save(item);
			});
		}
		if (!eXsContractVo.getDeletedList().isEmpty()) {
			eXsContractDetailedDao.deleteAll(eXsContractVo.getDeletedList());
		}
		if(!eXsContractVo.getModifyedList().isEmpty()){
			eXsContractVo.getModifyedList().forEach( item ->{
				eXsContractDetailedDao.dynamicSave(item,eXsContractDetailedDao.findById(item.getHtMo()).orElse(null));
			});
		}
		EXsContract   eXsContract=wwhtDao.save(eXsContractVo.geteXsContract());
		return  eXsContract;
	}

	

	@Override
	public void update(ProcessContractVo eXsContractVo) {
		SessionUser securityUser=SessionUser.SessionUser();
		eXsContractVo.geteXsContract().setDataMan(securityUser.getUsername());
		eXsContractVo.geteXsContract().setDataRq(new Date());
		eXsContractVo.geteXsContract().setDataCorp(securityUser.getCorpId());
		if (!eXsContractVo.getDeletedList().isEmpty()) {
			eXsContractDetailedDao.deleteAll(eXsContractVo.getDeletedList());
		}
		if (!eXsContractVo.getAddedList().isEmpty()){
			eXsContractVo.getAddedList().forEach( item -> {
				item.setHtFlag("登记");
				item.setDataMan(securityUser.getUserId());
				item.setDataRq(new Date());
				item.setDataCorp(securityUser.getCorpId());
				item.setHtMo(eXsContractVo.geteXsContract().getHtNo()+"-"+item.getHtId());
				eXsContractDetailedDao.save(item);
			});
		}
		if(!eXsContractVo.getModifyedList().isEmpty()){
			eXsContractVo.getModifyedList().forEach( item ->{
				eXsContractDetailedDao.dynamicSave(item,eXsContractDetailedDao.findById(item.getHtMo()).orElse(null));
			});
		}
		wwhtDao.dynamicSave(eXsContractVo.geteXsContract(),wwhtDao.findById(eXsContractVo.geteXsContract().getHtNo()).orElse(null));
	}

	@Override
	public void deleteByNote(String htNo) {
		wwhtDao.deleteByHtNo(htNo);
		eXsContractDetailedDao.deleteByHtNo(htNo);
		
	}

	@Override
	public Result getFlag(String htNo, String flag) {
		String flagString=wwhtDao.getFlag(htNo);
		if(flag.equals(flagString)) {
			return  Result.resultOk("操作成功！");
		}
		return  Result.error("该单不是“"+flag+"”状态,不能操作！");
	}
/**
 * 
 * @param htNo
 * @param flag
 * @return 多选时判断状态
 */
	@Override
	public Result getFlags(String htNo, String flag) {
		int cn=Integer.parseInt(wwhtDao.getSingleResult("select  count(ht_flag) cn  from e_xs_contract where ht_no in("+htNo+")"));
		if(cn==1) {
			String flagString=wwhtDao.getSingleResult("select  distinct ht_flag  from e_xs_contract where ht_no in("+htNo+")");
			if(flag.equals(flagString)) {
				return  Result.resultOk("操作成功！");
			}
		}
		return  Result.error("存在订单不是“"+flag+"”状态,不能操作！");
	}
	
	@Override
	public Result confirm(String htNote) {
		wwhtDao.updateFlag(htNote, "确认");
		return Result.resultOkMsg("确认");
	}

	@Override
	public Result cancel(String htNote) {
		wwhtDao.updateFlag(htNote, "登记");
		return Result.resultOkMsg("取消");
	}

	@Override
	public BasePage<EXsContractDetailed> getContractDetailed(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String where =" ";
		if(ObjectUtil.isNotEmpty(map.get("htNo"))) {
            where+=" and a.ht_no like '%"+map.get("htNo")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchCustomer"))) {
            where+=" and c.ht_customer = '"+map.get("srchCustomer")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
            where+=" and a.ht_code = '"+map.get("srchCode")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
            where+=" and c.ht_stype = '"+map.get("htStype")+"'";
        }
		String sqlString=" select c.ht_tax,c.ht_currency,a.ht_no,a.ht_mo,a.ht_id,a.ht_code,a.ht_sl,a.ht_price,a.ht_je,a.ht_jq,a.ht_sm,a.ht_flag,a.data_man,a.data_rq,a.data_corp,a.ht_requirements,c.ht_stype,(select string_agg( param_name,',') from sys_param c where strpos(a.ht_standard,c.param_id)>0 and  c.param_mod='技术'  )ht_standard_name,ht_standard,ht_customer_associated_remarks,ht_customer_associated " + 
				" ,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,  b.cpcode_size ,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_fl,b.cpcode_bz,b.cpcode_xp,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03 from e_xs_contract_detailed a,e_js_cpcode b ,e_xs_contract c "
				+ "  where a.ht_no=c.ht_no and  a.ht_code=b.cpcode_id " +where;
		return eXsContractDetailedDao.QueryPageLists(baseDto,sqlString);
	}
	
	@Override
	public ModelAndView table(ModelAndView mv,String htNo,String ywType) {
		String sql = "select ht_no htno,to_char(ht_date,'yyyy-MM-dd') htdate,f_getname('GETCORPNAME', '', '', cg.data_corp) corpname,f_get_param_name('订单类型',ht_type,'"+   SessionUser.getCurrentCorpId()   +"','',false) httype,ht_customer htsupplier,f_getname('GETCUSTOMERNAME', ht_customer, '', cg.data_corp) htsuppliername,cg.data_corp datacorp,ht_validity htquality,customer_address customeraddress,ht_transport_costs httransport,f_get_param_name('交易币种',ht_currency,'"+   SessionUser.getCurrentCorpId()   +"','财务',false) htbz,ht_settlement htsettlement,ht_customer htsupplement,ht_supplement htsupplementname,ht_address htaddress,f_get_param_name('运输方式',ht_transport_costs,'"+   SessionUser.getCurrentCorpId()   +"','销售',false) httransportcosts,ht_transport_mode httransportmode,ht_requirements htrequirements from e_xs_contract cg,sys_customer cu where ht_no = '"+htNo+"' and customer_id = ht_customer";
		List<Map> table = eXsContractDetailedDao.QueryListMap(sql);
		mv.addObject("htno", table.get(0).get("htno"));
		mv.addObject("htdate", table.get(0).get("htdate"));
		mv.addObject("corpname", table.get(0).get("corpname"));
		mv.addObject("httype", table.get(0).get("httype"));
		mv.addObject("htsuppliername", table.get(0).get("htsuppliername"));
		mv.addObject("htquality", table.get(0).get("htquality"));
		mv.addObject("httransport", table.get(0).get("httransport"));
		mv.addObject("htsettlement", table.get(0).get("htsettlement"));
		mv.addObject("htsupplement", table.get(0).get("htsupplement"));
		mv.addObject("customeraddress", table.get(0).get("customeraddress"));
		mv.addObject("htrequirements", table.get(0).get("htrequirements"));
		mv.addObject("htsupplementname", table.get(0).get("htsupplementname"));
		mv.addObject("htaddress", table.get(0).get("htaddress"));
		mv.addObject("httransportcosts", table.get(0).get("httransportcosts"));
		mv.addObject("httransportmode", table.get(0).get("httransportmode"));
		mv.addObject("htbz", table.get(0).get("htbz"));
		sql = "select to_char(ht.ht_jq,'yyyy-MM-dd') htjq,ht.ht_code htcode,f_getparamname('GETBCPCODENAME',cp.cpcode_name,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')||'   '||cp.cpcode_size \"NameAndSize\",f_getparamname('GETBYPARENTID',cp.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"') cpcodebz,round(ht.ht_sl ,2) htsl,round(ht.ht_price ,4) htprice,round(ht.ht_je,2) htje,ht.ht_requirements htrequirements from e_js_cpcode cp,e_xs_contract_detailed ht,e_xs_contract c where c.ht_no=ht.ht_no and c.data_corp=ht.data_corp and  ht.ht_no = '"+htNo+"' and ht.ht_code = cp.cpcode_id";
		List<Map> tableMx = eXsContractDetailedDao.QueryListMap(sql);
		mv.addObject("mx", tableMx);
		double sumje = 0;
		double sumsl = 0;
		for(int i = 0 ; i < tableMx.size() ; i++) {
			sumsl += Double.parseDouble(tableMx.get(i).get("htsl").toString());
			sumje += Double.parseDouble(tableMx.get(i).get("htje").toString());
		}
		DecimalFormat df = new DecimalFormat("0.00");
		mv.addObject("sumje", df.format(sumje));
		mv.addObject("sumsl", df.format(sumsl));
		mv.addObject("zwJe", Convert.digitToChinese(sumje));
		mv.addObject("mxCount", tableMx.size());
		
		sql = "select customer_bank customertax from sys_customer where customer_id = '"+table.get(0).get("htsupplier")+"'";
		List<Map> gF = eXsContractDetailedDao.QueryListMap(sql);
		mv.addObject("gF", gF.get(0).get("customertax"));
		sql = "select corp_tax corptax from sys_org where org_id = '"+table.get(0).get("datacorp")+"'";
		List<Map> xF = eXsContractDetailedDao.QueryListMap(sql);
		mv.addObject("xF", xF.get(0).get("corptax"));
		return mv;
	}

	@Override
	public Map<Object, String> getBySplitId(String htMo) {
		String sql="select a.ht_no htno,b.ht_mo htmo,b.ht_code code,(b.ht_sl-COALESCE((select sum(scsl) from  m_sc_scrw where ht_mo=b.ht_mo and data_corp=b.data_corp),0) )  htsl,b.ht_jq scjq,f_getparamname('GETBCPCODENAME',c.cpcode_name,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') sname,c.cpcode_size size from e_xs_contract a,e_xs_contract_detailed b,e_js_cpcode c where a.ht_no=b.ht_no and a.data_corp=b.data_corp  and b.ht_code=c.cpcode_id and b.ht_mo='"+htMo+"' ";
		List<Map> listMaps=eXsContractDetailedDao.QueryListMap(sql);
		return listMaps.get(0);
	}

	@Override
	public Map<Object, String> getByHpId(String htMo) {
		String sql="select '"+htMo+"' htmo,b.ht_code code,sum(b.ht_sl) htsl,min(b.ht_jq) scjq,f_getparamname('GETBCPCODENAME',c.cpcode_name,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') sname,c.cpcode_size size from e_xs_contract a,e_xs_contract_detailed b,e_js_cpcode c where a.ht_no=b.ht_no and a.data_corp=b.data_corp  and b.ht_code=c.cpcode_id and b.ht_mo in ('"+htMo.replace(",", "','")+"') group by b.ht_code,a.ht_stype,c.cpcode_name,c.cpcode_size ";
		List<Map> listMaps=eXsContractDetailedDao.QueryListMap(sql);
		return listMaps.get(0);
	}

	
	@Override
	public Result hx(String htNo) {
		wwhtDao.updateFlag(htNo, "核销");
		return Result.resultOkMsg("核销");
	}
	@Override
	public Result qxhx(String htNo) {
		wwhtDao.updateFlag(htNo, "核准");
		return Result.resultOkMsg("取消核销");
	}
	
	
	@Override
	public BasePage<EXsContractDetailed> getUsableContractDetailed(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String where =" ";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
            where+=" and c.ht_date >='"+map.get("srchRq1")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
            where+=" and c.ht_date <='"+map.get("srchRq2")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("htNo"))) {
            where+=" and a.ht_no like '%"+map.get("htNo")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchCustomer"))) {
            where+=" and c.ht_customer = '"+map.get("srchCustomer")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
			where+=" and a.ht_code in ('"+map.get("srchCode").replace(",", "','")+"')";
		}
		if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
            where+=" and c.ht_stype = '"+map.get("htStype")+"'";
        }
		String sqlString=" select a.ht_no||'_'||a.ht_code id,f_getname('GETDWEXP',c.ht_customer,'',c.data_corp) ht_customer,c.ht_date,c.ht_tax,c.ht_currency,a.ht_no,a.ht_mo,a.ht_id,a.ht_code,a.ht_sl,a.ht_price,a.ht_je,a.ht_jq,a.ht_sm,a.ht_flag,a.data_man,a.data_rq,a.data_corp,a.ht_requirements,c.ht_stype,(select string_agg( param_name,',') from sys_param c where strpos(a.ht_standard,c.param_id)>0 and  c.param_mod='技术'  )ht_standard_name,ht_standard,ht_customer_associated_remarks,ht_customer_associated " + 
				" ,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,  b.cpcode_size ,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_fl,b.cpcode_xp,f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_bz,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03,(select COALESCE(sum(sl),0) from v_ck_mx where in_contract=a.ht_no and code=a.ht_code  )kcfs from e_xs_contract_detailed a,e_js_cpcode b ,e_xs_contract c "
				+ "  where a.ht_no=c.ht_no and  a.ht_code=b.cpcode_id and a.ht_flag<>'核销'  "+where+" order by c.ht_date,a.ht_no,a.ht_mo ";
		return eXsContractDetailedDao.QueryPageLists(baseDto,sqlString);
	}




	
	
	
	
	
	

	
	
	
}
