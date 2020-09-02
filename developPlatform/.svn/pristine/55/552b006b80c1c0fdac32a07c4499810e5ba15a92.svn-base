package com.tengzhi.business.finance.voucher.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.voucher.model.EFVoucherAuxiliaryitems;

import java.util.List;

public interface AuxiliaryItemsService extends BaseService {
	BasePage<EFVoucherAuxiliaryitems> findAll(BaseDto baseDto,String fusebstable);
	BasePage<EFVoucherAuxiliaryitems> getAssistingGridlist(BaseDto baseDto);

	EFVoucherAuxiliaryitems findById(String Id);

	EFVoucherAuxiliaryitems save(EFVoucherAuxiliaryitems EFVoucherAuxiliaryitems) throws Exception;
	
	List<EFVoucherAuxiliaryitems> findAll() ;

	void update(EFVoucherAuxiliaryitems EFVoucherAuxiliaryitems);
	boolean judgeUniqueset(EFVoucherAuxiliaryitems efva);
	boolean judgeUniqueother(EFVoucherAuxiliaryitems efva);

	Result deleteById(String Id);

}
