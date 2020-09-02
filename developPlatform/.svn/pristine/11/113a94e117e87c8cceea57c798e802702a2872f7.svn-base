package com.tengzhi.business.system.org.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.org.model.SysOrg;
import com.tengzhi.business.system.org.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王翔
 * @create 2020-04-12
 */
@RestController
@RequestMapping("/system/org/")
public class SysOrgControler {
    @Autowired
    private SysOrgService sysOrgService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }


    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "org.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return sysOrgService.findAll(baseDto).getResult();
    }


    /**
     * 通过ID获取对象
     *
     * @param orgId
     * @return
     */
    @GetMapping(value = "org.html/{orgId}")
    public Result getById(@PathVariable String orgId) {
        return Result.resultOk(sysOrgService.findByOrgId(orgId));
    }


    /**
     * 新增
     *
     * @return
     */
    @PostMapping("add.html")
    public Result add(@RequestBody SysOrg sysOrg) throws Exception {
        sysOrg = sysOrgService.save(sysOrg);
        return Result.resultOk(sysOrg.getOrgId());
    }


    /**
     * 修改
     *
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody SysOrg sysOrg) {
        sysOrgService.update(sysOrg);
        return Result.resultOk(sysOrg.getOrgId());
    }

    /**
     * 删除数据
     *
     * @return
     */
    @DeleteMapping(value = "org.html/{tireId}")
    public Result delete(@PathVariable String tireId) {
        return sysOrgService.deleteByTireId(tireId);
    }

    /**
     * 获取账套列表
     *
     * @param orgIds
     * @return
     */
    @GetMapping("combobox/getOrgList")
    public List<SelectVo> getComboboxOrgList(@RequestParam(name = "ids", required = false) String orgIds) {
        return sysOrgService.findAllEnableBean(orgIds).stream().map(sysOrg -> {
            return new SelectVo(sysOrg.getOrgId(), sysOrg.getCorpExp());
        }).collect(Collectors.toList());
    }

    /**
     * 获取公司名称列表
     *
     * @param orgIds
     * @return
     */
    @GetMapping("combobox/getOrgNameList")
    public List<SelectVo> getOrgNameList(@RequestParam(name = "ids", required = false) String orgIds) {
        return sysOrgService.findAllEnableBean(orgIds).stream().map(sysOrg -> {
            return new SelectVo(sysOrg.getOrgId(), sysOrg.getOrgName());
        }).collect(Collectors.toList());
    }

    /**
     * 获取行业下拉
     *
     * @return
     */
    @GetMapping("combobox/getIndustyList")
    public List<SelectVo> getIndustyList() {
        return sysOrgService.getIndustyList();
    }

    @GetMapping("combobox/getCorpList")
    public List<SelectVo> getCorpList() {
        return sysOrgService.getCorpList();
    }
}

