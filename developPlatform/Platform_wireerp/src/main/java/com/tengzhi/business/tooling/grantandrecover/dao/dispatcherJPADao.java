package com.tengzhi.business.tooling.grantandrecover.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.tooling.grantandrecover.pojo.MGzMjrecord;
import com.tengzhi.business.tooling.grantandrecover.pojo.MGzMjrecordPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @author 鱼游浅水
 * @create 2020-06-28
 */
public interface dispatcherJPADao extends BasedaoRepository<MGzMjrecord, MGzMjrecordPK> {
    @Transactional
    @Modifying
    @Query("update MGzMjrecord set mjFlag='确认',mjSl=?4 where mjNote=?1 and mjCode=?2 and mjAct=?3 ")
    int modifyStatus(String Note,String code,String act,Integer sj);

    @Transactional
    @Modifying
    @Query("delete from MGzMjrecord where mjNote=?1")
    int deleteByNote(String Note);

}
