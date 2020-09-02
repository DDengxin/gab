package com.tengzhi.business.ck.yw.ckrk.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseReceipt.dao.ECkInDao;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseSettle.dao.ECwYsyfDao;
import com.tengzhi.business.ck.yw.ckrk.service.WarehouseInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class WarehouseInServiceImpl implements WarehouseInService {
    @Autowired
    private ECkInDao eCkInDao;

    @Autowired
    private ECwYsyfDao eCwYsyfDao;

    @Override
    public BasePage<ECkIn> getSrchTopList(BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixAnd((c) -> {
            c.eq("data_corp", SessionUser.SessionUser().getCorpId(), true);
            c.in("in_flag","结算","库审");
            c.ge("in_rq", map.get("srchRq1"));
            c.le("in_rq", map.get("srchRq2"));

            c.like("in_note", map.get("srchinNote"));
            c.like("in_customer", map.get("srchSupplier"));
            c.eq("in_flag", map.get("srchflag"));
            c.eq("in_type", map.get("cgStype"));

        });
        String sql = " select in_rq ,in_note ,in_customer ,in_lib,in_act,in_bz,in_tax ,abs(sum(in_js)) in_js,abs(sum(in_sl)) in_sl,abs(sum(in_zl)) in_zl,in_flag ,data_man ,MAX(data_date) data_date,data_corp ,in_man ,MAX(in_date) in_date,in_type ,f_get_param_name('产品大类',in_type,'"+   SessionUser.getCurrentCorpId()   +"') intypename,f_get_param_name('仓库收发',in_act,'"+   SessionUser.getCurrentCorpId()   +"') inactname,f_get_param_name('交易币种',in_bz,'"+   SessionUser.getCurrentCorpId()   +"') inbzname,f_getname('GETDWEXP',in_customer,'',data_corp) incustomername,f_get_param_name('库房档案',in_lib,'"+   SessionUser.getCurrentCorpId()   +"') inlibname,f_getname('GETUSERNAME',data_man,'',data_corp) datamanname,f_getname('GETUSERNAME',in_man,'',data_corp) inmanname "
                + " from  e_ck_in  where  1=1 "
                + where
                + " group by in_rq,in_note,in_customer,in_act,in_flag,data_man,data_corp,in_lib,in_bz,in_tax,in_type,in_man "
                +" order by in_rq desc,in_note desc ";

        return eCkInDao.QueryPageLists(baseDto, sql);
    }

    @Override
    public BasePage<ECkIn> getSrchBottomList(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        Object inNote = map.get("inNote");
        String where = "and  in_note='" + inNote + "'";
        if (ObjectUtil.isNotEmpty(map.get("inContract"))) {
            where += " and in_contract = '" + map.get("inContract") + "'";
        }
        if (ObjectUtil.isNotEmpty(map.get("inFlag"))) {
            where += " and in_flag = '" + map.get("inFlag") + "'";
        }
        String sql = "select in_note||'_'||in_contract||'_'||in_kfcode as id,in_code,abs(in_js) in_js ,abs(in_sl) in_sl ,abs(in_zl) in_zl ,in_pack ,in_bpack ,in_price ,in_contract ,in_kfcontract ,in_Kfcode, in_checkflag , in_month ,in_type,f_get_param_name('产品大类',in_type,'"+   SessionUser.getCurrentCorpId()   +"') intypename ,in_luono ,in_vnote in_hs,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',in_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,b.cpcode_size,f_getparamname('GETBYPARENTID',b.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_bz,b.cpcode_xp,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03,b.cpcode_Size_En, f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',in_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl  from  e_ck_in a,e_js_cpcode b where a.in_Kfcode=b.cpcode_id   " + where;
        return eCkInDao.QueryNoPageLists(sql);
    }

    @Override
    public Result getFlag(String inNote, String flag) {
        String flagString = eCkInDao.getFlag(inNote);
        if (flag.equals(flagString)) {
            return Result.resultOk("操作成功！");
        }
        return Result.error("该单不是“" + flag + "”状态,不能操作！");
    }

    @Override
    public Result setFlag(String inNote, String flag) {
        SessionUser securityUser = SessionUser.SessionUser();
        if ("库审".equals(flag)) {
            eCkInDao.rk(inNote, securityUser.getUserId());
            eCwYsyfDao.setFlag(inNote, "库审");
        } else if ("结算".equals(flag)) {
            //取消入库需要判断库存
            String sqlString = "select  proc_ck_out('" + inNote + "','" + securityUser.getCorpId() + "')";
            String magess = eCkInDao.getSingleResult(sqlString);
            if ("操作成功！".equals(magess)) {
                //库存判断结束
                eCkInDao.qxrk(inNote);
            } else {
                return Result.resultOk(magess);
            }

        }
        return Result.resultOk("操作成功！");
    }

    @Override
    public ModelAndView table(ModelAndView mv, String inNote) {
        String sql = "select in_act,in_note,to_char(in_rq,'YYYY-MM-DD') in_rq,f_getname('GETCORPNAME', '','',data_corp) \"corpName\",f_getname('GETDWNAME', in_customer,'',data_corp) \"corpExp\",(f_get_param_name('产品大类',in_type,'"+   SessionUser.getCurrentCorpId()   +"','')||f_get_param_name('仓库收发',in_act,'"+   SessionUser.getCurrentCorpId()   +"','')) \"typeAndAct\",f_getname('GETUSERNAME', data_man,'', data_corp) \"manName\",f_get_param_name('库房档案',in_lib,'"+   SessionUser.getCurrentCorpId()   +"','') \"libName\",to_char(now(),'YYYY-MM-DD HH24:MI:SS') now,f_getname('GETUSERNAME', in_man,'', data_corp) \"inMan\" FROM E_Ck_In WHERE in_note= '" + inNote + "' limit 1 ";
        List<Map> table = eCkInDao.QueryListMap(sql);
        mv.addObject("inNote", table.get(0).get("in_note"));
        mv.addObject("inRq", table.get(0).get("in_rq"));
        mv.addObject("corpName", table.get(0).get("corpName"));
        mv.addObject("corpExp", table.get(0).get("corpExp"));
        mv.addObject("typeAndAct", table.get(0).get("typeAndAct"));
        mv.addObject("manName", table.get(0).get("manName"));
        mv.addObject("libName", table.get(0).get("libName"));
        mv.addObject("now", table.get(0).get("now"));
        mv.addObject("inMan", table.get(0).get("inMan"));
        String mxSql = "select ((coalesce(j.cpcode_size_en,''))||'   '||f_getparamname('GETBCPCODENAME',j.cpcode_name,'','技术',in_type,'"+   SessionUser.getCurrentCorpId()   +"')||'   '||(coalesce(j.cpcode_size,''))) \"cpNSB\",round(in_sl,2) in_sl,f_get_param_name('计量单位',j.cpcode_bz,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_bz,e.in_pack,f_get_param_name('产品大类',j.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"','') cpcode_type from e_js_cpcode j,e_ck_in e where e.in_code = j.cpcode_id and e.in_note= '" + inNote + "'";
        List<Map> tableMx = eCkInDao.QueryListMap(mxSql);
        mv.addObject("mx", tableMx);
        mv.addObject("mxCount", tableMx.size());
        double sum = 0;
        for (int i = 0; i < tableMx.size(); i++) {
            sum += Double.parseDouble(tableMx.get(i).get("in_sl").toString());
        }
        mv.addObject("sum", sum);
        return mv;
    }

}
