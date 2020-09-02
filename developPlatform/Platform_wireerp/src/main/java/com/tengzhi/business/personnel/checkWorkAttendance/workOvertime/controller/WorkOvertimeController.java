package com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.service.WorkOvertimeService;
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
import com.tengzhi.business.personnel.checkWorkAttendance.leave.service.LeaveService;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.model.CheckWorkAttendance;

@RestController
@RequestMapping("/personnel/checkWorkAttendance/workOvertime/")
public class WorkOvertimeController {
	@Autowired
	private WorkOvertimeService workOvertimeService;
	
	@Autowired
	private LeaveService leaveService;

	
	@GetMapping(value="*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	/**
	 * 查询
	 * @throws ParseException 
	 * **/
	@PostMapping(value = "workOvertime.html")
	public Result getList(BaseDto baseDto) throws IOException, ParseException {
		return workOvertimeService.findAll(baseDto).getResult();
	}
	
	/**
	 * 新增
	 * **/
	@PostMapping(value = "add.html")
	public Result add(@RequestBody CheckWorkAttendance checkWorkAttendance) throws Exception{
		 checkWorkAttendance = workOvertimeService.save(checkWorkAttendance);
		 return Result.resultOk(checkWorkAttendance.getJbqjId());
	}
	/**
	 * 根据id查询
	 * */
	@GetMapping(value = "workOvertime.html/{jbqjId}")
	public Result getById(@PathVariable String jbqjId) {
		return Result.resultOk(workOvertimeService.findByqjId(jbqjId));
	}

	/**
	 * 根据id查询
	 */
	@GetMapping(value = "workOvertime/{note}")
	public Result getByNote(@PathVariable String note) {
		return Result.resultOk(workOvertimeService.findByJbNote(note));
	}

	/**
	 * 修改
	 * **/
	@PutMapping("add.html")
	public Result modify(@RequestBody CheckWorkAttendance checkWorkAttendance) {
		workOvertimeService.update(checkWorkAttendance);
		return Result.resultOk(checkWorkAttendance.getJbqjId());
	}
	
	/**
	 * 删除
	 * **/
	 @DeleteMapping(value = "workOvertime.html/{jbqjId}")
	    public Result delete(@PathVariable(value = "jbqjId") String jbqjId) {
	    	workOvertimeService.deleteByqjId(jbqjId);
	        return Result.resultOk();
	    }
	 
	 
		@GetMapping("addType")
		public List<Map<Object,String>> parameType() {
			List<Map<Object, String>> maps = workOvertimeService.parameType();
			return maps;
		}
	
		@PutMapping(value = "getFlag/{note}/{flag}")
	    public Result getFlag(@PathVariable(value = "note") String note,@PathVariable(value = "flag") String flag)throws Exception {
	        return leaveService.getFlag(note,flag);
	    }

}
