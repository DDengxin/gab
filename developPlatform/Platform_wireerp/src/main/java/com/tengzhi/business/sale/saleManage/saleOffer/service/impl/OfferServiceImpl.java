package com.tengzhi.business.sale.saleManage.saleOffer.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.sale.saleManage.saleOffer.dao.EXsOfferDao;
import com.tengzhi.business.sale.saleManage.saleOffer.dao.EXsOfferDetailDao;
import com.tengzhi.business.sale.saleManage.saleOffer.dao.EXsOfferHistoryDao;
import com.tengzhi.business.sale.saleManage.saleOffer.model.EXsOffer;
import com.tengzhi.business.sale.saleManage.saleOffer.service.OfferService;
import com.tengzhi.business.sale.saleManage.saleOffer.vo.EXsOfferVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {

    @Autowired
    private EXsOfferDao offerDao;

    @Autowired
    private EXsOfferDetailDao offerDetailDao;
    @Autowired
    private EXsOfferHistoryDao offerHistoryDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Override
    public BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(c->{
            c.ge("rq",map.get("srchRq1"));
            c.le("rq",map.get("srchRq2"));
            c.eq("note",map.get("srchNote"));
            c.eq("sale_master",map.get("srchMaster"));
            c.eq("sale_man",map.get("srchMan"));
            c.eq("sale_flag",map.get("srchFlag"));
        });
        String sql = "select a.note,to_char(a.rq,'yyyy-MM-dd')rq,a.sale_master,a.sale_man,a.sale_flag,a.data_man,a.data_corp,sum(b.amount)amount,sum(b.money)money,a.last_note,a.sale_stype," +
                "f_get_param_name('业务员',a.sale_man,'"+   SessionUser.getCurrentCorpId()   +"','')sale_man_name,f_getname('GETUSERNAME',a.data_man,'',a.data_corp)data_man_name," +
                "f_getname('GETDWEXP',a.sale_master,'','"+SessionUser.getCurrentCorpId()+"')sale_master_name " +
                " from e_xs_offer a,e_xs_offer_detail b where a.note =b.item group by a.note,a.rq,a.sale_master,a.sale_man,a.sale_flag,a.data_man,a.data_corp,a.last_note";
        return offerDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public EXsOffer save(EXsOfferVo eXsOfferVo) throws Exception {
        SessionUser sessionUser = SessionUser.SessionUser();
        String note = sysGenNoteService.getNote(EXsOffer.class,"BJ","yyMM",4);
        eXsOfferVo.geteXsOffer().setNote(note);
        eXsOfferVo.geteXsOffer().setThisNote(note);
        eXsOfferVo.geteXsOffer().setDataCorp(sessionUser.getCorpId());
        eXsOfferVo.geteXsOffer().setDataMan(sessionUser.getUserId());
        eXsOfferVo.geteXsOffer().setSaleFlag("登记");
        if(!eXsOfferVo.getAddedList().isEmpty()){
            eXsOfferVo.getAddedList().forEach(detail->{
                detail.setItem(note);
                String itemMo = offerDetailDao.getSingleResult("select max(item_mo) from e_xs_offer_detail where item='"+note+"'");
                int num = (itemMo==""||itemMo==null?0: Integer.parseInt(itemMo.substring(itemMo.lastIndexOf("-"))))+1;
                detail.setItemMo(note+"-"+num);
                offerDetailDao.save(detail);
            });
        }
        if(!eXsOfferVo.getDeletedList().isEmpty()){
            offerDetailDao.deleteAll(eXsOfferVo.getDeletedList());
        }
        if(!eXsOfferVo.getModifyedList().isEmpty()){
            eXsOfferVo.getModifyedList().forEach(detail->{
                offerDetailDao.dynamicSave(detail,offerDetailDao.findById(detail.getItemMo()).orElse(null));
            });
        }
        EXsOffer offer = offerDao.save(eXsOfferVo.geteXsOffer());
        offerDao.executeUpdateSql("update e_xs_offer set sale_flag='报废',last_note='"+note+"' where note = '"+note+"'");
        offerDao.executeUpdateSql("update e_xs_offer set sale_flag='报废',last_note='"+note+"' where this_note = '"+note+"'");
        return offer;
    }

    @Override
    public void update(EXsOfferVo eXsOfferVo) {
        SessionUser sessionUser = SessionUser.SessionUser();
        eXsOfferVo.geteXsOffer().setDataCorp(sessionUser.getCorpId());
        eXsOfferVo.geteXsOffer().setDataMan(sessionUser.getUserId());
        if(!eXsOfferVo.getAddedList().isEmpty()){
            eXsOfferVo.getAddedList().forEach(detail->{
                String itemMo = offerDetailDao.getSingleResult("select max(item_mo) from e_xs_offer_detail where item='"+detail.getItem()+"'");
                int num = (itemMo==""||itemMo==null?0: Integer.parseInt(itemMo.substring(itemMo.lastIndexOf("-"))))+1;
                detail.setItemMo(detail.getItem()+"-"+num);
                offerDetailDao.save(detail);
            });
        }
        if(!eXsOfferVo.getDeletedList().isEmpty()){
            offerDetailDao.deleteAll(eXsOfferVo.getDeletedList());
        }
        if(!eXsOfferVo.getModifyedList().isEmpty()){
            eXsOfferVo.getModifyedList().forEach(detail->{
                offerDetailDao.dynamicSave(detail,offerDetailDao.findById(detail.getItemMo()).orElse(null));
            });
        }
        offerDao.dynamicSave(eXsOfferVo.geteXsOffer(),offerDao.findById(eXsOfferVo.geteXsOffer().getNote()).orElse(null));
    }

    @Override
    public Map<String, Object> findByNote(String note) {
        return offerDao.QueryToFirstMap("select *,f_getname('GETDWEXP',sale_master,'','"+SessionUser.getCurrentCorpId()+"')sale_master_name from e_xs_offer where note='"+note+"'");
    }

    @Override
    public void deleteByNte(String note) {
        offerDao.daleteByNote(note);
        offerDetailDao.daleteByNote(note);
    }

    @Override
    public BasePage<Map<String, Object>> getOfferDetailList(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = "where item= '"+map.get("note")+"'";
        String sql = "select item,item_mo,code,type,amount,price,money,to_char(delivery_time,'yyyy-MM-dd')delivery_time,origin_place,process_status,is_pickling," +
                "material_price,material_freight,process_freight,sale_freight,other_cost,gross_margin,remarks,master_id,shop,pack,sur,tccd,bccd,tqd,bqd,tbz,bbz," +
                "cysm,ydbz,ydyq,bzbq,lhlb,zdlh,cfyq,sxfx,sxqj,jyxm,jyyq,cpyt,scbz,cybz,yjbz,yjje,ylcz,ylgg,length,cd,cz,gg,sale_difference_price,yzgg,yzsl,yzprice from e_xs_offer_detail a "+where;
        return offerDetailDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public Result getFlag(String note, String flag) {
        String flagString = offerDao.getFlag(note);
        if(flag.equals(flagString)){
            return Result.resultOk("操作成功");
        }
        return Result.error("该单不是“" + flag + "”状态,不能操作！");
    }

    @Override
    public Result confirm(String note) {
        offerDao.updateFlag(note,"确认");
        //确认过的数据保存到历史记录中
        //同一个客户的同一种产品只保留最新的报价
        offerHistoryDao.executeUpdateSql("delete from e_xs_offer_history where code in (select code from e_xs_offer_detail where item='"+note+"') " +
                "and master_id=(select sale_master from e_xs_offer where note='"+note+"')");
        offerHistoryDao.executeUpdateSql("insert into e_xs_offer_history(item,item_mo,code,type,amount,price,money,delivery_time,origin_place,process_status,is_pickling," +
                "material_price,material_freight,process_freight,sale_freight,other_cost,gross_margin,remarks,master_id,shop,pack,sur,tccd,bccd,tqd,bqd,tbz,bbz," +
                "cysm,ydbz,ydyq,bzbq,lhlb,zdlh,cfyq,sxfx,sxqj,jyxm,jyyq,cpyt,scbz,cybz,yjbz,yjje,ylcz,ylgg,length,cd,cz,gg,sale_difference_price,yzgg,yzsl,yzprice)  " +
                "select item,item_mo,code,type,amount,price,money,delivery_time,origin_place,process_status,is_pickling,material_price,material_freight,process_freight,sale_freight,other_cost," +
                "gross_margin,remarks,master_id,shop,pack,sur,tccd,bccd,tqd,bqd,tbz,bbz,cysm,ydbz,ydyq,bzbq,lhlb,zdlh,cfyq,sxfx,sxqj,jyxm,jyyq,cpyt,scbz,cybz,yjbz,yjje,ylcz,ylgg,length,cd,cz,gg," +
                "sale_difference_price,yzgg,yzsl,yzprice from e_xs_offer_detail where item='"+note+"' ");
        return Result.resultOkMsg("确认");
    }

    @Override
    public Result cancle(String note) {
        offerDao.updateFlag(note,"登记");
        return Result.resultOkMsg("取消登记");
    }

    @Override
    public void updateDetailJs(EXsOfferVo eXsOfferVo) {
        if(!eXsOfferVo.getAddedList().isEmpty()){
            eXsOfferVo.getAddedList().forEach(detail->{
                String itemMo = offerDetailDao.getSingleResult("select max(item_mo) from e_xs_offer_detail where item='"+detail.getItem()+"'");
                int num = (itemMo==""||itemMo==null?0: Integer.parseInt(itemMo.substring(itemMo.lastIndexOf("-"))))+1;
                detail.setItemMo(detail.getItem()+"-"+num);
                offerDetailDao.save(detail);
            });
        }
        if(!eXsOfferVo.getDeletedList().isEmpty()){
            offerDetailDao.deleteAll(eXsOfferVo.getDeletedList());
        }
        if(!eXsOfferVo.getModifyedList().isEmpty()){
            eXsOfferVo.getModifyedList().forEach(detail->{
                offerDetailDao.dynamicSave(detail,offerDetailDao.findById(detail.getItemMo()).orElse(null));
            });
        }
    }


    @Override
    public BasePage<Map<String, Object>> getHistoryList(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(c->{
            c.eq("origin_place",map.get("originPlace"));
            c.eq("ylcz",map.get("ylcz"));
            c.eq("ylgg",map.get("ylgg"));
        });
        String sql = "select a.note,to_char(a.rq,'yyyy-MM-dd')rq,a.sale_master,a.sale_flag,a.data_man,a.data_corp,sum(b.amount)amount,sum(b.money)money,b.item,b.item_mo,b.code,b.type,b.price,b.delivery_time,b.origin_place," +
                "b.process_status,b.is_pickling,b.material_price,b.material_freight,b.process_freight,b.sale_freight,b.other_cost,b.gross_margin,b.remarks,b.master_id,b.shop,b.pack,b.sur,b.tccd,b.bccd,b.tqd,b.bqd,b.tbz,b.bbz," +
                "b.cysm,b.ydbz,b.ydyq,b.bzbq,b.lhlb,b.zdlh,b.cfyq,b.sxfx,b.sxqj,b.jyxm,b.jyyq,b.cpyt,b.scbz,b.cybz,b.yjbz,b.yjje,b.ylcz,b.ylgg,b.length,b.cd,b.cz,b.gg,b.sale_difference_price,b.yzgg,b.yzsl,b.yzprice " +
                " from e_xs_offer a,e_xs_offer_history b where a.note =b.item group by a.note,a.rq,a.sale_master,a.sale_man,a.sale_flag,a.data_man,a.data_corp,b.item,b.item_mo,b.code,b.type,b.price,b.delivery_time,b.origin_place," +
                "b.process_status,b.is_pickling,b.material_price,b.material_freight,b.process_freight,b.sale_freight,b.other_cost,b.gross_margin,b.remarks,b.master_id,b.shop,b.pack,b.sur,b.tccd,b.bccd,b.tqd,b.bqd,b.tbz,b.bbz," +
        "b.cysm,b.ydbz,b.ydyq,b.bzbq,b.lhlb,b.zdlh,b.cfyq,b.sxfx,b.sxqj,b.jyxm,b.jyyq,b.cpyt,b.scbz,b.cybz,b.yjbz,b.yjje,b.ylcz,b.ylgg,b.length,b.cd,b.cz,b.gg,b.sale_difference_price,b.yzgg,b.yzsl,b.yzprice ";
        return offerDao.QueryMapPageList(baseDto,sql,true);
    }



}
