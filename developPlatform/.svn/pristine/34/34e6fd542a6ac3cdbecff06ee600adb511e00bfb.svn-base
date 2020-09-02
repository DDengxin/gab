package com.tengzhi.app.ck.controller;

import com.tengzhi.app.ck.service.impl.CKFlagServiceImpl;
import com.tengzhi.app.ck.service.impl.CKServiceImpl;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.tools.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *  app的辅料入库
 */
@RestController
@RequestMapping("/app/ck/")
public class CKController {
    @Autowired
    private CKServiceImpl ckService;

    @Autowired
    private CKFlagServiceImpl ckFlagService;


    /**
     * 查询成品出库的出库方式
     * @return
     */
    @PostMapping("selectWarehouseWay")
    public Result selectWarehouseWay(String paramId,String paramType, String paramValue3) throws Exception {
        return Result.formPage(ckService.selectWarehouseWay(paramId,paramType,paramValue3));
    }

    /**
     * 根据产品类型查询产品的口径
     * @return
     */
    @PostMapping("selectProductAct")
    public Map<String, Object> selectAct(String paramType,String parentId,String parentName,String actType) throws Exception {
        return ckService.selectAct(paramType,parentId,parentName,actType);
    }

    /**
     * 根据产品类型查询产品的库房
     * @return
     */
    @PostMapping("selectProductHouse")
    public  Map<String, Object> selectProductHouse(String paramType,String parentId,String parentName) throws Exception {
        return ckService.selectLib(paramType,parentId,parentName);
    }

    /**
     * 查询产品大类
     * @return
     */
    @PostMapping("selectProductCategory")
    public Map<String, Object> selectProductCategory(String parentId,String paramId,String parentName) throws Exception {
        return ckService.selectProductCategory(parentId,paramId,parentName);
    }

    /**
     * 查询成品出库的出库单号
     * @return
     */
    @PostMapping("selectWarehouseSingleNumber")
    public Result selectWarehouseSingleNumber(String outAct,String outFlag) throws Exception {
        return Result.formPage(ckService.selectWarehouseSingleNumber(outAct,outFlag));
    }

    /**
     * 根据成品出库的包装号扫描信息
     * @return
     */
    @PostMapping("selectPackingNumberInfo")
    public Map<String, Object> selectPackingNumberInfo(String pack,String bpack) throws Exception {
        return ckService.selectPackingNumberInfo(pack,bpack);
    }

    /**
     * 新增出库信息到临时表
     * @return
     */
    @PostMapping("insertOutInfoTemporary")
    public Result insertOutInfoTemporary(String outString) throws Exception {
        ckService.insert(outString);
        return  Result.resultOk();
    }

    /**
     * 批量保存到out表
     * @return
     */
    @PostMapping("batchInsertSave")
    public Result batchInsertSave(String note,String lib,String in_act,String dataAct) throws Exception {
        ckService.batchInsert(note,lib,in_act,dataAct);
        return  Result.resultOk();
    }

    /**
     * 查询是否有单号下的包装号
     * @return
     */
    @PostMapping("selectPackNumber")
    public Map<String, Object> selectPackNumber(String out_note,String out_pack,String out_bpack) throws Exception {
        return ckService.selectPackNumber(out_note,out_pack,out_bpack);
    }

    /**
     * 查询出库明细视图中是否有这个包装号
     * @return
     */
    @PostMapping("selectCKDetailsPackNumber")
    public Map<String, Object> selectCKDetailsPackNumber(String lib,String code,String in_act,String in_contract,String dataAct,String pack,String bpack) throws Exception {
        return ckService.selectCKDetailsPackNumber(lib,code,in_act,in_contract,pack,bpack,dataAct);
    }

    /**
     * 从库存信息里查询该包装号的详细信息
     * @return
     */
    @PostMapping("selectCKPackInfo")
    public Map<String, Object> selectCKPackInfo(String note,String lib,String code,String in_act,String in_contract,String pack,String bpack) throws Exception {
        return ckService.selectCKPackInfo(note,lib,code,in_act,in_contract,pack,bpack);
    }

    /**
     * 根据单号查询该包装号的所有信息
     * @return
     */
    @PostMapping("selectCKNoteInfo")
    public Map<String, Object> selectCKNoteInfo(String note,String dataAct) throws Exception {
        return ckService.selectCKNoteInfo(note,dataAct);
    }

    /**
     * 根据单号查询该包装号的所有信息
     * @return
     */
    @PostMapping("selectRKNoteInfoDH")
    public Map<String, Object> selectRKNoteInfoDH(String note) throws Exception {
        return ckService.selectRKNoteInfoDH(note);
    }

    /**
     * 出库判断是否件数一样
     * @return
     */
    @PostMapping("selectPackPieces")
    public Map<String, Object> selectPackPieces(String out_note) throws Exception {
        return ckService.selectPackPieces(out_note);
    }

    /**
     * 库审判断数量是否大于最小数量范围
     * @return
     */
    @PostMapping("selectPackQuantity")
    public Map<String, Object> selectPackQuantity(String note,String dataAct) throws Exception {
        return ckService.selectPackQuantity(note,dataAct);
    }

    /**
     * 出库
     * @return
     */
    @PostMapping("updateFlag")
    public Result updateFlag(String out_flag,String out_note,String dataAct) throws Exception {
        return ckFlagService.updateFlag(out_note,dataAct);
    }

    /**
     * 出库按钮把出库状态改为库审
     * @return
     */
    @PostMapping("updatePackFlag")
    public Result updatePackFlag(String note,String out_flag, String out_lib, String out_act, String dataAct) throws Exception {
        return ckFlagService.updatePackFlag(note,out_flag,out_lib,out_act,dataAct);
    }

    /**
     * 交货查询
     * @return
     */
    @PostMapping("selectDelivery")
    public Map<String, Object> selectDelivery(String fhNote) throws Exception {
        return ckService.selectDelivery(fhNote);
    }

    /**
     * 交货单号查询
     * @return
     */
    @PostMapping("selectDeliverySingleNumber")
    public Map<String, Object> selectDeliverySingleNumber(String rqOne,String rqTwo,String fhType,String fhAct,String fhLib) throws Exception {
        return ckService.selectDeliverySingleNumber(rqOne,rqTwo,fhType,fhAct,fhLib);
    }

    /**
     * 批量删除
     */
    @PostMapping("batchDelete")
    public Result batchDelete(String dataAct,String pack,String bpack) throws Exception {
        return ckFlagService.batchDelete(dataAct,pack,bpack);
    }





}
