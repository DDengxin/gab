package com.tengzhi.business.resouces.controller;

import com.tengzhi.business.resouces.service.impl.DictServiceImpl;
import com.tengzhi.business.resouces.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dict")
public class DictController {
    @Autowired
    DictServiceImpl dictService;

    @GetMapping("/requestMethod")
    public List<SelectVo> getRequestMethod() {
        return dictService.getRequestMethod();
    }

    @GetMapping("/boolean")
    public List<SelectVo> getRequestMethod(@RequestParam(value = "t",required = false) String trueText, @RequestParam (value = "f",required = false) String falseText) {
        return dictService.getBoolean(trueText, falseText);
    }

    @GetMapping("/label")
    public Set<SelectVo> getlabel() {
        return dictService.getlabel();
    }
}