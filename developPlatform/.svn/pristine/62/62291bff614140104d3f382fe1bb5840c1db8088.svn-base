package com.tengzhi.business.base.page.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.model.BaseModel;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.base.page.model.SysGrid;
import com.tengzhi.business.base.page.model.SysGridCol;
import com.tengzhi.business.base.page.service.SysGridColService;
import com.tengzhi.business.base.page.service.SysGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/system/common/page/")
public class SysGridControler {

    @Autowired
    private SysGridService sysGridService;
    @Autowired
    private SysGridColService sysGridColService;

    @GetMapping("searchList/dynamicForm.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }
    @PostMapping("searchList/dynamicForm.html")
    public Result getSetting(BaseDto dto) {
        return null;
        //sysGridService.get(gridId);
    }

    /**
     * 公共查询页面
     *
     * @param mv
     * @param gridId
     * @return
     */
    @GetMapping("searchList/{gridId}.html")
    public ModelAndView pageForwart(ModelAndView mv, @PathVariable(value = "gridId") String gridId) {
        //查询主表信息
        SysGrid sysGrid = sysGridService.findById(gridId);
        //查询详表信息
        if (null != sysGrid) {
            mv.addObject("grid", sysGrid);

            List<SysGridCol> sysGridColList = sysGridColService.findByGridIdOrderByGridOrdAsc(gridId);
            mv.addObject("grid_columns", sysGridColList);
        }

        mv.addObject("grid_search_form", null);
        //查询表单信息
        mv.setViewName("/system/common/page/searchList.html");
        return mv;
    }

    @PostMapping("searchList/action/loadGrid/{gridId}")
    public Result loadList(@PathVariable(value = "gridId") String gridId) {
        try {
            return Result.resultOk(sysGridService.loadList(gridId));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.resultError(e);
        }
    }

}
