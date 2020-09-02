package com.tengzhi.business.system.workflow.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.security.common.tool.spring.SpringUtil;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.business.system.user.dao.SysUserDao;
import com.tengzhi.business.system.user.dao.SysUsersDao;
import com.tengzhi.business.system.user.model.SysUser;
import com.tengzhi.business.system.workflow.dao.*;
import com.tengzhi.business.system.workflow.excpetion.WorkFlowException;
import com.tengzhi.business.system.workflow.extension.Workflow;
import com.tengzhi.business.system.workflow.model.*;
import com.tengzhi.business.system.workflow.service.InstanceService;
import com.tengzhi.business.system.workflow.vo.Examine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.el.ELProcessor;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service("TZinstanceService")
public class InstanceServiceImpl implements InstanceService {
    @Autowired
    private DescribeDao dao;
    @Autowired
    private LinkDao linkDao;
    @Autowired
    private BacklogsDao backlogDao;
    @Autowired
    private LineDao lineDao;
    @Autowired
    private TransactDao transactDao;
    @Autowired
    private MatterDao matterDao;
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private DescribDao describDao;

    @Autowired
    private SysUsersDao sysUsersDao;

    @Autowired
    private LinkTableDao linkTableDao;

    private String opinion;

    private String signature;

//==========================================公用方法====================================================================

    public String getOpinion() {
        return opinion;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    /**
     * 增加待办
     *
     * @param link
     * @param businessId
     * @param model
     * @param title
     * @param url
     * @param map
     * @param vmap
     */
    public void addbacklogs(Link link, String businessId, String model, String title, String url,
                            Map<String, Object> map, Map<String, Object> vmap, LinkTable bean) {
        Backlog backlog = new Backlog();
        backlog.setId(UUIDUtil.uuid());
        backlog.setTime(new Date());
        backlog.setBusinessId(businessId);
        backlog.setTitle(title);
        backlog.setProcessId(link.getProcessId());
        backlog.setLinkId(link.getId());
        backlog.setLinkName(link.getLinkName());
        backlog.setHandleUrl(url);
        backlog.setModule(model);
        backlog.setUnitName(vmap.get(bean.getWorkflowUnitFieldName()) == null ? "" : vmap.get(bean.getWorkflowUnitFieldName()).toString());
        //backlog.setUnitName(vmap.get(bean.getWorkflowUnitFieldName()) == null ? "": (bean.getWorkflowUnitFieldName() == null ? "":  vmap.get(bean.getWorkflowUnitFieldName() )    )    ));
        backlog.setSubmitUserId(vmap.get(bean.getWorkflowWorkerIdFieldName()) == null ? "" : vmap.get(bean.getWorkflowWorkerIdFieldName()).toString());
        // 流程名称
        List<com.tengzhi.business.system.workflow.vo.Describe> describeList = describDao
                .queryDescribe(link.getProcessId());
        if (null != describeList && describeList.size() > 0) {
            backlog.setProcessName(describeList.get(0).getProcessName());
        }
        SecurityUser securityUser = SessionUser.SessionUser();
        backlog.setFounder(securityUser.getNickName());
        backlog.setFounderId(securityUser.getJobNumber());
        List<String> uids = new ArrayList<String>(), userNames = new ArrayList<String>();
        if ("0".equals(link.getTransactorType())) {
            String str = link.getTransactor();// sql
            if (StringUtils.isBlank(str)) {
                throw WorkFlowException.ProcessConfigurationError("环节未配置sql,请检查工作流配置。");
            }
            String reg = "\\{([a-zA-Z0-9_]*)\\}";
            Pattern patten = Pattern.compile(reg);// 编译正则表达式
            Matcher matcher = patten.matcher(str);// 指定要匹配的字符串
            while (matcher.find() && matcher.groupCount() > 0) {
                String str1 = matcher.group(1);
                if (!vmap.containsKey(str1)) {
                    throw WorkFlowException.ProcessConfigurationError(str1 + "不存在业务表单,请检查工作流配置。");
                }
                str = str.replace("{" + str1 + "}", "'" + vmap.get(str1) + "'");
            }
            String useridStr = describDao.getStr(str);
            if (StringUtils.isBlank(useridStr) || "null".equals(useridStr)) {
                throw WorkFlowException.ProcessConfigurationError(link.getLinkName() + "环节,办理人员为空,请检查工作流配置。");
            }
            String[] userids = useridStr.split(",");
            for (int i = 0; i < userids.length; i++) {
                if (StringUtils.isBlank(userids[i])) {
                    continue;
                }
                try {
                    String username = sysUserDao.findByJobNumber(userids[i]).getRealName();
                    uids.add(userids[i]);
                    userNames.add(username);
                    Backlog backlogs = backlog;
                    backlogs.setId(UUIDUtil.uuid());
                    backlogs.setTransactorId(userids[i]);
                    backlogs.setTransactor(username);
                    backlogDao.save(backlogs);
                } catch (Exception e) {
                    throw WorkFlowException.ProcessConfigurationError(userids[i] + ",办理人员不存在,请检查工作流配置。");
                }
            }
            addMatter(link, backlog, uids.stream().map(String::valueOf).collect(Collectors.joining(",")),
                    userNames.stream().map(String::valueOf).collect(Collectors.joining(",")));
        } else if ("1".equals(link.getTransactorType())) {// 固定人员
            String[] userids = link.getTransactorId().split(",");
            String[] username = link.getTransactor().split(",");
            for (int i = 0; i < username.length; i++) {
                backlog.setId(UUIDUtil.uuid());
                backlog.setTransactorId(userids[i]);
                backlog.setTransactor(username[i]);
                backlogDao.save(backlog);
            }
            addMatter(link, backlog, link.getTransactorId(), link.getTransactor());
        } else if ("2".equals(link.getTransactorType())) {// 角色组
            String[] userids = link.getTransactorId().split(",");
            String roleids = StringUtils.join(userids, "','");
            List<SysUser> list = sysUsersDao.findUsers(roleids);
            for (int i = 0; i < list.size(); i++) {
                SysUser sys = list.get(i);
                Backlog backlogs = backlog;
                backlogs.setId(UUIDUtil.uuid());
                backlogs.setTransactorId(sys.getJobNumber());
                backlogs.setTransactor(sys.getRealName());
                backlogDao.save(backlogs);
            }
            addMatter(link, backlog, link.getTransactorId(), link.getTransactor());
        } else if ("3".equals(link.getTransactorType())) {// java 类
            Workflow work = (Workflow) SpringUtil.getBean(link.getTransactor());
            List<SysUser> list = work.find(vmap);
            for (int i = 0; i < list.size(); i++) {
                SysUser sys = list.get(i);
                uids.add(sys.getJobNumber());
                userNames.add(sys.getRealName());
                Backlog backlogs = backlog;
                backlogs.setId(UUIDUtil.uuid());
                backlogs.setTransactorId(sys.getJobNumber());
                backlogs.setTransactor(sys.getRealName());
                backlogDao.save(backlogs);
            }
            addMatter(link, backlog, uids.stream().map(String::valueOf).collect(Collectors.joining(",")),
                    userNames.stream().map(String::valueOf).collect(Collectors.joining(",")));
        } else if ("4".equals(link.getTransactorType())) {// 自定义
            String expression = link.getTransactor();
            if (StringUtils.isNotBlank(expression)) {
                ELProcessor elProc = new ELProcessor();
                Iterator<Entry<String, Object>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Entry<String, Object> entry = it.next();
                    elProc.defineBean(entry.getKey(), entry.getValue());
                }
                String ret = (String) elProc.getValue(expression, String.class);
                if (StringUtils.isBlank(ret)) {
                    throw WorkFlowException.ProcessConfigurationError("流程办理人员为空,请检查工作流配置。");
                }
                String[] userids = ret.split(",");
                for (int i = 0; i < userids.length; i++) {
                    if (StringUtils.isBlank(userids[i])) {
                        continue;
                    }
                    String username = sysUserDao.findByJobNumber(userids[i]).getRealName();
                    uids.add(userids[i]);
                    userNames.add(username);
                    Backlog backlogs = backlog;
                    backlogs.setId(UUIDUtil.uuid());
                    backlogs.setTransactorId(userids[i]);
                    backlogs.setTransactor(username);
                    backlogDao.save(backlogs);
                }
                addMatter(link, backlog, uids.stream().map(String::valueOf).collect(Collectors.joining(",")),
                        userNames.stream().map(String::valueOf).collect(Collectors.joining(",")));
            } else {
                backlog.setId(UUIDUtil.uuid());
                backlog.setTransactorId(securityUser.getJobNumber());//
                backlog.setTransactor(securityUser.getNickName());
                backlogDao.save(backlog);
                addMatter(link, backlog, securityUser.getJobNumber(), securityUser.getNickName());
            }

        } else {
            throw WorkFlowException.ProcessConfigurationError("流程未指定办理人员类型,请检查工作流配置。");
        }
    }

