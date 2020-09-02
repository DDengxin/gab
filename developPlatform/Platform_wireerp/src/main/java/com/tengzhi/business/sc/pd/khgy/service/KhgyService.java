package com.tengzhi.business.sc.pd.khgy.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContract;
import com.tengzhi.business.sale.processProduct.processContract.vo.ProcessContractVo;
import com.tengzhi.business.sc.pd.khgy.vo.MScKfgylistGcVo;

import java.io.IOException;
import java.util.Map;

/**
 * @Auther: 龚行礼
 * @Date: 2020/8/24 0013 21:45
 * @Description:客户工艺
 */
public interface KhgyService extends BaseService {
	BasePage<Map<String, Object>> getSrchTopList(BaseDto baseDto) throws IOException;

	BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;

	BasePage<Map<String, Object>> getByscgy(BaseDto baseDto) throws IOException;

	Result saveOrUpdate(MScKfgylistGcVo mScKfgylistGcVo);;

}
