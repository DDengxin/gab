package com.tengzhi.business.system.initdata.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.system.initdata.model.SysDbDo;

public interface SysInitDataService extends BaseService {

    Result upload(MultipartFile files,String dbTable,String dbName, String dataType) throws Exception;
	BasePage<Map<String, Object>> getSrchTopList(BaseDto baseDto) throws Exception;
    Map<String, Object> getSrchBottomNList(BaseDto baseDto)throws IOException;
    Result callSolveDataProduce();

    Result callSysDataInitProduce();
}
