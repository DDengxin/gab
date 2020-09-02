package com.tengzhi.business.ck.yw.warehouseWarn.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.ck.yw.warehouseWarn.service.WarehouseWarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@RequestMapping("/ck/yw/warehouseWarn")
public class WarehouseWarnControler {

    @Autowired
    private WarehouseWarnService warehouseWarnService;

    @GetMapping("/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * @param cpcodeType 产品分类
     * @param mv         ModelAndView
     * @param request    Request对象
     * @return
     */
    @GetMapping("/{cpcodeType}/list.html")
    public ModelAndView toPage(@PathVariable(value = "cpcodeType") String cpcodeType, ModelAndView mv, HttpServletRequest request) {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        mv.setView(new RedirectView(String.format("/ck/yw/warehouseWarn/%s", servletPath)));
        request.getParameterMap().forEach((key, value) -> {
            mv.addObject(key, value);
        });
        mv.addObject("cpcodeType", cpcodeType);
        return mv;
    }

    /**
     * 查询库存告警数据
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/list")
    public Result getList(BaseDto baseDto) throws Exception {
        return warehouseWarnService.QueryPageWarnList(baseDto).getResult();
    }

}
