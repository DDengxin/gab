package com.tengzhi.business.sale.processProduct.incomingMaterialWarehouse.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.mesGz.gzck.vo.ECkInVo;
import com.tengzhi.business.sale.processProduct.incomingMaterialWarehouse.service.IncomingMaterialWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/sale/processProduct/incomingMaterialWarehouse")
public class IncomingMaterialWarehouseController {


    @Autowired
    private IncomingMaterialWarehouseService incomingMaterialWarehouseService;


    @GetMapping(value="/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }


    /**
     *
     * @param paramType 产品类型(YL,WL,XC)
     * @param paramMod
     * @param request
     * @param rt
     * @param mv
     * @return
     */
    @GetMapping(value = { "/{paramType}/{paramMod}/list.html" })
    public ModelAndView toPage(@PathVariable String paramType,@PathVariable String paramMod, HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        mv.setView(new RedirectView(String.format("/sale/processProduct/incomingMaterialWarehouse/%s", servletPath)));
        request.getParameterMap().forEach((key, value) -> {
            mv.addObject(key, value);
        });
        mv.addObject("paramType", paramType);
        mv.addObject("paramMod", paramMod);
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "/getSrchTopList")
    public Result getSrchTopList(BaseDto baseDto) throws Exception {
        return incomingMaterialWarehouseService.getSrchTopList(baseDto).getResult();
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "/getSrchBottomList")
    public Result getSrchBottomList(BaseDto baseDto) throws IOException {
        return incomingMaterialWarehouseService.getSrchBottomList(baseDto).getResult();
    }


    @PostMapping(value = "add.html")
    public Result add(@RequestBody ECkInVo vo) throws Exception {
        return  incomingMaterialWarehouseService.save(vo);
    }

    @PutMapping(value = "add.html")
    public Result update(@RequestBody ECkInVo vo) throws Exception {
        return  incomingMaterialWarehouseService.update(ECkInVo.initECgContractVo(vo));
    }

    @PutMapping(value = "/confirm/{inNote}")
    public Result ok(@PathVariable(value = "inNote") String inNote) throws Exception {
        return incomingMaterialWarehouseService.setFlag(inNote,"结算");
    }
    @PutMapping(value = "/cancel/{inNote}")
    public Result no(@PathVariable(value = "inNote") String inNote) throws Exception {
        return incomingMaterialWarehouseService.setFlag(inNote,"登记");
    }

    @PutMapping(value = "/getFlag/{inNote}/{flag}")
    public Result ok(@PathVariable(value = "inNote") String inNote,@PathVariable(value = "flag") String flag) throws Exception {
        return incomingMaterialWarehouseService.getFlag(inNote, flag);
    }

    @PutMapping(value = "/getDjFlag")
    public Result getDjFlag(@RequestBody BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        return incomingMaterialWarehouseService.getFlag(String.valueOf(map.get("inNote")),"登记");
    }

    /**
     * @Param: [vo]
     * @description: Get(获取数据|通过ID获取对象)Restful
     */
    @GetMapping(value = "getById/{inNote}")
    public Result getById(@PathVariable(value = "inNote") String inNote) {
        return Result.resultOk(incomingMaterialWarehouseService.findByInNote(inNote));
    }


    @DeleteMapping(value = "delete/{inNote}")
    public Result delete(@PathVariable(value = "inNote") String inNote) throws Exception {
        incomingMaterialWarehouseService.deleteById(inNote);
        return Result.resultOk();
    }

    /**
     * 委外加工合同选择列表
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping("/getjjhtList")
    public  Result getjjhtList(BaseDto baseDto) throws IOException {
        return incomingMaterialWarehouseService.getjjhtList(baseDto).getResult();
    }


    /**
     * 委外加工合同选择列表(采购)
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping("/getCgWwhtSelectList")
    public  Result getCgWwhtSelectList(BaseDto baseDto) throws IOException {
        return incomingMaterialWarehouseService.getCgWwhtSelectList(baseDto).getResult();
    }


    /**
     * 打印列表
     * @param mv
     * @return
     */
    @GetMapping("/table.html")
    public ModelAndView table(ModelAndView mv,@RequestParam(value="inNote") String inNote) {
        mv = incomingMaterialWarehouseService.table(mv,inNote);
        return mv;
    }

    @PostMapping("/getProductList")
    public Result getProductList(BaseDto baseDto){
        return incomingMaterialWarehouseService.getProductList(baseDto).getResult();
    }
}
