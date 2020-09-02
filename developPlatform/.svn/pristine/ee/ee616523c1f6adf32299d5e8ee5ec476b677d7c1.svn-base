package com.tengzhi.app.ck.service.impl;


import com.tengzhi.app.ck.dao.CKDao;
import com.tengzhi.app.ck.dao.CKFlagDao;
import com.tengzhi.app.ck.model.EckOutLs;
import com.tengzhi.app.ck.service.CKFlagService;
import com.tengzhi.app.ck.service.CKService;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseSettle.dao.ECwYsyfDao;
import com.tengzhi.business.ck.yw.ckck.dao.ECkOutDao;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.system.params.model.SysParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class CKFlagServiceImpl implements CKFlagService {
    @Autowired
    CKFlagDao ckFlagDao;
    
    @Autowired
    ECkOutDao eCkOutDao;
    
    @Autowired
	private ECwYsyfDao eCwYsyfDao;
    /**
     * 出库按钮把出库状态改为出库
     */
    @Override
    public Result updateFlag( String out_note,String dataAct) {
    	
    	SessionUser securityUser=SessionUser.SessionUser();
    	eCkOutDao.out(out_note,securityUser.getUserId());
    	eCwYsyfDao.setFlag(out_note,"库审");
        ckFlagDao.updateFlag("库审",out_note,dataAct);
        return  Result.resultOk();
    }
    /**
     * 出库按钮把出库状态改为库审
     */
    @Override
    public Result updatePackFlag(String out_note,String out_flag, String out_lib, String out_act, String dataAct) {
        ckFlagDao.updatePackFlag(out_flag,out_lib,out_act,dataAct,out_note);
        return  Result.resultOk();
    }

    /**
     * 批量删除
     */
    @Override
    public Result batchDelete(String dataAct,String pack,String bpack) {
        //小包装删除
        if (!StringUtils.isBlank(pack)) {
            ckFlagDao.deletePack(pack,dataAct);
        }
        //大包装删除
        if (!StringUtils.isBlank(bpack)) {
            ckFlagDao.deleteBPack(bpack,dataAct);
        }
        return  Result.resultOk();

    }
}
