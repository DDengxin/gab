package com.tengzhi.business.cg.yw.purchaseContract.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.excel.ExcelUtil;
import com.tengzhi.business.base.common.WarehouseActionCaliber;
import com.tengzhi.business.cg.yw.purchaseContract.dao.ECgContractDao;
import com.tengzhi.business.cg.yw.purchaseContract.dao.ECgContractDetailedDao;
import com.tengzhi.business.cg.yw.purchaseContract.dao.ECgContractSqlDao;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContractDetailed;
import com.tengzhi.business.cg.yw.purchaseContract.service.PurchaseContractService;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ECgContractVo;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ExamineVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.service.SysParamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;


@Service
@Transactional
public class PurchaseContractServiceImpl  implements PurchaseContractService {
	
	
	@Autowired
	private ECgContractSqlDao eCgContractSqlDao;

	
	@Autowired
	private ECgContractDao eCgContractDao;
	
	@Autowired
	private ECgContractDetailedDao eCgContractDetailedDao;

	@Autowired
	private SysGenNoteService sysGenNoteService;

	
	@Autowired
	private  SysParamService sysParamService;

	private final String processDefinitionId = "Process_1:7:26bb6b74-894f-11ea-86bb-04d4c4e724f5" ;

	@Override
	public BasePage<ECgContract> getSrchTopList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		String where=" data_corp='"+securityUser.getCorpId()+"' ";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			where+=" and ht_date >='"+map.get("srchRq1")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			where+=" and ht_date <='"+map.get("srchRq2")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
			where+=" and ht_no like '%"+map.get("srchNo")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchSupplier"))) {
			where+=" and ht_supplier like '%"+map.get("srchSupplier")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchhtType"))) {
			where+=" and ht_type like '%"+map.get("srchhtType")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchflag"))) {
			where+=" and ht_flag like '%"+map.get("srchflag")+"%'";
		}
		
		if(ObjectUtil.isNotEmpty(map.get("cgStype"))) {
			where+=" and cg_stype = '"+map.get("cgStype")+"'";
		}
		String sqlString="select ht_no,ht_flag,data_man,data_rq,data_corp,ht_date,ht_type,ht_supplier,ht_invoice,ht_tax,ht_urgent,ht_quality,ht_transport,ht_settlement,ht_supplement,f_get_param_name('交易币种',ht_bz,'"+   SessionUser.getCurrentCorpId()   +"') ht_bz,cg_stype " 
				+",f_get_param_name('采购合同',ht_type,'"+   SessionUser.getCurrentCorpId()   +"') httypename,f_get_param_name('发票类型',ht_invoice,'"+   SessionUser.getCurrentCorpId()   +"') htinvoicename,f_get_param_name('紧急程度',ht_urgent,'"+   SessionUser.getCurrentCorpId()   +"') hturgentname\r\n" 
				+",f_getname('GETDWEXP',ht_supplier,'',data_corp) htsuppliername,f_getname('GETUSERNAME',data_man,'',data_corp) datamanname "
			    +" from e_cg_contract where "+where;
		return eCgContractDao.QueryPageLists(baseDto,sqlString+" order by ht_date desc,ht_no desc ");
	}

	@Override
	public BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlString=" select a.ht_mo,a.ht_no,a.ht_id,a.ht_code,a.ht_js,a.ht_sl,a.ht_price,a.ht_je,a.ht_hs,to_char(a.ht_jq,'yyyy-MM-dd') ht_jq,a.ht_sm,a.ht_dgnote, "
				+ "a.ht_sqnote,a.ht_cgnote,a.ht_flag,a.ht_zl,a.data_man,a.data_corp" +
				",f_getsl('GETKDGSL',a.ht_sqnote,a.ht_code,a.ht_no,a.data_corp)dgsl "+
				" ,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',c.cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name_name,cpcode_name,"
				+ "b.cpcode_size,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',c.cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_fl_name,cpcode_fl,"
				+ "f_getparamname('GETCPCODESIZE',b.cpcode_size,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name,"
				+ "f_getparamname('GETCPCODEXP',cpcode_xp,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name,"
				+ "f_getparamname('GETCPCODESIZEEN',b.cpcode_size_en,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name, "
				+ "f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz_name,cpcode_bz, "
				+ "b.cpcode_xp,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03,b.cpcode_size_en " 
				+ "from e_cg_contract_detailed a,e_js_cpcode b,e_cg_contract c  where a.ht_no=c.ht_no and a.data_corp=c.data_corp  and  a.ht_code=b.cpcode_id and a.ht_no= '"+map.get("htNo")+"'";
		//return eCgContractDetailedDao.QueryPageLists(baseDto,sqlString);
		return  eCgContractDetailedDao.QueryToMapPage(baseDto, sqlString);
	}

	@Override
	public ECgContract save(ECgContractVo eCgContractVo)throws Exception {
		String prefix=sysParamService.getParamValue1("采购合同", eCgContractVo.geteCgContract().getHtType());
		String note=sysGenNoteService.getyyMMNote4(ECgContract.class,eCgContractVo.geteCgContract().getCgStype());
		SessionUser securityUser=SessionUser.SessionUser();
		eCgContractVo.geteCgContract().setHtNo(note);
		eCgContractVo.geteCgContract().setHtFlag("登记");
		eCgContractVo.geteCgContract().setDataMan(securityUser.getUserId());
		eCgContractVo.geteCgContract().setDataRq(new Date());
		eCgContractVo.geteCgContract().setDataCorp(securityUser.getCorpId());
		Integer sid = Integer.parseInt(eCgContractDetailedDao.getSingleResult("select coalesce(max(ht_id)+1,1) from e_xs_contract_detailed where ht_no=:1 ",eCgContractVo.geteCgContract().getHtNo()));
		if (!eCgContractVo.getAddedList().isEmpty()){
			for (ECgContractDetailed item:eCgContractVo.getAddedList()){
				item.setHtNo(note);
				item.setHtFlag("登记");
				item.setDataMan(securityUser.getUserId());
				item.setDataRq(new Date());
				item.setDataCorp(securityUser.getCorpId());
				item.setHtId(sid);
				sid++;
				item.setHtMo(note + "-" + item.getHtId());
				eCgContractDetailedDao.save(item);
				//核销
				if(item.getHtSqnote()!=null &&item.getHtSl().compareTo(new BigDecimal(item.getDgsl()))>=0) {
					eCgContractDetailedDao.requisitionhx("核销",item.getHtSqnote(),item.getHtCode());
				}
			};
		}
		if (!eCgContractVo.getDeletedList().isEmpty()) {
			eCgContractDetailedDao.deleteAll(eCgContractVo.getDeletedList());
		}
		if(!eCgContractVo.getModifyedList().isEmpty()){
			eCgContractVo.getModifyedList().forEach( item ->{
				eCgContractDetailedDao.dynamicSave(item,eCgContractDetailedDao.findById(item.getHtMo()).orElse(null));
			});
		}
		ECgContract   eCgContract=eCgContractDao.save(eCgContractVo.geteCgContract());

		return  eCgContract;
	}

	@Override
	public void update(ECgContractVo eCgContractVo)throws Exception {

		SessionUser securityUser=SessionUser.SessionUser();
		eCgContractVo.geteCgContract().setDataMan(securityUser.getUserId());
		eCgContractVo.geteCgContract().setDataRq(new Date());
		eCgContractVo.geteCgContract().setDataCorp(securityUser.getCorpId());
		Integer sid = Integer.parseInt(eCgContractDetailedDao.getSingleResult("select coalesce(max(ht_id)+1,1) from e_xs_contract_detailed where ht_no=:1 ",eCgContractVo.geteCgContract().getHtNo()));
		if (!eCgContractVo.getAddedList().isEmpty()){
			for(ECgContractDetailed item:eCgContractVo.getAddedList()) {
				item.setHtNo(eCgContractVo.geteCgContract().getHtNo());
				item.setHtFlag("登记");
				item.setDataMan(securityUser.getUserId());
				item.setDataRq(new Date());
				item.setDataCorp(securityUser.getCorpId());
				item.setHtId(sid);
				sid++;
				item.setHtMo(eCgContractVo.geteCgContract().getHtNo() + "-" + item.getHtId());
				eCgContractDetailedDao.save(item);
				//核销
				if(item.getHtSqnote()!=null &&item.getHtSl().compareTo(new BigDecimal(item.getDgsl()))>=0) {
					eCgContractDetailedDao.requisitionhx("核销",item.getHtSqnote(),item.getHtCode());
				}
				eCgContractDetailedDao.save(item);
			};
		}
		if (!eCgContractVo.getDeletedList().isEmpty()) {
			eCgContractDetailedDao.deleteAll(eCgContractVo.getDeletedList());
		}
		if(!eCgContractVo.getModifyedList().isEmpty()){
			eCgContractVo.getModifyedList().forEach( item ->{
				eCgContractDetailedDao.dynamicSave(item,eCgContractDetailedDao.findById(item.getHtMo()).orElse(null));
			});
		}
		eCgContractDao.dynamicSave(eCgContractVo.geteCgContract(),eCgContractDao.findById(eCgContractVo.geteCgContract().getHtNo()).orElse(null));
	}

	@Override
	public ECgContract findById(String htNo) {
		return eCgContractDao.findById(htNo).orElse(null);

	}



	@Override
	public void deleteById(String htNO){
		eCgContractDao.deleteByHtNo(htNO);
		eCgContractDetailedDao.deleteByHtNo(htNO);
	}


	@Override
	public List<SelectVo> getUrgent(String trueText, String falseText) {
		return comboParam("采购","JJCD");
	}

	@Override
	public List<SelectVo> getHtType(String trueText, String falseText) {
		return comboParam("采购","CGHT");
	}
	@Override
	public List<SelectVo> getHtBz(String trueText, String falseText) {
		return comboParam("财务","JYBZ");
	}

	@Override
	public List<SelectVo> getHtInvoice(String trueText, String falseText) {
		return comboParam("财务","FPLX");
	}

	@Override
	public List<SelectVo> getHtTax(String trueText, String falseText) {
		List<SelectVo> voList=new ArrayList<SelectVo>();
		Object[] val = {"财务","FPSL"};
      	String sql="select param_id,param_value from sys_param  where param_status='true' and param_mod=?  and parent_id=? order by  param_ord ";
      	List<Map> mapList=eCgContractDao.QueryListMap(sql,val);
      	mapList.forEach(Map -> {
      		voList.add(new SelectVo(Map.get("param_value"),String.valueOf(Map.get("param_id"))));
      	});
      	return voList;
		//return comboParam("财务","FPSL");
	}


