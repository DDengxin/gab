package com.tengzhi.business.xt.reception.visitors.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.reception.visitors.model.Visitors;
import com.tengzhi.business.xt.reception.visitors.service.VisitorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/xt/reception/visitors/")
public class VisitorsController {

    @Autowired
    VisitorsService VisitorsService;

    @GetMapping(value="*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "getList")
    public Result getList(BaseDto baseDto){
        return VisitorsService.getList(baseDto).getResult();
    }

    /**
     * 新增
     * **/
    @PostMapping(value = "add.html")
    public Result add(@RequestBody Visitors Visitors) throws Exception{
        Visitors = VisitorsService.save(Visitors);
        return Result.resultOk(Visitors.getNote());
    }

    /**
     *
     * 通过id获取对象
     * **/
    @GetMapping(value = "list.html/{note}")
    public Result getById(@PathVariable String note) {
        return Result.resultOk(VisitorsService.findBynote(note));
    }


    /**
     * 修改
     * **/
    @PutMapping("add.html")
    public Result modify(@RequestBody Visitors Visitors) {
        VisitorsService.update(Visitors);
        return Result.resultOk(Visitors.getNote());
    }
    /**
     * 删除
     * **/
    @DeleteMapping(value = "list.html/{note}")
    public Result delete(@PathVariable(value = "note") String note) {
        VisitorsService.deleteById(note);
        return Result.resultOk();
    }



}
