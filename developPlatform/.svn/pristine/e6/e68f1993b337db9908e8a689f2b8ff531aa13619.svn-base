package com.tengzhi.business.finance.capitalManager.capitalCheck.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.capitalManager.capitalCheck.model.EFVoucherCapitalCheck;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface CapitalCheckDao extends BasedaoRepository<EFVoucherCapitalCheck,String> {


    @Modifying
    @Query(value =" insert into e_f_voucher_capital_check(fcardid,data_corp,ftypeid,fnumber,fname,fdeptid,note,createtime,man,flag,rq,ftype_number,ftype_name)  select  a.fcardid,:dataCorp,a.ftypeid,a.fnumber,a.fname,a.fdeptid,:note,to_timestamp(:createtime,'yyyy-MM-dd HH24:mi:ss'),:man,'',to_date(:rq,'yyyy-MM-dd'),b.fnumber,b.fname   from e_f_voucher_capital_register a,e_f_voucher_capitaltype b  where a.data_corp=b.data_corp and a.ftypeid=b.fid and a.data_corp=:dataCorp",nativeQuery = true)
    int jcOption(@Param(value = "dataCorp") String dataCorp,@Param(value = "note") String note,@Param(value = "createtime") String createtime,@Param(value = "man") String man,@Param(value = "rq") String rq);

    @Query(value =" select distinct rq from EFVoucherCapitalCheck where note = :note ")
    String getRq(@Param(value = "note") String note);


}
