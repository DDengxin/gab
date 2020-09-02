package com.tengzhi.business.finance.capitalManager.capitalCheck.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.capitalManager.capitalCheck.model.EFVoucherCapitalCheck;
import com.tengzhi.business.finance.capitalManager.capitalCheck.service.CapitalCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/finance/capitalManager/capitalCheck/")
public class CapitalCheckController {

    @Autowired
    private CapitalCheckService service;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }


    /**
     * 结存今日资产档案
     */
    @GetMapping(value = "jcOption.html")
    public Result jcOption(){
        return service.jcOption();
    }

    /**
     * 导出
     * @param response
     * @param request
     * @throws IOException
     */
    @PostMapping(value = "getSrchTopListExportExcel")
    public void getSrchTopListExportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
        service.getSrchTopListExportExcel(response, request);
    }


    @RequestMapping(value = "getJcTree.html")
    public List<Map<String,Object>> getJcTree(BaseDto dto){
        return service.getJcTree(dto);
    }



    /**
     * 新增一条记录
     * **/
    @PostMapping(value = "add.html")
    public Result add(@RequestBody EFVoucherCapitalCheck mode) throws Exception{
        return Result.resultOk(service.save(mode));
    }

    @GetMapping("getById/{ksid}")
    public Result getById(@PathVariable String ksid){
        return Result.resultOk(service.findById(ksid));
    }

    /**
     * 删除对象数据
     * @param ksid
     * **/
    @DeleteMapping(value = "deleteById/{ksid}")
    public Result delete(@PathVariable(value = "ksid") String ksid) {
        service.deleteByNote(ksid);
        return Result.resultOk();
    }


    /**
     *
     *修改数据
     * @param model
     * */
    @PutMapping("add.html")
    public Result modify(@RequestBody EFVoucherCapitalCheck model) {
        service.update(model);
        return Result.resultOk();
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "list.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return service.getList(baseDto).getResult();
    }

}
