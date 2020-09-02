package com.tengzhi.business.mesPersonnel.producetSchedule.staffing.controller;

import java.io.IOException;
import java.text.ParseException;
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
import com.tengzhi.business.mesPersonnel.producetSchedule.staffing.model.Staffing;
import com.tengzhi.business.mesPersonnel.producetSchedule.staffing.service.StaffingService;

@RestController
@RequestMapping("/mesPersonnel/producetSchedule/staffing/")
public class StaffingController {
	@Autowired
	private StaffingService staffingService;
	
	@GetMapping(value="*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	/**
	 * 查询
	 * @throws IOException 
	 * */
	@PostMapping(value="staffingList.html")
	public Result getList(BaseDto baseDto) throws IOException{	
		return staffingService.findAll(baseDto).getResult();
	}
	
	/**
	 * 新增
	 * */
	@PostMapping(value = "add.html")
	public Result add(@RequestBody Staffing staffing) throws Exception {
		return staffingService.save(staffing);
	}
	
	/**
	 * 根据id查询
	 * @throws ParseException 
	 * */
	@GetMapping(value = "staffingList.html/{work}")
	public Result getById(@PathVariable String work) throws ParseException {
		String[] works = work.split("_");
		return Result.resultOk(staffingService.findWork(works[0],works[1],works[2]));
	}
	
	
	/**
	 * 修改
	 * **/
	@PutMapping("add.html")
	public Result modify(@RequestBody Staffing staffing) {
		staffingService.update(staffing);
		return Result.resultOk(staffing.getWorkId());
	}
	
	
	/**
	 * 删除
	 * @throws ParseException 
	 * */
	@DeleteMapping(value = "delete.html/{work}")
	public Result delete(@PathVariable String work) throws ParseException {
		String[] works = work.split("_");
		staffingService.delete(works[0],works[1],works[2]);
		return Result.resultOk();
	}
	 
	 
	   @GetMapping(value = "type/treelist")
		public List<Map> getTreeList() throws IOException {
			String mod="人事",type="生产班别";
			return staffingService.getTreeList(mod,type);
		}
	   
	   @GetMapping(value = "type/typelist")
		public List<Map> getTypeList() throws IOException {
			String mod="启用",type="生产设备";
			return staffingService.getTypeList(mod,type);
		}
	   @GetMapping(value = "type/deptlist")
	   public List<Map> getDeptList(){
		   return staffingService.getDeptList();
   
	   }
	
	

}
