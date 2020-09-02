package com.tengzhi.app.jy.controller;

import com.tengzhi.app.jy.service.impl.JYServiceImpl;
import com.tengzhi.base.jpa.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/app/jy/")
public class JYController {
    @Autowired
    private JYServiceImpl jyService;

    /**
     * 查询检验类型
     * @return
     */
    @PostMapping("selectTestType")
    public Map<String, Object> selectTestType(String paramType, String parentId) throws Exception {
        return jyService.selectTestType(paramType,parentId);
    }

    /**
     * 根据包装号查询包装信息
     * @return
     */
    @PostMapping("selectTestPackInfo")
    public Map<String, Object> selectTestPackInfo(String pack) throws Exception {
        return jyService.selectTestPackInfo(pack);
    }

    /**
     * 根据大类查询小类信息
     * @return
     */
    @PostMapping("selectSmallClass")
    public Map<String, Object> selectSmallClass(String bigClass) throws Exception {
        return jyService.selectSmallClass(bigClass);
    }

    /**
     * 插入炉号检验信息和主表检验信息
     * @return
     */
    @PostMapping("insertLuHao")
    public Result insertLuHao(String jyInfo,String listJy,String type) throws Exception {
        jyService.insertLuHao(jyInfo,listJy,type);
        return  Result.resultOk();
    }

    /**
     * 查询检验档案大类
     * @return
     */
    @PostMapping("selectTestBigClass")
    public Map<String, Object> selectTestBigClass(String paramType,String parentId1,String parentId2,String parentId3,String parentId4) throws Exception {
        return jyService.selectTestBigClass(paramType,parentId1,parentId2,parentId3,parentId4);
    }

    /**
     * 插入检验信息和主表检验信息
     * @return
     */
    @PostMapping("insertJY")
    public Result insertJY(String jyInfo,String listJy,String type) throws Exception {
        jyService.insertJy(jyInfo,listJy,type);
        return  Result.resultOk();
    }

    /**
     * 查询检测档案大类
     * @return
     */
    @PostMapping("selectDetectBigClass")
    public Map<String, Object> selectDetectBigClass(String parentId1,String parentId2) throws Exception {
        return jyService.selectDetectBigClass(parentId1,parentId2);
    }

    /**
     * 插入检测信息和主表检验信息
     * @return
     */
    @PostMapping("insertJC")
    public Result insertJC(String jyInfo,String listJy,String type) throws Exception {
        jyService.insertJC(jyInfo,listJy,type);
        return  Result.resultOk();
    }

    /**
     * 根据材质编码和检测项查询标准值 炉号检测
     * @return
     */
    @PostMapping("selectLHItem")
    public Map<String, Object> selectLHItem(String code, String item) throws Exception {
        return jyService.selectLHItem(code,item);
    }
    /**
     * 根据材质编码和检测项查询标准值  检验
     * @return
     */
    @PostMapping("selectJYItem")
    public Map<String, Object> selectJYItem(String code, String item) throws Exception {
        return jyService.selectJYItem(code,item);
    }
    /**
     * 根据材质编码和检测项查询标准值   检测
     * @return
     */
    @PostMapping("selectJCItem")
    public Map<String, Object> selectJCItem(String code, String item) throws Exception {
        return jyService.selectJCItem(code,item);
    }

}
