package com.tengzhi.business.production.subcontract.wwfk.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.base.tools.excel.ExcelUtil;
import com.tengzhi.business.finance.payment.dao.ExportDao;
import com.tengzhi.business.finance.payment.dao.paymentDao;
import com.tengzhi.business.finance.payment.model.payment;
import com.tengzhi.business.production.subcontract.wwfk.service.WwfkService;
import com.tengzhi.business.system.core.service.SysGenNoteService;

@Service
@Transactional
public class WwfkServiceImpl implements WwfkService{
	
	
	@Autowired
	private paymentDao paymentDao;
	
	@Autowired
	private ExportDao exportDao;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;
	
	@Override
	public BasePage<payment> findAll(BaseDto baseDto) throws IOException, ParseException {
		// TODO Auto-generated method stub
		SessionUser securityUser=SessionUser.SessionUser();
		Map<String, String> map = baseDto.getParamsMap();
		Object srchRq1=map.get("srchRq1");
	    Object srchRq2=map.get("srchRq2");
	    Object srchNote=map.get("srchNote");
	    Object srchBz=map.get("srchBz");
	    String sqlWhere = " where  sfk_xtype='FK' and sfk_type='WW' and data_corp='"+securityUser.getCorpId()+"' ";
	    if (!StringUtils.isBlank(srchRq1.toString())) {
	       sqlWhere += " and sfk_rq >='" + srchRq1 + "'";
	    }
	    if (!StringUtils.isBlank(srchRq2.toString())) {
	       sqlWhere += " and sfk_rq <='" + srchRq2 + "'";
	    }
	    if (!StringUtils.isBlank(srchNote.toString())) {
	      sqlWhere += " and sfk_note like'%" + srchNote + "%'";
	    }
	    if (!StringUtils.isBlank(srchBz.toString())) {
	      sqlWhere += " and sfk_bz like'%" + srchBz + "%'";
	    }
	    String sql="select sfk_note ,sfk_rq ,f_getname('GETDWEXP',sfk_dw,'',data_corp) sfk_dw ,f_get_param_name('付款分类',sfk_dtype,'"+   SessionUser.getCurrentCorpId()   +"') sfk_dtype ,sfk_type ,f_get_param_name('交易币种',sfk_bz,'"+   SessionUser.getCurrentCorpId()   +"')sfk_bz ,sfk_shl ,sfk_bzje ,sfk_yfje ,sfk_fkje ,f_get_param_name('付款方式',sfk_fkfs,'"+   SessionUser.getCurrentCorpId()   +"') sfk_fkfs ,sfk_mess ,sfk_flag ,sfk_month ,sfk_xtype ,sfk_sm ,data_man,data_date,data_corp,sfk_id       " + 
	        " from  e_cw_sfk "+sqlWhere+"  group by sfk_rq ,sfk_note ,sfk_dw ,sfk_dtype ,sfk_type ,sfk_bz ,sfk_shl ,sfk_bzje ,sfk_yfje ,sfk_fkje ,sfk_fkfs ,sfk_mess ,sfk_flag ,sfk_month ,sfk_xtype ,sfk_sm ,data_man,data_date,data_corp,sfk_id order by sfk_rq desc";
	        sql ="select * from ("+sql+") n ";
	        String countSql="select count(*) cn from ("+sql+")t ";
	        return paymentDao.QueryPageLists(sql,countSql,baseDto);
	}
	
	
	
	@Override
    public payment save(payment payment) throws Exception {
		payment.setSfkNote(sysGenNoteService.getNote(payment.class, "WWFK", "yyMMdd", 4));
		SessionUser securityUser=SessionUser.SessionUser();
		if (judgeUnique(payment)) {
			payment.setSfkId(UUIDUtil.uuid());
			payment.setDataDate(new Date());
			payment.setDataCorp(securityUser.getCorpId());
			payment.setDataMan(securityUser.getUserId());
			payment.setSfkXtype("FK");
			payment.setSfkType("WW");
			payment.setSfkFlag("登记");
			payment.setSfkMonth("未结");
			payment.Validate();
            return paymentDao.save(payment);
	
		  } else { throw new Exception("编码已存在"); }
		 
    }
	
	@Override
    public void deleteByPaymentId (String fpId){
        paymentDao.deleteById(fpId);
    }
	
	 @Override
	    public boolean judgeUnique(payment payment) {
	        return paymentDao.findAll(Specifications.where((c) -> {
	            c.eq("sfkNote", payment.getSfkNote());
			/*
			 * c.lt("fprq1", invoice.getFpRq()); c.gt("fprq2", invoice.getFpRq());
			 */
	        })).size() <= 0;
	    }
	 
