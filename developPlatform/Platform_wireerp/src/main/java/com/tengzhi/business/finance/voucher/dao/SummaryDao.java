package com.tengzhi.business.finance.voucher.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;

import com.tengzhi.business.finance.voucher.model.Summary;

public interface SummaryDao extends BasedaoRepository<Summary,Long> {
	Summary findByfid(Long id);
	void deleteByfid(Long id);
	
}
