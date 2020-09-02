package com.tengzhi.business.xt.logistics.express.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.logistics.express.model.EXtExpress;
import com.tengzhi.business.xt.logistics.express.service.ExpressSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/xt/logistics/express")
public class ExpressController {

    @Autowired
    private ExpressSerive expressSerive;

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
        return expressSerive.findAll(baseDto).getResult();
    }

    /**
     * 新增
     * @param eXtExpress
     * @return
     * @throws Exception
     */
    @PostMapping("add.html")
    public Result add(@RequestBody EXtExpress eXtExpress) throws Exception{
        EXtExpress express = expressSerive.save(eXtExpress);
        return Result.resultOk(express.getNote());
    }

    /**
     * 编辑
     * @param eXtExpress
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody EXtExpress eXtExpress){
        expressSerive.update(eXtExpress);
        return  Result.resultOkMsg("修改成功");
    }

    /**
     * 获取一条数据
     * @param note
     * @return
     */
    @GetMapping("getByNote/{note}")
    public Result getByNote(@PathVariable String note){
        return Result.resultOk(expressSerive.findByNote(note));
    }

    /**
     * 删除
     * @param note
     * @return
     */
    @DeleteMapping("delete/{note}")
    public Result deleteByNote(@PathVariable String note){
        expressSerive.delete(note);
        return Result.resultOkMsg("删除成功");
    }


}
