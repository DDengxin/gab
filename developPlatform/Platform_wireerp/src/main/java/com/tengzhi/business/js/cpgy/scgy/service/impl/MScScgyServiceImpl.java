package com.tengzhi.business.js.cpgy.scgy.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.business.js.cpgy.scgy.dao.MScScgyDao;
import com.tengzhi.business.js.cpgy.scgy.dao.MScScgymxDao;
import com.tengzhi.business.js.cpgy.scgy.model.MScScgy;
import com.tengzhi.business.js.cpgy.scgy.model.MScScgymx;
import com.tengzhi.business.js.cpgy.scgy.model.MScScgymx.MScScgymx_PK;
import com.tengzhi.business.js.cpgy.scgy.service.MScScgyService;
import com.tengzhi.business.js.cpgy.scgy.vo.MScScgyVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;

import cn.hutool.core.util.ObjectUtil;


@Service
@Transactional
public class MScScgyServiceImpl  implements MScScgyService {
	@Autowired
	private MScScgyDao  mScScgyDao;

	@Autowired
	private MScScgymxDao mScScgymxDao;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;


	@Override
	public BasePage<MScScgy> getSrchTopList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser= SessionUser.SessionUser();
		String where=" where 1=1 ";
		if(ObjectUtil.isNotEmpty(map.get("srchId"))) {
            where+=" and gy_id like '%"+map.get("srchId")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
            where+=" and gy_name like '%"+map.get("srchName")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchType"))) {
            where+=" and gy_type like '%"+map.get("srchType")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchFlag"))) {
            where+=" and gy_flag like '%"+map.get("srchFlag")+"%'";
        }
		
		String sqlString="select *,f_get_param_name('产品大类',gy_type,'"+   SessionUser.getCurrentCorpId()   +"') typename,f_getname('GETDWEXP',gy_customer,'','"+securityUser.getCorpId()+"') customername,f_getparamname('GETPARENTIDBYRAMNAME',gy_cptype,'','',gy_type,'"+   SessionUser.getCurrentCorpId()   +"') cptypename \r\n" + 
				",f_getparamname('GETBCPCODENAME',gy_cpname,'','技术',gy_type,'"+   SessionUser.getCurrentCorpId()   +"') cpname from m_sc_scgy "+where;
		System.err.println(sqlString);
		return mScScgyDao.QueryPageLists(baseDto,sqlString+" order by gy_id desc");
	}

