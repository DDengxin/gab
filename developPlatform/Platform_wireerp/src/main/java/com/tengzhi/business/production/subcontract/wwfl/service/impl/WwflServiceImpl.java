package com.tengzhi.business.production.subcontract.wwfl.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.app.ck.model.DeliveryNotice;
import com.tengzhi.app.ck.model.DeliveryNotice.DeliveryNotice_PK;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.base.common.WarehouseActionCaliber;
import com.tengzhi.business.production.subcontract.wwfl.dao.WwflDao;
import com.tengzhi.business.production.subcontract.wwfl.service.WwflService;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.vo.ECkDeliveryNoticeVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.service.SysParamService;

@Service
@Transactional
public class WwflServiceImpl implements WwflService {

	@Autowired
	private WwflDao wwflDao;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;
	
	@Autowired
	private SysParamService sysParamService;

	@Override
	public BasePage<Map<String, Object>> getTopList(BaseDto dto) throws IOException {
		Map<String, String> map = dto.getParamsMap();
		SessionUser sessionUser = SessionUser.SessionUser();
		String where = " and fh_act in (select param_id from sys_param where param_name ='" + WarehouseActionCaliber.wwck + "' )    ";
		where += SqlJoint.whereSuffixAnd((c) -> {
			c.eq("data_corp", sessionUser.getCorpId(), true);
			c.ge("fh_rq", map.get("srchRq1"));
			c.le("fh_rq", map.get("srchRq2"));
			c.eq("fh_type", map.get("fhType"));
			c.eq("fh_flag", map.get("fhFlag"));
			c.contains("fh_note", map.get("fhNote"));
			c.contains("fh_contract", map.get("fhContract"));
			c.contains("fh_customer", map.get("srchCustomer"));
		});
		String sql = " select fh_rq,fh_note,fh_contract,f_getname ( 'GETDWNAME', fh_customer, '', data_corp ) fh_customer_name,f_get_param_name('仓库收发',fh_act,'"+   SessionUser.getCurrentCorpId()   +"') fh_act_name,sum(abs(fh_sl)) fh_sl,fh_flag,fh_type  "
				+ " from e_ck_delivery_notice "
				+ " where 1=1 "+ where +
				" group by fh_rq,fh_note,fh_customer,data_corp,fh_act,fh_flag,fh_type,fh_contract ";
		return wwflDao.QueryMapPageList(dto, sql + " order by fh_rq desc ", true);
	}

	@Override
	public BasePage<Map<String, Object>> getBottomLeftList(BaseDto dto) throws IOException {
		Map<String, String> map = dto.getParamsMap();
		SessionUser sessionUser = SessionUser.SessionUser();
		String sql = " select fh_type,fh_lib,"
				+ "f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,"
				+ "f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name, "
				+ " f_getparamname('GETCPCODEXP',cpcode_xp,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name,"
				+ "f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,"
				+ "f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl_name,"
				+ "cpcode_size ,fh_sl,fh_ceiling,fh_lower,fh_contract,fh_code, "
				+ " coalesce((select sum(out_sl) from e_ck_out where data_corp='" + sessionUser.getCorpId()
				+ "' and  out_code= notice.fh_code and out_note= notice.fh_note),0.000) sjfh,  "
				+ " f_getname('GETGXANDLIBNAME',fh_lib,fh_type,'') fh_lib_name,fh_ph,fh_remarks "
				+ " from e_ck_delivery_notice notice "
				+ " left join e_js_cpcode cpcode on notice.fh_code = cpcode.cpcode_id where  notice.data_corp='"
				+ sessionUser.getCorpId() + "' ";
		sql += SqlJoint.start().eq("fh_note", map.get("fhNote"), true)
				.getAndSuffixSqlStr();
		return wwflDao.QueryMapPageList(dto, sql, true);
	}


	@Override
	public BasePage<Map<String, Object>> getDemandNotifyListById(String fhNote) {
		SessionUser sessionUser = SessionUser.SessionUser();
		String sql = " select notice.fh_note, notice.fh_customer, notice.fh_lib, notice.fh_contract, notice.fh_code, notice.fh_sl, notice.fh_ceiling, notice.fh_lower, to_char(notice.fh_rq,'yyyy-MM-dd') fh_rq,notice.fh_act,\n"
				+ "notice.fh_type, notice.fh_flag, notice.fh_tax, notice.fh_bz, notice.fh_hs, notice.fh_price, notice.fh_remarks, notice.ht_mo, notice.fh_ph, "
				+ " cpcode.cpcode_name, cpcode.cpcode_size, "
				+ " f_getname ( 'GETDWNAME', notice.fh_customer, '', notice.data_corp ) fh_customer_name, "
				+ " f_getparamname('GETBYCPCODEFL',cpcode.cpcode_fl,'','技术',cpcode.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl, "
				+ " f_getname ( 'GETGXANDLIBNAME', notice.fh_lib, notice.fh_type, '' ) fh_lib_name, "
				+ " f_getparamname('GETBYPARENTID',cpcode.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"') cpcode_bz "
				+ " from e_ck_delivery_notice notice "
				+ " left join e_js_cpcode cpcode on notice.fh_code = cpcode.cpcode_id " + " where 1=1 ";
		sql += SqlJoint.start().eq("notice.data_corp", sessionUser.getCorpId()).eq("fh_note", fhNote).getAndSuffixSqlStr();
		BaseDto dto = new BaseDto();
		dto.setPageIndex(0);
		dto.setPageSize(999999);
		return wwflDao.QueryMapPageList(dto, sql, true);
	}


