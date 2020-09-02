package com.tengzhi.business.sale.saleStatistics.orderStatistics.dao.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.security.common.model.SessionUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.business.cg.da.sysCustomer.service.SysCustomerService;
import com.tengzhi.business.sale.saleStatistics.orderStatistics.dao.ExportDdDao;

import cn.hutool.core.util.ObjectUtil;

@Repository
public class ExportDdImpl extends BasicsDaoImpl<Object, String> implements ExportDdDao {

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
		/*
		 * Map<String, String> map = dto.getParamsMap(); SessionUser
		 * securityUser=SessionUser.SessionUser(); String sqlWhere =
		 * " a.data_corp='"+securityUser.getCorpId()+"' ";
		 * if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
		 * sqlWhere+=" and ht_date >='"+map.get("srchRq1")+"'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
		 * sqlWhere+=" and ht_date <='"+map.get("srchRq2")+"'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
		 * sqlWhere+=" and ht_no like '%"+map.get("srchNo")+"%'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchCustromer"))) {
		 * sqlWhere+=" and ht_customer = '"+map.get("srchCustromer")+"'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
		 * sqlWhere+=" and ht_code like '%"+map.get("srchCode")+"%'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchHtType"))) {
		 * sqlWhere+=" and ht_type like '%"+map.get("srchHtType")+"%'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchHtItemType"))) {
		 * sqlWhere+=" and ht_item_type like '%"+map.get("srchHtItemType")+"%'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
		 * sqlWhere+=" and cpcode_name = '"+map.get("srchName")+"'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchSize"))) {
		 * sqlWhere+=" and cpcode_size like '%"+map.get("srchSize")+"%'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchFl"))) {
		 * sqlWhere+=" and cpcode_fl = '"+map.get("srchFl")+"'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
		 * sqlWhere+=" and ht_stype = '"+map.get("htStype")+"'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("htCustomerAssociatedRemarks"))) {
		 * sqlWhere+=" and ht_customer_associated_remarks like '%"+map.get(
		 * "htCustomerAssociatedRemarks")+"%' "; } String
		 * sql=" select to_char(a.ht_date,'yyyy-MM-dd') \"htDate\",a.ht_no \"htNo\",a.ht_tax \"htTax\",a.data_corp \"dataCorp\",a.ht_flag \"htFlag\",a.ht_stype \"htStype\",a.ht_mo \"htMo\",a.ht_code \"htCode\",a.cpcode_size \"cpcodeSize\",a.cpcode_size_en \"cpcodeSizeEn\",a.ht_standard \"htStandard\",a.ht_sl \"htSl\",a.ht_price \"htPrice\",a.ht_je \"htJe\",to_char(a.ht_jq,'yyyy-MM-dd') \"htJq\",a.ht_requirements \"htRequirements\",a.ht_sm \"htSm\",a.ht_customer_associated_remarks \"htCustomerAssociatedRemarks\",f_get_param_name('订单类型',ht_type,'"+   SessionUser.getCurrentCorpId()   +"','cn') \"htType\",f_get_param_name('订单类型',ht_item_type,'"+   SessionUser.getCurrentCorpId()   +"','cn') \"htItemType\",f_getname('GETDWEXP',ht_customer,'',a.data_corp) \"htCustomer\",f_get_param_name('交易币种',ht_currency,'"+   SessionUser.getCurrentCorpId()   +"') \"htCurrency\""
		 * +
		 * ",f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')  \"cpcodeName\",f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') \"cpcodeFl\",cpcode_xp \"cpcodeXp\",COALESCE(b.outjs,0) outjs,COALESCE(b.outsl,0) outsl,COALESCE(b.outzl,0) outzl,COALESCE(ht_sl-COALESCE(b.outsl,0),0) cl   "
		 * +
		 * "  from v_xs_ht a left join (select out_contract,out_code,data_corp,sum(out_js) outjs,sum(out_sl) outsl,sum(out_zl) outzl from  e_ck_out  GROUP BY out_contract,out_code,data_corp) b on (a.ht_no=b.out_contract and a.ht_code=b.out_code and a.data_corp=b.data_corp ) where "
		 * +sqlWhere ; return super.queryToMapTransformers(sql);
		 */
		Map<String, String> map = dto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		String sqlWhere = " a.data_corp='"+securityUser.getCorpId()+"' ";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			sqlWhere+=" and ht_date >='"+map.get("srchRq1")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			sqlWhere+=" and ht_date <='"+map.get("srchRq2")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
			sqlWhere+=" and ht_no like '%"+map.get("srchNo")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCustromer"))) {
			sqlWhere+=" and ht_customer = '"+map.get("srchCustromer")+"'";
		}
		//业务员权限过滤
		sqlWhere += " and ht_customer = " + SysCustomerService.getBusinessIdsWhereCustomerSqlFragment(null);

		if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
			sqlWhere+=" and ht_code like '%"+map.get("srchCode")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchHtType"))) {
			sqlWhere+=" and ht_type like '%"+map.get("srchHtType")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchHtItemType"))) {
			sqlWhere+=" and ht_item_type like '%"+map.get("srchHtItemType")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
			sqlWhere+=" and cpcode_name = '"+map.get("srchName")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchSize"))) {
			sqlWhere+=" and cpcode_size like '%"+map.get("srchSize")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchFl"))) {
			sqlWhere+=" and cpcode_fl = '"+map.get("srchFl")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
			sqlWhere+=" and ht_stype = '"+map.get("htStype")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchHtFlag"))) {
			sqlWhere+=" and htmxflag = '"+map.get("srchHtFlag")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("htCustomerAssociatedRemarks"))) {
			sqlWhere+=" and ht_customer_associated_remarks like '%"+map.get("htCustomerAssociatedRemarks")+"%' ";
		}

		dto.setSortField("ht_date");
		dto.setSortOrder("DESC");
		String  sql=" select to_char(a.ht_date,'yyyy-MM-dd') \"htDate\",a.ht_no \"htNo\",a.ht_type \"htType\",a.ht_customer \"htCustomer\",a.ht_currency \"htCurrency\",a.ht_item_type \"htItemType\",a.ht_tax \"htTax\",a.data_corp \"dataCorp\",a.ht_flag \"htFlag\",a.ht_stype \"htStype\",a.ht_mo \"htMo\",a.ht_code \"htCode\",a.cpcode_name \"cpcodeName\",a.cpcode_size \"cpcodeSize\",a.cpcode_size_en \"cpcodeSizeEn\",a.ht_standard \"htStandard\",a.ht_sl \"htSl\",a.ht_price \"htPrice\",a.ht_je \"htJe\",to_char(a.ht_jq,'yyyy-MM-dd') \"htJq\",a.ht_requirements \"htRequirements\",a.ht_sm \"htSm\",a.ht_customer_associated_remarks \"htCustomerAssociatedRemarks\",f_get_param_name('订单类型',ht_type,'"+   SessionUser.getCurrentCorpId()   +"','cn') httypename,f_get_param_name('订单类型',ht_item_type,'"+   SessionUser.getCurrentCorpId()   +"','cn') htitemtypename,f_getname('GETDWEXP',ht_customer,'',a.data_corp) htcustomerexp,f_get_param_name('交易币种',ht_currency,'"+   SessionUser.getCurrentCorpId()   +"') htcurrencyname,htmxflag"
                + ",f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcodename,f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcodefl,cpcode_xp \"cpcodeXp\",COALESCE(b.outjs,0) outjs,COALESCE(b.outsl,0) outsl,COALESCE(b.outzl,0) outzl,COALESCE(ht_sl-COALESCE(b.outsl,0),0) cl   "
                + "  from v_xs_ht a left join (select out_contract,out_code,data_corp,sum(out_js) outjs,sum(out_sl) outsl,sum(out_zl) outzl from  e_ck_out  GROUP BY out_contract,out_code,data_corp) b on (a.ht_no=b.out_contract and a.ht_code=b.out_code and a.data_corp=b.data_corp ) where "+sqlWhere ;
		String countsql="select count(*) from ("+sql+")t";
		
	return	super.queryToMap(sql);//
		
		
	}

}
