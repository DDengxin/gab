package com.tengzhi.app.menu.controller;

import com.tengzhi.app.menu.service.impl.MenuServiceImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.notice.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 *  app的Menu菜单和仓库菜单
 */
@RestController
@RequestMapping("/app/menu/")
public class MenuController {
    @Autowired
    private MenuServiceImpl menuService;

    @PostMapping(value = "findAppBottomMenu")
    public Result findAppBottomMenu(String rightModule,String parentId) throws IOException {
        return Result.formPage(menuService.findAppBottomMenu(rightModule,parentId));
    }
}
