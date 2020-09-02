package com.tengzhi.business.sc.pd.htps.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.sale.saleProduct.saleContract.dao.EXsContractDao;
import com.tengzhi.business.sale.saleProduct.saleContract.dao.EXsContractDetailedDao;
import com.tengzhi.business.sale.saleProduct.saleContract.dao.EXsContractDetailedItemDao;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContractDetailed;
import com.tengzhi.business.sale.saleProduct.saleContract.vo.EXsContractVo;
import com.tengzhi.business.sc.pd.htps.service.HtpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class HtpsServiceImpl implements HtpsService {

	@Autowired
	private EXsContractDao eXsContractDao;
	
	@Autowired
	private EXsContractDetailedDao eXsContractDetailedDao;

	@Autowired
	private EXsContractDetailedItemDao eXsContractDetailedItemDao;
	
	

	@Override
	public BasePage<EXsContract> findAll(BaseDto baseDto) throws IOException {
		String where="";
		Map<String, String> map = baseDto.getParamsMap();
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
            where+=" and a.ht_date >='"+map.get("srchRq1")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
            where+=" and a.ht_date <='"+map.get("srchRq2")+"' ";
        }
		if(ObjectUtil.isNotEmpty(map.get("htNo"))) {
            where+=" and a.ht_no like '%"+map.get("htNo")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htCustomer"))) {
            where+=" and a.ht_customer = '"+map.get("htCustomer")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htType"))) {
            where+=" and a.ht_type = '"+map.get("htType")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htItemType"))) {
            where+=" and a.ht_item_type = '"+map.get("htItemType")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htCustomerAssociated"))) {
            where+=" and b.ht_customer_associated = '"+map.get("htCustomerAssociated")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htCustomerAssociatedRemarks"))) {
            where+=" and b.ht_customer_associated_remarks = '"+map.get("htCustomerAssociatedRemarks")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
            where+=" and a.ht_stype = '"+map.get("htStype")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("htFlag"))) {
			if("审核中".equals(map.get("htFlag"))) {
				where += " and a.ht_flag not in  ('登记','确认','核准','核销')";
			}else {
				where+=" and a.ht_flag = '"+map.get("htFlag")+"'";
			}
			
		}
		
		String sqlString=" select a.ht_no,a.ht_date,f_get_param_name('订单类型',a.ht_type,'"+   SessionUser.getCurrentCorpId()   +"','cn')ht_type,f_get_param_name('订单类型',a.ht_item_type,'"+   SessionUser.getCurrentCorpId()   +"','cn')ht_item_type,f_getname('GETDWEXP',a.ht_customer,'',a.data_corp)ht_customer,f_get_param_name('业务员',c.customer_buyer,'"+   SessionUser.getCurrentCorpId()   +"','')data_man,sum(b.ht_sl)ht_sl,sum(b.ht_je)ht_je,a.ht_flag from e_xs_contract a ,e_xs_contract_detailed b, sys_customer c where a.ht_customer= c.customer_id and a.ht_no=b.ht_no "+ where 
				+ " group by a.ht_no,a.ht_date,a.ht_type,a.ht_item_type,a.ht_customer,c.customer_buyer,a.ht_flag,a.data_corp order by ht_date desc,ht_no desc";
		return eXsContractDao.QueryPageLists(baseDto,sqlString);
	}
	
	
	@Override
	public EXsContract findByNote(String htNote) {
		return eXsContractDao.QueryListModel(EXsContract.class, "select a.*,f_getname('GETDWEXP',a.ht_customer,'',a.data_corp)ht_customer_name from e_xs_contract a where ht_no= :1 ", htNote).get(0);
	}
	
	@Override
	public List<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String where ="and a.ht_no= '"+map.get("htNo")+"'";
		if(ObjectUtil.isNotEmpty(map.get("htFlag"))) {
            where+=" and a.ht_flag = '"+map.get("htFlag")+"'";
        }
		String sqlString=" select a.ht_no,a.ht_mo,a.ht_id,a.ht_code,a.ht_sl,a.ht_price,a.ht_je,to_char(a.ht_jq,'yyyy-MM-dd') ht_jq,a.ht_sm,a.ht_flag,a.data_corp,a.ht_requirements,"
				+ "(select string_agg( param_name,',') from sys_param c where strpos(a.ht_standard,c.param_id)>0 and  c.param_mod='技术'  )ht_standard_name,ht_standard "
				+ ",ht_customer_associated_remarks,ht_customer_associated,"
				+ "f_getname('GETUSERNAME',ht_reply_man,'',a.data_corp) ht_reply_man " + 
				" ,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name, "
				+ " b.cpcode_size ,b.cpcode_size_en,"
				+ "f_getparamname('GETCPCODESIZE',b.cpcode_size,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name, 	"
				+ "f_getparamname('GETCPCODESIZEEN',b.cpcode_size_en,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,"
				+ "f_getparamname('GETCPCODEXP',b.cpcode_xp,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name,"
				+ "f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_fl,"
				+ "b.cpcode_bz,b.cpcode_xp,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03 from e_xs_contract_detailed a,e_js_cpcode b,e_xs_contract c "
				+ "  where a.ht_no=c.ht_no and a.data_corp=c.data_corp and a.ht_code=b.cpcode_id "+where ;

		return eXsContractDetailedDao.QueryToMap(sqlString).stream().map(row -> {
			return new HashMap<String, Object>() {{
				putAll(row);
				put("htItemData", eXsContractDetailedItemDao.QueryToMap("select ht_mo,data_corp,ht_item_code,ht_item_value,ht_item_value_reply,f_get_param_name('线材项目',ht_item_code,data_corp,'')ht_item_code_name,item_sid from e_xs_contract_detailed_item items where ht_mo = '"+row.get("htMo")+"'"));
			}};
		}).collect(Collectors.toList());

	}
	
	

	@Override
	public Result getFlag(String htNo, String flag) {
		String flagString=eXsContractDao.getFlag(htNo);
		if(flag.equals(flagString)) {
			return  Result.resultOk("操作成功！");
		}
		return  Result.error("该单不是“"+flag+"”状态,不能操作！");
	}

	

	
	@Override
	public Result confirm(String htNo,String htMo,String htJq) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(htJq);
			eXsContractDetailedDao.updateHtjq(htMo,date);
			eXsContractDao.updateFlag(htNo, "回复");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Result.resultOkMsg("确认回复");
	}
	@Override
	public Result cancel(String htNo) {
		eXsContractDao.updateFlag(htNo, "未回复");
		eXsContractDetailedDao.cancelReply(htNo);
		//List <EXsContractDetailed> detaileds = eXsContractDetailedDao.QueryListModel(EXsContractDetailed.class, " select * from e_xs_contract_detailed where ht_no = :1", htNo);
		return Result.resultOkMsg("取消回复");
	}


	@Override
	public Result reply(EXsContractVo eXsContractVo) {
		SessionUser securityUser=SessionUser.SessionUser();
		if(!eXsContractVo.getModifyedList().isEmpty()){
			eXsContractVo.getModifyedList().forEach(item -> {
				item.setHtReplyMan(securityUser.getUserId());
				eXsContractDetailedDao.dynamicSave(item, eXsContractDetailedDao.findById(item.getHtMo()).orElse(null));
				item.getHtItemData().forEach(model ->{
					if("modified".equals(model.get_state())){
						model.setHtItemReplyMan(securityUser.getUserId());
						model.setHtItemReplyDate(new Date());
						eXsContractDetailedItemDao.dynamicSave(model,eXsContractDetailedItemDao.findById(model.getItemSid()).orElse(null));
					}
				});
				eXsContractDao.updateFlag(eXsContractVo.geteXsContract().getHtNo(), "回复");
			});
			return Result.resultOk("操作成功！");
		}else {
			return  Result.error("无修改");
		}
	}


	
	
	
	
}
