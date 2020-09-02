package com.tengzhi.business.system.modification.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.modification.enums.SysDataModificationTableEnum;
import com.tengzhi.business.system.modification.service.SysDataModificationService;
import com.tengzhi.business.system.workflow.vo.Examine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/data/modification/")
public class SysDataModificationController {
    @Autowired
    private SysDataModificationService sysDataModificationService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "list.html")
    public Result getList(BaseDto baseDto) throws IOException, ParseException {
        return sysDataModificationService.getList(baseDto).getResult();
    }

    /**
     * 新增数据
     *
     * @return
     */
    @PostMapping("add.html")
    public Result add(@RequestBody Map<String,Object> map) throws Exception {
        return sysDataModificationService.save(map);
    }


    /**
     * 新增时搜索
     * @return
     */
    @PostMapping("modifySearch.html")
    public Result modifySearch(BaseDto baseDto) {
        return sysDataModificationService.modifySearch(baseDto);
    }


    /**
     * 更新数据
     *
     * @return
     */
    @PutMapping("add.html")
    public Result update(@RequestBody Map<String,Object> map) throws Exception {
        return sysDataModificationService.update(map);
    }


    /**
     * 删除数据
     *
     * @return
     */
    @DeleteMapping(value = "list.html/{modifyNote}")
    public Result delete(@PathVariable(value = "modifyNote") String modifyNote) {
        sysDataModificationService.deleteById(modifyNote);
        return Result.resultOk();
    }

    /**
     * 数据修改类型-下拉框
     *
     * @return
     */
    @GetMapping(value = "combobox/modifyType")
    public List<SelectVo> ComboboxRightModule() {
        return SelectVo.listValueOfEnum(SysDataModificationTableEnum.class);
    }



    @GetMapping(value = "GetFindId/{id}")
    public Result GetFindId(@PathVariable String id) {
        return Result.resultOk(sysDataModificationService.findByID(id));
    }


    @PutMapping(value = "GetFindId/agree")
    public Result GetFindIdAgree(@RequestBody Examine examine) {
        return sysDataModificationService.dynamicModify(examine);
    }


}
