package com.tengzhi.business.personnel.personnelTraining.trainingReport.service;


import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.personnel.personnelTraining.trainingImplement.vo.TrainingImplementVo;
import com.tengzhi.business.personnel.personnelTraining.trainingNotice.vo.TrainingNoticeVo;
import com.tengzhi.business.personnel.personnelTraining.trainingReport.model.EHrTrainingReport;
import com.tengzhi.business.project.projectArchives.xmda.model.EXmXmda;

import java.util.Map;

public interface TrainingReportService extends BaseService {

    /**
     * 查询
     * @param baseDto
     * @return
     * */
    BasePage<Map<String,Object>> getList(BaseDto baseDto);
  /*  BasePage<Map<String,Object>> getPersonList(BaseDto baseDto);*/

    /**
     * 根据Note获取数据
     * @param jlNote
     * */
    Map<String, Object> getByNote(String jlNote);
    /**
     * 修改
     * @param eHrTrainingReport
     * @return
     * */
    void update(EHrTrainingReport eHrTrainingReport);

}
