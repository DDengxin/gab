package com.tengzhi.business.finance.voucher.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.voucher.model.EFVoucherBscategory;
import com.tengzhi.business.finance.voucher.model.EFVoucherBscategoryentry;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface BusinessCategoryEntryDao extends BasedaoRepository<EFVoucherBscategoryentry, Long> {
    @Query(value = "select f_getvoucherid()", nativeQuery = true)
    Long getEntryid();
}
