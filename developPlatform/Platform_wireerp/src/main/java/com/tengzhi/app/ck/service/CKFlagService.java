package com.tengzhi.app.ck.service;

import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.system.params.model.SysParams;

import java.util.List;
import java.util.Map;

public interface CKFlagService extends BaseService {
    /**
     * 出库按钮把出库状态改为出库
     */
    Result updateFlag( String out_note,String dataAct);

    /**
     * 出库按钮把出库状态改为库审
     */
    Result updatePackFlag(String out_note,String out_flag,String out_lib,String out_act,String dataAct);

    /**
     * 批量删除
     */
    Result batchDelete(String dataAct,String pack,String bpack);


}
