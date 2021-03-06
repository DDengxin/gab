package com.tengzhi.business.mSbJt.by.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.mSbJt.by.dao.MSbSbwbDao;
import com.tengzhi.business.mSbJt.by.model.MSbSbwb;
import com.tengzhi.business.mSbJt.by.service.MSbSbwbService;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MSbSbwbServiceImpl implements MSbSbwbService{
	@Autowired
	private MSbSbwbDao mSbSbwbDao;
	@SuppressWarnings("rawtypes")
	@Autowired
	private SysGenNoteService sysGenNoteService;

	@Override
    public MSbSbwb save(MSbSbwb mSbSbwb) throws Exception {
		//自动生成单号
		mSbSbwb.setSbwbNote(sysGenNoteService.getyyMMNote4(MSbSbwb.class, "WX"));
		SessionUser sessionUser=SessionUser.SessionUser();
		if (judgeUnique(mSbSbwb)) {
			mSbSbwb.setSbwbFlag("登记");
			mSbSbwb.setDataCorp(sessionUser.getCorpId());
			mSbSbwb.setDataMan(sessionUser.getRealName());
			mSbSbwb.Validate();
            return mSbSbwbDao.save(mSbSbwb);
		  } else { throw new Exception("单号已存在"); }
    }
	
	
	 @Override
	  public boolean judgeUnique(MSbSbwb mSbSbwb) {
	        return mSbSbwbDao.findAll(Specifications.where((c) -> {
	            c.eq("sbwbNote", mSbSbwb.getSbwbNote());
	        })).size() <= 0;
	    }

	@Override
	public MSbSbwb findBySbwbNote(String sbwbNote) {
		MSbSbwb mb=mSbSbwbDao.findBySbwbNote(sbwbNote);
		mb.setDeptname(mSbSbwbDao.getDepatName(mb.getSbwbDept(), mb.getDataCorp()));
		return mb;
	}


	@Override
    public void deleteBySbwbNote(String sbwbNote){
        mSbSbwbDao.deleteBySbwbNote(sbwbNote);
    }

	@Override
	public List<SelectVo> getSbwbXtype(String trueText, String falseText) {
		return comboParam("设备","LSSBDB");
	}
	@Override
	public List<SelectVo> getSbwbDtype(String trueText, String falseText) {
		return comboParam("设备","SBDB");
	}
	
	@Override
	public List<SelectVo> getSbwbType(String trueText, String falseText) {
		return comboParam("设备","WXDA");
	}
	@Override
	public List<SelectVo> getSbwbKtype(String trueText, String falseText) {
		return comboParam("设备","WXGL");
	}
	@Override
	public List<SelectVo> getJjcd(String trueText, String falseText) {
		return comboParam("采购","JJCD");
	}
	@Override
	public List<SelectVo> comboParam(String mod, String pareatId){
		List<SelectVo> voList=new ArrayList<SelectVo>();
		Object[] val = {mod,pareatId};
      	String sql="select param_id,param_name from sys_param  where param_status='true' and param_mod=?  and parent_id=? order by  param_ord ";
      	List<Map> mapList=mSbSbwbDao.QueryListMap(sql,val);
      	mapList.forEach(Map -> {
      		voList.add(new SelectVo(Map.get("param_name"),String.valueOf( Map.get("param_name"))));
      	});
      	return voList;
	}
	@Override
	public List<SelectVo> getSbwbDept(String trueText, String falseText) {
		return comboParam1();
	}
	public List<SelectVo> comboParam1(){
		List<SelectVo> voList=new ArrayList<SelectVo>();
      	String sql="select dept_name,dept_id from e_hr_dept where dept_flag='启用' order by dept_id ";
      	List<Map> mapList=mSbSbwbDao.QueryListMap(sql);
      	mapList.forEach(Map -> {
      		voList.add(new SelectVo(Map.get("dept_name"),String.valueOf( Map.get("dept_name"))));
      	});
      	return voList;
	}
	@Override
	public List<SelectVo> getSbwbSman(String trueText, String falseText) {
		return comboParam2();
	}
	
	@Override
	public List<SelectVo> getDataMan(String trueText, String falseText){
		return comboParam2();
	}
	@Override
	public List<SelectVo> getSbwbDman(String trueText, String falseText){
		return comboParam2();
	}
	public List<SelectVo> comboParam2(){
		List<SelectVo> voList=new ArrayList<SelectVo>();
		String sql="select worker_name,worker_id from e_hr_worker where worker_flag='启用' order by worker_id ";
		List<Map> mapList=mSbSbwbDao.QueryListMap(sql);
		mapList.forEach(Map -> {
			voList.add(new SelectVo(Map.get("worker_id"),String.valueOf( Map.get("worker_name"))));
		});
		return voList;
	}
	@Override
	public List<SelectVo> getSbwbSbid(String trueText, String falseText) {
		return comboParam3();
	}
	public List<SelectVo> comboParam3(){
		List<SelectVo> voList=new ArrayList<SelectVo>();
		String sql="select machine_name,machine_no from m_sb_jt where machine_flag='启用' order by machine_no ";
		List<Map> mapList=mSbSbwbDao.QueryListMap(sql);
		mapList.forEach(Map -> {
			voList.add(new SelectVo(Map.get("machine_no"),String.valueOf( Map.get("machine_no"))));
		});
		return voList;
	}
	
	 @Override
	 public void update(MSbSbwb mSbSbwb){
		mSbSbwbDao.dynamicSave(mSbSbwb,mSbSbwbDao.findBySbwbNote(mSbSbwb.getSbwbNote()));
	 }
	
	//确认
	@Override
	public boolean sure(String sbwbNote) {
		MSbSbwb eXtNotice = mSbSbwbDao.findBySbwbNote(sbwbNote);
		if("登记".equals(eXtNotice.getSbwbFlag())) {
			eXtNotice.setSbwbFlag("确认");
			SessionUser securityUser=SessionUser.SessionUser();
			update(eXtNotice);
			return true;
		}else {
			return false;
		}
	}
	//取消确认
	@Override
	public boolean cancel(String sbwbNote) {
		MSbSbwb eXtNotice = mSbSbwbDao.findBySbwbNote(sbwbNote);
		if("确认".equals(eXtNotice.getSbwbFlag())) {
			eXtNotice.setSbwbFlag("登记");
			SessionUser securityUser=SessionUser.SessionUser();
			update(eXtNotice);
			return true;
		}else {
			return false;
		}
	}
	
	//接单
	@Override
	public boolean okk(String sbwbNote) {
		MSbSbwb eXtNotice = mSbSbwbDao.findBySbwbNote(sbwbNote);
		if("确认".equals(eXtNotice.getSbwbFlag())) {
			eXtNotice.setSbwbFlag("接单");
			SessionUser securityUser=SessionUser.SessionUser();
			update(eXtNotice);
			return true;
		}else {
			return false;
		}
	}
	//安排
	@Override
	public boolean ook(MSbSbwb mSbSbwb) {
		String flagString=mSbSbwbDao.getFlag(mSbSbwb.getSbwbNote());
		if("接单".equals(flagString)) {
			mSbSbwb.setSbwbFlag("安排");
			SessionUser securityUser=SessionUser.SessionUser();
			update(mSbSbwb);
			return true;
		}else {
			return false;
		}
	}
	//完成
	@Override
	public boolean conf(MSbSbwb mSbSbwb) {
		String flagString=mSbSbwbDao.getFlag(mSbSbwb.getSbwbNote());
		if("安排".equals(flagString)) {
			mSbSbwb.setSbwbFlag("完成");
			SessionUser securityUser=SessionUser.SessionUser();
			update(mSbSbwb);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public Result getFlag(String sbwbNote, String flag) {
		String flagString=mSbSbwbDao.getFlag(sbwbNote);
		if(flag.equals(flagString)) {
			return  Result.resultOk("操作成功！");
		}
		return  Result.error("该单不是“"+flag+"”状态,不能操作！");
	}
	public Result setFlag(String sbwbNote,String flag) {
		mSbSbwbDao.setFlag(sbwbNote,flag);
		return  Result.resultOk("操作成功！");
	}
	

	@Override
	public BasePage<MSbSbwb> getSrchButonList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sbwbNote=map.get("sbwbNote");
		String rq1 = map.get("srchRq1");
		String rq2 = map.get("srchRq2");
		String sbwbDept=map.get("sbwbDept");
		String sbwbType=map.get("sbwbType");
		String sbwbFlag=map.get("sbwbFlag");
		String machineId=map.get("machineId");
		String machineName=map.get("machineName");
		String sbwbPman=map.get("sbwbPman");
        String sqlwhere="";
 		if(rq1 != null && rq1.trim().length() > 0) {
 			sqlwhere +=" and b.sbwb_rq >= '"+rq1+"' ";
 		}
 		if(rq2 != null && rq2.trim().length() > 0) {
 			sqlwhere +=" and b.sbwb_rq <= '"+rq2+"' ";
 		}
 		
		if(StringUtils.isNotEmpty(sbwbNote)) {
			sqlwhere+=" and  sbwb_note like '%"+sbwbNote+"%'";
		}
		if(!StringUtils.isBlank(sbwbDept)) {
			sqlwhere+=" and  sbwb_dept like '%"+sbwbDept+"%'";
		}
		if(!StringUtils.isBlank(sbwbType)) {
			sqlwhere+=" and  sbwb_type like '%"+sbwbType+"%'";
		}
		if(!StringUtils.isBlank(sbwbFlag)) {
			sqlwhere+=" and  sbwb_flag like '%"+sbwbFlag+"%'";
		}
		if(!StringUtils.isBlank(machineId)) {
			sqlwhere+=" and  machine_id like '%"+machineId+"%'";
		}
		if(!StringUtils.isBlank(machineName)) {
			sqlwhere+=" and  machine_name like '%"+machineName+"%'";
		}
		if(!StringUtils.isBlank(sbwbPman)) { 
			sqlwhere = "  and position(',"+ sbwbPman +",' in ',' || b.sbwb_pman ||',') > 0 ";
		}
	
		String sql="select sbwb_sman,sbwb_pman,sbwb_dman,b.data_man,sbwb_note,sbwb_rq,sbwb_sbid,sbwb_dept,"
				+ "sbwb_type,sbwb_sm,sbwb_flag,sbwb_jtime,sbwb_ptime,"
				+ "sbwb_dtime,sbwb_ktype,sbwb_dsm,t.machine_name,"
				+ "f_getname('GETDEPTNAME', sbwb_dept, '', b.data_corp) deptname,"
				+ "f_get_param_name('维保人员',b.sbwb_dman,'"+   SessionUser.getCurrentCorpId()   +"','',true)username,"
				+ "f_get_param_name('维保人员',b.sbwb_pman,'"+   SessionUser.getCurrentCorpId()   +"','',true)usernamee"
				+ " from m_sb_jt t,m_sb_sbwb b where t.machine_id=b.sbwb_sbid "+sqlwhere+"ORDER BY sbwb_note";

		return mSbSbwbDao.QueryPageLists(baseDto,sql);
	}


	@Override
	public boolean okk(MSbSbwb mSbSbwb) {
		// TODO Auto-generated method stub
		return false;
	}

}
