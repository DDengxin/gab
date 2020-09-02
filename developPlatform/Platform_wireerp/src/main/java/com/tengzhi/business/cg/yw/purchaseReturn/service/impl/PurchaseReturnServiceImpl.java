package com.tengzhi.business.cg.yw.purchaseReturn.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SessionUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.base.common.WarehouseActionCaliber;
import com.tengzhi.business.cg.yw.purchaseReceipt.dao.ECkInDao;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn.ECkIn_PK;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;
import com.tengzhi.business.cg.yw.purchaseReturn.service.PurchaseReturnService;
import com.tengzhi.business.cg.yw.purchaseSettle.dao.ECwYsyfDao;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.service.SysParamService;

import cn.hutool.core.util.ObjectUtil;


@Service
@Transactional
public class PurchaseReturnServiceImpl  implements PurchaseReturnService {
	@Autowired
	private ECkInDao eCkInDao;

	@Autowired
	private SysGenNoteService sysGenNoteService;
	
	@Autowired
	private SysParamService sysParamService;
	
	@Autowired
	private ECwYsyfDao eCwYsyfDao;

	@Override
    public BasePage<ECkIn> getSrchTopList(BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        Object srchrq1=map.get("srchRq1");
        Object srchrq2=map.get("srchRq2");
        Object inNote=map.get("srchinNote");
        Object supplier=map.get("srchSupplier");
        Object cgStype=map.get("cgStype");
        SessionUser securityUser=SessionUser.SessionUser();
        String sqlWhere = " where  data_corp='"+securityUser.getCorpId()+"' ";
		if (!StringUtils.isBlank(srchrq1.toString())) {
			 sqlWhere += " and in_rq >='" + srchrq1 + "'";
		}
		if (!StringUtils.isBlank(srchrq2.toString())) {
			 sqlWhere += " and in_rq <='" + srchrq2 + "'";
		}
		if (!StringUtils.isBlank(inNote.toString())) {
			sqlWhere += " and in_note like'%" + inNote + "%'";
		}
		if (!StringUtils.isBlank(supplier.toString())) {
			sqlWhere += " and in_customer like'%" + supplier + "%'";
		}
		
		if(ObjectUtil.isNotEmpty(map.get("srchflag"))) {
			sqlWhere+=" and in_flag like '%"+map.get("srchflag")+"%'";
		}
		
		if(cgStype != null && cgStype.toString().trim().length() > 0) {
			String parameterId = eCkInDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
					, WarehouseActionCaliber.cgth,cgStype.toString(),securityUser.getCorpId());
			sqlWhere +=" and in_act='"+parameterId+"' ";
			sqlWhere +=" and in_type = '"+cgStype+"' ";
 		}
		
