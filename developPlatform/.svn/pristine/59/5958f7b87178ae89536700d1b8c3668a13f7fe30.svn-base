package com.tengzhi.business.system.email.controller;

import java.io.IOException;
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

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.email.model.EmailConfig;
import com.tengzhi.business.system.email.service.EmailService;

@RestController
@RequestMapping("/system/")
public class EmailConfigController {
	@Autowired
	private EmailService emailService;

	@GetMapping("emailConfig/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "emailConfig/list")
	@Log("查询邮件配置")
	public Result getList(BaseDto baseDto) throws IOException {
		return emailService.findAll(baseDto).getResult();
	}

	/**
	 * 通过ID获取对象
	 *
	 * @param orgId
	 * @return
	 */
	@GetMapping(value = "emailConfig/{id}")
	public Result getById(@PathVariable String id) {
		return Result.resultOk(emailService.findById(id));
	}

	/**
	 * 新增
	 *
	 * @return
	 */
	@PostMapping("emailConfig")
	@Log("新增邮件配置")
	public Result add(@RequestBody EmailConfig emailConfig) throws Exception {
		emailConfig = emailService.save(emailConfig);
		return Result.resultOk(emailConfig.getId());
	}

	/**
	 * 修改
	 *
	 * @return
	 */
	@PutMapping("emailConfig")
	@Log("修改邮件配置")
	public Result modify(@RequestBody EmailConfig emailConfig) {
		emailService.update(emailConfig);
		return Result.resultOk(emailConfig.getId());
	}

	/**
	 * 删除数据
	 *
	 *
	 * @return
	 */
	@DeleteMapping(value = "emailConfig/{id}")
	@Log("删除邮件配置")
	public Result delete(@PathVariable(value = "id") String id) {
		emailService.deleteById(id);
		return Result.resultOk();
	}
}
