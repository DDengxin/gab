package com.tengzhi.business.project.projectArchives.xmda.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.project.projectArchives.xmda.model.EXmXmda;
import com.tengzhi.business.project.projectArchives.xmda.service.XmdaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/project/projectArchives/xmda/")
public class XmDaController {


    @Autowired
    private  XmdaService xmdaService;

    @GetMapping("/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    @PostMapping(value = "xmDaList.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return xmdaService.findAll(baseDto).getResult();
    }

    /**
     *获取一条记录
     * @param xmId
     * */
    @GetMapping(value = "getById/{xmId}")
    public Result getById(@PathVariable String xmId) {
        return Result.resultOk(xmdaService.findByXmId(xmId));
    }

    /**
     * 新增一条记录
     * **/
    @PostMapping(value = "add.html")
    public Result add(@RequestBody EXmXmda eXmXmda) throws Exception{
        eXmXmda = xmdaService.save(eXmXmda);
        return Result.resultOk(eXmXmda.getXmId());
    }
    /**
     *
     *保存对象信息
     * @param eXmXmda
     * */
    @PostMapping(value = "add.html/{xmId}")
    public Result getById(@RequestBody EXmXmda eXmXmda) throws Exception {
        xmdaService.save(eXmXmda);
        return Result.resultOk();
    }
    /**
     *
     *修改数据
     * @param eXmXmda
     * */
    @PutMapping("add.html")
    public Result modify(@RequestBody EXmXmda eXmXmda) {
        xmdaService.update(eXmXmda);
        return Result.resultOk(eXmXmda.getXmId());
    }

    /**
     * 状态判断
     * @param baseDto
     * */

    @PutMapping(value = "/getXmFlag")
    public Result getXmFlag(@RequestBody BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        return xmdaService.getXmFlag(String.valueOf(map.get("xmId")), String.valueOf(map.get("xmFlag")));
    }

    /**
     * 状态切换至核准
     * @param xmId
     * */
    @PutMapping(value = "/ok/{xmId}")
    public Result ok(@PathVariable(value = "xmId") String xmId) throws Exception {
        return xmdaService.setxmFlag(xmId, "核准");
    }

    /**
     *取消确认状态
     * @param xmId
     * */
    @PutMapping(value = "/no/{xmId}")
    public Result no(@PathVariable(value = "xmId") String xmId) throws Exception {
        return xmdaService.setxmFlag(xmId, "登记");
    }

    /**
     * 删除对象数据
     * @param xmId
     * **/
    @DeleteMapping(value = "deleteById/{xmId}")
    public Result delete(@PathVariable(value = "xmId") String xmId) {
        xmdaService.deleteByXmId(xmId);
        return Result.resultOk();
    }
}
