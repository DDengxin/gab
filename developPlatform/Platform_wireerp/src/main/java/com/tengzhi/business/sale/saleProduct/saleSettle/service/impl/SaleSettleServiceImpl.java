package com.tengzhi.business.sale.saleProduct.saleSettle.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.business.cg.da.sysCustomer.service.SysCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseSettle.dao.ECwYsyfDao;
import com.tengzhi.business.cg.yw.purchaseSettle.model.ECwYsyf;
import com.tengzhi.business.cg.yw.purchaseSettle.vo.ECwYsyfVo;
import com.tengzhi.business.mesGz.gzck.vo.EckOut;
import com.tengzhi.business.sale.saleArchives.customerArchives.dao.CustomerArchivesDao;
import com.tengzhi.business.sale.saleProduct.saleDelivery.dao.SaleDeliveryDao;
import com.tengzhi.business.sale.saleProduct.saleSettle.service.SaleSettleService;

import cn.hutool.core.util.ObjectUtil;


@Service
@Transactional
public class SaleSettleServiceImpl implements SaleSettleService {

	
	@Autowired
	private ECwYsyfDao eCwYsyfDao;
	
	@Autowired
	private SaleDeliveryDao saleDeliveryDao;
	
	@Autowired
	private CustomerArchivesDao customerArchivesDao;
	
	
	@Override
	public BasePage<EckOut> getSrchTopList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlWhere=" ";
		if ("加工".equals(map.get("paramMod").toString())){
			sqlWhere =" where a.out_code=b.cpcode_id and out_act in(SELECT param_id FROM sys_param WHERE param_type like '仓库收发' and param_name ='加工出库')";
		}
		if ("销售".equals(map.get("paramMod").toString())){
			sqlWhere=" where a.out_code=b.cpcode_id and out_act in (SELECT param_id FROM sys_param WHERE param_type like '仓库收发' and param_name in('销售出库','销售退回') AND parent_name=('销售'))";
		}
		//String sqlWhere = " where a.out_code=b.cpcode_id and out_act in (select param_id from sys_param where param_name like '"+map.get("paramMod")+"%'   ) ";

