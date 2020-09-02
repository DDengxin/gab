package com.tengzhi.business.system.library.controller;

import java.sql.Timestamp;
import java.util.List;

import com.tengzhi.annotations.Log;
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
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.library.model.Library;
import com.tengzhi.business.system.library.service.LibraryService;

@RestController
@RequestMapping("/system/library")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	@Autowired
	SysGenNoteService sysGenNoteService;

	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询所有数据
	 *
	 * @return
	 */
	@PostMapping(value = "/queryall")
	public Result findAllTable(BaseDto baseDto){
		return libraryService.findAll(baseDto);
	}
	/**
	 * 初始化单号
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "setArNote")
	public Result setArNote(){
		return Result.resultOk(sysGenNoteService.getyyyyMMddNote4(Library.class, "DA"));
	}
	
	/**
	 * 新增
	 * */
	@PostMapping(value = "add.html")
	public Result add(@RequestBody Library library) throws Exception {
		SessionUser user = SessionUser.SessionUser();
		library.setArNote(sysGenNoteService.getyyyyMMddNote4(Library.class, "DA"));
		library.setArUuid(UUIDUtil.uuid());
		library.setGenUserId(user.getUserId());
		library.setGenTime(new Timestamp(System.currentTimeMillis()));
		library.setArModifyUserId(user.getUserId());
		library.setArModifyTime(new Timestamp(System.currentTimeMillis()));
		library.setArVersion(1);
		library.setDataCorp(user.getCorpId());
		library.setArNewest(true);
		return libraryService.save(library);
	}
    
	@GetMapping(value = "libraryList.html/{arUuid}")
	@Log(type="R101402" , value="档案文库查阅功能")
	public Result getByName(@PathVariable String arUuid) {
		return libraryService.findByArUuid(arUuid);
	}
	
	/**
	 * 修改
	 * **/
	@PutMapping("add.html")
	public Result modify(@RequestBody Library library) {
		libraryService.updateNewEst(false, library.getArUuid());
		library.setArVersion(library.getArVersion() + 1);
		library.setArUuid(UUIDUtil.uuid());
		libraryService.update(library);
		return Result.resultOk(library.getArUuid());
	}
	/**
	 * 
	 * 删除
	 */
	@DeleteMapping(value = "delete.html/{arUuid}")
	public Result delete(@PathVariable String arUuid) {
		libraryService.delete(arUuid);
		return Result.resultOk();
	}
	
	/**
	 * 导出excel
	 *
	 * @return
	 */
	/*@PostMapping(value = "/exportExcel")
	public void exportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
		testService.exportExcel(response, request);
	}


	@GetMapping(value = "/template")
	public void template(HttpServletResponse response, HttpServletRequest request) throws IOException {
		ExcelUtil util = ExcelUtil.getInstance();
		util.ExcelToWeb(new Test(), "测试模板", response);
	}
	

	@PostMapping("/upload")
	public Result upload(MultipartFile files) throws Exception {
	    return testService.upload(files);
}*/

	  /**
     * @Param: [vo]
     * @description:版本号
     */
	@GetMapping(value="/getVersion/{arNote}")
    public List<SelectVo> getVersion(@PathVariable String arNote) {
		SessionUser user = SessionUser.SessionUser();
        return libraryService.getVersion(arNote,user.getCorpId());
    }
}
