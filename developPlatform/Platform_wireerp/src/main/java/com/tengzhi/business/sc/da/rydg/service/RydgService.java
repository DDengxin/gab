package com.tengzhi.business.sc.da.rydg.service;

import java.io.IOException;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sc.da.rydg.model.MRyYgdg;
import com.tengzhi.business.sc.da.rydg.model.MRyYgdg.MRyYgdg_PK;
import com.tengzhi.business.sc.da.rydg.vo.MRyYgdgVo;

public interface RydgService extends BaseService<MRyYgdg,MRyYgdg_PK>{
	
	BasePage<MRyYgdg> getSrchList(BaseDto basedto) throws IOException;

	//获取所有部门或父级部门为生产部的人员信息
	BasePage<MRyYgdg> getSrchProductionList(BaseDto baseDto) throws IOException;
	
	MRyYgdg save(MRyYgdgVo mRyYgdgVo) throws Exception;
	
	Result update(MRyYgdgVo mRyYgdgVo);

	Result saveOrUpdate(MRyYgdgVo mRyYgdgVo);
	
	MRyYgdg findByPkId(BaseDto baseDto)throws IOException;
	
	void deleteAll(String id)throws IOException;

	BasePage<MRyYgdg> getDeptGridList(String deptId);

	BasePage<MRyYgdg> getDeptGridListToSysUser(String deptId);
	

}
