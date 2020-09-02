package com.tengzhi.business.finance.capitalManager.capitalChange.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseRequisition.model.EcgRequisition;
import com.tengzhi.business.finance.capitalManager.capitalChange.dao.CapitalChangeDao;
import com.tengzhi.business.finance.capitalManager.capitalChange.model.EFVoucherCapitalChange;
import com.tengzhi.business.finance.capitalManager.capitalChange.service.CapitalChangeService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CapitalChangeServiceImpl implements CapitalChangeService {

    @Autowired
    private CapitalChangeDao dao;
    @Autowired
    private SysGenNoteService sysGenNoteService;



    @Override
    public EFVoucherCapitalChange save(EFVoucherCapitalChange model) throws Exception {
        SessionUser su = SessionUser.SessionUser();
        String note=sysGenNoteService.getyyMMNote4(EcgRequisition.class,"ZCJS");
        model.setNote(note);
        model.setKsid(UUID.randomUUID().toString());
        model.setMan(su.getUserId());
        model.setDataCorp(su.getCorpId());
        model.setCreatetime(new Date());
        return dao.save(model);
    }

    @Override
    public BasePage<EFVoucherCapitalChange> getList(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixAnd(item->{
            item.ge("a.rq",map.get("srchRq1"));
            item.le("a.rq",map.get("srchRq2"));
            item.eq("a.data_corp",SessionUser.SessionUser().getCorpId());
            item.contains("a.note",map.get("note"));
            item.contains("b.fnumber",map.get("fnumber"));
            item.contains("b.fname",map.get("fname"));
        });
        String sql = " select a.*,b.fnumber,b.fname,b.device_type, " +
                " (select fnumber||fname from e_f_voucher_capitaltype ct where ct.fid=a.ftypeid ) ftypeid_name," +
                " f_getname('GETDEPTNAME',a.use_dept,'',a.data_corp)use_dept_name," +
                " f_getname('GETUSERNAME',a.use_man,'',a.data_corp)use_man_name," +
                " f_getname('GETUSERNAME',a.new_use_man,'',a.data_corp)new_use_man_name," +
                " f_getname('GETUSERNAME',a.man,'',a.data_corp)man_name," +
                " f_getname('GETDEPTNAME',a.manager_dept,'',a.data_corp)manager_dept_name,  " +
                " f_getname('GETDEPTNAME',a.new_use_dept,'',a.data_corp)new_use_dept_name " +
                " from e_f_voucher_capital_register b,e_f_voucher_capital_change a where a.data_corp=b.data_corp and a.fcardid=b.fcardid " + where;
        return dao.QueryPageLists(baseDto , sql );
    }

    @Override
    public EFVoucherCapitalChange findById(String fcardid) {
        return dao.QueryToFirstBean(" select a.*, " +
                " f_getname('GETUSERNAME',a.use_man,'',a.data_corp)use_man_name, " +
                " f_getname('GETUSERNAME',a.new_use_man,'',a.data_corp)new_use_man_name, " +
                " b.fnumber||b.fname fnullname  " +
                " from  e_f_voucher_capital_register b,e_f_voucher_capital_change a where a.data_corp=b.data_corp and a.fcardid=b.fcardid and a.ksid='"+fcardid+"' ");
    }

    @Override
    public void update(EFVoucherCapitalChange model) {
        EFVoucherCapitalChange dbModel = dao.findById(model.getKsid()).orElse(null);
        dao.dynamicSave(model, dbModel);
    }

    @Override
    public void deleteByNote(String ksid) {
        dao.deleteById(ksid);
    }
}
