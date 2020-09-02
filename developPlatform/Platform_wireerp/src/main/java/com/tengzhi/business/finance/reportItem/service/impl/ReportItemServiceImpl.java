package com.tengzhi.business.finance.reportItem.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.finance.reportItem.dao.AcctReportItemDao;
import com.tengzhi.business.finance.reportItem.dao.ReportItemDao;
import com.tengzhi.business.finance.reportItem.model.EFVoucherAcctreportitem;
import com.tengzhi.business.finance.reportItem.model.EFVoucherReportitem;
import com.tengzhi.business.finance.reportItem.service.ReportItemService;
import com.tengzhi.business.finance.reportItem.vo.ReportItemModel;
import com.tengzhi.business.finance.reportItem.vo.ReportItemVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReportItemServiceImpl implements ReportItemService {

    @Autowired
    private ReportItemDao dao;
    @Autowired
    private AcctReportItemDao arDao;


    @Override
    public BasePage<EFVoucherReportitem> getList(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(item->{
            item.eq("data_corp", SessionUser.SessionUser().getCorpId());
            item.eq("freportid",map.get("report"));
        });
        String sql = " select freportid,freportitem,fdesc,case fdc when '1' then '借' else '贷' end fdc_name,frownum,fdatasource,fformula,data_corp from e_f_voucher_reportitem "+where;
        return dao.QueryPageLists(baseDto,sql);
    }


    public List<Map> getList(){
        String sql = " select freportitem faccountid,freportitem fnumber,freportitem||''||fdesc fnumberfullname from e_f_voucher_reportitem  where data_corp='"+SessionUser.SessionUser().getCorpId()+"' ";
        return dao.QueryListMap(sql);
    }

    public List<Map>  getReportList(){
        return dao.QueryListMap(" select fid,fname from e_f_voucher_report where data_corp='"+SessionUser.SessionUser().getCorpId()+"' order by fid asc ");
    }

    public Map<String,Object> getById(String freportitem,String freportid){
        Map<String,Object> rmap = new HashMap<String,Object>();
        String fformula = dao.QueryFirstString(" select REPLACE(REPLACE(fformula,'[',''),']','') from e_f_voucher_reportitem    where freportitem='"+freportitem+"' and freportid='"+freportid+"' ");
        String type = ((fformula != null && fformula.indexOf("*") > 0)? "2":"1");
        rmap.put("type",type);

        List<Map> dataList = new ArrayList<Map>();
        if("2".equals(type)){   //报表列
            String [] arr = fformula.split("\\+|\\-");
            List<Map> dbList = dao.QueryListMap(" select freportitem,freportitem||''||fdesc fnumberfullname from e_f_voucher_reportitem where freportitem in ('"+ fformula.replace("-","','").replace("+","','") +"')  and data_corp='"+SessionUser.SessionUser().getCorpId()+"' ");
//            List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
            for(String str : arr){
                if(str == null || str.trim().length() == 0)continue;
                Map<String,String> dataMap = new HashMap<String,String>();
                dataMap.put("faccountid",str);
                dataMap.put("fnumber",str);
                dataMap.put("operation", ((fformula.indexOf(str)>0)? fformula.substring(fformula.indexOf(str)-1,fformula.indexOf(str)):"+" ));
                String fnumberfullname = "";
                for(Map<String,String> dbMap : dbList){
                    if(str != null && str.equals(dbMap.get("freportitem"))){
                        fnumberfullname = dbMap.get("fnumberfullname");
                    }
                }
                dataMap.put("fnumberfullname",fnumberfullname);
                dataList.add(dataMap);
            }
        }else if("1".equals(type)){  //科目
            String sqlwhere = SqlJoint.whereSuffixAnd(item->{
               item.eq("a.freportid",freportid);
               item.eq("a.freportitem",freportitem);
               item.eq("a.data_corp",SessionUser.SessionUser().getCorpId());
            });
            dataList = dao.QueryListMap(" select b.fnumber,a.faccountid,b.fnumber || '' || b.fname fnumberfullname,a.fop operation from e_f_voucher_acctreportitem a ,e_f_voucher_ac b where cast(a.faccountid as varchar) = cast(b.fnumber as varchar)  "+sqlwhere);
        }
        rmap.put("data", dataList);
        return rmap;
    }


    public Result saveData(ReportItemVo vo){
        SessionUser su = SessionUser.SessionUser();
        if("1".equals(vo.getHeadData().getType())){  //科目
            arDao.executeUpdate(" delete from EFVoucherAcctreportitem where dataCorp='"+su.getCorpId()+"' and freportid='"+vo.getHeadData().getFreportid()+"' and freportitem='"+vo.getHeadData().getFreportitem()+"' ");
            vo.getEntryData().forEach(item->{
                EFVoucherAcctreportitem ar = new EFVoucherAcctreportitem() ;
                ar.setDataCorp(su.getCorpId());
                ar.setFreportid(BigInteger.valueOf(Long.parseLong(vo.getHeadData().getFreportid())));
                ar.setFreportitem(vo.getHeadData().getFreportitem());
                ar.setFaccountid(BigInteger.valueOf(Long.parseLong(item.getFaccountid())));
                ar.setFop(item.getOperation());
                arDao.save(ar);
            });
        }else if("2".equals(vo.getHeadData().getType())){  //报表项
            String fformula = "";
            for(ReportItemModel model : vo.getEntryData()){
                fformula += (model.getOperation() + "["+model.getFaccountid()+"]");
            }
            String sql = " update EFVoucherReportitem set fformula=?1 where freportitem=?2 and freportid=?3 and dataCorp=?4 ";
            dao.executeUpdate(sql, fformula.substring(1),vo.getHeadData().getFreportitem(),BigInteger.valueOf(Long.parseLong(vo.getHeadData().getFreportid())),su.getCorpId());
        }
        return Result.resultOk();
    }



}
