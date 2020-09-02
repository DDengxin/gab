package com.tengzhi.business.system.dept.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.dept.model.SysDept;
import com.tengzhi.business.system.dept.service.impl.SysDeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/dept/")
public class SysDeptControler {
    @Autowired
    private SysDeptServiceImpl sysDeptService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "dept.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return sysDeptService.findAll(baseDto).getResult();
    }

    /**
     * 通过ID获取对象
     *
     * @param deptId
     * @return
     */
    @GetMapping(value = "dept.html/{deptId}")
    public Result getById(@PathVariable String deptId) {
        return Result.resultOk(sysDeptService.findByDeptId(deptId));
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping("add.html")
    public Result add(@RequestBody SysDept sysDept) throws Exception {
        sysDept = sysDeptService.save(sysDept);
        return Result.resultOk(sysDept.getDeptId());
    }

    /**
     * 修改
     *
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody SysDept sysDept) {
        sysDeptService.update(sysDept);
        return Result.resultOk(sysDept.getDeptId());
    }


    /**
     * 删除数据pda
     *
     * @return
     */
    @DeleteMapping(value = "dept.html/{tierId}")
    public Result delete(@PathVariable(value = "tierId") String tierId) {
        return sysDeptService.deleteByTierId(tierId);
    }

    @GetMapping("combobox/dept")
    public List<SelectVo> comboboxDept() {
        return sysDeptService.getComboboxList(true).stream().map(sysDept -> {
            return new SelectVo(sysDept.getDeptId(), sysDept.getDeptName());
        }).collect(Collectors.toList());
    }

    @PostMapping("combobox/getDeptTreeList")
    public List<SelectVo> comboboxDeptTreeList() {
        return sysDeptService.getComboboxList(true).stream().map(sysDept -> {
            return new SelectVo(sysDept.getDeptId(), sysDept.getDeptName()) {{
                setPid(sysDept.getParentId());
            }};
        }).collect(Collectors.toList());
    }

    @PostMapping("combobox/getDeptTreeList/{corpId}")
    public List<SelectVo> comboboxDeptTreeList(@PathVariable String corpId) {
        return sysDeptService.getComboboxList(true,corpId).stream().map(sysDept -> {
            return new SelectVo(sysDept.getDeptId(), sysDept.getDeptName()) {{
                setPid(sysDept.getParentId());
            }};
        }).collect(Collectors.toList());
    }

    @PostMapping("combobox/comboboxDeptTreeListByProduction")
    public List<SelectVo> comboboxDeptTreeListByProduction(){
        List<SysDept> sysDepts = sysDeptService.comboboxDeptTreeListByProduction(true);
        List<SelectVo> collect = sysDepts.stream().map(sysDept -> {
            return new SelectVo(sysDept.getDeptId(), sysDept.getDeptName()) {{
                setPid(sysDept.getParentId());
            }};
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 该请求已经废弃(2020-08-03)
     * @param parentId
     * @return
     */
   /*@PostMapping("combobox/getDeptTreeList/{parentId}")
    public List<SelectVo> comboboxDeptTreeList(@PathVariable(value = "parentId") String parentId) {
        return sysDeptService.getComboboxList(parentId);
    }*/

    /**
     * 获取组织架构树
     * @param includeEmployees 是否包含员工信息
     * @return
     */
    @PostMapping("getOrganizationTree")
    public Result getOrganizationTree(@RequestParam(defaultValue = "false",required = false) String includeEmployees ) {
        return sysDeptService.getOrganizationTree("true".equalsIgnoreCase(includeEmployees));
    }
}
