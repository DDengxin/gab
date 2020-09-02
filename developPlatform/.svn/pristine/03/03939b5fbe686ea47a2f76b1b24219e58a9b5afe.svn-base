package com.tengzhi.business.system.workflow.dao.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.security.common.model.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.workflow.dao.BacklogsDao;
import com.tengzhi.business.system.workflow.dao.DescribDao;
import com.tengzhi.business.system.workflow.dao.DescribeDao;
import com.tengzhi.business.system.workflow.dao.MatterDao;
import com.tengzhi.business.system.workflow.model.Backlog;
import com.tengzhi.business.system.workflow.model.Matter;
import com.tengzhi.business.system.workflow.vo.Describe;
import com.tengzhi.business.system.workflow.vo.Edges;
import com.tengzhi.business.system.workflow.vo.Nodes;

@Repository
public class DescribDaoimpl extends BasicsDaoImpl<Describe, String> implements DescribDao {
	@Autowired
	private BacklogsDao backlogDao;
	@Autowired
	private MatterDao dao;
	@Autowired
	private DescribeDao Describedao;

	@Override
	public List<Describe> queryDescribe(String processId) {
		String sql = "SELECT * FROM \"sys_workflow_describe\" where  id=?1";
		@SuppressWarnings("unchecked")
		List<Describe> list = super.query(sql, Describe.class, processId);
		return list;
	}

	@Override
	public List<Edges> queryEdges(String processId) {
		String sql = "SELECT * FROM \"sys_workflow_line\" where  process_id=?1";
		@SuppressWarnings("unchecked")
		List<Edges> list = super.query(sql, Edges.class, processId);
		return list;
	}

	@Override
	public List<Nodes> queryNodes(String processId) {
		String sql = "SELECT * FROM \"sys_workflow_link\" where  process_id=?1";
		@SuppressWarnings("unchecked")
		List<Nodes> list = super.query(sql, Nodes.class, processId);
		return list;
	}

	@Override
	public void update(String table, String note, String noteValue, String flag, String flagValue) {
		String sql = "update " + table + " set " + flag + "=?1 where " + note + "=?2";
		super.executeUpdateBysql(sql, flagValue, noteValue);
	}

	@Override
	public void delete(String id) {
		String sql = "delete FROM sys_workflow_describe where id='" + id + "'";
		super.executeUpdateBysql(sql);
		String sql1 = "delete FROM  sys_workflow_line where process_id='" + id + "'";
		super.executeUpdateBysql(sql1);
		String sql2 = "delete FROM sys_workflow_link where process_id='" + id + "'";
		super.executeUpdateBysql(sql2);
		
	}

