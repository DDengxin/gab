package com.tengzhi.business.sc.pd.htps.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import com.tengzhi.business.sale.saleProduct.saleContract.vo.EXsContractVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface HtpsService extends BaseService<EXsContract, String> {

	BasePage<EXsContract> findAll(BaseDto basedto) throws IOException;

	List<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;

	EXsContract findByNote(String htNote);
	
	Result getFlag(String htNo,String flag);
	
	Result confirm(String htNo,String htMo,String htJq);
	
	Result cancel(String htNo );
	
	Result reply(EXsContractVo vo) ;
	

}