		if(ObjectUtil.isNotEmpty(map.get("paramMod"))) {

		}
		if(ObjectUtil.isNotEmpty(map.get("outFlag"))) {
			sqlWhere+=" and a.out_flag = '"+map.get("outFlag")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			sqlWhere+=" and a.out_rq >='"+map.get("srchRq1")+"' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			sqlWhere+=" and a.out_rq <='"+map.get("srchRq2")+"' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("outNote"))) {
			sqlWhere+=" and a.out_note like '%"+map.get("outNote")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("outCustomer"))) {
			sqlWhere+=" and a.out_customer = '"+map.get("outCustomer")+"'";
		}
		//业务员权限过滤
		sqlWhere += " and a.out_customer = " + SysCustomerService.getBusinessIdsWhereCustomerSqlFragment(null);

		if(ObjectUtil.isNotEmpty(map.get("srchFlag"))) {
			sqlWhere+=" and a.out_flag = '"+map.get("srchFlag")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("outType"))) {
			sqlWhere+=" and a.out_type = '"+map.get("outType")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("outCode"))) {
			sqlWhere+=" and a.out_code = '"+map.get("outCode")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("cpcodeName"))) {
			sqlWhere += " and b.cpcode_name = '"+map.get("cpcodeName")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchSize"))) {
			sqlWhere += " and b.cpcode_size = '"+map.get("srchSize")+"'";
		}

		String sql = "select f_getname('GETDWEXP',out_customer,'',a.data_corp)customer ,out_customer,out_rq ,out_note  ,f_get_param_name('库房档案',out_lib,'"+   SessionUser.getCurrentCorpId()   +"','cn')out_lib,"
				+ "out_act ,sum(out_js) out_js ,sum(out_sl) out_sl,sum(out_zl) out_zl,out_flag ,sum(out_sl*out_price)out_je,f_getname('GETUSERNAME',a.data_man,'',a.data_corp)data_man ,MAX(a.data_date) data_date,a.data_corp  "
				+ " from  e_ck_out a ,e_js_cpcode b " + sqlWhere
				+ "  group by out_rq,out_note,out_customer,out_act,out_flag,a.data_man,a.data_corp,out_lib  ";
		return saleDeliveryDao.QueryPageLists(baseDto,sql );
	}

	@Override
	public BasePage<Map<String, Object>>   getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlWhere = "  and  cw_act in (select param_id from sys_param where param_name like '"+map.get("paramMod")+"%'   )   ";
		//String sqlWhere ="";
		if(ObjectUtil.isNotEmpty(map.get("outNote"))) {
            sqlWhere+=" and cw_note = '"+map.get("outNote")+"' ";
        }

		String sql = "select  cw_rq, cw_note, cw_lib,cpcode_name,cpcode_size,"
				+ "f_getparamname('GETCPCODESIZE',b.cpcode_size,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name, "
				+ "f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name_name,"
				+ "f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,"
				+ "f_getparamname('GETCPCODEXP',cpcode_xp,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name, "
				+ "f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') as cpcode_fl,"
				+ "cpcode_xp,cpcode_size_en, "
				+ "cw_customer, cw_item, cw_code,cw_js,cw_sl, cw_sje, cw_price, cw_shl,  cw_act,  cw_bz   "
				+ "from e_cw_ysyf a, e_js_cpcode b where a.cw_code=b.cpcode_id "+sqlWhere;
		/*String sql = "select out_note cwNote,out_lib cwLib,out_customer cwCustomer,out_contract cwItem,out_code cwCode,sum(out_js)cwJs,sum(out_sl)cwSl,sum(out_sl)*out_price cwSje,out_tax cwTax "
				+ "from e_ck_out "+sqlWhere
				+ "group by  out_note,out_lib,out_tax,out_customer,out_contract,out_code,out_price ";
		 rmap.put("data", saleDeliveryDao.QueryListMap(sql)); rmap.put("status",
		 true); return rmap;
		*/
		//return eCwYsyfDao.QueryPageLists(baseDto,sql);
		return eCwYsyfDao.QueryMapPageList(baseDto, sql, true);

	}
	@Override
	public BasePage<Map<String, Object>>   getSettleList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		Map<String, Object> rmap = new HashMap<String, Object>();
		String sqlWhere = "  and  out_act in (select param_id from sys_param where param_name like '"+map.get("paramMod")+"%'   )   ";
		if(ObjectUtil.isNotEmpty(map.get("outNote"))) {
            sqlWhere+=" and out_note = '"+map.get("outNote")+"' ";
        }

		String sql = "select out_rq cw_rq,out_note cw_note,out_lib cw_lib,"
				+ "cpcode_name,cpcode_size,"
				+ "f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name, "
				+ "f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name_name,"
				+ "f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') as cpcode_fl,"
				+ "cpcode_xp, f_getparamname('GETCPCODEXP',cpcode_xp,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name,"
				+ "cpcode_size_en, f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,"
				+ "out_customer cw_customer,out_contract cw_item,out_code cw_code,sum(out_js)cw_js,sum(out_sl)cw_sl,sum(out_sl)*out_price cw_sje,out_price cw_price,out_tax cw_shl, out_act cw_act, out_bz cw_bz,out_tax cw_hl  "
				+ "from e_ck_out a, e_js_cpcode b where a.out_code=b.cpcode_id "+sqlWhere
				+ "group by  out_note,out_rq,out_lib,out_tax,out_customer,out_contract,out_code,cpcode_fl,cpcode_xp,cpcode_size_en,cpcode_type,out_price,cpcode_name,cpcode_size,out_act,out_bz,cpcode_fl,out_tax ";
		/*String sql = "select out_note cwNote,out_lib cwLib,out_customer cwCustomer,out_contract cwItem,out_code cwCode,sum(out_js)cwJs,sum(out_sl)cwSl,sum(out_sl)*out_price cwSje,out_tax cwTax "
				+ "from e_ck_out "+sqlWhere
				+ "group by  out_note,out_lib,out_tax,out_customer,out_contract,out_code,out_price ";
		 rmap.put("data", saleDeliveryDao.QueryListMap(sql)); rmap.put("status",
		 true); return rmap;
		*/
		//return eCwYsyfDao.QueryPageLists(baseDto,sql);
		return  eCwYsyfDao.QueryMapPageList(baseDto, sql, true);
	}

	@Override
	public Result settle(ECwYsyfVo eCwYsyfVo) throws IOException {
		SessionUser securityUser=SessionUser.SessionUser();
		if (!eCwYsyfVo.getAddedList().isEmpty()) {
			eCwYsyfVo.getAddedList().forEach(item -> {
				//start保存e_cw_ysyf表
				item.setCwType("CP");
				item.setCwStype("XS");
				item.setCwFlag("结算");
				item.setCwFph("N");
				item.setCwDw(eCwYsyfVo.geteCwYsyf().getCwDw());
				BigDecimal je = null; 
				BigDecimal sje = null; 
				BigDecimal se = null; 
				BigDecimal jc = BigDecimal.valueOf(1F);
				BigDecimal wsje =null;
				/*
				 * if("件数".equals(item.getCwCk())) {
				 * je=item.getCwJs().multiply(item.getCwPrice());
				 * se=je.multiply(item.getCwShl()); item.setCwSe(se); sje=je.add(se);
				 * item.setCwSje(sje); }else if("数量".equals(item.getCwCk())) {
				 * je=item.getCwSl().multiply(item.getCwPrice());
				 * se=je.multiply(item.getCwShl()); item.setCwSe(se); sje=je.add(se);
				 * item.setCwSje(sje); }else if("重量".equals(item.getCwCk())) {
				 * je=item.getCwZl().multiply(item.getCwPrice());
				 * se=je.multiply(item.getCwShl()); item.setCwSe(se); sje=je.add(se);
				 * item.setCwSje(sje); }
				 */
				item.setCwCk("数量");
				je=item.getCwSl().multiply(item.getCwPrice());
				/*
				 * se=je.multiply(item.getCwShl()); item.setCwSe(se); sje=je.add(se);
				 * item.setCwSje(sje);
				 */
				sje = je;
				item.setCwSje(sje);
				wsje = sje.divide(jc.add(item.getCwShl()),2,BigDecimal.ROUND_HALF_UP);
				se = sje.subtract(wsje);
				item.setCwSe(se);
				item.setDataMan(securityUser.getUserId());
				item.setDataDate(new Date());
				item.setDataCorp(securityUser.getCorpId());
				eCwYsyfDao.save(item);
				//end
				//start更新出库状态以及出库单价等字段
				
				
				/*
				 * modECkOut.setOutNote(item.getCwNote());
				 * modECkOut.setOutContract(item.getCwItem());
				 * modECkOut.setOutCode(item.getCwCode()); modECkOut.setOutTax(item.getCwShl());
				 * modECkOut.setOutRate(item.getCwHl()); modECkOut.setOutBz(item.getCwBz());
				 * modECkOut.setOutPrice(item.getCwPrice()); modECkOut.setOutPack(item.geto);
				 * modECkOut.setOutFlag("结算"); modECkOut.setDataMan(securityUser.getUsername());
				 * modECkOut.setDataDate(new Date());
				 * modECkOut.setDataCorp(securityUser.getCorpId());
				 * modECkOut.setDataMan(securityUser.getUsername());
				 * saleDeliveryDao.dynamicSave(modECkOut, saleDeliveryDao.findById(new
				 * ECkIn_PK(modECkOut.getOutNote(), item.getInPack())).orElse(null));
				 */
				//end
			});
			saleDeliveryDao.updateFlag(eCwYsyfVo.geteCwYsyf().getCwNote(),"结算");
		}
		
		return Result.resultOkMsg("操作成功");
	}

	@Override
	public Result cancel(String outNote) throws IOException {
			eCwYsyfDao.deleteByInNote(outNote);
			saleDeliveryDao.updateFlag(outNote,"确认");
			return Result.resultOkMsg("操作成功");
	}

	@Override
	public Result getByNote(String cwNote) {
		String customrDay =customerArchivesDao.getSingleResult("select  coalesce(customer_day,0) cusday    from sys_customer where customer_id in  (select  out_customer from e_ck_out where out_note= '"+cwNote+"')  ");
		return Result.resultOk( eCwYsyfDao.QueryListModel(ECwYsyf.class," select distinct out_note cw_note,out_customer cw_dw,f_getname('GETDWEXP',out_customer,'',data_corp) dw_name,out_lib cw_lib,out_bz cw_bz,out_tax cw_shl,current_date + interval '"+customrDay+" D' cw_fkrq from e_ck_out  where out_note = :1 ",cwNote).get(0),"操作成功");
		
	}

	@Override
	public ModelAndView table(ModelAndView mv, String outNote,String addressNote) {
		SessionUser sessionUser=SessionUser.SessionUser();
		String sql = " select  f_getname('GETCORPNAME', '','',a.data_corp) corpname,'单号：'||out_note out_note,'日期：'||to_char(out_rq,'yyyy-mm-dd') rq ,'打印时间：'||to_char(now(),'yyyy-mm-dd hh24:mi:ss') now,'单位名称：'||b.customer_name customer_name,'制单：'||'"+sessionUser.getRealName()+"' manname,'送货地址：'||ht_address ht_address,'联系方式：'||coalesce(customer_contact,'')  customer_contact"
				+ " from  e_ck_out a,sys_customer b,e_xs_contract c where a.out_customer=b.customer_id and a.out_contract=c.ht_no and  out_note='"+outNote+"' limit 1 ";
		List<Map> table = saleDeliveryDao.QueryListMap(sql);
		
		if(table.size()>0) {
			mv.addObject("corpName", table.get(0).get("corpname"));
			mv.addObject("note", table.get(0).get("out_note"));
			mv.addObject("rq", table.get(0).get("rq"));
			mv.addObject("customername", table.get(0).get("customer_name"));
			mv.addObject("lxfs", table.get(0).get("customer_contact"));
			mv.addObject("address", table.get(0).get("ht_address"));
			mv.addObject("manName", table.get(0).get("manname"));
			mv.addObject("now", table.get(0).get("now"));
			/*mv.addObject("typeAndAct", table.get(0).get("typeandact"));
			mv.addObject("libName", table.get(0).get("libname"));
			*/
			String mxSql = "select  out_code,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,cpcode_size|| case when cpcode_type ='CP' and length(cpcode_size_en)>0 then  '*'||cpcode_size_en||'mm'  when  length(cpcode_xp)>0 then '*'||cpcode_xp||f_get_param_name('计量单位',cpcode_bz,'"+   SessionUser.getCurrentCorpId()   +"','') else '' end cpcode_size,out_ph,round(sum(out_sl),2)sl,round(out_price,4)out_price,round(sum(out_sl)*out_price,4) out_je  from e_ck_out a ,e_js_cpcode b "
					+ " where a.out_code=b.cpcode_id and out_note='"+outNote+"' "
					+ " group by out_code,cpcode_name,cpcode_type,cpcode_size, cpcode_size_en,cpcode_xp ,cpcode_bz, out_ph,out_price ";
			List<Map> tableMx = saleDeliveryDao.QueryListMap(mxSql);
			if(tableMx.size()>0) {
				mv.addObject("mx", tableMx);
				mv.addObject("mxCount", tableMx.size());
				double sum = 0;
				double je = 0;
				for(int i = 0 ; i < tableMx.size() ; i++) {
					sum += Double.parseDouble(tableMx.get(i).get("sl").toString());
					je += Double.parseDouble(tableMx.get(i).get("out_je").toString());
				}
				mv.addObject("sum", sum);
				mv.addObject("je", je);
			}
		}
		if(addressNote.length()>0) {
			Map  address= saleDeliveryDao.QueryListMap("select '送货地址：'||address address,'联系人：'||contacts contacts,'联系方式：'||contact_method contact_method from e_cw_customer_address where address_note ='"+addressNote+"'").get(0);
			mv.addObject("address", address.get("address"));
			mv.addObject("lxr", address.get("contacts"));
			mv.addObject("lxfs", address.get("contacts_method"));
		}
		return mv;
	}
	

}
