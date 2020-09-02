package com.tengzhi.business.sale.saleArchives.customerAddr.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.da.sysCustomer.service.SysCustomerService;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sale.saleArchives.customerAddr.dao.CustomerAddrDao;
import com.tengzhi.business.sale.saleArchives.customerAddr.model.ECwCustomerAddress;
import com.tengzhi.business.sale.saleArchives.customerAddr.service.CustomerAddrService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CustomerAddrServiceImpl implements CustomerAddrService {

	@Autowired
	private CustomerAddrDao customerAddrDao;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;
	
	@Override
    public BasePage<Map<String, Object>> search_option(BaseDto baseDto) throws IOException {
		SessionUser sessionUser=SessionUser.SessionUser();
        Map<String, String> map = baseDto.getParamsMap();
        String where = " where 1=1 and data_corp='"+sessionUser.getCorpId()+"' ";
        if (StringUtils.isNotBlank(map.get("srchCustomer"))) {
            where += " AND customer_id = '" + map.get("srchCustomer") + "'";
        }
		//业务员权限过滤
		where += " and customer_id = " + SysCustomerService.getBusinessIdsWhereCustomerSqlFragment(null);
        if (StringUtils.isNotBlank(map.get("srchAddress"))) {
            where += " AND address like '%" + map.get("srchAddress") + "%'";
        }
        if (StringUtils.isNotBlank(map.get("srchContacts"))) {
            where += " AND contacts like '%" + map.get("srchContacts") + "%'";
        }



		String sql = "select address_note,customer_three_name,customer_id,f_getname('GETCUSTOMEREXP',customer_id,'','"+sessionUser.getCorpId()+"') customer_name,address,contacts,sort_ord,gen_time,gen_user_id,f_getname('GETUSERNAME',gen_user_id,'','"+sessionUser.getCorpId()+"') user_name,contact_method from e_cw_customer_address " + where + " order by customer_id,sort_ord asc";
        return customerAddrDao.QueryMapPageList(baseDto,sql, true);
	}
	
	@Override
	public Result save(ECwCustomerAddress eCwCustomerAddress) throws Exception {
		ECwCustomerAddress Class ;
		SessionUser sessionUser=SessionUser.SessionUser();
		Class = getOne(eCwCustomerAddress.getCustomerId(),eCwCustomerAddress.getSortOrd()+"");
		if(Class==null) {
			eCwCustomerAddress.setDataCorp(sessionUser.getCorpId());
			eCwCustomerAddress.setAddressNote(sysGenNoteService.getNote(ECwCustomerAddress.class, "ADD", "yyMM", 3));
			eCwCustomerAddress.setGenUserId(SessionUser.SessionUser().getUserId());
			eCwCustomerAddress.setGenTime(new Date());
			customerAddrDao.save(eCwCustomerAddress);
			return Result.resultOk();
		}else {
			return Result.error("该客户排序重复");
		}
	}
	
	@Override
	public Result update(ECwCustomerAddress eCwCustomerAddress) throws Exception {
		eCwCustomerAddress.setGenUserId(SessionUser.SessionUser().getUserId());
		eCwCustomerAddress.setGenTime(new Date());
		customerAddrDao.dynamicSave(eCwCustomerAddress,customerAddrDao.findById(eCwCustomerAddress.getAddressNote()).orElse(null));
		return Result.resultOk();
	}
		
	@Override
	public ECwCustomerAddress getOne(String customerId, String sortOrd) {
		SessionUser sessionUser=SessionUser.SessionUser();
		return customerAddrDao.getOne(customerId,Integer.parseInt(sortOrd),sessionUser.getCorpId());
	}
	
	@Override
	public String getcustomerName(String customerId) {
		SessionUser sessionUser=SessionUser.SessionUser();
		return customerAddrDao.getcustomerName(customerId,sessionUser.getCorpId());
	}
	
	@Override
	public int deleteByNote(String customerId, String sortOrd) {
		SessionUser sessionUser=SessionUser.SessionUser();
		return customerAddrDao.deleteByNote(customerId,Integer.parseInt(sortOrd),sessionUser.getCorpId());
	}

	@Override
	public List<SelectVo> getAddressByCustomer(String customerId) {
		SessionUser sessionUser=SessionUser.SessionUser();
		List<ECwCustomerAddress> customerAddress = customerAddrDao.QueryListModel(ECwCustomerAddress.class, "select * from e_cw_customer_address where customer_id='"+customerId+"' and data_corp='"+sessionUser.getCorpId()+"' ORDER BY sort_ord asc");
        List<SelectVo> list = new ArrayList<SelectVo>();
        customerAddress.forEach(item -> {
            list.add(new SelectVo(item.getAddressNote(), item.getAddress()));
        });
        return list;
	}

	public int deleteByNote(String addressNote) {
		return customerAddrDao.deleteByNote(addressNote);

	}

	public ECwCustomerAddress getByNote(String addressNote) {
		return customerAddrDao.getByNote(addressNote);
	}
	
}
