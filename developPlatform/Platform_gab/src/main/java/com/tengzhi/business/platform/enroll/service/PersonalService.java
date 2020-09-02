package com.tengzhi.business.platform.enroll.service;

import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.platform.enroll.vo.modelVo;
import com.tengzhi.business.system.user.model.SysUser;
import com.tengzhi.business.system.workflow.vo.Examine;

@SuppressWarnings("rawtypes")
public interface PersonalService  extends BaseService{
	
	
	BasePage<Map<String, Object>> getList(BaseDto dto);

	
	modelVo personById( String  personNote) throws Exception;

	
	//修改
   Result update(modelVo vo);
	
	//查询单条记录
	Result personalone(String id);
	
	//审核通过授权
	Result agree(Examine examine) throws Exception;
	
	//平台用户新增客户
	Result  save(modelVo modelVo) throws Exception;
	
	void deleteByPersonNote(String note);
	
	//获取状态
		Result  getPersonalStatus(String expertNote,String status);

	Result  getPersonalStatus(String expertNote);
}
