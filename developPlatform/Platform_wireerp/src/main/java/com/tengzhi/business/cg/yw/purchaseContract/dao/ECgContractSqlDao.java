package com.tengzhi.business.cg.yw.purchaseContract.dao;

import java.util.List;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
public interface ECgContractSqlDao  extends BasicsDao<ECgContract, String>{
	List findList(BaseDto dto);


}
