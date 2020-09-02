package com.tengzhi.business.mesGz.gzda1.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.mesGz.gzda1.service.MGzMjdaService;

@RestController
@RequestMapping("/mesGz/gzda1/")
public class MGzMjdaController {

	@Autowired
	private MGzMjdaService MGzMjdaService;

	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "list")
	public Result getInboxList(BaseDto baseDto) throws IOException {
		return MGzMjdaService.findAll(baseDto);
	}


	
}
