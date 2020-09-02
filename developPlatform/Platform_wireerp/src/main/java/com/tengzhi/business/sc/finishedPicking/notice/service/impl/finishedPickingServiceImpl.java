package com.tengzhi.business.sc.finishedPicking.notice.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.app.ck.model.DeliveryNotice;
import com.tengzhi.app.ck.model.DeliveryNotice.DeliveryNotice_PK;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.base.common.WarehouseActionCaliber;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.dao.SaleDeliveryNoticeDao;
import com.tengzhi.business.sale.saleProduct.saleDeliveryNotice.vo.ECkDeliveryNoticeVo;
import com.tengzhi.business.sc.finishedPicking.notice.service.finishedPickingService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.service.SysParamService;

import cn.hutool.core.util.ObjectUtil;

@Service
@Transactional
public class finishedPickingServiceImpl implements finishedPickingService {

	@Autowired
	private SaleDeliveryNoticeDao saleDeliveryNoticeDao;

	@Autowired
	private SysGenNoteService sysGenNoteService;

	@Autowired
	private SysParamService sysParamService;

	@Override
	public BasePage<DeliveryNotice> getSrchTopList(BaseDto baseDto) throws Exception {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlWhere = " where fh_act in (select param_id from sys_param where param_name ='" + WarehouseActionCaliber.scly
				+ "' )    ";
		if (ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			sqlWhere += " and a.fh_rq >='" + map.get("srchRq1") + "' ";
		}
		if (ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			sqlWhere += " and a.fh_rq <='" + map.get("srchRq2") + "' ";
		}
		if (ObjectUtil.isNotEmpty(map.get("srchNote"))) {
			sqlWhere += " and a.fh_note like '%" + map.get("srchNote") + "%'";
		}
		if (ObjectUtil.isNotEmpty(map.get("srchSupplier"))) {
			sqlWhere += " and a.fh_customer = '" + map.get("srchSupplier") + "'";
		}
		if (ObjectUtil.isNotEmpty(map.get("srchFlag"))) {
			sqlWhere += " and a.fh_flag = '" + map.get("srchFlag") + "'";
		}
		if (ObjectUtil.isNotEmpty(map.get("fhType"))) {
			sqlWhere += " and a.fh_type = '" + map.get("fhType") + "'";
		}
		String sql = "select fh_rq,fh_note,f_getname('GETDEPTNAME', 'JD01', null, data_corp)customername,fh_customer,f_get_param_name('仓库收发',fh_act,'"+   SessionUser.getCurrentCorpId()   +"') actname,f_get_param_name('库房档案',fh_lib,'"+   SessionUser.getCurrentCorpId()   +"') libname,fh_lib,sum(fh_sl)fh_sl,fh_act,fh_type,fh_flag,f_getname('GETUSERNAME',data_man,'',data_corp)data_man "
				+ " from e_ck_delivery_notice a " + sqlWhere
				+ " group by fh_rq,fh_note,fh_customer,fh_lib,fh_act,fh_type,data_corp,fh_flag,data_man  ";
		return saleDeliveryNoticeDao.QueryPageLists(baseDto, sql + "  order by fh_rq desc,fh_note desc ");
	}

