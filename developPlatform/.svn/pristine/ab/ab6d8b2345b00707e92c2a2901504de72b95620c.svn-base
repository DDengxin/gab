package com.tengzhi.business.xt.dailyRoutine.wcsq.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.dailyRoutine.wcsq.model.EXtWcsq;
import com.tengzhi.business.xt.dailyRoutine.wcsq.service.WcsqService;
import com.tengzhi.business.xt.dailyRoutine.wcsq.vo.EXtWcsqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("xt/dailyRoutine/wcsq")
public class WcsqController {

    @Autowired
    private WcsqService wcsqService;

    @GetMapping(value="/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    @PostMapping(value="/getList")
    public Result getList(BaseDto baseDto) throws IOException{
        return wcsqService.findAll(baseDto).getResult();
    }

    /**
     * 新增数据
     * @param eXtWcsqVo
     * @return
     * @throws Exception
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody EXtWcsqVo eXtWcsqVo) throws Exception{
        EXtWcsq eXtWcsq = wcsqService.save(EXtWcsqVo.initEXtWcsqVo(eXtWcsqVo));
        return Result.resultOk(eXtWcsq.getNote());
    }
    /**
     * 修改数据
     * @param eXtWcsqVo
     * @return
     */
    @PutMapping(value = "add.html")
    public Result modify(@RequestBody EXtWcsqVo eXtWcsqVo) {
        wcsqService.update(EXtWcsqVo.initEXtWcsqVo(eXtWcsqVo));
        return Result.resultOk("修改成功");
    }
    /**
     * 获取一条数据
     * @param note
     * @return
     * @throws Exception
     */
    @GetMapping(value = "getById/{note}")
    public Result getById(@PathVariable String note) {
        return Result.resultOk(wcsqService.findByNote(note));
    }

    /**
     * 删除
     * @param note
     * @return
     */
    @DeleteMapping(value = "delete/{note}")
    public Result delete(@PathVariable String note){
        wcsqService.deleteByNote(note);
        return Result.resultOk("删除成功!");
    }

    @PutMapping(value = "/getFlag/{note}/{flag}")
    public Result getFlag(@PathVariable (value="note")String note,@PathVariable(value = "flag")String flag){
        return wcsqService.getFlag(note,flag);
    }
    /**
     * 状态切换至确认
     * @param note
     * */
    @PutMapping(value = "confirm/{note}")
    public Result confirm(@PathVariable(value = "note") String note) throws Exception {
        return wcsqService.confirm(note);
    }

    /**
     *取消确认状态
     * @param note
     * */
    @PutMapping(value = "cancle/{note}")
    public Result cancle(@PathVariable(value = "note") String note) throws Exception {
        return wcsqService.cancle(note);
    }

    @PostMapping(value = "/getZcmxBynote")
    public Result getZcmxBynote(BaseDto baseDto) throws IOException {
        return wcsqService.getZcmxBynote(baseDto).getResult();
    }


    @PostMapping(value = "/getConfirmList")
    public Result getConfirmList(BaseDto baseDto) throws IOException{
        return wcsqService.getConfirmList(baseDto).getResult();
    }

    @PutMapping(value = "out/{note}")
    public Result out(@PathVariable(value = "note")String note){
        return wcsqService.out(note);
    }

    @PutMapping(value = "back/{note}")
    public Result back(@PathVariable(value = "note")String note){
        return wcsqService.back(note);
    }


    @PostMapping("wcsqAdd.html")
    public Result save(@RequestBody EXtWcsq eXtWcsq)throws IOException{
        EXtWcsq wcsq = wcsqService.save(eXtWcsq);
        return Result.resultOk(wcsq.getNote());
    }

    @PutMapping("wcsqAdd.html")
    public Result update(@RequestBody EXtWcsq eXtWcsq){
        wcsqService.update(eXtWcsq);
        return Result.resultOkMsg("修改成功");
    }
}
