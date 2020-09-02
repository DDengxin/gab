package com.tengzhi.business.cg.yw.purchaseContract.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContractDetailed;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ECgContractVo;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ExamineVo;
import com.tengzhi.business.resouces.vo.SelectVo;

public interface PurchaseContractService extends BaseService {
	BasePage<ECgContract> getSrchTopList(BaseDto baseDto) throws IOException;

	BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;

	ECgContract save(ECgContractVo sysRightVo) throws Exception;

	void update(ECgContractVo eCgContractVo) throws Exception;


	ECgContract findById(String htNo);

	void deleteById(String htNO);

//	Result submit(String id);

	BasePage<Map<String, Object>> getCgddSelectList(BaseDto baseDto) throws IOException;

	List<Map> getLib(String libType);

	List<SelectVo> getHtInvoice(String trueText, String faext);

	List<SelectVo> getHtTax(String trueText, String falseText);

	List<SelectVo> getUrgent(String trueText, String falseText);

	List<SelectVo> getHtType(String trueText, String falseText);

	List<SelectVo> getHtBz(String trueText, String falseText);

//	 void saveDataAndcommit(ECgContractVo initECgContractVo) throws Exception;

//	 Result agree(ExamineVo examineVo);
//	 
//	 Result disagree(ExamineVo examineVo);

	Result getContractDetailed(BaseDto baseDto) throws IOException;

	List<SelectVo> comboParam(String mod, String pareatId);

	Result getFlag(String htNO, String flag);

	BasePage<Map<String, Object>> getWlbmSelectList(BaseDto baseDto) throws IOException;

	List<SelectVo> getSqType(String trueText, String falseText);

	ModelAndView table(ModelAndView mv, String ht_no);

	Result hx(String htNo,String htMo);

	Result qxhx(String htNo,String htMo);

	Result getFlagH(String htNO, String flag,String code);

	Result getCancelFlagH(String htNO, String flag,String code);
	
	//导出excel
	void exportExcel(HttpServletResponse response, HttpServletRequest request);


}
