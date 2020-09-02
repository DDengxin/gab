package com.tengzhi.business.sc.da.leave.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.page.impl.BasePageImpl;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.da.leave.dao.EHrJbqjDao;
import com.tengzhi.business.sc.da.leave.model.EHrJbqj;
import com.tengzhi.business.sc.da.leave.service.EHrJbqjService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/11 0011 14:23
 * @Description:
 */

@Service
public class EHrJbqjServiceImpl implements EHrJbqjService {

    private EHrJbqjDao eHrJbqjDao;

    @Autowired
    public EHrJbqjServiceImpl(EHrJbqjDao eHrJbqjDao){
        this.eHrJbqjDao=eHrJbqjDao;
    }

    @Override
    public BasePage<EHrJbqj> findAll(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String sqlWhere="  ";
        if(StringUtils.isNotEmpty(map.get("qjNote"))) {
            sqlWhere += " and a.qj_note = '"+map.get("qjNote")+"'";
        }
        if(StringUtils.isNotEmpty(map.get("workerId"))) {
            sqlWhere += " and a.worker_id like '%"+map.get("workerId")+"%' ";
        }
        if(StringUtils.isNotEmpty(map.get("workerName"))) {
            sqlWhere += " and b.worker_name like '%"+map.get("workerName")+"%' ";
        }
        if(StringUtils.isNotEmpty(map.get("srchRq1"))) {
            sqlWhere += " and to_char(a.qj_Krq,'yyyy-mm-dd') >='"+map.get("srchRq1")+"'";
        }
        if(StringUtils.isNotEmpty(map.get("srchRq2"))) {
            sqlWhere += " and to_char(a.qj_Krq,'yyyy-mm-dd') <='"+map.get("srchRq2")+"'";
        }
        if(StringUtils.isNotEmpty(map.get("srchWorkerDept"))) {
            sqlWhere += " and b.worker_dept = '"+map.get("srchWorkerDept")+"'";
        }
        String sql ="select a.qj_note,a.qj_rq,a.worker_id,a.qj_dtype,a.qj_ztype,a.qj_xtype,a.qj_sy"
                + ",a.qj_krq,a.qj_drq,a.qj_sc,a.qj_kq,a.qj_flag,a.qj_month,a.data_man,a.data_date,"
                + "a.data_corp, b.worker_name,a.jbqj_id,f_getname('GETDWNAME',b.worker_dept,'',a.data_corp) worker_dept_name from e_hr_jbqj a,e_hr_worker b  "
                + "where a.worker_id=b.worker_id and a.qj_dtype='请假'"+sqlWhere+" and b.worker_dept in (select dept_id from " +
                "sys_dept where parent_id in (select dept_id from sys_dept where dept_name = '生产部'))";
        return eHrJbqjDao.QueryPageLists(baseDto, sql);
    }

    @Override
    public Result updateFlag(String jbqjId) {
        Result resultMsg = new Result();
        int i = eHrJbqjDao.updateFlag("核准", jbqjId);
        if (i == 1) {
            resultMsg.putMsg("msg", "确认成功");
        } else
            resultMsg.putMsg("msg", "确认失败");
        return resultMsg;
    }
}
