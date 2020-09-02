package com.tengzhi.base.security.common.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/managementUsers")
public class ManagementUsersController {
	@Autowired
	private SessionRegistry sessionRegistry;

	@PostMapping("/list")
	@ResponseBody
	public Result list(BaseDto dato, String username) {
		List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
		List<SecurityUser> User = new ArrayList<SecurityUser>();
		allPrincipals.forEach(x -> {
			if (x instanceof SecurityUser) {
				List<SessionInformation> activeUserSessions = sessionRegistry.getAllSessions(x, false);
				if (!activeUserSessions.isEmpty()) {
					User.add((SecurityUser) x);
				}
			}
		});
		List<SecurityUser> result = null;
		if (StringUtils.isNotBlank(username)) {
            result = User.stream().filter((SecurityUser s) -> username.equals(s.getNickName())).collect(Collectors.toList());
        } else {
            result = User;
        }
		return Result.formPage(pageBySubList(result, dato.getPageSize(), dato.getPageIndex()), result.size());
	}

	/**
	 * 利用subList方法进行分页
	 * 
	 * @param list        分页数据
	 * @param pagesize    页面大小
	 * @param currentPage 当前页面
	 */
	@SuppressWarnings("unchecked")
	public static List<?> pageBySubList(List list, int pagesize, int currentPage) {
		List<String> subList = null;
		if (list != null && list.size() > 0) {
			currentPage++;
			int totalcount = list.size();
			int pagecount = 0;
			int m = totalcount % pagesize;
			if (m > 0) {
				pagecount = totalcount / pagesize + 1;
			} else {
				pagecount = totalcount / pagesize;
			}
			if (m == 0) {
				subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
			} else {
				if (currentPage == pagecount) {
					subList = list.subList((currentPage - 1) * pagesize, totalcount);
				} else {
					subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
				}
			}
		}
		return subList;
	}

	@GetMapping("/toIndex")
	public ModelAndView toIndex(ModelAndView mv) {
		mv.setViewName("managementUsers/list");
		return mv;
	}

	// 根据用户清除session
	@PutMapping("/removeUserSessionByUsername/{username}")
	@ResponseBody
	public Result removeUserSessionByUsername(@PathVariable String username) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (username.equals(authentication.getName())) {
			return Result.error("自己不能让自己下线");
		}
		List<Object> users = sessionRegistry.getAllPrincipals(); // 获取session中所有的用户信息
		for (Object principal : users) {
			if (principal instanceof SecurityUser) {
				final SecurityUser loggedUser = (SecurityUser) principal;
				if (username.equals(loggedUser.getUsername())) {
					List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false); // false代表不包含过期session
					if (null != sessionsInfo && sessionsInfo.size() > 0) {
						for (SessionInformation sessionInformation : sessionsInfo) {
							sessionInformation.expireNow();
						}
					}
				}
			}
		}
		return Result.resultOk();
	}

}
