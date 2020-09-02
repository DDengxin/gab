package com.tengzhi.app.cf.controller;

import com.tengzhi.app.cf.service.impl.CFServiceImpl;
import com.tengzhi.app.ck.service.impl.CKFlagServiceImpl;
import com.tengzhi.app.ck.service.impl.CKServiceImpl;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseReceipt.service.PurchaseReceiptService;
import com.tengzhi.business.cg.yw.purchaseReceipt.service.impl.PurchaseReceiptServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 *  app的处理拆分
 */
@RestController
@RequestMapping("/app/cf/")
public class CFController {
    @Autowired
    private CFServiceImpl cfService;

    @Autowired
    private PurchaseReceiptServiceImpl purchaseReceiptService;

    /**
     * 判断数据在不在v_stock和判断数据在e_ck_out,e_ck_out_ls
     * @return
     */
    @PostMapping("selectDateIn")
    public Map<String, Object> selectDateIn(String pack) throws Exception {
        return cfService.selectDateIn(pack);
    }

    /**
     * 根据包装号查询订单明细（v_ck_mx表）
     * @return
     */
    @PostMapping("selectCKNoteMX")
    public Map<String, Object> selectCKNoteMX(String pack) throws Exception {
        return cfService.selectCKNoteMX(pack);
    }

    /**
     * 根据包装号查询订单明细（v_ck_mx表）
     * @return
     */
    @PostMapping("selectCKNoteOrder")
    public Map<String, Object> selectCKNoteOrder(String contract,String pack) throws Exception {
        return cfService.selectCKNoteOrder(contract,pack);
    }

    /**
     * 拆分生成包装号
     * @return
     */
    @PostMapping("getPackNumber")
    public Result getPackNumber(String date) throws Exception {
        return  Result.resultOk(cfService.getPackNumber(date));
    }

    /**
     * 拆分入库插入
     * @return
     */
    @PostMapping("insertRkSplit")
    public Result insertRkSplit(String oldPack,String newPack,String outString) throws Exception {
    	cfService.insert(oldPack,newPack,outString);
        return  Result.resultOk();
    }

    /**
     * 更新原包装号的数量
     * @return
     */
    @PostMapping("updateSplitData")
    public Result updateSplitData(String sl,String pack,String in_contract) throws Exception {
        return purchaseReceiptService.updateSplitData(BigDecimal.valueOf(Double.valueOf(sl)),pack,in_contract);
    }

    /**
     * 根据大包装号查询订单号（v_ck_mx表）
     * @return
     */
    @PostMapping("selectCKBigNoteOrder")
    public Map<String, Object> selectCKBigNoteOrder(String bpack) throws Exception {
        return cfService.selectCKBigNoteOrder(bpack);
    }

    /**
     * 根据订单号查询订单明细（v_ck_mx表）
     * @return
     */
    @PostMapping("selectCKBigNoteMX")
    public Map<String, Object> selectCKBigNoteMX(String contract,String bpack) throws Exception {
        return cfService.selectCKBigNoteMX(contract,bpack);
    }

    /**
     * 查询小包装号是否在大包装号里（e_ck_in表）
     * @return
     */
    @PostMapping("selectBigSmall")
    public Map<String, Object> selectBigSmall(String pack,String bpack) throws Exception {
        return cfService.selectBigSmall(pack,bpack);
    }

    /**
     * 修改大包装号为小包装号（e_ck_in表）
     * @return
     */
    @PostMapping("updateBigPack")
    public Map<String, Object> updateBigPack(String bpack,String pack) throws Exception {
        return cfService.updateBigPack(bpack,pack);
    }

    /**
     * 修改大包装号为小包装号(全部拆除)
     * @return
     */
    @PostMapping("updateAllBigPack")
    public Map<String, Object> updateAllBigPack(String bpack,String code) throws Exception {
        return cfService.updateAllBigPack(bpack,code);
    }

    /**
     * 根据订单号生产大包装号
     * @return
     */
    @PostMapping("getOrderBig")
    public Result getOrderBig(String prefix) throws Exception {
        return  Result.resultOk(cfService.getOrderBig(prefix));
    }

    /**
     * 判断订单是否相等和判断小包装号与大包装号是否相等（e_ck_in表）
     * @return
     */
    @PostMapping("selectOrderEqual")
    public Map<String, Object> selectOrderEqual(String pack,String contract,String bpack) throws Exception {
        return cfService.selectOrderEqual(pack,contract,bpack);
    }

