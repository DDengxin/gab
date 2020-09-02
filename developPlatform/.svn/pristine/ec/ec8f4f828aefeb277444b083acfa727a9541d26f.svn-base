package com.tengzhi.business.production.productionSite.siteTask.dao;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.production.productionSite.siteTask.model.MScGclistSl;
import com.tengzhi.business.production.productionSite.siteTask.model.MScGclistSl.MScGclistSl_PK;



public interface MScGclistSlDao extends BasedaoRepository<MScGclistSl,MScGclistSl_PK> {
	
	@Modifying
	@Query(value =" update MScGclistSl set bgc=:bgc  where sgc = :sgc and bgc='N' ")
	int updateBgc(@Param(value = "sgc")String sgc,@Param(value = "bgc") String bgc);

	@Modifying
	@Query(value =" update MScGclistSl set bgc=:bgc  where scrwNo = :scMo and bgcGx = :gx and bgc='N' ")
	int updateBgcAll(@Param(value = "scMo")String scMo,@Param(value = "gx")String gx,@Param(value = "bgc") String bgc);
	
	@Modifying
	@Query(value =" update MScGclistSl set sgcSl=:xlyl,doDate=now()  where sgc = :sgc and bgc='N' ")
	int updateTslAndTldate(@Param(value = "sgc") String sgc,@Param(value = "xlyl") BigDecimal xlyl);

	@Modifying
	@Query(value =" insert into m_sc_gclist_sl (bgc, sgc, sgc_js, sgc_sl, sgc_zl, bgc_gx, bgc_ct, scrw_no, do_man, do_date, wl_type, wl_code, wl_name, wl_size, wl_ph, gc_cl, vnote, data_corp) select :bgc, sgc, sgc_js, :tsl, sgc_zl, bgc_gx, bgc_ct, scrw_no,:userId, now(), wl_type, wl_code, wl_name, wl_size, wl_ph, gc_cl, vnote, data_corp from m_sc_gclist_sl where sgc=:sgc and bgc='N'  ",nativeQuery = true)
	int insertTl(@Param(value = "bgc") String bgc, @Param(value = "tsl") BigDecimal tsl,@Param(value = "userId") String userId,@Param(value = "sgc") String sgc);

	 
	@Modifying
	@Query(value =" INSERT INTO m_sc_gclist_sl(bgc, sgc, sgc_js, sgc_sl, sgc_zl, bgc_gx, bgc_ct, scrw_no, do_man, do_date, wl_type, wl_code, wl_name, wl_size, wl_ph, gc_cl, vnote, data_corp)  select  :bgc,bgc,js,sl,zl,gx,tct,scitem,czman,now(),wl_type,wlcode,wl_name, wl_size, wlph,'CL',vnote,data_corp from v_sc_gclistview where bgc=:sgc  ",nativeQuery = true)
	int insertHptl(@Param(value = "bgc") String bgc,@Param(value = "sgc") String sgc);

	@Modifying
	@Query(value =" update MScGclistSl set bgc=:bgc  where scrwNo = :scMo and bgcGx = :gx and bgc='N' and wlCode=:wlCode ")
	int updateBgcByCode(@Param(value = "bgc")String bgc,@Param(value = "scMo") String scMo,@Param(value = "gx") String gx,@Param(value = "wlCode") String wlCode);
}
