package com.tengzhi.business.production.productionSite.productPacking.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.production.productionSite.productPacking.model.MScPrintPackTemp;

public interface MScPrintPackTempDao extends BasedaoRepository<MScPrintPackTemp,String > {

	
	
	@Modifying
	@Query(value=" insert into m_sc_print_pack_temp(wl_code,wl_name,wl_size,wl_sl,wl_standard,wl_pack,wl_bpack,wl_scrq,data_rq,data_man)  select a.code,tname,tsize,tsl,ht_standard,bgc,pack_no,a.rq,now(),:userId from m_sc_gclist a,m_sc_scrw b,e_xs_contract_detailed c where a.sc_mo=b.sc_mo and b.ht_mo=c.ht_mo  and a.pack_no = :bpack ",nativeQuery = true) 
	int insertPrintByBpack(@Param(value = "userId") String userId,@Param(value = "bpack") String bpack);
	
	@Modifying
	@Query(value=" insert into m_sc_print_pack_temp(wl_code,wl_name,wl_size,wl_sl,wl_standard,wl_pack,wl_bpack,wl_scrq,data_rq,data_man)  select a.code,tname,tsize,tsl,ht_standard,bgc,pack_no,a.rq,now(),:userId from m_sc_gclist a,m_sc_scrw b,e_xs_contract_detailed c where a.sc_mo=b.sc_mo and b.ht_mo=c.ht_mo  and a.bgc = :pack ",nativeQuery = true) 
	int insertPrintByPack(@Param(value = "userId") String userId,@Param(value = "pack") String pack);
	
	@Modifying
	@Query(value =" delete from MScPrintPackTemp ")
	int deleteAllPacks();
	
}