/*	@Override
	public BasePage<MScScgymx> getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlString="select * from m_sc_scgymx  where gy_id ='"+map.get("gyId")+"' order by gx_ord ";
		String sqlString2 = "f_get_param_name('半成品检验',gx_sxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false),f_get_param_name('半成品检验',gx_xxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false)," +
				"f_get_param_name('半成品检验',gx_dojj,'"+   SessionUser.getCurrentCorpId()   +"','',false),gx_jgyq";
		String newSql = "select m_sc_scgymx.*,f_get_param_name('半成品检验',gx_sxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false),f_get_param_name('半成品检验', gx_xxjj, " +
				"'', false),f_get_param_name('半成品检验',gx_dojj,'"+   SessionUser.getCurrentCorpId()   +"','',false),gx_jgyq from m_sc_scgymx.gx_id = m_sc_scgx.gx_id where gy_id ='"+map.get("gyId")+"' order by gx_ord";
		String joinSql = "select m_sc_scgymx.*,f_get_param_name('半成品检验',gx_sxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false) sxname,f_get_param_name('半成品检验',gx_xxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false) xxname,f_get_param_name('半成品检验',gx_dojj,'"+   SessionUser.getCurrentCorpId()   +"','',false) doname,gx_jgyq from m_sc_scgymx,m_sc_scgx where m_sc_scgymx" +
				".gx_id = m_sc_scgx.gx_id and gy_id ='"+map.get("gyId")+"' order by gx_ord";
		List<Map<String, Object>> maps = mScScgymxDao.QueryToMap(joinSql);
		return mScScgymxDao.QueryPageLists(baseDto,joinSql);
	}*/

	@Override
	public List<Map<String, Object>> getSrchBottomJoin(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String joinSql = "select m_sc_scgymx.*,f_get_param_name('半成品检验',gx_sxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false) sxname,f_get_param_name('半成品检验',gx_xxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false) xxname,f_get_param_name('半成品检验',gx_dojj,'"+   SessionUser.getCurrentCorpId()   +"','',false) doname,gx_jgyq from m_sc_scgymx,m_sc_scgx where m_sc_scgymx" +
				".gx_id = m_sc_scgx.gx_id and gy_id ='"+map.get("gyId")+"' order by gx_ord";
		List<Map<String, Object>> data = mScScgymxDao.QueryToMap(joinSql);
		return data;
	}

	@Override
	public Result save(MScScgyVo mScScgyVo)throws Exception {
		//String note=sysGenNoteService.getNote(MScScgy.class,"GY","",4);
		//手动输入需判断ID是否重复start
		String note=mScScgyVo.getmScScgy().getGyId();
		String cn=mScScgyDao.getSingleResult("select count(*)cn from m_sc_scgy where gy_id=? ", note);
		if(Integer.parseInt(cn)>0) {
			return Result.error("工艺号："+note+"已存在，不能重复添加");
		}
		//end
		SessionUser securityUser=SessionUser.SessionUser();
		mScScgyVo.getmScScgy().setGyId(note);
		if (!mScScgyVo.getAddedList().isEmpty()){
			mScScgyVo.getAddedList().forEach( item -> {
				item.setGyId(mScScgyVo.getmScScgy().getGyId());
				item.setGyName(mScScgyVo.getmScScgy().getGyName());
				mScScgymxDao.save(item);
			});
		}
		if (!mScScgyVo.getDeletedList().isEmpty()) {
			mScScgymxDao.deleteAll(mScScgyVo.getDeletedList());
		}
		if(!mScScgyVo.getModifyedList().isEmpty()){
			mScScgyVo.getModifyedList().forEach( item ->{
				item.setGyId(mScScgyVo.getmScScgy().getGyId());
				item.setGyName(mScScgyVo.getmScScgy().getGyName());
				MScScgymx_PK pk = new MScScgymx_PK(mScScgyVo.getmScScgy().getGyId(),item.getGxOrd(),item.getGxId());
				mScScgymxDao.dynamicSave(item,mScScgymxDao.findById(pk).orElse(null));
			});
		}
		mScScgyVo.getmScScgy().setDataCorp(securityUser.getCorpId());
		MScScgy MScScgy=mScScgyDao.save(mScScgyVo.getmScScgy());
		return Result.resultOkMsg(MScScgy.getGyId());
	}

	@Override
	public Result update(MScScgyVo mScScgyVo)throws Exception {
		//String note=sysGenNoteService.getNote(MScScgy.class,"GX","",4);
		SessionUser securityUser=SessionUser.SessionUser();
		String gyId=mScScgyVo.getmScScgy().getGyId();
		//因为前台改动是需要改动序号，然而gyid+序号+gxid组成组合键，这与修改序号的需求矛盾，
		//除非在表中另加一个主键，不用联合主键才能满足该需求，不另加主键的情况只能先删后增 2020-06-26 gxl
		
		//删除明细
		mScScgymxDao.deleteByGxId(gyId);
		if (!mScScgyVo.getmScScgymx().isEmpty()){
			mScScgyVo.getmScScgymx().forEach( item -> {
				item.setGyId(gyId);
				item.setGyName(mScScgyVo.getmScScgy().getGyName());
				mScScgymxDao.save(item);
			});
		}
		//增加明细
		/*if (!mScScgyVo.getAddedList().isEmpty()){
			mScScgyVo.getAddedList().forEach( item -> {
				item.setGyId(gyId);
				item.setGyName(mScScgyVo.getmScScgy().getGyName());
				mScScgymxDao.save(item);
			});
		}
		if(!mScScgyVo.getModifyedList().isEmpty()){
			mScScgyVo.getModifyedList().forEach( item ->{
				item.setGyId(gyId);
				item.setGyName(mScScgyVo.getmScScgy().getGyName());
				mScScgymxDao.save(item);
			});
		}*/
		//end
		//更新主表
		mScScgyVo.getmScScgy().setDataCorp(securityUser.getCorpId());
		mScScgyDao.dynamicSave(mScScgyVo.getmScScgy(),mScScgyDao.findById(gyId).orElse(null));
		return Result.resultOkMsg("修改成功");
	}

	@Override
	public MScScgy findById(String gyId) {
		SessionUser securityUser=SessionUser.SessionUser();
		MScScgy model=mScScgyDao.findById(gyId).orElse(null);
		String customername;
		if (model != null) {
			customername=mScScgyDao.getSingleResult(" select f_getname('GETDWEXP','"+model.getGyCustomer()+"','','"+securityUser.getCorpId()+"') customername");
			model.setCustomername(customername);
		}
		return model;

	}

	@Override
	public MScScgymx findById(String gyId, Integer gxOrd,String gxId) {
		MScScgymx_PK pk = new MScScgymx_PK(gyId,gxOrd,gxId );
		return mScScgymxDao.findById(pk).orElse(null);

	}

	@Override
	public List<MScScgymx> findByMscScgxId(String gxId) {
		List<MScScgymx> mScScgymxList = mScScgymxDao.findByGxId(gxId);
		return mScScgymxList;
	}


	@Override
	public void deleteById(String gyId){
		mScScgyDao.deleteById(gyId);
		mScScgymxDao.deleteByGxId(gyId);
	}

	 @Override
	    public List<SelectVo> getGyAllSelectList() {
	    	List<SelectVo> rset = new ArrayList<SelectVo>();
	        List<MScScgy> sysParams = mScScgyDao.QueryListModel(MScScgy.class, "select  * from m_sc_scgy where  gy_flag='true' order by gy_ord ");
	        sysParams.forEach(item -> {
	            rset.add(new SelectVo(item.getGyId(), item.getGyName()));
	        });
	        return rset;
	    }

}
