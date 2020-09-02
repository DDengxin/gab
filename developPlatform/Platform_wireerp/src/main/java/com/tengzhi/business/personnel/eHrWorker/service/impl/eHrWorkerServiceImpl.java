package com.tengzhi.business.personnel.eHrWorker.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.personnel.eHrWorker.dao.EHrWorkerFamilyDao;
import com.tengzhi.business.personnel.eHrWorker.dao.eHrWorkerDao;
import com.tengzhi.business.personnel.eHrWorker.model.EHrWorker;
import com.tengzhi.business.personnel.eHrWorker.model.EHrWorkerFamily;
import com.tengzhi.business.personnel.eHrWorker.service.eHrWorkerService;
import com.tengzhi.business.personnel.eHrWorker.vo.EHrWorkerVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.dept.dao.SysDeptDao;
import com.tengzhi.business.system.dept.model.SysDept;
import com.tengzhi.business.system.user.dao.SysUserDao;
import com.tengzhi.business.system.user.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class eHrWorkerServiceImpl implements eHrWorkerService {
    @Autowired
    private eHrWorkerDao eHrWorkerDao;
    @SuppressWarnings("rawtypes")
    @Autowired
    private SysGenNoteService sysGenNoteService;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private EHrWorkerFamilyDao eHrWorkerFamilyDao;

    @Override
    public BasePage<EHrWorker> findAll(BaseDto baseDto) throws IOException {
    	Map<String, String> map = baseDto.getParamsMap();
    	
        String sqlshere = " where worker_corpid in ('"+SessionUser.SessionUser().getSysUser().getOrgIds().replaceAll(",", "','")+"') ";
        //默认当前公司账套
        /*if ("true".equalsIgnoreCase(String.valueOf(map.get("currentOrgId")))) {
            sqlshere += " and worker_corpid = '" + SessionUser.SessionUser().getCorpId() + "'";
        }*/
        if(StringUtils.isNotEmpty(map.get("orgId"))){
            sqlshere = " where worker_corpid='"+map.get("orgId")+"' ";
        }
        if (StringUtils.isNotEmpty(map.get("workerCorpid"))) {
            sqlshere += " and worker_corpid = '" + map.get("workerCorpid") + "'";
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
        if (StringUtils.isNotEmpty(map.get("workerFlag"))) {
            sqlshere += " and worker_flag = '" + map.get("workerFlag") + "'";
        }

        if (StringUtils.isNotEmpty(map.get("orgCorp"))) {
            sqlshere += " and worker_organization_corp = '" + map.get("orgCorp") + "'";
        }
        if (StringUtils.isNotEmpty(map.get("orgDept"))) {
            sqlshere += " and worker_organization_dept = '" + map.get("orgDept") + "'";
        }
        String sql = "select worker_id,worker_name,worker_sex,worker_dept,worker_duty,worker_post,worker_phone,worker_email,worker_corpid,worker_flag,worker_time,worker_operator,worker_territory,worker_address,"
                + "worker_race,worker_insurance,worker_registered,worker_background,f_getname('GETDEPTNAME',worker_dept,'',worker_corpid) worker_dept_name,f_get_param_name('学历类型',worker_background,'"+   SessionUser.getCurrentCorpId()   +"','') worker_xl_name, "
                + " f_get_param_name('职务级别',worker_duty,'"+   SessionUser.getCurrentCorpId()   +"','') worker_duty_name, f_get_param_name('工作岗位',worker_post,'"+   SessionUser.getCurrentCorpId()   +"','') worker_post_name,f_get_param_name('保险类型',worker_insurance,'"+   SessionUser.getCurrentCorpId()   +"','') worker_insurance_name "
                + " ,worker_type, f_get_param_name('人事参数',worker_type,'"+   SessionUser.getCurrentCorpId()   +"','') worker_type_name,worker_organization_dept,f_getname('GETDEPTNAME',worker_organization_dept,'','')org_dept_name "
                + " , f_getname('GETCORPEXP', null, null,worker_corpid) worker_corp_name,worker_organization_corp,f_getname('GETCORPEXP', null, null,worker_organization_corp) org_corp_name  "
                + "from e_hr_worker" + sqlshere; 
        return eHrWorkerDao.QueryPageLists(baseDto,sql  );
    }

    @Override
    public EHrWorker save(EHrWorker eHrWorker) throws Exception {
        if (!StringUtils.isNotEmpty(eHrWorker.getWorkerId())) {
            throw new Exception("请输入工号");
        } else {
            eHrWorker.setWorkerId(eHrWorker.getWorkerId().trim());
        }
        //eHrWorker.setWorkerId(sysGenNoteService.getNote(EHrWorker.class, "T", "yyMM", 4));
        SessionUser securityUser=SessionUser.SessionUser();
        if (judgeUnique(eHrWorker)) {
            eHrWorker.setWorkerCorpid(securityUser.getCorpId());
            eHrWorker.setWorkerOperator(securityUser.getUserId());
            eHrWorker.setWorkerTime(new Date());
            eHrWorker.Validate();
            return eHrWorkerDao.save(eHrWorker);

        } else {
            throw new Exception("编码已存在");
        }

    }

    @Override
    public void deleteByWorkerId(String workerId) throws Exception {
         EHrWorker eHrWorker = eHrWorkerDao.findByWorkerId(workerId);
        if(null != eHrWorker && Objects.equals(true, eHrWorker.getWorkerFlag())){
            throw new Exception("当前档案已启用,禁止删除");
        }else{
            eHrWorkerDao.delete(eHrWorker);
        }

    }

    @Override
    public boolean judgeUnique(EHrWorker eHrWorker) {
        return eHrWorkerDao.findAll(Specifications.where((c) -> {
            c.eq("workerId", eHrWorker.getWorkerId());
        })).size() <= 0;
    }

    @Override
    public void update(EHrWorker eHrWorker) {
    	if(1==sysUserDao.findByUnique(eHrWorker.getWorkerId())) {
    	SysUser sysUser = sysUserDao.findByJobNumber(eHrWorker.getWorkerId());
    	if(StringUtils.isNotEmpty(sysUser.getJobNumber())) {
    		if(StringUtils.isNotEmpty(eHrWorker.getWorkerDept())) {
    			SysDept sysDept = sysDeptDao.findByDeptId(eHrWorker.getWorkerDept());
    			sysUser.setDeptId(eHrWorker.getWorkerDept());
    			sysUser.setDeptName(sysDept.getDeptName());
    		}
    		if(StringUtils.isNotEmpty(eHrWorker.getWorkerPost())) {
    			sysUser.setPositionId(eHrWorker.getWorkerPost());
    		}
    		sysUser.setGender("true".equalsIgnoreCase(eHrWorker.getWorkerSex()));
    		sysUserDao.dynamicSave(sysUser, sysUserDao.findByJobNumber(eHrWorker.getWorkerId()));
    	}
    }
    	
        eHrWorkerDao.dynamicSave(eHrWorker, eHrWorkerDao.findByWorkerId(eHrWorker.getWorkerId()));
    }

    @Override
    public Map<String, Object> findByWorkerId(String workerId) {
        //EHrWorker er = eHrWorkerDao.findByWorkerId(workerId);
        String sql = "select *, "
                + "f_getname('GETDEPTNAME',worker_dept,'',worker_corpid) worker_dept_name,f_get_param_name('学历类型',worker_background,'"+   SessionUser.getCurrentCorpId()   +"','') worker_xl_name, "
                + " f_get_param_name('职务级别',worker_duty,'"+   SessionUser.getCurrentCorpId()   +"','') worker_duty_name, f_get_param_name('工作岗位',worker_post,'"+   SessionUser.getCurrentCorpId()   +"','') worker_post_name,f_get_param_name('保险类型',worker_insurance,'"+   SessionUser.getCurrentCorpId()   +"','') worker_insurance_name "
                + " , f_get_param_name('人事参数',worker_type,'"+   SessionUser.getCurrentCorpId()   +"','') worker_type_name,f_getname('GETDEPTNAME',worker_organization_dept,'','')org_dept_name "
                + " , f_getname('GETCORPEXP', null, null,worker_corpid) worker_corp_name,f_getname('GETCORPEXP', null, null,worker_organization_corp) org_corp_name  "
                + "from e_hr_worker where worker_id='"+workerId+"'";
        Map<String, Object> er = eHrWorkerDao.QueryToFirstMap(sql);
        //er.setWorkerDeptName(eHrWorkerDao.getDeptName(er.getWorkerDept(), er.getWorkerCorpid()));
        return er;
    }


    @Override
    public List<Map<Object, String>> selectDept() {

        return eHrWorkerDao.selectDept();
    }

    @Override
    public List<SelectVo> getDeptWorker(String deptId) {
        List<SelectVo> voList = new ArrayList<SelectVo>();
        String sql = "select worker_id, worker_name  from e_hr_worker where  worker_dept in (select dept_id from sys_dept  where dept_id='" + deptId + "') and worker_flag='启用' ";
        List<Map> mapList = eHrWorkerDao.QueryListMap(sql);
        mapList.forEach(Map -> {
            voList.add(new SelectVo(Map.get("worker_id"), String.valueOf(Map.get("worker_name"))));
        });
        return voList;
    }

    /* 学历 */
    @Override
    public List<SelectVo> getXl(String trueText, String falseText) {
        return comboParam("人事", "XLLX");
    }

    /* 员工类型 */
    @Override
    public List<SelectVo> getYglx(String trueText, String falseText) {
        return comboParam("人事", "YGLX");
    }

    /* 工作岗位 */
    @Override
    public List<SelectVo> getGzgw(String corpId) {
        return comboParam("人事", "GZGW",corpId);
    }

    /* 职务 */
    @Override
    public List<SelectVo> getGzzw(String corpId) {
        return comboParam("人事", "ZWLX",corpId);
    }

    /* 保险 */
    @Override
    public List<SelectVo> getGzbx(String trueText, String falseText) {
        return comboParam("人事", "BXLX");
    }

    @Override
    public List<SelectVo> getCorp() {
        List<SelectVo> voList = new ArrayList<SelectVo>();
        String sql = "select org_id,corp_exp from sys_org  order by org_id ";
        List<Map> mapList = eHrWorkerDao.QueryListMap(sql);
        mapList.forEach(Map -> {
            voList.add(new SelectVo(Map.get("org_id"), String.valueOf(Map.get("corp_exp"))));
        });
        return voList;
    }


    public List<SelectVo> comboParam(String mod, String pareatId) {
        List<SelectVo> voList = new ArrayList<SelectVo>();
        Object[] val = {mod, pareatId};
        String sql = "select param_id,param_name from sys_param  where param_status='true' and param_mod=?  and parent_id=? order by  param_ord ";
        List<Map> mapList = eHrWorkerDao.QueryListMap(sql, val);
        mapList.forEach(Map -> {
            voList.add(new SelectVo(Map.get("param_id"), String.valueOf(Map.get("param_name"))));
        });
        return voList;
    }

    public List<SelectVo> comboParam(String mod, String pareatId,String orgId) {
        List<SelectVo> voList = new ArrayList<SelectVo>();
        Object[] val = {mod, pareatId,orgId};
        String sql = "select param_id,param_name from sys_param  where param_status='true' and param_mod=?  and parent_id=? and ?=any(string_to_array(org_id,',')) order by  param_ord ";
        List<Map> mapList = eHrWorkerDao.QueryListMap(sql, val);
        mapList.forEach(Map -> {
            voList.add(new SelectVo(Map.get("param_id"), String.valueOf(Map.get("param_name"))));
        });
        return voList;
    }


    @Override
    public Result saveData(EHrWorkerVo eHrWorkerVo) throws Exception {
        if (!StringUtils.isNotEmpty(eHrWorkerVo.geteHrWorker().getWorkerId())) {
            throw new Exception("请输入工号");
        } else {
            eHrWorkerVo.geteHrWorker().setWorkerId(eHrWorkerVo.geteHrWorker().getWorkerId().trim());
        }

        SessionUser securityUser=SessionUser.SessionUser();
        if (judgeUnique(eHrWorkerVo.geteHrWorker())) {
            eHrWorkerVo.geteHrWorker().setWorkerCorpid(securityUser.getCorpId());
            eHrWorkerVo.geteHrWorker().setWorkerOperator(securityUser.getUserId());
            eHrWorkerVo.geteHrWorker().setWorkerTime(new Date());
            Integer sid = Integer.parseInt(eHrWorkerDao.getSingleResult("select coalesce(max(jt_sid)+1,1) from e_hr_worker_family  "));
            if (!eHrWorkerVo.getAddedList().isEmpty()) {
                for (EHrWorkerFamily item:eHrWorkerVo.getAddedList()){
                    item.setWorkerId(eHrWorkerVo.geteHrWorker().getWorkerId());
                    item.setDataMan(securityUser.getUserId());
                    item.setDataDate(new Date());
                    item.setDataCorp(securityUser.getCorpId());
                    item.setJtSid(sid);
                    sid++;
                    eHrWorkerFamilyDao.save(item);
                };
            }
            if (!eHrWorkerVo.getDeletedList().isEmpty()) {
                eHrWorkerFamilyDao.deleteAll(eHrWorkerVo.getDeletedList());
            }
            if (!eHrWorkerVo.getModifyedList().isEmpty()) {
                eHrWorkerVo.getModifyedList().forEach(item -> {
                    eHrWorkerFamilyDao.dynamicSave(item, eHrWorkerFamilyDao.QueryToFirstBean("select * from e_hr_worker_family where jt_sid =:1",item.getJtSid()));
                });
            }
            eHrWorkerDao.save(eHrWorkerVo.geteHrWorker());
        } else {
            throw new Exception("编码已存在");
        }
        return Result.resultOk();
    }

    @Override
    public Result updateData(EHrWorkerVo eHrWorkerVo) throws Exception {
        SessionUser securityUser=SessionUser.SessionUser();
        eHrWorkerVo.geteHrWorker().setWorkerCorpid(securityUser.getCorpId());
        eHrWorkerVo.geteHrWorker().setWorkerOperator(securityUser.getUserId());
        eHrWorkerVo.geteHrWorker().setWorkerTime(new Date());
        Integer sid = Integer.parseInt(eHrWorkerDao.getSingleResult("select coalesce(max(jt_sid)+1,1) from e_hr_worker_family  "));
        if (!eHrWorkerVo.getAddedList().isEmpty()) {
            for (EHrWorkerFamily item:eHrWorkerVo.getAddedList()){
                item.setWorkerId(eHrWorkerVo.geteHrWorker().getWorkerId());
                item.setDataMan(securityUser.getUserId());
                item.setDataDate(new Date());
                item.setDataCorp(securityUser.getCorpId());
                item.setJtSid(sid);
                sid++;
                eHrWorkerFamilyDao.save(item);
            };
        }
        if (!eHrWorkerVo.getDeletedList().isEmpty()) {
            eHrWorkerFamilyDao.deleteAll(eHrWorkerVo.getDeletedList());
        }
        if (!eHrWorkerVo.getModifyedList().isEmpty()) {
            eHrWorkerVo.getModifyedList().forEach(item -> {
                eHrWorkerFamilyDao.dynamicSave(item, eHrWorkerFamilyDao.QueryToFirstBean("select * from e_hr_worker_family where jt_sid =:1",item.getJtSid()));
            });
        }
        eHrWorkerDao.dynamicSave(eHrWorkerVo.geteHrWorker(),eHrWorkerDao.findById(eHrWorkerVo.geteHrWorker().getWorkerId()).orElse(null));
        return Result.resultOk();
    }

    @Override
    public BasePage<Map<String, Object>> getFamilyList(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        String sql = "select * from e_hr_worker_family where worker_id = '"+map.get("workerId")+"' " ;
        return eHrWorkerFamilyDao.QueryToMapPage(baseDto,sql);
    }
}
