package com.tengzhi.business.system.modification.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.impl.BaseServiceImpl;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseContract.dao.ECgContractDetailedDao;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContractDetailed;
import com.tengzhi.business.cg.yw.purchaseReceipt.dao.ECkInDao;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseSettle.dao.ECwYsyfDao;
import com.tengzhi.business.ck.yw.ckck.dao.ECkOutDao;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.finance.invoice.dao.invoiceDao;
import com.tengzhi.business.finance.payment.dao.paymentDao;
import com.tengzhi.business.sale.saleProduct.saleContract.dao.EXsContractDetailedDao;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContractDetailed;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.modification.dao.SysDataModificationDao;
import com.tengzhi.business.system.modification.enums.SysDataModificationTableEnum;
import com.tengzhi.business.system.modification.model.SysDataModification;
import com.tengzhi.business.system.modification.service.SysDataModificationService;
import com.tengzhi.business.system.workflow.service.ProcessService;
import com.tengzhi.business.system.workflow.vo.Examine;
import com.tengzhi.business.tooling.toolingstore.service.impl.MyHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.StreamSupport;

/**
 * @author gaodu
 */
@Service
@Transactional
public class SysDataModificationServiceImpl extends BaseServiceImpl implements SysDataModificationService {
    @Autowired
    private SysDataModificationDao sysDataModificationDao;
    @Autowired
    private SysGenNoteService sysGenNoteService;
    @Autowired
    private ECkOutDao eCkOutDao;
    @Autowired
    private ECkInDao eCkInDao;
    @Autowired
    private ECwYsyfDao eCwYsyfDao;
    @Autowired
    private paymentDao paymentDao;
    @Autowired
    private invoiceDao invoiceDao;
    @Autowired
    private EXsContractDetailedDao eXsContractDetailedDao;
    @Autowired
    private ECgContractDetailedDao eCgContractDetailedDao;
    @Autowired
    private ProcessService processService;
    @Override
    public BasePage<Map<String, Object>> getList(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixAnd((c -> {
            c.eq("data_corp", SessionUser.SessionUser().getCorpId(), true);
            c.ge("cast(gen_time as date)", map.get("srchRq1"));
            c.le("cast(gen_time as date)", map.get("srchRq2"));
            c.contains("modify_note", map.get("modifyNote"));
            c.eq("modify_type", map.get("modifyType"));
            c.eq("target_type", map.get("targetType"));
            c.contains("target_note", map.get("targetNote"));
            c.eq("gen_user_id", map.get("genUserId"));
            c.eq("modify_flag", map.get("modifyFlag"));
        }));
        String sql = " select *,f_get_param_name('行业分类',target_type,'"+   SessionUser.getCurrentCorpId()   +"',null,null) target_type_name, "
                + " f_getname('GETUSERNAME', gen_user_id, null, data_corp) gen_user_id_name "
                + " from sys_data_modification "
                + " where 1=1 " + where;
        return sysDataModificationDao.QueryMapPageList(baseDto, sql, true);
    }

