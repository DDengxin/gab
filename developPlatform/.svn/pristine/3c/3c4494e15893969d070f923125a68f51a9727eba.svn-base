package com.tengzhi.app.rk.service.impl;


import com.tengzhi.app.ck.dao.CKFlagDao;
import com.tengzhi.app.ck.service.CKFlagService;
import com.tengzhi.app.rk.dao.RKFlagDao;
import com.tengzhi.app.rk.service.RKFlagService;
import com.tengzhi.base.jpa.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class RKFlagServiceImpl implements RKFlagService {
    @Autowired
    RKFlagDao rkFlagDao;


    /**
     * 出库按钮把出库状态改为库审
     */
    @Override
    public Result updatePackFlag(String inFlag, String inNote,  String dataAct) {
        rkFlagDao.updatePackFlag(inFlag,inNote,dataAct);
        return  Result.resultOk();
    }

    /**
     * 出库按钮把出库状态改为库审(入库扫描页面)
     */
    @Override
    public Result updatePackFlagSM(String inFlag, String inLib,  String inAct) {
        rkFlagDao.updatePackFlagSM(inFlag,inLib,inAct);
        return  Result.resultOk();
    }



    /**
     * 批量删除
     */
    @Override
    public Result batchDelete(String pack,String bpack) {
    	//小包装删除
        if (!StringUtils.isBlank(pack)) {
            rkFlagDao.deletePack(pack);
        }
        //大包装删除
        if (!StringUtils.isBlank(bpack)) {
            rkFlagDao.deleteBPack(bpack);
        }
        return  Result.resultOk();

    }


}
