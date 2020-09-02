package com.tengzhi.business.xt.reception.visitors.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.dao.SysParamDao;
import com.tengzhi.business.xt.reception.visitors.dao.VisitorsDao;
import com.tengzhi.business.xt.reception.visitors.model.Visitors;
import com.tengzhi.business.xt.reception.visitors.service.VisitorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class VisitorsSreviceImpl implements VisitorsService {
    @Autowired
    private VisitorsDao VisitorsDao;
    @Autowired
    private SysParamDao sysParamDao;
    @Autowired
    private SysGenNoteService sysGenNoteService;


    @Override
    public BasePage<Map<String, Object>> getList(BaseDto dto) {
        Map<String, String> map = dto.getParamsMap();
        String where = SqlJoint.whereSuffixAnd((c) -> {
            c.ge("rq", map.get("srchRq1"));
            c.le ("rq", map.get("srchRq2"));
            c.contains("vdw", map.get("vdw"));
        });
        String sql = " select note,to_char(rq,'yyyy-mm-dd')rq,vdw,vsl,vsm,vcap,vlysl,to_char(vlfrq,'yyyy-mm-dd')vlfrq,to_char(vghrq,'yyyy-mm-dd')vghrq,vcgz,vsgz,man,dept,data_corp,flag,visgh,f_getname ( 'GETDEPTNAME', dept, '', data_corp ) deptname," +
                " f_getname ( 'GETUSERNAME', man, '', data_corp ) manname  from e_xt_visitors   where 1=1 " + where;
        return VisitorsDao.QueryMapPageList(dto, sql, true);
    }


  @Override
    public Visitors save(Visitors Visitors) throws Exception {
        SessionUser securityUser=SessionUser.SessionUser();
        Visitors.setNote(sysGenNoteService.getNote(Visitors.class, "VI", "", 4));
        Visitors.setMan(securityUser.getUserId());
        Visitors.setDept(securityUser.getDeptId());
        Visitors.setFlag("登记");
        Visitors.setData_corp(securityUser.getCorpId());
        return VisitorsDao.save(Visitors);

    }




    @Override
    public Visitors findBynote(String note) {
        Visitors sys=VisitorsDao.findByNote(note);
        return sys;
    }
    @Override
    public void update(Visitors Visitors) {
        VisitorsDao.dynamicSave(Visitors, VisitorsDao.findByNote(Visitors.getNote()));
    }
    @Override
    public void deleteById(String note) {
        VisitorsDao.deleteById(note);
    }


}
