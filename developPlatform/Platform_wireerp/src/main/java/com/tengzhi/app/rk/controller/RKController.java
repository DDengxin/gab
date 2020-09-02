package com.tengzhi.app.rk.controller;

import com.tengzhi.app.ck.service.impl.CKFlagServiceImpl;
import com.tengzhi.app.ck.service.impl.CKServiceImpl;
import com.tengzhi.app.rk.service.impl.RKFlagServiceImpl;
import com.tengzhi.app.rk.service.impl.RKServiceImpl;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.tools.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *  入库
 */
@RestController
@RequestMapping("/app/rk/")
public class RKController {
    @Autowired
    private RKServiceImpl rkService;

    @Autowired
    private RKFlagServiceImpl rkFlagService;

    /**
     * 查询入库明细中是否有这个包装号
     * @return
     */
    @PostMapping("selectRKDetailsPackNumber")
    public Map<String, Object> selectRKDetailsPackNumber(String act,String lib,String kw,String pack, String bpack) throws Exception {
        return rkService.selectRKDetailsPackNumber(act,lib,kw,pack,bpack);
    }

    /**
     * 从工程表里里查询该包装号的详细信息
     * @return
     */
    @PostMapping("selectRKPackInfo")
    public Map<String, Object> selectRKPackInfo(String act,String lib,String kw,String pack) throws Exception {
        return rkService.selectRKPackInfo(act,lib,kw,pack);
    }

    /**
     * 新增入库信息到临时表
     * @return
     */
    @PostMapping("insertInInfoTemporary")
    public Result insertInInfoTemporary(String inString) throws Exception {
        rkService.insert(inString);
        return  Result.resultOk();
    }

    /**
     * 库审判断数量是否大于最小数量范围
     * @return
     */
    @PostMapping("selectPackQuantity")
    public Map<String, Object> selectPackQuantity(String note,String dataAct) throws Exception {
        return rkService.selectPackQuantity(note,dataAct);
    }

    /**
     * 根据单号查询该包装号的所有信息
     * @return
     */
    @PostMapping("selectRKNoteInfo")
    public Map<String, Object> selectRKNoteInfo(String note) throws Exception {
        return rkService.selectRKNoteInfo(note);
    }

    /**
     * 第二种新增入库信息到临时表
     * @return
     */
    @PostMapping("insertInInfo")
    public Result insertInInfo(String inString,String pack,String zl,String luhao,String js ) throws Exception {
        rkService.insert2(inString,pack,zl,luhao,js);
        return  Result.resultOk();
    }

    /**
     * 入库按钮把入库状态改为库审
     * @return
     */
    @PostMapping("updatePackFlag")
    public Result updatePackFlag(String inFlag, String note, String dataAct) throws Exception {
        return rkFlagService.updatePackFlag(inFlag,note,dataAct);
    }

    /**
     * 入库按钮把入库状态改为库审(入库扫描页面)
     * @return
     */
    @PostMapping("updatePackFlagSM")
    public Result updatePackFlagSM(String inFlag, String inLib,  String inAct,String kw) throws Exception {
        return rkFlagService.updatePackFlagSM(inFlag,inLib,inAct);
    }

    /**
     * 批量保存到in表
     * @return
     */
    @PostMapping("batchInsertSave")
    public Result batchInsertSave(String note,String dataAct) throws Exception {
        rkService.batchInsert(note,dataAct);
        return  Result.resultOk();
    }

    /**
     * 入库扫描批量保存到in表
     * @return
     */
    @PostMapping("batchInsertsmSave")
    public Result batchInsertsmSave(String lib,String act,String kw) throws Exception {
        rkService.batchInsertsm(lib,act,kw);
        return  Result.resultOk();
    }

    /**
     * 批量删除
     */
    @PostMapping("batchDelete")
    public Result batchDelete(String pack,String bpack) throws Exception {
        return rkFlagService.batchDelete(pack,bpack);
    }

    /**
     * 入货单号查询
     * @return
     */
    @PostMapping("selectIncomingSingleNumber")
    public Map<String, Object> selectIncomingSingleNumber(String rqOne,String rqTwo,String shType,String shAct,String shLib) throws Exception {
        return rkService.selectIncomingSingleNumber(rqOne,rqTwo,shType,shAct,shLib);
    }

    /**
     * 入货查询
     * @return
     */
    @PostMapping("selectIncoming")
    public Map<String, Object> selectIncoming(String shNote) throws Exception {
        return rkService.selectIncoming(shNote);
    }

    /**
     * 生成包装号
     * @return
     */
    @PostMapping("getPackNumber")
    public Result getPackNumber() throws Exception {
        return  Result.resultOk(rkService.getPackNumber());
    }

    
    @PostMapping("getIncoming")
    public Map<String, Object> getIncoming(String dataAct, String inAct, String inLib,String inKw, String packType) throws Exception {
        return rkService.getIncoming(dataAct,inAct,inLib,inKw,packType);
    }

    @PostMapping("selectLocation")
    public Map<String, Object> selectLocation(String paramType,String paramValue) throws Exception {
        return rkService.selectLocation(paramType,paramValue);
    }
    
    @PostMapping("getReceivedQuantity")
    public Map<String, Object> getReceivedQuantity(String note,String item,String code) throws Exception {
        return  rkService.getReceivedQuantity( note, item, code);
    }
}
