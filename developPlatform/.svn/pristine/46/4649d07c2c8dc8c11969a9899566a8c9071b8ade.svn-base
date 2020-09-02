package com.tengzhi.workflow.test.service;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.workflow.test.model.Employee;

public interface EmployeeService {
	BasePage<Employee> findAll(BaseDto baseDto) throws IOException;

	Employee findByid(String id);

	Employee save(Employee Employee) throws Exception;

	void update(Employee Employee);

	void deleteByid(String id);
	
	Result getBacklog(BaseDto baseDto);
	
	Employee saveAndSubmit(Employee Employee) throws Exception;
	
	Map<String,Object> audit(String id);
	
	void agree(Employee employee);
	
	void disagree(Employee employee);
}
