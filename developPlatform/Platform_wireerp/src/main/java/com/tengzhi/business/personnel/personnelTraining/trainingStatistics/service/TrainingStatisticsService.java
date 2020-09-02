package com.tengzhi.business.personnel.personnelTraining.trainingStatistics.service;


import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface TrainingStatisticsService extends BaseService {

    /**
     * 查询
     * @param baseDto
     * @return
     * */
    BasePage<Map<String,Object>> getList(BaseDto baseDto);

    /*导出xls*/
    void getXls(HttpServletResponse response, HttpServletRequest request);
}
