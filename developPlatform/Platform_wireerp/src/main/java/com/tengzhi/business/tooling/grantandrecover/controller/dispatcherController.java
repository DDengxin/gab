package com.tengzhi.business.tooling.grantandrecover.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.tooling.grantandrecover.service.dispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-06-28
 */
@RestController
@RequestMapping("/tooling/*/")
public class dispatcherController {

    @Autowired
    private dispatcherService dispatcherservice;


    /**
    * @Param: [model]
    * @return: org.springframework.ui.Model
    * @Author: 鱼油浅水
    * @Date: 2020/6/28 13:43
    * @description: 首页第一次渲染(不用.html减少没必要的静态文件名称暴露)
    */
    @RequestMapping("*")
    public ModelAndView view(ModelAndView mv){
        return mv;
    }


    /**
    * @Param: [baseDto]
    * @return: com.tengzhi.base.jpa.result.Result
    * @Author: 鱼油浅水
    * @Date: 2020/6/28 17:52
    * @description: 工装首页查询
    */
    @RequestMapping("sendAndReceiveToolingHome")
    public Result getList(BaseDto baseDto) throws IOException {
        return dispatcherservice.findAll(baseDto);
    }

    /**
    * @Param: [baseDto]
    * @return: com.tengzhi.base.jpa.result.Result
    * @Author: 鱼油浅水
    * @Date: 2020/6/28 18:19
    * @description: 查询这一单的数据
    */
    @RequestMapping("sendAndReceiveToolingHomeYo")
    public Result querySingleById(BaseDto baseDto) throws IOException {
        return dispatcherservice.querySingleById(baseDto);
    }

    /**
    * @Param: [Note]
    * @return: com.tengzhi.base.jpa.result.Result
    * @Author: 鱼油浅水
    * @Date: 2020/6/28 22:25
    * @description: 查询视图模具领料出入库产品记录
    */
    @RequestMapping("queryAllOutboundStorage")
    public Result queryAllOutboundStorage(BaseDto baseDto){
        return dispatcherservice.queryAllOutboundStorage(baseDto);
    }

    /**
    * @Param: [map]
    * @return: com.tengzhi.base.jpa.result.Result
    * @Author: 鱼油浅水
    * @Date: 2020/6/28 22:40
    * @description:模具保存
    */
    @PostMapping("add")
    public Result toolingSave(@RequestBody Map<String,Object> map) throws Exception {
        return dispatcherservice.toolingSave(map);
    }

    /**
    * @Param: [map]
    * @return: com.tengzhi.base.jpa.result.Result
    * @Author: 鱼油浅水
    * @Date: 2020/6/28 22:41
    * @description:模具修改
    */
    @PutMapping("add")
    public Result toolingUpdate(@RequestBody Map<String,Object> map){
        return dispatcherservice.toolingUpdate(map);
    }


    /**
     * @Param: [Note]
     * @return: com.tengzhi.base.jpa.result.Result
     * @Author: 鱼油浅水
     * @Date: 2020/6/28 22:42
     * @description: 模具确认
     */
    @PutMapping("confirm")
    public Result toolingConfirm(@RequestBody Map<String,Object> map) throws Exception {
        return dispatcherservice.toolingconfirm(map);
    }


    /**
    * @Param: [Note]
    * @return: com.tengzhi.base.jpa.result.Result
    * @Author: 鱼油浅水
    * @Date: 2020/6/28 22:41
    * @description: 模具删除
    */
    @RequestMapping("toolingDelete/{Note}")
    public Result toolingUpdate(@PathVariable String Note){
        return dispatcherservice.toolingDelete(Note);
    }

    /**
    * @Param: [Act]
    * @return: com.tengzhi.base.jpa.result.Result
    * @Author: 鱼油浅水
    * @Date: 2020/7/2 0:26
    * @description: 前端过滤分析当前数据是否存在已经登记的数据
    */
    @RequestMapping("enquiryRegistration")
    public Result enquiryRegistration(String mjAct){
        return dispatcherservice.enquiryRegistration(mjAct);
    }

    @RequestMapping("selectMoldStatus")
    public List<SelectVo> selectMoldStatus(){
        return dispatcherservice.SELECT_VOS();
    }

}
