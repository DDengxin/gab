package com.tengzhi.business.mesPersonnel.checkWorker.absence.controller;

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
import com.tengzhi.business.mesPersonnel.checkWorker.absence.service.AbsenceService;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.model.CheckWorkAttendance;

@RestController
@RequestMapping("/mesPersonnel/checkWorker/absence/")
public class AbsenceController {
	
	@Autowired
	private AbsenceService absenceService;
	
	@GetMapping(value="*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	
	
	/**
	 * 查询
	 * **/
	@PostMapping(value ="leave.html")
	public Result getList(BaseDto baseDto) throws IOException {
		return absenceService.findAll(baseDto).getResult();
	}
	
	/**
	 * 新增
	 * **/
	@PostMapping(value = "add.html")
	public Result add(@RequestBody CheckWorkAttendance checkWorkAttendance) throws Exception{
		 checkWorkAttendance =absenceService.save(checkWorkAttendance);
		 return Result.resultOk(checkWorkAttendance.getJbqjId());
	}
	/**
	 * 根据id查询
	 * */
	@GetMapping(value = "leave.html/{jbqjId}")
	public Result getById(@PathVariable String jbqjId) {
		return Result.resultOk(absenceService.findByJbqjId(jbqjId));
	}
	
	/**
	 * 修改
	 * **/
	@PutMapping("add.html")
	public Result modify(@RequestBody CheckWorkAttendance checkWorkAttendance) {
		absenceService.update(checkWorkAttendance);
		return Result.resultOk(checkWorkAttendance.getJbqjId());
	}
	
	/**
	 * 删除
	 * **/
	 	@DeleteMapping(value = "leave.html/{jbqjId}")
	    public Result delete(@PathVariable(value = "jbqjId") String jbqjId) {
	 		absenceService.deleteByqjId(jbqjId);
	        return Result.resultOk();
	    }
	 	
	 	
	 	@GetMapping("addType")
		public List<Map<Object,String>> parameType() {
			return absenceService.parameType();
		}
	
	
	

}
