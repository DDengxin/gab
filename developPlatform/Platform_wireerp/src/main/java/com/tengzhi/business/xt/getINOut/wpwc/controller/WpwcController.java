package com.tengzhi.business.xt.getINOut.wpwc.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.getINOut.wpwc.model.EXtWpwc;
import com.tengzhi.business.xt.getINOut.wpwc.service.WpwcService;
import com.tengzhi.business.xt.getINOut.wpwc.vo.EXtWpwcVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/xt/getInOut/wpwc")
public class WpwcController {

    @Autowired
    private WpwcService wpwcService;

    @GetMapping(value="/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/getList")
    public Result getList(BaseDto baseDto) throws IOException{
        return wpwcService.findAll(baseDto).getResult();
    }

    /**
     * 新增
     * @param eXtWpwcVo
     * @return
     * @throws Exception
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody EXtWpwcVo eXtWpwcVo) throws Exception{
        EXtWpwc eXtWpwc = wpwcService.save(EXtWpwcVo.initEXtWpwcVo(eXtWpwcVo));
        return Result.resultOk(eXtWpwc.getNote());
    }

    /**
     * 修改数据
     * @param eXtWpwcVo
     * @return
     */
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody EXtWpwcVo eXtWpwcVo){
        wpwcService.update(EXtWpwcVo.initEXtWpwcVo(eXtWpwcVo));
        return Result.resultOk("修改成功");
    }

    /**
     * 获取一条数据
     * @param note
     * @return
     */
    @GetMapping(value = "getById/{note}")
    public Result getById(@PathVariable String note){
        return Result.resultOk(wpwcService.findByNote(note));
    }

    /**
     * 删除
     * @param note
     * @return
     */
    @DeleteMapping(value = "delete/{note}")
    public Result delete(@PathVariable String note){
        wpwcService.deleteByNote(note);
        return Result.resultOk("删除成功");
    }

    @PutMapping(value = "/getFlag/{note}/{flag}")
    public Result getFlag(@PathVariable (value = "note") String note,@PathVariable (value = "flag") String flag ){
        return wpwcService.getFlag(note,flag);
    }

    /**
     * 状态变更为确认
     * @param note
     * @return
     */
    @PutMapping(value = "confirm/{note}")
    public Result confirm(@PathVariable(value = "note")String note){
        return Result.resultOk(wpwcService.confirm(note));
    }

    /**
     * 取消确认状态
     * @param note
     * @return
     */
    @PutMapping(value = "cancle/{note}")
    public Result cancle(@PathVariable(value = "note")String note){
        return Result.resultOk(wpwcService.cancle(note));
    }

    /**
     * 获取外出物品明细
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/getWpwcMx")
    public Result getWpwcMx(BaseDto baseDto) throws IOException{
        return wpwcService.getWpwcMx(baseDto).getResult();
    }
}
