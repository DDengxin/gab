package com.tengzhi.business.finance.capitalManager.capitalRegister.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.capitalManager.capitalRegister.dao.CapitalRegisterDao;
import com.tengzhi.business.finance.capitalManager.capitalRegister.model.EFVoucherCapitalRegister;
import com.tengzhi.business.finance.capitalManager.capitalRegister.service.CapitalRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CapitalRegisterServiceImpl implements CapitalRegisterService {

    @Autowired
    private CapitalRegisterDao dao;

    @Override
    public EFVoucherCapitalRegister save(EFVoucherCapitalRegister model) throws Exception {
        model.setDataCorp(SessionUser.SessionUser().getCorpId());
        model.setFcardid(UUID.randomUUID().toString());
        model.setMan(SessionUser.SessionUser().getUserId());
        model.setCreatetime(new Date());
        model.setFstate(1);  //正常
        return dao.save(model);
    }

    @Override
    public BasePage<EFVoucherCapitalRegister> getList(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(item->{
            item.eq("a.data_corp",SessionUser.SessionUser().getCorpId());
            item.contains("a.code",map.get("code"));
            item.contains("a.name",map.get("name"));
            item.eq("a.parent_id",map.get("parentId"));
        });
        String sql = " select a.*," +
                " f_getname('GETDEPTNAME',a.fdeptid,'',a.data_corp) fdeptid_name," +
                " (select ct.fnumber||ct.fname  from e_f_voucher_capitaltype ct where ct.fid=a.ftypeid )ftypeid_name," +
                " case when a.fdeprmethod='100' then '平均年限法' else '不提折旧' end fdeprmethod_name, " +
                " case when a.fstate=1 then '正常' else '异常' end fstate_name," +
                " case when length(COALESCE(a.parent_id,'')) = 0 then '-' " +
                "else (select cr.fnumber||cr.fname from e_f_voucher_capital_register cr where cr.fcardid=a.parent_id) end parent_id_name   " +
                "  from e_f_voucher_capital_register a "+where;
        return dao.QueryPageLists(baseDto , sql );
    }

    @Override
    public EFVoucherCapitalRegister findById(String fcardid) {
        return dao.QueryToFirstBean(" select * from e_f_voucher_capital_register where fcardid='"+fcardid+"' ");
    }

    @Override
    public void update(EFVoucherCapitalRegister model) {
        EFVoucherCapitalRegister dbModel = dao.findById(model.getFcardid()).orElse(null);
        dao.dynamicSave(model, dbModel);
    }

    @Override
    public void deleteByNote(String fcardid) {
        dao.deleteById(fcardid);
    }

    public List<Map<String,Object>> getDeviceSelectList(){
        return dao.QueryToMap(" select fcardid,fnumber||fname fullname  from e_f_voucher_capital_register where device_type='主设备' and data_corp='"+SessionUser.SessionUser().getCorpId()+"' ");
    }

}
