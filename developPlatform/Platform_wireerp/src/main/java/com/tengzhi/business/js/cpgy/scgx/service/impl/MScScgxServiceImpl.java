package com.tengzhi.business.js.cpgy.scgx.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.js.cpgy.scgy.model.MScScgy;
import com.tengzhi.business.js.cpgy.scgy.model.MScScgymx;
import com.tengzhi.business.js.cpgy.scgy.service.MScScgyService;
import com.tengzhi.business.sc.task.sctack.model.MScScrwGx;
import com.tengzhi.business.sc.task.sctack.service.ScTackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.business.js.cpgy.scgx.dao.MScScgxDao;
import com.tengzhi.business.js.cpgy.scgx.dao.MScScgxmxDao;
import com.tengzhi.business.js.cpgy.scgx.model.MScScgx;
import com.tengzhi.business.js.cpgy.scgx.model.MScScgxmx;
import com.tengzhi.business.js.cpgy.scgx.model.MScScgxmx.MScScgxmx_PK;
import com.tengzhi.business.js.cpgy.scgx.service.MScScgxService;
import com.tengzhi.business.js.cpgy.scgx.vo.MScScgxVo;
import com.tengzhi.business.mSbJt.model.MSbJt;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.dao.SysParamDao;
import com.tengzhi.business.system.params.model.SysParams;

import cn.hutool.core.util.ObjectUtil;


@Service
@Transactional
public class MScScgxServiceImpl  implements MScScgxService {
	@Autowired
	private MScScgxDao  mScScgxDao;

	@Autowired
	private MScScgxmxDao mScScgxmxDao;
	
	@Autowired
	private SysParamDao sysParamDao;

	@Autowired
	private MScScgyService mScScgyService;

	@Autowired
	private ScTackService scTackService;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;


	@Override
	public BasePage<MScScgx> getSrchTopList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		String where=" where 1=1 ";
		
