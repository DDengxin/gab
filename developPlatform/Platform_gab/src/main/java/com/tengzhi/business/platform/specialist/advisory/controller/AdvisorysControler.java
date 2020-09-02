package com.tengzhi.business.platform.specialist.advisory.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.specialist.advisory.model.Advisorys;
import com.tengzhi.business.platform.specialist.advisory.service.AdvisorysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/platform/specialist/advisory")
public class AdvisorysControler {

    @Autowired
    private AdvisorysService AdvisoryService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    @PostMapping(value = "inquiry")
    public Result inquiry(BaseDto baseDto) throws Exception {
            return AdvisoryService.findAll(baseDto).getResult();
    }
    @GetMapping(value = "uniqueQuery/{id}")
    public Result uniqueQuery(@PathVariable String id) {
        return Result.resultOk(AdvisoryService.findById(id));
    }
    @PostMapping("addArticle")
    public Result addArticle(@RequestBody Advisorys advisory){
        AdvisoryService.save(advisory);
        return Result.resultOk();
    }
    @PutMapping("addArticle")
    public Result modify(@RequestBody Advisorys advisory) {
        AdvisoryService.update(advisory);
        return Result.resultOk();
    }
    @DeleteMapping(value = "deleteArticle/{id}")
    public Result delete(@PathVariable String id) {
        AdvisoryService.deleteById(id);
        return Result.resultOk();
    }



}

