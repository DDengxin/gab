package com.tengzhi.business.xt.logistics.purchase.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseRequisition.model.EcgRequisition;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.service.SysParamService;
import com.tengzhi.business.xt.logistics.purchase.dao.PurchaseDao;
import com.tengzhi.business.xt.logistics.purchase.model.Purchase;
import com.tengzhi.business.xt.logistics.purchase.service.PurchaseService;
import com.tengzhi.business.xt.logistics.purchase.vo.PurchaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class PurchaseImpl implements PurchaseService {
    @Autowired
    private PurchaseDao PurchaseDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Autowired
    private SysParamService sysParamService;

    @Override
    public BasePage<Map<String, Object>>  getSrchTopList(BaseDto dto) {
        Map<String, String> map = dto.getParamsMap();
        String where = SqlJoint.whereSuffixAnd((c) -> {
            c.ge("rq", map.get("srchRq1"));
            c.le ("rq", map.get("srchRq2"));
            c.contains("name", map.get("name"));
            c.eq("lb",map.get("srchlb"));
            c.eq("flag",map.get("srchflag"));
        });
        String sql = "select note,sum(je)je,lb,ny,man,flag,data_corp,to_char(rq,'yyyy-mm-dd')rq from  e_xt_purchase where 1=1 "+where+" group by rq,note,lb,ny,man,flag,data_corp  ";
        return PurchaseDao.QueryMapPageList(dto, sql, true);
    }

    @Override
    public BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        Object note = map.get("note");
        String sql = "  select note,mo,name,sl,unit,price,je,sm from e_xt_purchase where  note='" + note + "' ";
        return  PurchaseDao.QueryMapPageList(baseDto, sql, true);
    }


    @Override
    public Result save(PurchaseVo PurchaseVo)throws Exception {
        List<Purchase> addModel=new ArrayList<Purchase>();
        List<Purchase> modifyedModel=new ArrayList<Purchase>();
        List<Purchase> deteleModel=new ArrayList<Purchase>();
        String note=sysGenNoteService.getyyMMNote4(EcgRequisition.class,"STCG");
        SessionUser securityUser=SessionUser.SessionUser();
        PurchaseVo.getHeaddata().setNote(note);
        PurchaseVo.getHeaddata().setFlag("登记");
        PurchaseVo.getHeaddata().setMan(securityUser.getUserId());
        PurchaseVo.getHeaddata().setData_corp(securityUser.getCorpId());
        if(PurchaseVo.getEntitydata().size()>0) {
            PurchaseVo.getEntitydata().forEach( item -> {
                if("added".equals(item.get_state())) {
                item.setNote(PurchaseVo.getHeaddata().getNote());
                item.setRq(PurchaseVo.getHeaddata().getRq());
                item.setLb(PurchaseVo.getHeaddata().getLb());
                item.setMan(PurchaseVo.getHeaddata().getMan());
                item.setFlag(PurchaseVo.getHeaddata().getFlag());
                item.setData_corp(PurchaseVo.getHeaddata().getData_corp());
                item.setMo(note+'-'+item.getSid());
                addModel.add(item);
            }else if("modified".equals(item.get_state())) {
                    modifyedModel.add(item);
                }else if("removed".equals(item.get_state())) {
                    deteleModel.add(item);
                }

            });
        }

        //保存到数据库
        if(addModel.size()>0) {
            addModel.forEach( model -> {
                PurchaseDao.save(model);
            });
        }
        if(modifyedModel.size()>0) {
            modifyedModel.forEach( model -> {
                PurchaseDao.dynamicSave(model,PurchaseDao.findById(model.getMo()).orElse(null));
            });
        }
        if(deteleModel.size()>0) {
            deteleModel.forEach( model -> {
                PurchaseDao.deleteById(model.getMo());
            });
        }
        return Result.resultOk(note);
    }
    @Override
    public Result update(PurchaseVo PurchaseVo)throws Exception {
        List<Purchase> addModel=new ArrayList<Purchase>();
        List<Purchase> modifyedModel=new ArrayList<Purchase>();
        List<Purchase> deteleModel=new ArrayList<Purchase>();
        String note=PurchaseVo.getHeaddata().getNote();
        SessionUser securityUser=SessionUser.SessionUser();
        PurchaseVo.getHeaddata().setNote(note);
        PurchaseVo.getHeaddata().setFlag("登记");
        PurchaseVo.getHeaddata().setMan(securityUser.getUserId());
        PurchaseVo.getHeaddata().setData_corp(securityUser.getCorpId());
        if(PurchaseVo.getEntitydata().size()>0) {
            PurchaseVo.getEntitydata().forEach( item -> {
                if("added".equals(item.get_state())) {
                    item.setNote(note);
                    item.setRq(PurchaseVo.getHeaddata().getRq());
                    item.setMan(PurchaseVo.getHeaddata().getMan());
                    item.setFlag(PurchaseVo.getHeaddata().getFlag());
                    item.setLb(PurchaseVo.getHeaddata().getLb());
                    item.setData_corp(PurchaseVo.getHeaddata().getData_corp());
                    item.setMo(note+'-'+item.getSid());
                }else if("modified".equals(item.get_state())) {
                    item.setNote(note);
                    modifyedModel.add(item);
                }else if("removed".equals(item.get_state())) {
                    item.setNote(note);
                    deteleModel.add(item);
                }

            });
        }

        //保存到数据库
        if(addModel.size()>0) {
            addModel.forEach( model -> {
                PurchaseDao.save(model);
            });
        }
        if(modifyedModel.size()>0) {
            modifyedModel.forEach( model -> {
                PurchaseDao.dynamicSave(model,PurchaseDao.findById(model.getMo()).orElse(null));
            });
        }
        if(deteleModel.size()>0) {
            deteleModel.forEach( model -> {
                PurchaseDao.deleteById(model.getMo());
            });
        }

        //修改表头
        PurchaseDao.executeUpdate("update Purchase set rq= ?1,ny= ?2,lb= ?3 where note= ?4 ", PurchaseVo.getHeaddata().getRq(),PurchaseVo.getHeaddata().getNy(),PurchaseVo.getHeaddata().getLb(),PurchaseVo.getHeaddata().getNote());

        //end
        return Result.resultOk(note);
    }


    @Override
    public Purchase findBySqnote(String note) {
        return PurchaseDao.findBySqnote(note);

    }


    @Override
    public void deleteById(String Note){
        PurchaseDao.deleteBysqNote(Note);
    }


    @Override
    public Result getFlag(String Note, String flag) {
        String flagString=PurchaseDao.getFlag(Note);
        if(flag.equals(flagString)) {
            return  Result.resultOk("操作成功！");
        }
        return  Result.error("该单不是“"+flag+"”状态,不能操作！");
    }

    @Override
    public Result setFlag(String note, String flag) {
        PurchaseDao.setFlag(note,flag);
        return  Result.resultOk("操作成功！");
    }
}
