package com.tengzhi.business.finance.receivables.receivablesCheck.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseSettle.dao.ECwYsyfDao;
import com.tengzhi.business.cg.yw.purchaseSettle.model.ECwYsyf;
import com.tengzhi.business.finance.receivables.receivablesCheck.service.ReceivablesCheckService;
import com.tengzhi.business.system.user.model.SysUser;

import cn.hutool.core.util.ObjectUtil;
@Service
@Transactional
public class ReceivablesCheckServiceImpl implements ReceivablesCheckService {

	@Autowired
	private ECwYsyfDao eCwYsyfDao;
	
	@Override
	public BasePage<ECwYsyf> getGridList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		
		String sqlWhere = " where cw_stype='"+map.get("cwStype")+"'   ";
		if(ObjectUtil.isNotEmpty(map.get("srchFlag"))) {
			sqlWhere+=" and cw_flag = '"+map.get("srchFlag")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
			sqlWhere+=" and cw_note ='"+map.get("srchNo")+"' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			sqlWhere+=" and cw_rq >='"+map.get("srchRq1")+"' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			sqlWhere+=" and cw_rq <='"+map.get("srchRq2")+"' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchNote"))) {
			sqlWhere+=" and cw_note like '%"+map.get("srchNote")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCustomer"))) {
			sqlWhere+=" and cw_dw = '"+map.get("srchCustomer")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCwType"))) {
			sqlWhere+=" and cw_type = '"+map.get("srchCwType")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchFlag"))) {
			sqlWhere+=" and cw_flag = '"+map.get("srchFlag")+"'";
		}
		String sql = "select cw_rq,cw_note,f_getname('GETDWEXP',cw_dw,'',data_corp)cw_dw,f_get_param_name('仓库收发',cw_act,'"+   SessionUser.getCurrentCorpId()   +"','')cw_act,sum(cw_sl) cw_sl,sum(cw_sje) cw_sje,cw_bz,cw_shl,cw_fkrq,cw_flag ,f_get_param_name('产品大类',cw_type,'"+   SessionUser.getCurrentCorpId()   +"','')cwtypename,cw_type from e_cw_ysyf "+sqlWhere
				+ "  group by cw_rq,cw_note,cw_dw,data_corp,cw_act,cw_bz,cw_shl,cw_fkrq,cw_flag,cw_type   ";
		return eCwYsyfDao.QueryPageLists(baseDto,sql );
	}

	@Override
	public BasePage<ECwYsyf> getDetailGridList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		
		String sqlWhere = " where   cw_stype='"+map.get("cwStype")+"' and cw_note ='"+map.get("cwNote")+"'   ";
		
		String sql = "select  cw_rq, cw_note, cw_dw,cw_lib, cw_item,cpcode_size,cw_sl,cw_price,cw_se,cw_sje,cw_bz,cw_shl,cw_fph,cw_act,cw_flag, " + 
				"    data_corp,cw_type,cw_stype,cpcode_size_en,cpcode_xp,cw_code,f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',cw_type,'"+   SessionUser.getCurrentCorpId()   +"') AS cpcode_fl," + 
				"    f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cw_type,'"+   SessionUser.getCurrentCorpId()   +"') as cpcode_name from v_cw_ysyf "+sqlWhere;
		return eCwYsyfDao.QueryPageLists(baseDto,sql );
	}

	@Override
	public ECwYsyf getDataByNote(String note) {
		ECwYsyf ysyf=new ECwYsyf();
		String sqlString=" select  distinct cw_note,a.cw_dw,a.cw_rq,f_getname('GETDWEXP',a.cw_dw,'',a.data_corp)dw_name,cw_type,cw_stype from e_cw_ysyf a where   cw_note ='"+note+"' ";
		BasePage<ECwYsyf> list=eCwYsyfDao.QueryNoPageLists(sqlString);
		if(list.getContent().size()>0) { 
			ysyf=list.getContent().get(0);
		} 
		return ysyf;
	}

	@Override
	public Result confirm(String cwNote) {
		eCwYsyfDao.setFlag(cwNote,"确认");
		return Result.resultOkMsg("修改成功");
	}

	@Override
	public Result cancel(String cwNote) {
		eCwYsyfDao.setFlag(cwNote,"库审");
		return Result.resultOkMsg("修改成功");
	}

	@Override
	public Result getFlag(String cwNote, String flag) {
		String flagString = eCwYsyfDao.getFlag(cwNote);
		if(flag.equals(flagString)){
			return Result.resultOkMsg("操作成功");
		}else {
			return  Result.error("该单不是“"+flag+"”状态,不能操作！");
		}
	}

	@Override
	public ModelAndView table(ModelAndView mv, String cwNote) {
		SessionUser sessionUser=SessionUser.SessionUser();
		String sql = " select  f_getname('GETCORPNAME', '','',a.data_corp) corpname,'单号：'||cw_note cw_note,'日期：'||to_char(now(),'yyyy-mm-dd') rq ,'打印时间：'||to_char(now(),'yyyy-mm-dd hh24:mi:ss') now,'单位名称：'||b.customer_name customer_name,'制单：'||'"+sessionUser.getRealName()+"' manname,'联系方式：'||coalesce(customer_contact,'')  customer_contact,f_get_param_name('仓库收发',cw_act,'"+   SessionUser.getCurrentCorpId()   +"','')||'单' actname "
				+ " from  e_cw_ysyf a,sys_customer b  where a.cw_dw=b.customer_id and   cw_note='"+cwNote+"' limit 1 ";
		List<Map> table = eCwYsyfDao.QueryListMap(sql);
		if(table.size()>0) {
			mv.addObject("corpName", table.get(0).get("corpname"));
			mv.addObject("note", table.get(0).get("cw_note"));
			mv.addObject("rq", table.get(0).get("rq"));
			mv.addObject("customername", table.get(0).get("customer_name"));
			mv.addObject("lxfs", table.get(0).get("customer_contact"));
			//mv.addObject("address", table.get(0).get("ht_address"));
			mv.addObject("manName", table.get(0).get("manname"));
			mv.addObject("now", table.get(0).get("now"));
			mv.addObject("actname", table.get(0).get("actname"));

			String mxSql = "select  cw_code,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,cpcode_size|| case when cpcode_type ='CP' and length(cpcode_size_en)>0 then  '*'||cpcode_size_en||'mm'  when  length(cpcode_xp)>0 then '*'||cpcode_xp||f_get_param_name('计量单位',cpcode_bz,'"+   SessionUser.getCurrentCorpId()   +"','') else '' end cpcode_size,round(cw_sl,2)cw_sl,round(cw_price,4)cw_price,round(cw_sje,4)cw_sje  from e_cw_ysyf a ,e_js_cpcode b "
					+ " where a.cw_code=b.cpcode_id and cw_note='"+cwNote+"' "
					+ "  ";
			List<Map> tableMx = eCwYsyfDao.QueryListMap(mxSql);
			if(tableMx.size()>0) {
				mv.addObject("mx", tableMx);
				mv.addObject("mxCount", tableMx.size());
				double sum = 0;
				double je = 0;
				for(int i = 0 ; i < tableMx.size() ; i++) {
					sum += Double.parseDouble(tableMx.get(i).get("cw_sl").toString());
					je += Double.parseDouble(tableMx.get(i).get("cw_sje").toString());
				}
				mv.addObject("sum", sum);
				mv.addObject("je", je);
			}
		}
		return mv;
	}

}
