package com.tengzhi.business.production.subcontract.wwtj.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.excel.ExcelUtil;
import com.tengzhi.business.cg.yw.purchaseReceipt.dao.ECkInDao;
import com.tengzhi.business.production.subcontract.wwtj.service.WwtjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WwtjServiceImpl implements WwtjService {
    @Autowired
    private ECkInDao eCkInDao;


    @Override
    public BasePage<Map<String, Object>> getSrchTopList(BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixAnd((c) -> {
            c.eq("a.data_corp", SessionUser.SessionUser().getCorpId());
            c.ge("a.ht_date", map.get("srch_Rq1"));
            c.le("a.ht_date", map.get("srch_Rq2"));
            c.contains("a.ht_no", map.get("srchHtNo"));
            c.eq("a.ht_supplier", map.get("srchSupplier"));

        });

        baseDto.setSortField("ht_no");
        baseDto.setSortOrder("DESC");

        String sql = "  select *,(COALESCE(flsl,0) - COALESCE(shl,0)) qjl  from ( " +
                "select to_char(a.ht_date,'yyyy-MM-dd')ht_date,a.ht_no,f_getname('GETDWEXP',a.ht_supplier,'',a.data_corp) ht_supplier_name,COALESCE(sum(b.ht_sl),0) ht_sl,  " +
                "COALESCE((select sum(cpck.out_sl) from e_ck_out cpck where cpck.out_contract = a.ht_no),0) flsl,  " +
                "COALESCE((select sum(inck.in_sl) from e_ck_in inck where inck.in_contract = a.ht_no),0) shl " +
                "from e_cg_contract a,e_cg_contract_detailed b   " +
                "where a.ht_type='WW' and a.ht_no=b.ht_no " + where +
                "group by a.ht_date,a.ht_no,a.ht_supplier,a.data_corp " +
                ") g ";
        return eCkInDao.QueryMapPageList(baseDto, sql + " order by 1 desc ", true);
    }

    @Override
    public void exportExcelOut(HttpServletResponse response, HttpServletRequest request) {
        //获取ExcelUitl实例
        ExcelUtil util = ExcelUtil.getInstance();
        //初始化dto对象
        BaseDto dto = BaseDto.ValueOfRequest(request);
        //将导出的页面定义为0
        dto.setPageIndex(0);
        //查询数据并且生成Excel
        Map<String, String> map = dto.getParamsMap();
        String where = SqlJoint.whereSuffixAnd((c) -> {
            c.eq("a.data_corp", SessionUser.SessionUser().getCorpId());
            c.ge("a.ht_date", map.get("srch_Rq1"));
            c.le("a.ht_date", map.get("srch_Rq2"));
            c.contains("a.ht_no", map.get("srchHtNo"));
            c.eq("a.ht_supplier", map.get("srchSupplier"));

        });
        String sql = "  select *,(COALESCE(flsl,0) - COALESCE(shl,0)) qjl  from ( " +
                "select to_char(a.ht_date,'yyyy-MM-dd')ht_date,a.ht_no,f_getname('GETDWEXP',a.ht_supplier,'',a.data_corp) ht_supplier_name,COALESCE(sum(b.ht_sl),0) ht_sl,  " +
                "COALESCE((select sum(cpck.out_sl) from e_ck_out cpck where cpck.out_contract = a.ht_no),0) flsl,  " +
                "COALESCE((select sum(inck.in_sl) from e_ck_in inck where inck.in_contract = a.ht_no),0) shl " +
                "from e_cg_contract a,e_cg_contract_detailed b   " +
                "where a.ht_type='WW' and a.ht_no=b.ht_no " + where +
                "group by a.ht_date,a.ht_no,a.ht_supplier,a.data_corp " +
                ") g ";
        List<Map<String,Object>> list=null;
        list=eCkInDao.QueryToMap(sql);//SelectListMap
        String  title = "委外统计";
        util.ExcelToWeb(request, title,response,list);
    }


}