	 @Override
	    public void update(payment payment){
	        //sysRole.setModifyTime(new Date());
		   // invoice.setFpRq(new Date());
	        paymentDao.dynamicSave(payment,paymentDao.findBySfkId(payment.getSfkId()));
	    }

	 @Override
	    public payment findByPaymentId(String fpId) {
	        payment ps= paymentDao.findBySfkId(fpId);
	        ps.setSfkdwname(paymentDao.getSfkdwname(ps.getSfkDw(), ps.getDataCorp()));
	        return ps;
	    }



	@Override
	public void ok(String sfkId) {
		// TODO Auto-generated method stub
		paymentDao.ok(sfkId);
		
	}
	
	@Override
	public void yok(String sfkId) {
		// TODO Auto-generated method stub
		paymentDao.yok(sfkId);
		
	}
	
	@Override
	public void qx(String sfkId) {
		// TODO Auto-generated method stub
		paymentDao.qx(sfkId);
		
	}
	
	@Override
	public void yqx(String sfkId) {
		// TODO Auto-generated method stub
		paymentDao.yqx(sfkId);
		
	}

	@Override
	public ModelAndView table(ModelAndView mv, String sfkId) {
		String sql ="select sfk_note sfknote,sfk_dw sfkdw,f_getname('GETCORPNAME', '', '', data_corp) corpname,f_get_param_name('付款分类',sfk_dtype,'"+   SessionUser.getCurrentCorpId()   +"','财务',false) sfkdtype,f_getname('GETCUSTOMERNAME', sfk_dw, '', data_corp) dwname,to_char(sfk_rq, 'yyyy-MM-dd') sfkrq,to_char(data_date, 'yyyy-MM-dd') datadate,to_char(now(), 'yyyy-MM-dd') nowdate,f_get_param_name('付款方式',sfk_fkfs,'"+   SessionUser.getCurrentCorpId()   +"','财务',false) sfktype,f_get_param_name('交易币种',sfk_bz,'"+   SessionUser.getCurrentCorpId()   +"','财务',false) sfkbz,sfk_shl sfkshl,sfk_bzje sfkbzje,sfk_yfje sfkyfje,sfk_fkje sfkfkje,sfk_mess sfkmess,sfk_sm sfksm,f_getname('GETUSERNAME', data_man, '', data_corp) man from e_cw_sfk where sfk_id = '"+sfkId+"'";
		List<Map> table = paymentDao.QueryListMap(sql);
		mv.addObject("sfknote", table.get(0).get("sfknote"));
		mv.addObject("sfkdtype", table.get(0).get("sfkdtype"));
		mv.addObject("corpname", table.get(0).get("corpname"));
		mv.addObject("dwname", table.get(0).get("dwname"));
		mv.addObject("sfkrq", table.get(0).get("sfkrq"));
		mv.addObject("sfktype", table.get(0).get("sfktype"));
		mv.addObject("sfkbz", table.get(0).get("sfkbz"));
		mv.addObject("nowdate", table.get(0).get("nowdate"));
		
		DecimalFormat df = new DecimalFormat("0.00");
		mv.addObject("sfkshl", df.format(Double.parseDouble(table.get(0).get("sfkshl").toString())));
		mv.addObject("sfkbzje", df.format(Double.parseDouble(table.get(0).get("sfkbzje").toString())));
		mv.addObject("sfkyfje", df.format(Double.parseDouble(table.get(0).get("sfkyfje").toString())));
		mv.addObject("sfkfkje", df.format(Double.parseDouble(table.get(0).get("sfkfkje").toString())));
		
		mv.addObject("sfkmess", table.get(0).get("sfkmess"));
		mv.addObject("sfksm", table.get(0).get("sfksm"));
		mv.addObject("datadate", table.get(0).get("datadate"));
		mv.addObject("man", table.get(0).get("man"));
		
		sql="select customer_bank from sys_customer where customer_id = '"+table.get(0).get("sfkdw")+"'";
		List<Map> bank = paymentDao.QueryListMap(sql);
		if(bank.size() > 0)
			mv.addObject("customerbank", bank.get(0).get("customer_bank"));
		return mv;
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
		util.ExcelToWeb(request, "财务付款", response, exportDao.findList(dto));
	}



	@Override
	public payment getByNote(String note) {
		payment ps= paymentDao.findBySfkNote(note);
        ps.setSfkdwname(paymentDao.getSfkdwname(ps.getSfkDw(), ps.getDataCorp()));
        return ps;
	}
	
}