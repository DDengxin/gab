package com.tengzhi.business.platform.manage.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.manage.dao.PriceConfigureDao;
import com.tengzhi.business.platform.manage.dao.PriceConfigureSqlDao;
import com.tengzhi.business.platform.manage.model.G_PriceConfigure;
import com.tengzhi.business.platform.manage.service.PriceConfigureService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.user.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class PriceConfigureServiceImpl implements PriceConfigureService {
	
	@Autowired
	private PriceConfigureDao priceConfigureDao;
	
	@Autowired
	private PriceConfigureSqlDao priceConfigureSqlDao;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;

	@Override
	public BasePage<Map<String, Object>> getSrch(BaseDto baseDto) throws IOException {
		return priceConfigureSqlDao.getSrch(baseDto);
	}
	
	@Override
	public Result save(G_PriceConfigure model) throws Exception {
		//验证排序字段不能重复
		if(judgeUnique(model)) {
			String note = sysGenNoteService.getNote(G_PriceConfigure.class, "BJ", "yyyy-mm-dd", 3);
			SessionUser sessionUser = SessionUser.SessionUser();
			SysUser user = sessionUser.getSysUser();
			model.setStartTime(new Date());
			model.setPcNote(note);
			model.setPcCorp(sessionUser.getCorpId());
            model.setPcMan(user.getUserId());
			priceConfigureDao.save(model);			
			return Result.resultOk("保存成功");		
		}else {
			return	Result.resultError(100, "该排序已存在");
		}
	}
	
	@Override
	public G_PriceConfigure findByPcNote(String Id) {
		// TODO Auto-generated method stub
		 return priceConfigureDao.findByPcNote(Id);
	}
	
	@Override
	public void update(G_PriceConfigure advisory) {
		priceConfigureDao.dynamicSave(advisory,priceConfigureDao.findByPcNote(advisory.getPcNote()));
	}
	
	@Override
	public void deleteByPcNote(String Id) {
		priceConfigureDao.deleteById(Id);
	}
   
	public boolean judgeUnique(G_PriceConfigure model) {
		return priceConfigureDao.findAll(Specifications.where(false, (c) -> {
			c.eq("pcSort", model.getPcSort());
		})).size() <= 0;
	}

	@Override
	public List<Map<String, Object>> findByType(String type) {
		return priceConfigureSqlDao.findByType(type);
	}
}
