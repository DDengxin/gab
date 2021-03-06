package com.tengzhi.business.finance.voucher.controller;

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.voucher.model.EFVoucher;
import com.tengzhi.business.finance.voucher.model.EFVoucherAuxiliaryitems;
import com.tengzhi.business.finance.voucher.model.EFVoucherentry;
import com.tengzhi.business.finance.voucher.service.VouchService;
import com.tengzhi.business.finance.voucher.vo.EFVouchVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/finance/voucher/vouch")
public class VouchController {
	@Autowired
	private VouchService vouchService;

	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		mv =vouchService.addhtml(mv,"");
		return mv;

	}
//	@GetMapping("add.html")
//	public ModelAndView addhtml(ModelAndView mv) {
//	mv =vouchService.addhtml(mv,"");
//		return mv;
//	}
	/**
	 * 查询配置数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "getVoucherList")
	@Log("查询业务表单")
	public Result getVoucherList(BaseDto baseDto ) {
		return vouchService.findAll(baseDto).getResult();
	}


	/**
	 * 通过ID获取对象
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/findById/{id}")
	public Result findById(@PathVariable BigInteger id) {
		return Result.resultOk(vouchService.findById(id));
	}
	/**
	 * 通过ID获取对象
	 *
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/getEntryGridList/{id}")
	public Result getEntryGridList(@PathVariable BigInteger id) {
		return Result.resultOk(vouchService.getEntryGridList(id));
	}



	/**
	 * 查询下拉框数据列表
	 * 
	 * @return
	 */
//	@GetMapping("auxiliaryItems/findAll")
//	public List<EFVoucher> findAll() {
//		return vouchService.findAll();
//	}
	/**
	 * 查询下拉框数据列表
	 *
	 * @return
	 */
//	@GetMapping("/getCurrentMonthNextfvoucherNumber")
//	public List<Map> getCurrentMonthNextfvoucherNumber(BaseDto baseDto) {
//		return vouchService.getCurrentMonthNextfvoucherNumber(baseDto);
//	}
	/**
	 * 查询下拉框数据列表
	 *
	 * @return
	 */
	@GetMapping("/getExplanationList")
	public List <Map> getExplanationList() {
		return vouchService.getExplanationList();
	}


	/**
	 * 查询下拉框数据列表
	 *
	 * @return
	 */
	@PostMapping("/getCurrentMonthNextfvoucherNumber")
	public List<Map> findAll(BaseDto baseDto) {
		return vouchService.getCurrentMonthNextfvoucherNumber(baseDto);
	}

	/**
	 * 查询下拉框数据列表
	 *
	 * @return
	 */
	@GetMapping("/getLeafSubjectList")
	List<Map> getLeafSubjectList(){
		return vouchService.getLeafSubjectList();
	}
	/**
	 * 查询下拉框数据列表
	 *
	 * @return
	 */
	@PostMapping("/getSubjectTreeList")
	List<Map> getSubjectTreeList(){
		return vouchService.getSubjectTreeList();
	}
	/**
	 * 查询下拉框数据列表
	 *
	 * @return
	 */
	@GetMapping("/getYearMonthList")
	List<Map> getYearMonthList(){
		return vouchService.getYearMonthList();
	}

	/**
	 * 查询下拉框数据列表
	 *
	 * @return
	 */
	@GetMapping("/getSubjectById/{id}")
	Map getSubjectById(@PathVariable String id){
		return vouchService.getSubjectById(id);
	}	/**
	 * 查询下拉框数据列表
	 *
	 * @return
	 */
	@GetMapping("/getAccountCurrency/{code}")
	List<SelectVo> getAccountCurrency(@PathVariable String code){
		return vouchService.getAccountCurrency(code);
	}
	/**
	 * 查询下拉框数据列表
	 *
	 * @return
	 */
	@GetMapping("/getFGROUPID")
	public List<SelectVo> getFGROUPID(BaseDto baseDto) {
		return vouchService.getFGROUPID();
	}

	//select fgenerateid id , fname text from e_f_voucher_certificateword a  where finternalind='t_Voucher'  and data_corp=''
	/**
	 * 通过ID获取对象
	 *
	 * @param id
	 * @return
	 */
//	@GetMapping(value = "auxiliaryItems/{id}")
//	public Result getById(@PathVariable String id) {
//		return Result.resultOk(vouchService.findById(id));
//	}

	/**
	 * 通过ID获取对象
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getCurrencyRate/{id}")
	public Result getCurrencyRate(@PathVariable String id) {
		return vouchService.getCurrencyRate(id);
	}



	/**
	 * 新增
	 *
	 * @return
	 */
	@PostMapping("saveData")
	@Log("新增业务表单")
	public Result add(@RequestBody EFVouchVo vo) throws Exception {

		EFVoucher efv = vouchService.saveData(EFVouchVo.initEFVouchVo(vo));
		return Result.resultOk(efv);
	}

	/**
	 * 修改
	 *
	 * @return
	 */
//	@PutMapping("saveData")
//	@Log("修改业务表单")
//	public Result modify(@RequestBody EFVoucherAuxiliaryitems efva) {
//		vouchService.update(efva);
//		return Result.resultOk(efva.getFitemid());
//	}

	/**
	 * 删除数据
	 *
	 *
	 * @return
	 */
//	@DeleteMapping(value = "auxiliaryItems/{id}")
//	@Log("删除业务表单")
//	public Result delete(@PathVariable(value = "id") Long id) {
//		return vouchService.deleteById(id);
//	}
}
