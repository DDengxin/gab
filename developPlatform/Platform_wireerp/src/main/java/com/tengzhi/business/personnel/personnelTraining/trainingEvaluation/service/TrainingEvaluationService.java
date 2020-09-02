package com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.service;


import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.personnel.personnelTraining.trainingEvaluation.vo.trainingEvaluationVo;

import java.util.Map;

public interface TrainingEvaluationService extends BaseService {

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
     * @param trainingEvaluationVo
     * @return
     * */
    Result update(trainingEvaluationVo trainingEvaluationVo);

    /**
     * 刷新grid
     * @param baseDto
     * @return
     * */
    BasePage<Map<String, Object>> getRushGrid(BaseDto baseDto);


    /**
     * 保存
     * @param trainingEvaluationVo
     * @return
     * */

    Result save(trainingEvaluationVo trainingEvaluationVo) throws Exception;

    /**
     * 删除
     * @param jlNote
     * @return
     * */

    void deleteByNote(String jlNote);

}