		String sql = "select in_rq ,in_note ,in_customer ,in_lib,in_act,in_bz,in_tax ,abs(sum(in_js)) in_js,abs(sum(in_sl)) in_sl,abs(sum(in_zl)) in_zl,abs(sum(in_sl*in_price)) inje,in_flag ,data_man ,MAX(data_date) data_date,data_corp  "
				+",f_get_param_name('产品大类',in_type,'"+   SessionUser.getCurrentCorpId()   +"') intypename,f_get_param_name('仓库收发',in_act,'"+   SessionUser.getCurrentCorpId()   +"') inactname,f_get_param_name('交易币种',in_bz,'"+   SessionUser.getCurrentCorpId()   +"') inbzname,f_getname('GETDWEXP',in_customer,'',data_corp) incustomername,f_get_param_name('库房档案',in_lib,'"+   SessionUser.getCurrentCorpId()   +"') inlibname,f_getname('GETUSERNAME',data_man,'',data_corp) datamanname "
				+ " from  e_ck_in " + sqlWhere + "  group by in_rq,in_note,in_customer,in_act,in_flag,data_man,data_corp,in_lib,in_bz,in_tax,in_type  ";
        return eCkInDao.QueryPageLists(baseDto,sql+" order by in_rq desc,in_note desc");
    }
	  
	 @Override
    public BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        Object inNote=map.get("inNote");
        String sql = "select in_contract||'_'||in_Kfcode as id,in_code,abs(in_js) in_js ,abs(in_sl) in_sl,abs(in_sl*in_price) inje ,abs(in_zl) in_zl ,in_pack ,in_bpack ,in_price ,in_contract ,in_kfcontract ,in_Kfcode, in_checkflag ,"
				+ " in_month ,in_type,f_get_param_name('产品大类',in_type,'"+   SessionUser.getCurrentCorpId()   +"') in_type_name ,in_luono ,in_vnote,in_hs,"
				+ "f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',in_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,"
				+ "b.cpcode_size,"
				+ "f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',in_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl_name,cpcode_fl,"
				+ "f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz_name,"
				+ "b.cpcode_xp,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03,b.cpcode_Size_En"
				+ ", f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',in_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name,"
				+ " f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',in_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name "
				+ ",f_getsl('GETKTHSL',in_note,in_pack,'',a.data_corp) rksl "
				+ "  from  e_ck_in a,e_js_cpcode b where a.in_Kfcode=b.cpcode_id and  in_note='" + inNote + "' ";
		sql = "select * from (" + sql + ")n ";
      //  return eCkInDao.QueryNoPageLists(sql);
		return  eCkInDao.QueryMapPageList(baseDto, sql, true);
    }
	 
	 @Override
		public Result save(ECkInVo eCkInVo) throws Exception {
			List<ECkIn> addECkIns=new ArrayList<ECkIn>();
			List<ECkIn> modifyedECkIns=new ArrayList<ECkIn>();
			String cgStype=eCkInVo.geteCkIn().getCgStype();
		 	SessionUser securityUser = SessionUser.SessionUser();
		 	String parameterId = eCkInDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
				 , WarehouseActionCaliber.cgth,cgStype,securityUser.getCorpId());
		 	SysParams sysParams = sysParamService.findByParameterId(parameterId, "仓库收发");
		 	String note = sysGenNoteService.getInOutNote(ECkIn.class,cgStype, WarehouseActionCaliber.cgth,securityUser.getCorpId());
			eCkInVo.geteCkIn().setInNote(note);
			eCkInVo.geteCkIn().setInFlag("登记");
			eCkInVo.geteCkIn().setDataMan(securityUser.getUserId());
			eCkInVo.geteCkIn().setDataDate(new Date());
			eCkInVo.geteCkIn().setDataCorp(securityUser.getCorpId());
			eCkInVo.geteCkIn().setInAct(parameterId);
			//业务逻辑判断start
			//新增
			for(int i=0;i<eCkInVo.getAddedList().size();i++) {
				ECkIn model=eCkInVo.getAddedList().get(i);
				model.setInNote(note);
				model.setInRq(eCkInVo.geteCkIn().getInRq());
				model.setInAct(eCkInVo.geteCkIn().getInAct());
				model.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
				model.setInFlag(eCkInVo.geteCkIn().getInFlag());
				model.setDataMan(securityUser.getUserId());
				model.setDataDate(eCkInVo.geteCkIn().getDataDate());
				model.setDataCorp(eCkInVo.geteCkIn().getDataCorp());
				model.setInCode(model.getInKfcode());
				model.setInBz(eCkInVo.geteCkIn().getInBz());
				model.setInTax(eCkInVo.geteCkIn().getInTax());
				//数量，重量，件数*paramvalue
				model.setInJs(model.getInJs().multiply(sysParams.getParamValue()));
				model.setInSl(model.getInSl().multiply(sysParams.getParamValue()));
				model.setInZl(model.getInZl().multiply(sysParams.getParamValue()));
				//model.setInBpack(model.getInPack());
				addECkIns.add(model);
			}
			//修改
			if (!eCkInVo.getModifyedList().isEmpty()) {
				for(int i=0;i<eCkInVo.getModifyedList().size();i++) {
					ECkIn item=eCkInVo.getModifyedList().get(i);
					item.setInNote(note);
					item.setInRq(eCkInVo.geteCkIn().getInRq());
					item.setInAct(eCkInVo.geteCkIn().getInAct());
					item.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
					item.setInFlag(eCkInVo.geteCkIn().getInFlag());
					item.setDataMan(securityUser.getUserId());
					item.setDataDate(eCkInVo.geteCkIn().getDataDate());
					item.setDataCorp(eCkInVo.geteCkIn().getDataCorp());
					item.setInCode(item.getInKfcode());
					item.setInBz(eCkInVo.geteCkIn().getInBz());
					item.setInTax(eCkInVo.geteCkIn().getInTax());
					item.setInJs(item.getInJs().multiply(sysParams.getParamValue()));
					item.setInSl(item.getInSl().multiply(sysParams.getParamValue()));
					item.setInZl(item.getInZl().multiply(sysParams.getParamValue()));
					modifyedECkIns.add(item);
				}
			}
			//业务逻辑判断end
			//开始保存到数据库
			if (!addECkIns.isEmpty()) {
				addECkIns.forEach(item -> {
					eCkInDao.save(item);
				});
			}
			if (!modifyedECkIns.isEmpty()) {
				modifyedECkIns.forEach(item -> {
					eCkInDao.dynamicSave(item, eCkInDao.findById(new ECkIn_PK(note, item.getInPack())).orElse(null));
				});
			}
			if (!eCkInVo.getDeletedList().isEmpty()) {
				eCkInVo.getDeletedList().forEach( item ->{
					ECkIn_PK pk = new ECkIn_PK(eCkInVo.geteCkIn().getInNote(),item.getInPack() );
					eCkInDao.deleteById(pk);
				});
			}
			//end
			return Result.resultOk(eCkInVo.geteCkIn());
		}

		@Override
		public Result update(ECkInVo eCkInVo) throws Exception {
			List<ECkIn> addECkIns=new ArrayList<ECkIn>();
			List<ECkIn> modifyedECkIns=new ArrayList<ECkIn>();
			String cgStype=eCkInVo.geteCkIn().getCgStype();
			SessionUser securityUser = SessionUser.SessionUser();
			String parameterId = eCkInDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
					, WarehouseActionCaliber.cgth,cgStype,securityUser.getCorpId());
			SysParams sysParams = sysParamService.findByParameterId(parameterId, "仓库收发");
			eCkInVo.geteCkIn().setInFlag("登记");
			eCkInVo.geteCkIn().setDataMan(securityUser.getUserId());
			eCkInVo.geteCkIn().setDataDate(new Date());
			eCkInVo.geteCkIn().setDataCorp(securityUser.getCorpId());
			eCkInVo.geteCkIn().setInAct(parameterId);
			//业务逻辑判断start
			//新增
			for(int i=0;i<eCkInVo.getAddedList().size();i++) {
				ECkIn model=eCkInVo.getAddedList().get(i);
				model.setInNote(eCkInVo.geteCkIn().getInNote());
				model.setInRq(eCkInVo.geteCkIn().getInRq());
				model.setInAct(eCkInVo.geteCkIn().getInAct());
				model.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
				model.setInFlag(eCkInVo.geteCkIn().getInFlag());
				model.setDataMan(securityUser.getUserId());
				model.setDataDate(eCkInVo.geteCkIn().getDataDate());
				model.setDataCorp(eCkInVo.geteCkIn().getDataCorp());
				model.setInCode(model.getInKfcode());
				model.setInBz(eCkInVo.geteCkIn().getInBz());
				model.setInTax(eCkInVo.geteCkIn().getInTax());
				//数量，重量，件数*paramvalue
				model.setInJs(model.getInJs().multiply(sysParams.getParamValue()));
				model.setInSl(model.getInSl().multiply(sysParams.getParamValue()));
				model.setInZl(model.getInZl().multiply(sysParams.getParamValue()));
				addECkIns.add(model);
			}
			//修改
				if (!eCkInVo.getModifyedList().isEmpty()) {
					for(int i=0;i<eCkInVo.getModifyedList().size();i++) {
						ECkIn item=eCkInVo.getModifyedList().get(i);
						item.setInNote(eCkInVo.geteCkIn().getInNote());
						item.setInRq(eCkInVo.geteCkIn().getInRq());
						item.setInAct(eCkInVo.geteCkIn().getInAct());
						item.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
						item.setInFlag(eCkInVo.geteCkIn().getInFlag());
						item.setDataMan(securityUser.getUserId());
						item.setDataDate(eCkInVo.geteCkIn().getDataDate());
						item.setDataCorp(eCkInVo.geteCkIn().getDataCorp());
						item.setInCode(item.getInKfcode());
						item.setInBz(eCkInVo.geteCkIn().getInBz());
						item.setInTax(eCkInVo.geteCkIn().getInTax());
						item.setInJs(item.getInJs().multiply(sysParams.getParamValue()));
						item.setInSl(item.getInSl().multiply(sysParams.getParamValue()));
						item.setInZl(item.getInZl().multiply(sysParams.getParamValue()));
						modifyedECkIns.add(item);
					}
				}
			//业务逻辑判断end
			//开始保存到数据库
			if (!addECkIns.isEmpty()) {
				addECkIns.forEach(item -> {
					eCkInDao.save(item);
				});
			}
			if (!modifyedECkIns.isEmpty()) {
				modifyedECkIns.forEach(item -> {
					eCkInDao.dynamicSave(item, eCkInDao.findById(new ECkIn_PK(eCkInVo.geteCkIn().getInNote(), item.getInPack())).orElse(null));
				});
			}
			if (!eCkInVo.getDeletedList().isEmpty()) {
				eCkInVo.getDeletedList().forEach( item ->{
					ECkIn_PK pk = new ECkIn_PK(eCkInVo.geteCkIn().getInNote(),item.getInPack() );
					eCkInDao.deleteById(pk);
				});
			}
			//end
			//修改表头 
			String sqlString ="update ECkIn set inLib=?1,inBz=?2,inTax=?3 where inNote=?4 ";
			eCkInDao.executeUpdate(sqlString, eCkInVo.geteCkIn().getInLib(),eCkInVo.geteCkIn().getInBz(),eCkInVo.geteCkIn().getInTax(),eCkInVo.geteCkIn().getInNote());
			//end
			return Result.resultOkMsg("修改成功");
		}
	 
	 @Override
	 public ECkIn findByInNote(String inNote) {
		 ECkIn in=new ECkIn();
		 String sqlString=" select *,(f_getname('GETDWEXP',in_customer,'',data_corp)) incustomername from E_Ck_In where in_Note ='"+inNote+"' ";
		 BasePage<ECkIn> list=eCkInDao.QueryNoPageLists(sqlString);
		 if(list.getContent().size()>0) {
			 in=list.getContent().get(0);
		 }		
		 return  in;
	  }
	 
	 @Override
	 public ECkIn findById(String inNote,String inPack) {
		  ECkIn_PK pk = new ECkIn_PK(inNote,inPack);
		  return eCkInDao.findById(pk).orElse(null);
	 
	  }
	 
	 @Override
    public void deleteById(String inNote){
		 eCkInDao.deleteByInNote(inNote);
		
    }

	 @Override
	 public BasePage <Map<String, Object>> getCgthSelectList(BaseDto baseDto) throws IOException {
			Map<String, Object> rmap = new HashMap<String, Object>();
		 	SessionUser securityUser = SessionUser.SessionUser();
			Map<String, String> map = baseDto.getParamsMap();
			Object srchRq1=map.get("srchRq1");
			Object srchRq2=map.get("srchRq2");
			Object srchNo=map.get("srchNo");
			Object srchSupplier=map.get("srchSupplier");
			Object srchLib=map.get("srchLib");
		    Object cgStype=map.get("cgStype");
	 		String sqlWhere="";
	 		if(srchRq1 != null && srchRq1.toString().trim().length() > 0) {
	 			sqlWhere +=" and i.in_rq >= '"+srchRq1+"' ";
	 		}
	 		if(srchRq1 != null && srchRq1.toString().trim().length() > 0) {
	 			sqlWhere +=" and i.in_rq <= '"+srchRq2+"' ";
	 		}
	 		if(srchNo != null && srchNo.toString().trim().length() > 0) {
	 			sqlWhere +=" and i.in_note like  '%"+srchNo+"%' ";
	 		}
	 		if(srchSupplier != null && srchSupplier.toString().trim().length() > 0) {
	 			sqlWhere +=" and i.in_customer ='"+srchSupplier+"' ";
	 			
	 		}
	 		if(srchLib != null && srchLib.toString().trim().length() > 0) {
	 			sqlWhere +=" and i.in_lib ='"+srchLib+"' ";
	 			
	 		}
	 		
	 		if(cgStype != null && cgStype.toString().trim().length() > 0) {
				String parameterId = eCkInDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
						, WarehouseActionCaliber.cgrk,cgStype.toString(),securityUser.getCorpId());
				sqlWhere +=" and i.in_act='"+parameterId+"' ";
				sqlWhere +=" and i.in_type ='"+cgStype+"' ";
	 		}
	 		String cgthact=eCkInDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
					, WarehouseActionCaliber.cgth,cgStype.toString(),securityUser.getCorpId());
	 		//String val[] = {srchSupplier.toString()};
			String sql=" select t.*, (t.in_sl-t.thsl) sl,((t.in_sl-t.thsl)*t.in_price) in_je from ( " +
					" select i.in_note||'_'||i.in_contract||'_'||in_kfcode id,to_char(i.in_rq, 'yyyy-mm-dd') in_rq,i.in_note  ,i.in_customer  ,i.in_code  ,i.in_js  ,i.in_sl  ,i.in_zl  ,i.in_pack  ,i.in_bpack  ,i.in_price  , " +
					" i.in_contract ,i.in_kfcontract ,i.in_kfcode ,i.in_lib ,i.in_checkflag  ,i.in_month ,i.in_type ,f_get_param_name('产品大类',i.in_type,'"+   SessionUser.getCurrentCorpId()   +"') in_type_name,i.in_luono  ,i.in_vnote  ,i.data_corp  " +
					",f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',in_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name_name,cpcode_name ,"
					+ "f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',in_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name, 	"
					+ "f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',in_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,"
					+ "f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',in_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl_name,"
					+ "b.cpcode_size  ,b.cpcode_fl  ,"
					+ "f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz_name,cpcode_bz,"
                    + "f_getparamname('GETCPCODEXP',cpcode_xp,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name,"
					+ "b.cpcode_xp  ,b.cpcode_check ,b.cpcode01,b.cpcode02,b.cpcode03,b.cpcode_size_en  "+
					" ,(select abs(COALESCE(sum(in_sl),0)) in_sl from e_ck_in ckin where ckin.in_contract=i.in_contract and ckin.in_kfcode=i.in_kfcode and  ckin.in_act in('"+cgthact+"')) thsl ," +
                    " in_contract_detailed " +
					"  from e_ck_in i ,e_js_cpcode b  where i.in_kfcode=b.cpcode_id  and i.in_flag in ('库审','结算')   "+sqlWhere+")t  where (t.in_sl-t.thsl)>0 ";
            return  eCkInDao.QueryToMapPage(baseDto, sql);
		}
	 
		@Override
		public Result getFlag(String inNote, String flag) {
			String flagString=eCkInDao.getFlag(inNote);
			if(flag.equals(flagString)) {
				return  Result.resultOk("操作成功！");
			}
			return  Result.error("该单不是“"+flag+"”状态,不能操作！");
		}
		
		@Override
		public Result setFlag(String inNote, String flag) {
			eCkInDao.setFlag(inNote,flag);
			//财务应收应付同步状态
			if("库审,结算".indexOf(flag)>0) {
				eCwYsyfDao.setFlag(inNote, flag);
			}
			return  Result.resultOk("操作成功！");
		}
		
		
}
