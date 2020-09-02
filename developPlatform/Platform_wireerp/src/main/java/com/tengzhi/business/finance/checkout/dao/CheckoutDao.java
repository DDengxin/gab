package com.tengzhi.business.finance.checkout.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.checkout.model.CheckOut;
import com.tengzhi.business.system.params.model.SysParams;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CheckoutDao extends BasedaoRepository<CheckOut,String> {

    @Query("from SysParams where parentId=?1 order by creationTime asc")
    List<SysParams> findAllParams(String id);

    @Modifying
    @Query(value ="delete from e_pz_jylist_jy where xm_code=?1 ", nativeQuery = true)
    int deleteByXm(String xm);


    @Modifying
    @Query(value ="update e_pz_jylist_jy set xm_value=:pval where xm_code=:xm and xm_tvalue=:pid", nativeQuery = true)
    void update(@Param("xm") String xm, @Param("pid") String pid, @Param("pval") String pval);


    @Query("from SysParams where paramId=?1 ")
    SysParams findParams(String id);

}
