package com.tengzhi.business.resouces.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.service.TemplateFileService;
import com.tengzhi.business.resouces.vo.FileTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resouces")
public class TemplateFileController {
    @Autowired
    TemplateFileService templateFileService;

    @PostMapping("/templateFile.html")
    public Result getList(BaseDto baseDto) {
        return Result.resultOk(new FileTreeVo[]{templateFileService.getTemplateFiles()});
    }
}