	@Override
	public Result update(ECkDeliveryNoticeVo vo) throws Exception {
		String flag = wwflDao.getFlag(vo.getDeliveryNotice().getFhNote());
		if(!"登记".equals(flag)) {
			return Result.error("【"+flag+"】状态不能进行修改操作！");
		}
		
		List<DeliveryNotice> addnotice = new ArrayList<DeliveryNotice>();
		List<DeliveryNotice> modifyedNotice = new ArrayList<DeliveryNotice>();
		List<DeliveryNotice> deletedNotice = new ArrayList<DeliveryNotice>();

		SessionUser securityUser = SessionUser.SessionUser();
		String parameterId = wwflDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
				, WarehouseActionCaliber.wwck,vo.getDeliveryNotice().getFhType(),securityUser.getCorpId());
		vo.getDeliveryNotice().setFhFlag("登记");
		vo.getDeliveryNotice().setDataMan(securityUser.getUserId());
		vo.getDeliveryNotice().setDataRq(new Date());
		vo.getDeliveryNotice().setDataCorp(securityUser.getCorpId());
		vo.getDeliveryNotice().setFhAct(parameterId);
		//新增行
		for (int i = 0; i < vo.getAddedList().size(); i++) {
			DeliveryNotice model = vo.getAddedList().get(i); 
			model.setFhNote(vo.getDeliveryNotice().getFhNote());
			model.setFhCustomer(vo.getDeliveryNotice().getFhCustomer());
			model.setFhLib(vo.getDeliveryNotice().getFhLib());
			model.setFhContract(vo.getDeliveryNotice().getFhContract());
			model.setDataMan(vo.getDeliveryNotice().getDataMan());
			model.setDataCorp(vo.getDeliveryNotice().getDataCorp());
			model.setDataRq(vo.getDeliveryNotice().getDataRq());
			model.setFhAct(vo.getDeliveryNotice().getFhAct());
			model.setFhType(vo.getDeliveryNotice().getFhType());
			model.setFhFlag(vo.getDeliveryNotice().getFhFlag());
			model.setFhTax(vo.getDeliveryNotice().getFhTax());
			model.setFhBz(vo.getDeliveryNotice().getFhBz());
			model.setFhHs(vo.getDeliveryNotice().getFhHs());
			model.setFhPrice(vo.getDeliveryNotice().getFhPrice());
			model.setHtMo(vo.getDeliveryNotice().getHtMo());
			addnotice.add(model);
		}
		
		// 修改
		if (!vo.getModifyedList().isEmpty()) {
			for (int i = 0; i < vo.getModifyedList().size(); i++) {
				DeliveryNotice model = vo.getModifyedList().get(i);
				model.setFhNote(vo.getDeliveryNotice().getFhNote());
				model.setFhCustomer(vo.getDeliveryNotice().getFhCustomer());
				model.setFhLib(vo.getDeliveryNotice().getFhLib());
				model.setFhContract(vo.getDeliveryNotice().getFhContract());
				model.setDataMan(vo.getDeliveryNotice().getDataMan());
				model.setDataCorp(vo.getDeliveryNotice().getDataCorp());
				model.setDataRq(vo.getDeliveryNotice().getDataRq());
				model.setFhAct(vo.getDeliveryNotice().getFhAct());
				model.setFhType(vo.getDeliveryNotice().getFhType());
				model.setFhFlag(vo.getDeliveryNotice().getFhFlag());
				model.setFhTax(vo.getDeliveryNotice().getFhTax());
				model.setFhBz(vo.getDeliveryNotice().getFhBz());
				model.setFhHs(vo.getDeliveryNotice().getFhHs());
				model.setFhPrice(vo.getDeliveryNotice().getFhPrice());
				model.setHtMo(vo.getDeliveryNotice().getHtMo());
				modifyedNotice.add(model);
			}
		} 
		 
		deletedNotice = vo.getDeletedList();
			
		// 业务逻辑判断end //开始保存到数据库
		
