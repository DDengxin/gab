package com.tengzhi.business.finance.voucher.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.voucher.model.EFVoucher;
import com.tengzhi.business.finance.voucher.model.EFVoucherAuxiliaryitems;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface VouchDao extends BasedaoRepository<EFVoucher, BigInteger> {
    @Query(value = "select f_getvoucherid()", nativeQuery = true)
    BigInteger getVoucherid();
}
