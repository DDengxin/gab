package com.tengzhi.business.platform.shopping.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.shopping.model.G_EXsContract;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractDao extends BasedaoRepository<G_EXsContract,String> {
	
}
