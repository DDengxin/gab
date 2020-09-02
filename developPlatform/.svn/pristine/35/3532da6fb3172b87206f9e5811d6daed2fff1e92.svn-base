package com.tengzhi.business.finance.capitalManager.capitalDepletion.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseRequisition.model.EcgRequisition;
import com.tengzhi.business.finance.capitalManager.capitalDepletion.dao.CapitalDepletionDao;
import com.tengzhi.business.finance.capitalManager.capitalDepletion.model.EFVoucherCapitalDepletion;
import com.tengzhi.business.finance.capitalManager.capitalDepletion.service.CapitalDepletionService;
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
public class CapitalDepletionServiceImpl implements CapitalDepletionService {

    @Autowired
    private CapitalDepletionDao dao;
    @Autowired
    private SysGenNoteService sysGenNoteService;



    @Override
    public EFVoucherCapitalDepletion save(EFVoucherCapitalDepletion model) throws Exception {
        SessionUser su = SessionUser.SessionUser();
        String note=sysGenNoteService.getyyMMNote4(EcgRequisition.class,"ZCJS");
        model.setNote(note);
        model.setKsid(UUID.randomUUID().toString());
        model.setMan(su.getUserId());
        model.setOprq(new Date());
        model.setDataCorp(su.getCorpId());
        model.setFlag("登记");
        return dao.save(model);
    }

    @Override
    public BasePage<EFVoucherCapitalDepletion> getList(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixAnd(item->{
            item.eq("a.data_corp",SessionUser.SessionUser().getCorpId());
            item.contains("b.fnumber",map.get("fnumber"));
            item.contains("b.fname",map.get("fname"));
        });
        String sql = " select a.*,b.fnumber||fname zccode_name,f_getname('GETUSERNAME',a.man,'',a.data_corp) man_name " +
                "  from e_f_voucher_capital_depletion a,e_f_voucher_capital_register b where a.zccode=b.fcardid and a.data_corp=b.data_corp   " + where;
        return dao.QueryPageLists(baseDto , sql );
    }

    @Override
    public EFVoucherCapitalDepletion findById(String fcardid) {
        return dao.QueryToFirstBean(" select a.*," +
                " (select fnumber||fname from e_f_voucher_capital_register cr where cr.fcardid=a.zccode) zccode_name  " +
                " from e_f_voucher_capital_depletion a where ksid='"+fcardid+"' ");
    }

    @Override
    public void update(EFVoucherCapitalDepletion model) {
        EFVoucherCapitalDepletion dbModel = dao.findById(model.getKsid()).orElse(null);
        dao.dynamicSave(model, dbModel);
    }

    @Override
    public void deleteByNote(String ksid) {
        dao.deleteById(ksid);
    }
}
