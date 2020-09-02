package com.tengzhi.business.xt.getINOut.rylf.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.getINOut.rylf.model.EXtRylf;
import com.tengzhi.business.xt.getINOut.rylf.service.RylfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/xt/getInOut/rylf")
public class RylfController {

    @Autowired
    private RylfService rylfService;

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
    public Result getList(BaseDto baseDto)throws IOException{
        return rylfService.findAll(baseDto).getResult();
    }

    /**
     * 新增
     * @param eXtRylf
     * @return
     * @throws Exception
     */
    @PostMapping("add.html")
    public Result add(@RequestBody EXtRylf eXtRylf) throws Exception{
        EXtRylf rylf = rylfService.save(eXtRylf);
        return Result.resultOk(rylf.getNote());
    }

    /**
     * 编辑
     * @param eXtRylf
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody EXtRylf eXtRylf) {
        rylfService.update(eXtRylf);
        return Result.resultOkMsg("修改成功");
    }

    /**
     * 获取一条数据
     * @param note
     * @return
     */
    @GetMapping("getById/{note}")
    public Result getById(@PathVariable String note){
        return Result.resultOk(rylfService.finById(note));
    }

    /**
     * 删除
     * @param note
     * @return
     */
    @DeleteMapping("delete/{note}")
    public Result delete(@PathVariable String note){
        rylfService.deleteByNote(note);
        return Result.resultOkMsg("删除成功");
    }

    /**
     * 获取状态
     * @param note
     * @param flag
     * @return
     */
    @PutMapping("getFlag/{note}/{flag}")
    public Result getFlag(@PathVariable String note,@PathVariable String flag){
        return rylfService.getFlag(note,flag);
    }

    /**
     * 确认
     * @param note
     * @return
     */
    @PutMapping("confirm/{note}")
    public Result confirm(@PathVariable String note){
        return rylfService.confirm(note);
    }

    /**
     * 取消确认
     * @param note
     * @return
     */
    @PutMapping("cancle/{note}")
    public Result cancle(@PathVariable String note){
        return rylfService.cancle(note);
    }

}
