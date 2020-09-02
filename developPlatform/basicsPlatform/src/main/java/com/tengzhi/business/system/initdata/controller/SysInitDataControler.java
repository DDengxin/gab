package com.tengzhi.business.system.initdata.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.tools.excel.Excel;
import com.tengzhi.base.tools.excel.ExcelUtil;
import com.tengzhi.business.system.initdata.dao.SysInitDataDao;
import com.tengzhi.business.system.initdata.service.SysInitDataService;

import javassist.CannotCompileException;
import javassist.NotFoundException;

@RestController
@RequestMapping("/system/initdata/")
public class SysInitDataControler {

	@Autowired
	private SysInitDataService sysInitDataService;
	@Autowired
	private SysInitDataDao sysInitDataDao;

	@GetMapping("*.html")
	public ModelAndView pageForward(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws Exception {
		return sysInitDataService.getSrchTopList(baseDto).getResult();
	}



	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchBottomNList")
	public Map<String, Object> getSrchBottomNList(BaseDto baseDto) throws IOException {
		return sysInitDataService.getSrchBottomNList(baseDto);
	}

	@PostMapping("/upload")
	public Result upload(MultipartFile files, String dbTable, String dbName, String dataType)
			throws Exception {
		return sysInitDataService.upload(files, dbTable, dbName, dataType);
	}

	@GetMapping(value = "/template/{dbname}/{dbtable}")
	public void template(HttpServletResponse response, @PathVariable String dbname, @PathVariable String dbtable) {
		String columnsql = "select a.attname ,replace(d.description,'imp_','') description from pg_class c,pg_attribute a,pg_description d\r\n"
				+ "where c.relname='" + dbtable
				+ "' and a.attnum>0 and a.attrelid=c.oid and d.description like 'imp_%' and d.objoid=a.attrelid and d.objsubid=a.attnum;";
		List<Map> lpm = sysInitDataDao.QueryListMap(columnsql, new  Object[]{});
		ExcelUtil execlutil = ExcelUtil.getInstance();
		execlutil.ExcelToWebByTable( dbname, response,lpm);
	}
	/**
	 * 数据处理
	 */

	@RequestMapping(value = "/callSolveDataProduce")
	public Result callSolveDataProduce() {
		return sysInitDataService.callSolveDataProduce();
	}
	/**
	 * 数据初始化
	 */
	@RequestMapping(value = "/callSysDataInitProduce")
	public Result callSysDataInitProduce() {
		return sysInitDataService.callSysDataInitProduce();
	}
}