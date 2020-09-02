package com.tengzhi.business.finance.financialBooks.ledger.service.impl;


import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.financialBooks.ledger.dao.LedgerDao;
import com.tengzhi.business.finance.financialBooks.ledger.service.LedgerService;
import com.tengzhi.business.finance.payment.model.payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Service
@Transactional
public class LedgerServiceImpl implements LedgerService {


    @Autowired
    private LedgerDao ledgerDao;

    @Autowired
    private LedgerService ledgerService;

    @Override
    public BasePage<payment> findAll(BaseDto baseDto) throws IOException, ParseException {

        Map<String, String> map = baseDto.getParamsMap();
        String sqlWhere = SqlJoint.whereSuffixAnd((c) -> {
            c.ge("sfk_rq", map.get("srchRq1"));
            c.le("sfk_rq", map.get("srchRq2"));
            c.contains("sfk_note", map.get("srchNote"));
            c.eq("sfk_bz", map.get("srchBz"));
            c.eq("sfk_dw", map.get("sfkDw"));
            c.eq("sfk_flag", map.get("sfkFlag"));
            c.eq("sfk_fkfs", map.get("sfkFkfs"));
        });
        String sql = "select sfk_note ,sfk_rq ,f_getname('GETDWEXP',sfk_dw,'',data_corp) sfk_dw,sfk_dtype ,sfk_type ,f_get_param_name('交易币种',sfk_bz,'" + SessionUser.getCurrentCorpId() + "') sfk_bz ,sfk_shl ,sfk_bzje ,sfk_yfje ,sfk_fkje ,f_get_param_name('收款方式',sfk_fkfs,'" + SessionUser.getCurrentCorpId() + "','')sfk_fkfs ,sfk_mess ,sfk_flag ,sfk_month ,sfk_xtype ,sfk_sm ,f_getname('GETUSERNAME',data_man,'',data_corp)data_man,data_date,data_corp,sfk_id   " +
                " from  e_cw_sfk where sfk_xtype='SK'" + sqlWhere + "  group by sfk_note ,sfk_rq ,sfk_dw ,sfk_dtype ,sfk_type ,sfk_bz ,sfk_shl ,sfk_bzje ,sfk_yfje ,sfk_fkje ,sfk_fkfs ,sfk_mess ,sfk_flag ,sfk_month ,sfk_xtype ,sfk_sm ,data_man,data_date,data_corp,sfk_id    ";
        return ledgerDao.QueryToBeanPage(baseDto, sql);
    }
}
