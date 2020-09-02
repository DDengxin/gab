package com.tengzhi.business.system.fileauth.dao;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.fileauth.model.FileAuth;

public interface FileAuthDao extends BasedaoRepository<FileAuth, String>{
		/**
		 *  授权保存方法
		 * 
		 * */
	  @Modifying
	     @Query(value = "INSERT INTO e_xt_archive_r_right(ar_note,link_dept_id,link_user_id,gen_user_id,gen_time)VALUES(:ar_note,:link_dept,:link_user_id,:gen_user_id,:gen_time);",nativeQuery = true)
	     int FileAuthAdd(@Param("ar_note") String arnote, @Param("link_dept") String linkdept, @Param("link_user_id") String linkuserid,@Param("gen_user_id") String genuserid,@Param("gen_time") Timestamp gentime);
	  
	  /**
	   * 
	   * 授权删除方法
	   * */
	  @Modifying
	     @Query(value = "DELETE FROM e_xt_archive_r_right where ar_note = :ar_note ",nativeQuery = true)
	     int FileAuthDelete(@Param("ar_note") String arnote);
}
