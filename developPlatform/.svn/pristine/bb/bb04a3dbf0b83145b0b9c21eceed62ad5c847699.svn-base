package com.tengzhi.business.finance.capitalManager.capitalCheck.service.impl;

import com.tengzhi.IM.layerIM.vo.FriendUsers;
import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.excel.ExcelUtil;
import com.tengzhi.business.cg.yw.purchaseRequisition.model.EcgRequisition;
import com.tengzhi.business.finance.capitalManager.capitalCheck.dao.CapitalCheckDao;
import com.tengzhi.business.finance.capitalManager.capitalCheck.model.EFVoucherCapitalCheck;
import com.tengzhi.business.finance.capitalManager.capitalCheck.service.CapitalCheckService;
import com.tengzhi.business.sale.saleProduct.saleContract.dao.EXsContractDao;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CapitalCheckServiceImpl implements CapitalCheckService {

    @Autowired
    private CapitalCheckDao dao;
    @Autowired
    private SysGenNoteService sysGenNoteService;
    @Autowired
    private EXsContractDao eXsContractDao;



    @Override
    public EFVoucherCapitalCheck save(EFVoucherCapitalCheck model) throws Exception {
        SessionUser su = SessionUser.SessionUser();
        String note=sysGenNoteService.getyyMMNote4(EcgRequisition.class,"ZCJS");
        model.setNote(note);
//        model.setKsid(UUID.randomUUID().toString());
        model.setMan(su.getUserId());
        model.setDataCorp(su.getCorpId());
        model.setCreatetime(new Date());
        return dao.save(model);
    }

    @Override
    public BasePage<EFVoucherCapitalCheck> getList(BaseDto baseDto) throws IOException {
        String sql = this.getSrchSql(baseDto);
        return dao.QueryPageLists(baseDto , sql );
    }

    private String getSrchSql(BaseDto dto){
        Map<String,String> map = dto.getParamsMap();
        SessionUser su = SessionUser.SessionUser();
        String where = SqlJoint.whereSuffixWhere(item->{
            item.eq("a.data_corp",su.getCorpId());
            item.contains("a.fnumber",map.get("fnumber"));
            item.contains("a.fname",map.get("fname"));
        });
        String sql = " select a.*, " +
                " f_getname('GETDEPTNAME',a.fdeptid,'',a.data_corp) fdeptid_name," +
                " a.ftype_number||a.ftype_name  ftypeid_name " +
                " from e_f_voucher_capital_check a " + where;
        return sql;
    }

    @Override
    public EFVoucherCapitalCheck findById(String fcardid) {
        return dao.QueryToFirstBean(" select a.*, " +
                " f_getname('GETUSERNAME',a.use_man,'',a.data_corp)use_man_name, " +
                " f_getname('GETUSERNAME',a.new_use_man,'',a.data_corp)new_use_man_name, " +
                " b.fnumber||b.fname fnullname  " +
                " from  e_f_voucher_capital_register b,e_f_voucher_capital_change a where a.data_corp=b.data_corp and a.fcardid=b.fcardid and a.ksid='"+fcardid+"' ");
    }

    @Override
    public void update(EFVoucherCapitalCheck model) {
//        EFVoucherCapitalCheck dbModel = dao.findById(model.getKsid()).orElse(null);
//        dao.dynamicSave(model, dbModel);
    }

    @Override
    public void deleteByNote(String ksid) {
        dao.deleteById(ksid);
    }

    public Result jcOption(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String rq = sdf.format(new Date());
        String createtime = sdf2.format(new Date());
        SessionUser su = SessionUser.SessionUser();
        String note=sysGenNoteService.getyyMMNote4(EFVoucherCapitalCheck.class,"PD");
        dao.executeUpdate(" delete from EFVoucherCapitalCheck where dataCorp='"+su.getCorpId()+"' and rq='"+rq+"'  ");
        dao.jcOption(su.getCorpId(),note,createtime,su.getUsername(),rq);
        return Result.resultOk();
    }
    @Override
    public void getSrchTopListExportExcel(HttpServletResponse response, HttpServletRequest request) {
        ExcelUtil util = ExcelUtil.getInstance();
        BaseDto dto = BaseDto.ValueOfRequest(request);
        dto.setPageIndex(0);
        Map<String, String> map = dto.getParamsMap();
        String  title = "【"+map.get("rq")+"】资产盘点";
        String sql = this.getSrchSql(dto);
        util.ExcelToWeb(request, title, response, eXsContractDao.QueryToMap(sql));
    }

    public List<Map<String,Object>> getJcTree(BaseDto dto){
        Map<String,String> map = dto.getParamsMap();
        String where = SqlJoint.whereSuffixAnd(item->{
            item.le("rq",map.get("srchRq2"));
            item.ge("rq",map.get("srchRq1"));
        });
        SessionUser su = SessionUser.SessionUser();
        String sql =
                " select distinct 'false' expanded,'ROOT' pid,to_char(rq,'yyyy-MM-dd') rq,note  from e_f_voucher_capital_check where data_corp='"+su.getCorpId()+"'  " + where;
        return dao.QueryToMap(sql);
    }

}
