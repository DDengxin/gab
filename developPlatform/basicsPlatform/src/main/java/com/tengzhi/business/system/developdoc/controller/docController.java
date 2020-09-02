package com.tengzhi.business.system.developdoc.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.developdoc.model.developDoc;
import com.tengzhi.business.system.developdoc.service.docService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 鱼游浅水
 * @create 2020-08-01
 */
@RestController
@RequestMapping("/system/developdoc")
public class docController {

    @Autowired
    private docService docService;

    @GetMapping("*")
    public ModelAndView view(ModelAndView modelAndView){
        return modelAndView;
    }

    @PostMapping("docDataFindAll")
    public Result docDataFindAll(BaseDto baseDto){
       return docService.docDataFindAll(baseDto);
    }

    @PostMapping("docDataSava")
    public Result docDataSava(@RequestBody developDoc developDoc) throws Exception {
        return docService.docDataSava(developDoc);
    }

    @PutMapping("docDataSava")
    public Result docDataUpdate(@RequestBody developDoc developDoc){
        return docService.docDataUpdate(developDoc);
    }

    @GetMapping("docByDataId")
    public Result docByDataId(String doc_id){
        return docService.docByDataId(doc_id);
    }


    @DeleteMapping("docDataDelete/{doc_id}")
    public Result docDataDelete(@PathVariable String doc_id){
        return docService.docDataDelete(doc_id);
    }

}
