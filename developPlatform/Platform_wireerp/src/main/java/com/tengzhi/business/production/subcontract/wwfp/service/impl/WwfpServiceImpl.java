package com.tengzhi.business.production.subcontract.wwfp.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.base.tools.excel.ExcelUtil;
import com.tengzhi.business.base.common.WarehouseActionCaliber;
import com.tengzhi.business.finance.allowance.dao.allowanceDao;
import com.tengzhi.business.finance.allowance.model.allowance;
import com.tengzhi.business.finance.invoice.dao.ExportInvoiceDao;
import com.tengzhi.business.finance.invoice.dao.invoiceDao;
import com.tengzhi.business.finance.invoice.model.invoice;
import com.tengzhi.business.finance.invoice.vo.invoiceVo;
import com.tengzhi.business.production.subcontract.wwfp.service.WwfpService;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.service.SysParamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WwfpServiceImpl implements WwfpService {
	@Autowired
	private invoiceDao invoiceDao;
	@Autowired
	private SysGenNoteService sysGenNoteService;
	@Autowired
	allowanceDao allowanceDao;
	@Autowired
	private ExportInvoiceDao exportDao;
	@Autowired
	private SysParamService sysParamService;

	@Override
	public BasePage<invoice> findAll(BaseDto baseDto) throws IOException, ParseException {
		// TODO Auto-generated method stub
		Map<String, String> map = baseDto.getParamsMap();
		Object srchRq1 = map.get("srchRq1");
		Object srchRq2 = map.get("srchRq2");
		Object fpNote = map.get("fpNote");
		Object fpDw = map.get("fpDw");
		Object fpSl = map.get("fpSl");
		Object fpBz = map.get("fpBz");
		Object fpFlag = map.get("fpFlag");
		Object fpMonth = map.get("fpMonth");
		Object fpDtype = map.get("fpDtype");
		
		//String sqlWhere = " where fp_dtype='" + fpDtype + "' ";
		String sqlWhere = " where fp_dtype='WW' ";
		if (!StringUtils.isBlank(srchRq1.toString())) {
			sqlWhere += " and fp_rq >='" + srchRq1 + "'";
		}
		if (!StringUtils.isBlank(srchRq2.toString())) {
			sqlWhere += " and fp_rq <='" + srchRq2 + "'";
		}
		if (!StringUtils.isBlank(fpNote.toString())) {
			sqlWhere += " and fp_note like'%" + fpNote + "%'";
		}
		if (!StringUtils.isBlank(fpDw.toString())) {
			sqlWhere += " and fp_dw ='" + fpDw + "'";
		}
		//业务员权限过滤(不限制业务权限)
		//sqlWhere += " and fp_dw = " + SysCustomerService.getBusinessIdsWhereCustomerSqlFragment(null);

		if (!StringUtils.isBlank(fpSl.toString())) {
			sqlWhere += " and fp_sl =" + fpSl + "";
		}
		if (!StringUtils.isBlank(fpBz.toString())) {
			sqlWhere += " and fp_bz ='" + fpBz + "'";
		}
		if (!StringUtils.isBlank(fpDw.toString())) {
			sqlWhere += " and fp_dw ='" + fpDw + "'";
		}
		if (!StringUtils.isBlank(fpFlag.toString())) {
			sqlWhere += " and fp_flag ='" + fpFlag + "'";
		}
		if(ObjectUtil.isNotEmpty(map.get("fpMonth"))) {
			sqlWhere+=" and fp_month ='"+map.get("fpMonth")+"' ";
		}
		
		if(ObjectUtil.isNotEmpty(map.get("fpCgtype"))) {
			if ("ALL".equals(map.get("fpCgtype"))) {
				  sqlWhere += " "; 
			  }else { 
				  sqlWhere +=" and fp_cgtype ='" + map.get("fpCgtype") + "'";
			  }
		}
		 
		String sql = "select fp_note ,fp_rq ,fp_dw ,fp_dtype ,fp_type ,fp_sl ,fp_se ,fp_je ,fp_sje ,fp_flag ,fp_month ,f_get_param_name('发票类型',fp_type,'"+   SessionUser.getCurrentCorpId()   +"','') fplx,f_get_param_name('交易币种',fp_bz,'"+   SessionUser.getCurrentCorpId()   +"','') fpbzs,f_getname('GETDWEXP', fp_dw, '', data_corp) fpdws, "
				+ "fp_xtype ,fp_sm ,data_man ,data_date ,data_corp ,fp_id ,fp_bz   "
				+ " from  e_cw_fp " + sqlWhere
				+ "  group by fp_rq,fp_note,fp_dw,fp_dtype,fp_type,fp_sl,fp_se,fp_je,fp_sje,fp_flag,fp_month,fp_xtype,fp_sm,data_man,data_date,data_corp,fp_id ,fp_bz ";
		return invoiceDao.QueryPageLists(baseDto,sql);
	}

	@Override
	public BasePage<allowance> findAllByAdd(BaseDto baseDto) throws IOException, ParseException {
		// TODO Auto-generated method stub
		Map<String, String> map = baseDto.getParamsMap();
		String srchrq1 = map.get("srchRq1");
		String srchrq2 = map.get("srchRq2");
		String cwStype = map.get("cwStype");
		String fpCgtype = map.get("fpCgtype");
		String fpBz = map.get("fpBz");
		String fpSl = map.get("fpSl");
		String fpDw = map.get("fpDw");
		String action = map.get("action");
		String fpNote = map.get("fpNote");
		String sqlWhere = "   ";
		if (!StringUtils.isBlank(cwStype)) {
			sqlWhere += " and cw_stype ='" + cwStype + "'";
		}
		
		if ("add".equals(action)) {
			sqlWhere += " and  cw_fph='N'  ";
		}else {
			sqlWhere += " and  cw_fph='"+fpNote+"'  ";
		}
		
	
		if (!StringUtils.isBlank(srchrq1)) {
			sqlWhere += " and cw_rq >='" + srchrq1 + "'";
		}
		if (!StringUtils.isBlank(srchrq2)) {
			sqlWhere += " and cw_rq <='" + srchrq2 + "'";
		}
		if (!StringUtils.isBlank(fpDw)) {
			sqlWhere += " and cw_dw ='" + fpDw + "'";
		}
		if (!StringUtils.isBlank(fpBz)) {
			sqlWhere += " and cw_bz ='" + fpBz + "'";
		}
		if (!StringUtils.isBlank(fpSl)) {
			sqlWhere += " and cw_shl ='" + fpSl + "'";
		}
		if (!StringUtils.isBlank(fpCgtype)){
			sqlWhere += " and cw_type ='" + fpCgtype + "'";
			sqlWhere +=" and cw_act in (select param_id from sys_param where param_name in ('"+ WarehouseActionCaliber.wwrk+"','"+ WarehouseActionCaliber.wwck+"') ) ";
		}else{
			String paramIds = "";
			List<Map> list = invoiceDao.QueryListMap(" select param_id from Sys_param where param_type='仓库收发' and param_name='"+ WarehouseActionCaliber.wwrk+"'  ");
			if(list.size() > 0){
				for(int i = 0;i<list.size();i++){
					Map pmap = list.get(i);
					paramIds += pmap.get("param_id").toString();
					if(i+1<list.size()){
						paramIds += "','";
					}
				}
			}
			List<Map> cklist = invoiceDao.QueryListMap(" select param_id from Sys_param where param_type='仓库收发' and param_name='"+ WarehouseActionCaliber.wwck+"'  ");
			if(cklist.size() > 0){
				for(int i = 0;i<cklist.size();i++){
					Map pmap = cklist.get(i);
					paramIds += pmap.get("param_id").toString();
					if(i+1<list.size()){
						paramIds += "','";
					}
				}
			}
			if(paramIds.trim().length() != 0){
				sqlWhere +=" and cw_act in ('"+paramIds+"')  ";
			}
		}
		String sql = "select cw_note,cw_rq,cw_act,cw_item,cw_code,cw_type,cw_js,cw_sl,cw_zl,cw_price,cw_sje,cw_se,cw_ck,cw_bz,cw_hl,cw_shl,cw_flag,cpcode_name , cpcode_size,(cw_sje-cw_se) cw_wse,f_get_param_name('交易币种',cw_bz,'"+   SessionUser.getCurrentCorpId()   +"','') cw_bz_name,   "
				+ " f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cw_code_name,f_get_param_name('仓库收发',cw_act,'"+   SessionUser.getCurrentCorpId()   +"','') cw_act_name"
				+ " from  e_cw_ysyf a, e_js_cpcode b  where  a.cw_code=b.cpcode_id and cw_flag='确认' "+ sqlWhere
				+ "  group by cw_note,cw_rq,cw_act,cw_item,cw_code,cw_type,cw_js,cw_sl,cw_zl,cw_price,cw_sje,cw_se,cw_ck,cw_bz,cw_hl,cw_shl,cw_flag,cpcode_name ,cpcode_size ,cpcode_type";
		sql = "select * from (" + sql + ") n ";
		String countSql = "select count(*) cn from (" + sql + ")t ";
		return allowanceDao.QueryPageLists(sql, countSql, baseDto);
	}

	@Override
	public Result save(invoiceVo invoiceVo) throws Exception {
		SessionUser securityUser=SessionUser.SessionUser();
		if (judgeUnique(invoiceVo.getInvoice())) {
			invoiceVo.getInvoice().setFpFlag("登记");
			invoiceVo.getInvoice().setFpMonth("未结");
			invoiceVo.getInvoice().setFpId(UUIDUtil.uuid());
			invoiceVo.getInvoice().setDataMan(securityUser.getUserId());
			invoiceVo.getInvoice().setDataDate(new Date());
			invoiceVo.getInvoice().setDataCorp(securityUser.getCorpId());
			invoiceVo.getInvoice().setFpDtype("WW");// TODO 暂时写死类型委外
			if (!invoiceVo.geteCgContractDetailed().isEmpty()) {
				invoiceVo.geteCgContractDetailed().forEach(item -> {
					allowanceDao.okAndFph(item.getCwNote(), item.getCwCode(), invoiceVo.getInvoice().getFpNote());
				});
			}
			invoice invoice = invoiceDao.save(invoiceVo.getInvoice());
			return Result.resultOk(invoice);
		} else {
			return Result.error("发票号已存在！");
		}
	}

	@Override
	public void update(invoiceVo invoiceVo) throws Exception {
		SessionUser securityUser=SessionUser.SessionUser();
		invoiceVo.getInvoice().setDataMan(securityUser.getUserId());
		invoiceVo.getInvoice().setDataDate(new Date());
		invoiceVo.getInvoice().setDataCorp(securityUser.getCorpId());
		invoiceVo.getInvoice().setFpDtype("WW");// TODO 暂时写死类型委外
		allowanceDao.allQx(invoiceVo.getInvoice().getFpNote());
		if (!invoiceVo.geteCgContractDetailed().isEmpty()) {
			invoiceVo.geteCgContractDetailed().forEach(item -> {
				allowanceDao.allok(item.getCwNote(), item.getCwCode(), invoiceVo.getInvoice().getFpNote());
			});
		}
		invoiceDao.dynamicSave(invoiceVo.getInvoice(), invoiceDao.findByFpId(invoiceVo.getInvoice().getFpId()));
	}


	@Override
	public void deleteByFpId(String fpId) {
		invoiceDao.updateYsyf(fpId);
		invoiceDao.deleteById(fpId);
	}

	@Override
	public boolean judgeUnique(invoice invoice) {
		return invoiceDao.findAll(Specifications.where((c) -> {
			c.eq("fpNote", invoice.getFpNote());
		})).size() <= 0;
	}

	@Override
	public invoice findByFpId(String fpId) {
		invoice in=invoiceDao.findByFpId(fpId);
		in.setFpdws(invoiceDao.getCorpName(in.getFpDw(), in.getDataCorp()));
		return in;
	}

	@Override
	public void ok(String fpId) {
		// TODO Auto-generated method stub
		invoiceDao.ok(fpId);

	}

	@Override
	public void yok(String fpId) {
		// TODO Auto-generated method stub
		invoiceDao.yok(fpId);

	}

	@Override
	public void qx(String fpId) {
		// TODO Auto-generated method stub
			invoiceDao.qx(fpId);

	}

	@Override
	public void yqx(String fpId) {
		// TODO Auto-generated method stub
		invoiceDao.yqx(fpId);

	}

	@Override
	public List<SelectVo> getHtFplx(String trueText, String falseText) {
		return comboParam("财务", "FPLX");
	}

	@Override
	public List<SelectVo> getHtFpsl(String trueText, String falseText) {
		return comboParams("财务", "FPSL");
	}
	
	@Override
	public List<SelectVo> getCplx(String trueText, String falseText) {
		return comboParam("技术", "CPDL");
	}

	public List<SelectVo> comboParam(String mod, String pareatId) {
		List<SelectVo> voList = new ArrayList<SelectVo>();
		Object[] val = { mod, pareatId };
		String sql = "select param_id,param_name from sys_param  where param_status='true' and param_mod=?  and parent_id=? order by  param_ord ";
		List<Map> mapList = invoiceDao.QueryListMap(sql, val);
		mapList.forEach(Map -> {
			voList.add(new SelectVo(Map.get("param_id"), String.valueOf(Map.get("param_name"))));
		});
		return voList;
	}

	public List<SelectVo> comboParams(String mod, String pareatId) {
		List<SelectVo> voList = new ArrayList<SelectVo>();
		Object[] val = { mod, pareatId };
		String sql = "select param_value,param_name from sys_param  where param_status='true' and param_mod=?  and parent_id=? order by  param_ord ";
		List<Map> mapList = invoiceDao.QueryListMap(sql, val);
		mapList.forEach(Map -> {
			voList.add(new SelectVo(Map.get("param_value"), String.valueOf(Map.get("param_name"))));
		});
		return voList;
	}

	@Override
	public void exportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
		//获取ExcelUitl实例
		ExcelUtil util = ExcelUtil.getInstance();
		//初始化dto对象
		BaseDto dto = BaseDto.ValueOfRequest(request);
		//将导出的页面定义为0
		dto.setPageIndex(0);
		//查询数据并且生成Excel
		util.ExcelToWeb(request, "应付发票", response, exportDao.findList(dto));
	}
	
}
