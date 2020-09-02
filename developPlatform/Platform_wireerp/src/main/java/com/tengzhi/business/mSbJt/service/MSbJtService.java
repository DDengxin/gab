package com.tengzhi.business.mSbJt.service;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContractDetailed;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ECgContractVo;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ExamineVo;
import com.tengzhi.business.mSbJt.model.MSbJt;
import com.tengzhi.business.mSbJt.model.MSbJtbj;
import com.tengzhi.business.mSbJt.vo.MSbJtVo;
import com.tengzhi.business.resouces.vo.SelectVo;

public interface MSbJtService extends BaseService {
	BasePage<Map<String, Object>> getSrchTopList(BaseDto baseDto) throws IOException;
	
	BasePage<MSbJtbj> getSrchBottomList(BaseDto baseDto) throws IOException;
	
	MSbJt save(MSbJtVo sysRightVo) throws Exception;
	
	void update(MSbJtVo mSbJtVo)throws Exception;
	
	
	MSbJtbj findById(String machineId, Integer machindBid);
	MSbJt findById2(String machineId);
	
	void deleteById(String machineId);
	//下拉框
	List<SelectVo> getMachineDtype(String trueText, String falseText);
	List<SelectVo> getMachineZtype(String trueText, String falseText);
	List<SelectVo> getMachineXtype(String trueText, String falseText);
	List<SelectVo> getMachineProcess(String trueText, String falseText);
	List<SelectVo> comboParam(String mod,String pareatId);
	
	List<SelectVo> getJtList(String gxCj);
	
	List<SelectVo> getGxJtList(String gxCj);
	
	List<SelectVo> getJtAllList();
	 
}
