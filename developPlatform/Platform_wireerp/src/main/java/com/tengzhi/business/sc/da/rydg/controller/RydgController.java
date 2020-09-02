package com.tengzhi.business.sc.da.rydg.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;
import com.tengzhi.business.cg.da.sysCustomer.service.impl.SysCustomerServiceImpl;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.cg.yw.purchaseContract.vo.ECgContractVo;
import com.tengzhi.business.sc.da.rydg.model.MRyYgdg;
import com.tengzhi.business.sc.da.rydg.service.RydgService;
import com.tengzhi.business.sc.da.rydg.vo.MRyYgdgVo;



@RestController
@RequestMapping("/sc/da/rydg")
public class RydgController {
	@Autowired
	RydgService rydgService;

	@GetMapping(value="/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	
	/**
	 * 生产定岗查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchList")
	public Result getSrchList(BaseDto baseDto) throws IOException {
		return rydgService.getSrchList(baseDto).getResult();
	}
	
	
	/**
     * 
     * @description: Post新增Restful
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody MRyYgdgVo vo) throws Exception {
		Result result = rydgService.saveOrUpdate(MRyYgdgVo.initMRyYgdgVo(vo));
		return result;
    }
    
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody MRyYgdgVo vo) throws Exception {
        return rydgService.update(MRyYgdgVo.initMRyYgdgVo(vo));
    }
    
    /**
     * @Param: [vo]
     * @description: Get(获取数据|通过ID获取对象)Restful
     */
    @PostMapping(value = "getById")
    public Result getById(@RequestBody BaseDto baseDto)throws IOException {
        return Result.resultOk(rydgService.findByPkId(baseDto));
    }
	
	
    @DeleteMapping(value = "delete/{id}")
    public Result delete(@PathVariable(value = "id") String id) throws Exception {
    	rydgService.deleteAll(id);
        return Result.resultOk();
    }
    
    
    /**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getDeptGridList/{deptId}")
	public Result getDeptGridList(@PathVariable(value = "deptId") String deptId) throws IOException {
		return rydgService.getDeptGridList(deptId).getResult();
	}

	/**
	 *@role:
	 *@Author:      huangbiao
	 *@Date:        2020/8/3 0003 17:27
	 *@Param:
	 *@return:
	 *@Description: 用于定岗选择部门后的数据列表查询
	 **/
	@PostMapping(value = "/getDeptGridListToSysUser/{deptId}")
	public Result getDeptGridListToSysUser(@PathVariable(value = "deptId") String deptId) throws IOException {
		return rydgService.getDeptGridListToSysUser(deptId).getResult();
	}

	
	
    
}
