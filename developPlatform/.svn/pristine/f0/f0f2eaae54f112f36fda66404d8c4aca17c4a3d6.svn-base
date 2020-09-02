package com.tengzhi.business.sc.da.staffOvertime.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.personnel.eHrWorker.model.EHrWorker;
import com.tengzhi.business.sc.da.staffOvertime.model.StaffOvertime;

import java.io.IOException;

/**
 * @Auther: huangbiao
 * @Date: 2020/8/4 0004 18:42
 * @Description:
 */

public interface StaffOvertimeService {

    BasePage<StaffOvertime> findAll(BaseDto baseDto);

    Result deleteById(String id);

    BasePage<StaffOvertime> findProudction(BaseDto baseDto) throws IOException;

    Result updateFlag(String jbqj_id);

}
