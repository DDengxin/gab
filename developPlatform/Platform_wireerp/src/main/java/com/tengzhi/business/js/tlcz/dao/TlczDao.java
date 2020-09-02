package com.tengzhi.business.js.tlcz.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.js.tlcz.model.EJsTlcz;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author: czf
 * @Date:2020-08-20 9:18
 */
public interface TlczDao extends BasedaoRepository<EJsTlcz,String> {


    @Modifying
    @Query(value="delete from EJsTlcz  where code = :code")
    void deleteByCode(@Param(value="code")String code);
}
