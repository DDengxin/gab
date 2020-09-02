package com.tengzhi.business.system.main.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.system.main.model.SysUserTodo;
import com.tengzhi.business.system.right.model.SysRight;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 王翔
 * @create 2020-04-29
 */
public interface SysMainService {

    /**
     * 获取用户菜单中用户可选的菜单列表(排除没有权限的和已选中的)
     * @param baseDto
     * @return
     */
    BasePage<SysRight> getUserMenuList(BaseDto baseDto);

    List<SysRight> ShowMenu();

    void sava(List<Map<String,Object>> list);

    void update(String id);

	ModelAndView getSetting(ModelAndView mv);

	void up(String id);

    void ap(String id);

    void update(SysUserTodo sysUserTodo);

    void add(SysUserTodo sysUserTodo);

    /**
     * 获取首页用户自定义图表
     * @return
     */
    List<Map> getDatachart();

    void EmptyCache();

}
