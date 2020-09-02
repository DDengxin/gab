package com.tengzhi.business.quality.qualityDetection.productDetection.dao;

import java.util.Collection;
import java.util.List;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.quality.qualityDetection.productDetection.model.EPzJylistJc;
import com.tengzhi.business.quality.qualityDetection.productDetection.model.EPzJylistJc.EPzJylistJc_PK;

public interface ProductDetectionDao extends BasedaoRepository<EPzJylistJc, EPzJylistJc_PK> {

	
	/**
     * 通过批次号查询所有记录
     * @param pch
     * @return
     */
    List<EPzJylistJc> findByPch(String pch);

    List<EPzJylistJc> findByPchIn(Collection<String> pchList);
}
