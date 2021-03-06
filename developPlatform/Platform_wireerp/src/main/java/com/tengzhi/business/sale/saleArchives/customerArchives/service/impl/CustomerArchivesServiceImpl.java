package com.tengzhi.business.sale.saleArchives.customerArchives.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.da.sysCustomer.dao.SysCustomerDao;
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;
import com.tengzhi.business.cg.da.sysCustomer.service.SysCustomerService;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sale.saleArchives.customerArchives.dao.CustomerArchivesDao;
import com.tengzhi.business.sale.saleArchives.customerArchives.service.CustomerArchivesService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.dao.SysParamDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class CustomerArchivesServiceImpl implements CustomerArchivesService{

	@Autowired
	private CustomerArchivesDao customerArchivesDao;
	@Autowired
	private SysParamDao sysParamDao;
	@Autowired
	private SysGenNoteService sysGenNoteService;
	@Autowired
	private SysCustomerDao syscustomerdao;
	@Override
	public BasePage<SysCustomer> findAll(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String customerId = map.get("customerId");
		String customerName = map.get("customerName");
		String customerFlag = map.get("customerFlag");
		String customerExp = map.get("customerExp");
		String customerBuyer = map.get("customerBuyer");
		SecurityUser securityUser=SessionUser.SessionUser();
		String sqlwhere = " where data_corp='"+securityUser.getCorpId()+"' and customer_type='"+map.get("stype")+"' ";
		if(StringUtils.isNotEmpty(customerId)){
			sqlwhere+=" and customer_id like '%"+customerId+"%'";
		}
		if(StringUtils.isNotEmpty(customerName)){ 
			sqlwhere+=" and customer_name like '%"+customerName+"%'";
		}
		if(StringUtils.isNotEmpty(customerFlag)){ 
			sqlwhere+=" and customer_flag = '"+customerFlag+"'";
		}
		if(StringUtils.isNotEmpty(customerExp)){ 
			sqlwhere+=" and customer_exp like  '%"+customerExp+"%'";
		}


		//业务员权限过滤

		if(StringUtils.isNotEmpty(customerId)){
		//if(map.get("stype").equals("C")) {//销售单位限制业务员
			sqlwhere+=" and ( customer_buyer is null or  customer_buyer = '' or customer_buyer = " + SysCustomerService.getBusinessIdsWhereBusinessSqlFragment(map.get("srchYwy"))+" ) ";
		}
		/*//业务员权限
		String businessPersonnelIdsSqlFragment = SessionUser.SessionUser().getBusinessPersonnelIdsSqlFragment(customerBuyer);
		if(null != businessPersonnelIdsSqlFragment) {
			sqlwhere += " and customer_buyer  in (" + businessPersonnelIdsSqlFragment + ") ";
		}else{
			sqlwhere = " and 1=2 ";
		}*/
		
		String sql="select customer_id,customer_name,customer_exp,f_get_param_name('业务员',customer_buyer,'"+   SessionUser.getCurrentCorpId()   +"','')customer_buyer,customer_province,customer_uid,customer_flag,data_man,data_rq,f_get_param_name('业务员',customer_buyer,'"+   SessionUser.getCurrentCorpId()   +"','') customerbuyername,"
				+ "customer_je,customer_day,f_get_param_name('客户等级',customer_level,'"+   SessionUser.getCurrentCorpId()   +"','')customer_level,f_getname('GETUSERNAME', data_man, '', data_corp) datamanname,"
				+ "f_getname('GETDWEXP', customer_uid, '',data_corp) lastcustomername,f_get_param_name('区域城市',customer_province,'"+   SessionUser.getCurrentCorpId()   +"','') customerprovincename from sys_customer"+sqlwhere+" order by  customer_id";
		String countSql = "select count(*) cn from (" + sql + ")t ";
		return customerArchivesDao.QueryPageLists(sql, countSql, baseDto);
	}
	@Override
	public SysCustomer save(SysCustomer sysCustomer) throws Exception {
		SessionUser securityUser=SessionUser.SessionUser();
		String paramValue1 = sysParamDao.getParamValue1("公司类型", "XSKF");
		sysCustomer.setCustomerId(sysGenNoteService.getNote(SysCustomer.class, paramValue1, "", 4));
		if(judgeUnique(sysCustomer)) {
			sysCustomer.setDataMan(securityUser.getUsername());
			sysCustomer.setDataRq(new Date());
			sysCustomer.setDataCorp(securityUser.getCorpId());
			sysCustomer.setCustomerType("C");
			//sysCustomer.setCustomerUid(sysCustomer.getCustomerId());//上级单位    ps:上级单位不能设置成本身的编码，会造成客户档案选择框列表显示不出来的问题
			return customerArchivesDao.save(sysCustomer);
		}else {
			throw new Exception("编码已存在");
		}
	}

	@Override
	public boolean judgeUnique(SysCustomer sysCustomer) {
		SessionUser sessionUser=SessionUser.SessionUser();
		return customerArchivesDao.findAll(Specifications.where((c)->{
			c.eq("customerId", sysCustomer.getCustomerId());
			c.eq("dataCorp",sessionUser.getCorpId());
		})).size()<=0;
	}
	@Override
	public SysCustomer findByCustomerId(String customerId) {
		SysCustomer sys = customerArchivesDao.findByCustomerId(customerId);
		sys.setLastcustomername(syscustomerdao.lastCustomername(sys.getCustomerUid(), sys.getDataCorp()));
		return sys;
	}
	@Override
	public void update(SysCustomer sysCustomer) {
		customerArchivesDao.dynamicSave(sysCustomer, customerArchivesDao.findByCustomerId(sysCustomer.getCustomerId()));
	}
	@Override
	public void deleteByCustomerId(String customerId) {
		customerArchivesDao.deleteById(customerId);
	}
	@Override
	public List<SelectVo> getCustomerAddressList(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}


}
