package com.tengzhi.business.sc.capacity.specification.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sc.capacity.specification.model.MScGgcl;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @Auther: huangbiao
 * @Date: 2020/8/12 0012 14:36
 * @Description:
 */
@SuppressWarnings("SqlResolve")
public interface MScGgclDao extends BasedaoRepository<MScGgcl,String> {

    @Query(value="select * from sys_param where parent_id = 'SCCJ'",nativeQuery=true)
    List<Map<Object,String>> findCjType();

    @Query(value="select * from sys_param where parent_id = 'CNLX'",nativeQuery=true)
    List<Map<Object,String>> findClType();

}
