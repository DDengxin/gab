package com.tengzhi.business.tooling.grantandrecover.dao;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.business.tooling.grantandrecover.pojo.MGzMjrecord;
import com.tengzhi.business.tooling.grantandrecover.pojo.MGzMjrecordPK;

import java.util.List;
import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-06-28
 */
public interface dispatcherDao extends BasicsDao<MGzMjrecord,MGzMjrecordPK>{

    /**
    * @Param: []
    * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    * @Author: 鱼油浅水
    * @Date: 2020/6/28 19:11
    * @description: 查询出入库视图
    */
    List<Map<String,Object>> queryAllOutboundStorage(Map<String,String> map);


    /**
    * @Param: []
    * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    * @Author: 鱼油浅水
    * @Date: 2020/6/28 23:44
    * @description: 查询一单的原料信息
    */
    List<Map<String,Object>> checkRawMaterials(String Note,String ACT);



    /**
     * @Param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: 鱼油浅水
     * @Date: 2020/6/28 23:44
     * @description: 查询模具出库记录 需要归还
     */
    List<Map<String,Object>> queryOutboundRecords(Map<String,String> map);


    /**
     * @Param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: 鱼油浅水
     * @Date: 2020/6/28 23:44
     * @description: 查询首页去重记录
     */
    List<Map<String,Object>> queryHomepageRecord(Map<String,String> map);


    /***
     * Sql 产品编码|模具编码|现规格 修改
     */
    int moldModification(String val1,String val2,String val3,String note, String code, String act);

}
