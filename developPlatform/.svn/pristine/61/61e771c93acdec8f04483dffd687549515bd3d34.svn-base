package com.tengzhi.business.system.fileauth.service;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;

public interface FileAuthService{
	
	Result findAll(BaseDto dto);
	BasePage<Map<String, Object>> findUserRightAll(BaseDto baseDto)throws IOException;
	void FileAuthSave(Map<String,Object> map);
	void delete(String arNote);

}
