package com.tengzhi.business.sc.pd.khgy.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.sale.processProduct.processContract.model.EXsContract;
import com.tengzhi.business.sale.processProduct.processContract.vo.ProcessContractVo;
import com.tengzhi.business.sc.pd.khgy.dao.MScKfgylistDao;
import com.tengzhi.business.sc.pd.khgy.dao.MScKfgylistGcDao;
import com.tengzhi.business.sc.pd.khgy.model.MScKfgylist;
import com.tengzhi.business.sc.pd.khgy.service.KhgyService;
import com.tengzhi.business.sc.pd.khgy.vo.MScKfgylistGcVo;
import com.tengzhi.business.sc.task.sctack.dao.ScTackDao;
import com.tengzhi.business.sc.task.sctack.model.MScScrw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Auther: 龚行礼
 * @Date: 2020/8/24 0013 21:45
 * @Description:客户工艺
 */

@Service
@Transactional
public class KhgyServiceImpl implements KhgyService {


	@Autowired
	private ScTackDao scTackDao;
	@Autowired
	private MScKfgylistDao mScKfgylistDao;
	@Autowired
	private MScKfgylistGcDao mScKfgylistGcDao;

	


	@Override
	public BasePage<Map<String, Object>> getSrchTopList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		String where=" and a.data_corp='"+securityUser.getCorpId()+"' ";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			where+=" and item_rq >='"+map.get("srchRq1")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			where+=" and item_rq <='"+map.get("srchRq2")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
			where+=" and ht_no like '%"+map.get("srchNo")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCustomer"))) {
			where+=" and master_id like '%"+map.get("srchCustomer")+"%'";
		}

		String sqlString=" select b.*,a.ht_no,a.ht_code,f_getname('GETDWEXP',b.master_id,'',a.data_corp) master_name from  e_xs_contract_detailed a ,m_sc_kfgylist b where a.ht_mo=b.item_mo  "+where;
		return mScKfgylistDao.QueryMapPageList(baseDto, sqlString+" order by item_rq desc,item_mo desc ", true);
	}

	@Override
	public BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlString=" select *,f_getname('GETGXNAME',scgy_gc,'','') gc_name  from  m_sc_kfgylist_gc where item_mo='"+map.get("itemMo")+"' order by scgy_Ord asc ";
		return  mScKfgylistGcDao.QueryMapPageList(baseDto, sqlString , true);
	}

	@Override
	public BasePage<Map<String, Object>> getByscgy(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		String masterId=map.get("masterId").toString();
		String ttype=map.get("ttype").toString();
		String itemMo=map.get("itemMo").toString();
		masterId="ATL14847";
		int cn=Integer.parseInt(mScKfgylistDao.getSingleResult("select count(*) cn from m_sc_kfgylist where item_mo=? ",itemMo));
		if(cn==0){
			itemMo=mScKfgylistDao.getSingleResult(" select item_mo from ( select item_mo from  m_sc_kfgylist where master_id=?1 and ttype=?2 order by item_rq desc )t limit 1 ",masterId,ttype);
		}
		String sqlString="select  item_mo,scgy_ord,scgy_gc,scgy_cz,scgy_ksize,scgy_yqsm,scgy_gx,f_getname('GETGXNAME', scgy_gc, '', '') gc_name,f_getname('GETGXNAME', scgy_gx, '', '') gx_name  from  m_sc_kfgylist_gc where 1=1 and item_mo='"+itemMo+"'  ";
		return mScKfgylistDao.QueryMapPageList(baseDto, sqlString+" order by scgy_ord asc  ", true);
	}

	@Override
	public Result saveOrUpdate(MScKfgylistGcVo mScKfgylistGcVo)  {
		SessionUser securityUser=SessionUser.SessionUser();
		String itemMo=mScKfgylistGcVo.getmScKfgylist().getItemMo();
		String scMo=mScKfgylistGcVo.getmScKfgylist().getScMo();
		MScKfgylist gyModel=mScKfgylistGcVo.getmScKfgylist();
		gyModel.setItemRq(new Date());
		int cn=Integer.parseInt(mScKfgylistDao.getSingleResult("select count(*) cn from m_sc_kfgylist where item_mo=? ",itemMo));
		if(cn>0){
			//删除旧的记录
			mScKfgylistDao.deleteById(itemMo);
			int cn2=Integer.parseInt(mScKfgylistDao.getSingleResult("select count(*) cn from m_sc_kfgylist_gc where item_mo=? ",itemMo));
			if(cn2>0){
				mScKfgylistGcDao.deleteById(itemMo);
			}
		}
		if(!mScKfgylistGcVo.getAddedList().isEmpty()) {
			mScKfgylistGcVo.getAddedList().forEach( item -> {
				item.setItemMo(itemMo);
				mScKfgylistGcDao.save(item);
			});
		}
		//更新m_sc_scrw表
		MScScrw.MScScrw_PK pk=new MScScrw.MScScrw_PK(scMo,itemMo);
		MScScrw scrwModel=scTackDao.findById(pk).orElse(null);
		scrwModel.setBzyl(gyModel.getBzyl());
		scrwModel.setTdyl(gyModel.getTdyl());
		scrwModel.setQtyl(gyModel.getQtyl());
		scrwModel.setLsyl(gyModel.getLsyl());
		scTackDao.dynamicSave(scrwModel,scTackDao.findById(pk).orElse(null));
		MScKfgylist   eXsContract=mScKfgylistDao.save(gyModel);
		return  Result.resultOkMsg("操作成功！");
	}




}
