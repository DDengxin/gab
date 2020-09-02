package com.tengzhi.business.platform.manage.dao;


import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.manage.model.G_PriceConfigure;

public interface PriceConfigureDao  extends BasedaoRepository<G_PriceConfigure,String> {
	G_PriceConfigure findByPcNote(String articleId);

	
	
}
