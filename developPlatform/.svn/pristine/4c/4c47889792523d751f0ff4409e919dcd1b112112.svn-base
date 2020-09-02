package com.tengzhi.business.xt.transaction.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.upload.model.SysUpload;
import com.tengzhi.business.xt.transaction.model.Transaction;

public interface TransactionDao extends BasedaoRepository<Transaction,String>{

	@Query(value ="select f_getname('GETUSERNAME', :swMan, '', :dataCorp) \"manName\" ",nativeQuery = true)
	String getDeptName(@Param(value="swMan")String swMan,@Param(value="dataCorp")String dataCorp);
	
	@Query(value =" from Transaction where swNote = :swNote")
	Transaction find(@Param(value="swNote")String swNote);
	
	@Modifying
	@Query(value =" delete from Transaction where swNote = :swNote ")
	void deleteData(@Param(value="swNote")String swNote);
	
	@Query(value = " select sw_file from e_xt_swsq where sw_note = :swNote ",nativeQuery = true)
	String getFileId(@Param(value="swNote") String swNote);
	
	@Query(value = " from SysUpload where line_primary = :line_primary")
	List<SysUpload> getUuid(@Param(value="line_primary") String line_primary);
	
	@Modifying
    @Query(value = "DELETE FROM com_file where uuid=:uuid",nativeQuery = true)
    int deleteByUuid(@Param(value="uuid") String uuid);
	
}
