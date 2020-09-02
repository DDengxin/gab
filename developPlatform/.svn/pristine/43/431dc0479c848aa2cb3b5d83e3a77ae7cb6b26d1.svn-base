package com.tengzhi.business.finance.constituent.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.constituent.service.EPzJyListLhService;
import com.tengzhi.business.finance.constituent.vo.EPzJyListLhVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 炉号成分
 */
@RestController
@RequestMapping("/finance/constituent/")
public class EPzJyListLhController {
	@Autowired
	private EPzJyListLhService ePzJyListLyService;

	/**
	 * 页面视图跳转
	 * @param mv
	 * @return
	 */
	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询列表请求
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "constituent.html")
	public Result getList(BaseDto baseDto) throws IOException {
		return ePzJyListLyService.findAll(baseDto);
	}

	/**
	 * 查询牌号
	 * @return
	 */
	@GetMapping(value = "combobox/PH/{codeType}")
	public Set<SelectVo> ComboboxRightModule(@PathVariable("codeType") String codeType) {
		return ePzJyListLyService.getPH(codeType);
	}

	@PostMapping("all/{id}")
	public Result paramsA(@PathVariable String id){
		return Result.resultOk(ePzJyListLyService.paramsAll(id));
	}


	@GetMapping(value = "constituent.html/{pch}")
	public Result getById(@PathVariable("pch") String pch) {
		return ePzJyListLyService.findbyid(pch);
	}


	@PostMapping("add.html")
	public Result add(@RequestBody EPzJyListLhVo EPzJyListLhVo) throws Exception {
		return ePzJyListLyService.save(EPzJyListLhVo);
	}

	@PutMapping("add.html")
	public Result modify(@RequestBody EPzJyListLhVo EPzJyListLhVo) {
		ePzJyListLyService.update(EPzJyListLhVo);
		return Result.resultOk();
	}


	@DeleteMapping(value = "/constituent.html/{pch}")
	public Result delete(@PathVariable(value = "pch") String pch) {
		ePzJyListLyService.deleteByPch(pch);
		return Result.resultOk();
	}

	/**
	 * 通过参数类型获取牌号列表
	 * @param phType
	 * @return
	 */
	@PostMapping(value = "/param/list/{phType}")
	public Result getPhTypeList(@PathVariable(value = "phType") String phType) {
		return Result.resultOk(ePzJyListLyService.getPhTypeList(phType));
	}

	/**
	 * 获取标准值
	 * @param codeType
	 * @param phType
	 * @param code
	 * @return
	 */
	@PostMapping(value = "/standard/list")
	public Result getStandardList(String codeType,String phType,String code ) {
		return Result.resultOk(ePzJyListLyService.getStandardValue(codeType,phType,code));
	}

	@GetMapping(value = "/note4pack/{pack}")
	public List<SelectVo> getNote4Pack(@PathVariable("pack") String pack) throws IOException {
		return ePzJyListLyService.getNote4Pack(pack);
	}


	@PostMapping(value = "/productSelection.html")
	public Result getProductSelectionList(BaseDto dto) throws IOException {
		return Result.formPage(ePzJyListLyService.getProductSelectionList(dto));
	}
}
