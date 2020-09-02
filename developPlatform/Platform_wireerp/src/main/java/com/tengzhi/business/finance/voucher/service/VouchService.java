package com.tengzhi.business.finance.voucher.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.voucher.model.EFVoucher;
import com.tengzhi.business.finance.voucher.model.EFVoucherentry;
import com.tengzhi.business.finance.voucher.vo.EFVouchVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface VouchService extends BaseService {
	BasePage<EFVoucher> findAll(BaseDto baseDto);

	List<Map> getLeafSubjectList() ;
	List<Map> getSubjectTreeList();
	List<Map>getEntryGridList(BigInteger Id);
	Map getSubjectById(String id);
	List<Map> getYearMonthList();
	List<SelectVo> getFGROUPID();

	EFVoucher findById(BigInteger Id);
//
//	EFVoucherAuxiliaryitems save(EFVoucherAuxiliaryitems EFVoucherAuxiliaryitems) throws Exception;
//
//	List<EFVoucher> findAll() ;
	ModelAndView addhtml(ModelAndView mv, String ht_no);
	List<Map> getCurrentMonthNextfvoucherNumber(BaseDto baseDto) ;
	BasePage<Map<String, Object>> getSubjectList(BaseDto baseDto) throws Exception;
//	void update(EFVoucherAuxiliaryitems EFVoucherAuxiliaryitems);
//	boolean judgeUniqueset(EFVoucherAuxiliaryitems efva);
//	boolean judgeUniqueother(EFVoucherAuxiliaryitems efva);
	BasePage<Map<String, Object>>  getAssistingGridlist(BaseDto baseDto) throws IOException;
//	Result deleteById(String Id);

	List<Map>  getExplanationList();

	List<SelectVo> getAccountCurrency(String id);
	List<SelectVo> getCurrency();
	Result getCurrencyRate(String id);

	EFVoucher saveData(EFVouchVo efVouchVo)throws Exception;
}
