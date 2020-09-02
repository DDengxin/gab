package com.tengzhi.business.sale.processProduct.incomingMaterialWarehouse.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.base.common.WarehouseActionCaliber;
import com.tengzhi.business.cg.yw.purchaseReceipt.dao.ECkInDao;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseSettle.dao.ECwYsyfDao;
import com.tengzhi.business.mesGz.gzck.vo.ECkInVo;
import com.tengzhi.business.sale.processProduct.incomingMaterialWarehouse.service.IncomingMaterialWarehouseService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IncomingMaterialWarehouseServiceImpl implements IncomingMaterialWarehouseService {

    @Autowired
    private ECkInDao eCkInDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Autowired
    private SysParamService sysParamService;

    @Autowired
    private ECwYsyfDao eCwYsyfDao;

    @Override
    public BasePage<Map<String,Object>> getSrchTopList(BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        SessionUser securityUser=SessionUser.SessionUser();
        String sqlWhere = SqlJoint.whereSuffixWhere((c) ->{
            c.ge("in_rq",map.get("srchRq1"));
            c.le("in_rq",map.get("srchRq2"));
            c.eq("in_customer", map.get("inCustomer"));
            c.eq("in_note",map.get("inNote"));
            c.eq("in_flag", map.get("inFlag"));
            c.eq("data_corp",securityUser.getCorpId());

        });
        sqlWhere += " and in_act in(select param_id from sys_param where param_name='"+ WarehouseActionCaliber.llrk+"')";
        String sql = " select to_char(in_rq,'yyyy-mm-dd')in_rq,in_note,in_type,f_get_param_name('产品大类',in_type,'"+   SessionUser.getCurrentCorpId()   +"') in_type_name," +
                "in_act,f_get_param_name('仓库收发',in_act,'"+   SessionUser.getCurrentCorpId()   +"') in_act_name,in_customer,f_getname('GETDWEXP',in_customer,'',data_corp) in_customer_name," +
                "in_lib,f_get_param_name('库房档案',in_lib,'"+   SessionUser.getCurrentCorpId()   +"') in_lib_name,sum(in_js)in_js,sum(in_sl)in_sl ,in_flag,data_man,f_getname('GETUSERNAME',data_man,'',data_corp) data_man_name   from e_ck_in   "+sqlWhere+" " +
                "group by data_corp,in_rq,in_note,in_type,in_act,in_customer,in_lib,in_flag,data_man ";
        return eCkInDao.QueryToMapPage(baseDto,sql);
    }

    @Override
    public BasePage<Map<String,Object>> getSrchBottomList(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        SessionUser securityUser=SessionUser.SessionUser();
        String sqlWhere = SqlJoint.whereSuffixAnd((c) ->{
            c.eq("in_note",map.get("inNote"));
            c.eq("a.data_corp",securityUser.getCorpId());
        });
        String sql = " select in_contract||'_'||in_Kfcode as id,in_code,in_js ,in_sl,coalesce((in_sl*in_price),0) inje ,in_zl ,in_pack ,in_bpack ,coalesce(in_price,0) in_price,in_contract ,in_kfcontract ,in_Kfcode, in_checkflag ,"
                + " in_month,in_type ,in_luono ,in_vnote,in_hs," +
                " f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',a.in_type,'"+   SessionUser.getCurrentCorpId()   +"')   cpcode_name_name,cpcode_name, " +
                " f_getparamname('GETCPCODESIZE',b.cpcode_size,'','技术',a.in_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name,cpcode_size ," +
                " f_getparamname('GETCPCODESIZEEN',b.cpcode_size_en,'','技术',a.in_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name, cpcode_size_en," +
                " f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',a.in_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl_name,cpcode_fl, " +
                " f_getparamname('GETCPCODEXP',b.cpcode_xp,'','技术',a.in_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name,cpcode_xp, " +
                " f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz_name, cpcode_bz," +
                " b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03, " +
                " f_get_param_name('产品大类',in_type,'"+   SessionUser.getCurrentCorpId()   +"') in_type_name,f_getsl('GETKSHSL',in_contract,in_code,in_note,a.data_corp) rksl,in_ph,a.in_remarks "
                + "  from  e_ck_in a,e_js_cpcode b where a.in_code=b.cpcode_id  "+sqlWhere;
        return eCkInDao.QueryToMapPage(baseDto,sql);
    }

    @Override
    public Result save(ECkInVo eCkInVo) throws Exception {
        List<ECkIn> addECkIns=new ArrayList<ECkIn>();
        List<ECkIn> modifyedECkIns=new ArrayList<ECkIn>();
        String inType = eCkInVo.geteCkIn().getInType();
        BigDecimal initBigDecimal=new BigDecimal("0");
        SessionUser securityUser = SessionUser.SessionUser();
        String parameterId = eCkInDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
                , WarehouseActionCaliber.llrk,inType,securityUser.getCorpId());
        SysParams sysParams = sysParamService.findByParameterId(parameterId, "仓库收发");
        String note = sysGenNoteService.getInOutNote(ECkIn.class,inType, WarehouseActionCaliber.wwrk,securityUser.getCorpId());
        eCkInVo.geteCkIn().setInNote(note);
        eCkInVo.geteCkIn().setInFlag("登记");
        eCkInVo.geteCkIn().setDataMan(securityUser.getUserId());
        eCkInVo.geteCkIn().setDataDate(new Date());
        eCkInVo.geteCkIn().setDataCorp(securityUser.getCorpId());
        eCkInVo.geteCkIn().setInAct(parameterId);
        //业务逻辑判断start
        //新增
        for(int i=0;i<eCkInVo.geteCkInMx().size();i++) {
            ECkIn model=eCkInVo.geteCkInMx().get(i);
            model.setInPrice(eCkInVo.geteCkIn().getInPrice());
            model.setInBz(eCkInVo.geteCkIn().getInBz());
            model.setInTax(eCkInVo.geteCkIn().getInTax());
            model.setInHs(eCkInVo.geteCkIn().getInHs());
            model.setInNote(note);
            model.setInRq(eCkInVo.geteCkIn().getInRq());
            model.setInType(eCkInVo.geteCkIn().getInType());
            model.setInContract(eCkInVo.geteCkIn().getInContract());
            model.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
            model.setInLib(eCkInVo.geteCkIn().getInLib());
            model.setInFlag(eCkInVo.geteCkIn().getInFlag());
            model.setDataMan(eCkInVo.geteCkIn().getDataMan());
            model.setDataDate(eCkInVo.geteCkIn().getDataDate());
            model.setDataCorp(eCkInVo.geteCkIn().getDataCorp());
            model.setInAct(eCkInVo.geteCkIn().getInAct());
            model.setInKfcode(model.getInCode());
            //数量，重量，件数*paramvalue
            model.setInJs(model.getInJs()== null ? initBigDecimal :model.getInJs().multiply(sysParams.getParamValue()));
            model.setInSl(model.getInSl()== null ? initBigDecimal :model.getInSl().multiply(sysParams.getParamValue()));
            model.setInZl(model.getInZl() == null ? initBigDecimal :model.getInZl().multiply(sysParams.getParamValue()));
            //如果为空自动生成包装号
            if (model.getInPack() == null) {
                try {
                    String pack = sysGenNoteService.getyyyyMMddNote4(ECkIn.class, "P");
                    model.setInPack(pack);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {

                //判断数据库有没有该包装号
                int count=eCkInDao.getAddInBack(model.getInPack());
                if(count==1) {
                    return 	Result.error("包装号：“"+model.getInPack()+"”在数据库中已存在，保存失败！");
                }
            }
            //默认大包装号和包装号相等，后续可能需要根据产品规则生成
            model.setInBpack(model.getInPack());
            eCkInDao.save(model);
        }
        //end
        return Result.resultOk(eCkInVo.geteCkIn());
    }

    @Override
    public Result update(ECkInVo eCkInVo) throws Exception {
        String flag = eCkInDao.getFlag(eCkInVo.geteCkIn().getInNote());
        if(!"登记".equals(flag)) {
            return Result.error("【"+flag+"】状态不能进行修改操作！");
        }
        BigDecimal initBigDecimal=new BigDecimal("0");
        List<ECkIn> addECkIns=new ArrayList<ECkIn>();
        List<ECkIn> modifyedECkIns=new ArrayList<ECkIn>();
        String inType = eCkInVo.geteCkIn().getInType();
        SessionUser securityUser = SessionUser.SessionUser();
        String parameterId = eCkInDao.getSingleResult("select f_get_in_out_act('GETACT',?1,?2,?3) "
                , WarehouseActionCaliber.llrk,inType,securityUser.getCorpId());
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
            model.setInPrice(eCkInVo.geteCkIn().getInPrice());
            model.setInBz(eCkInVo.geteCkIn().getInBz());
            model.setInTax(eCkInVo.geteCkIn().getInTax());
            model.setInHs(eCkInVo.geteCkIn().getInHs());
            model.setInNote(eCkInVo.geteCkIn().getInNote());
            model.setInRq(eCkInVo.geteCkIn().getInRq());
            model.setInType(eCkInVo.geteCkIn().getInType());
            model.setInContract(eCkInVo.geteCkIn().getInContract());
            model.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
            model.setInLib(eCkInVo.geteCkIn().getInLib());
            model.setInFlag(eCkInVo.geteCkIn().getInFlag());
            model.setDataMan(eCkInVo.geteCkIn().getDataMan());
            model.setDataDate(eCkInVo.geteCkIn().getDataDate());
            model.setDataCorp(eCkInVo.geteCkIn().getDataCorp());
            model.setInAct(eCkInVo.geteCkIn().getInAct());
            model.setInKfcode(model.getInCode());
            //数量，重量，件数*paramvalue
            model.setInJs(model.getInJs()== null ? initBigDecimal :model.getInJs().multiply(sysParams.getParamValue()));
            model.setInSl(model.getInSl()== null ? initBigDecimal :model.getInSl().multiply(sysParams.getParamValue()));
            model.setInZl(model.getInZl() == null ? initBigDecimal :model.getInZl().multiply(sysParams.getParamValue()));
            //如果为空自动生成包装号
            if (model.getInPack() == null) {
                try {
                    String pack = sysGenNoteService.getyyyyMMddNote4(ECkIn.class, "P");
                    model.setInPack(pack);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                //判断数据库有没有该包装号
                int count=eCkInDao.getAddInBack(model.getInPack());
                if(count==1) {
                    return 	Result.error("包装号：“"+model.getInPack()+"”在数据库中已存在，保存失败！");
                }
            }
            //默认大包装号和包装号相等，后续可能需要根据产品规则生成
            model.setInBpack(model.getInPack());
            addECkIns.add(model);
        }
        //修改
        if (!eCkInVo.getModifyedList().isEmpty()) {
            for(int i=0;i<eCkInVo.getModifyedList().size();i++) {
                ECkIn model=eCkInVo.getModifyedList().get(i);
                model.setInPrice(eCkInVo.geteCkIn().getInPrice());
                model.setInBz(eCkInVo.geteCkIn().getInBz());
                model.setInTax(eCkInVo.geteCkIn().getInTax());
                model.setInHs(eCkInVo.geteCkIn().getInHs());
                model.setInNote(eCkInVo.geteCkIn().getInNote());
                model.setInRq(eCkInVo.geteCkIn().getInRq());
                model.setInType(eCkInVo.geteCkIn().getInType());
                model.setInContract(eCkInVo.geteCkIn().getInContract());
                model.setInCustomer(eCkInVo.geteCkIn().getInCustomer());
                model.setInLib(eCkInVo.geteCkIn().getInLib());
                model.setInFlag(eCkInVo.geteCkIn().getInFlag());
                model.setDataMan(eCkInVo.geteCkIn().getDataMan());
                model.setDataDate(eCkInVo.geteCkIn().getDataDate());
                model.setDataCorp(eCkInVo.geteCkIn().getDataCorp());
                model.setInAct(eCkInVo.geteCkIn().getInAct());
                model.setInKfcode(model.getInCode());
                //数量，重量，件数*paramvalue
                model.setInJs(model.getInJs()== null ? initBigDecimal :model.getInJs().multiply(sysParams.getParamValue()));
                model.setInSl(model.getInSl()== null ? initBigDecimal :model.getInSl().multiply(sysParams.getParamValue()));
                model.setInZl(model.getInZl() == null ? initBigDecimal :model.getInZl().multiply(sysParams.getParamValue()));

                //判断数据库有没有该包装号
                ECkIn oldCkIn=eCkInDao.findById(new ECkIn.ECkIn_PK(eCkInVo.geteCkIn().getInNote(), model.getInPack())).orElse(null);
                if(!oldCkIn.getInPack().equals(model.getInPack())) {
                    int count=eCkInDao.getAddInBack(model.getInPack());
                    if(count==1) {
                        return 	Result.error("包装号：“"+model.getInPack()+"”在数据库中已存在，保存失败！");
                    }
                }

                modifyedECkIns.add(model);
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
        //修改表头
        String sqlString ="update ECkIn set inLib=?1,inBz=?2,inTax=?3 where inNote=?4 ";
        eCkInDao.executeUpdate(sqlString, eCkInVo.geteCkIn().getInLib(),eCkInVo.geteCkIn().getInBz(),eCkInVo.geteCkIn().getInTax(),eCkInVo.geteCkIn().getInNote());
        //end
        return Result.resultOkMsg("修改成功");
    }

    @Override
    public ECkIn findById(String inNote, String inPack) {
        return null;
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
    }

    @Override
    public Result updateSplitData(BigDecimal sl, String pack, String contract) {
        return null;
    }

    @Override
    public BasePage<Map<String, Object>> getjjhtList(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        SessionUser securityUser=SessionUser.SessionUser();
        String sqlWhere = SqlJoint.whereSuffixAnd((c) ->{
            c.eq("ht_no",map.get("htNo"));
            System.out.println(c);
        });
        String  sql = "select 'add' \"_state\",wl_code in_code ,wl_sl in_sl,  f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')   cpcode_name_name,cpcode_name, " +
                               " f_getparamname('GETCPCODESIZE',b.cpcode_size,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name,cpcode_size ," +
                                " f_getparamname('GETCPCODESIZEEN',b.cpcode_size_en,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name, cpcode_size_en," +
                                " f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl_name,cpcode_fl, " +
                                " f_getparamname('GETCPCODEXP',b.cpcode_xp,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name,cpcode_xp, " +
                                " f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz_name, cpcode_bz  from  e_xs_contract_detailed_wl ,e_js_cpcode b where cpcode_id = wl_code "+sqlWhere;
        return eCkInDao.QueryToMapPage(baseDto,sql);
    }

    @Override
    public BasePage<Map<String, Object>> getCgWwhtSelectList(BaseDto baseDto) throws IOException {
        return null;
    }

    @Override
    public ModelAndView table(ModelAndView mv, String inNote) {
        return null;
    }

    @Override
    public BasePage<Map<String, Object>> getProductList(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        SessionUser securityUser=SessionUser.SessionUser();
        String sqlWhere =SqlJoint.whereSuffixAnd((c)->{
           c.eq("ht_customer",map.get("SrchHtCustomer"));
           c.like("ht_no",map.get("srchHtNo"));
           c.eq("cpcode_type","inType");

        });
        String sql="SELECT " +
                "f_getparamname('GETBCPCODENAME',c.cpcode_name,'','技术',c.cpcode_type,'"+SessionUser.getCurrentCorpId()+"')   cpcode_name_name," +
                "f_getparamname('GETCPCODESIZE',c.cpcode_size,'','技术',c.cpcode_type,'"+SessionUser.getCurrentCorpId()+"') cpcode_size_name," +
                "f_getparamname('GETCPCODESIZEEN',c.cpcode_size_en,'','技术',c.cpcode_type,'"+SessionUser.getCurrentCorpId()+"') cpcode_size_en_name," +
                "f_getparamname('GETBYCPCODEFL',c.cpcode_fl,'','技术',c.cpcode_type,'"+SessionUser.getCurrentCorpId()+"') cpcode_fl_name," +
                "f_getparamname('GETCPCODEXP',c.cpcode_xp,'','技术',c.cpcode_type,'"+   SessionUser.getCurrentCorpId()+"')  cpcode_xp_name," +
                "f_getname('GETDWEXP',A.ht_customer,'','"+SessionUser.getCurrentCorpId()+"')ht_customer_name," +
                "A.ht_no,A.ht_type,A.ht_customer,A.ht_item_type,A.ht_date,A.data_man,A.ht_flag,b.wl_code,b.wl_type,b.wl_ph,b.wl_js,b.wl_sl,b.wl_zl,b.wl_sh,C.cpcode_id,C.cpcode_name,C.cpcode_size,C.cpcode_size_en,C.cpcode_fl,C.cpcode_bz,C.cpcode_xp FROM e_xs_contract A,e_xs_contract_detailed_wl b,e_js_cpcode C WHERE A.ht_no=b.ht_no AND b.wl_code=C.cpcode_id AND A.ht_type='JG'  AND C.cpcode_flag='Y' "+sqlWhere;
        return eCkInDao.QueryToMapPage(baseDto,sql);

    }
}
