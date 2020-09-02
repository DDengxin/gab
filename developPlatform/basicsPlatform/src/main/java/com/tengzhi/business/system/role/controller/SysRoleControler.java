package com.tengzhi.business.system.role.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.role.model.SysRole;
import com.tengzhi.business.system.role.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/system/role/")
public class SysRoleControler {
    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "role.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return sysRoleService.findAll(baseDto);
    }


    @PostMapping(value = "auth.html")
    public Result getAuthAll(BaseDto baseDto) {
        return Result.resultOk(sysRoleService.findSysRightAll(baseDto));
    }

    /**
     * 通过ID获取对象
     *
     * @param roleId
     * @return
     */
    @GetMapping(value = "role.html/{roleId}")
    public Result getById(@PathVariable String roleId) {
        return Result.resultOk(sysRoleService.findByRoleId(roleId));
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping("add.html")
    public Result add(@RequestBody SysRole sysRole) throws Exception {
        sysRole = sysRoleService.save(sysRole);
        return Result.resultOk(sysRole.getRoleId());
    }

    /**
     * 修改
     *
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody SysRole sysRole) {
        sysRoleService.update(sysRole);
        return Result.resultOk(sysRole.getRoleId());
    }

    /**
     * 删除数据
     *
     * @return
     */
    @DeleteMapping(value = "/role.html/{roleId}")
    public Result delete(@PathVariable(value = "roleId") String roleId) {
        sysRoleService.deleteByRoleId(roleId);
        return Result.resultOk();
    }


    /**
     * 保存用户授权
     *
     * @param map
     * @return
     */
    @PostMapping("auth_add.html")
    public Result saveUserAuth(@RequestBody Map<String, Object> map) {
        sysRoleService.saveUserAuth(map);
        return Result.resultOkMsg("授权成功!");
    }


}
