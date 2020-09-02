package com.tengzhi.business.mesPersonnel.checkWorker.overtime.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.business.mesPersonnel.checkWorker.overtime.dao.OvertimeDao;
import com.tengzhi.business.mesPersonnel.checkWorker.overtime.service.OvertimeService;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.model.CheckWorkAttendance;
import com.tengzhi.business.system.core.service.SysGenNoteService;

@Service
@Transactional
public class OvertimeServiceImpl implements OvertimeService{
	@Autowired
	private OvertimeDao overtimeDao;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;
	

	@Override
	public BasePage<CheckWorkAttendance> findAll(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		return  overtimeDao.QueryPageList(baseDto, Specifications.where((c)->{
			c.startingWith("qjRq", map.get("qjRq"));
			c.startingWith("workerId", map.get("workerId"));
			c.startingWith("qjDtype", "加班");
			c.startingWith("qjKrq", map.get("qjKrq"));
			c.startingWith("qjDrq", map.get("qjDrq"));
			c.startingWith("qjNote", map.get("qjNote"));
			
			
			
		}));
		
	}

	@Override
	public CheckWorkAttendance save(CheckWorkAttendance checkWorkAttendance) throws Exception {
		checkWorkAttendance.setJbqjId(UUIDUtil.uuid());
		SessionUser securityUser=SessionUser.SessionUser();
		if(judgeUnique(checkWorkAttendance)) {
			checkWorkAttendance.setDataDate(new Date());
			checkWorkAttendance.setQjFlag("登记");
			checkWorkAttendance.setQjDtype("加班");
			checkWorkAttendance.setDataCorp(securityUser.getCorpId());
			checkWorkAttendance.setQjRq(new Date());
			checkWorkAttendance.setDataMan(securityUser.getUsername());
			checkWorkAttendance.setQjNote((sysGenNoteService.getNote(CheckWorkAttendance.class, "JB", "yyMMdd", 4)));
			return overtimeDao.save(checkWorkAttendance);
		}else {
			throw new Exception("请假编号已存在");
		}
	}

	@Override
	public boolean judgeUnique(CheckWorkAttendance checkWorkAttendance) {
		return overtimeDao.findAll(Specifications.where((c)->{
			c.eq("jbqjId", checkWorkAttendance.getJbqjId());
		})).size()<=0;
	}

	@Override
	public CheckWorkAttendance findByqjId(String jbqjId) {
		
		return overtimeDao.findByJbqjId(jbqjId);
	}

	@Override
	public void update(CheckWorkAttendance checkWorkAttendance) {
		checkWorkAttendance.setDataDate(new Date());
		
		
		overtimeDao.dynamicSave(checkWorkAttendance, overtimeDao.findByJbqjId(checkWorkAttendance.getJbqjId()));
	}

	@Override
	public void deleteByqjId(String jbqjId) {
		
		overtimeDao.deleteById(jbqjId);
		
	}

	@Override
	public List<Map<Object,String>> parameType() {
		return overtimeDao.parameType();
	}


}
