package com.tengzhi.business.mesPersonnel.checkWorker.overtime.contriller;

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
import com.tengzhi.business.mesPersonnel.checkWorker.overtime.service.OvertimeService;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.model.CheckWorkAttendance;

@RestController
@RequestMapping("/mesPersonnel/checkWorker/overtime/")
public class OvertimeController {
	
	@Autowired
	private OvertimeService overtimeService;
	@GetMapping(value="*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	/**
	 * 查询
	 * **/
	@PostMapping(value = "workOvertime.html")
	public Result getList(BaseDto baseDto) throws IOException {
		return overtimeService.findAll(baseDto).getResult();
	}
	
	/**
	 * 新增
	 * **/
	@PostMapping(value = "add.html")
	public Result add(@RequestBody CheckWorkAttendance checkWorkAttendance) throws Exception{
		 checkWorkAttendance =overtimeService.save(checkWorkAttendance);
		 return Result.resultOk(checkWorkAttendance.getJbqjId());
	}
	/**
	 * 根据id查询
	 * */
	@GetMapping(value = "workOvertime.html/{jbqjId}")
	public Result getById(@PathVariable String jbqjId) {
		return Result.resultOk(overtimeService.findByqjId(jbqjId));
	}
	
	/**
	 * 修改
	 * **/
	@PutMapping("add.html")
	public Result modify(@RequestBody CheckWorkAttendance checkWorkAttendance) {
		overtimeService.update(checkWorkAttendance);
		return Result.resultOk(checkWorkAttendance.getJbqjId());
	}
	
	/**
	 * 删除
	 * **/
	 @DeleteMapping(value = "workOvertime.html/{jbqjId}")
	    public Result delete(@PathVariable(value = "jbqjId") String jbqjId) {
		 overtimeService.deleteByqjId(jbqjId);
	        return Result.resultOk();
	    }
	 
	 
		@GetMapping("addType")
		public List<Map<Object,String>> parameType() {
			
			return overtimeService.parameType();
		}

}
