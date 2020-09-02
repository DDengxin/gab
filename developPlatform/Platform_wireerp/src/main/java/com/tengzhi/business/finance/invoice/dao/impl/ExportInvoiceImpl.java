package com.tengzhi.business.finance.invoice.dao.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.security.common.model.SessionUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.invoice.dao.ExportInvoiceDao;

import cn.hutool.core.util.ObjectUtil;

@Repository
public class ExportInvoiceImpl extends BasicsDaoImpl<Object, String> implements ExportInvoiceDao {

	@Override
	public Result find(BaseDto dto) {
		Map<String, String> map = dto.getParamsMap();
		String str = map.get("nickName");
		String where = SqlJoint.where(e -> {
			e.eq("nick_name", str);
		}, true);
		return super.QueryPageList("select * from sys_demo_test" + where, null, dto);
	}
	
	@Override
	public List findList(BaseDto dto) {
		Map<String, String> map = dto.getParamsMap();
		Object srchRq1 = map.get("srchRq1");
		Object srchRq2 = map.get("srchRq2");
		Object fpNote = map.get("fpNote");
		Object fpDw = map.get("fpDw");
		Object fpSl = map.get("fpSl");
		Object fpBz = map.get("fpBz");
		Object fpFlag = map.get("fpFlag");
		Object fpMonth = map.get("fpMonth");
		Object fpDtype = map.get("fpDtype");
		
		String sqlWhere = " where fp_dtype='" + fpDtype + "' ";
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
		String sql = "select fp_note \"fpNote\",fp_rq \"fpRq\",fp_sl \"fpSl\",fp_se \"fpSe\",fp_je \"fpJe\",fp_sje \"fpSje\",fp_flag \"fpFlag\",fp_month \"fpMonth\",f_get_param_name('发票类型',fp_type,'"+   SessionUser.getCurrentCorpId()   +"','') \"fpType\",f_get_param_name('交易币种',fp_bz,'"+   SessionUser.getCurrentCorpId()   +"','') \"fpBz\",f_getname('GETDWEXP', fp_dw, '', data_corp) \"fpDw\", "
				+ "fp_sm \"fpSm\" ,fp_id \"fpId\" from  e_cw_fp " + sqlWhere +" group by fp_rq,fp_note,fp_dw,fp_dtype,fp_type,fp_sl,fp_se,fp_je,fp_sje,fp_flag,fp_month,fp_xtype,fp_sm,data_man,data_date,data_corp,fp_id ,fp_bz ";
	    return super.queryToMapTransformers(sql);
	}

}
