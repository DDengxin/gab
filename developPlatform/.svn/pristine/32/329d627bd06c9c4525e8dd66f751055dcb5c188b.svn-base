package com.tengzhi.business.xt.getINOut.wlwp.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.getINOut.wlwp.model.EXtWlwp;
import com.tengzhi.business.xt.getINOut.wlwp.service.WlwpService;
import com.tengzhi.business.xt.getINOut.wlwp.vo.EXtWlwpVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/xt/getInOut/wlwp")
public class WlwpController {

    @Autowired
    WlwpService wlwpService;

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
    @PostMapping("/getList")
    public Result getList(BaseDto baseDto) throws IOException{
        return  wlwpService.findAll(baseDto).getResult();
    }

    /**
     * 新增
     * @param eXtWlwpVo
     * @return
     * @throws Exception
     */
    @PostMapping("add.html")
    public Result add(@RequestBody EXtWlwpVo eXtWlwpVo) throws  Exception{
        EXtWlwp eXtWlwp = wlwpService.save(EXtWlwpVo.initEXtWlwpVo(eXtWlwpVo));
        return Result.resultOk(eXtWlwp.getNote());
    }

    /**
     * 编辑
     * @param eXtWlwpVo
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody EXtWlwpVo eXtWlwpVo){
        wlwpService.update(EXtWlwpVo.initEXtWlwpVo(eXtWlwpVo));
        return  Result.resultOk("修改成功");
    }

    /**
     * 读取一条数据
     * @param note
     * @return
     */
    @GetMapping("getById/{note}")
    public Result getById(@PathVariable String note){
        return Result.resultOk(wlwpService.findByNote(note));
    }

    @DeleteMapping("delete/{note}")
    public Result delete(@PathVariable String note){
        wlwpService.deleteByNote(note);
        return Result.resultOk("删除成功");
    }

    /**
     * 读取状态
     * @param note
     * @param flag
     * @return
     */
    @PutMapping("/getFlag/{note}/{flag}")
    public Result getFlag(@PathVariable String note,@PathVariable String flag){
        return wlwpService.getFlag(note,flag);
    }

    /**
     * 确认
     * @param note
     * @return
     */
    @PutMapping("confirm/{note}")
    public  Result confirm(@PathVariable String note){
        return wlwpService.confirm(note);
    }

    /**
     * 取消确认
     * @param note
     * @return
     */
    @PutMapping("cancle/{note}")
    public Result cancle(@PathVariable String note){
        return wlwpService.cancle(note);
    }

    @PostMapping("/getWlwpMx")
    public Result getWlwpMx(BaseDto baseDto) throws  IOException{
        return wlwpService.getWlwpMx(baseDto).getResult();
    }
}
