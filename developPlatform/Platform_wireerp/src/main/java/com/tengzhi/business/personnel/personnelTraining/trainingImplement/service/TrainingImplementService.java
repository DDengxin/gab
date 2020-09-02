package com.tengzhi.business.personnel.personnelTraining.trainingImplement.service;


import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.personnel.personnelTraining.trainingImplement.vo.TrainingImplementVo;

import java.util.Map;

public interface TrainingImplementService extends BaseService {

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
     * @param TrainingImplementVo
     * @return
     * */
    Result update(TrainingImplementVo TrainingImplementVo);

    /**
     * 刷新grid
     * @param baseDto
     * @return
     * */
    BasePage<Map<String, Object>> getRushGrid(BaseDto baseDto);


    /**
     * 保存
     * @param TrainingImplementVo
     * @return
     * */

    Result save(TrainingImplementVo TrainingImplementVo) throws Exception;

    /**
     * 删除
     * @param jlNote
     * @return
     * */

    void deleteByNote(String jlNote);

}
