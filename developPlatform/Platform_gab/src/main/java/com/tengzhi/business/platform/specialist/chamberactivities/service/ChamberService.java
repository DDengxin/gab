package com.tengzhi.business.platform.specialist.chamberactivities.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.specialist.chamberactivities.model.G_chamber;
/**
 * 
 * @author lsh
 *
 */
public interface ChamberService {
	//保存
	Result save(G_chamber g_chamber);
	//查询
    BasePage<G_chamber> findAll(BaseDto baseDto) throws Exception;


}