    //封装查询数据结果集方法
    private List<Map<String, Object>> SelectData(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String sql = "";
        if (map.get("modifyType").equals("采购合同")) {
            sql += "SELECT\n" +
                    "\tb.*,\n" +
                    "\tb.ht_code old_ht_code,\n" +
                    "\tf_getparamname('GETBCPCODENAME',C.cpcode_name,'','技术',C.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,\n" +
                    "\tC.cpcode_size,\n" +
                    "\tC.cpcode_size_en,\n" +
                    "\t(select param_name from sys_param where param_mod = '技术' and param_id =C.cpcode_bz) AS cpcode_bz \n" +
                    "FROM \n" +
                    "\te_cg_contract A,\n" +
                    "\te_cg_contract_detailed b,\n" +
                    "\te_js_cpcode C \n" +
                    "WHERE\n" +
                    "\tA.ht_no = b.ht_no \n" +
                    "\tAND b.ht_code = C.cpcode_id\n" +
                    "\t" + SqlJoint.whereSuffixAnd(c -> {
                c.eq("a.cg_stype", map.get("targetType"));
                c.eq("a.ht_no", map.get("targetNote"));
            });
        } else if (map.get("modifyType").equals("销售合同")) {
            sql += "SELECT\n" +
                    "\tb.*,\n" +
                    "\tb.ht_code old_ht_code,\n" +
                    "\tf_getparamname('GETBCPCODENAME',C.cpcode_name,'','技术',C.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,\n" +
                    "\tC.cpcode_size,\n" +
                    "\tC.cpcode_size_en,\n" +
                    "\t( SELECT param_name FROM sys_param WHERE param_mod = '技术' AND param_id = C.cpcode_bz ) AS cpcode_bz \n" +
                    "FROM\n" +
                    "\te_xs_contract A,\n" +
                    "\te_xs_contract_detailed b,\n" +
                    "\te_js_cpcode C \n" +
                    "WHERE\n" +
                    "\tA.ht_no = b.ht_no\n" +
                    "\tAND b.ht_code = C.cpcode_id\n" +
                    "\t" + SqlJoint.whereSuffixAnd(c -> {
                c.eq("a.ht_stype", map.get("targetType"));
                c.eq("a.ht_no", map.get("targetNote"));
            });
        } else if (map.get("modifyType").equals("出库")) {
            sql += "SELECT \n" +
                    "\tA.*,\n" +
                    "\tf_getparamname('GETBCPCODENAME',C.cpcode_name,'','技术',C.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,\n" +
                    "\tC.cpcode_size,\n" +
                    "\tC.cpcode_size_en,\n" +
                    "\t( SELECT param_name FROM sys_param WHERE param_mod = '技术' AND param_id = C.cpcode_bz ) AS cpcode_bz \n" +
                    "FROM\n" +
                    "\te_ck_out A,\n" +
                    "\te_js_cpcode C \n" +
                    "WHERE\n" +
                    "\tA.out_code = C.cpcode_id" + SqlJoint.whereSuffixAnd(c -> {
                c.eq("out_type", map.get("targetType"));
                c.eq("out_note", map.get("targetNote"));
            });
        } else if (map.get("modifyType").equals("入库")) {
            sql += "SELECT \n" +
                    "\tA.*,\n" +
                    "\tf_getparamname('GETBCPCODENAME',C.cpcode_name,'','技术',C.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,\n" +
                    "\tC.cpcode_size,\n" +
                    "\tC.cpcode_size_en,\n" +
                    "\t( SELECT param_name FROM sys_param WHERE param_mod = '技术' AND param_id = C.cpcode_bz ) AS cpcode_bz \n" +
                    "FROM\n" +
                    "\te_ck_in A,\n" +
                    "\te_js_cpcode C \n" +
                    "WHERE\n" +
                    "\tA.in_code = C.cpcode_id" + SqlJoint.whereSuffixAnd(c -> {
                c.eq("in_type", map.get("targetType"));
                c.eq("in_note", map.get("targetNote"));
            });
        }
        return sysDataModificationDao.QueryhumpMap(sql);
    }

    @Override
    public Result modifySearch(BaseDto baseDto) {
        return Result.formPage(SelectData(baseDto), SelectData(baseDto).size());
    }

    @Override
    public SysDataModification findByID(String Id) {
        return sysDataModificationDao.findById(Id).orElse(null);
    }

    @Override
    public Result save(Map<String, Object> map) throws Exception {
        Map<String, String> head = (Map<String, String>) map.get("Head");
        List<Map<String, Object>> body = (List<Map<String, Object>>) map.get("Body");
        String targetBeforeData = String.valueOf(ObjectUtil.isNotEmpty(map.get("OldData"))?map.get("OldData"):"");
        String setTargetModifyData = String.valueOf(ObjectUtil.isNotEmpty(map.get("modifyData"))?map.get("modifyData"):"");
        SessionUser sessionUser = SessionUser.SessionUser();
        //初始化SysDataModification
        SysDataModification sysDataModification = new SysDataModification();
        sysDataModification.setModifyNote(sysGenNoteService.getyyMMNote4(SysDataModification.class, "MODIFY"));
        sysDataModification.setTargetAfterData(JSON.toJSONString(body));
        sysDataModification.setModifyType(head.get("modifyType"));
        sysDataModification.setTargetModifyData(setTargetModifyData);
        sysDataModification.setTargetType(head.get("targetType"));
        sysDataModification.setTargetNote(head.get("targetNote"));
        sysDataModification.setTargetBeforeData(targetBeforeData);
        sysDataModification.setGenTime(new Date());
        sysDataModification.setGenUserId(sessionUser.getUserId());
        sysDataModification.setDataCorp(sessionUser.getCorpId());
        sysDataModification.setModifyFlag("登记");
        sysDataModificationDao.save(sysDataModification);
        return Result.resultOk();
    }


