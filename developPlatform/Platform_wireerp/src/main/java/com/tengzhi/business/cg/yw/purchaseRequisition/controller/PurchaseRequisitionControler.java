package com.tengzhi.business.cg.yw.purchaseRequisition.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseRequisition.service.ECgRequisitionService;
import com.tengzhi.business.cg.yw.purchaseRequisition.vo.EcgRequisitiontVo;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/cg/yw/purchaseFile/purchaseRequisition")
public class PurchaseRequisitionControler {

    @Autowired
    private ECgRequisitionService eCgRequisitionService;

    @Autowired
    private SysParamService sysParamService;

    @GetMapping("/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 区分模块的参数入口路径
     *
     * @param cgStype
     * @param request
     * @param rt
     * @param mv
     * @return
     */
    @GetMapping(value = {"{cgStype}/list.html"})
    public ModelAndView toPage(@PathVariable String cgStype,
                               HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        mv.setView(new RedirectView(String.format("/cg/yw/purchaseFile/purchaseRequisition/%s", servletPath)));
        request.getParameterMap().forEach((key, value) -> {
            mv.addObject(key, value);
        });
        mv.addObject("cgStype", cgStype);
        SysParams sysParams = sysParamService.findByParameterId(cgStype, "产品大类");
        mv.addObject("cgName", sysParams.getParamName());
        //是否定向
        mv.addObject("orient", true);
        return mv;
    }


    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "/getSrchTopList")
    public Result getSrchTopList(BaseDto baseDto) throws IOException {
        return eCgRequisitionService.getSrchTopList(baseDto).getResult();
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "/getSrchBottomList")
    public Result getSrchBottomList(BaseDto baseDto) throws IOException {
        return eCgRequisitionService.getSrchBottomList(baseDto).getResult();
    }

    /**
     * @description: Post新增Restful
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody EcgRequisitiontVo vo) throws Exception {
        return eCgRequisitionService.save(vo);
    }

    @PutMapping(value = "add.html")
    public Result modify(@RequestBody EcgRequisitiontVo vo) throws Exception {
        return eCgRequisitionService.update(vo);
    }


    /**
     * @Param: [vo]
     * @description: Get(获取数据 | 通过ID获取对象)Restful
     */
    @GetMapping(value = "getById/{sqNote}")
    public Result getById(@PathVariable(value = "sqNote") String sqNote) {
        return Result.resultOk(eCgRequisitionService.findBySqnote(sqNote));
    }

    @PutMapping(value = "/getDjFlag")
    public Result getDjFlag(@RequestBody BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        return eCgRequisitionService.getFlag(String.valueOf(map.get("sqNote")), "登记");
    }

    @PutMapping(value = "/getQrFlag")
    public Result getQrFlag(@RequestBody BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        return eCgRequisitionService.getFlag(String.valueOf(map.get("sqNote")), "核准");
    }


    @PutMapping(value = "/getFlag")
    public Result getFlag(@RequestBody BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        return eCgRequisitionService.getFlag(String.valueOf(map.get("sqNote")), String.valueOf(map.get("flag")));
    }

    @PutMapping(value = "/setFlag")
    public Result setFlag(@RequestBody BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        return eCgRequisitionService.setFlag(String.valueOf(map.get("sqNote")), String.valueOf(map.get("flag")));
    }

    @DeleteMapping(value = "delete/{sqNote}")
    public Result delete(@PathVariable(value = "sqNote") String sqNote) throws Exception {
        eCgRequisitionService.deleteById(sqNote);
        return Result.resultOk();
    }

    /**
     * @throws IOException
     * @Param: [vo]
     * @description:采购申请选择list
     */
    @PostMapping("/getCgsqSelectList")
    public Map<String, Object> getCgsqSelectList(BaseDto baseDto) throws IOException {
        return eCgRequisitionService.getCgsqSelectList(baseDto);
    }

    @PutMapping(value = "/ok/{sqNote}")
    public Result ok(@PathVariable(value = "sqNote") String sqNote) throws Exception {
        return eCgRequisitionService.setFlag(sqNote, "核准");
    }

    @PutMapping(value = "/no/{sqNote}")
    public Result no(@PathVariable(value = "sqNote") String sqNote) throws Exception {
        return eCgRequisitionService.setFlag(sqNote, "登记");
    }


    @RequestMapping("/taxiadsadas")
    public Result aaaaaaa(BaseDto baseDto) throws IOException {
        return eCgRequisitionService.a(baseDto);
    }


    @RequestMapping("/avacsaca")
    public Result avacsaca(String a,String note,String code) throws IOException {
         eCgRequisitionService.avacsaca(a,note,code);
        return Result.resultOk();
    }

}
