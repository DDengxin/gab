package com.tengzhi.business.system.sms.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.sms.model.SysSmsLog;
import com.tengzhi.business.system.sms.service.impl.SysSmsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/system/sms/")
public class SysSmsControler {
    @Autowired
    private SysSmsServiceImpl sysRoleService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "log.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return sysRoleService.findAll(baseDto).getResult();
    }

    /**
     * 通过ID获取对象
     *
     * @param uuid
     * @return
     */
    @GetMapping(value = "log.html/{uuid}")
    public Result getById(@PathVariable String uuid) {
        return Result.resultOk(sysRoleService.findByUuid(uuid));
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping("add.html")
    public Result add(@RequestBody SysSmsLog sysRole) {
        return Result.resultOk(sysRoleService.send(sysRole));
    }


    /**
     * 删除数据
     *
     *
     * @return
     */
    @DeleteMapping(value = "/log.html/{uuid}")
    public Result delete(@PathVariable(value = "uuid") String uuid) {
        sysRoleService.deleteByUuid(uuid);
        return Result.resultOk();
    }

}