    /**
     * 拆分入库插入
     * @return
     */
    @PostMapping("insertOrderMx")
    public Result insertOrderMx(String scanString, String rightId,String newKw) throws Exception {
    	cfService.insertOrderMx(scanString,rightId,newKw);
        return  Result.resultOk();
    }

    /**
     * 更新e_ck_pack_sacn状态
     * @return
     */
    @PostMapping("updateScanningFlag")
    public Result updateScanningFlag(String flag,String pack,String bpack) throws Exception {
        cfService.updateScanningFlag(flag,pack,bpack);
        return  Result.resultOk();
    }

    /**
     * 更新大包装号(装箱)
     * @return
     */
    @PostMapping("updateFillBigPack")
    public Result updateFillBigPack(String pack,String bpack) throws Exception {
        cfService.updateFillBigPack(pack,bpack);
        return  Result.resultOk();
    }

    /**
     * 根据大包装号查询扫描表信息（e_ck_pack_sacn表）
     * @return
     */
    @PostMapping("selectScanning")
    public Map<String, Object> selectScanning(String bpack) throws Exception {
        return cfService.selectScanning(bpack);
    }

    /**
     * 装箱根据大小包装号查询包装信息（大小包装号一样）
     * @return
     */
    @PostMapping("selectFillBigSmall")
    public Map<String, Object> selectFillBigSmall(String pack) throws Exception {
        return cfService.selectFillBigSmall(pack);
    }

    /**
     * 库位调整页面选择库房
     * @return
     */
    @PostMapping("selectLocationHouse")
    public Map<String, Object> selectLocationHouse() throws Exception {
        return cfService.selectLocationHouse();
    }

    /**
     * 判断数据在不在v_stock和判断数据在e_ck_out,e_ck_out_ls(库位调整页面)
     * @return
     */
    @PostMapping("selectDateInLocation")
    public Map<String, Object> selectDateInLocation(String pack,String bpack) throws Exception {
        return cfService.selectDateInLocation(pack,bpack);
    }

    /**
     * 根据大小包装查询包装号信息
     * @return
     */
    @PostMapping("selectBigSmallInfo")
    public Map<String, Object> selectBigSmallInfo(String pack,String bpack) throws Exception {
        return cfService.selectBigSmallInfo(pack,bpack);
    }

    /**
     * 更新大小包装号的库位
     * @return
     */
    @PostMapping("updateKwPack")
    public Result updateKwPack(String dataAct,String lib) throws Exception {
        
    	cfService.updateKw(dataAct,lib);
    	/*if (!StringUtils.isBlank(bpack.toString())) {
            cfService.updateKwBigPack(inKw,bpack);
        }
        if (!StringUtils.isBlank(pack.toString())) {
            cfService.updateKwSmallPack(inKw,pack);
        }*/
        return  Result.resultOk();
    }

    /**
     * 更新大小包装号的库位
     * @return
     */
    @PostMapping("updateKwPackFlag")
    public Result updateKwPackFlag(String flag,String bpack,String pack) throws Exception {
        if (!StringUtils.isBlank(bpack)) {
            cfService.updateBigScanningKwFlag(flag,bpack);
        }
        if (!StringUtils.isBlank(pack)) {
            cfService.updateSmallScanningKwFlag(flag,pack);
        }
        return  Result.resultOk();
    }

    /**
     * 根据库房查询扫描表信息（e_ck_pack_sacn表）
     * @return
     */
    @PostMapping("selectScanningLib")
    public Map<String, Object> selectScanningLib(String lib) throws Exception {
        return cfService.selectScanningLib(lib);
    }

    /**
     * 批量删除(库位调整页面)
     */
    @PostMapping("batchDeleteKw")
    public Result batchDeleteKw(String pack,String bpack) throws Exception {
        cfService.batchDeleteKw(pack,bpack);
        return  Result.resultOk();
    }

    /**
     * 根据小包装号查询打印数据单打（v_ck_mx）
     */
    @PostMapping("selectSmallPrint")
    public Map<String, Object> selectSmallPrint(String pack,String bpack) throws Exception {
        return cfService.selectSmallPrint(pack,bpack);
    }

    /**
     * 根据大包装号查询打印数据连打（v_ck_mx）
     */
    @PostMapping("selectBigPrint")
    public Map<String, Object> selectBigPrint(String bpack) throws Exception {
        return cfService.selectBigPrint(bpack);
    }



}