    @Override
    public Result update(Map<String, Object> map)throws Exception{
        Map<String, String> head = (Map<String, String>) map.get("Head");
        List<Map<String, Object>> body = (List<Map<String, Object>>) map.get("Body");
        String setTargetModifyData = String.valueOf(ObjectUtil.isNotEmpty(map.get("modifyData"))?map.get("modifyData"):"");
        //初始化SysDataModification
        SysDataModification sysDataModification = new SysDataModification();
        sysDataModification.setTargetModifyData(setTargetModifyData);
        sysDataModification.setTargetAfterData(JSON.toJSONString(body));
        sysDataModificationDao.dynamicSave(sysDataModification,sysDataModificationDao.findById(head.get("modifyNote")).orElse(null));
        return Result.resultOk();
    }

    @Override
    public Result deleteById(String modifyNote) {
        SysDataModification sysDataModification = sysDataModificationDao.findById(modifyNote).orElse(null);
        if (null == sysDataModification) {
            return Result.error("数据不存在");
        } else if (!"登记".equals(sysDataModification.getModifyFlag())) {
            return Result.error("当前状态不允许删除");
        } else {
            sysDataModificationDao.delete(sysDataModification);
            return Result.resultOk();
        }
    }


    @Override
    public Result dynamicModify(Examine examine) {
        // 审批流
        processService.agree(examine);
        //1.查出当前单号对应一个数据包
        SysDataModification sysDataModification=findByID(examine.getBusinessId());
        if ("核准".equals(sysDataModification.getModifyFlag())){
            //2.把json转了直接开修
            List<Map<String,Object>> body= (List<Map<String, Object>>) JSONArray.parse(sysDataModification.getTargetAfterData());
            //3.按类型查出枚举对象中所在的枚举值
            SysDataModificationTableEnum modifyType = SysDataModificationTableEnum.valueOfName(sysDataModification.getModifyType());
            sysDataModification.setTargetTableName(modifyType.getTableName());
            //修改业务表单数据
            if (SysDataModificationTableEnum.CONTRACT_PURCHASE.equals(modifyType)) {
                //为了避免报错过于卡顿采用统计应收应付里的数据动态修改发票主表信息 ||定义去重集合
                Set<Map<String,String>> set=new HashSet();
                StreamSupport.stream(body.spliterator(),false).forEach(item->{
                    ECgContractDetailed detailed = new ECgContractDetailed();
                    detailed.setHtCode(item.get("htCode").toString());
                    detailed.setHtSl(new BigDecimal(item.get("htSl").toString()));
                    detailed.setHtPrice(new BigDecimal(item.get("htPrice").toString()));
                    detailed.setHtJe(new BigDecimal(item.get("htJe").toString()));
                    eCgContractDetailedDao.dynamicSave(detailed, eCgContractDetailedDao.findById(item.get("htMo").toString()).orElse(null));
                    eCgContractDetailedDao.flush();
                    invoiceDao.updateE_Ck_In(item.get("htCode").toString(),new BigDecimal(item.get("htPrice").toString()),sysDataModification.getTargetNote(),item.get("oldHtCode").toString());
                    invoiceDao.updateE_Ck_In_Ls(item.get("htCode").toString(),new BigDecimal(item.get("htPrice").toString()),sysDataModification.getTargetNote(),item.get("oldHtCode").toString());
                    invoiceDao.updateE_Ck_Receiving_Notice(item.get("htCode").toString(),new BigDecimal(item.get("htPrice").toString()),sysDataModification.getTargetNote(),item.get("oldHtCode").toString());
                    invoiceDao.updateE_Cw_Ysyf(item.get("htCode").toString(),new BigDecimal(item.get("htPrice").toString()),sysDataModification.getTargetNote(),"CG",item.get("oldHtCode").toString());
                    //集合收集器
                    Map<String,String> ListenerCollection= new MyHashMap();
                    ListenerCollection.put("htCode",item.get("htCode").toString());
                    set.add(ListenerCollection);
                });
                //从监听编码集合收集器中获取编码修改主表信息 || 提升速度 采用并行流
                StreamSupport.stream(set.spliterator(),false).forEach(item->{
                    //查出应收应付对应票据所有数据
                    List<Map<String,Object>> YsYfmap=invoiceDao.QueryhumpMap("SELECT cw_fph,cw_sje,cw_se FROM e_cw_ysyf where cw_item=?1 and cw_code=?2 and cw_stype=?3 ",sysDataModification.getTargetNote(),item.get("htCode").toString(),"CG");
                    if(!YsYfmap.isEmpty()){
                        BigDecimal cwSje = new BigDecimal(0);
                        BigDecimal cwSe = new BigDecimal(0);
                        BigDecimal cwWse;
                        YsYfmap.forEach(itemChild->{
                            //.开始统计
                            cwSje.add(new BigDecimal(ObjectUtil.isNotEmpty(itemChild.get("cwSje"))?itemChild.get("cwSje").toString():"0"));
                            cwSe.add(new BigDecimal(ObjectUtil.isNotEmpty(itemChild.get("cwSe"))?itemChild.get("cwSe").toString():"0"));
                        });
                        cwWse=cwSje.subtract(cwSe);
                        if(!YsYfmap.get(0).get("cwFph").toString().equals("N")){
                            invoiceDao.updateE_Cw_FP(cwSje,cwSe,cwWse,YsYfmap.get(0).get("cwFph").toString());
                        }
                    }
                });
            } else if (SysDataModificationTableEnum.CONTRACT_SALE.equals(modifyType)) {
                //为了避免报错过于卡顿采用统计应收应付里的数据动态修改发票主表信息 ||定义去重集合
                Set<Map<String,String>> set=new HashSet();
                StreamSupport.stream(body.spliterator(),false).forEach(item->{
                    EXsContractDetailed detailed = new EXsContractDetailed();
                    detailed.setHtCode(item.get("htCode").toString());
                    detailed.setHtSl(new BigDecimal(item.get("htSl").toString()));
                    detailed.setHtPrice(new BigDecimal(item.get("htPrice").toString()));
                    detailed.setHtJe(new BigDecimal(item.get("htJe").toString()));
                    eXsContractDetailedDao.dynamicSave(detailed,eXsContractDetailedDao.findById(item.get("htMo").toString()).orElse(null));
                    eXsContractDetailedDao.flush();
                    invoiceDao.updateE_Ck_In(item.get("htCode").toString(),new BigDecimal(item.get("htPrice").toString()),sysDataModification.getTargetNote(),item.get("oldHtCode").toString());
                    invoiceDao.updateE_Ck_In_Ls(item.get("htCode").toString(),new BigDecimal(item.get("htPrice").toString()),sysDataModification.getTargetNote(),item.get("oldHtCode").toString());
                    invoiceDao.updateE_Ck_Out(item.get("htCode").toString(),new BigDecimal(item.get("htPrice").toString()),sysDataModification.getTargetNote(),item.get("oldHtCode").toString());
                    invoiceDao.updateE_Ck_Out_Ls(item.get("htCode").toString(),new BigDecimal(item.get("htPrice").toString()),sysDataModification.getTargetNote(),item.get("oldHtCode").toString());
                    invoiceDao.updateE_Ck_Delivery_Notice(item.get("htCode").toString(),new BigDecimal(item.get("htPrice").toString()),sysDataModification.getTargetNote(),item.get("oldHtCode").toString());
                    invoiceDao.updateE_Cw_Ysyf(item.get("htCode").toString(),new BigDecimal(item.get("htPrice").toString()),sysDataModification.getTargetNote(),"XS",item.get("oldHtCode").toString());
                    //集合收集器
                    Map<String,String> ListenerCollection= new MyHashMap();
                    ListenerCollection.put("htCode",item.get("htCode").toString());
                    set.add(ListenerCollection);
                });
                //从监听编码集合收集器中获取编码修改主表信息 || 提升速度 采用并行流
                StreamSupport.stream(set.spliterator(),false).forEach(item->{
                    //查出应收应付对应票据所有数据
                        List<Map<String,Object>> YsYfmap=invoiceDao.QueryhumpMap("SELECT cw_fph,cw_sje,cw_se FROM e_cw_ysyf where cw_item=?1 and cw_code=?2 and cw_stype=?3 ",sysDataModification.getTargetNote(),item.get("htCode"),"XS");
                    if(!YsYfmap.isEmpty()) {
                        BigDecimal cwSje = new BigDecimal(0);
                        BigDecimal cwSe = new BigDecimal(0);
                        BigDecimal cwWse;
                        YsYfmap.forEach(itemChild -> {
                            //.开始统计
                            cwSje.add(new BigDecimal(ObjectUtil.isNotEmpty(itemChild.get("cwSje")) ? itemChild.get("cwSje").toString() : "0"));
                            cwSe.add(new BigDecimal(ObjectUtil.isNotEmpty(itemChild.get("cwSe")) ? itemChild.get("cwSe").toString() : "0"));
                        });
                        cwWse = cwSje.subtract(cwSe);
                        if(!YsYfmap.get(0).get("cwFph").toString().equals("N")){
                            invoiceDao.updateE_Cw_FP(cwSje,cwSe,cwWse,YsYfmap.get(0).get("cwFph").toString());
                        }
                    }
                });

            } else if (SysDataModificationTableEnum.WAREHOUSE_OUT.equals(modifyType)) {
                body.forEach(item -> {
                    ECkOut eCkOut = new ECkOut();
                    eCkOut.setOutPh(ObjectUtil.isNotEmpty(item.get("outPh"))?item.get("outPh").toString():null);
                    eCkOut.setOutLuono(ObjectUtil.isNotEmpty(item.get("outLuono"))?item.get("outLuono").toString():null);
                    eCkOutDao.dynamicSave(eCkOut, eCkOutDao.findById(new ECkOut.ECkOut_PK(sysDataModification.getTargetNote(), item.get("outPack").toString())).orElse(null));
                    eCkOutDao.flush();
                    String out=SqlJoint.where(e->{
                        if(ObjectUtil.isNotEmpty(item.get("outPh"))) {
                            e.JOinStrComma("out_ph", item.get("outPh").toString());
                        }
                        if(ObjectUtil.isNotEmpty(item.get("outLuono"))) {
                            e.JOinStrComma("out_luono", item.get("outLuono").toString());
                        }
                    },false);
                    if(StrUtil.isNotBlank(out)){
                        //修改的in_pack 关联到e_ck_out的out_pack修改out_ph、out_luohao
                        String sqlOut="update e_ck_out set "+out+" where out_original_pack='"+item.get("outPack").toString()+"'";
                        eCkOutDao.executeUpdateSql(sqlOut);
                    }
                });
            } else if (SysDataModificationTableEnum.WAREHOUSE_IN.equals(modifyType)) {
                body.forEach(item -> {
                    ECkIn eCkIn = new ECkIn();
                    eCkIn.setInPh(ObjectUtil.isNotEmpty(item.get("inPh"))?item.get("inPh").toString():null);
                    eCkIn.setInLuono(ObjectUtil.isNotEmpty(item.get("inLuono"))?item.get("inLuono").toString():null);
                    eCkInDao.dynamicSave(eCkIn, eCkInDao.findById(new ECkIn.ECkIn_PK(sysDataModification.getTargetNote(),item.get("inPack").toString())).orElse(null));
                    eCkOutDao.flush();
                    String in=SqlJoint.where(e->{
                        if(ObjectUtil.isNotEmpty(item.get("inPh"))){
                            e.JOinStrComma("in_ph",item.get("inPh").toString());
                        }
                        if(ObjectUtil.isNotEmpty(item.get("inLuono"))){
                            e.JOinStrComma("in_luono",item.get("inLuono").toString());
                        }
                    },false);
                    String out=SqlJoint.where(e->{
                        if(ObjectUtil.isNotEmpty(item.get("inPh"))) {
                            e.JOinStrComma("out_ph", item.get("inPh").toString());
                        }
                        if(ObjectUtil.isNotEmpty(item.get("inLuono"))) {
                            e.JOinStrComma("out_luono", item.get("inLuono").toString());
                        }
                    },false);
                    if(StrUtil.isNotBlank(in)){
                        //修改的数据的in_pack 关联本表的 in_original_pack 修改
                        String sqlIn="update e_ck_in set "+in+" where in_original_pack='"+item.get("inPack").toString()+"'";
                        eCkOutDao.executeUpdateSql(sqlIn);
                    }
                    if(StrUtil.isNotBlank(out)){
                        //修改的in_pack 关联到e_ck_out的out_pack修改out_ph、out_luohao
                        String sqlOut="update e_ck_out set "+out+" where out_original_pack='"+item.get("inPack").toString()+"'";
                        eCkOutDao.executeUpdateSql(sqlOut);
                    }
                });
            }
        }
        return Result.resultOk();
    }
}
