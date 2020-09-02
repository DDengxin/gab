package com.tengzhi.business.mSbJt.wx.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.mSbJt.wx.dao.MSbSbwbDaoo;
import com.tengzhi.business.mSbJt.wx.model.MSbSbwbb;
import com.tengzhi.business.mSbJt.wx.service.MSbSbwbServicee;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MSbSbwbServiceImpll implements MSbSbwbServicee{
	@Autowired
	private MSbSbwbDaoo mSbSbwbDao;
	@SuppressWarnings("rawtypes")
	@Autowired
	private SysGenNoteService sysGenNoteService;
    
	@Override
	public BasePage<MSbSbwbb> findAll(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
        return mSbSbwbDao.QueryPageList(baseDto, Specifications.where((c) -> {
            c.contains("sbwbNote", map.get("sbwbNote"));
            c.contains("sbwbSbid", map.get("sbwbSbid"));
            c.eq("sbwbType", "保养");
        }));
	}
	
	@Override
    public MSbSbwbb save(MSbSbwbb mSbSbwb) throws Exception {
		mSbSbwb.setSbwbNote(sysGenNoteService.getNote(MSbSbwbb.class, "T", "yyMM", 4));
		if (judgeUnique(mSbSbwb)) {
			mSbSbwb.setSbwbFlag("登记");//setSbwbNote("登记");
			mSbSbwb.setSbwbType("保养");
			mSbSbwb.Validate();
            return mSbSbwbDao.save(mSbSbwb);
		  } else { throw new Exception("单号已存在"); }
    }
	
	
	 @Override
	  public boolean judgeUnique(MSbSbwbb mSbSbwb) {
	        return mSbSbwbDao.findAll(Specifications.where((c) -> {
	            c.eq("sbwbNote", mSbSbwb.getSbwbNote());
	        })).size() <= 0;
	    }

	@Override
	public Map<String, Object> findBySbwbNote(String sbwbNote) {
		String corpId = SessionUser.SessionUser().getCorpId();
		String sql = "select sbwb_rq \"sbwbRq\",sbwb_note \"sbwbNote\",sbwb_sbid \"sbwbSbid\",sbwb_dept \"sbwbDept\",sbwb_type \"sbwbType\",sbwb_dtype \"sbwbDtype\",sbwb_xtype \"sbwbXtype\",sbwb_sm \"sbwbSm\",sbwb_flag \"sbwbFlag\",sbwb_sman \"sbwbSman\",sbwb_jtime \"sbwbJtime\",sbwb_ptime \"sbwbPtime\",sbwb_pman \"sbwbPman\",sbwb_dtime \"sbwbDtime\",sbwb_dman \"sbwbDman\",sbwb_dsm \"sbwbDsm\",sbwb_ktype \"sbwbKtype\",data_man \"dataMan\",data_date \"dataDate\",data_corp \"dataCorp\",f_getname('GETDEPTNAME', sbwb_dept, '', '"+corpId+"') deptName from m_sb_sbwb where sbwb_note = '"+sbwbNote+"'";
		List<Map> sbwb = mSbSbwbDao.QueryListMap(sql);
	    Map<String, Object> map = sbwb.get(0);
		return map;
	}
	 @Override
	    public void update(MSbSbwbb mSbSbwb){
		 mSbSbwb.setSbwbType("保养");
		 mSbSbwbDao.update(mSbSbwb);
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
	public List<SelectVo> getSbwbDept(String trueText, String falseText) {
		return comboParam1();
	}
	public List<SelectVo> comboParam1(){
		List<SelectVo> voList=new ArrayList<SelectVo>();
      	String sql="select dept_name,dept_id from e_hr_dept where dept_flag='启用' order by dept_id ";
      	List<Map> mapList=mSbSbwbDao.QueryListMap(sql);
      	mapList.forEach(Map -> {
      		voList.add(new SelectVo(Map.get("dept_id"),String.valueOf( Map.get("dept_name"))));
      	});
      	return voList;
	}
	
	@Override
	public List<SelectVo> getSbwbSman(String trueText, String falseText) {
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
	public List<SelectVo> comboParam(String mod, String pareatId){
		List<SelectVo> voList=new ArrayList<SelectVo>();
		Object[] val = {mod,pareatId};
      	String sql="select param_id,param_name from sys_param  where param_status='true' and param_mod=?  and parent_id=? order by  param_ord ";
      	List<Map> mapList=mSbSbwbDao.QueryListMap(sql,val);
      	mapList.forEach(Map -> {
      		voList.add(new SelectVo(Map.get("param_id"),String.valueOf( Map.get("param_name"))));
      	});
      	return voList;
	}
	//确认
	@Override
	public boolean sure(String sbwbNote) {
		MSbSbwbb eXtNotice = mSbSbwbDao.findBySbwbNote(sbwbNote);
		if("登记".equals(eXtNotice.getSbwbFlag())) {
			eXtNotice.setSbwbFlag("确认");
			update(eXtNotice);
			return true;
		}else {
			return false;
		}
	}
	//接单
	@Override
	public boolean okk(String sbwbNote) {
		MSbSbwbb eXtNotice = mSbSbwbDao.findBySbwbNote(sbwbNote);
		if("确认".equals(eXtNotice.getSbwbFlag())) {
			eXtNotice.setSbwbFlag("接单");
			update(eXtNotice);
			return true;
		}else {
			return false;
		}
	}
	//完成
	@Override
	public boolean conf(String sbwbNote) {
		MSbSbwbb eXtNotice = mSbSbwbDao.findBySbwbNote(sbwbNote);
		if("接单".equals(eXtNotice.getSbwbFlag())) {
			eXtNotice.setSbwbFlag("完成");
			update(eXtNotice);
			return true;
		}else {
			return false;
		}
	}

	

}
