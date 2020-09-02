package com.tengzhi.business.production.subcontract.wwht.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContractDetailed;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ECgContractVo;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContract;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContractDetailed;
import com.tengzhi.business.sale.processProduct.processContract.vo.ProcessContractVo;

public interface WwhtService extends BaseService<EXsContract, String> {

	BasePage<EXsContract> findAll(BaseDto basedto) throws IOException;

	BasePage<EXsContractDetailed> getSrchBottomList(BaseDto baseDto) throws IOException;

	EXsContract save(ProcessContractVo EXsContractvo) throws Exception;

	EXsContract findByNote(String htNote);

	void update(ProcessContractVo eXsContractVo);

	void deleteByNote(String htNo);

	Result confirm(String htNote);

	Result cancel(String htNote);

	Result getFlag(String htNo, String flag);

	Result getFlags(String htNo, String flag);

	BasePage<EXsContractDetailed> getContractDetailed(BaseDto baseDto) throws IOException;

	ModelAndView table(ModelAndView mv, String htNo,String ywType);

	BasePage<EXsContractDetailed> getScddList(BaseDto basedto) throws IOException;

	Map<Object, String> getBySplitId(String htMo);

	Map<Object, String> getByHpId(String htMo);

	/**
	 * 核销
	 * 
	 * @param htNo
	 * @return
	 */
	Result hx(String htNo);

	Result qxhx(String htNo);

	/**
	 * 可用的合同明细
	 * 
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<EXsContractDetailed> getUsableContractDetailed(BaseDto baseDto) throws IOException;

	
	
	
}
