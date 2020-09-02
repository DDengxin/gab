package com.tengzhi.business.finance.voucher.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.voucher.dao.SummaryDao;
import com.tengzhi.business.finance.voucher.model.Summary;
import com.tengzhi.business.finance.voucher.service.SummaryService;
import com.tengzhi.business.js.product.model.Jscpcode;

@Service
public class SummaryServiceImpl implements SummaryService {
	@Autowired
	SummaryDao summaryDao;

	@Override
	public BasePage<Summary> findAll(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		return summaryDao.QueryPageList(baseDto, Specifications.where((c) -> {
			c.eq("dataCorp",securityUser.getCorpId());
		}));

	}

	@Override
	public Summary findById(Long Id) {
		return summaryDao.findById(Id).orElse(null);
	}

	@Override
	public Summary save(Summary summary) throws Exception {
		SessionUser sessionUser = SessionUser.SessionUser();
        if (judgeUniqueset(summary)){
        	summary.setDataCorp(sessionUser.getCorpId());
            return summaryDao.save(summary);
        } else {
            throw new Exception("编码已存在");
        }
	}
	@Override
	public List<Summary> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Summary summary) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean judgeUniqueset(Summary summary) {
		if (summary.getFid()==null) {
			return true;
		}else{
			Summary byfid = summaryDao.findByfid(summary.getFid());
			if (byfid==null) {
				return true;
			}else{
				return false;
			}
		}
	}

	@Override
	public boolean judgeUniqueother(Summary summary) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Result deleteById(Long Id) {
		summaryDao.deleteByfid(Id);
		return Result.resultOkMsg("删除成功");
	}

}
