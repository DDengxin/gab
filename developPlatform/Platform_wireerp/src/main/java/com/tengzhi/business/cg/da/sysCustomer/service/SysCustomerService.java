package com.tengzhi.business.cg.da.sysCustomer.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;
import org.apache.commons.lang3.StringUtils;

public interface SysCustomerService extends BaseService<SysCustomer,String>{
	
	BasePage<SysCustomer> findAll(BaseDto basedto) throws IOException;
	
	SysCustomer save(SysCustomer sysCustomer) throws Exception;
	
	SysCustomer findByCustomerId(String customerId);
	
	void update(SysCustomer sysCustomer);
	
	void deleteByCustomerId(String ywCode);
	
	
	boolean judgeUnique(SysCustomer sysCustomer);
	
	List<Map> getTreeList(String mod, String type);
	
	List<Map> getCityList(String mod,String type);

	/**
	 * 【业务】获取限制业务人员的sql语句片段(与客户进行匹配)
	 * @exp：select * from sys_customer where customer_id = SysCustomerService.getBusinessIdsWhereCustomerSqlFragment();
	 * @param businessPersonnelIds 业务员 未null时候返回当前用户拥有的所有业务员权限
	 * @return
	 */
	static String getBusinessIdsWhereCustomerSqlFragment(Object businessPersonnelIds){
		SessionUser sessionUser = SessionUser.SessionUser();
		String fragment = "any( "
				+"select cast(_bussiness_customer_.customer_id as varchar) "
				+"from sys_customer as _bussiness_customer_"
				//如果客户没有绑定业务员则不管控
				+" where (_bussiness_customer_.customer_buyer is null or _bussiness_customer_.customer_buyer  = '') "
				//通过业务员ID限制客户
				+" or _bussiness_customer_.customer_buyer  in (select _inner_bussiness_users_.id from f_get_business_authority('%s','%s') as _inner_bussiness_users_) "
				+") ";
		fragment = String.format(fragment, sessionUser.getUserId(),null == businessPersonnelIds ? "" : businessPersonnelIds.toString());
		return fragment;
	}
	/**
	 * 【业务】获取限制业务人员的sql语句片段(直接与业务员ID进行匹配)
	 * @exp：select * from sys_customer where customer_buyer = SysCustomerService.getBusinessIdsWhereBusinessSqlFragment();
	 * @param businessPersonnelIds 业务员 未null时候返回当前用户拥有的所有业务员权限
	 * @return
	 */
	static String getBusinessIdsWhereBusinessSqlFragment(Object businessPersonnelIds){
		SessionUser sessionUser = SessionUser.SessionUser();
		String fragment = "any( "
				+"select _bussiness_users_.id from f_get_business_authority('%s','%s') as _bussiness_users_"
				+") ";
		fragment = String.format(fragment, sessionUser.getUserId(),null == businessPersonnelIds ? "" : businessPersonnelIds.toString());
		return fragment;
	}

}
