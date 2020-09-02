package com.tengzhi.business.js.cpbom.dao;

import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.js.cpbom.model.EJsCpcode;

public interface CpBomDao extends BasedaoRepository<EJsCpcode, String> {
	
	 @Query(value =" select  * from e_js_cpcode_bom where cpcode_bom = :cpcodeBom and cpcode_stype = '1' limit 1 ", nativeQuery = true)
	 EJsCpcode findBycpId(@Param(value = "cpcodeBom") String cpcodeBom);
	 
	 @Query(value =" select cpcode_bom from e_js_cpcode_bom where cpcode_id = :cpcodeId  limit 1 ", nativeQuery = true)
	 String findBom(@Param(value = "cpcodeId") String cpcodeId);

	 @Query(value =" select f_getname('CPCODENAME', :cpcodeId, '', :dataCorp) \"cpcodeName\",cpcode_size,f_get_param_name('计量单位',cpcode_bz,data_corp,'技术','false') \"bzName\" from e_js_cpcode where cpcode_id = :cpcodeId ", nativeQuery = true)
	 Map<String,Object> getCpcode(@Param(value = "cpcodeId") String cpcodeId,@Param(value = "dataCorp") String dataCorp);
	
	 @Modifying
	 @Query(value =" delete from EJsCpcode where cpcodeId = :cpcodeId and cpcodeUid = :cpcodeUid and cpcodeBom = :cpcodeBom ")
	 void deleteById(@Param(value = "cpcodeId") String cpcodeId,@Param(value = "cpcodeUid") String cpcodeUid,@Param(value = "cpcodeBom") String cpcodeBom);

	 @Modifying
	 @Query(value =" delete from EJsCpcode where cpcodeBom = :cpcodeBom  and cpcodeStype > :cpcodeStype ")
	 void deleteByChildren(@Param(value = "cpcodeBom") String cpcodeBom,@Param(value = "cpcodeStype") String cpcodeStype);
	 
	 @Modifying
	 @Query(value =" delete from EJsCpcode where cpcodeBom = :cpcodeBom  and cpcodeStype = :cpcodeStype and cpcodeId = :cpcodeId ")
	 void deleteBySelf(@Param(value = "cpcodeBom") String cpcodeBom,@Param(value = "cpcodeStype") String cpcodeStype,@Param(value = "cpcodeId") String cpcodeId);
}
