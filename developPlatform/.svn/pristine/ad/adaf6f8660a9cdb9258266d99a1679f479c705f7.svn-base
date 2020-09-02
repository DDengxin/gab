package com.tengzhi.workflow.test.controller;

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
import com.tengzhi.workflow.test.model.Employee;
import com.tengzhi.workflow.test.service.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("/workflow/test")
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl employeeService;

	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getList")
	public Result getList(BaseDto baseDto) throws IOException {
		return employeeService.findAll(baseDto).getResult();
	}
	
	/**
	 * 查询待办数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getBacklog")
	public Result getBacklog(BaseDto baseDto) throws IOException {
		return employeeService.getBacklog(baseDto);
	}
	
	

	/**
	 * 新增
	 *
	 * @return
	 */
	@PostMapping(value = "/Employee")
	public Result add(@RequestBody Employee employee) throws Exception {
		employee = employeeService.save(employee);
		return Result.resultOk(employee.getId());
	}
	
	@PostMapping(value = "/Employee/saveAndSubmit")
	public Result saveAndSubmit(@RequestBody Employee employee) throws Exception {
		employee = employeeService.saveAndSubmit(employee);
		return Result.resultOk(employee.getId());
	}
	
	
	 /**
     * 通过ID获取对象
     *
     * @param roleId
     * @return
     */
    @GetMapping(value = "/Employee/{id}")
    public Result getById(@PathVariable String id) {
        return Result.resultOk(employeeService.findByid(id));
    }
    
    @GetMapping(value = "/Employee/audit/{id}")
    public Result audit(@PathVariable String id) {
		return Result.resultOk(employeeService.audit(id));
    }

	/**
	 * 修改
	 *
	 * @return
	 */
	@PutMapping(value = "/Employee")
	public Result modify(@RequestBody Employee employee) {
		employeeService.update(employee);
		return Result.resultOk(employee.getId());
	}

	/**
	 * 删除
	 *
	 *
	 * @return
	 */
	@DeleteMapping(value = "/{deptId}")
	public Result delete(@PathVariable(value = "deptId") String id) {
		employeeService.deleteByid(id);
		return Result.resultOk();
	}
	
	/**
	 *同意
	 *
	 * @return
	 */
	@PutMapping(value = "/Employee/agree")
	public Result agree(@RequestBody Employee employee) {
		employeeService.agree(employee);
		return Result.resultOk(employee.getId());
	}
	
	/**
	 *不同意
	 *
	 * @return
	 */
	@PutMapping(value = "/Employee/disagree")
	public Result disagree(@RequestBody Employee employee) {
		employeeService.disagree(employee);
		return Result.resultOk(employee.getId());
	}
	
	@PutMapping(value = "/Employee/Submit")
	public Result Submit(@RequestBody Employee employee) {
		employeeService.Submit(employee);
		return Result.resultOk(employee.getId());
	}
	
}
