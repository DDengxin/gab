package com.tengzhi.business.finance.voucher.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.voucher.model.EFVoucherAuxiliaryitems;
import com.tengzhi.business.finance.voucher.model.EFVoucherentry;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface VouchEntryDao extends BasedaoRepository<EFVoucherentry, BigInteger> {
      @Query(value = "select f_getvouchentryid()", nativeQuery = true)
      BigInteger getEntryid();

}
