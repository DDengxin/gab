package com.tengzhi.business.sale.domesticTrade.domesticTradeContract.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sale.domesticTrade.domesticTradeContract.service.DomesticTradeService;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import com.tengzhi.business.sale.saleProduct.saleContract.vo.EXsContractVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/sale/domesticTrade/domesticTradeContract")
public class DomesticTradeContract {
    @Autowired
    private DomesticTradeService domesticTradeService;

    @GetMapping(value="/*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    @GetMapping("/table.html")
    public ModelAndView table(ModelAndView mv,@RequestParam(value="htNo") String htNo) {
        mv = domesticTradeService.table(mv,htNo);
        return mv;
    }

    /**
     * 	查询数据列表
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "list.html")
    public Result getSrchTopList(BaseDto baseDto){
        return domesticTradeService.findAll(baseDto).getResult();
    }

    /**
     *
     *
     * @param paramType 类型(CP,YL,WL)
     * @param paramMod 模块
     * @param request
     * @param rt
     * @param mv
     * @return
     */
    @GetMapping(value = {"{paramType}/{paramMod}/list.html"})
    public ModelAndView toPage(@PathVariable String paramType, @PathVariable String paramMod,
                               HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
        mv.setView(new RedirectView(String.format("/sale/domesticTrade/domesticTradeContract/%s",servletPath)));
        request.getParameterMap().forEach((key,value) ->{
            mv.addObject(key,value);
        });
        mv.addObject("paramType",paramType);
        mv.addObject("paramMod",paramMod);
        return mv;
    }
    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "/getSrchBottomList")
    public Result getSrchBottomList(BaseDto baseDto) throws IOException {
        return domesticTradeService.getSrchBottomList(baseDto).getResult();
    }
    @PostMapping(value = "add.html")
    public Result add(@RequestBody EXsContractVo vo) throws Exception {
        EXsContract eXsContract=domesticTradeService.save(EXsContractVo.initEXsContractVo(vo));
        return Result.resultOk(eXsContract.getHtNo());
    }

    @PutMapping(value = "add.html")
    public Result modify(@RequestBody EXsContractVo vo) throws Exception {
        domesticTradeService.update(EXsContractVo.initEXsContractVo(vo));
        return Result.resultOkMsg("修改成功");
    }

    @GetMapping(value = "getByNote/{htNO}")
    public Result getById(@PathVariable(value = "htNO") String htNO) {
        return Result.resultOk(domesticTradeService.findByNote(htNO));
    }
    @PutMapping(value = "confirm/{htNo}")
    public Result confirm(@PathVariable(value = "htNo") String htNo) throws Exception {
        return domesticTradeService.confirm(htNo);
    }

    @PutMapping(value = "cancel/{htNo}")
    public Result cancel(@PathVariable(value = "htNo") String htNo) throws Exception {
        return domesticTradeService.cancel(htNo);
    }

    @DeleteMapping(value = "delete/{htNo}")
    public Result delete(@PathVariable(value = "htNo") String htNo) throws Exception {
        domesticTradeService.deleteByNote(htNo);
        return Result.resultOkMsg("删除成功");
    }
    @PostMapping(value = "/getContractDetailed")
    public Result getContractDetailed(BaseDto baseDto) throws IOException {
        return domesticTradeService.getContractDetailed(baseDto).getResult();
    }

    @PutMapping(value = "/getFlag/{htNo}/{flag}")
    public Result getFlag(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "flag") String flag) throws Exception {
        return domesticTradeService.getFlag(htNo,flag);
    }

    /**
     *
     * @param htNo
     * @param flag
     * @return 多选是判断状态
     * @throws IOException
     * @throws Exception
     */
    @PutMapping(value = "/getFlags/{htNo}/{flag}")
    public Result getFlags(@PathVariable(value = "htNo") String htNo,@PathVariable(value = "flag") String flag) throws IOException, Exception {
        return domesticTradeService.getFlags(htNo,flag);
    }

    /**
     *
     *
     * @param paramType 类型 (CP,YL,WL)
     * @param paramMod 模块
     * @param request
     * @param rt
     * @param mv
     * @return
     */
    @GetMapping(value = {"{paramType}/{paramMod}/scddlist.html"})
    public ModelAndView ddtj(@PathVariable String paramType, @PathVariable String paramMod,
                             HttpServletRequest request, RedirectAttributes rt,ModelAndView mv) {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/")+1);
        mv.setView(new RedirectView(String.format("/sale/saleProduct/saleContract/%s",servletPath)));
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
     * 	查询数据列表
     * @param baseDto
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/scddlist.html")
    public Result getScddList(BaseDto baseDto) throws IOException {
        return domesticTradeService.getScddList(baseDto).getResult();
    }

    @GetMapping(value = "/getBySplitId/{htMo}")
    public Result getBySplitId(@PathVariable(value = "htMo") String htMo) {
        return Result.resultOk(domesticTradeService.getBySplitId(htMo));
    }

    @GetMapping(value = "/getByHpId/{htMo}")
    public Result getByHpId(@PathVariable(value = "htMo") String htMo) {
        return Result.resultOk(domesticTradeService.getByHpId(htMo));
    }
    @PostMapping(value = "/exportExcelScdd")
    public Result exportExcelScdd(HttpServletResponse response, HttpServletRequest request ) throws IOException {
        domesticTradeService.exportExcelScdd(response, request );
        return Result.resultOk();
    }


    /**
     *  核销
     * @param htNo
     * @return
     * @throws Exception
     */
    @PutMapping(value = "hx/{htNo}")
    public Result hx(@PathVariable(value = "htNo") String htNo) throws Exception {
        return domesticTradeService.hx(htNo);
    }

    @PutMapping(value = "qxhx/{htNo}")
    public Result qxhx(@PathVariable(value = "htNo") String htNo) throws Exception {
        return domesticTradeService.qxhx(htNo);
    }

    @PostMapping(value = "/getUsableContractDetailed")
    public Result getUsableContractDetailed(BaseDto baseDto) throws IOException {
        return domesticTradeService.getUsableContractDetailed(baseDto).getResult();
    }

    @GetMapping(value = "getItemList/{htStype}")
    public Result getItemList(@PathVariable(value = "htStype") String htStype) throws IOException {
        return Result.resultOk(domesticTradeService.getItemList(htStype));
    }

    @PostMapping(value = "/getDetailedList")
    public Result getDetailedList(BaseDto baseDto) throws IOException {
        return Result.resultOk(domesticTradeService.getDetailedList(baseDto));
    }
    @PutMapping(value = "termsAdd.html")
    public Result updateTerms(@RequestBody EXsContract eXsContract) throws Exception {
        return domesticTradeService.updateTerms(eXsContract);
    }
}
