package com.tengzhi.business.sc.pd.scrw.service;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContract;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContractDetailed;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContractDetailedWl;
import com.tengzhi.business.sale.processProduct.processContract.vo.ProcessContractVo;
import com.tengzhi.business.sale.processProduct.processContract.vo.ProcessContractWlVo;

public interface ScrwpdService extends BaseService<EXsContract, String> {

	BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;

	BasePage<Map<String, Object>> getScddList(BaseDto basedto) throws IOException;

	Map<Object,String> getBySplitId(String htMo);
	
	Map<Object,String> getByHpId(String htMo);

	Result xsddSplit(MScScrw model)throws Exception;

	Result xsddHp(MScScrw model)throws Exception;

	Result getFlag(String htNo,String flag);

}
