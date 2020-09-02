package com.tengzhi.business.platform.shopping.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.shopping.model.GShopAddr;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddrDao extends BasedaoRepository<GShopAddr, String> {

	@Modifying
	@Query(value = "update e_cw_customer_address set is_default = false where address_note != :addressNote", nativeQuery = true)
	int setAllFalse(@Param(value = "addressNote") String addressNote);

	@Modifying
	@Query(value = "update e_cw_customer_address set is_default = true where address_note = :addressNote", nativeQuery = true)
	int setTrue(@Param(value = "addressNote") String addressNote);

	@Query(value = "select address from e_cw_customer_address where address_note = :addressNote", nativeQuery = true)
	String findAddrById(@Param(value = "addressNote") String addressNote);


	/**
	 * 此处因账套出问题，导致数据出问题，先注释待修改
	 */
//	@Modifying
//	@Query(value = "delete from e_cw_customer_address  where address_note = :addressNote and data_corp =:userId", nativeQuery = true)
//	void deleteById(@Param(value = "addressNote") String addressNote,@Param(value = "userId") String userId);
	@Modifying
	@Query(value = "delete from e_cw_customer_address  where address_note = :addressNote", nativeQuery = true)
	void deleteById(@Param(value = "addressNote") String addressNote);

}
