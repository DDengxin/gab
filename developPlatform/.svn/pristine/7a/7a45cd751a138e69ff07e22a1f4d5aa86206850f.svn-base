package com.tengzhi.business.finance.voucher.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.voucher.dao.VchTemplateEntryDao;
import com.tengzhi.business.finance.voucher.vo.VchTemplateVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.voucher.dao.VchTemplateDao;
import com.tengzhi.business.finance.voucher.model.VchTemplate;
import com.tengzhi.business.finance.voucher.service.VchTemplateService;

@Service
@Transactional
public class VchTemplateServiceImpl implements VchTemplateService {
	@Autowired
	VchTemplateDao vchTemplateDao;
	@Autowired
	VchTemplateEntryDao vchTemplateEntryDao;
	@Autowired
	private SysGenNoteService sysGenNoteService;
	@Override
	public BasePage<Map<String,Object>> findAll(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
        StringBuffer sqlWhere = SqlJoint.where(e -> {
        	e.eq("ftype",map.get("ftype").toString());
        	e.eq("data_corp",securityUser.getCorpId());
      });
		String sql="select * from e_f_voucher_vchtemplate where 1=1 "
		+" and ftempname like '%"+map.get("ftempname").toString()+"%' and"
		+sqlWhere;
		return vchTemplateDao.QueryToMapPage(baseDto,sql);
	}
    @Override
	public BasePage<Map<String, Object>> getMxList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		String sqlWhere = "  and b.fvctemplateid='"+map.get("fvctemplateid")+"' ";
		String sql ="select b.* from e_f_voucher_vchtemplate a,e_f_voucher_vchtemplateentry b where a.fvctemplateid=b.fvctemplateid and a.data_corp=b.data_corp "
					+sqlWhere
		+ "  and b.data_corp='" +securityUser.getCorpId() + "'";
		return vchTemplateDao.QueryToMapPage(baseDto, sql);
	}

	public Result saveData(VchTemplateVo vo){
		SessionUser su = SessionUser.SessionUser();

		String note = sysGenNoteService.getNote("","VchT","yyyy",8);

		SessionUser securityUser=SessionUser.SessionUser();
		if(null==vo.getMaindata().getFvctemplateid()||vo.getMaindata().getFvctemplateid().length()==0) vo.getMaindata().setFvctemplateid(note);

		vo.getMaindata().setDataCorp(securityUser.getCorpId());

		if (!vo.getAddedList().isEmpty()){
			vo.getAddedList().forEach( item -> {

				item.setFvctemplateid(vo.getMaindata().getFvctemplateid());
//				item.setFid(vchTemplateEntryDao.getfid());
				item.setDataCorp(securityUser.getCorpId());
				vchTemplateEntryDao.save(item);

			});
		}
		if (!vo.getDeletedList().isEmpty()) {
			vchTemplateEntryDao.deleteAll(vo.getDeletedList());
		}
		if(!vo.getModifyedList().isEmpty()){
			vo.getModifyedList().forEach( item ->{

//				if(null==item.getFid()) item.setFid(vchTemplateEntryDao.getfid());
				item.setFvctemplateid(vo.getMaindata().getFvctemplateid());
				item.setDataCorp(securityUser.getCorpId());
				vchTemplateEntryDao.save(item);
//				vchTemplateEntryDao.dynamicSave(item,vchTemplateEntryDao.findById(item.getFid()).orElse(null));
			});
		}
		VchTemplate category=vchTemplateDao.dynamicSave(vo.getMaindata(),vchTemplateDao.findById(vo.getMaindata().getFvctemplateid()).orElse(null));





		return Result.resultOk(category);
	}

	@Override
	public Result deleteById(String Id) {

		vchTemplateDao.executeUpdateSql("  delete from e_f_voucher_vchtemplate where fvctemplateid='"+Id+"'; delete from e_f_voucher_vchtemplateentry where fvctemplateid='"+Id +"';  ");
	return Result.resultOkMsg("删除成功");
	}

	@Override
	public Map<String, Object> getById(String Id) {
		String sql="select * from e_f_voucher_vchtemplate where fvctemplateid  = '"+Id+"' ";
		return vchTemplateDao.QueryToFirstMap(sql);
	}
}