	@Override
	public BasePage<DeliveryNotice> getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		Object fhNote = map.get("fhNote");
		String sql = "select ck.fh_contract,ck.fh_code,ck.fh_sl,ck.fh_ceiling,ck.fh_lower,cpcode_size,case when fh_type='CP' then cpcode_size_en else f_get_param_name('原料产地',cpcode_size_en,'"+   SessionUser.getCurrentCorpId()   +"')  end    cpcode_size_en,cpcode_xp,f_getparamname('GETBYCPCODEFL',cp.cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl,f_getparamname('GETBYPARENTID',cp.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')   cpcode_bz,f_getparamname('GETBCPCODENAME',cp.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')   cpcode_name,fh_price,fh_hs,fh_price*fh_sl fh_je,fh_tax,fh_bz from e_ck_delivery_notice ck,e_js_cpcode cp where ck.fh_code=cp.cpcode_id and fh_note='"
				+ fhNote + "' ";
		return saleDeliveryNoticeDao.QueryNoPageLists(sql);
	}

	@Override
	public Result save(ECkDeliveryNoticeVo eCkDeliveryNoticeVo) throws Exception {
		List<DeliveryNotice> addECkReNos = new ArrayList<DeliveryNotice>();
		List<DeliveryNotice> modifyedECkReNos = new ArrayList<DeliveryNotice>();
		SessionUser securityUser = SessionUser.SessionUser();
		String parameterId = saleDeliveryNoticeDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
				, WarehouseActionCaliber.scly,eCkDeliveryNoticeVo.getDeliveryNotice().getFhType(),securityUser.getCorpId());
		String note = sysGenNoteService.getInOutNote(ECkOut.class,eCkDeliveryNoticeVo.getDeliveryNotice().getFhType(), WarehouseActionCaliber.scly,securityUser.getCorpId());
		// 新增
		for (int i = 0; i < eCkDeliveryNoticeVo.getAddedList().size(); i++) {
			DeliveryNotice model = eCkDeliveryNoticeVo.getAddedList().get(i);
			model.setFhNote(note);
			model.setFhAct(parameterId);
			model.setFhFlag("登记");
			model.setDataMan(securityUser.getUserId());
			model.setDataRq(new Date());
			model.setDataCorp(securityUser.getCorpId());
			model.setFhCeiling(model.getFhSl().multiply(new BigDecimal("0.05")));// 误差5%
			model.setFhLower(model.getFhSl().multiply(new BigDecimal("-0.05")));
			addECkReNos.add(model);
		}

		for (int i = 0; i < eCkDeliveryNoticeVo.getModifyedList().size(); i++) {
			DeliveryNotice model = eCkDeliveryNoticeVo.getModifyedList().get(i);
			model.setFhNote(note);
			model.setFhAct(parameterId);
			model.setFhFlag("登记");
			model.setDataMan(securityUser.getUserId());
			model.setDataRq(new Date());
			model.setDataCorp(securityUser.getCorpId());
			model.setFhCeiling(model.getFhSl().multiply(new BigDecimal("0.05")));// 误差5%
			model.setFhLower(model.getFhSl().multiply(new BigDecimal("-0.05")));
			modifyedECkReNos.add(model);
		}

		// 开始保存到数据库
		if (!addECkReNos.isEmpty()) {
			addECkReNos.forEach(item -> {
				saleDeliveryNoticeDao.save(item);
			});
		}
		if (!modifyedECkReNos.isEmpty()) {
			modifyedECkReNos.forEach(item -> {
				saleDeliveryNoticeDao.dynamicSave(item,
						saleDeliveryNoticeDao.findById(new DeliveryNotice_PK(note, item.getFhCode())).orElse(null));
			});
		}
		if (!eCkDeliveryNoticeVo.getDeletedList().isEmpty()) {
			eCkDeliveryNoticeVo.getDeletedList().forEach(item -> {
				DeliveryNotice_PK pk = new DeliveryNotice_PK(eCkDeliveryNoticeVo.getDeliveryNotice().getFhNote(),
						item.getFhCode());
				saleDeliveryNoticeDao.deleteById(pk);
			});
		}
		// end
		return Result.resultOk(eCkDeliveryNoticeVo.getDeliveryNotice());
	}

	@Override
	public Result update(ECkDeliveryNoticeVo eCkDeliveryNoticeVo) throws Exception {
		List<DeliveryNotice> addECkReNos = new ArrayList<DeliveryNotice>();
		List<DeliveryNotice> modifyedECkReNos = new ArrayList<DeliveryNotice>();
		SessionUser securityUser = SessionUser.SessionUser();
		String parameterId = saleDeliveryNoticeDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
				, WarehouseActionCaliber.scly,eCkDeliveryNoticeVo.getDeliveryNotice().getFhType(),securityUser.getCorpId());

		// 新增
		for (int i = 0; i < eCkDeliveryNoticeVo.getAddedList().size(); i++) {
			DeliveryNotice model = eCkDeliveryNoticeVo.getAddedList().get(i);
			model.setFhNote(eCkDeliveryNoticeVo.getDeliveryNotice().getFhNote());
			model.setFhAct(parameterId);
			model.setFhFlag("登记");
			model.setDataMan(securityUser.getUserId());
			model.setDataRq(new Date());
			model.setDataCorp(securityUser.getCorpId());
			model.setFhCeiling(model.getFhSl().multiply(new BigDecimal("0.05")));// 误差5%
			model.setFhLower(model.getFhSl().multiply(new BigDecimal("-0.05")));
			addECkReNos.add(model);
		}

		for (int i = 0; i < eCkDeliveryNoticeVo.getModifyedList().size(); i++) {
			DeliveryNotice model = eCkDeliveryNoticeVo.getModifyedList().get(i);
			model.setFhNote(eCkDeliveryNoticeVo.getDeliveryNotice().getFhNote());
			model.setFhAct(parameterId);
			model.setFhFlag("登记");
			model.setDataMan(securityUser.getUserId());
			model.setDataRq(new Date());
			model.setDataCorp(securityUser.getCorpId());
			model.setFhCeiling(model.getFhSl().multiply(new BigDecimal("0.05")));// 误差5%
			model.setFhLower(model.getFhSl().multiply(new BigDecimal("-0.05")));
			modifyedECkReNos.add(model);
		}

		// 开始保存到数据库
		if (!addECkReNos.isEmpty()) {
			addECkReNos.forEach(item -> {
				saleDeliveryNoticeDao.save(item);
			});
		}
		if (!modifyedECkReNos.isEmpty()) {
			modifyedECkReNos.forEach(item -> {
				saleDeliveryNoticeDao
						.dynamicSave(item,
								saleDeliveryNoticeDao
										.findById(new DeliveryNotice_PK(
												eCkDeliveryNoticeVo.getDeliveryNotice().getFhNote(), item.getFhCode()))
										.orElse(null));
			});
		}
		if (!eCkDeliveryNoticeVo.getDeletedList().isEmpty()) {
			eCkDeliveryNoticeVo.getDeletedList().forEach(item -> {
				DeliveryNotice_PK pk = new DeliveryNotice_PK(eCkDeliveryNoticeVo.getDeliveryNotice().getFhNote(),
						item.getFhCode());
				saleDeliveryNoticeDao.deleteById(pk);
			});
		}
		// end
		// 修改表头
		// end
		return Result.resultOk(eCkDeliveryNoticeVo.getDeliveryNotice());
	}

	@Override
	public DeliveryNotice findByInNote(String fhNote) {
		DeliveryNotice in = new DeliveryNotice();
		String sqlString = " select *,f_getname('GETDEPTNAME', 'JD01', null, data_corp)customername from E_Ck_delivery_Notice where fh_note ='"
				+ fhNote + "' ";
		BasePage<DeliveryNotice> list = saleDeliveryNoticeDao.QueryNoPageLists(sqlString);
		if (list.getContent().size() > 0) {
			in = list.getContent().get(0);
		}
		return in;
	}

	@Override
	public void deleteByNote(String fhNote) {
		saleDeliveryNoticeDao.deleteByNote(fhNote);

	}

	@Override
	public Result getFlag(String fhNote, String flag) {
		String flagString = saleDeliveryNoticeDao.getFlag(fhNote);
		if (flag.equals(flagString)) {
			return Result.resultOk("操作成功！");
		}
		return Result.error("该单不是“" + flag + "”状态,不能操作！");
	}

	@Override
	public Result setFlag(String fhNote, String flag) {
		saleDeliveryNoticeDao.setFlag(fhNote, flag);
		return Result.resultOk("操作成功！");
	}

}
