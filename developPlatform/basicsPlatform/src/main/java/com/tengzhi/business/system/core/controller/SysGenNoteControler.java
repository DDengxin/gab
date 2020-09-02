package com.tengzhi.business.system.core.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.dept.model.SysDept;
import com.tengzhi.business.system.dept.service.impl.SysDeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/core/gennote/")
public class SysGenNoteControler {
    @Autowired
    private SysGenNoteService sysGenNoteService;

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
    public Result getList(BaseDto baseDto) throws IOException {
        return sysGenNoteService.getList(baseDto).getResult();
    }

   /* *//**
     * 通过ID获取对象
     *
     * @param deptId
     * @return
     *//*
    @GetMapping(value = "dept.html/{deptId}")
    public Result getById(@PathVariable String deptId) {
        return Result.resultOk(sysGenNoteService.findByDeptId(deptId));
    }

    *//**
     * 新增
     *
     * @return
     *//*
    @PostMapping("managerfield.html")
    public Result add(@RequestBody SysDept sysDept) throws Exception {
        sysDept = sysGenNoteService.save(sysDept);
        return Result.resultOk(sysDept.getDeptId());
    }

    *//**
     * 修改
     *
     * @return
     *//*
    @PutMapping("managerfield.html")
    public Result modify(@RequestBody SysDept sysDept) {
        sysGenNoteService.update(sysDept);
        return Result.resultOk(sysDept.getDeptId());
    }*/

}
