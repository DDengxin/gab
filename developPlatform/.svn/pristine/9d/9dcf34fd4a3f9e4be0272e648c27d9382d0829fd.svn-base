package com.tengzhi.IM.business.message.controller;

import com.tengzhi.IM.business.message.service.SysImMessageService;
import com.tengzhi.base.jpa.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/Mes")
public class mescontroller {

	@Autowired
	private SysImMessageService sysImMessageService;


	@RequestMapping(value = "/updateTimFlockAddMes")
	public Result updateTimFlockAddMes() {
		return sysImMessageService.updateTimFlockAddMes();
	}



	@PostMapping("upload")
	public Map<String, Object> upload(HttpServletRequest request,MultipartFile file) throws Exception {
		return sysImMessageService.upload(request,file);
	}


	@GetMapping("download/{uuid}")
	public Map<String, Object> download(@PathVariable String uuid, HttpServletRequest request,HttpServletResponse response) {
		return sysImMessageService.download(uuid, request, response);
	}

}
