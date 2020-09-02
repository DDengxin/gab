package com.tengzhi.business.platform.manage.service.impl;

import cn.hutool.core.lang.UUID;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.form.dao.DynamicFormDetailSqlDao;
import com.tengzhi.business.platform.manage.dao.ServiceManageDao;
import com.tengzhi.business.platform.manage.dao.ServiceManageSqlDao;
import com.tengzhi.business.platform.manage.model.G_ServiceManage;
import com.tengzhi.business.platform.manage.service.ServiceManageService;
import com.tengzhi.business.platform.manage.vo.serviceVo;
import com.tengzhi.business.system.workflow.service.ProcessService;
import com.tengzhi.business.system.workflow.vo.Examine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


@Service
@Transactional
public class ServiceManageServiceImpl implements ServiceManageService {
	
	@Autowired
	private  ServiceManageDao  serviceManageDao;
	
	@Autowired
	private  ServiceManageSqlDao serviceManageSqlDao;
	
	@Autowired
	private DynamicFormDetailSqlDao dynamicFormDetailSqlDao;
	
	@Autowired
	private ProcessService processService;
	
	@SuppressWarnings("unchecked")
	@Override
	public BasePage<Map<String, Object>> getSrch(BaseDto baseDto) throws IOException {		
		return serviceManageSqlDao.getSrch(baseDto);				
	}
	
	@Override
    public Result update(serviceVo serviceVo) {
		SessionUser user = SessionUser.SessionUser();
		G_ServiceManage g_ServiceManage = serviceVo.getG_ServiceManage();
		g_ServiceManage.setServiceFlag("登记");
		g_ServiceManage.setDataMan(user.getUserId());
		g_ServiceManage.setDataCorp(user.getCorpId());
		g_ServiceManage.setDataDate(new Date());
		serviceManageDao.update(g_ServiceManage);
		dynamicFormDetailSqlDao.updataDynamicFormDetail(g_ServiceManage.getServiceResult(), g_ServiceManage.getServiceNote(), "g_service_manage", serviceVo.getDynamic());
		return Result.resultOk(g_ServiceManage.getServiceNote());
    }
	
	@Override
	public Result getSrchAllCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findByNote(String Id,String result) {
		return serviceManageSqlDao.findByNote(Id,result);
	}

	@Override
	public void deleteByServiceNote(String Id) {
		serviceManageDao.deleteById(Id);
	}

	@Override
	public Result saveService(serviceVo serviceVo) {
		SessionUser user = SessionUser.SessionUser();
		G_ServiceManage g_ServiceManage = serviceVo.getG_ServiceManage();
		String note = UUID.randomUUID().toString(true);
		g_ServiceManage.setServiceNote(note);
		g_ServiceManage.setServiceFlag("登记");
		g_ServiceManage.setDataMan(user.getUserId());
		g_ServiceManage.setDataCorp(user.getCorpId());
		g_ServiceManage.setDataDate(new Date());
		g_ServiceManage.setApplyMan(user.getUserId());
		g_ServiceManage.setApplyDate(new Date());
		serviceManageDao.save(g_ServiceManage);
		dynamicFormDetailSqlDao.saveDynamicFormDetail(g_ServiceManage.getServiceResult(), note, "g_service_manage", serviceVo.getDynamic());
		Map<String,Object> map = serviceVo.getFlow();
		if(!map.isEmpty()) {
			Examine examine = new Examine();
			examine.setMenuId(map.get("menuId").toString());
			examine.setTitle(map.get("title").toString());
			examine.setUrl(map.get("url").toString());
			examine.setBusinessId(g_ServiceManage.getServiceNote());
			processService.startFinishFirstStep(examine);
		}
		return Result.resultOk(g_ServiceManage.getServiceNote());
	}

	@Override
	public Map<String, Object> examination(String id) {
		return serviceManageSqlDao.examination(id);
	}
	
}
