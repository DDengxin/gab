package com.tengzhi.business.sc.da.staffOvertime.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.personnel.eHrWorker.model.EHrWorker;
import com.tengzhi.business.sc.da.staffOvertime.dao.StaffOvertimeDao;
import com.tengzhi.business.sc.da.staffOvertime.model.StaffOvertime;
import com.tengzhi.business.sc.da.staffOvertime.service.StaffOvertimeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/4 0004 18:42
 * @Description: 生产员工请假服务接口
 */

@Service
public class StaffOvertimeServiceImpl implements StaffOvertimeService {

    @Autowired
    private StaffOvertimeDao staffOvertimeDao;

    /**
     *@role:
     *@Author:      huangbiao
     *@Date:        2020/8/5 0005 9:30
     *@Param:       [baseDto]
     *@return:      com.tengzhi.base.jpa.page.BasePage<com.tengzhi.business.sc.da.staffOvertime.model.StaffOvertime>
     *@Description: 查询加班表中所有的生产人员
     **/
    @Override
    public BasePage<StaffOvertime> findAll(BaseDto baseDto) {
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
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(StringUtils.isNotEmpty(map.get("srchRq1"))) {
            sqlWhere += " and to_char(a.qj_Krq,'yyyy-mm-dd') >='"+map.get("srchRq1")+"'";
        }
        if(StringUtils.isNotEmpty(map.get("srchRq2"))) {
            sqlWhere += " and to_char(a.qj_Krq,'yyyy-mm-dd') <='"+map.get("srchRq2")+"'";
        }
        if(StringUtils.isNotEmpty(map.get("qjZtype"))) {
            sqlWhere += " and a.qj_ztype = '"+map.get("qjZtype")+"'";
        }
        if(StringUtils.isNotEmpty(map.get("workerDept"))) {
            sqlWhere += " and a.qj_ztype = '"+map.get("workerDept")+"'";
        }
        if(StringUtils.isNotEmpty(map.get("srchWorkerDept"))) {
            sqlWhere += " and b.worker_dept = '"+map.get("srchWorkerDept")+"'";
        }
        String sql ="select a.qj_note,a.qj_rq,a.worker_id,a.qj_dtype,a.qj_ztype,a.qj_xtype,a.qj_sy"
                + ",a.qj_krq,a.qj_drq,a.qj_sc,a.qj_kq,a.qj_flag,a.qj_month,a.data_man,a.data_date,"
                + "a.data_corp,b.worker_name,a.jbqj_id,f_getname('GETDWNAME',b.worker_dept,'',a.data_corp) worker_dept_name  from e_hr_jbqj a,e_hr_worker b "
                + "where a.worker_id=b.worker_id and a.qj_dtype='加班'" +sqlWhere+" and b.worker_dept in (select " +
                "dept_id from sys_dept where parent_id in (select dept_id from sys_dept where dept_name = '生产部'))";
        return staffOvertimeDao.QueryPageLists(baseDto, sql);
    }

    @Override
    public Result deleteById(String id){
        staffOvertimeDao.deleteById(id);
        return Result.resultOkMsg("删除成功");
    }

    @Override
    public BasePage<StaffOvertime> findProudction(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();

        String sqlshere = " where worker_corpid in ('"+ SessionUser.SessionUser().getSysUser().getOrgIds().replaceAll(",", "','")+"') ";
        //默认当前公司账套
        /*if ("true".equalsIgnoreCase(String.valueOf(map.get("currentOrgId")))) {
            sqlshere += " and worker_corpid = '" + SessionUser.SessionUser().getCorpId() + "'";
        }*/

        if (StringUtils.isNotEmpty(map.get("workCorpid"))) {
            sqlshere += " and worker_corpid = '" + map.get("workCorpid") + "'";
        }


        if (StringUtils.isNotEmpty(map.get("workerId"))) {
            sqlshere += " and worker_id like '%" + map.get("workerId") + "%'";
        }
        if (StringUtils.isNotEmpty(map.get("workerName"))) {
            sqlshere += " and worker_name like '%" + map.get("workerName") +	 "%'";
        }
        if (StringUtils.isNotEmpty(map.get("workerDept"))) {
            sqlshere += " and worker_dept = '" + map.get("workerDept") + "'";
        }
        if (StringUtils.isNotEmpty(map.get("workerType"))) {
            sqlshere += " and worker_type = '" + map.get("workerType") + "'";
        }
        if (StringUtils.isNotEmpty(map.get("workerPost"))) {
            sqlshere += " and worker_post = '" + map.get("workerPost") + "'";
        }
        if (StringUtils.isNotEmpty(map.get("workerDuty"))) {
            sqlshere += " and worker_duty = '" + map.get("workerDuty") + "'";
        }

        String sql = "select worker_id,worker_name,worker_sex,worker_dept,worker_duty,worker_post,worker_phone,worker_email,worker_corpid,worker_flag,worker_time,worker_operator,worker_territory,worker_address,"
                + "worker_race,worker_insurance,worker_registered,worker_background,f_getname('GETDEPTNAME',worker_dept,'',worker_corpid) worker_dept_name,f_get_param_name('学历类型',worker_background,'"+   SessionUser.getCurrentCorpId()   +"','') worker_xl_name, "
                + " f_get_param_name('职务级别',worker_duty,'"+   SessionUser.getCurrentCorpId()   +"','') worker_duty_name, f_get_param_name('工作岗位',worker_post,'"+   SessionUser.getCurrentCorpId()   +"','') worker_post_name,f_get_param_name('保险类型',worker_insurance,'"+   SessionUser.getCurrentCorpId()   +"','') worker_insurance_name "
                + " ,worker_type, f_get_param_name('人事参数',worker_type,'"+   SessionUser.getCurrentCorpId()   +"','') worker_type_name "
                + " , f_getname('GETCORPEXP', null, null,worker_corpid) worker_corp_name "
                + "from e_hr_worker" + sqlshere+"and e_hr_worker.worker_dept in (select dept_id from sys_dept where parent_id " +
                "in (select dept_id from sys_dept where dept_name = '生产部'))";
        return staffOvertimeDao.QueryPageLists(baseDto,sql);
    }

    @Override
    public Result updateFlag(String jbqj_id) {
        int i = staffOvertimeDao.updateFlag(jbqj_id,"核准");
        Result result;
        if (i==1){
            result = Result.resultOkMsg("修改成功");
        }else{
            result = Result.resultOkMsg("修改失败");
        }
        return result;
    }


}
