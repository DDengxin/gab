package com.tengzhi.app.rk.service;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;

import java.util.Map;

public interface RKService extends BaseService {
    /**
     * 查询入库明细中是否有这个包装号
     */
    Map<String, Object> selectRKDetailsPackNumber(String act,String lib,String kw,String pack, String bpack);

    /**
     * 从工程表里里查询该包装号的详细信息
     */
    Map<String, Object> selectRKPackInfo(String act,String lib,String kw,String pack);

    /**
     * 新增入库信息到临时表
     */
    void insert(String inString);

    /**
     * 根据单号查询该包装号的所有信息
     */
    Map<String, Object> selectRKNoteInfo(String note);

    /**
     * 第二种新增入库信息到临时表
     */
    void insert2(String inString,String pack,String zl,String luhao,String js);

    /**
     * 批量保存到in表
     */
    void batchInsert(String lib,String act);

    /**
     * 入库扫描批量保存到in表
     */
    void batchInsertsm(String lib,String act,String kw);

    /**
     * 库审判断数量是否大于最小数量范围
     */
    Map<String, Object> selectPackQuantity(String inLib,String inAct);

    /**
     * 入货单号查询
     */
    Map<String, Object> selectIncomingSingleNumber(String rqOne,String rqTwo,String shType,String shAct,String shLib);

    /**
     * 入货查询
     */
    Map<String, Object> selectIncoming(String shNote);

    /**
     * 自动生成包装号
     */
    String  getPackNumber();
    /**
     * 查询临时表信息
     * @param menu 菜单
     * @param packType (大小包装)
     * @param inAct 口径
     * @param inLib 库房
     * @param inKw 库位
     * @return
     */
    Map<String,Object> getIncoming(String menu, String inAct, String inLib,String inKw, String packType);

    /**
     * 根据库房查询区位
     * @param paramType  参数类型
     * @param paramValue  参数值1
     * @return
     */
    Map<String, Object> selectLocation(String paramType,String paramValue);
    
    /**
     * 已收货数量
     * @param note
     * @param item
     * @param code
     * @return
     */
    Map<String, Object> getReceivedQuantity(String note,String item,String code);
}