		if (!addnotice.isEmpty()){
			addnotice.forEach(model -> {
				wwflDao.save(model);
			});
		}
		if (!modifyedNotice.isEmpty()) {
			modifyedNotice.forEach(model -> {
				wwflDao.dynamicSave(model, wwflDao.findById(new DeliveryNotice_PK(vo.getDeliveryNotice().getFhNote(), model.getFhCode())).orElse(null));
			});
		}
		if (!deletedNotice.isEmpty()) {
			vo.getDeletedList().forEach(model -> {
				DeliveryNotice_PK pk = new DeliveryNotice_PK(vo.getDeliveryNotice().getFhNote(), model.getFhCode());
				wwflDao.deleteById(pk);
			});
		} // end		
		return Result.resultOkMsg("修改成功"); 
	}

	@Override
	public Result save(ECkDeliveryNoticeVo vo) throws Exception {
		
		List<DeliveryNotice> addnoticeList = new ArrayList<DeliveryNotice>();//添加数据
		List<DeliveryNotice> modifyednoticeList = new ArrayList<DeliveryNotice>();
		List<DeliveryNotice> deletednoticeList = new ArrayList<DeliveryNotice>();

		SessionUser securityUser = SessionUser.SessionUser();
		String parameterId = wwflDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
				, WarehouseActionCaliber.wwck,vo.getDeliveryNotice().getFhType(),securityUser.getCorpId());

		if(vo.getDeliveryNotice().getFhNote() == null || vo.getDeliveryNotice().getFhNote().trim().length() == 0) {
			String note = sysGenNoteService.getInOutNote(ECkOut.class,vo.getDeliveryNotice().getFhType(), WarehouseActionCaliber.wwck,securityUser.getCorpId());
			vo.getDeliveryNotice().setFhNote(note);
		}
		vo.getDeliveryNotice().setFhFlag("登记");
		vo.getDeliveryNotice().setDataMan(securityUser.getUserId());
		vo.getDeliveryNotice().setDataRq(new Date());
		vo.getDeliveryNotice().setDataCorp(securityUser.getCorpId());
		vo.getDeliveryNotice().setFhAct(parameterId);
		
		//新增行
		for (int i = 0; i < vo.getAddedList().size(); i++) {
			DeliveryNotice model = vo.getAddedList().get(i); 
			model.setFhNote(vo.getDeliveryNotice().getFhNote());
			model.setFhCustomer(vo.getDeliveryNotice().getFhCustomer());
			model.setFhLib(vo.getDeliveryNotice().getFhLib());
			model.setFhContract(vo.getDeliveryNotice().getFhContract());
			model.setDataMan(vo.getDeliveryNotice().getDataMan());
			model.setDataCorp(vo.getDeliveryNotice().getDataCorp());
			model.setDataRq(vo.getDeliveryNotice().getDataRq());
			model.setFhAct(vo.getDeliveryNotice().getFhAct());
			model.setFhType(vo.getDeliveryNotice().getFhType());
			model.setFhFlag(vo.getDeliveryNotice().getFhFlag());
			model.setFhTax(vo.getDeliveryNotice().getFhTax());
			model.setFhBz(vo.getDeliveryNotice().getFhBz());
			model.setFhHs(vo.getDeliveryNotice().getFhHs());
			model.setFhPrice(vo.getDeliveryNotice().getFhPrice());
			model.setHtMo(vo.getDeliveryNotice().getHtMo());
			addnoticeList.add(model);
		}
		
		// 修改
		if (!vo.getModifyedList().isEmpty()) {
			for (int i = 0; i < vo.getModifyedList().size(); i++) {
				DeliveryNotice model = vo.getModifyedList().get(i);
				model.setFhNote(vo.getDeliveryNotice().getFhNote());
				model.setFhCustomer(vo.getDeliveryNotice().getFhCustomer());
				model.setFhLib(vo.getDeliveryNotice().getFhLib());
				model.setFhContract(vo.getDeliveryNotice().getFhContract());
				model.setDataMan(vo.getDeliveryNotice().getDataMan());
				model.setDataCorp(vo.getDeliveryNotice().getDataCorp());
				model.setDataRq(vo.getDeliveryNotice().getDataRq());
				model.setFhAct(vo.getDeliveryNotice().getFhAct());
				model.setFhType(vo.getDeliveryNotice().getFhType());
				model.setFhFlag(vo.getDeliveryNotice().getFhFlag());
				model.setFhTax(vo.getDeliveryNotice().getFhTax());
				model.setFhBz(vo.getDeliveryNotice().getFhBz());
				model.setFhHs(vo.getDeliveryNotice().getFhHs());
				model.setFhPrice(vo.getDeliveryNotice().getFhPrice());
				model.setHtMo(vo.getDeliveryNotice().getHtMo());
				modifyednoticeList.add(model);
			}
		} 
		 
		deletednoticeList = vo.getDeletedList();
			
		// 业务逻辑判断end //开始保存到数据库
		
		if (!addnoticeList.isEmpty()){
			addnoticeList.forEach(model -> {
				wwflDao.save(model);
			});
		}
		if (!modifyednoticeList.isEmpty()) {
			modifyednoticeList.forEach(model -> {
				wwflDao.dynamicSave(model, wwflDao.findById(new DeliveryNotice_PK(vo.getDeliveryNotice().getFhNote(), model.getFhCode())).orElse(null));
			});
		}
		if (!deletednoticeList.isEmpty()) {
			vo.getDeletedList().forEach(model -> {
				DeliveryNotice_PK pk = new DeliveryNotice_PK(vo.getDeliveryNotice().getFhNote(), model.getFhCode());
				wwflDao.deleteById(pk);
			});
		} // end		
		return Result.resultOk(vo.getDeliveryNotice());
	}

	@Override
	public Result delete(String fhNote) throws Exception {
		String flag = wwflDao.getFlag(fhNote);
		if(!"登记".equals(flag)) {
			return Result.error("【"+flag+"】状态不能进行删除操作！");
		}
		wwflDao.deleteByNote(fhNote);
		return Result.resultOk();
	}

	@Override
	public Result confirm(String fhNote) {
		String flag = wwflDao.getFlag(fhNote);
		if(!"登记".equals(flag)) {
			return Result.error("【"+flag+"】状态不能进行确认操作！");
		}
		wwflDao.updateFlag(fhNote, "确认");
		return Result.resultOkMsg("确认完成！");
	}
	
	@Override
	public Result cancel(String fhNote) {
		String flag = wwflDao.getFlag(fhNote);
		if(!"确认".equals(flag)) {
			return Result.error("【"+flag+"】状态不能进行确认操作！");
		}
		wwflDao.updateFlag(fhNote, "登记");
		return Result.resultOkMsg("取消");
	}
	
	
	@Override
	public ModelAndView table(ModelAndView mv,String fhNote,String fhType) {
		SessionUser securityUser=SessionUser.SessionUser();
		String sql = " select fh_note fhnote,to_char(fh_rq,'yyyy-MM-dd')fhrq,f_getname('GETCORPNAME', '', '', data_corp) corpname,data_corp datacorp,f_get_param_name('产品大类',fh_type,'"+   SessionUser.getCurrentCorpId()   +"','',false) fhtype,f_getname('GETCUSTOMERNAME', fh_customer, '', data_corp) fhcustomername,fh_customer fhcustomer  from e_ck_delivery_notice where fh_note='"+fhNote+"' and data_corp='"+securityUser.getCorpId()+"' ";
		List<Map> table = wwflDao.QueryListMap(sql);
		mv.addObject("fhnote", table.get(0).get("fhnote"));
		mv.addObject("fhrq", table.get(0).get("fhrq"));
		mv.addObject("corpname", table.get(0).get("corpname"));
		mv.addObject("fhtype", table.get(0).get("fhtype"));
		mv.addObject("fhcustomername", table.get(0).get("fhcustomername"));
		sql = " select notice.fh_code,f_getparamname('GETBCPCODENAME',cp.cpcode_name,'','技术',notice.fh_type,'"+   SessionUser.getCurrentCorpId()   +"')||'   '||cp.cpcode_size \"NameAndSize\",round(notice.fh_sl ,2) fhsl,notice.fh_remarks fhremarks from e_ck_delivery_notice notice,e_js_cpcode cp where notice.fh_note = '"+fhNote+"' and notice.fh_code = cp.cpcode_id ";
		List<Map> tableMx = wwflDao.QueryListMap(sql);
		mv.addObject("mx", tableMx);
		double sumsl = 0;
		for(int i = 0 ; i < tableMx.size() ; i++) {
			sumsl += Double.parseDouble(tableMx.get(i).get("fhsl").toString());
		}
		DecimalFormat df = new DecimalFormat("0.00");
		mv.addObject("sumsl", df.format(sumsl));
		mv.addObject("mxCount", tableMx.size());
		
		sql = "select customer_bank customertax from sys_customer where customer_id = '"+table.get(0).get("fhcustomer")+"'";
		List<Map> gF = wwflDao.QueryListMap(sql);
		mv.addObject("gF", gF.get(0).get("customertax"));
		sql = "select corp_tax corptax from sys_org where org_id = '"+table.get(0).get("datacorp")+"'";
		List<Map> xF = wwflDao.QueryListMap(sql);
		mv.addObject("xF", xF.get(0).get("corptax"));
		return mv;
	}
	
	
	
}




