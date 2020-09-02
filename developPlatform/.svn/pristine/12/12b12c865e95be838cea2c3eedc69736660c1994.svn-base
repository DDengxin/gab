package com.tengzhi.business.system.workflow.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.impl.CommunalServiceImpl;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.business.system.workflow.dao.*;
import com.tengzhi.business.system.workflow.excpetion.WorkFlowException;
import com.tengzhi.business.system.workflow.model.*;
import com.tengzhi.business.system.workflow.service.InstanceService;
import com.tengzhi.business.system.workflow.service.ProcessService;
import com.tengzhi.business.system.workflow.vo.Examine;
import com.tengzhi.business.system.workflow.vo.SaveVo;
import com.tengzhi.business.system.workflow.vo.Source;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProcessServiceImpl extends CommunalServiceImpl<Describe, String> implements ProcessService {

    @Autowired
    private DescribeDao dao;
    @Autowired
    private LinkDao linkDao;
    @Autowired
    private LineDao lineDao;
    @Autowired
    private DescribDao describDao;
    @Autowired
    private InstanceService TZinstanceService;
    @Autowired
    private LinkTableDao linkTableDao;
    @Autowired
    private MatterDao matterDao;
    @Autowired
    private BacklogsDao backlogsDao;

    /**
     * 获取流程
     */
    @Override
    public BasePage<Describe> getProcess(BaseDto baseDto) {
        return describDao.getProcess(baseDto);
    }

    @Override
    public Source getProcessInstance(String processId) {
        Source source = new Source();
        source.setDescribe(describDao.queryDescribe(processId));
        source.setEdges(describDao.queryEdges(processId));
        source.setNodes(describDao.queryNodes(processId));
        return source;
    }

    protected void check(Examine examine) {
        if (StringUtils.isBlank(examine.getBacklogId())) {
            throw WorkFlowException.ProcessConfigurationError("流程待办id不能为空");
        }
    }

    /**
     * 启动流程时候检查流程参数是否合法
     *
     * @param examine
     */
    protected void startCheck(Examine examine) {
        if (StringUtils.isBlank(examine.getMenuId())) {
            throw WorkFlowException.ProcessConfigurationError("流程所属菜单id不能为空");
        } else if (StringUtils.isBlank(examine.getBusinessId())) {
            throw WorkFlowException.ProcessConfigurationError("流程关联业务主键不能为空");
        } else if (StringUtils.isBlank(examine.getUrl())) {
            throw WorkFlowException.ProcessConfigurationError("流程办理url不能为空");
        }
		/*if (StringUtils.isBlank(examine.getTitle())) {
			throw WorkFlowException.ProcessConfigurationError("流程标题不能为空");
		}*/

    }

    @Override
    public Result disagree(Examine examine) {
        check(examine);
        TZinstanceService.Back(examine.getBacklogId(), examine.getOpinion(), examine.getSignature());
        return Result.resultOk();
    }

    @Override
    public Result agree(Examine examine) {
        check(examine);
        TZinstanceService.handle(examine.getBacklogId(), examine.getOpinion(), examine.getSignature());
        return Result.resultOk();
    }

    @Override
    public Result startFinishFirstStep(Examine examine) {
        startCheck(examine);
        Link link = TZinstanceService.Start(examine.getMenuId(), examine.getBusinessId(), examine.getTitle(),
                examine.getUrl());
        return Result.resultOk();
    }

    @Override
    public Result handle(Examine examine) {
        Link link = TZinstanceService.handle(examine.getBacklogId(), examine.getOpinion(), "");
        return Result.resultOk();
    }

    @Override
    public Result release(String id) {
        Describe des = dao.findById(id).orElse(null);
        if ("2".equals(des.getState())) {
            des.setState("1");
        } else {
            des.setState("2");
        }
        dao.update(des);
        return Result.resultOk();
    }

    @Override
    public Result saveProcess(SaveVo soure) {
        Describe desc = soure.getDescribe().get(0);
        Describe descri = null;
        if (desc.getId() != null) {
            descri = dao.findById(desc.getId()).orElse(null);
        }
        SessionUser securityUser = SessionUser.SessionUser();
        if (descri != null && !"2".equals(descri.getState())) {
            // 修改
            Describe des = soure.getDescribe().get(0);
            lineDao.delete(des.getId());
            linkDao.delete(des.getId());
            soure.getEdges().forEach(e -> {
                e.setId(UUIDUtil.uuid());
                e.setProcessId(des.getId());
                lineDao.store(e);
            });
            soure.getNodes().forEach(e -> {
                e.setId(UUIDUtil.uuid());
                e.setProcessId(des.getId());
                linkDao.store(e);
            });
            dao.dynamicSave(des, dao.findById(des.getId()).orElse(null));
        } else {
            // 新增
            String pid = "";
            List<Describe> des = soure.getDescribe();
            for (Describe e : des) {
                pid = UUIDUtil.uuid();
                e.setId(pid);
                e.setCreationTime(new Date());
                e.setCreator(securityUser.getNickName());
                e.setCreatorId(securityUser.getJobNumber());
                e.setDataCorp(securityUser.getSysUser().getOrgId());
                e.setVersion(describDao.getversion(e.getProcessClassifyId()));
                e.setState("0");
                dao.store(e);
            }
            for (Line e : soure.getEdges()) {
                e.setId(UUIDUtil.uuid());
                e.setProcessId(pid);
                lineDao.store(e);
            }
            for (Link e : soure.getNodes()) {
                e.setId(UUIDUtil.uuid());
                e.setProcessId(pid);
                linkDao.store(e);
            }
        }
        return Result.resultOk();
    }

    @Override
    public Result delete(String id) {
        describDao.delete(id);
        return Result.resultOk();
    }

    /**
     * 修改业务表
     */
    public void updateBusiness(LinkTable bean, String noteValue, String flagValue) {
        if (bean != null) {
            if (bean.getWorkflowFlagFieldName() == null) {
                throw WorkFlowException.ProcessConfigurationError("状态字段不能为空,请检查工作流配置。");
            }
            if (bean.getWorkflowNoteFieldName() == null) {
                throw WorkFlowException.ProcessConfigurationError("单号字段不能为空,请检查工作流配置。");
            }
            if (bean.getWorkflowTableName() == null) {
                throw WorkFlowException.ProcessConfigurationError("业务表不能为空,请检查工作流配置。");
            }
            describDao.update(bean.getWorkflowTableName(), bean.getWorkflowNoteFieldName(), noteValue,
                    bean.getWorkflowFlagFieldName(), flagValue);
        } else {
            //throw new RuntimeException("业务表单配置错误！");
        }
    }

    @Override
    public Result repeal(Examine examine) {
        if (examine.getBusinessId() == null) {
            throw WorkFlowException.ProcessConfigurationError("业务id字段不能为空");
        }
        if (backlogsDao.findAll(Specifications.where(c -> {
            c.eq("businessId", examine.getBusinessId());
        })).size() == 0) {
            throw WorkFlowException.InstanceNotExist("当前数据不处于审核状态");
        }
        if (StringUtils.isEmpty(examine.getProcessId())) {
            examine.setProcessId(describDao.getProcessId(examine.getBusinessId()));
        }
        if (examine.getProcessId() == null) {
            throw WorkFlowException.ProcessConfigurationError("流程id字段不能为空");
        }
        Describe desc = dao.findById(examine.getProcessId()).orElse(null);
        List<Link> Linklist = linkDao.findAll(Specifications.where(e -> {
            e.eq("processType", "1");
            e.eq("processId", desc.getId());
        }));
        if (Linklist.size() == 0) {
            throw WorkFlowException.ProcessConfigurationError("流程未定义开始环节,请检查工作流配置。");
        }
        LinkTable bean = linkTableDao.findById(desc.getWorkflowTableId()).orElse(null);
        updateBusiness(bean, examine.getBusinessId(), Linklist.get(0).getLinkName());
        describDao.delallBacklogs(examine.getBusinessId());// 删除所有有待办
        List<Matter> list = matterDao.findAll(Specifications.where(e -> {
            e.eq("businessId", examine.getBusinessId());
            e.eq("processId", examine.getProcessId());
        }));
        if (list != null && list.size() > 0) {
            Matter matter = list.get(0);
            matter.setProcessType("4");
            matterDao.update(matter);
        }
        return Result.resultOk();
    }

    @Override
    public List<Map> getProcessClassify(String process_module, String process_system_type) {
        return describDao.getClassify(process_module, process_system_type);
    }

    @Autowired
    private TransactDao transactDao;

    @Override
    public Result saveSignature(Examine examine) {
        List<Transact> list = transactDao.findAll(Specifications.where(e -> {
            e.eq("businessId", examine.getBusinessId());
            e.eq("processId", examine.getProcessId());
        }));
        if (list.size() > 0) {
            Transact transact = list.get(0);
            transact.setSignature(examine.getSignature());
            transactDao.update(transact);
        }
        return Result.resultOk();
    }
}
