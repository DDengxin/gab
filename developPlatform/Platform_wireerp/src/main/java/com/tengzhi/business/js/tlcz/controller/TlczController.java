package com.tengzhi.business.js.tlcz.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.js.tlcz.model.EJsTlcz;
import com.tengzhi.business.js.tlcz.service.TlczService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @Author: czf
 * @Date:2020-08-20 9:17
 */
@RestController
@RequestMapping("/js/tlcz")
public class TlczController {

    @Autowired
    private TlczService tlczService;

    @GetMapping("/*")
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
    public Result getList(BaseDto baseDto)throws IOException{
        return tlczService.findAll(baseDto).getResult();
    }

    /**
     * 新增
     * @param eJsTlcz
     * @return
     * @throws Exception
     */
    @PostMapping("add.html")
    public Result add(@RequestBody EJsTlcz eJsTlcz) throws  Exception{
        EJsTlcz tlcz = tlczService.save(eJsTlcz);
        return Result.resultOk(tlcz.getCode());
    }

    /**
     * 编辑
     * @param eJsTlcz
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody EJsTlcz eJsTlcz){
        tlczService.update(eJsTlcz);
        return Result.resultOkMsg("修改成功");
    }

    /**
     * 获取一条数据
     * @param code
     * @return
     */
    @GetMapping("getByCode/{code}")
    public Result getByCode(@PathVariable String code){
        return Result.resultOk(tlczService.findByCode(code));
    }

    /**
     * 删除
     * @param code
     * @return
     */
    @DeleteMapping("delete/{code}")
    public Result deleteByCode(@PathVariable String code){
        tlczService.deleteByCode(code);
        return Result.resultOkMsg("删除成功");
    }


}
