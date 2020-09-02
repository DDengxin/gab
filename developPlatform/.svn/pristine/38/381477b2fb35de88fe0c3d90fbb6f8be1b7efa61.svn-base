package com.tengzhi.business.sale.saleStatistics.orderStatistics.dao.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.business.base.common.ProductType;
import com.tengzhi.business.cg.da.sysCustomer.service.SysCustomerService;
import org.springframework.stereotype.Repository;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.sale.saleStatistics.orderStatistics.dao.ExportXsDao;

import cn.hutool.core.util.ObjectUtil;

@Repository
public class ExportXsImpl extends BasicsDaoImpl<Object, String> implements ExportXsDao {

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
	public List findList(BaseDto baseDto) throws IOException {
		Map<String, String> map = null;
		map = baseDto.getParamsMap();
		SessionUser sessionUser=SessionUser.SessionUser();
		//通过枚举获取产品类型(默认为物料)
		/*
		 * ProductType productType =
		 * ProductType.valueOfParamId(map.get("stype"),ProductType.WL);
		 * 
		 * String sqlWhere =
		 * " where cw_type = '"+map.get("stype")+"' and data_corp='"+sessionUser.
		 * getCorpId()+"' and cw_stype='XS' ";
		 * if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
		 * sqlWhere+=" and cw_rq >='"+map.get("srchRq1")+"'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
		 * sqlWhere+=" and cw_rq <='"+map.get("srchRq2")+"'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
		 * sqlWhere+=" and cw_note like '%"+map.get("srchNo")+"%'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchCustromer"))) {
		 * sqlWhere+=" and cw_dw = '"+map.get("srchCustromer")+"'"; } String srchName =
		 * map.get("srchName").replace(",", "','"); if(!"无".equals(srchName)) {
		 * if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
		 * sqlWhere+=" and cw_code in ('"+srchName+"')"; } }
		 * if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
		 * sqlWhere+=" and cw_code like '%"+map.get("srchCode")+"%'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchSize"))) {
		 * sqlWhere+=" and cpcode_size like '%"+map.get("srchSize")+"%'"; } String
		 * srchFl = map.get("srchFl").replace(",", "','");
		 * if(ObjectUtil.isNotEmpty(map.get("srchFl"))) {
		 * sqlWhere+=" and cpcode_fl in ('"+srchFl+"')"; }
		 * if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
		 * sqlWhere+=" and cw_type = '"+map.get("htStype")+"'"; } //业务员权限过滤
		 * sqlWhere+=" and cw_dw = " +
		 * SysCustomerService.getBusinessIdsWhereCustomerSqlFragment(map.get("srchYwy"))
		 * ;
		 */
		/*if(ObjectUtil.isNotEmpty(map.get("srchYwy"))) {
			String businessPersonnelIdsSqlFragment = SessionUser.SessionUser().getBusinessPersonnelIdsSqlFragment(map.get("srchYwy"));
			if(null != businessPersonnelIdsSqlFragment) {
				sqlWhere += " and cw_dw in (select customer_id from (select customer_id,f_getname('GETCUSTOMERBUYER',cw_dw,'',data_corp) ywy from sys_customer) t where ywy in (" + businessPersonnelIdsSqlFragment + ") )";
			}
		}*/
		/*
		 * if(ObjectUtil.isNotEmpty(map.get("srchLib"))) {
		 * sqlWhere+=" and cw_lib = '"+map.get("srchLib")+"'"; }
		 * if(ObjectUtil.isNotEmpty(map.get("srchFpFlag"))) {
		 * if("已开票".equals(map.get("srchFpFlag"))) { sqlWhere+=" and cw_fph <> 'N'";
		 * }else { sqlWhere+=" and cw_fph = 'N'"; } } String sql =
		 * "select to_char(cw_rq,'yyyy-MM-dd') \"cwRq\",cw_note \"cwNote\",cw_dw \"cwDw\",f_getname('GETDWEXP', cw_dw, '', '"
		 * +sessionUser.getCorpId()
		 * +"') \"deptName\",cw_lib \"cwLib\",f_get_param_name('库房档案',cw_lib,'"+   SessionUser.getCurrentCorpId()   +"','仓库',false) \"libName\",cw_code \"cwCode\",cpcode_name \"cpcodeName\",cpcode_size \"cpcodeSize\",cpcode_size_en \"cpcodeSizeEn\",cpcode_fl \"cpcodeFl\",f_get_param_name('"
		 * +productType.getProductCategoryParamType()
		 * +"', cpcode_fl, '技术', false) \"flName\",COALESCE(cw_sl,0) cw_sl,COALESCE(cw_sje,0) cw_sje,cw_bz \"cwBz\",f_get_param_name('交易币种',cw_bz,'"+   SessionUser.getCurrentCorpId()   +"','财务',false) \"bzName\",cw_act \"cwAct\",f_get_param_name('仓库收发',cw_act,'"+   SessionUser.getCurrentCorpId()   +"') \"actName\",cw_flag \"cwFlag\",cw_type \"cwType\",cw_stype \"cwStype\" from v_cw_ysyf "
		 * +sqlWhere; if("单号".equals(map.get("reqType"))) { sql =
		 * "select \"cwRq\",\"cwNote\",\"deptName\" \"cwDw\",sum(cw_sl) sl,sum(cw_sje) je,\"bzName\" \"cwBz\",\"actName\" \"cwAct\",\"cwType\",\"cwStype\",\"cwFlag\" from ("
		 * +sql+") t group by \"cwRq\",\"cwNote\",\"cwDw\",\"cwBz\",\"cwAct\",\"cwType\",\"cwStype\",\"cwFlag\",\"actName\",\"bzName\",\"deptName\""
		 * ; }else if("产品".equals(map.get("reqType"))) { sql =
		 * "select \"flName\" \"cpcodeFl\",\"cwCode\",\"cpcodeName\",\"cpcodeSize\",\"cpcodeSizeEn\",sum(cw_sl) sl,sum(cw_sje) je,\"bzName\" \"cwBz\" from ("
		 * +sql+") t group by \"cpcodeFl\",\"flName\",\"cwCode\",\"cpcodeName\",\"cpcodeSize\",\"cpcodeSizeEn\",\"cwBz\",\"bzName\" order by \"cpcodeFl\",\"cpcodeName\",\"cpcodeSize\",\"cpcodeSizeEn\""
		 * ; }else if("客户".equals(map.get("reqType"))) { sql =
		 * "select \"deptName\" \"cwDw\",sum(cw_sl) sl,sum(cw_sje) je,\"bzName\" \"cwBz\" from ("
		 * +sql+") t group by \"cwDw\",\"cwBz\",\"deptName\",\"bzName\""; }else
		 * if("业务员".equals(map.get("reqType"))) { sql =
		 * "select \"deptName\" \"cwDw\",sum(cw_sl) sl,sum(cw_sje) je,\"bzName\" \"cwBz\" from ("
		 * +sql+") t group by \"cwDw\",\"cwBz\",\"deptName\",\"bzName\""; }else
		 * if("分类".equals(map.get("reqType"))) { sql =
		 * "select \"flName\" \"cpcodeFl\",sum(cw_sl) sl,sum(cw_sje) je,\"bzName\" \"cwBz\" from ("
		 * +sql+") t group by \"cpcodeFl\",\"cwBz\",\"flName\",\"bzName\""; }else
		 * if("币种".equals(map.get("reqType"))) { sql =
		 * "select \"bzName\" \"cwBz\",sum(cw_sl) sl,sum(cw_sje) je from ("
		 * +sql+") t group by \"cwBz\",\"bzName\""; }else
		 * if("库房".equals(map.get("reqType"))) { sql =
		 * "select \"libName\" \"cwLib\",sum(cw_sl) sl,sum(cw_sje) je,\"bzName\" \"cwBz\" from ("
		 * +sql+") t group by \"cwLib\",\"libName\",\"cwBz\",\"bzName\""; }else
		 * if("业务方式".equals(map.get("reqType"))) { sql =
		 * "select \"actName\" \"cwAct\",sum(cw_sl) sl,sum(cw_sje) je,\"bzName\" \"cwBz\" from ("
		 * +sql+") t group by \"cwAct\",\"cwBz\",\"actName\",\"bzName\""; }else
		 * if("默认".equals(map.get("reqType"))) { try { sql = defaultSql(dto); } catch
		 * (IOException e) { e.printStackTrace(); } }
		 * 
		 * 
		 */
		//Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		String sqlWhere = " and  cw_type = '"+map.get("stype")+"' and a.data_corp='"+securityUser.getCorpId()+"' and cw_stype='XS' ";
		//String sqlWhere = " and  cw_type ='CP' ";
		
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			sqlWhere+=" and cw_rq >='"+map.get("srchRq1")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			sqlWhere+=" and cw_rq <='"+map.get("srchRq2")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
			sqlWhere+=" and cw_note like '%"+map.get("srchNo")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCustromer"))) {
			sqlWhere+=" and cw_dw = '"+map.get("srchCustromer")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
			sqlWhere+=" and cpcode_name = '"+map.get("srchName")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
			sqlWhere+=" and cw_code like '%"+map.get("srchCode")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchSize"))) {
			sqlWhere+=" and cpcode_size like '%"+map.get("srchSize")+"%'";
		}
		String srchFl = map.get("srchFl").replace(",", "','");
		if(ObjectUtil.isNotEmpty(map.get("srchFl"))) {
			sqlWhere+=" and cpcode_fl in ('"+srchFl+"')";
		}
		if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
			//sqlWhere+=" and cw_type = '"+map.get("htStype")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchYwy"))) {
			//业务员权限过滤(当前登录用户所有业务权限 & 查询业务员)
			sqlWhere+=" and cw_dw = " + SysCustomerService.getBusinessIdsWhereCustomerSqlFragment(map.get("srchYwy"));
		}else{
			//业务员权限过滤(全部)
			sqlWhere+=" and cw_dw = " + SysCustomerService.getBusinessIdsWhereCustomerSqlFragment(null);
		}
		if(ObjectUtil.isNotEmpty(map.get("srchLib"))) {
			sqlWhere+=" and cw_lib = '"+map.get("srchLib")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchFpFlag"))) {
			if("已开票".equals(map.get("srchFpFlag"))) {
				sqlWhere+=" and cw_fph <> 'N'";
			}else {
				sqlWhere+=" and cw_fph = 'N'";
			}
			
		}
		if(ObjectUtil.isNotEmpty(map.get("srchLib"))) {
			sqlWhere+=" and cw_lib = '"+map.get("srchLib")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchItem"))) {
			sqlWhere+=" and cw_item = '"+map.get("srchItem")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("htCustomerAssociatedRemarks"))) {
			sqlWhere+=" and ht_customer_associated_remarks like '%"+map.get("htCustomerAssociatedRemarks")+"%'";
		}
		baseDto.setSortField("cw_rq");
		baseDto.setSortOrder("DESC");
		String sql=" select to_char(cw_rq,'yyyy-MM-dd') \"cwRq\",cw_act \"cwAct\",cw_dw \"cwDw\",cw_item \"cwItem\",cw_code \"cwCode\",cpcode_name \"cpcodeName\",cpcode_size \"cpcodeSize\",cpcode_size_en \"cpcodeSizeEn\",cpcode_xp \"cpcodeXp\",cw_sl \"cwSl\",cw_price \"cwPrice\",cw_sje \"cwSje\",cw_lib \"cwLib\",cw_bz \"cwBz\",cw_fph \"cwFph\",cw_note \"cwNote\",cw_shl \"cwShl\",cw_flag \"cwFlag\",COALESCE(cw_wje,0) \"cwWje\",COALESCE(cw_se,0) \"cwSe\",f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',cw_type,'"+   SessionUser.getCurrentCorpId()   +"') \"flName\",f_get_param_name('仓库收发',cw_act,'"+   SessionUser.getCurrentCorpId()   +"') actname,f_get_param_name('交易币种',cw_bz,'"+   SessionUser.getCurrentCorpId()   +"') bzname,f_getname('GETDWEXP',cw_dw,'',a.data_corp) dwexp,f_getname('GETCUSTOMERBUYER',cw_dw,'',a.data_corp) ywy,f_getname('GETCUSTOMERBUYERID',cw_dw,'',a.data_corp) \"ywyId\",f_get_param_name('库房档案',cw_lib,'"+   SessionUser.getCurrentCorpId()   +"') libname,ht_customer_associated_remarks \"htCustomerAssociatedRemarks\"  from v_cw_ysyf a ,e_xs_contract_detailed b where a.cw_item=ht_no and cw_code=ht_code "+sqlWhere;
	
	
		
		
		
		return super.queryToMapTransformers(sql);
	}

	private String defaultSql(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		String sqlWhere = " where cw_type = '"+map.get("stype")+"' and data_corp='"+securityUser.getCorpId()+"' and cw_stype='XS' ";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			sqlWhere+=" and cw_rq >='"+map.get("srchRq1")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			sqlWhere+=" and cw_rq <='"+map.get("srchRq2")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
			sqlWhere+=" and cw_note like '%"+map.get("srchNo")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCustromer"))) {
			sqlWhere+=" and cw_dw = '"+map.get("srchCustromer")+"'";
		}
		String srchName = map.get("srchName").replace(",", "','");
		if(!"无".equals(srchName)) {
			if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
				sqlWhere+=" and cw_code in ('"+srchName+"')";
			}
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
			sqlWhere+=" and cw_code like '%"+map.get("srchCode")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchSize"))) {
			sqlWhere+=" and cpcode_size like '%"+map.get("srchSize")+"%'";
		}
		String srchFl = map.get("srchFl").replace(",", "','");
		if(ObjectUtil.isNotEmpty(map.get("srchFl"))) {
			sqlWhere+=" and cpcode_fl in ('"+srchFl+"')";
		}
		if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
			sqlWhere+=" and cw_type = '"+map.get("htStype")+"'";
		}
		//业务员权限过滤
		sqlWhere+=" and cw_dw = " + SysCustomerService.getBusinessIdsWhereCustomerSqlFragment(map.get("srchYwy"));
		/*
		if(ObjectUtil.isNotEmpty(map.get("srchYwy"))) {
			String businessPersonnelIdsSqlFragment = SessionUser.SessionUser().getBusinessPersonnelIdsSqlFragment(map.get("srchYwy"));
			if(null != businessPersonnelIdsSqlFragment) {
				sqlWhere += " and cw_dw in (select customer_id from (select customer_id,f_getname('GETCUSTOMERBUYER',cw_dw,'',data_corp) ywy from sys_customer) t where ywy in (" + businessPersonnelIdsSqlFragment + ") )";
			}
		}*/
		if(ObjectUtil.isNotEmpty(map.get("srchLib"))) {
			sqlWhere+=" and cw_lib = '"+map.get("srchLib")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchFpFlag"))) {
			if("已开票".equals(map.get("srchFpFlag"))) {
				sqlWhere+=" and cw_fph <> 'N'";
			}else {
				sqlWhere+=" and cw_fph = 'N'";
			}
		}
		baseDto.setSortField("ht_date");
		baseDto.setSortOrder("DESC");
		String sql=" select to_char(cw_rq,'yyyy-MM-dd') \"cwRq\",cw_item \"cwItem\",cw_code \"cwCode\",cpcode_name \"cpcodeName\",cpcode_size \"cpcodeSize\",cpcode_size_en \"cpcodeSizeEn\",cw_sl \"cwSl\",cw_price \"cwPrice\",cw_sje \"cwSje\",cw_fph \"cwFph\",cw_note \"cwNote\",cw_shl \"cwShl\",cw_flag \"cwFlag\",COALESCE(cw_wje,0) \"cwWje\",COALESCE(cw_se,0) \"cwSe\",f_get_param_name('成品大类',cpcode_fl,'"+   SessionUser.getCurrentCorpId()   +"','技术',false) \"cpcodeFl\",f_get_param_name('仓库收发',cw_act,'"+   SessionUser.getCurrentCorpId()   +"') \"cwAct\",f_get_param_name('交易币种',cw_bz,'"+   SessionUser.getCurrentCorpId()   +"') \"cwBz\",f_getname('GETDWEXP',cw_dw,'',data_corp) \"cwDw\",f_getname('GETCUSTOMERBUYER',cw_dw,'',data_corp) ywy,f_getname('GETCUSTOMERBUYERID',cw_dw,'',data_corp) \"ywyId\",f_get_param_name('库房档案',cw_lib,'"+   SessionUser.getCurrentCorpId()   +"') \"cwLib\"  from v_cw_ysyf "+sqlWhere;
		return sql;
	}
	
}
