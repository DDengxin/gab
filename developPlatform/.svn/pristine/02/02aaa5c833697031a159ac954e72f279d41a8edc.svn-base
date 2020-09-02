package com.tengzhi.business.sc.da.leave.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sc.da.leave.model.EHrJbqj;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @Auther: huangbiao
 * @Date: 2020/8/11 0011 14:23
 * @Description:
 */
@SuppressWarnings("SqlResolve")
public interface EHrJbqjDao extends BasedaoRepository<EHrJbqj, String> {

    @Modifying
    @Transactional
    @Query(value="update e_hr_jbqj set qj_flag = ?1 where jbqj_id = ?2",nativeQuery=true)
    int updateFlag(String flag,String jbqjId);

}
