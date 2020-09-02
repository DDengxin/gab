package com.tengzhi.business.system.developarchive.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.developarchive.model.archiveDoc;
import com.tengzhi.business.system.developarchive.service.archiveService;
import com.tengzhi.business.system.developdoc.service.docService;
import com.tengzhi.business.system.right.constant.RightModuleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author zs
 * @create 2020-08-24
 */
@RestController
@RequestMapping("/system/developarchive")
public class ArchiveController {

    @Autowired
    private archiveService archiveService;

    @GetMapping("*")
    public ModelAndView view(ModelAndView modelAndView){
        return modelAndView;
    }

    @PostMapping("archiveDataFindAll")
    public Result archiveDataFindAll(BaseDto baseDto){
       return archiveService.archiveDataFindAll(baseDto).getResult();
    }

    @PostMapping("archiveparentFindAll")
    public Result archiveparentFindAll(BaseDto baseDto){
        return archiveService.archiveparentFindAll(baseDto);
    }

    @PostMapping("archiveDataSava")
    public Result archiveDataSava(@RequestBody archiveDoc archiveDoc) throws Exception {
        return archiveService.archiveDataSava(archiveDoc);
    }

    @PutMapping("archiveDataSava")
    public Result archiveDataUpdate(@RequestBody archiveDoc archiveDoc){
        return archiveService.archiveDataUpdate(archiveDoc);
    }

    @GetMapping("archiveByDataId")
    public Result archiveByDataId(String uuid){
        return archiveService.archiveByDataId(uuid);
    }


    @DeleteMapping("archiveDataDelete/{uuid}")
    public Result archiveDataDelete(@PathVariable String uuid){
        return archiveService.archiveDataDelete(uuid);
    }

    @GetMapping(value = "/selectsubclass")
    public List<SelectVo> Selectsubclass() {
        return archiveService.Selectsubclass();
    }
    @GetMapping(value = "/selectlabel")
    public List<SelectVo> Selectlabel() {
        return archiveService.Selectlabel();
    }

    /**
     * @param menuid 菜单ID
     * @param typestr 类型 备用，当前只查询操作手册
     * @return
     */
    @GetMapping("archiveByMendata")
    public Result archiveByMendata(String menuid,String typestr){
        return archiveService.archiveByMendata(menuid,typestr);
    }

}
