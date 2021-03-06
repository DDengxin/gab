package com.tengzhi.business.platform.enroll.service.impl;

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
import com.tengzhi.business.platform.enroll.constant.GabUserTypeEnum;
import com.tengzhi.business.platform.enroll.dao.ExpertDao;
import com.tengzhi.business.platform.enroll.dao.GUserInfoDao;
import com.tengzhi.business.platform.enroll.dao.PersonalDao;
import com.tengzhi.business.platform.enroll.model.GUserInfo;
import com.tengzhi.business.platform.enroll.model.G_personal;
import com.tengzhi.business.platform.enroll.service.PersonalService;
import com.tengzhi.business.platform.enroll.service.RegisterService;
import com.tengzhi.business.platform.enroll.vo.modelVo;
import com.tengzhi.business.sale.saleArchives.customerArchives.service.CustomerArchivesService;
import com.tengzhi.business.system.sms.constant.SmsType;
import com.tengzhi.business.system.sms.model.SysSmsLog;
import com.tengzhi.business.system.sms.service.SysSmsService;
import com.tengzhi.business.system.user.dao.GabSysUserDao;
import com.tengzhi.business.system.user.model.SysUser;
import com.tengzhi.business.system.user.service.SysUserService;
import com.tengzhi.business.system.workflow.service.ProcessService;
import com.tengzhi.business.system.workflow.vo.Examine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private ExpertDao expertDao;
	@Autowired
	private SysUserService sysuserService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private GabSysUserDao gabSysUserDao;
	@Autowired
	SysSmsService sysSmsService;
	@Autowired
	private PersonalDao personalDao;
    @Autowired
    private  SysCustomerDao sysCustomerDao  ;
	@Autowired
	private GUserInfoDao gUserInfoDao;

	@Override
	public Result personalone(String id) {
		Result rs = new Result();
		String sql = "		 SELECT * from  g_personal  where  person_note = '" + id + "'";
		String sql1 = "SELECT  user_id, nick_name,mobile, email   from sys_user where supply_id = '" + id + "'";
		rs.put("data", expertDao.QueryhumpMap(sql).get(0));
		rs.put("user", expertDao.QueryhumpMap(sql1).get(0));
		return rs;
	}

	@Autowired
	CustomerArchivesService customerArchivesService;

	@Override
	public Result agree(Examine examine) throws Exception {
		// 审批流
		processService.agree(examine);
		// 给对应的供应商进行授权
		String note = examine.getBusinessId();
		String status = personalDao.getPersonalStatus(note);
		String  extension =   examine.getExtension();
		Map<String,Object> mapExtension= (Map<String, Object>) JSONObject.parse(extension);
		GUserInfo gUserInfo = gUserInfoDao.findById(note).orElse(new GUserInfo());
		List<GUserInfo>  listGUserInfo= gUserInfoDao.findAll(Specifications.where((c) -> c.eq("erpUserid", String.valueOf(mapExtension.get("erpUserid")))));
		boolean  notExistsUserFlag =    listGUserInfo.size()>0;
		if(notExistsUserFlag){
			return  Result.error("该用户已经被绑定，请重新选择！");
		}


		if ("核准".equals(status)) {
			// 获取用户id
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("roleId", GabUserTypeEnum.需求用户.getInitRole());
			list.add(map2);
			map.put("userId", String.valueOf(mapExtension.get("erpUserid")) );
			map.put("roleMap", list);
			sysuserService.saveUserAuth(map);
			//修改G_userinfo表中的信息是否完善字段为核准
			//gUserInfoDao.updateFlag(note,"核准");
			//更新g_userinfo 表的erp_userid erp_corpid
			gUserInfo.setErpCorp(String.valueOf(mapExtension.get("erpCorp")));
			gUserInfo.setErpUserid(String.valueOf(mapExtension.get("erpUserid")));
			gUserInfoDao.dynamicSave(gUserInfo, gUserInfoDao.findById(gUserInfo.getUserId()).orElse(null));
			gUserInfoDao.flush();
			// 发送短信给对应供应商
			SysUser   user  = new SysUser();
			user.setUserId(gUserInfo.getUserId());
			user.setMobile(gUserInfo.getUserMtel());
			user.setOrgId(gUserInfo.getDataCorp());

			SysSmsLog sysSmsLog = sysSmsService.send(SysSmsLog.GenSms(user, SmsType.GREANT, "已审核通过"));
			if (sysSmsLog.getSuccess()) {


			} else {
				throw new RuntimeException("审核通过，短信发送失败");
			}

		}

		return Result.resultOk();
	}

	@Override
	public modelVo personById( String  personId) throws Exception {
		modelVo entry = new modelVo();
		entry.setgUserInfo(gUserInfoDao.findById(personId).orElse(new GUserInfo()));
		entry.setPersonal(personalDao.findById(personId).orElse(new G_personal()));

		return entry;
	}

	@Override
	public Result update(modelVo vo) {
		 SessionUser sessionUser = SessionUser.SessionUser();
         SysUser session_user = sessionUser.getSysUser();
		// 修改用户基础信息
		GUserInfo  gUserInfo = vo.getgUserInfo();
		gUserInfoDao.dynamicSave(gUserInfo, gUserInfoDao.findById(gUserInfo.getUserId()).orElse(null) );

		// 修改客户基础信息
		G_personal  person = 	vo.getPersonal();
		if (vo.getPersonal() != null) {
			personalDao.dynamicSave(person, personalDao.findById(gUserInfo.getUserId()).orElse(null) );
		}


		//完善信息自动提交审批
		if(StringUtils.isNotEmpty(vo.getSelectType()) && "complete".equals(vo.getSelectType())){
			personalDao.updateStatus(gUserInfo.getUserId(),"确认");
			Examine  examine = new   Examine();
			examine.setTitle("客戶管理");
			examine.setMenuId(GabUserTypeEnum.需求用户.getRegisterApprovalMenuId());
			examine.setUrl(GabUserTypeEnum.需求用户.getRegisterApprovalUrl());
			examine.setBusinessId(gUserInfo.getUserId());
			processService.startFinishFirstStep(examine);
			//状态修改为确认
			//gUserInfoDao.updateFlag(gUserInfo.getUserId(),"确认");

		}
     return  Result.resultOkMsg("修改成功");

	}

    @Override
    public BasePage<Map<String, Object>> getList(BaseDto dto) {
       // Map<String, String> map = dto.getParamsMap();
        String sql = " select  *,(SELECT  is_platform  from  g_userinfo  where  user_id = g_personal.person_note  limit 1   ) platformUser from g_personal ";
    	Map<String, String> map = dto.getParamsMap();
		String where = SqlJoint.where(e -> {
			e.ge("register_date", map.get("srchRq1"));
			e.le("register_date", map.get("srchRq2"));
			e.contains("person_name", map.get("personName"));
		
		}, true);
        return personalDao.QueryToMapPage(dto,sql + where);
    }

	@SuppressWarnings("static-access")
	@Override
	public Result save(modelVo modelVo) throws Exception {
	        if (modelVo.getgUserInfo() != null) {
				GUserInfo  gUserInfo = modelVo.getgUserInfo();
                G_personal person = modelVo.getPersonal();
				Property pro = SpringUtil.getBean(Property.class);
	                // 验证的关键字段不能重复
	                if (judgeUnique(gUserInfo)) {
	                    // 用户基础信息表
						gUserInfo.setFlag("登记");
						gUserInfo.setDataDate(new Date());
						gUserInfo.setUserType(GabUserTypeEnum.专家团队.getName());
						gUserInfo.setDataCorp(pro.getBusiness().getDefaultCorp());
						gUserInfo.setUserPwd(MD5Util.encode(gUserInfo.getUserPwd()));
						gUserInfo.setPlatform(true);
	                   if (modelVo.getPersonal() != null) {
	                	   //客户扩展表
	                        person.setRegisterDate(new Date());
	                        person.setPersonNote(gUserInfo.getUserId() );
	                        person.setPersonName(gUserInfo.getUserName());
	                        person.setStatus("登记");
	                        person.setIsPlatform(true);

	                    }
                       //数据验证
						gUserInfo.Validate();
                        personalDao.save(person);
						gUserInfoDao.save(gUserInfo);
						//添加初始权限
	                    /*Map<String, Object> map = new HashMap<String, Object>();
	        			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        			Map<String, Object> map2 = new HashMap<String, Object>();
	        			map2.put("roleId", GabUserTypeEnum.需求用户.getInitRole());
	        			list.add(map2);
	        			map.put("userId", gUserInfo.getUserId());
	        			map.put("roleMap", list);
	        			sysuserService.saveUserAuth(map);*/

						return Result.resultOk("保存成功");

					} else {
						return Result.resultError(300, "该客户的用户账号或用户名称,或手机号码已存在");
					}
	           

	        } else {
				return Result.resultError(300, "新增失败");
			}

	}
	
	
	   public boolean judgeUnique(GUserInfo gUserInfo) {
	            // 需求方客户
	            return gUserInfoDao.findAll(Specifications.where(false, (c) -> {
	                c.eq("userId", gUserInfo.getUserId());
	                c.eq("userName", gUserInfo.getUserName());
					c.eq("userMtel", gUserInfo.getUserMtel());
	            })).size() <= 0;

	    }

	@Override
	public void deleteByPersonNote(String note) {
		//删除用户表 的记录
		gUserInfoDao.deleteById(note);
		//删除个人表中的记录
		personalDao.deleteById(note);


		
	}

	@Override
	public Result getPersonalStatus(String expertNote, String status) {
		String  flag =	personalDao.getPersonalStatus(expertNote);
		if (status.equals(flag)) {
			return Result.resultOk("操作成功！");
		}
		return Result.error("该单不是“" + status + "”状态,或该数据非平台用户添加,不能操作！");
		
	}

	@Override
	public Result getPersonalStatus(String expertNote) {

         return  Result.resultOk(personalDao.getPersonalStatus(expertNote));

	}
}
