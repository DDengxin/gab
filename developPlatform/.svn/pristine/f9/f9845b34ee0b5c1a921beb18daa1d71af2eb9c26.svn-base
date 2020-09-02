package com.tengzhi.business.production.productionSite.siteTask.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.production.productionSite.siteTask.model.MScGclist;




public interface MScGclistDao extends BasedaoRepository<MScGclist,String> {

	@Modifying
	@Query(value =" INSERT INTO m_sc_gclist(rq, bgc, sc_mo, code, gy, gx, tname, tsize, tph, tts, tjs, tsl, tzl, tcj, tdept, tbc, do_man, do_date, gc_flag, bgc_tl, bgc_wc, bgc_sc, bgc_cl_price, data_corp, bgc_vnote, wl_type, tct, gc_cl, gc_qw) "
			+ " select to_date(to_char(now(), 'YYYY-MM-DD'), 'YYYY-MM-DD'),:bgc,scitem,wlcode,gy,gx,wl_name,wl_size, wlph,ylpch,js,:sl,zl,tcj,tdept,tbc,czman,now(),flag,now(),now(),0,clprice,data_corp,vnote,wl_type,tct,'CL',gc_qw from v_sc_gclistview where bgc=:sgc ",nativeQuery = true)
	int insertHpxl(@Param(value = "bgc") String bgc, @Param(value = "sl") BigDecimal sl,@Param(value = "sgc") String sgc);

	
}
