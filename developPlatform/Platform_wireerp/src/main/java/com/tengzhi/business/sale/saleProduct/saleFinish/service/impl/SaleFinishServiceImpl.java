package com.tengzhi.business.sale.saleProduct.saleFinish.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContractDetailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sale.saleProduct.saleContract.dao.EXsContractDetailedDao;
import com.tengzhi.business.sale.saleProduct.saleFinish.service.SaleFinishService;

import cn.hutool.core.util.ObjectUtil;
@Service
@Transactional
public class SaleFinishServiceImpl implements SaleFinishService {

	@Autowired
	private EXsContractDetailedDao eXsContractDetailedDao;
	
	@Override
	public Map<String, Object> getContractDetailed(BaseDto baseDto) throws IOException {
		Map<String, Object> rmap = new HashMap<String, Object>();
		Map<String, String> map = baseDto.getParamsMap();
		String sqlWhere ="";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
            sqlWhere+=" and xs.ht_date >='"+map.get("srchRq1")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
            sqlWhere+=" and xs.ht_date <='"+map.get("srchRq2")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
            sqlWhere+=" and xs.ht_no like '%"+map.get("srchNo")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchCustomer"))) {
            sqlWhere+=" and xs.ht_customer = '"+map.get("srchCustomer")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
			if(!"ALL".equals(map.get("htStype"))) {
                sqlWhere+=" and xs.ht_stype = '"+map.get("htStype")+"'";
            }
        }
		if(ObjectUtil.isNotEmpty(map.get("cpcodeName"))) {
            sqlWhere +=" and f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') like '%"+map.get("cpcodeName")+"%' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("cpcodeSize"))) {
            sqlWhere+=" and cp.cpcode_size = '"+map.get("cpcodeSize")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
            sqlWhere+=" and mx.ht_code = '"+map.get("srchCode")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("htCustomerAssociatedRemarks"))) {
            sqlWhere+=" and mx.ht_customer_associated_remarks  like '%"+map.get("htCustomerAssociatedRemarks")+"%' ";
        }
		String where =" ";
		if(ObjectUtil.isNotEmpty(map.get("srchHtFlag"))) {
            where =" where  ht_flag = '"+map.get("srchHtFlag")+"' ";
        }
 		String sql="select a.*,(ddsl-xssl) ddcl from ( " + 
 				"select mx.ht_code,to_char(xs.ht_date,'yyyy-mm-dd')ht_date,xs.ht_no,mx.ht_mo,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,cpcode_size,cpcode_size_en,cpcode_fl,cpcode_xp,f_getname('GETDWEXP',xs.ht_customer,'',xs.data_corp) customername,mx.ht_sl ddsl,COALESCE( (select sum(in_sl) from e_ck_in where in_contract=mx.ht_no and in_code = mx.ht_code),0)rksl,"
 				+ "COALESCE( (select sum(sl) from v_ck_mx where in_contract=mx.ht_no and code=mx.ht_code),0)kcsl,COALESCE((select sum(out_sl) from e_ck_out where out_contract=mx.ht_no and out_code=mx.ht_code and out_act ='CP51'),0)xssl,mx.ht_flag,ht_customer_associated_remarks "
 				+ " from  e_xs_contract xs ,e_xs_contract_detailed mx,e_js_cpcode cp where xs.ht_no=mx.ht_no and mx.ht_code=cp.cpcode_id  " +sqlWhere + 
 				" )a "+where +" order by ht_date,ht_no,ht_mo" ;
		rmap.put("data", eXsContractDetailedDao.QueryListMap(sql));
		rmap.put("status", true);
		return rmap;
	}

	@Override
	public Result getFlag(String htMo, String flag,String type) throws Exception{
		List<Map<String,Object>> flagString=eXsContractDetailedDao.getFlag(htMo);
		if("登记".equals(flag)) {
			if("sale".equals(type)){
				flag="生产核销,登记";
			}else if("produce".equals(type)){
				flag="登记";
			}
			
		}else if("核销".equals(flag)){
			if("sale".equals(type)) {
				flag="业务核销";
			}else if("produce".equals(type)) {
				flag="生产核销";
			}
		}
		if(flag.indexOf(flagString.get(0).get("ht_flag").toString())>-1) {
			return  Result.resultOk("操作成功！");
		}else {
			return  Result.error("该单不是“"+flag+"”状态,不能操作！");
		}
		
		
	}

	@Override
	public Result hx(String htNo,String htMo,String type) {
		String flag="";
		if("sale".equals(type)) {
			flag="业务核销";
		}else if("produce".equals(type)) {
			flag="生产核销";
		}
		eXsContractDetailedDao.updateFlag(htMo, flag);
		//主表核销
		if("0".equals(eXsContractDetailedDao.getSingleResult("select count(*) from e_xs_contract_detailed where ht_no = '"+htNo+"'  and ht_flag<>'业务核销'  "))) {
			eXsContractDetailedDao.getNo(htNo,"核销");
		}
		return Result.resultOkMsg(flag);
	}

	@Override
	public Result qxhx(String htNo,String htMo,String type) {
		String flag="";
		if("sale".equals(type)) {
			flag="登记";
		}else if("produce".equals(type)) {
			flag="登记";
		}
		//主表取消
		eXsContractDetailedDao.getNo(htNo,"核准");
		eXsContractDetailedDao.updateFlag(htMo, flag);
		return Result.resultOkMsg(flag);
	}

}
