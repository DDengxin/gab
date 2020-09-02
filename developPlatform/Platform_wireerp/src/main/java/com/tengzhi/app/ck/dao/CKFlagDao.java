package com.tengzhi.app.ck.dao;


import com.tengzhi.app.ck.model.EckOutLsFlag;
import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.params.model.SysParams;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CKFlagDao extends BasedaoRepository<EckOutLsFlag, String>{

    /**
     * 出库按钮把出库的确认状态改为出库
     * @return
     */
    @Modifying
    @Query(value =" update EckOutLsFlag set outFlag=:outFlag  where outNote = :outNote and dataAct =:dataAct and outFlag='登记' ")
    int updateFlag(@Param(value = "outFlag") String outFlag,@Param(value = "outNote") String outNote,@Param(value = "dataAct") String dataAct);


    /**
     * 出库按钮把出库状态改为库审
     * @return
     */
    @Modifying
    @Query(value =" update EckOutLsFlag set outFlag=:outFlag  where outLib = :outLib   and outAct = :outAct and dataAct = :dataAct and outNote=:outNote")
    int updatePackFlag(@Param(value = "outFlag") String outFlag,@Param(value = "outLib") String outLib,
                      @Param(value = "outAct") String outAct,@Param(value = "dataAct") String dataAct,@Param(value = "outNote") String outNote);

    /**
     * 小包装号删除
     * @return
     */
    @Modifying
    @Query(value =" delete FROM EckOutLsFlag  where outPack = :outPack and dataAct =:dataAct and outFlag='登记' ")
    int deletePack(@Param(value = "outPack") String outPack,@Param(value = "dataAct") String dataAct);

    /**
     * 大包装号删除
     * @return
     */
    @Modifying
    @Query(value =" delete FROM EckOutLsFlag  where outBpack = :outBpack and dataAct =:dataAct and outFlag='登记' ")
    int deleteBPack(@Param(value = "outBpack") String outBpack,@Param(value = "dataAct") String dataAct);
    
    @Modifying
    @Query(value =" delete FROM EckOutLsFlag  where outNote = :outNote and dataAct =:dataAct and outFlag='登记' ")
    int deleteByNote(@Param(value = "outNote") String outNote,@Param(value = "dataAct") String dataAct);

}
