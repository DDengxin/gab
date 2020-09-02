package com.tengzhi.business.sc.tj.scrw.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.sc.task.sctack.dao.ScTackDao;
import com.tengzhi.business.sc.tj.scrw.service.ScrwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class ScrwServiceImpl implements ScrwService {

    @Autowired
    private ScTackDao rwdao;

    //任务单统计
    @Override
    public BasePage<Map<String, Object>> findAll(BaseDto basedto) {
        Map<String, String> map = basedto.getParamsMap();
        SessionUser securityUser = SessionUser.SessionUser();
        String corpId = securityUser.getCorpId();

        String whererq = SqlJoint.whereSuffixAnd((c) -> {
            c.ge("rq", map.get("srchRq1"));
            c.le("rq", map.get("srchRq2"));
            c.eq("cpcode_type", map.get("srchCpcodeType"));
        });


        String where = SqlJoint.whereSuffixWhere((c) -> {
            c.eq("data_corp", corpId, true);
            c.contains("par_name", map.get("srchScmo"));
            c.contains("ht_mo", map.get("srchNote"));
            c.contains("code", map.get("srchCode"));
            c.contains("cpcode_size", map.get("srchSize"));
        });
        //当前未完成数量 不排除不良数量  未完成数量=计划-合格
        String sqlString = " select *,f_getname('GETJTNAMES', gx_ct, '', '') ct_name "
                + " ,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,data_corp) cpcode_name_name "
                + " ,f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cpcode_type,data_corp) cpcode_size_name "
                + " from (" +

                " select 'ROOT' pid,a.sc_mo,a.sc_mo par_name,a.sc_mo||'--工艺:'||f_getname('GETGYNAME',a.sc_gylx,'','') sc_mo_name,a.code,b.cpcode_name,b.cpcode_size,'' gx_ct,a.scsl jh_sl,null wc_sl,null ww_sl,null bl_sl,0 gx_ord,a.data_corp,'' sc_man,a.ht_mo,b.cpcode_type "
                + " from m_sc_scrw a,e_js_cpcode b "
                + " where a.code=b.cpcode_id and a.sc_gylx is not null " + whererq +

                " union all " +

                " select a.sc_mo pid,a.gx_id sc_mo,a.sc_mo par_name,a.gx_name sc_mo_name,a.tcode,a.tname,a.tsize,a.gx_ct,gx_sl jh_sl,to_number(f_getsl('GETSCMOWCSL',a.sc_mo,a.gx_note,'',''),'99999.999999') wc_sl,gx_sl-to_number(f_getsl('GETSCMOWCSL',a.sc_mo,a.gx_note,'',''),'99999.999999') ww_sl,to_number(f_getsl('GETSCMOBLSL',a.sc_mo,a.gx_note,'',''),'99999.999999') bl_sl,a.gx_ord,b.data_corp,(select f_getname('USERNAMERWTOCOL',gc.do_man, '', '') from m_sc_gclist gc where gc.sc_mo=a.sc_mo and gc.gx_note=a.gx_note group by gc.do_man) sc_man,b.ht_mo,c.cpcode_type "
                + " from m_sc_scrw_gx a,m_sc_scrw b,e_js_cpcode c "
                + " where a.sc_mo=b.sc_mo and a.tcode=c.cpcode_id " + whererq + ") t   " + where;
        return rwdao.QueryToMapPage(basedto, sqlString);
    }

    //工序统计
    @Override
    public BasePage<Map<String, Object>> findGxAll(BaseDto basedto) {
        Map<String, String> map = basedto.getParamsMap();
        SessionUser securityUser = SessionUser.SessionUser();
        String corpId = securityUser.getCorpId();
        String where = SqlJoint.whereSuffixAnd((c) -> {
            c.eq("data_corp", corpId, true);
            c.eq("gx_id", map.get("srchScgx"));
            c.eq("gx_type", map.get("srchGxStype"));
        });
        String whererq = SqlJoint.whereSuffixAnd((c) -> {
            c.eq("a.data_corp", corpId, true);
            c.eq("a.gx_id", map.get("srchScgx"));
            c.ge("c.rq", map.get("srchRq1"));
            c.le("c.rq", map.get("srchRq2"));
            c.eq("a.gx_type", map.get("srchGxStype"));
        });
        //当前未完成数量 不排除不良数量  未完成数量=计划-合格
        String sqlString = "select *,f_getname('GETJTNAMES', gx_ct, '', '') ct_name" +
                "  ,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',gx_type,data_corp) cpcode_name_name " +
                "  ,f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',gx_type,data_corp) cpcode_size_name " +
                "  from (" +
                "  select 'ROOT' pid ,gx_id||'-'||gx_name gx_id,gx_name gx_acm ,null  cpcode_name,null cpcode_size,null gx_ct,null jh_sl,null wc_sl,null ww_sl,null bl_sl,data_corp,null sc_man,gx_ord,gx_type from m_sc_scgx where gx_flag='true' " + where +
                " union all \n" +
                "  select a.gx_id||'-'||a.gx_name AS pid, b.tcode AS gx_id,b.gx_name gx_acm, b.tname cpcode_name, b.tsize cpcode_size, b.gx_ct , sum(b.gx_sl) AS jh_sl, to_number(f_getsl('GETGXSL',a.gx_id||b.tcode||b.tname||b.tsize,'','',''),'99999.999999') AS wc_sl,sum(b.gx_sl)-to_number(f_getsl('GETGXSL',a.gx_id||b.tcode||b.tname||b.tsize,'','',''),'99999.999999') AS ww_sl, to_number(f_getsl('GETGXBLSL',a.gx_id||b.tcode||b.tname||b.tsize,'','',''),'99999.999999') AS bl_sl, a.data_corp,(select f_getname('USERNAMERWTOCOL',gc.do_man, '', '') from m_sc_gclist gc where gc.gx=a.gx_id and gc.code=b.tcode and gc.data_corp=a.data_corp group by gc.do_man) sc_man,null gx_ord,a.gx_type FROM m_sc_scgx a,m_sc_scrw_gx b, m_sc_scrw c WHERE b.sc_mo = c.sc_mo  and a.gx_id=b.gx_id " + whererq + " GROUP BY a.gx_id,b.gx_name,b.tcode,b.tname, b.tsize, b.gx_ct,a.data_corp) t ";
        return rwdao.QueryToMapPage(basedto, sqlString);
    }

    //机台统计
    @Override
    public BasePage<Map<String, Object>> findJtAll(BaseDto basedto) {
        Map<String, String> map = basedto.getParamsMap();
        SessionUser securityUser = SessionUser.SessionUser();
        String corpId = securityUser.getCorpId();
        String whererq = SqlJoint.whereSuffixAnd((c) -> {
            c.ge("rq", map.get("srchRq1"));
            c.le("rq", map.get("srchRq2"));
            c.eq("cpcode_type", map.get("srchCpcodeType"));
        });
        String where = SqlJoint.whereSuffixWhere((c) -> {
            c.eq("data_corp", corpId, true);
            c.eq("machine_id", map.get("srchScct"));
        });
        String sqlString = " select * ,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,data_corp) cpcode_name_name " +
                "  ,f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cpcode_type,data_corp) cpcode_size_name " +
                "  from (" +
                " select 'ROOT' pid,machine_id,machine_name,machine_id gx_acm,null tcode,null cpcode_name,null cpcode_size,null hg_sl,null bl_sl,null bf_sl,data_corp,null sc_man,'' cpcode_type  from m_sb_jt where machine_flag='启用' \n" +
                " union all\n" +
                " select pid,machine_id,machine_name,gx_acm,tcode,tname,tsize,sum(hg_sl) hg_sl,sum(bl_sl) bl_sl,sum(bf_sl) bf_sl, data_corp,f_getname('USERNAMERWTOCOL',do_man, '', '') sc_man,cpcode_type from (\n" +
                " select tct pid,code machine_id,code machine_name,tct gx_acm,code tcode,tname,tsize,sum(tsl) hg_sl,0 bl_sl,0 bf_sl,a.data_corp,a.do_man,b.cpcode_type from m_sc_gclist a,e_js_cpcode b  where a.code=b.cpcode_id and gc_flag='HG' \n" + whererq +
                " group by tct,code,tname,tsize,a.data_corp,a.do_man,b.cpcode_type\n" +
                " union all \n" +
                " select tct pid,code machine_id,code machine_name,tct gx_acm,code tcode,tname,tsize,0 hg_sl,sum(tsl) bl_sl,0 bf_sl,a.data_corp,a.do_man,b.cpcode_type from m_sc_gclist a,e_js_cpcode b  where a.code=b.cpcode_id and gc_flag not in ('HG','BF') \n" + whererq +
                " group by tct,code,tname,tsize,a.data_corp,a.do_man,b.cpcode_type\n" +
                " union all \n" +
                " select tct pid,code machine_id,code machine_name,tct gx_acm,code tcode,tname,tsize,0 hg_sl,0 bl_sl,sum(tsl) bf_sl,a.data_corp,a.do_man,b.cpcode_type from m_sc_gclist a,e_js_cpcode b  where a.code=b.cpcode_id and gc_flag='BF' \n" + whererq +
                " group by tct,code,tname,tsize,a.data_corp,a.do_man,b.cpcode_type\n" +
                " ) s group by pid,machine_id,machine_name,gx_acm,tcode,tname,tsize,data_corp,do_man,cpcode_type" +
                ") t " + where;

        return rwdao.QueryToMapPage(basedto, sqlString);
    }
}
