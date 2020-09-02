package com.tengzhi.business.system.position.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.system.position.model.SysPosition;

import java.io.IOException;
import java.util.Map;


/**
 * 岗位已弃用，请不要使用
 */
@Deprecated
public interface SysPositionService extends BaseService {


    BasePage<SysPosition> findAll(BaseDto baseDto) throws IOException;

    SysPosition findByPositionId(String positionId);

    SysPosition save(SysPosition sysPosition) throws Exception;

    void update(SysPosition sysPosition);

    Result deleteByPositionId(String positionId,String tierId);

    boolean judgeUnique(SysPosition sysPosition);

    void PositionAuthSave(Map<String,Object> map);
    /**
     * @Param: [baseDto]
     * @return: com.tengzhi.base.jpa.page.BasePage<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: 王翔
     * @Date: 2020/4/21 20:01
     * @description: 常规原生Sql实现授权角色查询
     */
    BasePage<Map<String, Object>> findSysPositionRightAll(BaseDto baseDto)throws IOException;


}
