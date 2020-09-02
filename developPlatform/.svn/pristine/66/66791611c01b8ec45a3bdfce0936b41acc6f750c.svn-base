package com.tengzhi.business.xt.logistics.express.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.xt.logistics.express.dao.ExpressDao;
import com.tengzhi.business.xt.logistics.express.model.EXtExpress;
import com.tengzhi.business.xt.logistics.express.service.ExpressSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;
@Service
@Transactional
public class ExpressServiceImpl implements ExpressSerive {

    @Autowired
    private ExpressDao expressDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;


    @Override
    public BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(c->{
           c.ge("express_rq",map.get("srchRq1"));
           c.le("express_rq",map.get("srchRq2"));
           c.eq("express_couerier_number",map.get("srchNumber"));
           c.eq("express_man",map.get("srchMan"));
           c.eq("express_recipients",map.get("srchRecipients"));
        });
        String sql = "select note,to_char(express_rq,'yyyy-MM-dd')express_rq,express_man,f_getname('GETUSERNAME',express_man,'',data_corp)express_man_name,express_dept," +
                "f_getname('GETDEPTNAME',express_dept,'','')dept_name,express_addressee,express_recipients,express_type,express_cost,express_recipients_site,express_recipients_tel," +
                "data_corp,f_getname('GETCORPEXP','','',data_corp)corp_exp,express_courier_number,express_remarks from e_xt_express "+where;
        return expressDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public EXtExpress save(EXtExpress eXtExpress) throws Exception {
        String note = sysGenNoteService.getNote(EXtExpress. class,"KD","yyMM",4);
        eXtExpress.setNote(note);
        return expressDao.save(eXtExpress);
    }

    @Override
    public void update(EXtExpress eXtExpress) {
        expressDao.update(eXtExpress);
    }

    @Override
    public Map<String, Object> findByNote(String note) {
        return expressDao.QueryToFirstMap("select note,to_char(express_rq,'yyyy-MM-dd')express_rq,express_man,f_getname('GETUSERNAME',express_man,'',data_corp)express_man_name,express_dept," +
                "f_getname('GETDEPTNAME',express_dept,'','')dept_name,express_addressee,express_recipients,express_type,express_cost,express_recipients_site,express_recipients_tel," +
                "data_corp,f_getname('GETCORPEXP','','',data_corp)corp_exp,express_courier_number,express_remarks from e_xt_express where note='"+note+"'");
    }

    @Override
    public void delete(String note) {
        expressDao.deleteByNote(note);
    }
}
