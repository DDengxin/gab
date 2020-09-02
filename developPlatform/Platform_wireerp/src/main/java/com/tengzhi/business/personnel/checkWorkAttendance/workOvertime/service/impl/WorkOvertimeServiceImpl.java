package com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SessionUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.dao.WorkOvertimeDao;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.model.CheckWorkAttendance;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.service.WorkOvertimeService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
@Service
@Transactional
public class WorkOvertimeServiceImpl implements WorkOvertimeService{
	@Autowired
	WorkOvertimeDao workOvertimeDao;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;

	@Override
	public BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws IOException, ParseException {
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
//		String sql ="select qj_note,qj_rq,worker_id,qj_dtype,qj_ztype,qj_xtype,qj_sy"
//				+ ",qj_krq,qj_drq,qj_sc,qj_kq,qj_flag,qj_month,data_man,data_date,"
//				+ "data_corp,f_getname('GETUSERNAME',worker_id,'',data_corp) worker_name,jbqj_id from e_hr_jbqj "
//				+ "where qj_dtype='加班'" +sqlWhere;
		String sql ="select a.qj_note,to_char(a.qj_rq,'yyyy-MM-dd')qj_rq ,a.worker_id,a.qj_dtype,a.qj_ztype,a.qj_xtype,a.qj_sy"
		+ ",a.qj_krq,a.qj_drq,a.qj_sc,a.qj_kq,a.qj_flag,a.qj_month,a.data_man,f_getname('GETUSERNAME',a.data_man,'',a.data_corp) data_man_name, to_char(a.data_date,'yyyy-MM-dd') data_date,"
		+ "a.data_corp,b.worker_name,a.jbqj_id,f_getname('GETDWNAME',b.worker_dept,'',a.data_corp) worker_dept_name  from e_hr_jbqj a,e_hr_worker b "
		+ "where a.worker_id=b.worker_id and a.qj_dtype='加班'" +sqlWhere;
//		return  workOvertimeDao.QueryPageList(baseDto, Specifications.where((c)->{
//			c.startingWith("qjRq", map.get("qjRq"));
//			c.startingWith("workerId", map.get("workerId"));
//			c.startingWith("qjDtype", "加班");
//			c.startingWith("qjKrq", map.get("qjKrq"));
//			c.startingWith("qjDrq", map.get("qjDrq"));
//			c.startingWith("qjNote", map.get("qjNote"));
//		}));
		//return workOvertimeDao.QueryPageLists(baseDto, sql);
		return workOvertimeDao.QueryMapPageList(baseDto,sql, true);
		
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
			return workOvertimeDao.save(checkWorkAttendance);
		}else {
			throw new Exception("请假编号已存在");
		}
	}

	@Override
	public boolean judgeUnique(CheckWorkAttendance checkWorkAttendance) {
		return workOvertimeDao.findAll(Specifications.where((c)->{
			c.eq("jbqjId", checkWorkAttendance.getJbqjId());
		})).size()<=0;
	}

	@Override
	public CheckWorkAttendance findByqjId(String jbqjId) {
		CheckWorkAttendance checkWorkAttendance = workOvertimeDao.findByJbqjId(jbqjId);
		checkWorkAttendance.setWorkerName(workOvertimeDao.findworkerName(checkWorkAttendance.getWorkerId(),checkWorkAttendance.getDataCorp()));
		
		return checkWorkAttendance;
	}

	@Override
	public void update(CheckWorkAttendance checkWorkAttendance) {
		checkWorkAttendance.setDataDate(new Date());
		
		
		workOvertimeDao.dynamicSave(checkWorkAttendance, workOvertimeDao.findByJbqjId(checkWorkAttendance.getJbqjId()));
	}

	@Override
	public void deleteByqjId(String jbqjId) {
		
		workOvertimeDao.deleteById(jbqjId);
		
	}

	@Override
	public List<Map<Object,String>> parameType() {
		return workOvertimeDao.parameType();
	}


	@Override
	public CheckWorkAttendance findByJbNote(String note) {
		CheckWorkAttendance checkWorkAttendance = workOvertimeDao.findByQjNote(note);
		checkWorkAttendance.setWorkerName(workOvertimeDao.findworkerName(checkWorkAttendance.getWorkerId(),checkWorkAttendance.getDataCorp()));
		
		return checkWorkAttendance;
	}

}
