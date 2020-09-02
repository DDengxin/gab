package com.tengzhi.business.tooling.toolingstore.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.mesGz.gzck.vo.EckOut;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * @author 鱼游浅水
 * @create 2020-06-28
 */
public interface ToolingStoreJPADao extends BasedaoRepository<EckOut,EckOut.ECkOut_PK> {

    @Transactional
    @Modifying
    @Query("update EckOut set outFlag='结算',outSl=?3 where outNote=?1 and outPack=?2 ")
    int modifyStatus(String Note,String act, BigDecimal sj);

    @Transactional
    @Modifying
    @Query("delete from EckOut where outNote=?1")
    int deleteByNote(String Note);

    @Transactional
    @Modifying
    @Query("delete from MGzMjrecord where mjNote=?1")
    int deleteByT(String Note);
    
    
    @Transactional
    @Modifying
    @Query("update EckOut set outFlag='结算' where outNote=?1  ")
    int settle(String Note);

}
