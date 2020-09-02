package com.tengzhi.business.sale.processProduct.incomingMaterialReturn.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;
import com.tengzhi.business.sale.processProduct.incomingMaterialReturn.service.IncomingMaterialReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/sale/processProduct/incomingMaterialReturn")
public class IncomingMaterialReturnController {

    @Autowired
    private IncomingMaterialReturnService incomingMaterialReturnService;

    @GetMapping("/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 区分模块的参数入口路径
     * @param paramType
     * @param request
     * @param mv
     * @return
     */
    @GetMapping(value = {"{paramMod}/{paramType}/list.html"})
    public ModelAndView toPage(@PathVariable String paramMod,@PathVariable String paramType,
                               HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
        mv.setView(new RedirectView(String.format("/sale/processProduct/incomingMaterialReturn/%s",servletPath)));
        request.getParameterMap().forEach((key,value) ->{
            mv.addObject(key,value);
        });
        mv.addObject("paramType",paramType);
        mv.addObject("paramMod",paramMod);
        //是否定向
        mv.addObject("orient",true);
        return mv;
    }


    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "/getSrchTopList")
    public Result getSrchTopList(BaseDto baseDto) throws Exception {
        return incomingMaterialReturnService.getSrchTopList(baseDto).getResult();
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "/getSrchBottomList")
    public Result getSrchBottomList(BaseDto baseDto) throws IOException {
        return incomingMaterialReturnService.getSrchBottomList(baseDto).getResult();
    }

    /**
     *
     * @description:
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody ECkInVo vo) throws Exception {
        return  incomingMaterialReturnService.saveData(ECkInVo.initECgContractVo(vo));
    }

    @PutMapping(value = "add.html")
    public Result modify(@RequestBody ECkInVo vo) throws Exception {
        return incomingMaterialReturnService.updateData(ECkInVo.initECgContractVo(vo));
    }


    /**
     * @Param: [vo]
     * @description:
     */
    @GetMapping(value = "getByNote/{inNote}")
    public Result getById(@PathVariable(value = "inNote") String inNote) {
        return Result.resultOk(incomingMaterialReturnService.findByInNote(inNote));
    }

    @DeleteMapping(value = "delete/{inNote}")
    public Result delete(@PathVariable(value = "inNote") String inNote) throws Exception {
        incomingMaterialReturnService.deleteById(inNote);
        return Result.resultOk();
    }

    /**
     * @throws IOException
     * @Param: [vo]
     *
     */
    @PostMapping("/getReturnList")
    public Result getReturnList(BaseDto baseDto) throws IOException {
        return incomingMaterialReturnService.getReturnList(baseDto).getResult();
    }

    @PutMapping(value = "getFlag/{inNote}/{flag}")
    public Result getDjFlag(@PathVariable(value = "inNote") String inNote,@PathVariable(value = "flag") String flag) throws Exception {
        return incomingMaterialReturnService.getFlag(inNote,flag);
    }

    @PutMapping(value = "/confirm/{inNote}")
    public Result ok(@PathVariable(value = "inNote") String inNote) throws Exception {
        return incomingMaterialReturnService.setFlag(inNote,"确认");
    }
    @PutMapping(value = "/cancel/{inNote}")
    public Result no(@PathVariable(value = "inNote") String inNote) throws Exception {
        return incomingMaterialReturnService.setFlag(inNote,"登记");
    }

}
