package com.tengzhi.business.production.subcontract.wwdz.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface WwdzService  extends BaseService {

    Map<String, Object> getDataList(BaseDto baseDto) throws IOException;

    Map<String, Object> getYSList(BaseDto baseDto) throws IOException;

    void exportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException;
}
