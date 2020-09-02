package com.tengzhi.business.js.tlcz.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.js.tlcz.dao.TlczDao;
import com.tengzhi.business.js.tlcz.model.EJsTlcz;
import com.tengzhi.business.js.tlcz.service.TlczService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @Author: czf
 * @Date:2020-08-20 9:21
 */
@Service
@Transactional
public class TlczServiceImpl implements TlczService {

    @Autowired
    private TlczDao tlczDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;


    @Override
    public BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();

        String where = SqlJoint.whereSuffixWhere(c->{
            c.ge("rq",map.get("srchRq1"));
            c.le("rq",map.get("srchRq2"));
            c.eq("code",map.get("srchCode"));
            c.eq("cz_lb",map.get("srchLb"));
            c.eq("cd",map.get("srchCd"));
            c.eq("flag",map.get("srchFlag"));
        });
        String sql = "select code,to_char(rq,'yyyy-MM-dd')rq,tlcz,cz_lb,cz_thfl,cz_stype,cd,gccz,flag,sm,data_man,to_char(data_date,'yyyy-MM-dd')data_date," +
                "data_corp,f_getparamname('GETTYPEBYRAMNAME',cd,'原料产地','','','"+SessionUser.getCurrentCorpId()+"') cd_name from e_js_tlcz "+where;
        return tlczDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public EJsTlcz save(EJsTlcz eJsTlcz) throws Exception {
        String code = sysGenNoteService.getNote(EJsTlcz.class,"","",5);
        SessionUser sessionUser = SessionUser.SessionUser();
        eJsTlcz.setDataCorp(sessionUser.getCorpId());
        eJsTlcz.setDataDate(new Date());
        eJsTlcz.setDataMan(sessionUser.getUserId());
        eJsTlcz.setCode(code);
        return tlczDao.save(eJsTlcz);
    }

    @Override
    public void update(EJsTlcz eJsTlcz) {
        SessionUser sessionUser = SessionUser.SessionUser();
        eJsTlcz.setDataCorp(sessionUser.getCorpId());
        eJsTlcz.setDataDate(new Date());
        eJsTlcz.setDataMan(sessionUser.getUserId());
        tlczDao.update(eJsTlcz);
    }

    @Override
    public Map<String, Object> findByCode(String code) {
        return tlczDao.QueryToFirstMap("select code,to_char(rq,'yyyy-MM-dd')rq,tlcz,cz_lb,cz_thfl,cz_stype,cd,gccz,flag,sm,data_man," +
                "to_char(data_date,'yyyy-MM-dd hh24:mi')data_date,data_corp from e_js_tlcz where code=:1",code);
    }

    @Override
    public void deleteByCode(String code) {
        tlczDao.deleteByCode(code);
    }


}
