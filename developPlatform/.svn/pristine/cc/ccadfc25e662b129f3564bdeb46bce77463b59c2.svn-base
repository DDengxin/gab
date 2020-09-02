package com.tengzhi.business.sale.processProduct.incomingMaterialReturn.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.base.common.WarehouseActionCaliber;
import com.tengzhi.business.cg.yw.purchaseReceipt.dao.ECkInDao;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;
import com.tengzhi.business.ck.yw.ckck.dao.ECkOutDao;
import com.tengzhi.business.sale.processProduct.incomingMaterialReturn.service.IncomingMaterialReturnService;
import com.tengzhi.business.sale.processProduct.incomingMaterialWarehouse.service.IncomingMaterialWarehouseService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.service.SysParamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class IncomingMaterialReturnServiceImpl  implements IncomingMaterialReturnService {

    @Autowired
    private ECkInDao eCkInDao ;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Autowired
    private SysParamService sysParamService;

    @Override
    public BasePage<Map<String, Object>> getSrchTopList(BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        String sqlWhere = SqlJoint.whereSuffixWhere(c ->{
                    c.ge("in_rq",map.get("srchRq1"));
                    c.le("in_rq",map.get("srchRq2"));
                    c.eq("in_type",map.get("inType"));
                    c.eq("in_note",map.get("inNote"));
                    c.eq("in_customer",map.get("inCustomer"));
                    c.eq("in_lib",map.get("inLib"));
                    c.eq("in_type",map.get("inType"));
                    c.eq("data_corp",SessionUser.getCurrentCorpId());
                });
        if(StringUtils.isNotBlank(map.get("inType"))) {
            String parameterId = eCkInDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
                    , WarehouseActionCaliber.llth,map.get("inType"),SessionUser.getCurrentCorpId());
            sqlWhere +=" and in_act='"+parameterId+"' ";
        }
        String sql = " select to_char(in_rq,'yyyy-mm-dd')in_rq ,in_note ,in_customer ,in_lib,in_act,in_bz,in_tax ,abs(sum(in_js)) in_js,abs(sum(in_sl)) in_sl,abs(sum(in_zl)) in_zl,abs(sum(in_sl*in_price)) inje,in_flag ,data_man ,MAX(data_date) data_date,data_corp  " +
                ",f_get_param_name('产品大类',in_type,'"+   SessionUser.getCurrentCorpId()   +"') intypename,f_get_param_name('仓库收发',in_act,'"+   SessionUser.getCurrentCorpId()   +"') inactname,f_get_param_name('交易币种',in_bz,'"+   SessionUser.getCurrentCorpId()   +"') inbzname," +
                " f_getname('GETDWEXP',in_customer,'',data_corp) incustomername,f_get_param_name('库房档案',in_lib,'"+   SessionUser.getCurrentCorpId()   +"') inlibname,f_getname('GETUSERNAME',data_man,'',data_corp) datamanname  " +
                " from  e_ck_in " + sqlWhere + "  group by in_rq,in_note,in_customer,in_act,in_flag,data_man,data_corp,in_lib,in_bz,in_tax,in_type ";
        return eCkInDao.QueryToMapPage(baseDto,sql);
    }

    @Override
    public BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        String sqlWhere = SqlJoint.whereSuffixAnd(c ->{
            c.eq("in_note",map.get("inNote"));
        });
        String sql = " select in_note , in_code,abs(in_js) in_js ,abs(in_sl) in_sl,abs(in_sl*in_price) in_je ,abs(in_zl) in_zl ,in_pack ,in_bpack ,in_price ,in_contract ,"
                + " f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name_name,cpcode_name, "
                + " f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name, b.cpcode_size, "
                + " f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name ,cpcode_size_en, "
                + " f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl_name,cpcode_fl,"
                + " f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz_name, cpcode_bz ,"
                + " f_getparamname('GETCPCODEXP',b.cpcode_xp,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name, b.cpcode_xp ,"
                + " b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03  "
                + " from e_ck_in ,e_js_cpcode b where in_code = cpcode_id "+sqlWhere;
        return eCkInDao.QueryToMapPage(baseDto,sql);
    }

    @Override
    public Result saveData(ECkInVo eCkInVo) throws Exception {
        List<ECkIn> addECkIns=new ArrayList<ECkIn>();
        List<ECkIn> modifyedECkIns=new ArrayList<ECkIn>();
        String inType=eCkInVo.geteCkIn().getInType();
        SessionUser securityUser = SessionUser.SessionUser();
        String parameterId = eCkInDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
                , WarehouseActionCaliber.llth,inType,securityUser.getCorpId());
        SysParams sysParams = sysParamService.findByParameterId(parameterId, "仓库收发");
        String note = sysGenNoteService.getInOutNote(ECkIn.class,inType, WarehouseActionCaliber.cgth,securityUser.getCorpId());
        eCkInVo.geteCkIn().setInNote(note);
        eCkInVo.geteCkIn().setInFlag("登记");
        eCkInVo.geteCkIn().setDataMan(securityUser.getUserId());
        eCkInVo.geteCkIn().setDataDate(new Date());
        eCkInVo.geteCkIn().setDataCorp(securityUser.getCorpId());
        eCkInVo.geteCkIn().setInAct(parameterId);
        //业务逻辑判断start
        //新增
        for(int i=0;i<eCkInVo.getAddedList().size();i++) {
            ECkIn model=eCkInVo.getAddedList().get(i);
            model.setInNote(note);
            model.setInRq(eCkInVo.geteCkIn().getInRq());
            model.setInAct(eCkInVo.geteCkIn().getInAct());
            model.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
            model.setInFlag(eCkInVo.geteCkIn().getInFlag());
            model.setDataMan(securityUser.getUserId());
            model.setDataDate(eCkInVo.geteCkIn().getDataDate());
            model.setDataCorp(eCkInVo.geteCkIn().getDataCorp());
            model.setInCode(model.getInCode());
            model.setInKfcode(model.getInCode());
            model.setInBz(eCkInVo.geteCkIn().getInBz());
            model.setInTax(eCkInVo.geteCkIn().getInTax());
            //数量，重量，件数*paramvalue
            model.setInJs(model.getInJs().multiply(sysParams.getParamValue()));
            model.setInSl(model.getInSl().multiply(sysParams.getParamValue()));
            model.setInZl(model.getInZl().multiply(sysParams.getParamValue()));
            //model.setInBpack(model.getInPack());
            addECkIns.add(model);
        }
        //修改
        if (!eCkInVo.getModifyedList().isEmpty()) {
            for(int i=0;i<eCkInVo.getModifyedList().size();i++) {
                ECkIn item=eCkInVo.getModifyedList().get(i);
                item.setInNote(note);
                item.setInRq(eCkInVo.geteCkIn().getInRq());
                item.setInAct(eCkInVo.geteCkIn().getInAct());
                item.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
                item.setInFlag(eCkInVo.geteCkIn().getInFlag());
                item.setDataMan(securityUser.getUserId());
                item.setDataDate(eCkInVo.geteCkIn().getDataDate());
                item.setDataCorp(eCkInVo.geteCkIn().getDataCorp());
                item.setInCode(item.getInKfcode());
                item.setInBz(eCkInVo.geteCkIn().getInBz());
                item.setInTax(eCkInVo.geteCkIn().getInTax());
                item.setInJs(item.getInJs().multiply(sysParams.getParamValue()));
                item.setInSl(item.getInSl().multiply(sysParams.getParamValue()));
                item.setInZl(item.getInZl().multiply(sysParams.getParamValue()));
                modifyedECkIns.add(item);
            }
        }
        //业务逻辑判断end
        //开始保存到数据库
        if (!addECkIns.isEmpty()) {
            addECkIns.forEach(item -> {
                eCkInDao.save(item);
            });
        }
        if (!modifyedECkIns.isEmpty()) {
            modifyedECkIns.forEach(item -> {
                eCkInDao.dynamicSave(item, eCkInDao.findById(new ECkIn.ECkIn_PK(note, item.getInPack())).orElse(null));
            });
        }
        if (!eCkInVo.getDeletedList().isEmpty()) {
            eCkInVo.getDeletedList().forEach( item ->{
                ECkIn.ECkIn_PK pk = new ECkIn.ECkIn_PK(eCkInVo.geteCkIn().getInNote(),item.getInPack() );
                eCkInDao.deleteById(pk);
            });
        }
        //end
        return Result.resultOk(eCkInVo.geteCkIn());
    }

    @Override
    public Result updateData(ECkInVo eCkInVo) throws Exception {
        List<ECkIn> addECkIns=new ArrayList<ECkIn>();
        List<ECkIn> modifyedECkIns=new ArrayList<ECkIn>();
        String inType=eCkInVo.geteCkIn().getInType();
        SessionUser securityUser = SessionUser.SessionUser();
        String parameterId = eCkInDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
                , WarehouseActionCaliber.llth,inType,securityUser.getCorpId());
        SysParams sysParams = sysParamService.findByParameterId(parameterId, "仓库收发");
        eCkInVo.geteCkIn().setInFlag("登记");
        eCkInVo.geteCkIn().setDataMan(securityUser.getUserId());
        eCkInVo.geteCkIn().setDataDate(new Date());
        eCkInVo.geteCkIn().setDataCorp(securityUser.getCorpId());
        eCkInVo.geteCkIn().setInAct(parameterId);
        //业务逻辑判断start
        //新增
        for(int i=0;i<eCkInVo.getAddedList().size();i++) {
            ECkIn model=eCkInVo.getAddedList().get(i);
            model.setInNote(eCkInVo.geteCkIn().getInNote());
            model.setInRq(eCkInVo.geteCkIn().getInRq());
            model.setInAct(eCkInVo.geteCkIn().getInAct());
            model.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
            model.setInFlag(eCkInVo.geteCkIn().getInFlag());
            model.setDataMan(securityUser.getUserId());
            model.setDataDate(eCkInVo.geteCkIn().getDataDate());
            model.setDataCorp(eCkInVo.geteCkIn().getDataCorp());
            model.setInCode(model.getInKfcode());
            model.setInBz(eCkInVo.geteCkIn().getInBz());
            model.setInTax(eCkInVo.geteCkIn().getInTax());
            //数量，重量，件数*paramvalue
            model.setInJs(model.getInJs().multiply(sysParams.getParamValue()));
            model.setInSl(model.getInSl().multiply(sysParams.getParamValue()));
            model.setInZl(model.getInZl().multiply(sysParams.getParamValue()));
            addECkIns.add(model);
        }
        //修改
        if (!eCkInVo.getModifyedList().isEmpty()) {
            for(int i=0;i<eCkInVo.getModifyedList().size();i++) {
                ECkIn item=eCkInVo.getModifyedList().get(i);
                item.setInNote(eCkInVo.geteCkIn().getInNote());
                item.setInRq(eCkInVo.geteCkIn().getInRq());
                item.setInAct(eCkInVo.geteCkIn().getInAct());
                item.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
                item.setInFlag(eCkInVo.geteCkIn().getInFlag());
                item.setDataMan(securityUser.getUserId());
                item.setDataDate(eCkInVo.geteCkIn().getDataDate());
                item.setDataCorp(eCkInVo.geteCkIn().getDataCorp());
                item.setInCode(item.getInKfcode());
                item.setInBz(eCkInVo.geteCkIn().getInBz());
                item.setInTax(eCkInVo.geteCkIn().getInTax());
                item.setInJs(item.getInJs().multiply(sysParams.getParamValue()));
                item.setInSl(item.getInSl().multiply(sysParams.getParamValue()));
                item.setInZl(item.getInZl().multiply(sysParams.getParamValue()));
                modifyedECkIns.add(item);
            }
        }
        //业务逻辑判断end
        //开始保存到数据库
        if (!addECkIns.isEmpty()) {
            addECkIns.forEach(item -> {
                eCkInDao.save(item);
            });
        }
        if (!modifyedECkIns.isEmpty()) {
            modifyedECkIns.forEach(item -> {
                eCkInDao.dynamicSave(item, eCkInDao.findById(new ECkIn.ECkIn_PK(eCkInVo.geteCkIn().getInNote(), item.getInPack())).orElse(null));
            });
        }
        if (!eCkInVo.getDeletedList().isEmpty()) {
            eCkInVo.getDeletedList().forEach( item ->{
                ECkIn.ECkIn_PK pk = new ECkIn.ECkIn_PK(eCkInVo.geteCkIn().getInNote(),item.getInPack() );
                eCkInDao.deleteById(pk);
            });
        }
        //end
        //修改表头
        String sqlString ="update ECkIn set inLib=?1,inBz=?2,inTax=?3 where inNote=?4 ";
        eCkInDao.executeUpdate(sqlString, eCkInVo.geteCkIn().getInLib(),eCkInVo.geteCkIn().getInBz(),eCkInVo.geteCkIn().getInTax(),eCkInVo.geteCkIn().getInNote());
        //end
        return Result.resultOkMsg("修改成功");
    }

    @Override
    public ECkIn findByInNote(String inNote) {
        ECkIn in=new ECkIn();
        String sqlString=" select *,(f_getname('GETDWEXP',in_customer,'',data_corp)) incustomername from E_Ck_In where in_Note ='"+inNote+"' ";
        BasePage<ECkIn> list=eCkInDao.QueryNoPageLists(sqlString);
        if(list.getContent().size()>0) {
            in=list.getContent().get(0);
        }
        return  in;
    }

    @Override
    public void deleteById(String inNote) {
        eCkInDao.deleteByInNote(inNote);
    }

    @Override
    public BasePage<Map<String, Object>> getReturnList(BaseDto baseDto) throws IOException {
        Map<String, Object> rmap = new HashMap<String, Object>();
        SessionUser securityUser = SessionUser.SessionUser();
        Map<String, String> map = baseDto.getParamsMap();
        String sqlWhere =SqlJoint.whereSuffixAnd(c ->{
            c.ge("in_rq",map.get("srchRq1"));
            c.le("in_rq",map.get("srchRq2"));
            c.eq("in_note",map.get("inNote"));
            c.eq("in_customer",map.get("inCustomer"));
            c.eq("in_lib",map.get("inLib"));
            c.eq("in_type",map.get("inType"));
            c.eq("i.data_corp",securityUser.getCorpId());
        });


        if(map.get("inType") != null && map.get("inType").trim().length() > 0) {
            String parameterId = eCkInDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
                    , WarehouseActionCaliber.llrk,map.get("inType"),securityUser.getCorpId());
            sqlWhere +=" and in_act='"+parameterId+"' ";
        }
        String thact=eCkInDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
                , WarehouseActionCaliber.llth,map.get("inType"),securityUser.getCorpId());

        String sql=" select t.*, (t.in_sl-t.thsl) sl,((t.in_sl-t.thsl)*t.in_price) in_je from ( "
                + " select i.in_note||'_'||i.in_contract||'_'||in_kfcode id,to_char(i.in_rq, 'yyyy-mm-dd') in_rq,i.in_note  ,i.in_customer  ,i.in_code  ,i.in_js    ,in_sl,i.in_zl  ,i.in_pack  ,i.in_bpack  ,i.in_price  , "
                + " i.in_contract ,i.in_kfcontract ,i.in_kfcode ,i.in_lib ,i.in_checkflag  ,i.in_month ,i.in_type ,f_get_param_name('产品大类',i.in_type,'"+   SessionUser.getCurrentCorpId()   +"') in_type_name,i.in_luono  ,i.in_vnote  ,i.data_corp , "
                + " f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name_name,cpcode_name ,"
                + " f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name, cpcode_size,	"
                + " f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,cpcode_size_en ,"
                + " f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl_name,cpcode_fl ,"
                + " f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz_name,cpcode_bz,"
                + " f_getparamname('GETCPCODEXP',cpcode_xp,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name,cpcode_xp,"
                + " b.cpcode_check ,b.cpcode01,b.cpcode02,b.cpcode03,  "
                + " (select abs(COALESCE(sum(in_sl),0)) in_sl from e_ck_in ckin where ckin.in_contract=i.in_contract and ckin.in_code=i.in_code and  ckin.in_act in('"+thact+"')) thsl ," +
                " in_contract_detailed " +
                "  from e_ck_in i ,e_js_cpcode b  where i.in_kfcode=b.cpcode_id  and i.in_flag in ('库审','结算')   "+sqlWhere+")t  where (t.in_sl-t.thsl)>0 ";
        return  eCkInDao.QueryToMapPage(baseDto, sql);
    }

    @Override
    public Result getFlag(String inNote, String flag) {
        String flagString=eCkInDao.getFlag(inNote);
        if(flag.equals(flagString)) {
            return  Result.resultOk("操作成功！");
        }
        return  Result.error("该单不是“"+flag+"”状态,不能操作！");
    }

    @Override
    public Result setFlag(String inNote, String flag) {
        eCkInDao.setFlag(inNote,flag);
        return  Result.resultOk("操作成功！");
    }}
