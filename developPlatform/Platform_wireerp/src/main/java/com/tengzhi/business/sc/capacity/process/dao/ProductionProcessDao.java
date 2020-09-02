package com.tengzhi.business.sc.capacity.process.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sc.capacity.specification.model.MScGgcl;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @Auther: huangbiao
 * @Date: 2020/8/13 0013 19:51
 * @Description:
 */
@SuppressWarnings("SqlResolve")
public interface ProductionProcessDao extends BasedaoRepository<MScGgcl,String> {

}
