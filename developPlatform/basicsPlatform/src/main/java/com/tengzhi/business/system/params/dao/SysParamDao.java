package com.tengzhi.business.system.params.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.model.SysParams.SysParams_PK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysParamDao extends BasedaoRepository<SysParams, SysParams_PK> {

    List<SysParams> findByParentIdAndParamXtype(@Param("parentId") String parentId,@Param("paramXtype") String paramXtype);

    @Modifying
    @Query("delete from  SysParams where paramId=:paramId and paramType=:paramType")
    void delLogByOrgId(@Param("paramId") String paramId, @Param("paramType") String paramType);

    @Modifying
    @Query("update SysParams set paramStatus='true' where paramId = :paramId")
    void delLogByOrgId1(@Param("paramId") String paramId);

    @Modifying
    @Query("update SysParams set paramStatus='false' where paramId = :paramId")
    void delLogByOrgId2(@Param("paramId") String paramId);

    @Query(value = " from SysParams where parentId = :parentId  and  paramValue1 is not null and paramValue1 <> '' ")
    List<SysParams> findComboboxParams(@Param("parentId") String parentId);

    @Query(value = " select paramName from SysParams where paramId= :parentId and paramType= :paramType")
    String findParentName(@Param("parentId") String parentId, @Param("paramType") String paramType);


    @Modifying
    @Query("update SysParams set paramStatus= :status where paramId = :paramId  and paramType= :paramType ")
    void updateStatus(@Param("paramId") String paramId, @Param("paramType") String paramType, @Param("status") String status);

    //@Query(value = " from SysParams where paramType = :paramType and paramId = :paramId ")
    //SysParams findByParamTypeAndParamId(@Param("paramType") String parentId, @Param("paramId") String paramId);

    @Query(value = " select param_id from Sys_param where param_type='仓库收发' and param_name=:paramName and param_Value3 = :paramValue3", nativeQuery = true)
    String getParamId(@Param("paramValue3") String paramValue3, @Param("paramName") String paramName);

    @Query(value = " select CAST(param_value  AS VARCHAR) from Sys_param where  param_type= :paramType and param_id= :paramId limit 1 ", nativeQuery = true)
    String getParamValue(@Param("paramType") String paramType, @Param("paramId") String paramId);

    @Query(value = " select param_value1  from Sys_param where param_type = :paramType and param_id=:paramId limit 1 ", nativeQuery = true)
    String getParamValue1(@Param("paramType") String paramType, @Param("paramId") String paramId);

    @Query(value = " select param_value2  from Sys_param where param_type = :paramType and param_id=:paramId limit 1 ", nativeQuery = true)
    String getParamValue2(@Param("paramType") String paramType, @Param("paramId") String paramId);

    @Query(value = " select param_value3  from Sys_param where param_type = :paramType and param_id=:paramId limit 1 ", nativeQuery = true)
    String getParamValue3(@Param("paramType") String paramType, @Param("paramId") String paramId);


    @Query(value = " select param_value1  from Sys_param where parent_id = :parentId and param_id=:paramId limit 1 ", nativeQuery = true)
    String getKeyParamValue1(@Param("parentId") String paramType, @Param("paramId") String paramId);


    @Query(value = " from SysParams where paramType = :paramType and paramId = :paramId ")
    SysParams findByParamTypeAndParamId(@Param("paramType") String paramType, @Param("paramId") String paramId);

    SysParams findByParamId(String ParameterId);

    @Modifying
    @Query("delete from  SysParams where paramId=:paramId")
    void delLogByOrgId(@Param("paramId") String paramId);

}
