package com.tengzhi.business.finance.constituent.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.constituent.model.EPzJylistLh;
import com.tengzhi.business.system.params.model.SysParams;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface EPzJylistLhDao extends BasedaoRepository<EPzJylistLh, EPzJylistLh.EPzJylistLh_PK> {

    @Query("from SysParams where parentId=?1 order by creationTime asc")
    List<SysParams> findAllParams(String id);

    @Modifying
    @Query(value ="delete from e_pz_jylist_lh where pch= ?1 ", nativeQuery = true)
    int deleteByPch(String pch);


    /**
     * 通过批次号查询所有记录
     * @param pch
     * @return
     */
    List<EPzJylistLh> findByPch(String pch);

    List<EPzJylistLh> findByPchIn(Collection<String> pchList);

}
