package com.tengzhi.business.xt.routine.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.user.model.SysUser;
import com.tengzhi.business.xt.routine.dao.RoutineDao;
import com.tengzhi.business.xt.routine.dao.RoutineDetailedDao;
import com.tengzhi.business.xt.routine.model.EXtSjxg;
import com.tengzhi.business.xt.routine.model.EXtSjxgDetailed;
import com.tengzhi.business.xt.routine.service.RoutineService;
import com.tengzhi.business.xt.routine.vo.EXtSjxgVo;

@Service
@Transactional
public class RoutineServiceImpl implements RoutineService {

    @Autowired
    RoutineDao Routinedao;
    
    @Autowired
    RoutineDetailedDao routineDaoDetailedDao;

	@Override
	public BasePage<Map<String,Object>> getSrchTopList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser sessionUser=SessionUser.SessionUser();
        String where = " where 1=1 ";
        if(StringUtils.isNotBlank(map.get("srchRq1"))) {
        	where += " AND sq_rq >= '" +map.get("srchRq1")+"'";
        }
        if(StringUtils.isNotBlank(map.get("srchRq2"))) {
        	where += " AND sq_rq <= '" +map.get("srchRq2")+"'";
        }
        if(StringUtils.isNotBlank(map.get("srchType"))) {
        	where += " AND sq_type = '" +map.get("srchType")+"'";
        }
        if(StringUtils.isNotBlank(map.get("workerDept"))) {
        	where += " AND sq_dept = '" +map.get("workerDept")+"'";
        }
        if(StringUtils.isNotBlank(map.get("srchMan"))) {
        	where += " AND sq_man = '" +map.get("srchMan")+"'";
        }
        if(StringUtils.isNotBlank(map.get("srchNote"))) {
        	where += " AND sq_note like '%" +map.get("srchNote")+"%'";
        }
        if(StringUtils.isNotBlank(map.get("srchFlag"))) {
        	where += " AND sq_flag = '" +map.get("srchFlag")+"'";
        }
        String sql = "select sq_rq \"sqRq\",sq_note \"sqNote\",sq_dept \"sqDept\",f_getname('GETDEPTNAME', sq_dept, '', '"+sessionUser.getCorpId()+"') \"deptName\",sq_man,f_getname('GETUSERNAME', sq_man, '', '"+sessionUser.getCorpId()+"') \"manName\",sq_type \"sqType\",f_get_param_name('数据修改',sq_type,'"+   SessionUser.getCurrentCorpId()   +"','财务',false) \"typeName\",sq_sy \"sqSy\",yw_note \"ywNote\",sq_flag \"sqFlag\" from e_xt_sjxg "+where+" order by sq_rq desc ";
        baseDto.setSortOrder("DESC");
        baseDto.setSortField("sqRq");
        return Routinedao.QueryPageList(sql,"select count(1) from ("+sql+") a",baseDto);
	}

	@Override
	public Result save(EXtSjxgVo vo) throws Exception {
		SessionUser sessionUser=SessionUser.SessionUser();
		vo.getHeaddata().setSqFlag("登记");
		vo.getHeaddata().setSqMan(sessionUser.getUserId());
		vo.getHeaddata().setSqDept(sessionUser.getDeptId());
		Routinedao.save(vo.getHeaddata());
		for(int i = 0;i<vo.getEntitydata().size();i++) {
			vo.getEntitydata().get(i).setSqNote(vo.getHeaddata().getSqNote());
			vo.getEntitydata().get(i).setSqNoteMo(vo.getHeaddata().getYwNote());
			routineDaoDetailedDao.save(vo.getEntitydata().get(i));
		}
		return Result.resultOk(vo.getHeaddata().getSqNote());
	}

	@Override
	public BasePage<EXtSjxgDetailed> getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlString="select sq_note,sq_note_mo,sq_code,sq_type,sq_xm,sq_yc,sq_value,sq_sm,sq_flag from e_xt_sjxg_mx where sq_note = '"+map.get("sqNote")+"'";
		return routineDaoDetailedDao.QueryPageLists(baseDto,sqlString+" order by sq_code ");
	}

	@Override
	public EXtSjxg getByNote(String sqNote) {
	     return Routinedao.findBySqnote(sqNote);
	}
    
	@Override
	public Result update(EXtSjxgVo vo) throws Exception {
		SessionUser sessionUser=SessionUser.SessionUser();
		vo.getHeaddata().setSqFlag("登记");
		vo.getHeaddata().setSqMan(sessionUser.getUserId());
		vo.getHeaddata().setSqDept(sessionUser.getDeptId());
		Routinedao.update(vo.getHeaddata());
		for(int i = 0;i<vo.getEntitydata().size();i++) {
			vo.getEntitydata().get(i).setSqNote(vo.getHeaddata().getSqNote());
			vo.getEntitydata().get(i).setSqNoteMo(vo.getHeaddata().getYwNote());
			routineDaoDetailedDao.update(vo.getEntitydata().get(i));
		}
		return Result.resultOk(vo.getHeaddata().getSqNote());
	}

	@Override
	public int delete(String sqNote) {
		int re = Routinedao.deleteBysqNote(sqNote);
		re = routineDaoDetailedDao.deleteBysqNote(sqNote);
		return re;
	}
	
	@Override
	public List<Map> getlList(String mod, String type,String parent) {
		String sqlString="select param_id combid,param_name combtext,parent_id pid from  sys_param  where param_mod='"+mod+"' and param_type='"+type+"' and param_status='true' and parent_id='"+parent+"' ";
		return routineDaoDetailedDao.QueryListMap(sqlString);
	}
	
}
