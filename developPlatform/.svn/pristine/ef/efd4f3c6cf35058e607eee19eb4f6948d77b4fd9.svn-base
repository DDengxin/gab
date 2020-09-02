package com.tengzhi.business.resouces.service.impl;

import com.tengzhi.business.resouces.service.TemplateFileService;
import com.tengzhi.business.resouces.vo.FileTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.IOException;

@Service
public class TemplateFileServiceImpl implements TemplateFileService {
    @Value("${spring.thymeleaf.prefix}")
    public String thymeleafPrefix;

    @Autowired
    WebApplicationContext webApplicationContext;

    private File getTemplateFilePath() {
        try {
            return webApplicationContext.getResource(thymeleafPrefix).getFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public FileTreeVo getTemplateFiles() {
        return FileTreeVo.formFile(getTemplateFilePath(), new String[]{".html"});
    }
}
