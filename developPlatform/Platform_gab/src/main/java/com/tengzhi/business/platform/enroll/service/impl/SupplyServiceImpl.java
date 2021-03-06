package com.tengzhi.business.platform.enroll.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.property.Property;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.security.common.tool.spring.SpringUtil;
import com.tengzhi.base.tools.MD5.MD5Util;
import com.tengzhi.business.cg.da.sysCustomer.dao.SysCustomerDao;
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;
import com.tengzhi.business.cg.da.sysCustomer.service.SysCustomerService;
import com.tengzhi.business.personnel.eHrWorker.dao.eHrWorkerDao;
import com.tengzhi.business.personnel.eHrWorker.model.EHrWorker;
import com.tengzhi.business.platform.common.vo.ExpertVo;
import com.tengzhi.business.platform.enroll.constant.GabUserTypeEnum;
import com.tengzhi.business.platform.enroll.dao.*;
import com.tengzhi.business.platform.enroll.model.GUserInfo;
import com.tengzhi.business.platform.enroll.model.G_Expert;
import com.tengzhi.business.platform.enroll.model.G_Supply;
import com.tengzhi.business.platform.enroll.model.G_personal;
import com.tengzhi.business.platform.enroll.service.SupplyService;
import com.tengzhi.business.platform.enroll.vo.modelVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.role.service.SysRoleService;
import com.tengzhi.business.system.sms.constant.SmsType;
import com.tengzhi.business.system.sms.model.SysSmsLog;
import com.tengzhi.business.system.sms.service.SmsService;
import com.tengzhi.business.system.sms.service.SysSmsService;
import com.tengzhi.business.system.user.dao.GabSysUserDao;
import com.tengzhi.business.system.user.model.SysUser;
import com.tengzhi.business.system.user.service.SysUserService;
import com.tengzhi.business.system.workflow.service.ProcessService;
import com.tengzhi.business.system.workflow.vo.Examine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class SupplyServiceImpl implements SupplyService {
    @Autowired
    private SysGenNoteService sysGenNoteService;
    /**
     * JAP
     */
    @Autowired
    private SupplyDao supplyDao;
    /**
     * 动态sql
     */
    @Autowired
    private SupplySqlDao supplySqlDao;
    @Autowired
    private ExpertDao expertDao;
    @Autowired
    private PersonalDao personDao;
    @Autowired
    private GabSysUserDao gabSysUserDao;
    @Autowired
    SmsService smsService;
    @Autowired
    SmsDao smsDao;
    @Autowired
    private ProcessService processService;
    @Autowired
    private SysUserService sysuserService;
    @Autowired
    SysSmsService sysSmsService;
    @Autowired
    private SysCustomerDao sysCustomerDao;
    @Autowired
    private eHrWorkerDao eHrWorkerDao;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private GUserInfoDao gUserInfoDao;

    @SuppressWarnings("static-access")
    @Override
    public Result save(modelVo modelVo) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        String note = UUID.randomUUID().toString(true);
        if (modelVo.getSysuser() != null) {
            SysUser user = modelVo.getSysuser();
            SysCustomer customer = new SysCustomer();
            Property pro = SpringUtil.getBean(Property.class);
            user.setOrgId(pro.getBusiness().getDefaultCorp());
            //获取发送到手机的验证码
            String randcode = supplyDao.GetRandomCode(user.getMobile());
            if (user.getRandCode().equals(randcode)) {
                // 验证的关键字段不能重复
                if (judgeUnique(user)) {
                    // 用户基础信息表
                    user.setIsForbidden(false);
                    user.setGenTime(new Timestamp(System.currentTimeMillis()));
                    user.setJobNumber(user.getUserId());
                    user.Validate();
                    user.setPassword(MD5Util.encode(user.getPassword()));
                    if (modelVo.getSupply() != null) {
                        customer.setCustomerId(user.getUserId());
                        sysCustomerDao.save(customer);
                        G_Supply supply = modelVo.getSupply();
                        supply.setRegisterDate(new Date());
                        supply.setSupplyNote(note);
                        supply.setSupplyName(user.getUserId());
                        supply.setStatus("登记");
                        supply.setSupplyUid(note);
                        supplyDao.save(supply);
                        // 供应商类型和唯一标识保存到用户表
                        user.setSupplyId(supply.getSupplyNote());
                        user.setType("厂商");
                        user.setAssociatedId(supply.getSupplyNote());
                        // 授权
                        map2.put("roleId", "GYSINIT");
                        arrayList.add(map2);
                        map.put("userId", user.getUserId());
                        map.put("roleMap", arrayList);
                        sysuserService.saveUserAuth(map);
                        //数据同步到sys_customer
                        SysCustomer customer_model = new SysCustomer();

                    } else if (modelVo.getPersonal() != null) {
                        customer.setCustomerId(user.getUserId());
                        sysCustomerDao.save(customer);
                        G_personal person = modelVo.getPersonal();
                        person.setRegisterDate(new Date());
                        person.setPersonNote(note);
                        person.setPersonName(user.getUserId());
                        person.setStatus("登记");
                        personDao.save(person);
                        user.setSupplyId(person.getPersonNote());
                        user.setType("客户");

                        //客户授权
                        map2.put("roleId", "KHINIT");
                        arrayList.add(map2);
                        map.put("userId", user.getUserId());
                        map.put("roleMap", arrayList);
                        sysuserService.saveUserAuth(map);

                    } else if (modelVo.getExpert() != null) {
                        // 专家注册
//                        examine.setTitle("专家审批");
//                        examine.setMenuId("R400102");// R400102R400102
//                        examine.setUrl("/platform/expert/expertapproval.html");
//                        examine.setBusinessId(note);

                        EHrWorker eHrWorker = new EHrWorker();
                        eHrWorker.setWorkerId(user.getUserId());
                        eHrWorker.setWorkerPhone(user.getMobile());
                        eHrWorkerDao.save(eHrWorker);
                        G_Expert expert = modelVo.getExpert();
                        expert.setRegisterDate(new Date());
                        expert.setExpertNote(note);
                        expert.setBindSupplyid(user.getUserId());
                        user.setType(user.getExpertType());
                        expert.setExpertName(user.getUserId());
                        expert.setStatus("登记");
                        expertDao.save(expert);
                        user.setSupplyId(expert.getExpertNote());
                        user.setType("专家");
                        user.setAssociatedId(expert.getExpertNote());
                        //专家授权
                        map2.put("roleId", "ZJINIT");
                        arrayList.add(map2);
                        map.put("userId", user.getUserId());
                        map.put("roleMap", arrayList);
                        sysuserService.saveUserAuth(map);
                    }
                    user.setDeleteLogo(false);


                    //同步userinfo表数据
                    GUserInfo guserInfo = new GUserInfo();
                    guserInfo.setUserId(user.getUserId());
                    guserInfo.setUserMtel(user.getMobile());
                    guserInfo.setUserPwd(user.getPassword());
                    guserInfo.setDataDate(new Date());
                    guserInfo.setUserType(user.getType());
                    guserInfo.setFlag("登记");
                    gUserInfoDao.save(guserInfo);


                    gabSysUserDao.save(user);
                    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                            .getRequestAttributes();
                    HttpServletRequest request = servletRequestAttributes.getRequest();
                    GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_USER");
                    List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
                    list.add(auth);
                    SessionUser sessionUser = new SessionUser(user.getUserId(), user.getUserId(), "", list);
                    sessionUser.setSysUser(user);
                    //sessionUser.setCode("SD");
                    request.getSession().setAttribute("authentication", sessionUser);
                    // 审批
//                    processService.startFinishFirstStep(examine);
                    return Result.resultOk("注册成功，即将跳转登录页面");

                } else {
                    return Result.resultError(300, "此用户名已存在");
                }
            } else {
                return Result.resultError(300, "验证码输入有误，或验证码已过期");
            }

        } else {
            return Result.resultError(300, "注册失败");
        }

    }

    public void approvalSave() {

    }

    @Override
    public boolean judgeUnique(SysUser sysUser) {

        if (null == sysUser.getType()) {
            // 专家或需求方
            return gabSysUserDao.findAll(Specifications.where(false, (c) -> {
                c.eq("jobNumber", sysUser.getJobNumber());
                c.eq("userId", sysUser.getUserId());
                c.eq("nickName", sysUser.getNickName());

            })).size() <= 0;
        } else {
            // 供应商
            return gabSysUserDao.findAll(Specifications.where(false, (c) -> {
                c.eq("jobNumber", sysUser.getJobNumber());
                c.eq("userId", sysUser.getUserId());
                c.eq("nickName", sysUser.getNickName());
                c.eq("orgId", sysUser.getOrgId());
                c.eq("orgName", sysUser.getOrgName());

            })).size() <= 0;

        }

    }

    @Override
    public modelVo findById() {// String id
        modelVo entry = new modelVo();
        SessionUser securityUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = securityUser.getUserId();
        SysUser user = gabSysUserDao.findById(id).orElse(null);
        entry.setSysuser(user);
        if (null == user.getType()) {

        } else if (user.getType().contains("厂商") || user.getType().contains("特色经贸商")) {

            entry.setSupply(supplyDao.findById(user.getSupplyId()).orElse(null));
        } else {

        }
        return entry;
    }

    @Override
    public void update(modelVo vo) {
        // TODO Auto-generated method stub
        // 修改用户基础信息
        SysUser user = gabSysUserDao.findById(vo.getSysuser().getUserId()).orElse(null);
        gabSysUserDao.dynamicSave(vo.getSysuser(), user);
        // 修改企业基础信息
        if (vo.getSupply() != null) {
            supplyDao.dynamicSave(vo.getSupply(), supplyDao.findById(user.getSupplyId()).orElse(null));
        }
        G_Supply g_supply = vo.getSupply();
        //数据同步到客户表
        SysCustomer sysCustomer = new SysCustomer();
        sysCustomer.setCustomerName(g_supply.getSupplyName());
        sysCustomer.setCustomerExp(g_supply.getCustomerExp());
        sysCustomer.setCustomerJe(g_supply.getSupplyJe());//客户额度
        sysCustomer.setCustomerDay(g_supply.getSupplyDay());//付款天数
        sysCustomer.setCustomerLevel(g_supply.getSupplyLevel());//客户级别
        sysCustomer.setCustomerProvince(g_supply.getDistrict());//单位区域
        sysCustomerDao.dynamicSave(sysCustomer, sysCustomerDao.findById(user.getSupplyId()).orElse(new SysCustomer()));


    }

    @Override
    public Map<String, Object> getAll(String cur, String pageSize, String pageIndex, String searchType, String... keyWords) {
        String sql = "";
        String sqlCount = "";
        Map<String, Object> rmap = new HashMap<String, Object>();
        String type = "夹具厂商 ";
        if (cur.equals("jj")) {
            type = "夹具厂商";
        } else if (cur.equals("hh")) {
            type = "混合厂商' or supply_type='整合厂商";
        } else if (cur.equals("fj")) {
            type = "辅件厂商";
        } else if (cur.equals("ts")) {
            type = "特色经贸商";
        }
        if (keyWords.length > 0 && searchType.equals("searchName")) {
            sql = "select supply_note \"supplyNote\",supply_info \"supplyInfo\",key_word \"keyWord\",supply_name \"supplyName\",('/system/upload/getImage?line_primary='||head_img) \"headImg\",'"
                    + cur + " stype' from g_supply where status ='核准'  and  supply_type  = '" + type + "' and supply_name like '%" + keyWords[0] + "%' limit " + pageSize + " offset ("
                    + pageIndex + " -1 ) * " + pageSize;
            sqlCount = "select supply_note \"supplyNote\",supply_info \"supplyInfo\",key_word \"keyWord\",supply_name \"supplyName\",('/system/upload/getImage?line_primary='||head_img) \"headImg\" from g_supply where status ='核准'  and supply_type  = '"
                    + type + "' and supply_name like '%" + keyWords[0] + "%' ";
        } else if (keyWords.length > 0 && searchType.equals("SearchHotWords")) {
            sql = "select supply_note \"supplyNote\",supply_info \"supplyInfo\",key_word \"keyWord\",supply_name \"supplyName\",('/system/upload/getImage?line_primary='||head_img) \"headImg\",'"
                    + cur + " stype' from g_supply where status ='核准'  and  key_word like '%" + keyWords[0] + "%' limit " + pageSize + " offset ("
                    + pageIndex + " -1 ) * " + pageSize;
            sqlCount = "select supply_note \"supplyNote\",supply_info \"supplyInfo\",key_word \"keyWord\",supply_name \"supplyName\",('/system/upload/getImage?line_primary='||head_img) \"headImg\" from g_supply where status ='核准'  and supply_type  = '"
                    + type + "' and supply_name like '%" + keyWords[0] + "%' ";
        } else {
            sql = "select supply_note \"supplyNote\",supply_info \"supplyInfo\",key_word \"keyWord\",supply_name \"supplyName\",('/system/upload/getImage?line_primary='||head_img) \"headImg\",'"
                    + cur + " stype' from g_supply where status ='核准'  and  supply_type  = '" + type + "' limit " + pageSize + " offset ("
                    + pageIndex + " -1 ) * " + pageSize;
            sqlCount = "select supply_note \"supplyNote\",supply_info \"supplyInfo\",key_word \"keyWord\",supply_name \"supplyName\",('/system/upload/getImage?line_primary='||head_img) \"headImg\" from g_supply where status ='核准'  and supply_type  = '"
                    + type + "'";
        }
        List<Map<String, Object>> list = supplyDao.SelectListMap(sql);
        rmap.put("data", list);
        list = supplyDao.SelectListMap(sqlCount);
        rmap.put("total", list.size());
        return rmap;

    }

    @Override
    public Map<String, Object> getSrchExpertTeam(String cur, String pageSize, String pageIndex) {
        Map<String, Object> rmap = new HashMap<String, Object>();
        String type = " ";
        if ("Team".equals(cur)) {
            type = "工艺专家";
        } else if ("artTeam".equals(cur)) {
            type = "工艺专家";
        } else if ("saleBeforeTeam".equals(cur)) {
            type = "售前专家";
        } else if ("saleAfterTeam".equals(cur)) {
            type = "售后专家";
        } else {

        }
        String sql = " 	SELECT ('/system/upload/getImage?line_primary='||head_img) headImgSrc, "
                + "    expert_note,  COALESCE ((SELECT user_name from g_userinfo  where user_id = g_expert.expert_note limit 1 ),'无姓名' ) nick_name, "
                + " strengths,   job_age, level ,introduce  from  g_expert  where  status ='核准' and  expert_type ='" + type + "'  limit "
                + pageSize + " offset (" + pageIndex + " -1 ) * " + pageSize;

        List<Map<String, Object>> list = supplyDao.SelectListMap(sql);
        rmap.put("data", list);
        sql = "	SELECT   job_age, level ,introduce  from  g_expert  where status ='核准'  and   expert_type = '" + type + "'";
        list = supplyDao.SelectListMap(sql);
        rmap.put("total", list.size());
        return rmap;
    }

    @Override
    public Map<String, Object> getByidExpert(String id) {
        //此id为sys_user表的user_id,之前为supply_id
        Map<String, Object> rmap = new HashMap<String, Object>();
        String corpId = SessionUser.getDefaultCorp();
        String userId = supplyDao.getGuserInfoId(id, corpId);
        if (userId == null) {
            userId = id;
        }
        rmap.put("data", supplySqlDao.GetSingleExpert(userId));
        rmap.put("art", supplySqlDao.GetAdvisory(id));
        rmap.put("random_data", supplySqlDao.getRandomExpert(userId));
        return rmap;
    }

    @Override
    public Map<String, Object> getRandomExpert() {
        // TODO Auto-generated method stub
        Map<String, Object> rmap = new HashMap<String, Object>();
        String sql = "SELECT  ('/system/upload/getImage?line_primary='||head_img) headImgSrc,  expert_note, (SELECT  supply_name from  g_supply    where supply_note =  g_expert.bind_supplyid   LIMIT 1 )supply_name,   expert_type,sale_experience, expert_name,job_age,main_job, level ,introduce  "
                + "from  g_expert where status = '核准'  order by random() limit 5 ";
        rmap.put("data", supplyDao.SelectListMap(sql));
        return rmap;
    }

    @Override
    public BasePage<Map<String, Object>> getSrchGridList(BaseDto baseDto) throws IOException {

        String sql = "SELECT (SELECT nick_name  from sys_user where supply_id  =g_supply.supply_note LIMIT 1 ) as nick_name,"
                + "  supply_name, "
                + " supply_note,supply_info,local_size from g_supply   ";
        String countsql = "select count(*) cn from (" + sql + ") t";
        baseDto.setSortField("supply_note");
        baseDto.setSortOrder("desc");
        return supplyDao.QueryPageList(sql, countsql, baseDto);
    }

    @Override
    public List<Map<String, Object>> getSupply(String supplyType) {
        // TODO
        String sql = "select supply_note \"supplyNote\",supply_name \"supplyName\",supply_info \"supplyInfo\",key_word \"keyWord\",('/system/upload/getImage?line_primary='||head_img) \"headImg\" from g_supply where supply_type = '"
                + supplyType + "' and status='核准'  order by register_date desc limit 8 ";
        return supplyDao.SelectListMap(sql);
    }

    @Override
    public Result getAllsupply() {
        // TODO
        String sql = "select supply_note,supply_name  from g_supply where  status = '核准'  ";
        return Result.resultOk(supplyDao.SelectListMap(sql));
    }

    @Override
    public List<SelectVo> getAllsupplyxxx() {
        String sql = "select supply_note,supply_name from g_supply where  status = '核准'  ";
        List<Map<String, Object>> l = supplyDao.SelectListMap(sql);
        List<SelectVo> list = new ArrayList<>();
        l.forEach(item -> {
            SelectVo selectVo = new SelectVo(item.get("supply_note"), item.get("supply_name").toString());
            list.add(selectVo);
        });
        return list;
    }

    @Override
    public Map<String, Object> getExpertTeamByParam(List<ExpertVo> expertVos) {
        String cur = expertVos.get(0).getCur();
        String pageSize = expertVos.get(0).getPageSize();
        String pageIndex = expertVos.get(0).getPageIndex();
        Map<String, Object> rmap = new HashMap<String, Object>();
        StringBuilder param = new StringBuilder();
        String type = " ";
        if ("Team".equals(cur)) {
            type = "工艺专家";
        } else if ("artTeam".equals(cur)) {
            type = "工艺专家";
        } else if ("saleBeforeTeam".equals(cur)) {
            type = "售前专家";
        } else if ("saleAfterTeam".equals(cur)) {
            type = "售后专家";
        } else {
        }

        for (int i = 0; i < expertVos.size(); i++) {
            if (expertVos.get(i).getType().equals("allType")) {
                if (expertVos.get(i).getValue().equals("0-3年")) {
                    param.append("and job_age>=0 and job_age<=3");
                } else if (expertVos.get(i).getValue().equals("3-5年")) {
                    param = new StringBuilder("and job_age>=3 and job_age<=5");
                } else if (expertVos.get(i).getValue().equals("5-8年")) {
                    param = new StringBuilder("and job_age>=5 and job_age<=8");
                } else if (expertVos.get(i).getValue().equals("8-15年")) {
                    param = new StringBuilder("and job_age>=8 and job_age<=15");
                } else if (expertVos.get(i).getValue().equals("15年以上")) {
                    param = new StringBuilder("and job_age>=15");
                }
            } else if (expertVos.get(i).getType().equals("filterLevel")) {
                if (expertVos.get(i).getValue().equals("一级")) {
                    param.append(" and level='一级'");
                } else if (expertVos.get(i).getValue().equals("二级")) {
                    param.append(" and level='二级'");
                } else if (expertVos.get(i).getValue().equals("三级")) {
                    param.append(" and level='三级'");
                }
            }

        }
        String sql = " 	SELECT ('/system/upload/getImage?line_primary='||head_img) headImgSrc, "
                + "    expert_note,  COALESCE ((SELECT user_name from g_userinfo  where user_id = g_expert.expert_note limit 1 ),'无姓名' ) nick_name, "
                + " strengths,   job_age, level ,introduce  from  g_expert  where  status ='核准' and  expert_type ='" + type + "' " + param + "  limit "
                + pageSize + " offset (" + pageIndex + " -1 ) * " + pageSize;

        List<Map<String, Object>> list = supplyDao.SelectListMap(sql);
        rmap.put("data", list);
        sql = "	SELECT   job_age, level ,introduce  from  g_expert  where status ='核准'  and   expert_type = '" + type + "'";
        list = supplyDao.SelectListMap(sql);
        rmap.put("total", list.size());
        return rmap;
    }

    @Override
    public Map<String, Object> getExpertTeamBykeyWords(String keyWords) {
        Map<String, Object> rmap = new HashMap<String, Object>();
        String sql = " 	SELECT ('/system/upload/getImage?line_primary='||head_img) headImgSrc, "
                + "    expert_note,  COALESCE ((SELECT user_name from g_userinfo  where user_id = g_expert.expert_note limit 1 ),'无姓名' ) nick_name, "
                + " strengths,   job_age, level ,introduce  from  g_expert  where  status ='核准'  and expert_name like'%" + keyWords + "%'  limit 8"
                + " offset (" + "1" + " -1 ) * 8";
        List<Map<String, Object>> list = supplyDao.SelectListMap(sql);
        rmap.put("data", list);
        sql = "	SELECT   job_age, level ,introduce  from  g_expert  where status ='核准'  and   expert_name like '" + keyWords + "'";
        list = supplyDao.SelectListMap(sql);
        rmap.put("total", list.size());
        return rmap;
    }

    @Override
    public Map<String, Object> getAdvisory(String supplyUid) {
        Map<String, Object> map = new HashMap();
        String sql = "select article_id,title,classify  from g_advisory where founder_id = (select user_id from sys_user where user_id = '" + supplyUid + "') and status='核准' order by release_date desc";
        List<Map<String, Object>> list = supplyDao.SelectListMap(sql);
        map.put("data", list);
        return map;
    }

    // getAllsupply

    /**
     * 审批查阅
     */
    @Override
    public Result getSupplyByNote(String needNote) {
        Result rs = new Result();
        String sql = "SELECT * from g_supply   where  supply_note  = '" + needNote + "'";
        String user_sql = "SELECT  user_id, nick_name,mobile, email   from sys_user where supply_id = '" + needNote
                + "'";

        rs.put("data", supplyDao.QueryhumpMap(sql).get(0));
        rs.put("user", supplyDao.QueryhumpMap(user_sql).get(0));

        return rs;

    }

    @Autowired
    private SysCustomerService sysCustomerService;

    /**
     * 审批结束后，给对应的供应商授权
     *
     * @throws Exception
     */
    @Override
    public Result agree(Examine examine) throws Exception {
        // 审批流
        processService.agree(examine);
        // 给对应的供应商进行授权
        String note = examine.getBusinessId();
        String status = supplyDao.getSupplyStatus(note);
        GUserInfo gUserInfo = gUserInfoDao.findById(note).orElse(new GUserInfo());
        Map<String, Object> mapExtension = (Map<String, Object>) JSONObject.parse(examine.getExtension());
        boolean notExistsUserFlag = gUserInfoDao.findAll(Specifications.where((c) -> c.eq("erpUserid", String.valueOf(mapExtension.get("erpUserid"))))).size() > 0;
        if (notExistsUserFlag) {
            return Result.error("该用户已经被绑定，请重新选择！");
        }
        if ("核准".equals(status)) {
            // 获取用户id
            Map<String, Object> map = new HashMap<String, Object>();
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("roleId", GabUserTypeEnum.供应厂商.getInitRole());
            list.add(map2);
            map.put("userId", String.valueOf(mapExtension.get("erpUserid")));
            map.put("roleMap", list);
            // 授权
            sysuserService.saveUserAuth(map);
            //更新g_userinfo 表的erp_userid erp_corpid
            gUserInfo.setErpCorp(String.valueOf(mapExtension.get("erpCorp")));
            gUserInfo.setErpUserid(String.valueOf(mapExtension.get("erpUserid")));
            gUserInfoDao.dynamicSave(gUserInfo, gUserInfoDao.findById(gUserInfo.getUserId()).orElse(null));
            // 发送短信给对应供应商
            SysUser user = new SysUser();
            user.setUserId(gUserInfo.getUserId());
            user.setMobile(gUserInfo.getUserMtel());
            user.setOrgId(gUserInfo.getDataCorp());
            SysSmsLog sysSmsLog = sysSmsService.send(SysSmsLog.GenSms(user, SmsType.GREANT, "已审核通过"));
            if (sysSmsLog.getSuccess()) {

            } else {
                throw new RuntimeException("审核通过，短信发送失败");
            }
        }

        return Result.resultOk("审批通过");
    }

    @Override
    public Result getCode(SysUser sysuser) {
        SysSmsLog sysSmsLog = sysSmsService.send(SysSmsLog.GenSms(sysuser, SmsType.GABREGISTER, RandomUtil.randomNumbers(6)));
        if (sysSmsLog.getSuccess()) {
            return Result.resultOkMsg("验证码已经发送，请及时查收，5分钟内有效!");
        } else {
            return Result.error("验证码发送失败，请重试");
        }
    }


    @Override
    public Map<String, Object> getSupplyById(String GuserInfoId) {
        String sql = "select supply_address \"supplyAddress\",  supply_note \"supplyNote\",supply_name \"supplyName\",'/system/upload/getImage?line_primary='||head_img \"headImg\",supply_info \"supplyInfo\",key_word \"keyWord\",customer_exp \"customerExp\",supply_uid \"supplyUid\"  from g_supply where supply_note = '" + GuserInfoId + "'";
        List<Map<String, Object>> list = supplyDao.QueryhumpMap(sql);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("supplyName", "暂无名称");
            map.put("supplyNote", "");
            map.put("headImg", "");
            map.put("supplyInfo", "");
            map.put("keyWord", "");
            map.put("customerExp", "");
            map.put("supplyAddress", "暂无数据");
            return map;

        }
    }


    @Override
    public BasePage<Map<String, Object>> getList(BaseDto dto) {
        // Map<String, String> map = dto.getParamsMap();
        String sql = " select  * ,(SELECT  is_platform  from  g_userinfo  where  user_id = g_supply.supply_note  limit 1   ) platformUser  from g_supply   ";
        Map<String, String> map = dto.getParamsMap();
        String where = SqlJoint.where(e -> {

            e.contains("supply_name", map.get("supply_name"));
            e.eq("supply_type", map.get("supply_type"));
            e.ge("register_date", map.get("srchRq1"));
            e.le("register_date", map.get("srchRq2"));

        }, true);
        return supplyDao.QueryToMapPage(dto, sql + where);
    }

    public boolean GUserinfoUnique(GUserInfo gUserInfo) {
        // 需求方客户
        return gUserInfoDao.findAll(Specifications.where(false, (c) -> {
            c.eq("userId", gUserInfo.getUserId());
            c.eq("userName", gUserInfo.getUserName());
            c.eq("userMtel", gUserInfo.getUserMtel());
        })).size() <= 0;

    }

    @Override
    public Result SupplySave(modelVo modelVo) throws Exception {
        if (modelVo.getgUserInfo() != null) {
            GUserInfo gUserInfo = modelVo.getgUserInfo();
            G_Supply supply = modelVo.getSupply();
            Property property = SpringUtil.getBean(Property.class);
            //注册需要同步erp 客户表
            SysCustomer customer1 = new SysCustomer();




            // 验证的关键字段不能重复
            if (GUserinfoUnique(gUserInfo)) {
                // 用户基础信息表
                gUserInfo.setFlag("登记");
                gUserInfo.setDataDate(new Date());
                gUserInfo.setUserType(GabUserTypeEnum.供应厂商.getName());
                gUserInfo.setDataCorp(property.getBusiness().getDefaultCorp());
             //   gUserInfo.setUserPwd(MD5Util.encode(gUserInfo.getUserPwd()));

                gUserInfo.setPlatform(true);

                if (supply != null) {
                    supply.setSupplyNote(gUserInfo.getUserId());
                    supply.setSupplyName(gUserInfo.getUserName());
                    supply.setStatus("登记");
                    // supply.setPlatform(true);
                    supply.setRegisterDate(new Date());

                }
                gUserInfo.Validate();
                gUserInfoDao.save(gUserInfo);
                supplyDao.save(supply);
                //添加初始权限
	                   /* Map<String, Object> map = new HashMap<String, Object>();
	        			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        			Map<String, Object> map2 = new HashMap<String, Object>();
	        			map2.put("roleId", "GYSINIT");
	        			list.add(map2);
	        			map.put("userId", user.getUserId());
	        			map.put("roleMap", list);
	        			sysuserService.saveUserAuth(map);*/
                return Result.resultOk("保存成功");

            } else {
                return Result.resultError(300, "该专家的用户账号或用户名称已存在");
            }


        } else {
            return Result.resultError(300, "新增失败");
        }

    }

    @Override
    public Result getSupplyStatus(String supplyNote, String status) {
        String flag = supplyDao.getSupplyStatus(supplyNote);
        if (status.equals(flag)) {
            return Result.resultOk("操作成功！");
        }
        return Result.error("该单不是“" + status + "”状态,或该数据非平台用户添加,不能操作！");

    }

    @Override
    public Result getSupplyStatus(String supplyNote) {
        String flag = supplyDao.getSupplyStatus(supplyNote);

        return Result.resultOk(flag);

    }

    @Override
    public modelVo SupplyInfo(String supplyNote) throws Exception {
        modelVo entry = new modelVo();
        //用户基础信息表
        GUserInfo gUserInfo = gUserInfoDao.findById(supplyNote).orElse(new GUserInfo());
        //供应商扩展表
        G_Supply supply = supplyDao.findById(supplyNote).orElse(new G_Supply());
        entry.setgUserInfo(gUserInfo);
        entry.setSupply(supply);
        return entry;
    }

    @Override
    public Result supplyedit(modelVo vo) {
        GUserInfo gUserInfo = vo.getgUserInfo();
        G_Supply supply = vo.getSupply();
        String userid = gUserInfo.getUserId();
        //修改用户基础信息
        GUserInfo   model  = gUserInfoDao.findById(gUserInfo.getUserId()).orElse(new GUserInfo());
        gUserInfo.setUserPwd(model.getUserPwd());
        gUserInfoDao.dynamicSave(gUserInfo, model   );
        //修改供应商档案表
        if (!ObjectUtil.isNull(supply)) {
            supplyDao.dynamicSave(supply, supplyDao.findById(gUserInfo.getUserId()).orElse(null));
        } else {
            throw new RuntimeException("非法数据,未找到企业信息");
        }
        //完善信息自动提交审批
        if ("complete".equals(vo.getSelectType())) {
            supplyDao.updateStatus("确认",userid);
            Examine examine = new Examine();
            examine.setTitle("供应商管理");
            examine.setMenuId(GabUserTypeEnum.供应厂商.getRegisterApprovalMenuId());
            examine.setUrl(GabUserTypeEnum.供应厂商.getRegisterApprovalUrl());
            examine.setBusinessId(userid);
           processService.startFinishFirstStep(examine);
        }
        return Result.resultOkMsg("操作成功");
    }


    @Override
    public void deleteBySupplyNote(String supplyNote) {
        //删除用户表 的记录
        gUserInfoDao.deleteById(supplyNote);
        //删除个人表中的记录
        supplyDao.deleteBySupplyNoteTrue(supplyNote);

    }


}
