package com.tengzhi.business.system.modification.service;


import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.system.modification.model.SysDataModification;
import com.tengzhi.business.system.workflow.vo.Examine;

import java.util.Map;

public interface SysDataModificationService extends BaseService {
    /**
     * 查询列表数据
     * @param baseDto
     * @return
     */
    BasePage<Map<String,Object>> getList(BaseDto baseDto);

    /**
     * 保存数据
     * @param map
     * @return
     */
    Result save(Map<String,Object> map) throws Exception;


    Result modifySearch(BaseDto baseDto);

    SysDataModification findByID(String Id);

    /**
     * 修改数据
     * @param sysDataModification
     * @return
     */
    Result update(Map<String, Object> map) throws Exception;

    /**
     * 通过主键删除数据
     * @param modifyNote
     * @return
     */
    Result deleteById(String modifyNote);


    //通过走审批动态修改数据
    Result dynamicModify(Examine examine);


}
