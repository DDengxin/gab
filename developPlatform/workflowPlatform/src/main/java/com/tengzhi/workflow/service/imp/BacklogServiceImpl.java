package com.tengzhi.workflow.service.imp;

import java.io.IOException;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.workflow.dao.BacklogDao;
import com.tengzhi.workflow.dao.OpinionDao;
import com.tengzhi.workflow.model.Backlog;
import com.tengzhi.workflow.model.Opinion;
import com.tengzhi.workflow.service.BacklogService;
import com.tengzhi.workflow.service.factory.activitiFactory;

@Service
@Transactional
public class BacklogServiceImpl extends activitiFactory implements BacklogService {
	@Autowired
	private BacklogDao dao;

	@Autowired
	private OpinionDao Opiniondao;

	@Override
	public BasePage<Backlog> getBacklog(BaseDto baseDto) throws IOException {
		SessionUser securityUser=SessionUser.SessionUser();
		Map<String, String> map = baseDto.getParamsMap();
		String where = "";
		if (StringUtils.isNotBlank(map.get("title"))) {
			where = " and title like '%" + map.get("title") + "%' ";
		}
		String sql = "select * from (select distinct EXE.proc_def_id_,RES.id_ as task_id,RES.name_ ,I.user_id_,bac.business_id,bac.process_instance_id,bac.title,bac.handle_url,bac.\"module\",bac.transactor,bac.transactor_id,bac.\"time\" from ACT_RU_TASK RES left join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_  "
				+ "left join ACT_RU_EXECUTION EXE on EXE.id_=RES.proc_inst_id_  left join act_tz_backlog bac  on EXE.business_key_=bac.business_id "
				+ "WHERE ( RES.assignee_='" + securityUser.getJobNumber() + "' or  I.USER_ID_ = '" + securityUser.getJobNumber()
				+ "') " + where + ") q";
		String count = "select count(1) from (select distinct RES.id_ as task_id,RES.name_ ,I.user_id_,bac.business_id,bac.title,bac.\"module\",bac.transactor,bac.transactor_id,bac.\"time\" from ACT_RU_TASK RES left join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_  "
				+ "left join ACT_RU_EXECUTION EXE on EXE.id_=RES.proc_inst_id_  left join act_tz_backlog bac  on EXE.business_key_=bac.business_id "
				+ "WHERE ( RES.assignee_='" + securityUser.getJobNumber() + "' or  I.USER_ID_ = '" + securityUser.getJobNumber()
				+ "') " + where + ") q";
		return dao.QueryPageLists(sql, count, baseDto);
	}

	@Override
	public BasePage<Opinion> gethistoryTask(BaseDto baseDto) throws IOException {
		String sql = "select RES.act_id_,RES.act_name_,RES.act_type_,(select nick_name from sys_user q where q.job_number=RES.assignee_) as assignee_,RES.start_time_,RES.end_time_,op.* from ACT_HI_ACTINST RES  "
				+ "left join act_tz_opinion op  on RES.task_id_ =op.task_id  WHERE RES.PROC_INST_ID_ = '"
				+ baseDto.getParams() + "' order by RES.start_time_";
		String count = "select count(1) from ACT_HI_ACTINST RES  " + "left join act_tz_opinion op  on RES.task_id_ =op.task_id  and op.process_instance_id='" + baseDto.getParams()
				+ "'  " + "WHERE RES.PROC_INST_ID_ = '" + baseDto.getParams() + "'";
		return Opiniondao.QueryPageLists(sql, count, baseDto);
	}

}