    /**
     * 修改业务表
     */
    public void updateBusiness(LinkTable bean, String noteValue, String flagValue) {
        if (bean != null) {
            if (bean.getWorkflowFlagFieldName() == null) {
                throw WorkFlowException.ProcessConfigurationError("流程未指定办理人员类型,请检查工作流配置。");
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
            //TODO:可能会存在问题
            //throw new RuntimeException("业务表单配置错误！");
        }
    }

    /**
     * 增加待办
     *
     * @param link
     * @param businessId
     * @param model
     * @param title
     * @param url
     * @param transact
     */
    public void addbacklogs(Link link, String businessId, String model, String title, String url,
                            List<Transact> transact, Map<String, Object> vmap, LinkTable bean) {
        Backlog backlog = new Backlog();
        backlog.setId(UUIDUtil.uuid());
        backlog.setTime(new Date());
        backlog.setBusinessId(businessId);
        backlog.setTitle(title);
        backlog.setProcessId(link.getProcessId());
        backlog.setLinkId(link.getId());
        backlog.setLinkName(link.getLinkName());
        backlog.setHandleUrl(url);
        backlog.setModule(model);
        SecurityUser securityUser = SessionUser.SessionUser();
        backlog.setFounder(securityUser.getNickName());
        backlog.setFounderId(securityUser.getJobNumber());
        backlog.setUnitName(vmap.get(bean.getWorkflowUnitFieldName()) == null ? ""
                : vmap.get(bean.getWorkflowUnitFieldName()).toString());
        backlog.setSubmitUserId(vmap.get(bean.getWorkflowWorkerIdFieldName()) == null ? ""
                : vmap.get(bean.getWorkflowWorkerIdFieldName()).toString());
        // 流程名称
        List<com.tengzhi.business.system.workflow.vo.Describe> describeList = describDao
                .queryDescribe(link.getProcessId());
        if (null != describeList && describeList.size() > 0) {
            backlog.setProcessName(describeList.get(0).getProcessName());
        }
        Transact sys = transact.get(0);
        Backlog backlogs = backlog;
        backlogs.setTransactorId(sys.getTransactorId());
        backlogs.setTransactor(sys.getTransactor());
        backlogDao.save(backlogs);
        addMatter(link, backlog, sys.getTransactorId(), sys.getTransactor());
    }

    /**
     * 增加办理记录
     *
     * @param link
     * @param businessId
     */
    public void addTransact(Link link, String businessId) {// 增加办理记录
        SecurityUser securityUser = SessionUser.SessionUser();
        Transact transact = new Transact();
        transact.setId(UUIDUtil.uuid());
        transact.setProcessId(link.getProcessId());
        transact.setLinkId(link.getId());
        transact.setLinkName(link.getLinkName());
        transact.setStartTime(new Date());
        transact.setEndTime(new Date());
        transact.setTransactor(securityUser.getNickName());
        transact.setTransactorId(securityUser.getJobNumber());
        transact.setOpinion(opinion);
        transact.setSignature(getSignature());
        setOpinion(null);
        setSignature(null);
        transact.setBusinessId(businessId);
        transactDao.save(transact);
    }

    public void addMatterEnd(Link link, String userids, String userName, String businessId, String Module, String title,
                             String url) {// 增加办理记录
        SecurityUser securityUser = SessionUser.SessionUser();
        List<Matter> list = matterDao.findAll(Specifications.where(e -> {
            e.eq("businessId", businessId);
            e.eq("processId", link.getProcessId());
        }));
        if (list == null || list.size() < 1) {
            Matter matter = new Matter();
            matter.setId(UUIDUtil.uuid());
            matter.setProcessId(link.getProcessId());
            matter.setBusinessId(businessId);
            matter.setFounder(securityUser.getNickName());
            matter.setFounderId(securityUser.getJobNumber());
            matter.setHandleUrl(url);
            matter.setLinkName(link.getLinkName());
            matter.setModule(Module);
            matter.setTime(new Date());
            matter.setTitle(title);
            matter.setTransactor(userName);
            matter.setTransactorId(userids);
            matter.setProcessType("3");
            matterDao.save(matter);
        } else {
            Matter matter = list.get(0);
            matter.setHandleUrl(url);
            matter.setLinkName(link.getLinkName());
            matter.setModule(Module);
            matter.setTitle(title);
            matter.setTransactor(userName);
            matter.setTransactorId(userids);
            matter.setProcessType("3");
            matterDao.update(matter);
        }
    }

    /**
     * 增加我申请的记录
     *
     * @param link
     * @param backlog
     * @param userids
     * @param userName
     */
    public void addMatter(Link link, Backlog backlog, String userids, String userName) {// 增加办理记录
        SecurityUser securityUser = SessionUser.SessionUser();
        List<Matter> list = matterDao.findAll(Specifications.where(e -> {
            e.eq("businessId", backlog.getBusinessId());
            e.eq("processId", link.getProcessId());
        }));
        if (list == null || list.size() < 1) {
            Matter matter = new Matter();
            matter.setId(UUIDUtil.uuid());
            matter.setProcessId(link.getProcessId());
            matter.setBusinessId(backlog.getBusinessId());
            matter.setFounder(securityUser.getNickName());
            matter.setFounderId(securityUser.getJobNumber());
            matter.setHandleUrl(backlog.getHandleUrl());
            matter.setLinkName(link.getLinkName());
            matter.setModule(backlog.getModule());
            matter.setTime(new Date());
            matter.setTitle(backlog.getTitle());
            matter.setTransactor(userName);
            matter.setTransactorId(userids);
            matter.setProcessType(link.getProcessType());
            matterDao.save(matter);
        } else {
            Matter matter = list.get(0);
            matter.setHandleUrl(backlog.getHandleUrl());
            matter.setLinkName(link.getLinkName());
            matter.setModule(backlog.getModule());
            matter.setTitle(backlog.getTitle());
            matter.setTransactor(userName);
            matter.setTransactorId(userids);
            matter.setProcessType(link.getProcessType());
            matterDao.update(matter);
        }
    }

    /**
     * 获取最新版本开始环节
     *
     * @param classifyId
     * @return
     */
    public List<Link> getLink(String classifyId) {
        Describe desc = getDescribe(classifyId);// 拿到最新版本
        List<Link> Linklist = linkDao.findAll(Specifications.where(e -> {
            e.eq("processType", "1");
            e.eq("processId", desc.getId());
        }));
        if (Linklist.size() == 0) {
            throw WorkFlowException.ProcessNotExist("流程未定义开始环节,请检查工作流配置。");
        }
        return Linklist;
    }

    /**
     * 获取上一环节
     *
     * @param classifyId
     * @return
     */
    public List<Link> getPreviousLink(String classifyId) {
        Describe desc = getDescribe(classifyId);// 拿到最新版本
        List<Link> Linklist = linkDao.findAll(Specifications.where(e -> {
            e.eq("processType", "1");
            e.eq("processId", desc.getId());
        }));
        if (Linklist.size() == 0) {
            throw WorkFlowException.ProcessConfigurationError("流程未定义开始环节,请检查工作流配置。");
        }
        return Linklist;
    }

    /**
     * 获取最新版本开始环节
     *
     * @param desc
     * @return
     */
    public List<Link> getLink(Describe desc) {
        List<Link> Linklist = linkDao.findAll(Specifications.where(e -> {
            e.eq("processType", "1");
            e.eq("processId", desc.getId());
        }));
        if (Linklist.size() == 0) {
            throw WorkFlowException.ProcessConfigurationError("流程未定义开始环节,请检查工作流配置。");
        }
        return Linklist;
    }

    /**
     * 获取最新版本
     *
     * @param classifyId
     * @return
     */
    public Describe getDescribe(String classifyId) {

        String sql = " select * from sys_workflow_describe "
                //启用状态的模板
                + " where  state = '2' "
                + " and process_classify_id = ?1  "
                //如果审批流程信息中的账套信息为null则允许所有账套使用
                + " and ( data_corp is null or   cast(?2  as varchar)= any(string_to_array( data_corp,',')) ) "
                + " order  by version asc ";
        List<Describe> list = dao.QueryToBean(sql, classifyId, SessionUser.SessionUser().getCorpId());
        /* change：2020/08/28 author:lgl
         Sort sort = Sort.by(Direction.DESC, "version");
        List<Describe> list = dao.findAll(Specifications.where(e -> {
            e.eq("processClassifyId", classifyId);
            e.eq("state", "2");
            e.eq("dataCorp", SessionUser.SessionUser().getCorpId());
        }), sort);*/
        if (list.size() == 0) {
            throw WorkFlowException.ProcessNotExist("流程未定义,请检查工作流配置。");
        }
        Describe desc = list.get(0);// 拿到最新版本
        return desc;
    }

    /**
     * 查询下一环节 通过流程id和关联id
     *
     * @param associatedId
     * @param processId
     * @return
     */
    public List<Link> getLinklist(String associatedId, String processId) {
        List<Link> Linklist = linkDao.findAll(Specifications.where(e -> {
            e.eq("associatedId", associatedId);
            e.eq("processId", processId);
        }));
        return Linklist;
    }

    /**
     * 删除待办
     *
     * @param processId
     * @param linkId
     * @param businessId
     * @param flag
     */
    public void deletebacklog(String processId, String linkId, String businessId, boolean flag) {
        SecurityUser securityUser = SessionUser.SessionUser();
        backlogDao.findAll(Specifications.where(e -> {
            e.eq("processId", processId);
            e.eq("linkId", linkId);
            e.eq("businessId", businessId);
            if (flag) {
                e.eq("transactorId", securityUser.getJobNumber());
            }
        })).forEach(item -> {
            backlogDao.delete(item);
        });
    }

    /**
     * 查询待办
     *
     * @param processId
     * @param linkId
     * @param businessId
     */
    public Backlog findbacklog(String processId, String linkId, String businessId) {
        SecurityUser securityUser = SessionUser.SessionUser();
        List<Backlog> list = backlogDao.findAll(Specifications.where(e -> {
            e.eq("processId", processId);
            e.eq("linkId", linkId);
            e.eq("businessId", businessId);
            e.eq("transactorId", securityUser.getJobNumber());
        }));
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 删除待办
     *
     * @param processId
     * @param linkId
     * @param businessId
     * @param flag
     * @return
     */
    public boolean findbacklog(String processId, String linkId, String businessId, boolean flag) {
        SecurityUser securityUser = SessionUser.SessionUser();
        int size = backlogDao.findAll(Specifications.where(e -> {
            e.eq("processId", processId);
            e.eq("linkId", linkId);
            e.eq("businessId", businessId);
            if (flag) {
                e.eq("transactorId", securityUser.getJobNumber());
            }
        })).size();
        return size > 0;
    }

    public String findNextUserName(Link link, Map<String, Object> vmap, Map<String, Object> map) {
        SessionUser sessionUser = SessionUser.SessionUser();
        if ("0".equals(link.getTransactorType())) {
            String str = link.getTransactor();// sql
            if (StringUtils.isBlank(str)) {

                throw WorkFlowException.ProcessConfigurationError("环节未配置sql,请检查工作流配置。");
            }
            String reg = "\\{([a-zA-Z0-9_]*)\\}";
            Pattern patten = Pattern.compile(reg);// 编译正则表达式
            Matcher matcher = patten.matcher(str);// 指定要匹配的字符串
            while (matcher.find() && matcher.groupCount() > 0) {
                String str1 = matcher.group(1);
                if (!vmap.containsKey(str1)) {
                    throw WorkFlowException.ProcessConfigurationError(str1 + "不存在业务表单,请检查工作流配置。");
                }
                str = str.replace("{" + str1 + "}", "'" + vmap.get(str1) + "'");
            }
            String useridStr = describDao.getStr(str);
            if (StringUtils.isBlank(useridStr) || "null".equals(useridStr)) {
                throw WorkFlowException.ProcessConfigurationError(link.getLinkName() + "环节,办理人员为空,请检查工作流配置。");
            }
            return useridStr;
        } else if ("1".equals(link.getTransactorType())) {// 固定人员
            return link.getTransactorId();
        } else if ("2".equals(link.getTransactorType())) {// 角色组
            String[] userid = link.getTransactorId().split(",");
            String roleids = StringUtils.join(userid, "','");
            List<SysUser> list = sysUsersDao.findUsers(roleids);
            String userids = "";
            for (int i = 0; i < list.size(); i++) {
                SysUser sys = list.get(i);
                userids += sys.getJobNumber() + ",";
            }
            return userids;
        } else if ("3".equals(link.getTransactorType())) {// java 类
            String userids = "";
            Workflow work = (Workflow) SpringUtil.getBean(link.getTransactor());
            List<SysUser> list = work.find(vmap);
            for (int i = 0; i < list.size(); i++) {
                SysUser sys = list.get(i);
                userids += sys.getJobNumber() + ",";
            }
            return userids;
        } else if ("4".equals(link.getTransactorType())) {// 自定义
            String expression = link.getTransactor();
            if (StringUtils.isNotBlank(expression)) {
                ELProcessor elProc = new ELProcessor();
                Iterator<Entry<String, Object>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Entry<String, Object> entry = it.next();
                    elProc.defineBean(entry.getKey(), entry.getValue());
                }
                String ret = (String) elProc.getValue(expression, String.class);
                if (StringUtils.isBlank(ret)) {
                    throw WorkFlowException.ProcessConfigurationError("流程办理人员为空,请检查工作流配置。");
                }
                return ret;
            } else {
                return sessionUser.getJobNumber();
            }
        }
        throw WorkFlowException.ProcessConfigurationError("流程未指定办理人员类型,请检查工作流配置。");
    }

    /**
     * @param links     当前环节
     * @param Linelists 下一步连线
     * @param vmap      业务数据
     * @param map       el表达式
     * @return
     */
    public Link findnext(Link links, List<Line> Linelists, Map<String, Object> vmap, Map<String, Object> map,
                         String businessId) {
        SecurityUser securityUser = SessionUser.SessionUser();
        addTransact(links, businessId);
        try {
            if (Linelists.size() == 1) {// 下一环节为单一环节
                List<Link> Linklist = getLinklist(Linelists.get(0).getLinkEndId(), Linelists.get(0).getProcessId());// 查询下一环节
                Link link = Linklist.get(0);
                if ("3".equals(link.getProcessType()) || "false".equals(link.getWhetherToSkip())) {// 结束环节
                    return links;
                } else {
                    if ("2".equals(link.getTransactorWay())) {// 多人办理
                        return links;
                    } else {
                        String userid = findNextUserName(link, vmap, map);// 查询下一步办理人
                        List<String> list = Arrays.asList(userid.split(","));
                        if (list.contains(securityUser.getJobNumber())) {// 下一环节办理人是当前人员
                            List<Line> Linelist = lineDao.getLines(link.getProcessId(), link.getId());
                            return findnext(link, Linelist, vmap, map, businessId);
                        } else {// 下一环节办理人不是当前人员
                            return links;
                        }
                    }
                }
            } else {
                for (int i = 0; i < Linelists.size(); i++) {
                    if ("1".equals(Linelists.get(i).getConditions())) {// 执行sql
                        String str = Linelists.get(i).getConditionsContent();
                        String reg = "\\{([a-zA-Z0-9_]*)\\}";
                        Pattern patten = Pattern.compile(reg);// 编译正则表达式
                        Matcher matcher = patten.matcher(str);// 指定要匹配的字符串
                        while (matcher.find() && matcher.groupCount() > 0) { // 此处find（）每次被调用后，会偏移到下一个匹配
                            str = str.replace("{" + matcher.group(1) + "}", "'" + vmap.get(matcher.group(1)) + "'");
                        }
                        if (describDao.judge(str)) {
                            String linkid = Linelists.get(i).getLinkEndId();
                            String ProcessId = Linelists.get(i).getProcessId();
                            List<Link> Linklist = getLinklist(linkid, ProcessId);
                            Link link = Linklist.get(0);
                            if ("3".equals(link.getProcessType()) || "false".equals(link.getWhetherToSkip())) {// 结束环节
                                return links;
                            } else {
                                if ("2".equals(link.getTransactorWay())) {// 多人办理
                                    return links;
                                } else {
                                    String userid = findNextUserName(link, vmap, map);// 查询下一步办理人
                                    List<String> list = Arrays.asList(userid.split(","));
                                    if (list.contains(securityUser.getJobNumber())) {// 下一环节办理人是当前人员
                                        List<Line> Linelist = lineDao.getLines(link.getProcessId(), link.getId());
                                        return findnext(link, Linelist, vmap, map, businessId);
                                    } else {// 下一环节办理人不是当前人员
                                        return links;
                                    }
                                }
                            }
                        }
                    } else {// 执行el表达
                        // 执行el表达
                        ELProcessor elProc = new ELProcessor();
                        Iterator<Entry<String, Object>> it = map.entrySet().iterator();
                        while (it.hasNext()) { // 设置变量
                            Entry<String, Object> entry = it.next();
                            elProc.defineBean(entry.getKey(), entry.getValue());
                        }
                        String expression = Linelists.get(i).getConditionsContent(); // 不需要使用${}或#{}
                        String ret = (String) elProc.getValue(expression, String.class);
                        if ("true".equals(ret)) {
                            String linkid = Linelists.get(i).getLinkEndId();
                            String ProcessId = Linelists.get(i).getProcessId();
                            List<Link> Linklist = getLinklist(linkid, ProcessId);
                            Link link = Linklist.get(0);
                            if ("3".equals(link.getProcessType()) || "false".equals(link.getWhetherToSkip())) {// 结束环节
                                return links;
                            } else {
                                if ("2".equals(link.getTransactorWay())) {// 多人办理
                                    return links;
                                } else {
                                    String userid = findNextUserName(link, vmap, map);// 查询下一步办理人
                                    List<String> list = Arrays.asList(userid.split(","));
                                    if (list.contains(securityUser.getJobNumber())) {// 下一环节办理人是当前人员
                                        List<Line> Linelist = lineDao.getLines(link.getProcessId(), link.getId());
                                        return findnext(link, Linelist, vmap, map, businessId);
                                    } else {// 下一环节办理人不是当前人员
                                        return links;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("======");
        }
        return links;
    }

    /**
     * 办理公用
     *
     * @param links
     * @param businessId
     * @param model
     * @param title
     * @param url
     * @param map
     * @param desc
     * @return
     */
    public synchronized Link handle(Link links, String businessId, String model, String title, String url,
                                    Map<String, Object> map, Describe desc) { // 查询线
        if (!"1".equals(links.getProcessType())
                && !findbacklog(links.getProcessId(), links.getId(), businessId, true)) {// 判断是否办理
            throw WorkFlowException.ProcesOperationError("流程已办理，请勿重复办理");
        }
        SecurityUser securityUser = SessionUser.SessionUser();
        Backlog backlog = findbacklog(links.getProcessId(), links.getId(), businessId);
        List<Line> NewLinelists = lineDao.getLines(links.getProcessId(), links.getId());
        if ("2".equals(links.getTransactorWay())) {// 多人办理
            deletebacklog(links.getProcessId(), links.getId(), businessId, true);// 删除自己的待办
            if (findbacklog(links.getProcessId(), links.getId(), businessId, false)) {// 还有待办返回本环节
                return links; // 返回本环节
            }
        } else {
            deletebacklog(links.getProcessId(), links.getId(), businessId, false);// 删除所有的待办
        }
        LinkTable bean = linkTableDao.findById(desc.getWorkflowTableId()).orElse(null);
        Map<String, Object> vmap = describDao.findall(bean.getWorkflowTableName(), bean.getWorkflowNoteFieldName(),
                businessId);
        links = findnext(links, NewLinelists, vmap, map, businessId);// 查询下一环节办理人是否为当前办理人，如果是返回下一环节
        List<Line> Linelists = lineDao.getLines(links.getProcessId(), links.getId());
        if (Linelists.size() == 1) {// 下一环节为单一环节
            List<Link> Linklist = getLinklist(Linelists.get(0).getLinkEndId(), Linelists.get(0).getProcessId());// 查询下一环节
            updateBusiness(bean, businessId, links.getLinkName());
            if ("3".equals(Linklist.get(0).getProcessType())) {// 结束环节
                updateBusiness(bean, businessId, Linklist.get(0).getLinkName());
                if (backlog == null) {
                    addMatterEnd(Linklist.get(0), securityUser.getJobNumber(), securityUser.getNickName(), businessId,
                            desc.getProcessModule(), title, url);
                } else {
                    addMatter(Linklist.get(0), backlog, securityUser.getJobNumber(), securityUser.getNickName());
                }
                addTransact(Linklist.get(0), businessId);
                return Linklist.get(0);
            }//AAAAAA
            addbacklogs(Linklist.get(0), businessId, model, title, url, map, vmap, bean); // 增加待办办理记录
            return Linklist.get(0);
        } else {
            // 执行el表达
            ELProcessor elProc = new ELProcessor();
            if (null != map) {
                Iterator<Entry<String, Object>> it = map.entrySet().iterator();
                while (it.hasNext()) { // 设置变量
                    Entry<String, Object> entry = it.next();
                    elProc.defineBean(entry.getKey(), entry.getValue());
                }
            }
            for (int i = 0; i < Linelists.size(); i++) {
                if ("1".equals(Linelists.get(i).getConditions())) {// 执行sql
                    String str = Linelists.get(i).getConditionsContent();
                    String reg = "\\{([a-zA-Z0-9_]*)\\}";
                    Pattern patten = Pattern.compile(reg);// 编译正则表达式
                    Matcher matcher = patten.matcher(str);// 指定要匹配的字符串
                    while (matcher.find() && matcher.groupCount() > 0) { // 此处find（）每次被调用后，会偏移到下一个匹配
                        str = str.replace("{" + matcher.group(1) + "}", "'" + vmap.get(matcher.group(1)) + "'");
                    }
                    if (describDao.judge(str)) {
                        String linkid = Linelists.get(i).getLinkEndId();
                        String ProcessId = Linelists.get(i).getProcessId();
                        List<Link> Linklist = getLinklist(linkid, ProcessId);
                        updateBusiness(bean, businessId, links.getLinkName());
                        if ("3".equals(Linklist.get(0).getProcessType())) {// 结束环节
                            updateBusiness(bean, businessId, Linklist.get(0).getLinkName());
                            if (backlog == null) {
                                addMatterEnd(Linklist.get(0), securityUser.getJobNumber(), securityUser.getNickName(),
                                        businessId, desc.getProcessModule(), title, url);
                            } else {
                                addMatter(Linklist.get(0), backlog, securityUser.getJobNumber(),
                                        securityUser.getNickName());
                            }
                            addTransact(Linklist.get(0), businessId);
                            return Linklist.get(0);
                        }
                        addbacklogs(Linklist.get(0), businessId, model, title, url, map, vmap, bean);
                        return Linklist.get(0);
                    }
                } else {// 执行el表达
                    String expression = Linelists.get(i).getConditionsContent(); // 不需要使用${}或#{}
                    String ret = (String) elProc.getValue(expression, String.class);
                    if ("true".equals(ret)) {
                        String linkid = Linelists.get(i).getLinkEndId();
                        String ProcessId = Linelists.get(i).getProcessId();
                        List<Link> Linklist = getLinklist(linkid, ProcessId);
                        updateBusiness(bean, businessId, links.getLinkName());
                        if ("3".equals(Linklist.get(0).getProcessType())) {// 结束环节
                            updateBusiness(bean, businessId, Linklist.get(0).getLinkName());
                            if (backlog == null) {
                                addMatterEnd(Linklist.get(0), securityUser.getJobNumber(), securityUser.getNickName(),
                                        businessId, desc.getProcessModule(), title, url);
                            } else {
                                addMatter(Linklist.get(0), backlog, securityUser.getJobNumber(),
                                        securityUser.getNickName());
                            }
                            addTransact(Linklist.get(0), businessId);
                            return Linklist.get(0);
                        }
                        addbacklogs(Linklist.get(0), businessId, model, title, url, map, vmap, bean);
                        return Linklist.get(0);
                    }
                }
            }
        }
        throw WorkFlowException.ProcessRunError("办理失败,跳转条件不成立");
    }
//=============================================================

    /**
     * 获取所有待办信息
     *
     */
    @Override
    public BasePage<Map<String,Object>> getBacklogs(BaseDto baseDto){
        return describDao.getBacklogs(baseDto);
    }

    @Override
    public List<Map> getProcessClassify(String process_module, String process_system_type) {
        return describDao.getProcessClassify(process_module, process_system_type);
    }

    @Override
    public Link Start(String classifyId, String businessId, String title, String url, Map<String, Object> map) {
        Describe desc = getDescribe(classifyId);
        List<Link> links = getLink(desc);// 查询开始环节
//		addTransact(links.get(0), null, businessId);// 增加开始环节办理记录
        return handle(links.get(0), businessId, desc.getProcessModule(), title, url, map, desc);
    }

    @Override
    public Link Start(String classifyId, String businessId, String title, String url) {
        Describe desc = getDescribe(classifyId);
        List<Link> links = getLink(desc);// 查询开始环节
//		addTransact(links.get(0), null, businessId);// 增加开始环节办理记录
        backlogDao.delete(businessId);
        //标题为空时自动补充标题
        if(StringUtils.isBlank(title)){
            SessionUser sessionUser = SessionUser.SessionUser();
            String userName = StringUtils.isBlank(sessionUser.getRealName())?sessionUser.getNickName():sessionUser.getRealName();
            title = String.format("%s发起【%s】流程(单号:%s)",userName,desc.getProcessClassify(),businessId);
        }
        return handle(links.get(0), businessId, desc.getProcessModule(), title, url, null, desc);
    }

    @Override
    public Link handle(Link link) {
        Map<String, Object> map = new HashMap<String, Object>();
        return handle(link, "", map);
    }

    @Override
    public Link handle(Link link, Map<String, Object> map) {
        return handle(link, "", map);
    }

    @Override
    public Link handle(Link link, String opinion) {
        Map<String, Object> map = new HashMap<String, Object>();
        return handle(link, opinion, map);
    }

    @Override
    public Link handle(Link link, String opinion, Map<String, Object> map) {
        SecurityUser securityUser = SessionUser.SessionUser();
        // 根据流程id和环节主键和办理人id查询到待办任务
        List<Backlog> list = backlogDao.findAll(Specifications.where(e -> {
            e.eq("processId", link.getProcessId());
            e.eq("linkId", link.getId());
            e.eq("transactorId", securityUser.getJobNumber());
        }));
        return handle(list.get(0).getId(), opinion, map);
    }

    @Override
    public Link handle(String backlogid, String opinion, Map<String, Object> map) {
        Backlog back = backlogDao.findById(backlogid).orElse(null);
        if (back == null) {
            throw WorkFlowException.ProcesOperationError("流程已办理，请勿重复办理");
        }
        Link links = linkDao.findById(back.getLinkId()).orElse(null);// 查询到办理环节
//		addTransact(links, opinion, back.getBusinessId());// 增加办理记录w
        setOpinion(opinion);
        Describe desc = dao.findById(back.getProcessId()).orElse(null);
        return handle(links, back.getBusinessId(), back.getModule(), back.getTitle(), back.getHandleUrl(), map, desc);
    }

    @Override
    public Link handle(String backlogid) {
        Map<String, Object> map = new HashMap<String, Object>();
        return handle(backlogid, "", map);
    }

    @Override
    public Link handle(String backlogid, String opinion, String signature) {
        setSignature(signature);
        Map<String, Object> map = new HashMap<String, Object>();
        return handle(backlogid, opinion, map);
    }

    @Override
    public Link handle(String backlogid, Map<String, Object> map) {
        return handle(backlogid, "", map);
    }

    @Override
    public BasePage<Transact> getTransact(BaseDto baseDto, String processId, String businessId) {
        return transactDao.QueryPageList(baseDto, Specifications.where(c -> {
            c.eq("processId", processId);
            c.eq("businessId", businessId);
        }));

    }

    @Override
    public List<Transact> getTransact(String processId, String businessId) {
        return transactDao.findAll(Specifications.where(c -> {
            c.eq(true, "processId", processId);
            c.eq(true, "businessId", businessId);
        }));
    }

    @Override
    public Link Back(String backlogid, String opinion, String signature) {
        setSignature(signature);
        Backlog back = backlogDao.findById(backlogid).orElse(null);
        Link link = linkDao.findById(back.getLinkId()).orElse(null);// 查询到当前办理环节
        setOpinion(opinion);
        addTransact(link, back.getBusinessId());// 增加当前环节办理记录
        Sort sort = Sort.by(Direction.ASC, "endTime");
        List<Transact> Transact = transactDao.findAll(Specifications.where(c -> {
            c.eq("processId", back.getProcessId());
            c.eq("businessId", back.getBusinessId());
        }), sort);
        Link plink = linkDao.findById(Transact.get(0).getLinkId()).orElse(null);// 查询开始办理环节
        deletebacklog(link.getProcessId(), link.getId(), back.getBusinessId(), false);// 删除所有的待办
        List<Transact> list = new ArrayList<>();
        list.add(Transact.get(0));
        Describe desc = dao.findById(back.getProcessId()).orElse(null);
        LinkTable bean = linkTableDao.findById(desc.getWorkflowTableId()).orElse(null);
        Map<String, Object> vmap = describDao.findall(bean.getWorkflowTableName(), bean.getWorkflowNoteFieldName(),
                back.getBusinessId());
        addbacklogs(plink, back.getBusinessId(), back.getModule(), back.getTitle(), back.getHandleUrl(), list, vmap,
                bean); // 增加待办办理记录
        updateBusiness(bean, back.getBusinessId(), plink.getLinkName());
        return plink;
    }

    /**
     * 存放环节和人员
     *
     * @param LinkName
     * @param UserName
     * @return
     */
    public Map<String, String> setProcess(String LinkName, String UserName) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("LinkName", LinkName);
        map.put("UserName", UserName);
        return map;
    }

    @Override
    public Result getProcessDescription(Examine examine) {
        List<Map<String, String>> Processlist = new ArrayList<Map<String, String>>();
        Describe desc = null;
        Link link = null;
        if (examine.getMenuId() != null) {
            desc = getDescribe(examine.getMenuId());
            // 获取最新版本的开始环节
            link = getLink(desc).get(0);
        } else {
            // 获取当前流程的开始环节
            desc = dao.findById(examine.getProcessId()).orElse(null);
            link = linkDao.findAll(Specifications.where(e -> {
                e.eq("processType", "1");
                e.eq("processId", examine.getProcessId());
            })).get(0);
        }
        LinkTable bean = linkTableDao.findById(desc.getWorkflowTableId()).orElse(new LinkTable());
        Map<String, Object> vmap = describDao.findall(bean.getWorkflowTableName(), bean.getWorkflowNoteFieldName(),
                examine.getBusinessId());
        Processlist.add(setProcess(link.getLinkName(), null));
        try {
            for (; ; ) {
                link = getNextLinkName(link, vmap);
                if (link == null) {
                    break;
                }
                Processlist.add(setProcess(link.getLinkName(), getNextUsername(link, vmap)));
                if ("3".equals(link.getProcessType())) {
                    break;
                }
            }
        } catch (Exception e) {
            Processlist.add(setProcess("未知环节", ""));
            System.err.println("获取流程环节异常结束，请检查配置是否为纯sql配置，是否有字段无法获取值");
        }
        return Result.resultOk(Processlist);
    }

    /**
     * 查询下一环节，纯sql配置
     *
     * @param link
     * @param vmap
     * @return
     */
    public Link getNextLinkName(Link link, Map<String, Object> vmap) {
        List<Line> Linelists = lineDao.getLines(link.getProcessId(), link.getId());// 查询下一环节连线
        if (Linelists.size() == 1) {// 下一环节为单一环节
            List<Link> Linklist = getLinklist(Linelists.get(0).getLinkEndId(), Linelists.get(0).getProcessId());// 查询下一环节
            // 通过流程id和关联id
            return Linklist.get(0);
        } else {
            for (int i = 0; i < Linelists.size(); i++) {
                if ("1".equals(Linelists.get(i).getConditions())) {// 执行sql
                    String str = Linelists.get(i).getConditionsContent();
                    String reg = "\\{([a-zA-Z0-9_]*)\\}";
                    Pattern patten = Pattern.compile(reg);// 编译正则表达式
                    Matcher matcher = patten.matcher(str);// 指定要匹配的字符串
                    while (matcher.find() && matcher.groupCount() > 0) { // 此处find（）每次被调用后，会偏移到下一个匹配
                        str = str.replace("{" + matcher.group(1) + "}", "'" + vmap.get(matcher.group(1)) + "'");
                    }
                    if (describDao.judge(str)) {
                        String linkid = Linelists.get(i).getLinkEndId();
                        String ProcessId = Linelists.get(i).getProcessId();
                        List<Link> Linklist = getLinklist(linkid, ProcessId);
                        return Linklist.get(0);
                    }
                }
            }
        }
        return null;
    }

    /**
     * 查询下一环节办理人员，纯sql配置
     *
     * @param link
     * @return
     */
    public String getNextUsername(Link link, Map<String, Object> vmap) {
        String UserName = "";
        try {
            if ("0".equals(link.getTransactorType())) {
                String str = link.getTransactor();// sql
                if (StringUtils.isBlank(str)) {
                    throw WorkFlowException.ProcessConfigurationError("环节未配置sql,请检查工作流配置。");
                }
                String reg = "\\{([a-zA-Z0-9_]*)\\}";
                Pattern patten = Pattern.compile(reg);// 编译正则表达式
                Matcher matcher = patten.matcher(str);// 指定要匹配的字符串
                while (matcher.find() && matcher.groupCount() > 0) {
                    String str1 = matcher.group(1);
                    if (!vmap.containsKey(str1)) {
                        throw WorkFlowException.ProcessConfigurationError(str1 + "不存在业务表单,请检查工作流配置。");
                    }
                    str = str.replace("{" + str1 + "}", "'" + vmap.get(str1) + "'");
                }
                String useridStr = describDao.getStr(str);
                if (StringUtils.isBlank(useridStr) || "null".equals(useridStr)) {
                    throw WorkFlowException.ProcessConfigurationError(link.getLinkName() + "环节,办理人员为空,请检查工作流配置。");
                }
                String[] userids = useridStr.split(",");
                for (int i = 0; i < userids.length; i++) {
                    try {
                        String name = sysUserDao.findByJobNumber(userids[i]).getRealName();
                        if (StringUtils.isNotBlank(name)) {
                            UserName += name + ",";
                        }
                    } catch (Exception e) {
                        throw WorkFlowException.ProcessConfigurationError(userids[i] + ",办理人员不存在,请检查工作流配置。");
                    }
                }
            } else if ("1".equals(link.getTransactorType())) {// 固定人员
                String[] userids = link.getTransactorId().split(",");
                for (int i = 0; i < userids.length; i++) {
                    String name = sysUserDao.findByJobNumber(userids[i]).getRealName();
                    if (StringUtils.isNotBlank(name)) {
                        UserName += name + ",";
                    }
                }
            } else if ("2".equals(link.getTransactorType())) {// 角色组
                String[] userids = link.getTransactorId().split(",");
                String roleids = StringUtils.join(userids, "','");
                List<SysUser> list = sysUsersDao.findUsers(roleids);
                for (int i = 0; i < list.size(); i++) {
                    SysUser sys = list.get(i);
                    UserName += sys.getRealName() + ",";
                }
            }
            if (!"".equals(UserName)) {
                UserName = UserName.substring(0, UserName.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return UserName;
    }

    @Override
    public Result getlink(String id) {
        return Result.resultOk(linkDao.findById(id).orElse(new Link()));
    }

}
