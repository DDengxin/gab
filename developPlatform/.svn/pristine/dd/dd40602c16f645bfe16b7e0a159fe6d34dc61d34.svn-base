package com.tengzhi.business.xt.getINOut.wlcl.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.getINOut.wlcl.model.EXtWlcl;
import com.tengzhi.business.xt.getINOut.wlcl.service.WlclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/xt/getInOut/wlcl")
public class WlclController {

    @Autowired
    private WlclService wlclService;

    @GetMapping(value="/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询列表
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping("/getList")
    public Result getList(BaseDto baseDto) throws IOException{
        return wlclService.findAll(baseDto).getResult();
    }

    /**
     * 新增
     * @param eXtWlcl
     * @return
     * @throws Exception
     */
    @PostMapping("add.html")
    public Result add(@RequestBody  EXtWlcl eXtWlcl) throws Exception{
        EXtWlcl wlcl = wlclService.save(eXtWlcl);
        return Result.resultOk(wlcl.getNote());
    }

    /**
     * 编辑
     * @param eXtWlcl
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody EXtWlcl eXtWlcl){
        wlclService.update(eXtWlcl);
        return Result.resultOk("修改成功");
    }

    /**
     * 获取一条数据
     * @param note
     * @return
     */
    @GetMapping("getById/{note}")
    public Result getById(@PathVariable String note){
        return Result.resultOk(wlclService.findByNote(note));
    }

    /**
     * 删除
     * @param note
     * @return
     */
    @DeleteMapping("delete/{note}")
    public Result delete(@PathVariable String note){
        wlclService.deleteByNote(note);
        return Result.resultOk("删除成功");
    }

    @PutMapping("getFlag/{note}/{flag}")
    public Result getFlag(@PathVariable String note,@PathVariable String flag){
        return wlclService.getFlag(note,flag);
    }

    @PutMapping("confirm/{note}")
    public Result confirm(@PathVariable String note){
        return wlclService.confirm(note);
    }

    @PutMapping("cancle/{note}")
    public Result cancle(@PathVariable String note){
        return wlclService.cancle(note);
    }
}
