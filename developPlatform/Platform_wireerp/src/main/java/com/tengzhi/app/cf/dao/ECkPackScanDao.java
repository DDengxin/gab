package com.tengzhi.app.cf.dao;


import com.tengzhi.app.cf.model.ECkPackScan;
import com.tengzhi.app.ck.model.EckOutLsFlag;
import com.tengzhi.base.jpa.dao.BasedaoRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ECkPackScanDao extends BasedaoRepository<ECkPackScan, String>{

    /**
     * 小包装号删除
     * @return
     */
    @Modifying
    @Query(value =" delete FROM ECkPackScan  where pack = :pack ")
    int deletePack(@Param(value = "pack") String pack);

    /**
     * 大包装号删除
     * @return
     */
    @Modifying
    @Query(value =" delete FROM ECkPackScan  where bpack = :bpack ")
    int deleteBPack(@Param(value = "bpack") String bpack);
    /**
     * 装箱扫描更新状态
     * @param bpack
     * @return
     */
    @Modifying
    @Query(value =" update  ECkPackScan  set flag = :newflag where bpack = :bpack and flag=:oldflag ")
    int updateFlag(@Param(value = "newflag") String newflag,@Param(value = "bpack") String bpack,@Param(value = "oldflag") String oldflag);
    

    @Modifying
    @Query(value =" update  ECkPackScan  set flag = :newflag where lib= :lib and dataAct = :dataAct ")
    int updateKw(@Param(value = "newflag") String newflag,@Param(value = "lib") String lib,@Param(value = "dataAct") String dataAct);
}