	@Override
	public Map<String, Object> findall(String table, String note, String noteValue) {
		String sql = "select * from " + table + " where " + note + "='" + noteValue + "' limit 1";
		List<Map<String, Object>> list = super.queryToMap(sql);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}

	}

	@Override
	public Boolean judge(String sql) {
		List<Map<String, Object>> list = super.queryToMap("select (" + sql + ") t");
		return (boolean) list.get(0).get("t");
	}

	@Override
	public String getStr(String sql) {
		List<Map<String, Object>> list = super.queryToMap("select (" + sql + ") t");
		return String.valueOf(list.get(0).get("t"));
	}

	@Override
	public String getversion(String id) {
		List<Map<String, Object>> list = super.queryToMap(
				"select cast(version as int2 )+1 as verson from sys_workflow_describe where version is not null  and process_classify_id='"
						+ id + "' ORDER BY \"version\" desc LIMIT 1");
		int i = list.size() == 0 ? 0 : (int) list.get(0).get("verson");
		if (i == 0) {
			i = 1;
		}
		return String.valueOf(i);
	}

	@Override
	public void delallBacklogs(String BusinessId) {
		String sql = "delete FROM sys_workflow_backlog where business_id='" + BusinessId + "'";
		super.executeUpdateBysql(sql);
	}

	@Override
	public BasePage<Map<String,Object>> getBacklogs(BaseDto baseDto) {
		SecurityUser securityUser= SessionUser.SessionUser();
		Map<String, String> map = baseDto.getParamsMap();
		String sqlwhere = "where  transactor_id='" + securityUser.getUserId() + "'";
		if (StringUtils.isNotEmpty(map.get("founder"))) {
			sqlwhere += " and founder like '%" + map.get("founder") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("title"))) {
			sqlwhere += " and title like '%" + map.get("title") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("processClassify"))) {
			sqlwhere += " and process_classify like '%" + map.get("processClassify") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("processModule"))) {
			sqlwhere += " and process_module like '%" + map.get("processModule") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("processSystemType"))) {
			sqlwhere += " and process_system_type like '%" + map.get("processSystemType") + "%'";
		}
		String sql = "select back.*,descri.process_classify,descri.process_classify_id,descri.process_module,descri.process_system_type from sys_workflow_backlog  back left join sys_workflow_describe descri on back.process_id=descri.\"id\" "
				+ sqlwhere;
		return backlogDao.QueryMapPageList(baseDto,sql,true);
	}

	@Override
	public BasePage<Map<String,Object>> getMatter(BaseDto baseDto) {
		SecurityUser securityUser= SessionUser.SessionUser();
		Map<String, String> map = baseDto.getParamsMap();
		String sqlwhere = "where  founder_id='" + securityUser.getUserId() + "'";
		if (StringUtils.isNotEmpty(map.get("transactor"))) {
			sqlwhere += " and transactor like '%" + map.get("transactor") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("title"))) {
			sqlwhere += " and title like '%" + map.get("title") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("processClassify"))) {
			sqlwhere += " and process_classify like '%" + map.get("processClassify") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("processModule"))) {
			sqlwhere += " and process_module like '%" + map.get("processModule") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("processSystemType"))) {
			sqlwhere += " and process_system_type like '%" + map.get("processSystemType") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("processType"))) {
			sqlwhere += " and process_type = '" + map.get("processType") + "'";
		}

		String sql = "select appl.*,descri.process_classify,descri.process_classify_id,descri.process_module,descri.process_system_type from sys_workflow_applyfor  appl left join sys_workflow_describe descri on appl.process_id=descri.\"id\""
				+ sqlwhere;
		return dao.QueryMapPageList(baseDto,sql,true);
	}

	@Override
	public List<Map> getProcessClassify(String process_module, String process_system_type) {
		String sql = "select distinct  descri.process_classify as text,descri.process_classify_id as id from sys_workflow_backlog  back left join sys_workflow_describe descri on back.process_id=descri.\"id\""
				+ " where  process_module=?1 and  process_system_type=?2";
		return backlogDao.QueryListMap(sql, process_module, process_system_type);
	}

	@Override
	public BasePage<com.tengzhi.business.system.workflow.model.Describe> getProcess(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser sessionUser=SessionUser.SessionUser();
		String sqlwhere = " where  1=1 and data_corp='"+sessionUser.getCorpId()+"'";
		if (StringUtils.isNotEmpty(map.get("transactor"))) {
			sqlwhere += " and transactor like '%" + map.get("transactor") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("title"))) {
			sqlwhere += " and title like '%" + map.get("title") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("processClassify"))) {
			sqlwhere += " and process_classify like '%" + map.get("processClassify") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("processModule"))) {
			sqlwhere += " and process_module like '%" + map.get("processModule") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("processSystemType"))) {
			sqlwhere += " and process_system_type like '%" + map.get("processSystemType") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("processName"))) {
			sqlwhere += " and process_name like '%" + map.get("processName") + "%'";
		}
		if (StringUtils.isNotEmpty(map.get("state"))) {
			sqlwhere += " and state like '%" + map.get("state") + "%'";
		}
		
		String sql = "select *,cast((select count(1) from sys_workflow_backlog back where back.process_id =descr.id ) as integer )  as coutsl from sys_workflow_describe descr"
				+ sqlwhere;
		String countSql = "select count(*) cn from (" + sql + ")t ";
		return Describedao.QueryPageLists(sql, countSql, baseDto);
	}

	@Override
	public List<Map> getClassify(String process_module, String process_system_type) {
		String sql = "select distinct  process_classify as text,process_classify_id as id from sys_workflow_describe "
				+ " where  process_module=?1 and  process_system_type=?2";
		return backlogDao.QueryListMap(sql, process_module, process_system_type);
	}

	@Override
	public String getProcessId(String businessid) {
		String sql = "SELECT process_id FROM \"sys_workflow_backlog\" where business_id =?1 LIMIT 1";
		return super.querySingleConversion(sql, businessid);
	}

}
