package com.tengzhi.business.sc.da.rydg.service.impl;

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
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.business.sc.da.rydg.dao.RydgDao;
import com.tengzhi.business.sc.da.rydg.model.MRyYgdg;
import com.tengzhi.business.sc.da.rydg.model.MRyYgdg.MRyYgdg_PK;
import com.tengzhi.business.sc.da.rydg.service.RydgService;
import com.tengzhi.business.sc.da.rydg.vo.MRyYgdgVo;

import cn.hutool.core.util.ObjectUtil;

@Service
@Transactional
public class YgdgServiceImpl implements RydgService{
	@Autowired
	private RydgDao rydgDao;
	
	/*
	修改后的在下一个方法
	 */
	@Override
	public BasePage<MRyYgdg> getSrchList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		String sqlwhere=" where 1=1 and data_corp='"+securityUser.getCorpId()+"'";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			sqlwhere+=" and work_rq >='"+map.get("srchRq1")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			sqlwhere+=" and work_rq <='"+map.get("srchRq2")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchDept"))) {
			sqlwhere+=" and work_dept ='"+map.get("srchDept")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchId"))) {
			sqlwhere+=" and work_id like '%"+map.get("srchId")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
			sqlwhere+=" and work_name like '%"+map.get("srchName")+"%'";
		}
		/*if(ObjectUtil.isNotEmpty(map.get("srchGx"))) {
			sqlwhere+=" and work_gx like '%"+map.get("srchGx")+"%'";
		}*/ //定岗中没有加工序的地方
		if(ObjectUtil.isNotEmpty(map.get("srchCt"))) {
			sqlwhere+=" and work_ct like '%"+map.get("srchCt")+"%'";
		}
		
		//修改页面
		if(ObjectUtil.isNotEmpty(map.get("workRq"))) {
			sqlwhere+=" and work_rq ='"+map.get("workRq")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("workDept"))) {
			sqlwhere+=" and work_dept ='"+map.get("workDept")+"'";
		}
		//end
		String sql="select *,f_getname('GETDEPTNAME',work_dept,'',data_corp) deptname,f_get_param_name('生产班次',work_bb,'"+   SessionUser.getCurrentCorpId()   +"') bbname,f_get_param_name('生产车间',work_cj,'"+   SessionUser.getCurrentCorpId()   +"') cjname,f_getname('GETGXNAMES', work_gx, '', '') gxname ,f_getname('GETJTNAMES', work_ct, '', '') ctname,f_get_param_name('工资方式',work_gz,'"+   SessionUser.getCurrentCorpId()   +"') gzname  from m_ry_ygdg a "+sqlwhere;
		return rydgDao.QueryPageLists( baseDto,sql+" order by work_rq desc ");
	}

	/**
	 *@role:
	 *@Author:      huangbiao
	 *@Date:        2020/8/4 0004 9:37
	 *@Param:       [baseDto]
	 *@return:      com.tengzhi.base.jpa.page.BasePage<com.tengzhi.business.sc.da.rydg.model.MRyYgdg>
	 *@Description: 1：查询生产部对应的部门ID  2:查询生产部所有的子部门ID   3:查询所有隶属于生产部下子部门的所有人员信息
	 **/
	@Override
	public BasePage<MRyYgdg> getSrchProductionList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlwhere=" where 1=1 ";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			sqlwhere+=" and work_rq >='"+map.get("srchRq1")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			sqlwhere+=" and work_rq <='"+map.get("srchRq2")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchDept"))) {
			sqlwhere+=" and work_dept ='"+map.get("srchDept")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchId"))) {
			sqlwhere+=" and work_id like '%"+map.get("srchId")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
			sqlwhere+=" and work_name like '%"+map.get("srchName")+"%'";
		}
		/*if(ObjectUtil.isNotEmpty(map.get("srchGx"))) {
			sqlwhere+=" and work_gx like '%"+map.get("srchGx")+"%'";
		}*/ //定岗中没有加工序的地方
		if(ObjectUtil.isNotEmpty(map.get("srchCt"))) {
			sqlwhere+=" and work_ct like '%"+map.get("srchCt")+"%'";
		}

		//修改页面
		if(ObjectUtil.isNotEmpty(map.get("workRq"))) {
			sqlwhere+=" and work_rq ='"+map.get("workRq")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("workDept"))) {
			sqlwhere+=" and work_dept ='"+map.get("workDept")+"'";
		}
		//end
		String sql="select *,f_getname('GETDEPTNAME',work_dept,'',data_corp) deptname,f_get_param_name('生产班次',work_bb,'"+   SessionUser.getCurrentCorpId()   +"') bbname," +
				"f_get_param_name('生产车间',work_cj,'"+   SessionUser.getCurrentCorpId()   +"') cjname,f_getname('GETGXNAMES', work_gx, '', '') gxname ,f_getname" +
				"('GETJTNAMES', work_ct, '', '') ctname,f_get_param_name('工资方式',work_gz,'"+   SessionUser.getCurrentCorpId()   +"') gzname  from m_ry_ygdg a "+sqlwhere+
				"and work_dept in (select dept_id from sys_dept where parent_id in (select dept_id from sys_dept where dept_name = '生产部'))";
		return rydgDao.QueryPageLists( baseDto,sql+" order by work_rq desc ");
	}


	@Override
	public MRyYgdg save(MRyYgdgVo mRyYgdgVo)throws Exception {
		SessionUser securityUser=SessionUser.SessionUser();
		if(!mRyYgdgVo.getModifyedList().isEmpty()){
			mRyYgdgVo.getModifyedList().forEach( item ->{
				item.setWorkRq(mRyYgdgVo.getmRyYgdg().getWorkRq());
				item.setWorkDept(mRyYgdgVo.getmRyYgdg().getWorkDept());
				item.setDataCorp(securityUser.getCorpId());
				item.setDataMan(securityUser.getUserId());
				item.setDataDate(new Date());
				rydgDao.save(item);
			});
		}
		return  mRyYgdgVo.getmRyYgdg();
	}

	@Override
	public Result update(MRyYgdgVo mRyYgdgVo) {
		SessionUser securityUser=SessionUser.SessionUser();
		
		rydgDao.deleteAll(mRyYgdgVo.getmRyYgdg().getWorkRq(),mRyYgdgVo.getmRyYgdg().getWorkDept());
		
		if(!mRyYgdgVo.getModifyedList().isEmpty()){
			mRyYgdgVo.getModifyedList().forEach( item ->{
				item.setWorkRq(mRyYgdgVo.getmRyYgdg().getWorkRq());
				item.setWorkDept(mRyYgdgVo.getmRyYgdg().getWorkDept());
				item.setDataCorp(securityUser.getCorpId() );
				item.setDataMan(securityUser.getUserId());
				item.setDataDate(new Date());
				rydgDao.save(item);
			});
		}
	   return Result.resultOkMsg("修改成功");
	}

	@Override
	public Result saveOrUpdate(MRyYgdgVo mRyYgdgVo) {
		SessionUser securityUser=SessionUser.SessionUser();
		List<MRyYgdg> mRyYgdgList = mRyYgdgVo.getModifyedList();
		int len = mRyYgdgList.size();
		for (int i = 0; i < len; i++) {
			MRyYgdg mRyYgdg = mRyYgdgList.get(i);

			mRyYgdg.setWorkRq(mRyYgdgVo.getmRyYgdg().getWorkRq());
			mRyYgdg.setWorkDept(mRyYgdgVo.getmRyYgdg().getWorkDept());
			mRyYgdg.setDataCorp(securityUser.getCorpId() );
			mRyYgdg.setDataMan(securityUser.getUserId());
			mRyYgdg.setDataDate(new Date());

			MRyYgdg_PK mRyYgdg_pk = new MRyYgdg_PK(mRyYgdg.getWorkId(),mRyYgdg.getWorkDept());
			Optional<MRyYgdg> judgeExist = rydgDao.findById(mRyYgdg_pk);
			System.out.println("judgeExist"+judgeExist.toString());
			if (judgeExist.toString() == "Optional.empty"){
				rydgDao.save(mRyYgdg);
//				if (result!=null){
//					return Result.resultOkMsg("保存成功");
//				}else{
//					return Result.resultOkMsg("保存失败");
//				}			s
			}else{
				try{
					rydgDao.update(mRyYgdg);
				}catch (Exception e){
					e.printStackTrace();
//					return Result.resultError(new Exception("修改失败"));
				}
//				return Result.resultOkMsg("修改成功");
			}
		}
		return Result.resultOkMsg("修改成功");
	}


	@Override
	public MRyYgdg findByPkId(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String rq= map.get("rq");
		String workId= map.get("workId");
		String getWorkDept= map.get("getWorkDept");
		MRyYgdg mRyYgdg=null;
		try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(rq);
            MRyYgdg_PK pk=new MRyYgdg_PK(workId,getWorkDept);
    		 mRyYgdg=rydgDao.findById(pk).orElse(null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return mRyYgdg;
	}
	
	@Override
	public void deleteAll(String id) throws IOException {
		String[] str=id.split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(str[0]);
			rydgDao.deleteAll(date,str[1]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public BasePage<MRyYgdg> getDeptGridList(String deptId) {
		String sql="select worker_id work_id,worker_name work_name,work_cj,work_gx,work_bb,work_ct,work_sm,work_gz  from e_hr_worker left join m_ry_ygdg  on worker_id=work_id where  worker_flag='启用' and worker_dept='"+deptId+"' ";
		return rydgDao.QueryNoPageLists(sql);
	}

	/**
	 *@role:
	 *@Author:      huangbiao
	 *@Date:        2020/8/4 0004 9:18
	 *@Param:       [deptId]
	 *@return:      com.tengzhi.base.jpa.page.BasePage<com.tengzhi.business.sc.da.rydg.model.MRyYgdg>
	 *@Description: 根据部门左连接查询sys_user,m_ry_ygdg表
	 **/
	@Override
	public BasePage<MRyYgdg> getDeptGridListToSysUser(String deptId) {
		String sql="select user_id work_id,nick_name work_name,f_get_param_name('生产车间',work_cj,'"+   SessionUser.getCurrentCorpId()   +"') cjname,work_cj,f_getname('GETGXNAMES'," +
				" work_gx, '', '') gxname,work_gx,f_get_param_name('生产班次',work_bb,'"+   SessionUser.getCurrentCorpId()   +"') bbname,work_bb,f_getname('GETJTNAMES', work_ct, ''," +
				" " +
				"'') ctname,work_ct,work_sm,work_gz  from " +
				"sys_user left join m_ry_ygdg  on user_id=work_id where is_forbidden= false and dept_id='"+deptId+"' ";
		return rydgDao.QueryNoPageLists(sql);
	}


}
