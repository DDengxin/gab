package com.tengzhi.business.finance.checkout.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.impl.BaseServiceImpl;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.checkout.dao.CheckoutDao;
import com.tengzhi.business.finance.checkout.model.CheckOut;
import com.tengzhi.business.finance.checkout.service.CheckoutService;
import com.tengzhi.business.finance.checkout.vo.SysVo;
import com.tengzhi.business.system.params.dao.SysParamDao;
import com.tengzhi.business.system.params.model.SysParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CheckoutServiceImpl extends BaseServiceImpl implements CheckoutService {

    @Autowired
    private CheckoutDao checkoutDao;

    @Autowired
    SysParamDao sysParamDao;

    @Override
    public Result findbyid(String xm) {
        Map<String, Object> map=new HashMap<>();
        List<Map<String, Object>> itemMap = new ArrayList<>();
        List<Map> list = checkoutDao.QueryListMap("select * from e_pz_jylist_jy where xm_code=:1", xm);
        map.put("rq", list.get(0).get("rq"));
        map.put("pch", list.get(0).get("pch"));
        map.put("jybh", list.get(0).get("jybh"));
        map.put("jybhName", ObjectUtil.isNotNull(checkoutDao.findParams(list.get(0).get("jybh").toString())) ?checkoutDao.findParams(list.get(0).get("jybh").toString()).getParamName():"");
        map.put("xmOrd",list.get(0).get("xm_ord"));
        map.put("xmCode", list.get(0).get("xm_code"));
        list.forEach(item -> {
            Map<String, Object> map1=new HashMap<>();
            map1.put("xmTvalue",item.get("xm_tvalue").toString());
            map1.put("xmValue",item.get("xm_value").toString());
            itemMap.add(map1);
        });
        map.put("itemMap",itemMap);
        return Result.resultOk(map);
    }

    @Override
    public List<Map<String, String>> getParamList(String paramId) {
        return sysParamDao.findAll(Specifications.where((c) -> {
            c.eq("parentId", paramId);
            c.addOrderBy("paramOrd", Sort.Direction.ASC);
        })).stream().map(row -> {
            return new HashMap<String, String>() {{
                put("xmTvalue", row.getParamId());
                put("xmValueNO", row.getParamName());
            }};
        }).collect(Collectors.toList());
    }


    @Override
    public Result findAll(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        String where = "";
        if (ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
            where += " and rq >='" + map.get("srchRq1") + "'";
        }
        if (ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
            where += " and rq <='" + map.get("srchRq2") + "'";
        }
        if (ObjectUtil.isNotEmpty(map.get("pch"))) {
            where += " and pch like '%" + map.get("pch") + "%'";
        }
        if (ObjectUtil.isNotEmpty(map.get("jybh"))) {
            where += " and jybh = '" + map.get("jybh") + "'";
        }
        if (ObjectUtil.isNotEmpty(map.get("xmCode"))) {
            where += " and xm_code = '" + map.get("xmCode") + "'";
        }

        String sqlString = "select rq,pch,jybh,xm_code,xm_ord from e_pz_jylist_jy where 1=1 " + where + " GROUP BY rq,pch,jybh,xm_code,xm_ord ORDER BY xm_ord DESC ";
        BasePage<CheckOut> constituents = checkoutDao.QueryPageLists(baseDto, sqlString);

        List<Map<String, Object>> itemList = new ArrayList<>();
        List<CheckOut> list = (List<CheckOut>) constituents.getResult().get("data");
        list.forEach(item -> {

            String sqlitem = "select xm_tvalue,xm_value from e_pz_jylist_jy where xm_code='" + item.getXmCode() + "' ";
            List<Map> Item = checkoutDao.QueryListMap(sqlitem);
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("rq", item.getRq());
            itemMap.put("pch", item.getPch());
            itemMap.put("xmCode", item.getXmCode());
            Item.forEach(T -> {
                itemMap.put((String)ObjectUtil.defaultIfNull(T.get("xm_tvalue"),""), (String)ObjectUtil.defaultIfNull(T.get("xm_value"),""));
            });

            itemList.add(itemMap);
        });
        return Result.resultOk().put("data", itemList).put("total", constituents.getPageTotal());
    }



    @Override
    public List<SysParams> paramsAll(String id) {
        return checkoutDao.findAllParams(id);
    }


    @Transactional
    @Override
    public Result save(SysVo sysVo) throws Exception {
        if (judgeUnique(sysVo.getConstituent().get("xmCode").toString())) {
            SessionUser securityUser=SessionUser.SessionUser();
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fmt.parse(sysVo.getConstituent().get("rq").toString());
            sysVo.getGrid().forEach(item -> {
                CheckOut checkOut = new CheckOut();
                checkOut.setRq(date);
                checkOut.setDataDate(new Date());
                checkOut.setPch(sysVo.getConstituent().get("pch").toString());
                checkOut.setJybh(sysVo.getConstituent().get("jybh").toString());
                checkOut.setXmOrd(sysVo.getConstituent().get("xmOrd").toString());
                checkOut.setXmCode(sysVo.getConstituent().get("xmCode").toString());
                checkOut.setXmTvalue(item.get("xmTvalue").toString());
                checkOut.setXmValue(item.get("xmValue").toString());
                checkOut.setDataMan(securityUser.getUserId());
                checkOut.setDataCorp(securityUser.getCorpId());
                checkoutDao.save(checkOut);
            });
            return Result.resultOk();
        } else {
            throw new Exception("项目号已存在");
        }

    }


    @Override
    public void update(SysVo sysVo) {
        sysVo.getGrid().forEach(item -> {
            checkoutDao.update(
                    sysVo.getConstituent().get("pch").toString(),
                    item.get("xmTvalue").toString(),
                    item.get("xmValue").toString());
        });
    }

    @Transactional
    @Override
    public void del(String note) {
        checkoutDao.deleteByXm(note);
    }

    @Override
    public boolean judgeUnique(String xmCode) {
        return checkoutDao.findAll(Specifications.where((c) -> {
            c.eq("xmCode", xmCode);
        })).size() <= 0;
    }

    @Override
    public List<Map> findpch(String key) {
        String sql=" select " +
                "b.in_bpack pch," +
                "c.param_id jybh," +
                "c.param_name \"jybhName\" " +
                "from e_js_cpcode a," +
                "(select in_code,in_bpack from e_ck_in where in_bpack like '%"+key+"%') b," +
                "sys_param c " +
                "where a.cpcode_id=b.in_code and a.cpcode_check=c.parent_id ";
        List<Map> list=checkoutDao.QueryListMap(sql);
        return list;
    }

}