		if(ObjectUtil.isNotEmpty(map.get("srchId"))) {
            where+=" and gx_id like '%"+map.get("srchId")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
            where+=" and gx_name like '%"+map.get("srchName")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchType"))) {
            where+=" and gy_type like '%"+map.get("srchType")+"%'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchFlag"))) {
            where+=" and gy_flag like '%"+map.get("srchFlag")+"%'";
        }
		
		String sqlString="select *,f_get_param_name('产品大类',gx_type,'"+   SessionUser.getCurrentCorpId()   +"') typename,f_getparamname('GETPARENTIDBYRAMNAME',gx_cptype,'','',gx_type,'"+   SessionUser.getCurrentCorpId()   +"') cptypename,f_getname('GETGXNAME', gx_uid, '', '') uidname"
				+ ",f_getparamname('GETTYPEBYRAMNAME',gx_cj,'生产车间','','','"+   SessionUser.getCurrentCorpId()   +"') cjname,f_getname('GETJTNAMES', gx_ct, '', '') ctname,f_get_param_name('半成品检验',gx_sxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false) sxname,f_get_param_name('半成品检验',gx_xxjj,'"+   SessionUser.getCurrentCorpId()   +"','',false) xxname,f_get_param_name('半成品检验',gx_dojj,'"+   SessionUser.getCurrentCorpId()   +"','',false) doname"
				+ ",f_get_param_name('生产方式',gx_stype,'"+   SessionUser.getCurrentCorpId()   +"','',false) stypename ,f_get_param_name('半成品标签',gx_bq,'"+   SessionUser.getCurrentCorpId()   +"','',false) bqname"
				+ " from m_sc_scgx "+where;
		return mScScgxDao.QueryPageLists(baseDto,sqlString+" order by gx_id desc");
	}

	@Override
	public BasePage<MScScgxmx> getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlString="select * from m_sc_scgxmx  where gx_id ='"+map.get("gxId")+"'";
		return mScScgxmxDao.QueryPageLists(baseDto,sqlString);
	}

	@Override
	public Result save(MScScgxVo mScScgxVo)throws Exception {
		//String note=sysGenNoteService.getNote(MScScgx.class,"GX","",4);
		
		//手动输入需判断ID是否重复start
		String note=mScScgxVo.getmScScgx().getGxId();
		String cn=mScScgxDao.getSingleResult("select count(*)cn from m_sc_scgx where gx_id=? ", note);
		if(Integer.parseInt(cn)>0) {
			return Result.error("工序号："+note+"已存在，不能重复添加");
		}
		//end
		SessionUser securityUser=SessionUser.SessionUser();
		mScScgxVo.getmScScgx().setGxId(note);
		
		if (!mScScgxVo.getAddedList().isEmpty()){
			mScScgxVo.getAddedList().forEach( item -> {
				item.setGxId(mScScgxVo.getmScScgx().getGxId());
				mScScgxmxDao.save(item);
			});
		}
		if (!mScScgxVo.getDeletedList().isEmpty()) {
			mScScgxmxDao.deleteAll(mScScgxVo.getDeletedList());
		}
		if(!mScScgxVo.getModifyedList().isEmpty()){
			mScScgxVo.getModifyedList().forEach( item ->{
				MScScgxmx_PK pk = new MScScgxmx_PK(mScScgxVo.getmScScgx().getGxId(),item.getGxxmOrd(),item.getGxxmId());
				mScScgxmxDao.dynamicSave(item,mScScgxmxDao.findById(pk).orElse(null));
			});
		}
		mScScgxVo.getmScScgx().setDataCorp(securityUser.getCorpId());
		MScScgx mScScgx=mScScgxDao.save(mScScgxVo.getmScScgx());
		return Result.resultOkMsg(mScScgx.getGxId());
	}

	@Override
	public Result update(MScScgxVo mScScgxVo)throws Exception {
		//String note=sysGenNoteService.getNote(MScScgx.class,"GX","",4);
		SessionUser securityUser=SessionUser.SessionUser();
		String gxId=mScScgxVo.getmScScgx().getGxId();
		if (!mScScgxVo.getAddedList().isEmpty()){
			mScScgxVo.getAddedList().forEach( item -> {
				item.setGxId(gxId);
				mScScgxmxDao.save(item);
			});
		}
		if (!mScScgxVo.getDeletedList().isEmpty()) {
			mScScgxmxDao.deleteAll(mScScgxVo.getDeletedList());
		}
		if(!mScScgxVo.getModifyedList().isEmpty()){
			mScScgxVo.getModifyedList().forEach( item ->{
				MScScgxmx_PK pk = new MScScgxmx_PK(gxId,item.getGxxmOrd(),item.getGxxmId());
				mScScgxmxDao.dynamicSave(item,mScScgxmxDao.findById(pk).orElse(null));
			});
		}
		mScScgxVo.getmScScgx().setDataCorp(securityUser.getCorpId());
		mScScgxDao.dynamicSave(mScScgxVo.getmScScgx(),mScScgxDao.findById(gxId).orElse(null));
		return Result.resultOkMsg("修改成功");
	}

	@Override
	public MScScgx findById(String gxId) {
		return mScScgxDao.findById(gxId).orElse(null);

	}

	@Override
	public MScScgxmx findById(String gxId, Integer gxxmOrd,String gxxmId) {
		MScScgxmx_PK pk = new MScScgxmx_PK(gxId,gxxmOrd,gxxmId );
		return mScScgxmxDao.findById(pk).orElse(null);

	}

	@Override
	public Result deleteById(String gxId){
		Result result = new Result();
		List<MScScgymx> mScScgymxList = mScScgyService.findByMscScgxId(gxId);
		List<MScScrwGx> mScScrwGxList = scTackService.findByGxId(gxId);
		if (mScScgymxList.size()==0) {
			if (mScScrwGxList.size()==0) {
				mScScgxDao.deleteById(gxId);
				mScScgxmxDao.deleteByGxId(gxId);
				result.putMsg("msg","删除成功");
			}else{
				result.putMsg("msg","删除失败，数据在生产任务中工序表已存在");
			}
		}else{
			result.putMsg("msg","删除失败，数据在工艺表中已存在");
		}
		return result;
	}

	 @Override
    public List<SelectVo> getGxAllSelectList() {
    	List<SelectVo> rset = new ArrayList<SelectVo>();
        List<MScScgx> sysParams = mScScgxDao.QueryListModel(MScScgx.class, "select  * from m_sc_scgx where  gx_flag='true' order by gx_ord ");
        sysParams.forEach(item -> {
            rset.add(new SelectVo(item.getGxId(), item.getGxName()));
        });
        return rset;
    }

	@Override
	public List<SelectVo> getGxRootSelectList(String gxtype) {
		List<SelectVo> rset = new ArrayList<SelectVo>();
		String sql="";
		if("".equals(gxtype)||gxtype==null||gxtype.length()==0){
			sql="select  * from m_sc_scgx where  gx_flag='true' and  gx_uid=''  order by gx_ord ";
		}else{
			sql="select  * from m_sc_scgx where  gx_flag='true' and  gx_uid='' and gx_type='"+gxtype+"'  order by gx_ord ";
		}
		List<MScScgx> sysParams = mScScgxDao.QueryListModel(MScScgx.class, sql);
		sysParams.forEach(item -> {
			rset.add(new SelectVo(item.getGxId(), item.getGxName()));
		});
		return rset;
	}

	@Override
	public List<SelectVo> getGxAllTreeList(String gxtype) {
		String sql = "";
		if("ROOT".equals(gxtype)||gxtype==null||gxtype.length()==0){
			sql="select  gx_id,gx_name,gx_uid from m_sc_scgx where  gx_flag='true'  order by gx_ord ";
		}else{
			sql="select  gx_id,gx_name,gx_uid from m_sc_scgx where  gx_flag='true'  and gx_type='"+gxtype+"'  order by gx_ord ";
		}
		List<MScScgx> sysParams = mScScgxDao.QueryListModel(MScScgx.class, sql);
		return sysParams.stream().map(item -> new SelectVo(item.getGxId(), item.getGxName()) {{
			setPid(item.getGxUid());
		}}).collect(Collectors.toList());
	}

	 @Override
	public List<SelectVo> getCjList(String gx) {
		List<SelectVo> list = new ArrayList<SelectVo>();
        String sqlString="SELECT  * FROM  sys_param  WHERE  param_id  =   any(string_to_array(( select gx_cj FROM m_sc_scgx WHERE gx_id = '"+gx+"' ), ',' )  ) ";
        List<SysParams> mSbJt = sysParamDao.QueryListModel(SysParams.class,sqlString);
         mSbJt.forEach(item -> {
            list.add(new SelectVo(item.getParamId(), item.getParamName()));
        });
        return list;
	}

	 @Override
	public List<SelectVo> getGxByScmoList(String scmo) {
		List<SelectVo> list = new ArrayList<SelectVo>();
        String sqlString="select * from m_sc_scgx where gx_id in( select gx_id from m_sc_scrw_gx where sc_mo='"+scmo+"' ) order by gx_ord  ";
        List<MScScgx> mSbJt = mScScgxDao.QueryListModel(MScScgx.class,sqlString);
         mSbJt.forEach(item -> {
            list.add(new SelectVo(item.getGxId(), item.getGxName()));
        });
        return list;
	}
	 
	
}
