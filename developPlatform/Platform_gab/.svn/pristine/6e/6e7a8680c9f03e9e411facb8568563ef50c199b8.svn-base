package com.tengzhi.business.system.user.controller;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.captcha.utils.CaptchaUtil;
import com.tengzhi.business.system.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	/**
	 * 登录页面
	 *
	 * @return
	 */
	@GetMapping
	public ModelAndView login(ModelAndView mv, @RequestParam(required = false) String corp,
							  @RequestParam(required = false, defaultValue = "/system/main") String url) {
		mv.setViewName("system/login");
		mv = loginService.getSetting(corp, mv);
		mv.addObject("url", url);
		return mv;
	}

	@RequestMapping("/loginCheck")
	public ModelAndView loginCheck(ModelAndView mv) {
		mv.setViewName("redirect:/system/main");
		return mv;
	}

	/**
	 * 图形验证码 请求的验证码会记录在session的"captcha"键值对中
	 * 通过CatchaUtil.ver(验证码,HttpServletRequest)可以验证验证码正确性 通
	 * 过CatchaUtil.clear()可以清除验证码缓存
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GetMapping(value = "/captcha", produces = "image/gif")
	public void captchaPicutre(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CaptchaUtil.out(104, 40, 4, request, response);
	}

	/**
	 * 手机验证码
	 * 
	 * @param mobile 手机号码
	 * @param corp   公司账套
	 * @return
	 */
	@PostMapping(value = "/captcha")
	@ResponseBody
	public Result captchaMobile(@RequestParam String mobile, @RequestParam(required = false) String corp) {
		try {
			loginService.sendLoginSms(mobile, corp);
			return Result.resultOk("发送成功");
		} catch (Exception e) {
			return Result.resultError(e);
		}
	}

	/**
	 * 验证是否登录
	 *  该方法已启用，之后将删除
	 * @return boolean
	 */
/*	@PostMapping(value = "/verifyLogin")
	@Anonymous
	public @ResponseBody Boolean verifyLogin(HttpServletRequest request) {
		SessionUser sysUser = SessionUser.SessionUser();
		if (sysUser == null) {
			sysUser = (SessionUser) request.getSession().getAttribute("authentication");
		}
		boolean flag = (sysUser == null || sysUser.getCode() != null);
		return !flag;
	}*/

	/**
	 * 验证是否登录
	 * 
	 * @return boolean
	 */
	/*@RequestMapping(value = "/getUser")
	@Anonymous
	public @ResponseBody Result getUser(HttpServletRequest request) {
		SessionUser sysUser = null;
		try {
			sysUser = SessionUser.SessionUser();
			if (sysUser == null) {
				sysUser = (SessionUser) request.getSession().getAttribute("authentication");
			}
			if (sysUser != null)
				return Result.resultOk(sysUser);
			else
				return Result.error();
		} catch (Exception e) {
			return Result.error();
		}
	}*/
}
