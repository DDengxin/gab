package com.tengzhi.business.ck.assets.idleAssets.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.ck.assets.idleAssets.model.ECkAssets;
import com.tengzhi.business.ck.assets.idleAssets.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @Author: czf
 * @Date:2020-08-19 9:13
 */
@RestController
@RequestMapping("ck/assets/idleAssets")
public class AssetsController {

    @Autowired
    private AssetsService service;

    @GetMapping("/*.html")
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
        return service.findAll(baseDto).getResult();
    }

    /**
     * 新增
     * @param eCkAssets
     * @return
     * @throws Exception
     */
    @PostMapping("add.html")
    public Result add(@RequestBody  ECkAssets eCkAssets) throws Exception{
        ECkAssets assets = service.save(eCkAssets);
        return Result.resultOk(assets.getSid());
    }

    /**
     * 编辑
     * @param eCkAssets
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody ECkAssets eCkAssets){
        service.update(eCkAssets);
        return Result.resultOkMsg("修改成功");
    }

    /**
     * 获取一条数据
     * @param sid
     * @return
     */
    @GetMapping("getById/{sid}")
    public Result getById(@PathVariable String sid){
        return Result.resultOk(service.findById(sid));
    }

    /**
     * 删除
     * @param sid
     * @return
     */
    @DeleteMapping("delete/{sid}")
    public Result deleteById(@PathVariable String sid){
        service.deleteById(sid);
        return Result.resultOkMsg("删除成功");
    }

    /**
     * 状态改变
     * @param sid
     * @return
     */
    @PutMapping("updateFlag/{sid}")
    public Result updateFlag(@PathVariable String sid){
        return service.updateFlag(sid);
    }
}
