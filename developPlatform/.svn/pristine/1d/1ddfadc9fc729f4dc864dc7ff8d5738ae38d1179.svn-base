package com.tengzhi.business.sale.saleProduct.saleSettle.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseSettle.model.ECwYsyf;
import com.tengzhi.business.cg.yw.purchaseSettle.vo.ECwYsyfVo;
import com.tengzhi.business.mesGz.gzck.vo.EckOut;
import com.tengzhi.business.mesGz.gzck.vo.EckOut.ECkOut_PK;

public interface SaleSettleService extends BaseService<EckOut, ECkOut_PK> {

	
	BasePage<EckOut>  getSrchTopList(BaseDto baseDto) throws IOException;
	
	BasePage<Map<String, Object>>  getSettleList(BaseDto baseDto) throws IOException;
	
	Result getByNote(String cwNote);

	Result settle(ECwYsyfVo eCwYsyfVo) throws IOException;
	
	Result cancel(String outNote) throws IOException;
	ModelAndView table(ModelAndView mv, String outNote,String addressNote)   ;

	BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;
}
