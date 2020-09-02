package com.tengzhi.business.platform.enroll.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.enroll.model.G_Supply;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupplyDao extends BasedaoRepository<G_Supply, String> {

	@Query(value = "SELECT \"code\" from  sys_sms_log   where type='GABREGISTER'   and    phone=:mobile  and   send_time   between (now() - interval '2000 Mins' ) and now()    ORDER BY  send_time desc  limit 1", nativeQuery = true)
	String GetRandomCode(@Param("mobile") String mobile);

	@Query(value = "SELECT user_id  from sys_user  where supply_id =:supplyid ", nativeQuery = true)
	String GetUserid(@Param("supplyid") String supplyid);

	@Query(value = "select  f_gab_getname ( 'ERPUSERTOGUSER', :userId, NULL,:corpId )", nativeQuery = true)
	String getGuserInfoId(@Param("userId") String userId, @Param("corpId") String corpId);


	@Query(value = "SELECT  status  from  g_supply   where  supply_note =:supply_note   ", nativeQuery = true)
	String getSupplyStatus(@Param("supply_note") String supply_note);

	@Modifying
	@Query(value = " DELETE FROM g_supply  WHERE supply_note = :supply_note", nativeQuery = true)
	int deleteBySupplyNoteTrue(@Param("supply_note") String supply_note);
	@Modifying
	@Query(value = "update    g_supply   set status = :status   where  supply_note =:supplyNote   ", nativeQuery = true)
	int   updateStatus(  @Param("status") String status,@Param("supplyNote") String supplyNote);

}
