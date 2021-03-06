package com.tengzhi.business.system.datachart.controller;

import java.io.IOException;
import java.util.List;

import com.tengzhi.business.resouces.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.datachart.model.SysDatachart;
import com.tengzhi.business.system.datachart.service.impl.SysDatachartServiceImpl;
@RestController
@RequestMapping("/system/datachart")
public class SysDatachartController {
	 @Autowired
	    private SysDatachartServiceImpl sysDatachartService;
	 @Autowired
		SysGenNoteService sysGenNoteService;
	 @GetMapping("*.html")
		public ModelAndView pageForwart(ModelAndView mv) {
			return mv;
		}
	/**
	 *  查询所有数据
	 **/
	@PostMapping(value = "/queryall")
	public Result findAllTable(BaseDto baseDto) throws IOException {
		return sysDatachartService.findAll(baseDto);
	}
	
	/**
	 * 初始化单号
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "setDatachartId")
	public Result setDatachartId()throws Exception{
		return Result.resultOk(sysGenNoteService.getyyyyMMddNote4(SysDatachart.class, "TB"));
	}
	
	/**
	 * 新增
	 * */
	@PostMapping(value = "add.html")
	public Result add(@RequestBody SysDatachart sysDatachart) throws Exception {
		SessionUser user = SessionUser.SessionUser();
		sysDatachart.setCorpId(user.getCorpId());
		return sysDatachartService.save(sysDatachart);
	}
	
	@GetMapping(value = "add.html/{datachartId}")
	public Result getByData(@PathVariable String datachartId) {
		return sysDatachartService.findByDatachartId(datachartId);
	}
	/**
	 * 修改
	 * **/
	@PutMapping("add.html")
	public Result modify(@RequestBody SysDatachart sysDatachart) {
		SessionUser user = SessionUser.SessionUser();
		sysDatachart.setCorpId(user.getCorpId());
		sysDatachartService.update(sysDatachart);
		return Result.resultOk(sysDatachart.getDatachartId());
	}
	
	/**
	 * 
	 * 删除
	 */
	@DeleteMapping(value = "delete.html/{datachartId}")
	public Result delete(@PathVariable String datachartId) {
		sysDatachartService.delete(datachartId);
		return Result.resultOk();
	}


	//图表选择列表
	@GetMapping("/datachartlist")
	public List<SelectVo> getDataChart() {
		return sysDatachartService.getDataChart();
	}

	/**
	 * 采购，销售交易量图表数据
	 * @return
	 */
	@GetMapping("/getChartDatalist")
	public Result getDataChartlist() {
		return sysDatachartService.getDataChartlist();
	}

	/**
	 * 销售前五产品量图表数据
	 * @return
	 */
	@GetMapping("/getChartXslDatalist")
	public Result getChartXslDatalist() {
		return sysDatachartService.getChartXslDatalist();
	}
	/**
	 * 采购前五产品量图表数据
	 * @return
	 */
	@GetMapping("/getChartCglDatalist")
	public Result getChartCglDatalist() {
		return sysDatachartService.getChartCglDatalist();
	}
}
