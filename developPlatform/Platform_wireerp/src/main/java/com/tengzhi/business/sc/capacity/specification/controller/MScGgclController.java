package com.tengzhi.business.sc.capacity.specification.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.capacity.specification.model.MScGgclVo;
import com.tengzhi.business.sc.capacity.specification.service.MScGgclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/12 0012 14:27
 * @Description:规格产能
 */
@SuppressWarnings("all")
@RestController
@RequestMapping(value = "/sc/capacity/specification/")
public class MScGgclController {

    private MScGgclService mScGgclService;

    @Autowired
    public MScGgclController(MScGgclService mScGgclService) {
        this.mScGgclService = mScGgclService;
    }

    @GetMapping(value="*.html")
    public ModelAndView index(ModelAndView modelAndView){
        return modelAndView;
    }

    /**
     *@role:
     *@Author:      huangbiao
     *@Date:        2020/8/13 0013 9:36
     *@Param:       [baseDto]
     *@return:      com.tengzhi.base.jpa.result.Result
     *@Description: 查询所有数据
     **/
    @PostMapping(value = "/findAll")
    public Result findAll(BaseDto baseDto){
        Result resultAll = mScGgclService.findAll(baseDto).getResult();
        return resultAll;
    }

    @GetMapping(value = "/findCj")
    public List<Map<Object,String>> findCj(){
        List<Map<Object, String>> cjType = mScGgclService.findCjType();
        return cjType;
    }

    @GetMapping(value = "/findCl")
    public List<Map<Object,String>> findCl(){
        List<Map<Object, String>> cjType = mScGgclService.findClType();
        return cjType;
    }

    @GetMapping(value = "/findByClId/{clId}")
    public Result findCl(@PathVariable(name = "clId") String clId){
        Result byClId = mScGgclService.findByClId(clId);
        return byClId;
    }

    @PostMapping(value="/saveUpdateDelete")
    public Result SaveDelete(@RequestBody MScGgclVo mScGgclVo){
        Result result = mScGgclService.OptionMscGgcl(mScGgclVo);
        return result;
    }

    @PutMapping(value="/saveUpdateDelete")
    public Result UpdateDelete(@RequestBody MScGgclVo mScGgclVo){
        Result result = mScGgclService.OptionMscGgcl(mScGgclVo);
        return result;
    }

    @DeleteMapping(value = "/deleteById/{clId}")
    public Result deleteById(@PathVariable(name = "clId") String clId){
        Result result = mScGgclService.deleteByClId(clId);
        return result;
    }












}
