package com.tengzhi.business.finance.allowance.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.da.sysCustomer.service.SysCustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.business.finance.allowance.dao.allowanceDao;
import com.tengzhi.business.finance.allowance.model.allowance;
import com.tengzhi.business.finance.allowance.service.allowanceService;
import com.tengzhi.business.finance.invoice.model.invoice;
import com.tengzhi.business.system.core.service.SysGenNoteService;

@Service
@Transactional
public class allowanceServiceImpl implements allowanceService{
	
	@Autowired
	private allowanceDao allowanceDao;
	@Autowired
	private SysGenNoteService sysGenNoteService;
	
	@Override
	public BasePage<allowance> findAll(BaseDto baseDto) throws IOException, ParseException {
		// TODO Auto-generated method stub
		Map<String, String> map = baseDto.getParamsMap();
		Object rq1=map.get("srchRq1");
	    Object rq2=map.get("srchRq2");
	    Object srchNote=map.get("srchNote");
	    Object srchBz=map.get("srchBz");
	    Object cwStype=map.get("cwStype");
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String sqlWhere = " where  cw_stype='"+cwStype+"'";
	    if (!StringUtils.isBlank(rq1.toString())) {
	       Date srchrq1 = simpleDateFormat.parse(rq1.toString());
	       sqlWhere += " and cw_rq >='" + srchrq1 + "'";
	    }
	    if (!StringUtils.isBlank(rq1.toString())) {
	       Date srchrq2 = simpleDateFormat.parse(rq2.toString());
	       sqlWhere += " and cw_rq <='" + srchrq2 + "'";
	    }
	    if (!StringUtils.isBlank(srchNote.toString())) {
	      sqlWhere += " and cw_note like'%" + srchNote + "%'";
	    }
	    if (!StringUtils.isBlank(srchBz.toString())) {
	      sqlWhere += " and cw_bz like'%" + srchBz + "%'";
	    }
		//业务员权限过滤
		sqlWhere+=" and cw_dw = " + SysCustomerService.getBusinessIdsWhereCustomerSqlFragment(null);
	    
	    String sql="select cw_note ,cw_rq ,cw_act ,cw_item ,cw_code ,cw_type ,cw_js ,cw_sl ,cw_zl ,cw_price ,cw_sje ,cw_se ,cw_ck ,cw_bz ,cw_hl ,cw_shl ,cw_fkrq,cw_fph,cw_fkh,cw_sm,cw_month,cw_flag,data_man,data_date,data_corp,cw_stype,cw_dw,f_get_param_name('交易币种',cw_bz,'"+   SessionUser.getCurrentCorpId()   +"','') cwbzs,f_getname('GETDWEXP', cw_dw, '', data_corp) cwdws  " + 
	        " from  e_cw_ysyf "+sqlWhere+"  group by cw_note ,cw_rq ,cw_act ,cw_item ,cw_code ,cw_type ,cw_js ,cw_sl ,cw_zl ,cw_price ,cw_sje ,cw_se ,cw_ck ,cw_bz ,cw_hl ,cw_shl ,cw_fkrq,cw_fph,cw_fkh,cw_sm,cw_month,cw_flag,data_man,data_date,data_corp,cw_stype,cw_dw";
	        sql ="select * from ("+sql+") n ";
	        String countSql="select count(*) cn from ("+sql+")t ";
	        return allowanceDao.QueryPageLists(sql,countSql,baseDto);
	}
	
	
	
	@Override
    public allowance save(allowance allowance) throws Exception {
		//SysGenNoteService sysGenNoteService=new SysGenNoteService();
		SessionUser securityUser=SessionUser.SessionUser();
		if("应付折让".equals(allowance.getCwStype())) {
			allowance.setCwNote(sysGenNoteService.getNote(allowance.class, "YFZR", "yyMMdd", 4));
		}else if("应收折让".equals(allowance.getCwStype())) {
			allowance.setCwNote(sysGenNoteService.getNote(allowance.class, "YSZR", "yyMMdd", 4));
		}
		
		if (judgeUnique(allowance)) {
			//allowance.setSfkId(UUIDUtil.uuid());
			allowance.setDataDate(new Date());
			allowance.setDataCorp(securityUser.getCorpId());
			allowance.setDataMan(securityUser.getUserId());
		    allowance.setCwFlag("登记");
			allowance.setCwMonth("未结");
			allowance.setCwAct("0702");
			allowance.Validate();
            return allowanceDao.save(allowance);
	
		  } else { throw new Exception("单号已存在"); }
		 
    }
	
	@Override
    public void deleteByCwNote (String cwNote){
        allowanceDao.deleteById(cwNote);
    }
	
	 @Override
	    public boolean judgeUnique(allowance allowance) {
	        return allowanceDao.findAll(Specifications.where((c) -> {
	            c.eq("cwNote", allowance.getCwNote());
			/*
			 * c.lt("fprq1", invoice.getFpRq()); c.gt("fprq2", invoice.getFpRq());
			 */
	        })).size() <= 0;
	    }
	 
	 @Override
	    public void update(allowance allowance){
	        //sysRole.setModifyTime(new Date());
		   // invoice.setFpRq(new Date());
	        allowanceDao.dynamicSave(allowance,allowanceDao.findByCwNote(allowance.getCwNote()));
	    }

	 @Override
	    public allowance findByCwNote(String cwNote) {
		    allowance al=allowanceDao.findByCwNote(cwNote);
		    al.setCwdws(allowanceDao.getCorpName(al.getCwDw(), al.getDataCorp()));
	        return allowanceDao.findByCwNote(cwNote);
	    }



	@Override
	public void ok(String cwNote,String cwCode) {
		// TODO Auto-generated method stub
		allowanceDao.ok(cwNote,cwCode);
		
	}
	
	@Override
	public void yok(String cwNote,String cwCode) {
		// TODO Auto-generated method stub
		allowanceDao.yok(cwNote,cwCode);
		
	}
	@Override
	public void qx(String cwNote,String cwCode) {
		// TODO Auto-generated method stub
		allowanceDao.qx(cwNote,cwCode);
		
	}
	
	@Override
	public void yqx(String cwNote,String cwCode) {
		// TODO Auto-generated method stub
		allowanceDao.yqx(cwNote,cwCode);
		
	}
	

}
