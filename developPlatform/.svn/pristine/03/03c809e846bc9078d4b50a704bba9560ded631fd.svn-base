package com.tengzhi.business.finance.voucher.service;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.voucher.model.VchTemplate;
import com.tengzhi.business.finance.voucher.vo.BsCategoryVo;
import com.tengzhi.business.finance.voucher.vo.VchTemplateVo;


public interface VchTemplateService extends BaseService{
	BasePage<Map<String,Object>> findAll(BaseDto baseDto);
	BasePage<Map<String, Object>> getMxList(BaseDto baseDto) throws IOException;
	Result saveData(VchTemplateVo vo );
	Result deleteById(String Id);
	Map<String,Object> getById(String Id);

}
