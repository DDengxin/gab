package com.tengzhi.business.base.export.dao.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.base.export.dao.ExportExcelDao;
import com.tengzhi.business.base.common.ProductType;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExportExcelDaoImpl extends BasicsDaoImpl<ECgContract, String> implements ExportExcelDao {

	@Override
	public List getSrchXstjListExportExcel(BaseDto dto) {
		SessionUser securityUser=SessionUser.SessionUser();
		Map<String, String> map = dto.getParamsMap();
		//通过枚举获取产品类型(默认为物料)
		ProductType productType = ProductType.valueOfParamId(map.get("stype"),ProductType.WL);

		String sqlWhere = " where cw_type = '"+map.get("stype")+"' and data_corp='"+securityUser.getCorpId()+"' and cw_stype='CG' ";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			sqlWhere+=" and cw_rq >='"+map.get("srchRq1")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			sqlWhere+=" and cw_rq <='"+map.get("srchRq2")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
			sqlWhere+=" and cw_note params '%"+map.get("srchNo")+"%'";
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
			sqlWhere+=" and cw_type = '"+map.get("htStype")+"'";
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
	
		String  sql=" select *,COALESCE(cw_wje,0) \"cwWje\",COALESCE(cw_se,0) \"cwSe\",f_get_param_name('GETBYCPCODEFL',cpcode_fl,'"+   SessionUser.getCurrentCorpId()   +"','技术',false) \"flName\",f_get_param_name('仓库收发',cw_act,'"+   SessionUser.getCurrentCorpId()   +"') actname,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cw_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcodename,f_get_param_name('交易币种',cw_bz,'"+   SessionUser.getCurrentCorpId()   +"') bzname,f_getname('GETDWEXP',cw_dw,'',data_corp) dwexp,f_getname('GETCUSTOMERBUYER',cw_dw,'',data_corp) ywy,f_getname('GETCUSTOMERBUYERID',cw_dw,'',data_corp) \"ywyId\",f_get_param_name('库房档案',cw_lib,'"+   SessionUser.getCurrentCorpId()   +"') libname  from v_cw_ysyf "+sqlWhere;

		 return super.queryToMap(sql);
	}

	@Override
	public List getCgList(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser sessionUser=SessionUser.SessionUser();
		//通过枚举获取产品类型(默认为物料)
		ProductType productType = ProductType.valueOfParamId(map.get("stype"),ProductType.WL);
		String sqlWhere = " where cw_type = '"+map.get("stype")+"' and data_corp='"+sessionUser.getCorpId()+"' and cw_stype='CG' ";
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
		/*String srchName = map.get("srchName").replace(",", "','");
		if(!"无".equals(srchName)) {
			if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
				sqlWhere+=" and cw_code in ('"+srchName+"')";
			}
		}*/
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
			sqlWhere+=" and cw_type = '"+map.get("htStype")+"'";
		}
		//业务员权限过滤
		//sqlWhere+=" and cw_dw = " + SysCustomerService.getBusinessIdsWhereCustomerSqlFragment(map.get("srchYwy"));
		/*if(ObjectUtil.isNotEmpty(map.get("srchYwy"))) {
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
		String sql = "select to_char(cw_rq,'yyyy-MM-dd') \"cwRq\",cw_note \"cwNote\",cw_dw \"cwDw\",f_getname('GETDWEXP', cw_dw, '', '"+sessionUser.getCorpId()+"') \"deptName\",cw_lib \"cwLib\",f_get_param_name('库房档案',cw_lib,'"+   SessionUser.getCurrentCorpId()   +"','仓库',false) \"libName\",cw_code \"cwCode\",cpcode_name \"cpcodeName\",cpcode_size \"cpcodeSize\",cpcode_size_en \"cpcodeSizeEn\",cpcode_fl \"cpcodeFl\",f_get_param_name('GETBYCPCODEFL',cpcode_fl,'"+   SessionUser.getCurrentCorpId()   +"','技术',false) \"flName\",COALESCE(cw_sl,0) cw_sl,COALESCE(cw_sje,0) cw_sje,cw_bz \"cwBz\",f_get_param_name('交易币种',cw_bz,'"+   SessionUser.getCurrentCorpId()   +"','财务',false) \"bzName\",cw_act \"cwAct\",f_get_param_name('仓库收发',cw_act,'"+   SessionUser.getCurrentCorpId()   +"') \"actName\",cw_flag \"cwFlag\",cw_type \"cwType\",cw_stype \"cwStype\" from v_cw_ysyf "+sqlWhere;
		if("单号".equals(map.get("reqType"))) {
			baseDto.setSortField("cwRq");
			baseDto.setSortOrder("DESC");
			sql = "select \"cwRq\",\"cwNote\",\"cwDw\",\"deptName\",sum(cw_sl) sl,sum(cw_sje) je,\"cwBz\",\"bzName\",\"cwAct\",\"actName\",\"cwType\",\"cwStype\",\"cwFlag\" from ("+sql+") t group by \"cwRq\",\"cwNote\",\"cwDw\",\"cwBz\",\"cwAct\",\"cwType\",\"cwStype\",\"cwFlag\",\"actName\",\"bzName\",\"deptName\"";
		}else if("产品".equals(map.get("reqType"))) {
			baseDto.setSortField("cpcodeFl");
			baseDto.setSortOrder("ASC");
			sql = "select \"cpcodeFl\",\"flName\",\"cwCode\",\"cpcodeName\",\"cpcodeSize\",\"cpcodeSizeEn\",sum(cw_sl) sl,sum(cw_sje) je,\"cwBz\",\"bzName\" from ("+sql+") t group by \"cpcodeFl\",\"flName\",\"cwCode\",\"cpcodeName\",\"cpcodeSize\",\"cpcodeSizeEn\",\"cwBz\",\"bzName\" order by \"cpcodeFl\",\"cpcodeName\",\"cpcodeSize\",\"cpcodeSizeEn\"";
		}else if("供应商".equals(map.get("reqType"))) {
			baseDto.setSortField("je");
			baseDto.setSortOrder("DESC");
			sql = "select \"cwDw\",\"deptName\",sum(cw_sl) sl,sum(cw_sje) je,\"cwBz\",\"bzName\" from ("+sql+") t group by \"cwDw\",\"cwBz\",\"deptName\",\"bzName\"";
		}else if("采购人员".equals(map.get("reqType"))) {
			baseDto.setSortField("je");
			baseDto.setSortOrder("DESC");
			sql = "select \"cwDw\",\"deptName\",sum(cw_sl) sl,sum(cw_sje) je,\"cwBz\",\"bzName\" from ("+sql+") t group by \"cwDw\",\"cwBz\",\"deptName\",\"bzName\"";
		}else if("分类".equals(map.get("reqType"))) {
			baseDto.setSortField("je");
			baseDto.setSortOrder("DESC");
			sql = "select \"cpcodeFl\",\"flName\",sum(cw_sl) sl,sum(cw_sje) je,\"cwBz\",\"bzName\" from ("+sql+") t group by \"cpcodeFl\",\"cwBz\",\"flName\",\"bzName\"";
		}else if("币种".equals(map.get("reqType"))) {
			baseDto.setSortField("je");
			baseDto.setSortOrder("DESC");
			sql = "select \"cwBz\",\"bzName\",sum(cw_sl) sl,sum(cw_sje) je from ("+sql+") t group by \"cwBz\",\"bzName\"";
		}else if("库房".equals(map.get("reqType"))) {
			baseDto.setSortField("je");
			baseDto.setSortOrder("DESC");
			sql = "select \"cwLib\",\"libName\",sum(cw_sl) sl,sum(cw_sje) je,\"cwBz\",\"bzName\" from ("+sql+") t group by \"cwLib\",\"libName\",\"cwBz\",\"bzName\"";
		}else if("业务方式".equals(map.get("reqType"))) {
			baseDto.setSortField("je");
			baseDto.setSortOrder("DESC");
			sql = "select \"cwAct\",\"actName\",sum(cw_sl) sl,sum(cw_sje) je,\"cwBz\",\"bzName\" from ("+sql+") t group by \"cwAct\",\"cwBz\",\"actName\",\"bzName\"";
		}
		return super.queryToMap(sql);
	}

	@Override
	public List statisticsExportExcel(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser sessionUser=SessionUser.SessionUser();
		String sqlWhere=" data_corp='" + sessionUser.getCorpId() + "'";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			sqlWhere+=" and sq_rq >='"+map.get("srchRq1")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			sqlWhere+=" and sq_rq <='"+map.get("srchRq2")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
			sqlWhere+=" and sq_note like '%"+map.get("srchNo")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchDept"))) {
			sqlWhere+=" and sq_dept = '"+map.get("srchDept")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
			sqlWhere+=" and code like '%"+map.get("srchCode")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchSize"))) {
			sqlWhere+=" and cpcode_size like '%"+map.get("srchSize")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchFl"))) {
			sqlWhere+=" and cpcode_fl = '"+map.get("srchFl")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
			sqlWhere+=" and cg_stype = '"+map.get("htStype")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
			sqlWhere+=" and cpcode_name = '"+map.get("srchName")+"'";
		}
		baseDto.setSortField("sq_rq");
		baseDto.setSortOrder("DESC");
		String sql = " SELECT *,"
			+"f_get_param_name('申请类型',sq_type,'"+   SessionUser.getCurrentCorpId()   +"') sq_type_name,f_getname('GETDEPTNAME',sq_dept,'',data_corp) sq_dept_name, "
			+" f_getname('GETUSERNAME',sq_man,'',data_corp) sq_man_name,"
			+"f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name_name,"
			+"f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl_name,"
			+"f_getparamname('GETPARENTID',cpcode_bz,'','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz_name,"
			+"f_getparamname('GETCPCODESIZE',cpcode_size,'','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_size_name,"
			+"f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_size_en_name"
			+" from v_cg_sq where "+sqlWhere;
		return super.queryToMapTransformers(sql);
	}

	@Override
	public List getSrchTopListExportExcel(BaseDto dto) {
Map<String, String> map  = dto.getParamsMap();
		
		SessionUser securityUser=SessionUser.SessionUser();
		String sqlWhere = " a.data_corp='"+securityUser.getCorpId()+"' ";
		
	        if (ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
	            sqlWhere += " and ht_date >='" + map.get("srchRq1") + "'";
	        }
	        if (ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
	            sqlWhere += " and ht_date <='" + map.get("srchRq2") + "'";
	        }
	        if (ObjectUtil.isNotEmpty(map.get("srchNo"))) {
	            sqlWhere += " and ht_no like '%" + map.get("srchNo") + "%'";
	        }
	        if (ObjectUtil.isNotEmpty(map.get("htSupplier"))) {
	            sqlWhere += " and ht_supplier = '" + map.get("htSupplier") + "'";
	        }
	        if (ObjectUtil.isNotEmpty(map.get("htCode"))) {
	            sqlWhere += " and ht_code like '%" + map.get("htCode") + "%'";
	        }
	        if (ObjectUtil.isNotEmpty(map.get("htType"))) {
	            sqlWhere += " and ht_type like '%" + map.get("htType") + "%'";
	        }
	        if (ObjectUtil.isNotEmpty(map.get("cpcodeSize"))) {
	            sqlWhere += " and cpcode_size like '%" + map.get("cpcodeSize") + "%'";
	        }
	        if (ObjectUtil.isNotEmpty(map.get("cpcodeFl"))) {
	            sqlWhere += " and cpcode_fl = '" + map.get("cpcodeFl") + "'";
	        }
	        if (ObjectUtil.isNotEmpty(map.get("cpcodeName"))) {
	            sqlWhere += " and cpcode_name = '" + map.get("cpcodeName") + "'";
	        }
	        if (ObjectUtil.isNotEmpty(map.get("htStype"))) {
	            sqlWhere += " and cg_stype = '" + map.get("htStype") + "'";
	        }
	      
	        String sql = "  select  a.*,f_get_param_name('采购合同',ht_type,'"+   SessionUser.getCurrentCorpId()   +"','cn') ht_type_name,f_getname('GETDWEXP',ht_supplier,'',a.data_corp) ht_supplier_name,f_get_param_name('交易币种',ht_bz,'"+   SessionUser.getCurrentCorpId()   +"') ht_bz_name "
	                + ",f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name_name,f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',cg_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl_name,COALESCE(b.in_js,0) in_js,COALESCE(b.in_sl,0) in_sl,COALESCE(b.in_zl,0) in_zl "
	                + " from v_cg_ht a left join v_shsl b on (a.ht_no=b.in_contract and a.ht_code=b.in_code and a.data_corp=b.data_corp) where " + sqlWhere;
	        String countsql = "select count(*) from (" + sql + ")t";
		//eXsContractDao.QueryMapPageList(baseDto,sql,true);
	        //QueryToMapPage
	//	return super.QueryMapPageList(dto,sql,true); //者QueryToMapPage
	        return  super.queryToMap(sql);//queryToMap
	}

	

}
