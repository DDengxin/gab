package com.tengzhi.business.xt.getINOut.clwc.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.getINOut.clwc.model.EXtClwc;
import com.tengzhi.business.xt.getINOut.clwc.service.ClwcService;
import com.tengzhi.business.xt.getINOut.clwc.vo.EXtClwcVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/xt/getInOut/clwc")
public class ClwcController {

    @Autowired
    private ClwcService clwcService;

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
        return clwcService.findAll(baseDto).getResult();
    }

    /**
     * 新增
     * @param eXtClwcVo
     * @return
     * @throws Exception
     */
    @PostMapping("add.html")
    public Result add(@RequestBody EXtClwcVo eXtClwcVo) throws  Exception{
        return clwcService.save(EXtClwcVo.initEXtClwcVo(eXtClwcVo));
    }

    /**
     * 编辑
     * @param eXtClwcVo
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody EXtClwcVo eXtClwcVo){
        return clwcService.update(EXtClwcVo.initEXtClwcVo(eXtClwcVo));
    }

    /**
     * 获取一条数据
     * @return
     */
    @PostMapping("/getById")
    public Result getById(BaseDto baseDto){
        return clwcService.findByNote(baseDto).getResult();
    }

    /**
     * 删除
     * @param note
     * @return
     */
    @DeleteMapping("delete/{note}")
    public Result delete(@PathVariable String note){
        clwcService.deleteByNote(note);
        return Result.resultOk("删除成功");
    }

    /**
     * 获取状态
     * @param note
     * @param flag
     * @return
     */
    @PutMapping("/getFlag/{note}/{flag}")
    public Result getFlag(@PathVariable String note,@PathVariable String flag){
        return clwcService.getFlag(note,flag);
    }

    /**
     * 确认
     * @param note
     * @return
     */
    @PutMapping("confirm/{note}")
    public Result confirm(@PathVariable String note){
        return clwcService.confirm(note);
    }

    /**
     * 取消确认
     * @param note
     * @return
     */
    @PutMapping("cancle/{note}")
    public Result cancle(@PathVariable String note){
        return clwcService.cancle(note);
    }

    /**
     * 获取用车的外出申请
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping("/getWcsqList")
    public Result getWcsqList(BaseDto baseDto) throws IOException{
        return clwcService.getWcsqList(baseDto).getResult();
    }
}