//	@Override
//	public Result submit(String id) {
//		// 业务表修改
//		ECgContract bean = eCgContractDao.findById(id).orElse(null);
//		if (!"登记".equals(bean.getHtFlag())) {
//			return Result.error("数据已提交，请勿重复提交");
//		}
//		SessionUser securityUser=SessionUser.SessionUser();
//		// 发起流程设置人
//		Map<String, Object> variables = new HashMap<String, Object>();
//		variables.put("userID", securityUser.getJobNumber());
//		SysUser user = userdao.findByJobNumber(securityUser.getJobNumber());
//		List<SysUser> list = userdao.findAll(Specifications.where((c) -> {
//			c.eq("deptName", user.getDeptName());
//			c.eq("positionName", "主管");
//		}));
//		List<SysUser> managerIds = userdao.findAll(Specifications.where((c) -> {
//			c.eq("deptName", user.getDeptName());
//			c.eq("positionName", "经理");
//		}));
//		if (list.size() > 0) {
//			variables.put("deptID", list.stream().map(SysUser::getJobNumber).collect(Collectors.joining(",")));
//		}
//		if (managerIds.size() > 0) {
//			variables.put("managerID", managerIds.stream().map(SysUser::getJobNumber).collect(Collectors.joining(",")));
//		}
//		// 额外参数扩展activiti待办表 模板，标题
//		Backlog back = new Backlog(bean.getHtType(), "采购订购", "/cg/yw/purchaseFile/purchaseContract/examine.html", securityUser);
//		try {
//			//String taskName = work.startById(processDefinitionId, bean.getHtNo(), variables, back);
//			//bean.setHtFlag(taskName);
//			eCgContractDao.update(bean);
//		} catch (Exception e) {
//			return Result.error();
//		}
//		return Result.resultOk();
//	}

