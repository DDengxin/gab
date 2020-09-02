package com.tengzhi.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.base.jpa.extension.SqlJoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.impl.BaseServiceImpl;
import com.tengzhi.dao.LogDao;
import com.tengzhi.model.Log;
import com.tengzhi.service.Logservice;

@Service
@Transactional
public class LogserviceImpl extends BaseServiceImpl<Log,String> implements Logservice {
	@Autowired
	private LogDao logDao;

	@Override
	public BasePage<Log> findAll(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		return logDao.QueryPageList(baseDto, Specifications.where((c) -> {
			c.like("username", map.get("username"));
			c.like("requestIp", map.get("requestIp"));
			c.eq("logType", map.get("logType"));
			c.contains("description", map.get("description"));
			c.contains("exceptionDetail", map.get("exceptionDetail"));
		}));
	}

	@Override
	public Log findByRightId(String rightId) {
		return null;
	}

	@Override
	public void save(Log log) {
		logDao.store(log);
	}

	@Override
	public void update(Log log) {
		logDao.update(log);
	}

	@Override
	public void deleteByRightId(String rightId) {

	}

	@Override
	public BasePage<Map<String, Object>> getArchiveLogList(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		StringBuffer sqlWhere = SqlJoint.where(c -> {
			c.like("username", map.get("username"));
			c.like("request_ip", map.get("requestIp"));
			c.eq("log_type", map.get("logType"));
			c.contains("description", map.get("description"));
			c.contains("exception_detail", map.get("exceptionDetail"));
		});
		baseDto.setSortField("create_time");
		baseDto.setSortOrder("DESC");
		String sql = "select a.*,b.ar_title,f_getname('GETARTYPENAME', b.ar_secrecy, '', '') ar_secrecy_name,b.ar_note " +
				"from sys_log a,e_xt_archive b " +
				"where position(b.ar_uuid in a.params) > 0 and "+sqlWhere;
		return logDao.QueryMapPageList(baseDto,sql+" order by 1 desc ",true);
	}

}
