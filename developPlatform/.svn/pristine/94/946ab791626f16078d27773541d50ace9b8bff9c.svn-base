package com.tengzhi.business.mSbJt.wx.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.business.mSbJt.by.model.MSbSbwb;
import com.tengzhi.business.mSbJt.wx.model.MSbSbwbb;
import com.tengzhi.business.personnel.eHrWorker.model.EHrWorker;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.xt.notice.model.EXtNotice;

public interface MSbSbwbServicee extends BaseService{
	
	BasePage<MSbSbwbb> findAll(BaseDto baseDto) throws IOException;
	
	MSbSbwbb save(MSbSbwbb mSbSbwb) throws Exception;
	boolean judgeUnique(MSbSbwbb mSbSbwb);
	Map<String, Object> findBySbwbNote(String sbwbNote);
	void deleteBySbwbNote(String sbwbNote);
	
	void update(MSbSbwbb mSbSbwb);
	
	List<SelectVo> getSbwbDtype(String trueText, String falseText);
	List<SelectVo> getSbwbXtype(String trueText, String falseText);
	List<SelectVo> getSbwbDept(String trueText, String falseText);
	List<SelectVo> getSbwbSman(String trueText, String falseText);
	List<SelectVo> getSbwbSbid(String trueText, String falseText);
	
	List<SelectVo> comboParam(String mod,String pareatId);

	boolean sure(String sbwbNote);
	boolean okk(String sbwbNote);
	boolean conf(String sbwbNote);
	

}
