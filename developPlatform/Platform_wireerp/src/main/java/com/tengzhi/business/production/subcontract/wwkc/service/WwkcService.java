package com.tengzhi.business.production.subcontract.wwkc.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface WwkcService  extends BaseService {

    BasePage<Map<String, Object>> getSrchTopList(String type, BaseDto baseDto) throws Exception;

    ModelAndView table(String incontract, String pack, String code, String type, ModelAndView mv);

    BasePage<Map<String, Object>> getVStock(BaseDto baseDto) throws Exception;

    Result exportExcel(HttpServletResponse response, HttpServletRequest request, String type, String parms) throws IOException;

    Result getPrintPackList(String packs);

}
