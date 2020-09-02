package com.tengzhi.business.mSbJt.by.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.business.mSbJt.by.model.MSbSbwb;
import com.tengzhi.business.personnel.eHrWorker.model.EHrWorker;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.xt.notice.model.EXtNotice;

public interface MSbSbwbService extends BaseService{
	BasePage<MSbSbwb> getSrchButonList(BaseDto baseDto) throws IOException;
	
	MSbSbwb save(MSbSbwb mSbSbwb) throws Exception;
	boolean judgeUnique(MSbSbwb mSbSbwb);
	MSbSbwb findBySbwbNote(String sbwbNote);
	void deleteBySbwbNote(String sbwbNote);
	
	void update(MSbSbwb mSbSbwb);
	
	List<SelectVo> getSbwbDtype(String trueText, String falseText);
	List<SelectVo> getSbwbXtype(String trueText, String falseText);
	List<SelectVo> getSbwbDept(String trueText, String falseText);
	List<SelectVo> getSbwbSman(String trueText, String falseText);
	List<SelectVo> getSbwbSbid(String trueText, String falseText);
	List<SelectVo> getSbwbType(String trueText,String falseText);
	List<SelectVo> getSbwbKtype(String trueText,String falseText);
	
	
	List<SelectVo> getDataMan(String trueText,String falseText);
	List<SelectVo> getSbwbDman(String trueText,String falseText);
	List<SelectVo> getJjcd(String trueText,String falseText);
	
	List<SelectVo> comboParam(String mod,String pareatId);

	boolean sure(String sbwbNote);
	Result getFlag(String sbwbNote,String flag);

	boolean ook(MSbSbwb mSbSbwb);
	boolean conf(MSbSbwb mSbSbwb);
	
	
	boolean okk(MSbSbwb mSbSbwb);

	boolean cancel(String sbwbNote);

	boolean okk(String sbwbNote);

	
	
	
	

	
	

}
