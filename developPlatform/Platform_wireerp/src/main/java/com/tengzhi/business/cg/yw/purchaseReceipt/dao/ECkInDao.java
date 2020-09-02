package com.tengzhi.business.cg.yw.purchaseReceipt.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn.ECkIn_PK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;


public interface ECkInDao extends BasedaoRepository<ECkIn,ECkIn_PK>{

	 @Modifying
	 @Query(value =" delete from ECkIn where inNote = :inNote ")
	 void deleteByInNote(@Param(value = "inNote") String inNote);
	
	 @Query(value =" select  in_Flag from E_Ck_In where in_Note = :inNote  limit 1 ", nativeQuery = true)
	 String getFlag(@Param(value = "inNote") String inNote);
	 
	 @Modifying
	 @Query(value =" update ECkIn set inFlag=:flag  where inNote = :inNote ")
	 int setFlag(@Param(value = "inNote") String inNote,@Param(value = "flag") String flag);

	 @Query(value =" select count(*) cn from E_Ck_In   where in_pack = :inPack and in_act not in ('0102') ", nativeQuery = true)
	 int getAddInBack(@Param(value = "inPack") String inPack);
	 
	 @Modifying
	 @Query(value =" update ECkIn set inFlag='库审',in_man=:inMan,in_date=now()  where inNote = :inNote ")
	 int rk(@Param(value = "inNote") String inNote,@Param(value = "inMan") String inMan);
	 
	 @Modifying
	 @Query(value =" update ECkIn set inFlag='结算',in_man=null,in_date=null  where inNote = :inNote ")
	 int qxrk(@Param(value = "inNote") String inNote);
	 
	 
	 @Modifying
	 @Query(value =" update ECkIn set inFlag='库审',inKw= :inKw,in_date=now(),inMan=:inMan  where inPack = :inPack ")
	 int rkpack(@Param(value = "inKw") String inKw,@Param(value = "inMan") String inMan,@Param(value = "inPack") String inPack);

	/**
	 * 更新原包装号的数量
	 * @return
	 */
	@Modifying
	@Query(value =" update ECkIn set inSl=:inSl where inPack = :inPack   and inContract = :inContract ")
	int updateSplitData(@Param(value = "inSl") BigDecimal inSl, @Param(value = "inPack") String inPack,
						 @Param(value = "inContract") String inContract);

	/**
	 * 修改大包装号为小包装号
	 * @return
	 */
	@Modifying
	@Query(value =" update ECkIn set inBpack=:inPack where inPack = :inPack   and inBpack = :inBpack ")
	int updateBigPack(@Param(value = "inBpack") String inBpack, @Param(value = "inPack") String inPack);

	/**
	 * 修改大包装号为小包装号(全部拆除)
	 * @return
	 */
	@Modifying
	@Query(value =" update ECkIn set inBpack=inPack where inBpack = :inBpack   and inCode = :inCode ")
	int updateAllBigPack( @Param(value = "inBpack") String inBpack,@Param(value = "inCode") String inCode);

	/**
	 * 更新大包装号(装箱)
	 * @return
	 */
	@Modifying
	@Query(value =" update ECkIn set inBpack=:inPack where inBpack = :inBpack   and inPack = :inPack ")
	int updateFillBigPack(@Param(value = "inPack") String inPack, @Param(value = "inBpack") String inBpack);

	/**
	 * 更新大包装号的库位
	 * @return
	 */
	@Modifying
	@Query(value =" update ECkIn set inKw=:inKw where inBpack = :inBpack")
	int updateKwBigPack(@Param(value = "inKw") String inKw, @Param(value = "inBpack") String inBpack);

	/**
	 * 更新小包装号的库位
	 * @return
	 */
	@Modifying
	@Query(value =" update ECkIn set inKw=:inKw where inPack = :inPack")
	int updateKwSmallPack(@Param(value = "inKw") String inKw, @Param(value = "inPack") String inPack);

	/**
	 * 更新拆分数量
	 * @param inKw
	 * @param inPack
	 * @return
	 */
	@Modifying
	@Query(value =" update ECkIn set inSl=:inSl where inPack = :inPack and inSl>0")
	int updateSplit(@Param(value = "inSl") String inSl, @Param(value = "inPack") String inPack);
}