//	@Override
//	public Result agree(ExamineVo examineVo) {
//		try {
//			Map<String, Object> variables=new HashMap<String, Object>();
//			variables.put("amount", getSum(examineVo));
//			String taskName = work.agree(examineVo.getTaskid(),examineVo.getOpinion(),  examineVo.getBusinessId(),examineVo.getInstanceId(),variables);
//			ECgContract bean = eCgContractDao.findById(examineVo.getBusinessId()).orElse(null);
//			bean.setHtFlag(taskName);
//			eCgContractDao.update(bean);
//		} catch (Exception e) {
//			return Result.error("提交失败");
//		}
//		return Result.resultOk("提交成功");
//	}
	
	public Double getSum(ExamineVo examineVo) {
		Double sum=0.0;
		List<ECgContractDetailed> list=eCgContractDetailedDao.findAll(Specifications.where((c) -> {
			c.eq("htNo",examineVo.getBusinessId());
		}));
		for (int i = 0; i < list.size(); i++) {
			sum+=list.get(i).getHtJe().doubleValue();
		}
		return sum;
	}

//	@Override
//	public Result disagree(ExamineVo examineVo) {
//		try {
//			String taskName=work.disagree(examineVo.getTaskid(), examineVo.getOpinion(), examineVo.getBusinessId(), examineVo.getInstanceId());
//			ECgContract bean = eCgContractDao.findById(examineVo.getBusinessId()).orElse(null);
//			bean.setHtFlag(taskName);
//			eCgContractDao.update(bean);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return Result.resultOk("提交成功");
//	}
//
//	@Override
//	public void saveDataAndcommit(ECgContractVo initECgContractVo) throws Exception {
//		update(initECgContractVo);
//		String taskName = work.agree(initECgContractVo.getTaskid(), initECgContractVo.getBusinessId(), initECgContractVo.getInstanceId());
//		ECgContract bean = eCgContractDao.findById(initECgContractVo.getBusinessId()).orElse(null);
//		bean.setHtFlag(taskName);
//		eCgContractDao.update(bean);
//	}


	@Override
	public BasePage<Map<String, Object>> getCgddSelectList(BaseDto baseDto) throws IOException {
		Map<String, Object> rmap = new HashMap<String, Object>();
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser = SessionUser.SessionUser();
		Object srchRq1=map.get("srchRq1");
		Object srchRq2=map.get("srchRq2");
		Object srchNo=map.get("srchNo");
		Object srchSupplier=map.get("srchSupplier");
		Object srchBz=map.get("srchBz");
		Object srchShl=map.get("srchShl");
		Object cgStype=map.get("cgStype");
 		String sqlWhere="";
 		String cgsh="";
 		String cgth="";
 		if(srchRq1 != null && srchRq1.toString().trim().length() > 0) {
 			sqlWhere +=" and c.ht_date >= '"+srchRq1+"' ";
 		}
 		if(srchRq2 != null && srchRq2.toString().trim().length() > 0) {
 			sqlWhere +=" and c.ht_date <= '"+srchRq2+"' ";
 		}
 		if(srchNo != null && srchNo.toString().trim().length() > 0) {
 			sqlWhere +=" and c.ht_no like  '%"+srchNo+"%' ";
 		}
 		if(srchSupplier != null && srchSupplier.toString().trim().length() > 0) {
 	   sqlWhere +=" and c.ht_supplier ='"+srchSupplier+"' ";
 		}
 		if(srchBz != null && srchBz.toString().trim().length() > 0) {
 			sqlWhere +=" and c.ht_bz ='"+srchBz+"' ";
 		}
 		if(srchShl != null && srchShl.toString().trim().length() > 0) {
 			sqlWhere +=" and c.ht_tax ='"+srchShl+"' ";
 		}
 		
 		if(cgStype != null && cgStype.toString().trim().length() > 0) {
 			cgsh=eCgContractDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
					, WarehouseActionCaliber.cgrk,cgStype.toString(),securityUser.getCorpId());
			cgth=eCgContractDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
					, WarehouseActionCaliber.cgth,cgStype.toString(),securityUser.getCorpId());
 			sqlWhere +=" and b.cpcode_type ='"+cgStype+"' ";
 		}
 		baseDto.setSortField("id");
		baseDto.setSortOrder("DESC");

		String parameterId=eCgContractDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
				, WarehouseActionCaliber.cgrk,cgStype.toString(),securityUser.getCorpId());
		SysParams sysParams=sysParamService.findByParameterId(parameterId, "仓库收发");
		String value3 = "1";    //参数设置 最大收货数量系数
		if(sysParams != null && isNumber(sysParams.getParamValue1())) value3 = sysParams.getParamValue1();
		String sql="select t.*,(t.ht_sl-t.shsl+t.thsl-t.shsl2) wssl,(t.maxkssl-t.shsl+t.thsl-t.shsl2)kssl from ( " +
				"select c.ht_no||'_'||ht_code id,c.ht_no ,to_char(c.ht_date, 'yyyy-mm-dd') ht_date,c.ht_supplier ,c.data_corp ,d.ht_id ,ht_code ,ht_js ,ht_sl ,   round(ht_price ,4) ht_price,ht_je ,ht_hs,to_char(d.ht_jq, 'yyyy-mm-dd') ht_jq,ht_sm ,ht_dgnote ,ht_sqnote ,ht_cgnote ,c.ht_bz ,c.ht_tax " +
				",f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',c.cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name_name,cpcode_name,"
				+ "f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"') cpcode_bz_name,cpcode_bz,"
				+ "b.cpcode_check ,b.cpcode01 ,b.cpcode02,b.cpcode03,"
				+ " f_getparamname('GETCPCODESIZE',b.cpcode_size,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name,b.cpcode_size , "
				+ "f_getparamname('GETCPCODESIZEEN',b.cpcode_size_en,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,b.cpcode_size_en  ,"
				+ "f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl_name,b.cpcode_fl ,"
				+ "f_getparamname('GETCPCODEXP',b.cpcode_xp,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name, b.cpcode_xp "+
				" , round(ht_sl*"+value3+",3) maxkssl "+
				",(select COALESCE(sum(in_sl),0) insl from e_ck_in i where i.in_contract=c.ht_no and i.in_kfcode=d.ht_code and  i.in_act in('"+cgsh+"') ) shsl " +
				",(select COALESCE(sum(in_sl),0) insl from e_ck_in i where i.in_contract=c.ht_no and i.in_kfcode=d.ht_code and  i.in_act in('"+cgth+"') ) thsl " +
				",(select COALESCE(sum(sh_sl),0) insl from e_ck_receiving_notice i where i.sh_contract=c.ht_no and i.sh_code=d.ht_code and  i.sh_act in('"+cgsh+"') and sh_flag='登记' ) shsl2 " +
				",f_getname('GETDWEXP',c.ht_supplier,'',c.data_corp) ht_supplier_name ,ht_mo "+
				"from e_cg_contract c ,e_cg_contract_detailed d,e_js_cpcode b where c.ht_no=d.ht_no and d.ht_code=b.cpcode_id and c.ht_flag='核准'  " +sqlWhere +
				" )t  where (t.ht_sl*"+value3+"-t.shsl-t.shsl2+t.thsl)>0 ";
		String countsql="select count(*) cn from ("+sql+") t";
		//return eCgContractDao.QueryPageList(sql,countsql,baseDto);
        return eCgContractDao.QueryToMapPage(baseDto,sql);
	}
	
	@Override
	public List<Map> getLib(String libType) {
		Object[] val = {libType};
      	String sql="select lib_id id,lib_name as text  from e_ck_lib  where lib_type=?  order by  lib_index ";
        return eCgContractDao.QueryListMap(sql,val);
	}
	
	@Override
	public List<SelectVo> comboParam(String mod, String pareatId){
		List<SelectVo> voList=new ArrayList<SelectVo>();
		Object[] val = {mod,pareatId};
      	String sql="select param_id,param_name from sys_param  where param_status='true' and param_mod=?  and parent_id=? order by  param_ord ";
      	List<Map> mapList=eCgContractDao.QueryListMap(sql,val);
      	mapList.forEach(Map -> {
      		voList.add(new SelectVo(Map.get("param_id"),String.valueOf( Map.get("param_name"))));
      	});
      	return voList;
	}


	@Override
	public Result getFlag(String htNo,String flag) {
		String flagString=eCgContractDao.getFlag(htNo);
		if(flag.equals(flagString)) {
			//eCgContractDetailedDao.getNo(htNo,flag);
			return  Result.resultOk("操作成功！");
		}
		return  Result.error("该单不是“"+flag+"”状态,不能操作！");
	}



	@Override
	public Result getFlagH(String htNo, String flag, String code) {
		String flagString=eCgContractDetailedDao.getFlagH(htNo,code);
		if(flag.equals(flagString)) {
			//eCgContractDetailedDao.getNo(htNo,flag);
			return  Result.resultOk("操作成功！");
		}
		if (flag.equals("核销")){
			eCgContractDetailedDao.updateHx(htNo,code,flag);
			return  Result.resultOk("操作成功！");
		}
		return  Result.error("该单不是“"+flag+"”状态,不能操作！");
	}

	@Override
	public Result getCancelFlagH(String htNo, String flag, String code) {
		String flagString=eCgContractDetailedDao.getFlagH(htNo,code);
		if(flag.equals(flagString)) {
			//eCgContractDetailedDao.getNo(htNo,flag);
			return  Result.resultOk("操作成功！");
		}

		if (flag.equals("登记")){
			eCgContractDetailedDao.updateHx(htNo,code,flag);
			return  Result.resultOk("操作成功！");
		}


		return  Result.error("该单不是“"+flag+"”状态,不能操作！");
	}





	@Override
	public Result getContractDetailed(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();

		StringBuffer where = SqlJoint.where(e -> {
			e.and(el->{el.between("cg.ht_date",map.get("srchRq1"),map.get("srchRq2"));});
			e.and(el->{el.contains("cg.ht_no",map.get("srchNo"));});
			e.and(el->{el.eq("cg.cg_stype", map.get("htStype"));});
			e.and(el->{el.contains("f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')",map.get("cpcodeName"));});
			e.and(el->{el.eq("cp.cpcode_size",map.get("cpcodeSize"));});
			e.and(el->{el.eq("mx.ht_code",map.get("srchCode"));});
			e.and(el->{el.eq("mx.ht_flag",map.get("srchHtFlag"));});
		});

		CharSequence sql="select a.*, (ddsl - rksl) ddcl\n" +
		"from (\n" +
		"         select to_char(cg.ht_date, 'yyyy-mm-dd')                                                                   ht_date,\n" +
		"                cg.ht_no,\n" +
		"                f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')                            cpcode_name," +
		"   	f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')  	     cpcode_size,\n" +
		"    f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')              cpcode_size_en,\n" +
		"                f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl,\n" +
		"                mx.ht_code,\n" +
		"          f_getparamname('GETCPCODEXP',cpcode_xp,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')        cpcode_xp,\n" +
		"                f_getname('TransformUser', cg.data_man,'', cg.data_corp)                                             customername,\n" +
		"                mx.ht_sl                                                                                            ddsl,\n" +
		"                COALESCE((select sum(in_sl) from e_ck_in where in_contract = mx.ht_no and in_code = mx.ht_code),\n" +
		"                         0)                                                                                         rksl,\n" +
		"                COALESCE((select sum(sl) from v_ck_mx where in_contract = mx.ht_no and code = mx.ht_code),\n" +
		"                         0)                                                                                         kcsl,\n" +
		"                COALESCE((select sum(in_sl)\n" +
		"                          from e_ck_in\n" +
		"                          where in_contract = mx.ht_no and in_code = mx.ht_code and in_act = 'CP51'),\n" +
		"                         0)                                                                                         xssl,\n" +
		"                case when mx.ht_flag = '核销' then '已核' else '未核' end                                                 ht_flag\n" +
		"         from e_cg_contract cg,\n" +
		"              e_cg_contract_detailed mx,\n" +
		"              e_js_cpcode cp\n"+
		"         where cg.ht_no = mx.ht_no\n" +
		"           and mx.ht_code = cp.cpcode_id\n" +where.toString()+
		"     ) a \n" +
		"order by ht_date,ht_no";

		return Result.formPage(eCgContractDetailedDao.QueryListMap(sql.toString()),eCgContractDetailedDao.QueryListMap(sql.toString()).size());
	}
	
	
	@Override
	public BasePage<Map<String, Object>> getWlbmSelectList(BaseDto baseDto) throws IOException {
		Map<String, Object> rmap = new HashMap<String, Object>();
		Map<String, String> map = baseDto.getParamsMap();
		Object srchCode=map.get("cpcodeId");
		Object srchCpcodefl=map.get("cpcodeFl");
		Object srchName=map.get("cpcodeName");
		Object srchSize=map.get("cpcodeSize");
		Object cgStype=map.get("cgStype");
 		String sqlWhere=" and '"+SessionUser.getCurrentCorpId()+"' = any(string_to_array( data_corp,',')) ";
 		if(srchCode != null && srchCode.toString().trim().length() > 0) {
 			sqlWhere +=" and  cpcode_id like '%"+srchCode+"%' ";
 		}
 		if(srchName != null && srchName.toString().trim().length() > 0) {
 			sqlWhere +=" and cpcode_name = '"+srchName+"' ";
 		}
 		if(srchSize != null && srchSize.toString().trim().length() > 0) {
 			sqlWhere +=" and cpcode_size like '%"+srchSize+"%' ";
 		}
 		
 		if(cgStype != null && cgStype.toString().trim().length() > 0) {
 			sqlWhere +=" and cpcode_type ='"+cgStype+"' ";
 		}
 		
 		if(srchCpcodefl != null && srchCpcodefl.toString().trim().length() > 0) {
 			 sqlWhere += " and strpos('"+srchCpcodefl+"',cpcode_fl )>0 "; //多选包含
 		}
 		String func="";

		/*ProductType productType = ProductType.valueOfParamId(cgStype);
		if(null != productType){
			if(ProductType.WL.equals(productType.getParamId())){
				func = " cpcode_fl,f_get_param_name('"+ productType.getProductCategoryParamType() +"',cpcode_fl,'cn') cpcodeflname,f_get_param_name('"+ productType.getProductNameParamType() +"',cpcode_name,'cn') cpcodename, cpcode_size_en cpcodesizeen, ";
			}else if(ProductType.CP.equals(productType.getParamId())){
				func = " cpcode_fl,f_get_param_name('"+ productType.getProductCategoryParamType() +"',cpcode_fl,'cn') cpcodeflname,f_get_param_name('"+ productType.getProductNameParamType() +"',cpcode_name,'cn') cpcodename, cpcode_size_en  cpcodesizeen ,";
			}else{
				func = " cpcode_fl,f_get_param_name('"+ productType.getProductCategoryParamType() +"',cpcode_fl,'cn') cpcodeflname,f_get_param_name('"+ productType.getProductNameParamType() +"',cpcode_name,'cn') cpcodename ,f_get_param_name('原料产地',cpcode_size_en,'"+   SessionUser.getCurrentCorpId()   +"','cn') cpcodesizeen,";
			}
		}*/

 		String sql="select cpcode_name, cpcode_fl,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_name_name,"
 				+ "f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_fl_name,"
 				+ "f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name,cpcode_size , "
 				+ "f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,cpcode_size_en ,"
 				+ "f_getparamname('GETCPCODEXP',cpcode_xp,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name,"
 				+ "cpcode_id ,cpcode_name_en ,f_get_param_name('计量单位',cpcode_bz,'"+   SessionUser.getCurrentCorpId()   +"','cn')  cpcode_bz_name,cpcode_bz,cpcode_xp ,f_get_param_name('检验档案',cpcode_check,'"+   SessionUser.getCurrentCorpId()   +"','cn') cpcode_check_name,cpcode_check,cpcode01,cpcode02,cpcode03,cpcode_flag ,data_man ,data_rq ,data_corp ,cpcode_flid ,cpcode_dunit ,cpcode_sm ,cpcode_pb  from e_js_cpcode where cpcode_flag='Y' "+sqlWhere;
		return eCgContractDao.QueryToMapPage(baseDto, sql);//QueryMapPageList(baseDto, sql, true)  QueryPageList(sql,countsql,baseDto)
	}
	
	@Override
	public List<SelectVo> getSqType(String trueText, String falseText) {
		return comboParam("采购","SQLX");
	}
	
	@Override
	public ModelAndView table(ModelAndView mv, String ht_no) {
		String sql = "select ht_no htno,to_char(ht_date,'yyyy-MM-dd') htdate,f_getname('GETCORPNAME', '', '', cg.data_corp) corpname,f_get_param_name('采购合同',ht_type,'"+   SessionUser.getCurrentCorpId()   +"','',false) httype,ht_supplier htsupplier,f_getname('GETCUSTOMERNAME', ht_supplier, '', cg.data_corp) htsuppliername,cg.data_corp datacorp,ht_quality htquality,customer_address customeraddress,ht_transport httransport,f_get_param_name('交易币种',ht_bz,'"+   SessionUser.getCurrentCorpId()   +"','财务',false) htbz,ht_settlement htsettlement,ht_supplement htsupplement,f_get_param_name('产品大类',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"','技术',false) cgstype from e_cg_contract cg,sys_customer cu where ht_no = '"+ht_no+"' and customer_id = ht_supplier";
		List<Map> table = eCgContractDao.QueryListMap(sql);
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
		mv.addObject("cgstype", table.get(0).get("cgstype"));
		mv.addObject("htbz", table.get(0).get("htbz"));
		sql = "select to_char(ht.ht_jq,'yyyy-MM-dd') htjq,ht.ht_code htcode,f_getparamname('GETBCPCODENAME',cp.cpcode_name,'','技术',c.cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')||'   '||cp.cpcode_size \"NameAndSize\",f_getparamname('GETBYPARENTID',cp.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"') cpcodebz,round(ht.ht_sl ,2) htsl,round(ht.ht_price ,4) htprice,round(ht.ht_je,2) htje from e_js_cpcode cp,e_cg_contract_detailed ht,e_cg_contract c where c.ht_no=ht.ht_no and c.data_corp=ht.data_corp and  ht.ht_no = '"+ht_no+"' and ht.ht_code = cp.cpcode_id";
		List<Map> tableMx = eCgContractDao.QueryListMap(sql);
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
		
		sql = "select customer_account customertax from sys_customer where customer_id = '"+table.get(0).get("htsupplier")+"'";
		List<Map> gF = eCgContractDao.QueryListMap(sql);
		mv.addObject("gF", gF.get(0).get("customertax"));
		sql = "select corp_tax corptax from sys_org where org_id = '"+table.get(0).get("datacorp")+"'";
		List<Map> xF = eCgContractDao.QueryListMap(sql);
		mv.addObject("xF", xF.get(0).get("corptax"));
		return mv;
	}


	@Override
	public Result hx(String htNo,String htMo) {
		eCgContractDetailedDao.updateFlag1("核销",htNo,htMo);
		if("0".equals(eCgContractDetailedDao.getSingleResult(" select count(*) from e_cg_contract_detailed where ht_no = '"+htNo+"' and ht_flag<>'登记'" ))) {
			eCgContractDao.updateFlag("核销",htNo);
		}
		return Result.resultOkMsg("核销");
	}

	@Override
	public Result qxhx(String htNo,String htMo) {
		eCgContractDetailedDao.updateFlag1("登记",htNo,htMo);
		eCgContractDao.updateFlag("核准",htNo);
		return Result.resultOkMsg("登记");
	}


	@Override
	public void exportExcel(HttpServletResponse response, HttpServletRequest request) {
		ExcelUtil util = ExcelUtil.getInstance();
		BaseDto dto = BaseDto.ValueOfRequest(request); 
		dto.setPageIndex(0);
		Map<String, String> map = dto.getParamsMap();
	
		
		
		String  title = "";
		if(StringUtils.isNotEmpty(map.get("stype"))) {
			if(map.get("stype").equals("WL")) {				
				title ="物料"+map.get("reqType")+"统计";
			}else if(map.get("stype").equals("YL")) {
				title ="原料"+map.get("reqType")+"统计";
			}
			
		}

		
		util.ExcelToWeb(request, title, response, eCgContractSqlDao.findList(dto));
	}

	private boolean isNumber(String str){
		String reg = "^[0-9]+(.[0-9]+)?$";
		return str.matches(reg);
	}
	



	
	

}
