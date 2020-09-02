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
import com.tengzhi.business.system.email.service.InboxService;

@RestController
@RequestMapping("/system/")
public class EmailController {
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private InboxService inboxService;

	@GetMapping("email/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	/**
	 * 查询收件箱数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "inbox/list")
	@Log("查询收件箱")
	public Result getInboxList(BaseDto baseDto) throws IOException {
		return inboxService.findAll(baseDto).getResult();
	}

	/**
	 * 邮件同步
	 *
	 * @return
	 */
	@GetMapping(value = "inbox/synchronous/{username}")
	@Log("收件箱同步")
	public Result synchronous(@PathVariable String username) throws IOException {
		return inboxService.synchronous(username);
	}
	
	/**
	 * 通过ID获取对象
	 *
	 * @param orgId
	 * @return
	 */
	@GetMapping(value = "email/{id}")
	public Result getById(@PathVariable String id) {
		return Result.resultOk(emailService.findById(id));
	}

	/**
	 * 新增
	 *
	 * @return
	 */
	@PostMapping("email")
	public Result add(@RequestBody EmailConfig emailConfig) throws Exception {
		emailConfig = emailService.save(emailConfig);
		return Result.resultOk(emailConfig.getId());
	}

	/**
	 * 修改
	 *
	 * @return
	 */
	@PutMapping("email")
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
	@DeleteMapping(value = "email/{id}")
	public Result delete(@PathVariable(value = "id") String id) {
		emailService.deleteById(id);
		return Result.resultOk();
	}
}
