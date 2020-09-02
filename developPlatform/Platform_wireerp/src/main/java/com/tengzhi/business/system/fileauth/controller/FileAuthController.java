package com.tengzhi.business.system.fileauth.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.fileauth.service.FileAuthService;

@RestController
@RequestMapping("/system/fileauth")
public class FileAuthController {
	@Autowired
	private FileAuthService fileAuthService;
	

	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	@PostMapping(value = "file_auth.html")
	    public Result getFileAuthAll(BaseDto baseDto) throws IOException {
	        return fileAuthService.findUserRightAll(baseDto).getResult();
	    }
	
	/**
	 *  查询所有数据
	 **/
	@PostMapping(value = "/queryall")
	public Result findAllTable(BaseDto baseDto)  {
		return fileAuthService.findAll(baseDto);
	}
	/**
	  *  授权保存
	 * 
	 * */
    @PostMapping("fileAuth_add.html")
    public Result FileAuthAdd(@RequestBody Map<String, Object> map) {
    	SessionUser user = SessionUser.SessionUser();
    	map.put("gen_user_id", user.getUserId());
    	fileAuthService.FileAuthSave(map);
        return Result.resultOkMsg("授权成功!");
    }
    /**
	 * 
	 * 删除
	 */
	@DeleteMapping(value = "delete.html/{arNote}")
	public Result delete(@PathVariable String arNote) {
		fileAuthService.delete(arNote);
		return Result.resultOk();
	}
}
