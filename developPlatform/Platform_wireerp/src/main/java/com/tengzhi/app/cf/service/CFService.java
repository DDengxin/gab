package com.tengzhi.app.cf.service;

import com.tengzhi.base.jpa.result.Result;

import java.math.BigDecimal;
import java.util.Map;

public interface CFService {
    /**
     * 判断数据在不在v_stock和判断数据在e_ck_out,e_ck_out_ls
     * @return
     */
    Map<String, Object> selectDateIn(String pack);

    /**
     * 根据包装号查询订单明细（v_ck_mx表）
     * @param pack 包装号
     * @return
     */
    Map<String, Object> selectCKNoteMX(String pack);

    /**
     * 根据包装号和订单号查询订单明细（v_ck_mx表）
     * @param contract 订单号
     * @param pack  包装号
     * @return
     */
    Map<String, Object> selectCKNoteOrder(String contract,String pack);

    /**
     * 自动生成包装号
     */
    String  getPackNumber(String date);

    /**
     * 拆分入库插入
     */
    void insert(String oldPack,String newPack,String outString);
    /**
     * 根据大包装号查询订单号（v_ck_mx表）
     * @return
     */
    Map<String, Object> selectCKBigNoteOrder(String bpack);

    /**
     * 根据订单号查询订单明细（v_ck_mx表）
     * @return
     */
    Map<String, Object> selectCKBigNoteMX(String contract,String bpack);

    /**
     *  查询小包装号是否在大包装号里（e_ck_in表）
     * @param pack 小包装号
     * @param bpack  大包装号
     * @return
     */
    Map<String, Object> selectBigSmall(String pack,String bpack);

    /**
     * 修改大包装号为小包装号
     */
    Result updateBigPack(String bpack, String pack);

    /**
     * 修改大包装号为小包装号(全部拆除)
     * @param bpack 大包装号
     * @param code  材质
     */
    Result updateAllBigPack(String bpack,String code);
    /**
     * 根据订单号生产大包装号
     * @param prefix  前缀
     * @return
     */
    String getOrderBig(String prefix);
    /**
     * 判断订单是否相等和判断小包装号与大包装号是否相等
     */
    Map<String, Object> selectOrderEqual(String pack,String contract,String bpack);

    /**
     * 将订单明细插入e_ck_pack_sacn表
     */
    void insertOrderMx(String scanString, String rightId,String newKw);
    /**
     * 更新e_ck_pack_sacn状态
     */
    Result updateScanningFlag(String flag,String pack,String bpack);

    /**
     * 更新大包装号(装箱)
     */
    Result updateFillBigPack(String pack,String bpack);
    /**
     * 根据大包装号查询扫描表信息（e_ck_pack_sacn表）
     * @return
     */
    Map<String, Object> selectScanning(String bpack);
    /**
     * 装箱根据大小包装号查询包装信息（大小包装号一样）
     * @return
     */
    Map<String, Object> selectFillBigSmall(String pack);
    /**
     * 库位调整页面选择库房
     * @return
     */
    Map<String, Object> selectLocationHouse();
    /**
     * 判断数据在不在v_stock和判断数据在e_ck_out,e_ck_out_ls(库位调整页面)
     */
    Map<String, Object> selectDateInLocation(String pack,String bpack);

    /**
     * 根据大小包装查询包装号信息
     */
    Map<String, Object> selectBigSmallInfo(String pack,String bpack);

    /**
     * 更新大包装号的库位
     */
    Result updateKwBigPack(String inKw,String bpack);

    /**
     * 更新小包装号的库位
     */
    Result updateKwSmallPack(String inKw,String pack);
    /**
     * 更新小包装号e_ck_pack_sacn状态(库位调整)
     */
    Result updateSmallScanningKwFlag(String flag,String pack);
    /**
     * 更新大包装号e_ck_pack_sacn状态(库位调整)
     */
    Result updateBigScanningKwFlag(String flag,String bpack);
    /**
     * 根据库房查询扫描表信息（e_ck_pack_sacn表）
     * @return
     */
    Map<String, Object> selectScanningLib(String lib);

    /**
     * 批量删除(库位调整页面)
     */
    Result batchDeleteKw(String pack,String bpack);
    /**
     * 根据小包装号查询打印数据单打（v_ck_mx）
     */
    Map<String, Object> selectSmallPrint(String pack,String bpack);

    /**
     * 根据大包装号查询打印数据连打（v_ck_mx）
     */
    Map<String, Object> selectBigPrint(String bpack);

    
    Result updateKw(String dataAct,String lib);
}
