package com.tengzhi.app.rk.service;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;

public interface RKFlagService extends BaseService {

    /**
     * 出库按钮把出库状态改为库审
     */
    Result updatePackFlag(String inFlag, String inLib,  String inAct);

    /**
     * 出库按钮把出库状态改为库审(入库扫描页面)
     */
    Result updatePackFlagSM(String inFlag, String inLib,  String inAct);



    /**
     * 批量删除
     */
    Result batchDelete(String pack,String bpack);


}
