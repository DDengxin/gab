package com.tengzhi.business.personnel.personnelTraining.trainingNotice.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.mSbJt.inspectionRecord.vo.InspectionVo;
import com.tengzhi.business.personnel.personnelTraining.trainingNotice.model.EHrTrainingNotice;
import com.tengzhi.business.personnel.personnelTraining.trainingNotice.vo.TrainingNoticeVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface TrainingNoticeService extends BaseService {

    /**
     * 查询
     * @param baseDto
     * @return
     * */
    BasePage<Map<String,Object>> getList(BaseDto baseDto);

    /**
     * 根据Note获取数据
     * @param tzAddress
     * */
    Map<String,Object> getByNote(String tzAddress);


    /**
     * 修改
     * @param trainingNoticeVo
     * @return
     * */
    Result update(TrainingNoticeVo trainingNoticeVo);

    /**
     * 保存
     * @param trainingNoticeVo
     * @return
     * */

    Result save(TrainingNoticeVo trainingNoticeVo) throws Exception;

    /**
     * 删除
     * @param tzNote
     * @return
     * */

    void deleteByNote(String tzNote);

    //刷新Grid
    BasePage<Map<String, Object>> getRushGrid(BaseDto baseDto);

}
