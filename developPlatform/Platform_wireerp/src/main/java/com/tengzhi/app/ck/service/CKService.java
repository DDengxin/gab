package com.tengzhi.app.ck.service;

import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.system.params.model.SysParams;


import java.util.List;
import java.util.Map;

public interface CKService extends BaseService {
    /**
     * 查询成品出库的出库方式
     * @return
     */
    List<SysParams> selectWarehouseWay(String paramId,String paramType, String paramValue3);

    /**
     * 根据产品类型查询产品的口径和产品的库房
     * @return
     */
    Map<String, Object> selectAct(String paramType,String parentId,String parentName,String actType);
    
    
    /**
     * 
     * 库房
     * @param paramType
     * @param parentId
     * @param parentName
     * @return
     */
    Map<String, Object> selectLib(String paramType,String parentId,String parentName);

    /**
     * 查询产品大类
     * @return
     */
    Map<String, Object> selectProductCategory(String parentId,String paramId,String parentName);
    /**
     * 查询成品出库的出库单号
     * @return
     */
    BasePage<ECkOut> selectWarehouseSingleNumber(String outAct, String outFlag);

    /**
     * 根据成品出库的包装号扫描信息
     * @return
     */
    Map<String, Object> selectPackingNumberInfo(String pack,String bpack);
    /**
     * 新增出库信息到临时表
     */
    void insert(String outString);
    /**
     * 批量保存到out表
     */
    void batchInsert(String note,String lib,String in_act,String dataAct);
    /**
     * 查询是否有单号下的包装号
     */
    Map<String, Object> selectPackNumber(String out_note,String out_pack,String out_bpack);

    /**
     * 查询出库明细视图中是否有这个包装号
     */
    Map<String, Object> selectCKDetailsPackNumber(String lib,String code,String in_act,String in_contract,String pack,String bpack,String dataAct);

    /**
     * 从库存信息里查询该包装号的详细信息
     */
    Map<String, Object> selectCKPackInfo(String note,String lib,String code,String in_act,String in_contract,String pack,String bpack);

    /**
     * 根据单号查询临时表信息
     */
    Map<String, Object> selectCKNoteInfo(String note,String dataAct);

    /**
     * 根据单号查询该包装号的所有信息(点货出库)
     * @return
     */
    Map<String, Object> selectRKNoteInfoDH(String note);

    /**
     * 出库判断是否件数一样
     */
    Map<String, Object> selectPackPieces(String out_note);

    /**
     * 库审判断数量是否大于最小数量范围
     */
    Map<String, Object> selectPackQuantity(String note, String dataAct);

    /**
     * 交货查询
     */
    Map<String, Object> selectDelivery(String fhNote);

    /**
     * 交货单号查询
     */
    Map<String, Object> selectDeliverySingleNumber(String rqOne,String rqTwo,String fhType,String fhAct,String fhLib);

}
