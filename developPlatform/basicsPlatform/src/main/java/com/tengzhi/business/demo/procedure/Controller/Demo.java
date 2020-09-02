package com.tengzhi.business.demo.procedure.Controller;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.demo.procedure.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author 鱼游浅水
 * @create 2020-06-15
 */
@RestController
@RequestMapping("demo/procedure")
public class Demo {

    @Autowired
    ProcedureService procedureService;


    @RequestMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }


    @RequestMapping(value = "/ProcedureCall")
    public Result ProcedureCall() {
        return procedureService.ProcedureCall();
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @RequestMapping("/ProcedureCalls")
    public Result ProcedureCalls() throws IOException {
        return procedureService.ProcedureCalls();
    }

}
