package com.tengzhi.app.ck.dao;


import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.system.params.model.SysParams;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CKDao extends BasedaoRepository<SysParams, String>{

    /**
     * 查询成品出库的出库方式
     * @return
     */
    @Query(value = "  from SysParams where paramId=:paramId and paramType= :paramType and  paramValue3 = :paramValue3")
    List<SysParams>
    selectWarehouseWay(@Param("paramId") String paramId,@Param("paramType") String paramType,@Param("paramValue3") String paramValue3);




}
