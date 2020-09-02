package com.tengzhi.business.sale.saleProduct.saleContract.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContractDetailed;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EXsContractDetailedDao extends BasedaoRepository<EXsContractDetailed, String> {

	@Modifying
	@Query(value =" delete from EXsContractDetailed where htNo = :htNo ")
	void deleteByHtNo(@Param(value = "htNo") String htNo);
	
	@Modifying
	@Query(value =" update EXsContractDetailed set htFlag=:htFlag  where htMo = :htMo ")
	void updateFlag(@Param(value = "htMo") String htNo,@Param(value = "htFlag") String htFlag);
	
	@Query(value=" select  ht_no,ht_flag from e_xs_contract_detailed where ht_mo= :htMo  ",nativeQuery = true)
	List<Map<String,Object>> getFlag(@Param("htMo")String htMo);

	@Modifying
	@Query(value=" update e_xs_contract set ht_flag=?2  where ht_no = ?1 ",nativeQuery = true)
	void getNo(String htNo,String ht_flag);

	//登记，核准
	
	@Modifying
	@Query(value =" update EXsContractDetailed set htJq=:htJq  where htMo = :htMo ")
	void updateHtjq(@Param(value = "htMo") String htNo,@Param(value = "htJq") Date htJq);

	@Modifying
	@Query(value =" update e_xs_contract_detailed set ht_jq = ht_yjq,ht_Reply_Man=null where ht_No = :htNo and ht_Yjq is not null ",nativeQuery = true)
	int cancelReply(@Param(value = "htNo") String htNo);
}
