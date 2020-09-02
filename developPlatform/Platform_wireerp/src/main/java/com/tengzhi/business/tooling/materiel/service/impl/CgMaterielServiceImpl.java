package com.tengzhi.business.tooling.materiel.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.base.common.WarehouseActionCaliber;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.mesGz.gzck.vo.ECKOutVo;
import com.tengzhi.business.mesGz.gzck.vo.EckOut;
import com.tengzhi.business.mesGz.gzck.vo.EckOut.ECkOut_PK;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.service.SysParamService;
import com.tengzhi.business.tooling.materiel.dao.CgMaterielDao;
import com.tengzhi.business.tooling.materiel.service.CgMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class CgMaterielServiceImpl implements CgMaterielService {

    @Autowired
    private CgMaterielDao materielDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Autowired
    private SysParamService sysParamService;

    @Override
    public BasePage<EckOut> findAll(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();

        String sqlWhere = " where out_act in (select param_id from sys_param where param_name ='" + WarehouseActionCaliber.lyth + "' )   ";
        if (ObjectUtil.isNotEmpty(map.get("srchFlag"))) {
            sqlWhere += " and a.out_flag = '" + map.get("srchFlag") + "'";
        }
        if (ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
            sqlWhere += " and a.out_rq >='" + map.get("srchRq1") + "' ";
        }
        if (ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
            sqlWhere += " and a.out_rq <='" + map.get("srchRq2") + "' ";
        }
        if (ObjectUtil.isNotEmpty(map.get("srchNote"))) {
            sqlWhere += " and a.out_note like '%" + map.get("srchNote") + "%'";
        }
        if (ObjectUtil.isNotEmpty(map.get("srchDept"))) {
            sqlWhere += " and a.out_customer = '" + map.get("srchDept") + "'";
        }
        if (ObjectUtil.isNotEmpty(map.get("srchFlag"))) {
            sqlWhere += " and a.out_flag = '" + map.get("srchFlag") + "'";
        }
        if (ObjectUtil.isNotEmpty(map.get("outType"))) {
            sqlWhere += " and a.out_type = '" + map.get("outType") + "'";
        }
        String sql = "select f_getname('GETDWNAME',out_customer,'',data_corp)out_customer,out_rq ,out_note  ,f_get_param_name('库房档案',out_lib,'"+   SessionUser.getCurrentCorpId()   +"','cn')out_lib,"
                + "out_act ,abs(sum(out_js)) out_js ,abs(sum(out_sl)) out_sl,abs(sum(out_zl)) out_zl,out_flag ,f_getname('GETUSERNAME',data_man,'',data_corp)data_man ,MAX(data_date) data_date,data_corp  "
                + " from  e_ck_out a " + sqlWhere
                + "  group by out_rq,out_note,out_customer,out_act,out_flag,data_man,data_corp,out_lib  ";
        return materielDao.QueryPageLists(baseDto, sql);
    }

    @Override
    public Map<String, Object> getKcList(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        Map<String, Object> rmap = new HashMap<String, Object>();
        String sqlWhere = "  where a.cpcode_type='"+map.get("outType")+"'   ";
        if (ObjectUtil.isNotEmpty(map.get("lib"))) {
            sqlWhere += " and a.lib = '" + map.get("outLib") + "'";
        }
        if (ObjectUtil.isNotEmpty(map.get("cpcodeCode"))) {
            sqlWhere += " and a.code = '" + map.get("cpcodeCode") + "' ";
        }
        if (ObjectUtil.isNotEmpty(map.get("cpcodeSize"))) {
            sqlWhere += " and a.cpcode_size = '" + map.get("cpcodeCode") + "' ";
        }
        if (ObjectUtil.isNotEmpty(map.get("cpcodeName"))) {
            sqlWhere += " and a.cpcode_name like '%" + map.get("cpcodeName") + "%' ";
        }
        if (ObjectUtil.isNotEmpty(map.get("cpcodeFl"))) {
            sqlWhere += " and a.cpcode_fl = '" + map.get("cpcodeFl") + "' ";
        }
        String fhStype = map.get("fhStype");
        String sql = "";

        if ("DBZFH".equals(fhStype)) {
            sql = "	select  a.code,f_getparamname('GETBCPCODENAME',a.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_name,a.cpcode_size,sum(a.js)js,sum(a.sl)sl, a.in_bpack bpack,a.cpcode02 out_hs,0 out_price,'RMB' out_bz,0 out_tax,a.cpcode_type out_type,cpcodeflname cpcode_fl from v_xnkc_item a    where   " + sqlWhere +
                    " group by a.in_contract  ,a.code,a.cpcode_name,a.cpcode_size, a.in_bpack  ,a.cpcode02  ,d.ht_price  ,c.ht_currency  ,c.ht_tax  ,a.cpcode_type  ,cpcodeflname";

        } else {
            sql = "	select  a.code,f_getparamname('GETBCPCODENAME',a.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_name,a.cpcode_size,a.js,a.sl,a.pack,a.in_bpack bpack,a.cpcode02 out_hs,0 out_price,'RMB' out_bz,0 out_tax,a.cpcode_type out_type,cpcodeflname cpcode_fl from v_xnkc_item a   " + sqlWhere;

        }


        rmap.put("data", materielDao.QueryListMap(sql));
        rmap.put("status", true);
        return rmap;
    }

    @Override
    public EckOut findByNote(String outNote) {
        EckOut in = new EckOut();
        String sqlString = " select  distinct a.out_note,a.out_customer,a.out_lib,a.out_rq,f_getname('GETDWNAME',a.out_customer,'',a.data_corp)customer,out_type from e_ck_out a where   out_note ='" + outNote + "' ";
        BasePage<EckOut> list = materielDao.QueryNoPageLists(sqlString);
        if (list.getContent().size() > 0) {
            in = list.getContent().get(0);
        }
        return in;
    }

    @Override
    public BasePage<EckOut> getOutList(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        String sqlWhere = "   ";
        if (ObjectUtil.isNotEmpty(map.get("outNote"))) {
            sqlWhere += " and out_note = '" + map.get("outNote") + "'";
        }
        String sql = "select out_original_pack,out_contract,out_code,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_name,cpcode_size,cpcode_size_en,abs(out_js) out_js,abs(out_sl) out_sl,out_pack,out_bpack,out_hs, out_price, out_bz, out_tax,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',out_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl from e_ck_out a ,e_js_cpcode b  where a.out_code=b.cpcode_id  " + sqlWhere;
        if (ObjectUtil.isNotEmpty(map.get("fhStype"))) {
            if ("DBZFH".equals(map.get("fhStype"))) {
                sql = "select out_original_pack,out_contract,out_code,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_name,cpcode_size,cpcode_size_en,abs(sum(out_js)) out_js,abs(sum(out_sl)) out_sl,out_bpack,out_hs, out_price, out_bz, out_tax,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',out_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl from e_ck_out a ,e_js_cpcode b  where a.out_code=b.cpcode_id  " + sqlWhere
                        + " group by out_contract  ,out_code,cpcode_name,cpcode_size,cpcode_size_en,out_bpack,out_hs, out_price, out_bz, out_tax,  b.cpcode_fl, out_type ";
            }
        }

        return materielDao.QueryPageLists(baseDto, sql);
    }

    @Override
    public Result save(ECKOutVo eCkOutVo) throws Exception {

        String fhStype = eCkOutVo.geteCkOut().getFhStype();

        List<EckOut> addECkOuts = new ArrayList<EckOut>();//添加数据
        List<EckOut> modifyedECkOuts = new ArrayList<EckOut>();
        List<EckOut> deletedECkOuts = new ArrayList<EckOut>();

        BigDecimal initBigDecimal = BigDecimal.valueOf(0L);
        SessionUser sessionUser = SessionUser.SessionUser();
        String parameterId = materielDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
                , WarehouseActionCaliber.lyth,eCkOutVo.geteCkOut().getOutType(),sessionUser.getCorpId());
        SysParams sysParams = sysParamService.findByParameterId(parameterId, "仓库收发");
        String note = sysGenNoteService.getInOutNote(ECkOut.class,eCkOutVo.geteCkOut().getOutType(), WarehouseActionCaliber.lyth,sessionUser.getCorpId());
        if ("XBZFH".equals(fhStype)) {
            //小包装
            eCkOutVo.geteCkOut().setOutNote(note);
            eCkOutVo.geteCkOut().setOutFlag("登记");
            eCkOutVo.geteCkOut().setDataMan(sessionUser.getUserId());
            eCkOutVo.geteCkOut().setDataDate(new Date());
            eCkOutVo.geteCkOut().setDataCorp(sessionUser.getCorpId());
            eCkOutVo.geteCkOut().setOutAct(parameterId);
            for (int i = 0; i < eCkOutVo.getAddedList().size(); i++) {
                EckOut model = eCkOutVo.getAddedList().get(i);
                model.setOutNote(note);
                model.setOutRq(eCkOutVo.geteCkOut().getOutRq());
                model.setOutAct(sysParams.getParamId());
                model.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
                model.setOutFlag(eCkOutVo.geteCkOut().getOutFlag());
                model.setDataMan(sessionUser.getUserId());
                model.setDataDate(eCkOutVo.geteCkOut().getDataDate());
                model.setDataCorp(eCkOutVo.geteCkOut().getDataCorp());
                model.setOutLib(eCkOutVo.geteCkOut().getOutLib());
                model.setOutKfcode(model.getOutCode());

                model.setOutJs(model.getOutJs() == null ? initBigDecimal : model.getOutJs().multiply(sysParams.getParamValue()));
                model.setOutSl(model.getOutSl() == null ? initBigDecimal : model.getOutSl().multiply(sysParams.getParamValue()));
                model.setOutZl(model.getOutZl() == null ? initBigDecimal : model.getOutZl().multiply(sysParams.getParamValue()));
                String pack = sysGenNoteService.getyyyyMMddNote4(ECkIn.class, "P");
                // 默认大包装号和包装号相等，后续可能需要根据产品规则生成
                model.setOutBpack(pack);
                model.setOutPack(pack);
                model.setOutType(eCkOutVo.geteCkOut().getOutType());
                addECkOuts.add(model);
            }

            // 修改
            if (!eCkOutVo.getModifyedList().isEmpty()) {
                for (int i = 0; i < eCkOutVo.getModifyedList().size(); i++) {
                    EckOut model = eCkOutVo.getModifyedList().get(i);
                    model.setOutNote(note);
                    model.setOutRq(eCkOutVo.geteCkOut().getOutRq());
                    model.setOutAct(eCkOutVo.geteCkOut().getOutAct());
                    model.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
                    model.setOutFlag(eCkOutVo.geteCkOut().getOutFlag());
                    model.setDataMan(sessionUser.getUserId());
                    model.setDataDate(eCkOutVo.geteCkOut().getDataDate());
                    model.setDataCorp(eCkOutVo.geteCkOut().getDataCorp());
                    model.setOutKfcode(model.getOutCode());
                    model.setOutLib(eCkOutVo.geteCkOut().getOutLib());
                    //数量，重量，件数*paramvalue
                    model.setOutJs(model.getOutJs() == null ? initBigDecimal : model.getOutJs().multiply(sysParams.getParamValue()));
                    model.setOutSl(model.getOutSl() == null ? initBigDecimal : model.getOutSl().multiply(sysParams.getParamValue()));
                    model.setOutZl(model.getOutZl() == null ? initBigDecimal : model.getOutZl().multiply(sysParams.getParamValue()));
                    // 判断保存的包装号有没有重复
                    model.setOutPh(eCkOutVo.geteCkOut().getOutPh());
                    model.setOutLuono(eCkOutVo.geteCkOut().getOutLuono());


                    modifyedECkOuts.add(model);
                }
            }

            deletedECkOuts = eCkOutVo.getDeletedList();

        } else {
            //大包装号
            //新增
            for (int i = 0; i < eCkOutVo.getAddedList().size(); i++) {
                EckOut eck = eCkOutVo.getAddedList().get(i);
                List<EckOut> models = getDetailByBpack(eck.getOutBpack(), eck.getOutContract());
                for (EckOut model : models) {
                    model.setOutNote(note);
                    model.setOutRq(eCkOutVo.geteCkOut().getOutRq());
                    model.setOutFlag("登记");
                    model.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
                    model.setOutLib(eCkOutVo.geteCkOut().getOutLib());
                    model.setOutType(eck.getOutType());
                    model.setDataMan(sessionUser.getUserId());
                    model.setDataDate(new Date());
                    model.setDataCorp(sessionUser.getCorpId());
                    model.setOutAct(parameterId);
                    model.setOutKfcode(model.getOutCode());
                    model.setOutLib(eCkOutVo.geteCkOut().getOutLib());
                    addECkOuts.add(model);
                }

            }
            //删除
            for (int i = 0; i < eCkOutVo.getDeletedList().size(); i++) {
                EckOut eck = eCkOutVo.getDeletedList().get(i);
                List<EckOut> models = getDetailByBpack(eck.getOutBpack(), eck.getOutContract());
                for (EckOut item : models) {
                    deletedECkOuts.add(item);
                }

            }


        }
        // 业务逻辑判断end //开始保存到数据库

        if (!addECkOuts.isEmpty()) {
            addECkOuts.forEach(model -> {
                materielDao.save(model);
            });
        }
        if (!modifyedECkOuts.isEmpty()) {
            modifyedECkOuts.forEach(model -> {
                materielDao.dynamicSave(model, materielDao.findById(new ECkOut_PK(note, model.getOutPack())).orElse(null));
            });
        }
        if (!deletedECkOuts.isEmpty()) {
            eCkOutVo.getDeletedList().forEach(model -> {
                ECkOut_PK pk = new ECkOut_PK(eCkOutVo.geteCkOut().getOutNote(), model.getOutPack());
                materielDao.deleteById(pk);
            });
        } // end

        return Result.resultOk(eCkOutVo.geteCkOut());
    }

    @Override
    public Result update(ECKOutVo eCkOutVo) {

        String fhStype = eCkOutVo.geteCkOut().getFhStype();

        BigDecimal initBigDecimal = new BigDecimal("0");
        List<EckOut> addECkOut = new ArrayList<EckOut>();
        List<EckOut> modifyedECkOuts = new ArrayList<EckOut>();
        List<EckOut> deletedECkOuts = new ArrayList<EckOut>();
        SessionUser sessionUser = SessionUser.SessionUser();
        String parameterId = materielDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
                , WarehouseActionCaliber.scly,eCkOutVo.geteCkOut().getOutType(),sessionUser.getCorpId());
        SysParams sysParams = sysParamService.findByParameterId(parameterId, "仓库收发");

        eCkOutVo.geteCkOut().setDataMan(sessionUser.getUserId());
        eCkOutVo.geteCkOut().setDataDate(new Date());
        eCkOutVo.geteCkOut().setDataCorp(sessionUser.getCorpId());
        eCkOutVo.geteCkOut().setOutFlag("登记");
        eCkOutVo.geteCkOut().setOutAct(parameterId);
        // 业务逻辑判断start
        // 新增
        if ("XBZFH".equals(fhStype)) {
            //小包装
            for (int i = 0; i < eCkOutVo.getAddedList().size(); i++) {
                EckOut model = eCkOutVo.getAddedList().get(i);
                model.setOutNote(eCkOutVo.geteCkOut().getOutNote());
                model.setOutRq(eCkOutVo.geteCkOut().getOutRq());
                model.setOutAct(eCkOutVo.geteCkOut().getOutAct());
                model.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
                model.setOutFlag(eCkOutVo.geteCkOut().getOutFlag());
                model.setDataMan(sessionUser.getUserId());
                model.setDataDate(eCkOutVo.geteCkOut().getDataDate());
                model.setDataCorp(eCkOutVo.geteCkOut().getDataCorp());
                model.setOutKfcode(model.getOutCode());
                model.setOutLib(eCkOutVo.geteCkOut().getOutLib());
                //数量，重量，件数*paramvalue
                model.setOutJs(model.getOutJs() == null ? initBigDecimal : model.getOutJs().multiply(sysParams.getParamValue()));
                model.setOutSl(model.getOutSl() == null ? initBigDecimal : model.getOutSl().multiply(sysParams.getParamValue()));
                model.setOutZl(model.getOutZl() == null ? initBigDecimal : model.getOutZl().multiply(sysParams.getParamValue()));
                // 默认大包装号和包装号相等，后续可能需要根据产品规则生成
                model.setOutBpack(model.getOutPack());
                addECkOut.add(model);
            }
            // 修改
            if (!eCkOutVo.getModifyedList().isEmpty()) {
                for (int i = 0; i < eCkOutVo.getModifyedList().size(); i++) {
                    EckOut model = eCkOutVo.getModifyedList().get(i);
                    model.setOutNote(eCkOutVo.geteCkOut().getOutNote());
                    model.setOutRq(eCkOutVo.geteCkOut().getOutRq());
                    model.setOutAct(eCkOutVo.geteCkOut().getOutAct());
                    model.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
                    model.setOutFlag(eCkOutVo.geteCkOut().getOutFlag());
                    model.setDataMan(sessionUser.getUserId());
                    model.setDataDate(eCkOutVo.geteCkOut().getDataDate());
                    model.setDataCorp(eCkOutVo.geteCkOut().getDataCorp());
                    model.setOutKfcode(model.getOutCode());
                    model.setOutLib(eCkOutVo.geteCkOut().getOutLib());
                    //数量，重量，件数*paramvalue
                    model.setOutJs(model.getOutJs() == null ? initBigDecimal : model.getOutJs().multiply(sysParams.getParamValue()));
                    model.setOutSl(model.getOutSl() == null ? initBigDecimal : model.getOutSl().multiply(sysParams.getParamValue()));
                    model.setOutZl(model.getOutZl() == null ? initBigDecimal : model.getOutZl().multiply(sysParams.getParamValue()));
                    // 判断保存的包装号有没有重复

                    modifyedECkOuts.add(model);
                }
            }
        } else {
            //大包装号
            //新增
            for (int i = 0; i < eCkOutVo.getAddedList().size(); i++) {
                EckOut eck = eCkOutVo.getAddedList().get(i);
                List<EckOut> models = getDetailByBpack(eck.getOutBpack(), eck.getOutContract());
                for (EckOut model : models) {
                    model.setOutNote(eCkOutVo.geteCkOut().getOutNote());
                    model.setOutRq(eCkOutVo.geteCkOut().getOutRq());
                    model.setOutFlag("登记");
                    model.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
                    model.setOutLib(eCkOutVo.geteCkOut().getOutLib());
                    model.setOutType(eck.getOutType());
                    model.setDataMan(sessionUser.getUserId());
                    model.setDataDate(new Date());
                    model.setDataCorp(sessionUser.getCorpId());
                    model.setOutAct(parameterId);
                    model.setOutKfcode(model.getOutCode());
                    model.setOutLib(eCkOutVo.geteCkOut().getOutLib());
                    addECkOut.add(model);
                }

            }
            //删除
            for (int i = 0; i < eCkOutVo.getDeletedList().size(); i++) {
                EckOut eck = eCkOutVo.getDeletedList().get(i);
                List<EckOut> models = getDetailByBpack(eck.getOutBpack(), eck.getOutContract());
                for (EckOut item : models) {
                    deletedECkOuts.add(item);
                }

            }


        }
        // 业务逻辑判断end
        // 开始保存到数据库
        if (!addECkOut.isEmpty()) {
            addECkOut.forEach(model -> {
                materielDao.save(model);
            });
        }
        if (!modifyedECkOuts.isEmpty()) {
            modifyedECkOuts.forEach(model -> {
            	materielDao.dynamicSave(model, materielDao.findById(new ECkOut_PK(eCkOutVo.geteCkOut().getOutNote(), model.getOutPack())).orElse(null));
            });
        }

        if (!eCkOutVo.getDeletedList().isEmpty()) {
            eCkOutVo.getDeletedList().forEach(model -> {
                ECkOut_PK pk = new ECkOut_PK(eCkOutVo.geteCkOut().getOutNote(), model.getOutPack());
                materielDao.deleteById(pk);
            });
        }

        return Result.resultOkMsg("修改成功");

    }

    @Override
    public Result deleteByNote(String outNote) {
        materielDao.deleteByNote(outNote);
        return Result.resultOkMsg("删除");
    }

    @SuppressWarnings("unused")
    private boolean checkExists(EckOut model, List<EckOut> eCkOuts) {
        boolean isflag = false;
        for (int i = 0; i < eCkOuts.size(); i++) {
            EckOut indexrow = eCkOuts.get(i);
            if (indexrow.getOutPack().equals(model.getOutPack())) {
                isflag = true;
                break;
            }
        }
        return isflag;
    }

    @Override
    public List<Map> getItemSelectList(String customer, String outType) {
        String sqlString = "select  distinct a.ht_no combid,a.ht_no combtext  from e_xs_contract a, e_xs_contract_detailed b  where  a.ht_no=b.ht_no and a.ht_customer ='" + customer + "'and a.ht_stype = '" + outType + "' and b.ht_flag<>'核销' ";
        return materielDao.QueryListMap(sqlString);
    }

    @Override
    public List<Map> getCodeSelectList(String contract) {
        String sqlString = "  select ht_code combid, b.cpcode_name||'φ'||b.cpcode_size||'数量:'||ht_sl combtext  from e_xs_contract_detailed a,e_js_cpcode b  where a.ht_code=b.cpcode_id   and a.ht_no ='" + contract + "'    ";
        return materielDao.QueryListMap(sqlString);
    }

    @Override
    public Result confirm(String outNote) {
        materielDao.updateFlag(outNote, "结算");
        return Result.resultOkMsg("修改成功");
    }

    @Override
    public Result cancel(String outNote) {
        materielDao.updateFlag(outNote, "登记");
        return Result.resultOkMsg("修改成功");
    }

    @Override
    public Result getFlag(String outNote, String flag) {
        String flagString = materielDao.getFlag(outNote);
        if (flag.equals(flagString)) {
            return Result.resultOkMsg("操作成功");
        } else {
            return Result.error("该单不是“" + flag + "”状态,不能操作！");
        }

    }

    @Override
    public List<EckOut> getDetailByBpack(String bpack, String contract) {

        return materielDao.QueryListModel(EckOut.class, "   select out_original_pack,code out_code,abs(sl) out_sl,abs(js) out_js,pack out_pack,in_bpack out_bpack,in_price out_price,xs.ht_tax out_tax,xs.ht_currency out_bz,xs.ht_no out_contract from v_ck_mx mx ,e_xs_contract xs where    in_bpack= :1  and xs.ht_no = :2", bpack, contract);
    }

    @Override
    public Result facksele(String park){
        String ss="select out_code,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_name,cpcode_size,cpcode_size_en,cpcode_xp,f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_fl,out_hs,out_bz,out_contract,out_tax,out_price from e_ck_out,e_js_cpcode where out_code=cpcode_id and  out_pack='"+park+"'";
        List<EckOut> eCkOuts=materielDao.QueryListModel(EckOut.class,ss);
        if(eCkOuts.size()==0){
            return Result.error("没有找到数据");
        }else{
           return Result.resultOk(eCkOuts.get(0));
        }
    }
}
