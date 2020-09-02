package com.tengzhi.business.system.position.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.position.model.SysPosition;
import com.tengzhi.business.system.position.service.SysPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/system/position/")
public class SysPositionControler {
    @Autowired
    private SysPositionService sysRoleService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "position.html")
    public Result getList(BaseDto baseDto) throws IOException {
            return sysRoleService.findAll(baseDto).getResult();
    }

    /**
     * 通过ID获取对象
     *
     * @param positionId
     * @return
     */
    @GetMapping(value = "position.html/{positionId}")
    public Result getById(@PathVariable String positionId) {
        return Result.resultOk(sysRoleService.findByPositionId(positionId));
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping("add.html")
    public Result add(@RequestBody SysPosition sysposition) throws Exception{
        sysposition = sysRoleService.save(sysposition);
        return Result.resultOk(
                "新增成功"
        );
    }

    /**
     * 修改
     *
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody SysPosition sysPosition) {
        sysRoleService.update(sysPosition);
        return Result.resultOk("修改成功");
    }
    /**
     * 删除数据
     *
     *
     * @return
     */
    @DeleteMapping("position.html/{positionId}/{TierId}")
    public Result delete(@PathVariable String positionId,@PathVariable String TierId) {
        return sysRoleService.deleteByPositionId(positionId,TierId);
    }

    /**
     * @Param: [list]
     * @return: com.tengzhi.base.jpa.result.Result
     * @Author: 王翔
     * @Date: 2020/4/21 11:28
     * @description: 原生Sql语句常规新增
     */
    @PostMapping("positionAuth_add.html")
    public Result PositionAuthAdd(@RequestBody Map<String,Object> map){
        sysRoleService.PositionAuthSave(map);
        return Result.resultOkMsg("授权成功!");
    }

    @PostMapping(value = "position_auth.html")
    public Result getPositionAuthAll(BaseDto baseDto) throws IOException {
        return sysRoleService.findSysPositionRightAll(baseDto).getResult();
    }

}
