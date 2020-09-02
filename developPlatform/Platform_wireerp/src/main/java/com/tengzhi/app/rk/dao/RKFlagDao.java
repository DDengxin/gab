package com.tengzhi.app.rk.dao;

import com.tengzhi.app.ck.model.EckOutLsFlag;
import com.tengzhi.app.rk.model.ECkInLsFlag;
import com.tengzhi.base.jpa.dao.BasedaoRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RKFlagDao extends BasedaoRepository<ECkInLsFlag, String> {
    /**
     * 出库按钮把出库状态改为库审
     * @return
     */
    @Modifying
    @Query(value =" update ECkInLsFlag set inFlag=:inFlag  where inNote = :inNote   and dataAct = :dataAct ")
    int updatePackFlag(@Param(value = "inFlag") String inFlag, @Param(value = "inNote") String inNote, @Param(value = "dataAct") String dataAct);

    /**
     * 出库按钮把出库状态改为库审(入库扫描页面)
     * @return
     */
    @Modifying
    @Query(value =" update ECkInLsFlag set inFlag=:inFlag  where inLib = :inLib   and inAct = :inAct  ")
    int updatePackFlagSM(@Param(value = "inFlag") String inFlag, @Param(value = "inLib") String inLib,
                         @Param(value = "inAct") String inAct);
    
    @Modifying
    @Query(value =" update ECkInLsFlag set inFlag=:inFlag  where inLib = :inLib   and inAct = :inAct and inKw= :inKw ")
    int updatePackFlagSMKW(@Param(value = "inFlag") String inFlag, @Param(value = "inLib") String inLib,
                         @Param(value = "inAct") String inAct,@Param(value = "inKw") String inKw);

    /**
     * 小包装号删除
     * @return
     */
    @Modifying
    @Query(value =" delete FROM ECkInLsFlag  where inPack = :inPack ")
    int deletePack(@Param(value = "inPack") String inPack);

    /**
     * 大包装号删除
     * @return
     */
    @Modifying
    @Query(value =" delete FROM ECkInLsFlag  where inBpack = :inBpack ")
    int deleteBPack(@Param(value = "inBpack") String inBpack);
    
    
    @Modifying
	@Query(value =" update ECkInLsFlag set inFlag='库审',inDate=now(),inMan=:inMan  where inPack = :inPack and dataAct= :dataAct ")
	int rkpackls(@Param(value = "inPack") String inPack,@Param(value = "inMan") String inMan,@Param(value = "dataAct") String dataAct);
}
