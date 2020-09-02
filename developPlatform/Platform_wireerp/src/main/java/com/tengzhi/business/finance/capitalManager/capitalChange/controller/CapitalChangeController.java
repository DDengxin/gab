package com.tengzhi.business.finance.capitalManager.capitalChange.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.capitalManager.capitalChange.model.EFVoucherCapitalChange;
import com.tengzhi.business.finance.capitalManager.capitalChange.service.CapitalChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/finance/capitalManager/capitalChange/")
public class CapitalChangeController {


    @Autowired
    private CapitalChangeService service;


    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 新增一条记录
     * **/
    @PostMapping(value = "add.html")
    public Result add(@RequestBody EFVoucherCapitalChange mode) throws Exception{
        return Result.resultOk(service.save(mode));
    }

    @GetMapping("getById/{ksid}")
    public Result getById(@PathVariable String ksid){
        return Result.resultOk(service.findById(ksid));
    }

    /**
     * 删除对象数据
     * @param ksid
     * **/
    @DeleteMapping(value = "deleteById/{ksid}")
    public Result delete(@PathVariable(value = "ksid") String ksid) {
        service.deleteByNote(ksid);
        return Result.resultOk();
    }


    /**
     *
     *修改数据
     * @param model
     * */
    @PutMapping("add.html")
    public Result modify(@RequestBody EFVoucherCapitalChange model) {
        service.update(model);
        return Result.resultOk();
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "list.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return service.getList(baseDto).getResult();
    }

}
