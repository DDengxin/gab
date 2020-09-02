package com.tengzhi.business.sc.pd.bzgy.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.sc.pd.bzgy.dao.BzgyDao;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Auther: 龚行礼
 * @Date: 2020/8/24 0013 10:06
 * @Description:标准工艺
 */
@Service
@Transactional(rollbackFor = Exception.class)//发生异常事务回滚
public class BzgyServiceImpl implements BzgyService {

    private BzgyDao bzgyDao;

    private SysGenNoteService sysGenNoteService;

    public BzgyServiceImpl(BzgyDao bzgyDao, SysGenNoteService sysGenNoteService) {
        this.bzgyDao = bzgyDao;
        this.sysGenNoteService = sysGenNoteService;
    }

    /**
     *@role:
     *@Author:      gongxingli
     *@Date:        2020/8/24 0012 10:53
     *@Param:       [baseDto]
     *@return:      com.tengzhi.base.jpa.result.Result
     *@Description: list页面查询
     **/  @Override
    public BasePage<Map<String, Object>> findAll(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String whereJudge = SqlJoint.whereSuffixWhere((c) ->{
            c.eq("data_corp", SessionUser.getCurrentCorpId());
            c.contains("gy_id",map.get("gyId"));
            c.eq("gy_syb",map.get("gySyb"));
            c.contains("gy_type",map.get("gyType"));
            c.contains("gy_ggno",map.get("gyGgno"));
        });
        String sqlString = "select gy_id, gy_syb,f_getparamname('GETTYPEBYRAMNAME',gy_syb,'生产车间','','','"+SessionUser.getCurrentCorpId()+"') syb_name, gy_type,(select string_agg( param_name,',') from sys_param c where strpos(a.gy_type,c.param_id)>0 and  c.param_mod='技术'  ) type_name, gy_ggno, gy_min, gy_max, gy_tlcode1, gy_tlcode2, gy_tlcode3, gy_sm, data_corp " +
               " from m_sc_gsbjgy a"+whereJudge;
        return bzgyDao.QueryMapPageList(baseDto, sqlString+" order by gy_syb desc,gy_id desc ", true);
    }
}
