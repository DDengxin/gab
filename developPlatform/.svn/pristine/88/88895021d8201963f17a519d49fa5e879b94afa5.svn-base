package com.tengzhi.business.system.dept.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.dept.model.SysDept;

import java.io.IOException;
import java.util.List;

public interface SysDeptService extends BaseService {


    BasePage<SysDept> findAll(BaseDto baseDto) throws IOException;

    SysDept findByDeptId(String deptId);

    SysDept save(SysDept sysDept) throws Exception;

    void update(SysDept sysDept);

    Result deleteByTierId(String tierId);

    boolean judgeUnique(SysDept sysDept);

    List<SysDept> getComboboxList(Boolean state);

    List<SysDept> getComboboxList(Boolean state,String corpId);

    /**
     * 该请求已经废弃(2020-08-03)
     * @param parentId
     * @return
     */
    @Deprecated
    List<SelectVo> getComboboxList(String parentId);

    /**
     * 获取组织架构树
     * @return
     */
    Result getOrganizationTree(boolean includeEmployees);
}
