package com.tengzhi.app.jy.service;

import com.tengzhi.base.jpa.service.BaseService;

import java.util.Map;

public interface JYService extends BaseService {
    /**
     * 查询检验类型
     * @return
     */
    Map<String, Object> selectTestType(String paramType, String parentId);

    /**
     * 根据包装号查询包装信息
     */
    Map<String, Object> selectTestPackInfo(String pack);

    /**
     * 根据大类查询小类信息
     */
    Map<String, Object> selectSmallClass(String bigClass);
    /**
     * 插入炉号检验信息和主表检验信息
     */
    void insertLuHao(String jyInfo,String listJy,String type);
    /**
     * 查询检验档案大类
     * @return
     */
    Map<String, Object> selectTestBigClass(String paramType,String parentId1,String parentId2,String parentId3,String parentId4);

    /**
     * 插入检验信息和主表检验信息
     */
    void insertJy(String jyInfo,String listJy,String type);
    /**
     * 查询检测档案大类
     * @return
     */
    Map<String, Object> selectDetectBigClass(String parentId1,String parentId2);

    /**
     * 插入检测信息和主表检验信息
     */
    void insertJC(String jyInfo,String listJy,String type);

    /**
     * 根据材质编码和检测项查询标准值  炉号检测
     * @return
     */
    Map<String, Object> selectLHItem(String code, String item);
    /**
     * 根据材质编码和检测项查询标准值  检验
     * @return
     */
    Map<String, Object> selectJYItem(String code, String item);
    /**
     * 根据材质编码和检测项查询标准值  检测
     * @return
     */
    Map<String, Object> selectJCItem(String code, String item);

}
