package com.tengzhi.business.finance.voucher.controller;

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.codeGeneration.vo.Structure;
import com.tengzhi.business.codeGeneration.vo.TableVo;
import com.tengzhi.business.finance.voucher.service.BusinessCategoryService;
import com.tengzhi.business.finance.voucher.vo.BsCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/finance/voucher/bscategory")
public class BusinessCategoryController {


    @Autowired
    private BusinessCategoryService service;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }


    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "getList")
    public Result getList(BaseDto baseDto) throws IOException {
        return service.getList(baseDto).getResult();
    }

    /**
     * 获取报表列下拉选
     * @return
     */
    @RequestMapping("getSelectList.html")
    public List<Map> getSelectList(){
        return service.getList();
    }

    /***
     * 获取报表类型下拉选
     * @return
     */
    @RequestMapping("getReportList")
    public List<Map>  getReportList(){
        return service.getReportList();
    }

    /**
     * 查询
     * @param fevbusid
     * @return
     */
    @GetMapping("getById/{fevbusid}")
    public Result getById(@PathVariable(value = "fevbusid") String fevbusid){
        return Result.resultOk(service.getById(fevbusid));
    }

    /**
     * 查询表结构
     *
     * @return
     */
    @PostMapping(value = "/findTableFieldDistinctValue")
    public List<Map> findTableFieldDistinctValue(String tableName,String fieldName) throws IOException {
        return service.findTableFieldDistinctValue( tableName, fieldName);
    }


    /**
     * 查询表结构
     *
     * @return
     */
    @PostMapping(value = "/findTableField")
    public Result findTableField(String tableName) throws IOException {
        return Result.formPage(service.findTableField(tableName));
    }
    /**
     * 查询所有表结构
     *
     * @return
     */
    @PostMapping(value = "/findAllTables")
    public List<TableVo> findAllTables() throws IOException {
        return service.findAllTables();
    }
    /**
     * 查询表结构
     *
     * @return
     */
    @PostMapping(value = "/findAllBsCategoryTypeNames")
    public List<Map> findAllBsCategoryTypeNames(String typeName) throws IOException {
        return service.findAllBsCategoryTypeNames(typeName);
    }
    /**
     * 查询所有表结构
     *
     * @return
     */
    @PostMapping(value = "/findAllBsCategoryTypes")
    public List<Map> findAllBsCategoryTypes() throws IOException {
        return service.findAllBsCategoryTypes();
    }
    /**
     * 查询所有表结构
     *
     * @return
     */
    @PostMapping(value = "/findAllCashFlowItems")
    public List<Map> findAllCashFlowItems()  throws IOException {
        return service.findAllCashFlowItems();
    }
    /**
     * 保存设置
     */
    @PostMapping("add.html")
    public Result saveData(@RequestBody BsCategoryVo vo){
        return service.saveData(BsCategoryVo.initVo(vo));
    }
    /**
     * 删除数据
     *
     *
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @Log("删除业务表单")
    public Result delete(@PathVariable(value = "id") String id) {
        return service.deleteById(id);
    }

}
