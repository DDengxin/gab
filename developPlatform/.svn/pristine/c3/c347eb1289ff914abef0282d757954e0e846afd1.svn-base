package com.tengzhi.business.mesPersonnel.checkWorker.absence.service.impl;

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
import com.tengzhi.business.mesPersonnel.checkWorker.absence.dao.AbsenceDao;
import com.tengzhi.business.mesPersonnel.checkWorker.absence.service.AbsenceService;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.model.CheckWorkAttendance;
import com.tengzhi.business.system.core.service.SysGenNoteService;
@Service
@Transactional
public class AbsenceServiceImpl implements AbsenceService{
	@Autowired
	private AbsenceDao absenceDao;
	@Autowired
	private SysGenNoteService sysGenNoteService;
	
	

	@Override
	public BasePage<CheckWorkAttendance> findAll(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		return absenceDao.QueryPageList(baseDto, Specifications.where((c) -> {
			c.startingWith("qjRq", map.get("qjRq"));
			c.startingWith("workerId", map.get("workerId"));
			c.startingWith("qjDtype","请假");
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
			checkWorkAttendance.setQjDtype("请假");
			checkWorkAttendance.setDataCorp(securityUser.getCorpId());
			checkWorkAttendance.setQjRq(new Date());
			checkWorkAttendance.setDataMan(securityUser.getUsername());
			checkWorkAttendance.setQjNote((sysGenNoteService.getNote(CheckWorkAttendance.class, "QJ", "yyMMdd", 4)));
			return absenceDao.save(checkWorkAttendance);
		}else {
			throw new Exception("请假编号已存在");
		}
	}

	@Override
	public boolean judgeUnique(CheckWorkAttendance checkWorkAttendance) {
		return absenceDao.findAll(Specifications.where((c)->{
			c.eq("jbqjId", checkWorkAttendance.getJbqjId());
		})).size()<=0;
	}


	@Override
	public void update(CheckWorkAttendance checkWorkAttendance) {
		checkWorkAttendance.setDataDate(new Date());
		
		
		absenceDao.dynamicSave(checkWorkAttendance, absenceDao.findByJbqjId(checkWorkAttendance.getJbqjId()));
	}

	@Override
	public void deleteByqjId(String jbqjId) {
		
		absenceDao.deleteById(jbqjId);
		
	}


	@Override
	public CheckWorkAttendance findByJbqjId(String jbqjId) {
		
		return absenceDao.findByJbqjId(jbqjId);
	}


	@Override
	public List<Map<Object, String>> parameType() {
		
			return absenceDao.parameType();
		
	}

}
