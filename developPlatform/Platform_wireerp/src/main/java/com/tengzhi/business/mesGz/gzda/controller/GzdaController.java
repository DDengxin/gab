package com.tengzhi.business.mesGz.gzda.controller;

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.mesGz.gzda.service.GzcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mesGz/gzda/")
public class GzdaController {

    @Autowired
    private GzcodeService gzcodeService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "gzcode/list")
    @Log("查询工装档案信息")
    public Result getInboxList(BaseDto baseDto) throws IOException {
        return gzcodeService.findAll(baseDto).getResult();
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "gzmj.html")
    public List<Map> getList(BaseDto baseDto) throws IOException {
        return gzcodeService.findGzmj(baseDto);
    }

    /**
     * 删除数据列表
     *
     * @return
     */
    @DeleteMapping(value = "l/delete/cpcode/{cpcodeId}")
    public Result delete(@PathVariable(value = "cpcodeId") String cpcodeId) {
        return Result.resultOk();
    }

}
