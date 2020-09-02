package com.tengzhi.business.finance.voucher.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.voucher.model.EFVoucher;
import com.tengzhi.business.finance.voucher.model.EFVoucherBscategory;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface BusinessCategoryDao extends BasedaoRepository<EFVoucherBscategory, BigInteger> {
    @Query(value = "select f_getvoucherid()", nativeQuery = true)
    BigInteger getVoucherid();
}
