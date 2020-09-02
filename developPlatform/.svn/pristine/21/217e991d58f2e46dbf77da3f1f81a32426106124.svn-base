package com.tengzhi.business.js.drawing.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.js.drawing.model.EJsCpcodeTdgl;
import com.tengzhi.business.js.drawing.service.DrawingService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/14 0014 09:18
 * @Description:
 */
@RestController
@RequestMapping(value="/js/drawing/")
public class drawingController {

    private DrawingService drawingService;

    @GetMapping(value = {"{paramType}/{paramMod}/list.html"})
    public ModelAndView toPage(@PathVariable String paramType, @PathVariable String paramMod,
                               HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
        mv.setView(new RedirectView(String.format("/js/drawing/%s",servletPath)));
        request.getParameterMap().forEach((key,value) ->{
            mv.addObject(key,value);
        });
        mv.addObject("paramType",paramType);
        mv.addObject("paramMod",paramMod);
        return mv;
    }
    public drawingController(DrawingService drawingService) {
        this.drawingService = drawingService;
    }

    @GetMapping(value = "*.html")
    public ModelAndView index(ModelAndView modelAndView){
        return modelAndView;
    }

    @PostMapping(value="findAll")
    public Result findAll(BaseDto baseDto){
        Result result = drawingService.findAll(baseDto).getResult();
        return result;
    }

    @GetMapping(value="findByNote/{note}")
    public Result findByNote(@PathVariable(name = "note") String note){
        Result result = drawingService.findByNote(note);
        return result;
    }

    @PostMapping(value = "add")
    public Result add(@RequestBody EJsCpcodeTdgl eJsCpcodeTdgl){
        Result addResult = drawingService.add(eJsCpcodeTdgl);
        return addResult;
    }

    @PutMapping(value = "add")
    public Result update(@RequestBody EJsCpcodeTdgl eJsCpcodeTdgl){
        Result addResult = drawingService.update(eJsCpcodeTdgl);
        return addResult;
    }

    @DeleteMapping(value = "deleteByNote/{note}")
    public Result delete(@PathVariable(name = "note") String note){
        Result addResult = drawingService.delete(note);
        return addResult;
    }








}
