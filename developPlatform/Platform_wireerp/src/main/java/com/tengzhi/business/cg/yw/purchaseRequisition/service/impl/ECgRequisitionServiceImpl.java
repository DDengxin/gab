package com.tengzhi.business.cg.yw.purchaseRequisition.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseRequisition.dao.ECgRequisitionDao;
import com.tengzhi.business.cg.yw.purchaseRequisition.model.EcgRequisition;
import com.tengzhi.business.cg.yw.purchaseRequisition.model.EcgRequisition.EcgRequisition_PK;
import com.tengzhi.business.cg.yw.purchaseRequisition.service.ECgRequisitionService;
import com.tengzhi.business.cg.yw.purchaseRequisition.vo.EcgRequisitiontVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;


@Service
@Transactional
public class ECgRequisitionServiceImpl  implements ECgRequisitionService {
	@Autowired
	private ECgRequisitionDao eCgRequisitionDao;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;

	@Autowired
	private  SysParamService sysParamService;


	@Override
	public BasePage<EcgRequisition> getSrchTopList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		String where="  data_corp='"+securityUser.getCorpId()+"' ";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			where+=" and sq_rq >='"+map.get("srchRq1")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			where+=" and sq_rq <='"+map.get("srchRq2")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
			where+=" and sq_note like '%"+map.get("srchNo")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchDept"))) {
			where+=" and sq_dept like '%"+map.get("srchDept")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchsqMan"))) {
			where+=" and sq_man like '%"+map.get("srchsqMan")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchflag"))) {
			where+=" and flag like '%"+map.get("srchflag")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("cgStype"))) {
			where+=" and cg_stype = '"+map.get("cgStype")+"'";
		}
		String sqlString="select sq_note,sq_rq,sq_type,sq_jjcd,sq_dept,sq_man,data_man,data_corp,cg_stype,flag,sum(sl) sl, sum(zl) zl \r " + 
		        ",f_get_param_name('紧急程度',sq_jjcd,'"+   SessionUser.getCurrentCorpId()   +"') jjcdname,f_get_param_name('申请类型',sq_type,'"+   SessionUser.getCurrentCorpId()   +"') typename,f_getname('GETDEPTNAME',sq_dept,'',data_corp) deptname,f_getname('GETUSERNAME',sq_man,'',data_corp) sqmanname,f_getname('GETUSERNAME',data_man,'',data_corp) datamanname" +
				" from e_cg_requisition where "+where+" group by sq_note,sq_rq,sq_type,sq_jjcd,sq_dept,sq_man,data_man,data_corp,cg_stype,flag ";
		return eCgRequisitionDao.QueryPageLists(baseDto,sqlString+" order by sq_rq desc,sq_note desc");
	}

	@Override
	public BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlString=" select code,sl,zl,to_char(sq_jq,'yyyy-MM-dd')sq_jq,dgnote,brand,sq_purpose,sq_sm,"
				+" f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name_name,"
				+" b.cpcode_size,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl,"
				+" f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz,b.cpcode_xp,"
				+ "f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name, "
				+ "f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,"
				+" b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03,b.cpcode_Size_En " +
				"from e_cg_requisition a,e_js_cpcode b where a.code=b.cpcode_id and  sq_note='"+map.get("sqNote")+"'";
		//return eCgRequisitionDao.QueryPageLists(baseDto,sqlString);
		return  eCgRequisitionDao.QueryMapPageList(baseDto, sqlString, true);
	}

	@Override
	public Result save(EcgRequisitiontVo ecgRequisitiontVo)throws Exception {
		List<EcgRequisition> addModel=new ArrayList<EcgRequisition>();
		List<EcgRequisition> modifyedModel=new ArrayList<EcgRequisition>();
		List<EcgRequisition> deteleModel=new ArrayList<EcgRequisition>();
		String prefix=sysParamService.getParamValue1("申请类型", ecgRequisitiontVo.getHeaddata().getSqType());
		String note=sysGenNoteService.getyyMMNote4(EcgRequisition.class,prefix);
		SessionUser securityUser=SessionUser.SessionUser();
		ecgRequisitiontVo.getHeaddata().setSqNote(note);
		ecgRequisitiontVo.getHeaddata().setFlag("登记");
		ecgRequisitiontVo.getHeaddata().setDataMan(securityUser.getUserId());
		ecgRequisitiontVo.getHeaddata().setDataDate(new Date());
		ecgRequisitiontVo.getHeaddata().setDataCorp(securityUser.getCorpId());
		if(ecgRequisitiontVo.getEntitydata().size()>0) {
			 ecgRequisitiontVo.getEntitydata().forEach( item -> {
				if("added".equals(item.get_state())) {
					 item.setSqNote(note);
					 item.setSqRq(ecgRequisitiontVo.getHeaddata().getSqRq());
					 item.setSqType(ecgRequisitiontVo.getHeaddata().getSqType());
					 item.setSqJjcd(ecgRequisitiontVo.getHeaddata().getSqJjcd());
					 item.setSqDept(ecgRequisitiontVo.getHeaddata().getSqDept());
					 item.setSqMan(ecgRequisitiontVo.getHeaddata().getSqMan());
					 item.setFlag(ecgRequisitiontVo.getHeaddata().getFlag());
					 item.setDataMan( ecgRequisitiontVo.getHeaddata().getDataMan());
					 item.setDataDate(ecgRequisitiontVo.getHeaddata().getDataDate());
					 item.setDataCorp(ecgRequisitiontVo.getHeaddata().getDataCorp());
					 item.setCgStype(ecgRequisitiontVo.getHeaddata().getCgStype());	
					 addModel.add(item);
					eCgRequisitionDao.requisitionhx("核销",item.getSqNote(),item.getCode());

				}else if("modified".equals(item.get_state())) {
					modifyedModel.add(item);
				}else if("removed".equals(item.get_state())) {
					deteleModel.add(item);
				}
				 
			 });
		}
		
		//保存到数据库
		if(addModel.size()>0) {
			addModel.forEach( model -> {
				eCgRequisitionDao.save(model);
			});
		}
		if(modifyedModel.size()>0) {
			modifyedModel.forEach( model -> {
				EcgRequisition_PK pk = new EcgRequisition_PK(model.getSqNote(),model.getCode() );
				eCgRequisitionDao.dynamicSave(model,eCgRequisitionDao.findById(pk).orElse(null));
			});
		}
		if(deteleModel.size()>0) {
			deteleModel.forEach( model -> {
				 EcgRequisition_PK pk = new EcgRequisition_PK(model.getSqNote(),model.getCode() );
				eCgRequisitionDao.deleteById(pk);
			});
		}
		return Result.resultOk(note);
	}
	@Override
	public Result update(EcgRequisitiontVo ecgRequisitiontVo)throws Exception {
		List<EcgRequisition> addModel=new ArrayList<EcgRequisition>();
		List<EcgRequisition> modifyedModel=new ArrayList<EcgRequisition>();
		List<EcgRequisition> deteleModel=new ArrayList<EcgRequisition>();
		String note=ecgRequisitiontVo.getHeaddata().getSqNote();
		SessionUser securityUser=SessionUser.SessionUser();
		ecgRequisitiontVo.getHeaddata().setSqNote(note);
		ecgRequisitiontVo.getHeaddata().setFlag("登记");
		ecgRequisitiontVo.getHeaddata().setDataMan(securityUser.getUserId());
		ecgRequisitiontVo.getHeaddata().setDataDate(new Date());
		ecgRequisitiontVo.getHeaddata().setDataCorp(securityUser.getCorpId());
		if(ecgRequisitiontVo.getEntitydata().size()>0) {
			 ecgRequisitiontVo.getEntitydata().forEach( item -> {
				if("added".equals(item.get_state())) {
					 item.setSqNote(note);
					 item.setSqRq(ecgRequisitiontVo.getHeaddata().getSqRq());
					 item.setSqType(ecgRequisitiontVo.getHeaddata().getSqType());
					 item.setSqJjcd(ecgRequisitiontVo.getHeaddata().getSqJjcd());
					 item.setSqDept(ecgRequisitiontVo.getHeaddata().getSqDept());
					 item.setSqMan(ecgRequisitiontVo.getHeaddata().getSqMan());
					 item.setFlag(ecgRequisitiontVo.getHeaddata().getFlag());
					 item.setDataMan( ecgRequisitiontVo.getHeaddata().getDataMan());
					 item.setDataDate(ecgRequisitiontVo.getHeaddata().getDataDate());
					 item.setDataCorp(ecgRequisitiontVo.getHeaddata().getDataCorp());
					 item.setCgStype(ecgRequisitiontVo.getHeaddata().getCgStype());	
					 addModel.add(item);
				}else if("modified".equals(item.get_state())) {
					 item.setSqNote(note);
					modifyedModel.add(item);
				}else if("removed".equals(item.get_state())) {
					 item.setSqNote(note);
					deteleModel.add(item);
				}
				 
			 });
		}
		
		//保存到数据库
		if(addModel.size()>0) {
			addModel.forEach( model -> {
				eCgRequisitionDao.save(model);
			});
		}
		if(modifyedModel.size()>0) {
			modifyedModel.forEach( model -> {
				EcgRequisition_PK pk = new EcgRequisition_PK(model.getSqNote(),model.getCode() );
				eCgRequisitionDao.dynamicSave(model,eCgRequisitionDao.findById(pk).orElse(null));
			});
		}
		if(deteleModel.size()>0) {
			deteleModel.forEach( model -> {
				 EcgRequisition_PK pk = new EcgRequisition_PK(model.getSqNote(),model.getCode() );
				eCgRequisitionDao.deleteById(pk);
			});
		}
		
		//修改表头 
		String sqlString ="update EcgRequisition set sqRq=?1,sqDept=?2,sqMan=?3,sqJjcd=?4,sqType=?5 where sqNote=?6 ";
		eCgRequisitionDao.executeUpdate(sqlString, ecgRequisitiontVo.getHeaddata().getSqRq(),ecgRequisitiontVo.getHeaddata().getSqDept(),ecgRequisitiontVo.getHeaddata().getSqMan(),
                ecgRequisitiontVo.getHeaddata().getSqJjcd(),ecgRequisitiontVo.getHeaddata().getSqType(),ecgRequisitiontVo.getHeaddata().getSqNote());
		//end
		
		return Result.resultOk(note);
	}
	
	
	@Override
	public EcgRequisition findBySqnote(String note) {
		return eCgRequisitionDao.findBySqnote(note);

	}
	

	@Override
	public void deleteById(String sqNote){
		eCgRequisitionDao.deleteBysqNote(sqNote);
	}

	
	@Override
	public Result getFlag(String sqNote, String flag) {
		String flagString=eCgRequisitionDao.getFlag(sqNote);
		if(flag.equals(flagString)) {
			return  Result.resultOk("操作成功！");
		}
		return  Result.error("该单不是“"+flag+"”状态,不能操作！");
	}

	@Override
	public Result setFlag(String sqNote, String flag) {
		eCgRequisitionDao.setFlag(sqNote,flag);
		return  Result.resultOk("操作成功！");
	}
	
	@Override
	public Map<String, Object> getCgsqSelectList(BaseDto baseDto) throws IOException {
		Map<String, Object> rmap = new HashMap<String, Object>();
		Map<String, String> map = baseDto.getParamsMap();
		Object srchRq1=map.get("srchRq1");
		Object srchRq2=map.get("srchRq2");
		Object srchNo=map.get("srchNote");
		Object srchCode=map.get("srchCode");
		Object cpcodeName=map.get("cpcodeName");
		Object cpcodeSize=map.get("cpcodeSize");
		Object cgStype=map.get("cgStype");
 		String sqlWhere="";
 		
 		if(srchRq1 != null && srchRq1.toString().trim().length() > 0) {
 			sqlWhere +=" and a.sq_rq >= '"+srchRq1+"' ";
 		}
 		if(srchRq2 != null && srchRq2.toString().trim().length() > 0) {
 			sqlWhere +=" and a.sq_rq <= '"+srchRq2+"' ";
 		}
 		if(srchNo != null && srchNo.toString().trim().length() > 0) {
 			sqlWhere +=" and a.sq_note like  '%"+srchNo+"%' ";
 		}
 		if(srchCode != null && srchCode.toString().trim().length() > 0) {
 			sqlWhere +=" and a.code ='"+srchCode+"' ";
 		}
 		if(cpcodeName != null && cpcodeName.toString().trim().length() > 0) {
 			sqlWhere +=" and f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') like '%"+cpcodeName+"%' ";
 		}
 		if(cpcodeSize != null && cpcodeSize.toString().trim().length() > 0) {
 			sqlWhere +=" and b.cpcode_size ='"+cpcodeSize+"' ";
 		}
 		
 		if(cgStype != null && cgStype.toString().trim().length() > 0) {
 			sqlWhere +=" and b.cpcode_type ='"+cgStype+"'  ";
 		}
		String sql="select t.*,(t.sqsl-t.ddsl) sl,(t.sqzl-t.ddzl) zl from (\r " + 
				" select a.sq_note||'_'||code id,a.sq_note sqnote,to_char(a.sq_rq, 'yyyy-mm-dd') sqrq,a.sq_type sqtype,a.sq_jjcd sqjjcd,a.sq_dept sqdept,a.code ,sl sqsl,zl sqzl,to_char(a.sq_jq, 'yyyy-mm-dd') sqjq,sq_sm sqsm,dgnote" +
				",f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcodename,b.cpcode_size cpcodesize,b.cpcode_fl cpcodefl,f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"') cpcodebz,b.cpcode_xp cpcodexp,b.cpcode_check cpcodecheck,b.cpcode01 ,b.cpcode02,b.cpcode03,b.cpcode_size_en cpcodesizeen "+
				",(select COALESCE(sum(ht_sl),0) insl from e_cg_contract_detailed i where i.ht_sqnote=a.sq_note and i.ht_code=a.code) ddsl" + 
				",(select COALESCE(sum(ht_zl),0) insl from e_cg_contract_detailed i where i.ht_sqnote=a.sq_note and i.ht_code=a.code) ddzl" + 
				",f_get_param_name('紧急程度',a.sq_jjcd,'"+   SessionUser.getCurrentCorpId()   +"') jjcdname,f_get_param_name('采购参数',a.sq_type,'"+   SessionUser.getCurrentCorpId()   +"') typename,f_getname('GETDEPTNAME',a.sq_dept,'',a.data_corp) deptname"+
				" from e_cg_requisition  a,e_js_cpcode b where a.code=b.cpcode_id and a.flag='核准'  " +sqlWhere +
				" )t  where (t.sqsl-t.ddsl)>0 or (t.sqsl-t.ddsl)>0 ";
		rmap.put("data", eCgRequisitionDao.QueryListMap(sql));
		rmap.put("status", true);
		return rmap;
	}


	@Override
    public Result a(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		Object srchRq1=map.get("srchRq1");
		Object srchRq2=map.get("srchRq2");
		Object srchCode=map.get("srchCode");
		Object srchCpcodefl=map.get("srchCpcodefl");
		Object srchName=map.get("srchName");
		Object srchSize=map.get("srchSize");
		Object cgStype=map.get("cgStype");
		Object cpcodeType=map.get("cpcodeType");
		Object srchNo=map.get("srchNote");
		String sqlWhere="";
		if(srchNo != null && srchNo.toString().trim().length() > 0) {
			sqlWhere +=" and a.sq_note ='"+srchNo+"'  ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchDept"))) {
			sqlWhere+=" and a.sq_dept like '%"+map.get("outCustomer")+"%'";
		}
		if(srchRq1 != null && srchRq1.toString().trim().length() > 0) {
			sqlWhere +=" and a.sq_rq >= '"+srchRq1+"' ";
		}
		if(srchRq2 != null && srchRq2.toString().trim().length() > 0) {
			sqlWhere +=" and a.sq_rq <= '"+srchRq2+"' ";
		}

		if(cpcodeType != null && cpcodeType.toString().trim().length() > 0) {
			sqlWhere +=" and b.cpcode_type ='"+cpcodeType+"'  ";
		}
		if(srchCode != null && srchCode.toString().trim().length() > 0) {
			sqlWhere +=" and  b.cpcode_id like '%"+srchCode+"%' ";
		}
		if(srchName != null && srchName.toString().trim().length() > 0) {
			sqlWhere +=" and f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') like '%"+srchName+"%' ";
		}
		if(srchSize != null && srchSize.toString().trim().length() > 0) {
			sqlWhere +=" and b.cpcode_size like '%"+srchSize+"%' ";
		}

		if(cgStype != null && cgStype.toString().trim().length() > 0) {
			sqlWhere +=" and b.cpcode_type ='"+cgStype+"' ";
		}

		if(srchCpcodefl != null && srchCpcodefl.toString().trim().length() > 0) {
			sqlWhere += " and strpos('"+srchCpcodefl+"',b.cpcode_fl )>0 "; //多选包含
		}
		String sql="SELECT " +
				" b.cpcode_fl, " +
				" a.sq_rq, " +
				" a.sq_note, " +
				" f_getname('GETDWNAME',sq_dept,'',a.data_corp)deptname ," +
				" f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name_name, " +
				" f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_fl_name, " +
				" f_getparamname('GETCPCODESIZE',b.cpcode_size_en,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name,  b.cpcode_size_en, " +
				" b.cpcode_id, " +
				" b.cpcode_name_en, f_getparamname('GETCPCODESIZEEN',b.cpcode_name_en,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name    ,  " +
				" b.cpcode_size , " +
				" f_get_param_name('计量单位',b.cpcode_bz,'"+   SessionUser.getCurrentCorpId()   +"','cn') cpcode_bz_name, " +
				" b.cpcode_xp ,f_getparamname('GETCPCODEXP',b.cpcode_xp,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name, " +
				" f_get_param_name('检验档案',b.cpcode_check,'"+   SessionUser.getCurrentCorpId()   +"','cn') cpcode_check_name, " +
				" b.cpcode01, " +
				" b.cpcode02, " +
				" b.cpcode03, " +
				" a.sq_dept, " +
				" f_getname('GETDEPTNAME',a.sq_dept,null,b.data_corp) sq_dept_name , " +
				" a.sl sqsl, " +
				" (select COALESCE(sum(ht_sl),0) insl from e_cg_contract_detailed i where i.ht_sqnote=a.sq_note and i.ht_code=a.code) ddsl, " +
				" (a.sl-(select COALESCE(sum(ht_sl),0) insl from e_cg_contract_detailed i where i.ht_sqnote=a.sq_note and i.ht_code=a.code)) sl, " +
				" b.cpcode_flag, " +
				" b.data_man , " +
				" b.data_rq , " +
				" b.data_corp , " +
				" b.cpcode_flid , " +
				" b.cpcode_dunit , " +
				" b.cpcode_sm , " +
				" b.cpcode_pb ,  " +
				" a.code code,a.flag " +
				"FROM " +
				" e_cg_requisition a,e_js_cpcode b " +
				"WHERE " +
				" a.code=b.cpcode_id " +
				" AND b.cpcode_flag = 'Y' "+sqlWhere;
		//eCgRequisitionDao.QueryMapPageList(baseDto,sql,true);
		return eCgRequisitionDao.QueryToMapPage(baseDto,sql).getResult();

	}

	@Override
	public void avacsaca(String a,String note, String code) {
		 a= "取消".equals(a)?"核准":"核销";
		 eCgRequisitionDao.requisitionhx(a,note,code);
	}
}
