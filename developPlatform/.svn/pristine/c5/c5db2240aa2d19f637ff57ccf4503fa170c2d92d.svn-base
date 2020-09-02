package com.tengzhi.business.xt.receive.jdap.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.excel.ExcelUtil;
import com.tengzhi.business.base.common.ProductType;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.xt.receive.jdap.dao.JdapDao;
import com.tengzhi.business.xt.receive.jdap.model.QyJdJdsq;
import com.tengzhi.business.xt.receive.jdap.service.JdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class JdapServiceImpl implements JdapService {
    @Autowired
    private JdapDao dao;
    @Autowired
    private SysGenNoteService sysGenNoteService;


    @Override
    public BasePage<Map<String,Object>> getList(BaseDto baseDto) {
        Map<String,String> map = baseDto.getParamsMap();
        String where  = SqlJoint.whereSuffixWhere(c->{
            c.ge("rq",map.get("srchRq1"));
            c.le("rq",map.get("srchRq2"));
            c.eq("fwdw",map.get("srch_fwdw"));
        });
        String sql="select note,rq,to_char(jdrq1,'yyyy-MM-dd')||'——'||to_char(jdrq2,'MM-dd') jdrq,jddept,jdfzr,jddd,lfmd,fk_dw,fk_name,fk_num,f_getname('GETCORPEXP', null, null,corp_id) corpName,jdyc,jtyq,ssyq,qtyq,qtzl,jdzg,bmnr,bmnr_mx,sp,qtsp,wp,dzsb,qtwp,sm,flag,opman,corp_id from e_xt_jdap "+where;
        return dao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public QyJdJdsq add(QyJdJdsq qyJdJdsq) {
        SessionUser sessionUser = SessionUser.SessionUser();
        qyJdJdsq.setOpman(sessionUser.getUserId());
        qyJdJdsq.setFlag("登记");
        qyJdJdsq.setNote(sysGenNoteService.getNote(QyJdJdsq.class,"QY","yyMM",4));
        return dao.save(qyJdJdsq);
    }

    @Override
    public QyJdJdsq getByNote(String note) {
        String sql="select note,rq,jdrq1,jdrq2,jddept,jdfzr,jddd,lfmd,fk_dw,fk_name,fk_num,jdyc,jtyq,ssyq,qtyq,qtzl,jdzg,bmnr,bmnr_mx,sp,qtsp,f_getname('GETDWNAME',jddept,null,corp_id) jddeptname,wp,dzsb,qtwp,sm,flag,opman,corp_id from e_xt_jdap ";
        return dao.QueryToFirstBean(sql);
    }

    @Override
    public QyJdJdsq edit(QyJdJdsq qyJdJdsq) {
        return dao.dynamicSave(qyJdJdsq,dao.findById(qyJdJdsq.getNote()).orElse(null));
    }

    @Override
    public void deleteByNote(String note) {
        dao.deleteById(note);
    }

    @Override
    public void change(String note) {

    }

    @Override
    public void changeFlage(String note, String state) {
        dao.updateFlage(state,note);
    }

    @Override
    public void getFalge(String note, String state) {
        String flage = dao.getFlage(note);
        if (flage.equals(state)){

        }
    }

    @Override
    public void getExcle(HttpServletResponse response, HttpServletRequest request) {
        //获取ExcelUitl实例
        ExcelUtil util = ExcelUtil.getInstance();
        //初始化dto对象
        BaseDto dto = BaseDto.ValueOfRequest(request);
        //将导出的页面定义为0
        dto.setPageIndex(0);
        //查询数据并且生成Excel
        Map<String, String> map = dto.getParamsMap();
        String sql = this.getSrchTopListSql(dto);
        util.ExcelToWeb(request, "接待安排" +new Date(), response, dao.QueryToMap(sql));

    }

    private String getSrchTopListSql(BaseDto dto) {
        Map<String, String> map = dto.getParamsMap();
        SessionUser securityUser=SessionUser.SessionUser();
        String sqlWhere = " corp_id='"+securityUser.getCorpId()+"' ";
        if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
            sqlWhere+=" and rq >='"+map.get("srchRq1")+"'";
        }
        if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
            sqlWhere+=" and rq <='"+map.get("srchRq2")+"'";
        }
        if(ObjectUtil.isNotEmpty(map.get("srch_fwdw"))) {
            sqlWhere+=" and fk_dw like'%"+map.get("srch_fwdw")+"%'";
        }
        dto.setSortField("rq");
        dto.setSortOrder("DESC");
        String sql="select note,rq,jddept,jdfzr,jddd,lfmd,jdrq1,jdrq2,fk_dw,fk_name,fk_num,jdyc,jtyq,ssyq,qtyq,qtzl,jdzg,bmnr,bmnr_mx,sp,qtsp,wp,dzsb,qtwp," +
                "sm,flag,opman,corp_id from e_xt_jdap where "+sqlWhere;
        return sql;
    }


}
