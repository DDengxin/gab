package com.tengzhi.business.platform.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.common.dao.OperatorDao;
import com.tengzhi.business.platform.common.dao.OperatorDaoSql;
import com.tengzhi.business.platform.common.dao.RegionSqlDao;
import com.tengzhi.business.platform.common.model.RegionVo;
import com.tengzhi.business.platform.common.service.OperatorService;
import com.tengzhi.business.platform.enroll.dao.GUserInfoDao;
import com.tengzhi.business.platform.enroll.dao.SupplyDao;
import com.tengzhi.business.platform.enroll.model.GUserInfo;
import com.tengzhi.business.platform.enroll.service.ExpertService;
import com.tengzhi.business.platform.specialist.advisory.service.AdvisorysService;
import com.tengzhi.business.platform.specialist.chamberactivities.service.ChamberActivitiesService;
import com.tengzhi.business.platform.specialist.product.dao.MyJscpcodeDao;
import com.tengzhi.business.platform.specialist.product.model.Jscpcode;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sale.saleProduct.saleContract.dao.EXsContractDetailedDao;
import com.tengzhi.business.system.user.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("operatorServiceImpl")
@Transactional
public class OperatorServiceImpl implements OperatorService {
    @Autowired
    private RegionSqlDao regionSqlDao;


    /**
     * Jap
     */
    @Autowired
    private OperatorDao operatorDao;
    /**
     * 原生sql
     */
    @Autowired
    private OperatorDaoSql OperatorDaoSql;

    @Autowired
    private AdvisorysService advisorysService;

    @Autowired
    private ExpertService expertService;

    @Autowired
    private ChamberActivitiesService chamberActivitiesService;
    @Autowired
    private GUserInfoDao gUserInfoDao;

    @Autowired
    private MyJscpcodeDao myJscpcodeDao;

    @Autowired
    private SupplyDao supplyDao;

    @Autowired
    private EXsContractDetailedDao eXsContractDetailedDao;


    /**
     * academic.html数据获取
     */
    @Override
    public Map<String, Object> getAcademic(String reqId, String type) {
        // TODO
        Map<String, Object> rmap = new HashMap<String, Object>();
        if (type.equals("学术论文")) {
            rmap = getTemplate1(reqId);
        } else if (type.equals("工艺知识")) {
            rmap = getTemplate2(reqId);
        } else if (type.equals("经验分享")) {
            rmap = getTemplate1(reqId);
        } else if (type.equals("行业动态")) {
            rmap = getTemplate2(reqId);
        } else if (type.equals("商会活动")) {
            rmap = getTemplate3(reqId);
        } else if (type.equals("技术案例")) {
            rmap = getTemplate2(reqId);
        }
        Map academic = (Map) rmap.get("academic");
        String annex = (String) academic.get("annex");
        rmap.put("Annex", advisorysService.getAnnexName(annex));
        return rmap;
    }


    /**
     * academic.html模板一
     */
    private Map<String, Object> getTemplate1(String reqId) {
        // TODO
        Map<String, Object> rmap = new HashMap<String, Object>();
        Map<String, Object> academic = advisorysService.getOne(reqId);
        rmap.put("academic", academic);
        rmap.put("founder", expertService.getAcademic(academic.get("founderId").toString()));
        rmap.put("recommend", advisorysService.getRecommend(academic.get("founderId").toString()));
        return rmap;
    }

    /**
     * academic.html模板二
     */
    private Map<String, Object> getTemplate2(String reqId) {
        // TODO
        Map<String, Object> rmap = new HashMap<String, Object>();
        Map<String, Object> academic = advisorysService.getOne(reqId);
        rmap.put("academic", academic);
        Map<String, Object> advisorys = advisorysService.getAdvisory(6);
        rmap.put("experienceSharing", advisorys.get("experienceSharing"));//经验分享
        rmap.put("industryDynamics", advisorys.get("industryDynamics"));//行业动态
        rmap.put("knowledgeList", chamberActivitiesService.getLimit("6"));//商会活动
        return rmap;
    }

    /**
     * academic.html模板三
     */
    private Map<String, Object> getTemplate3(String reqId) {
        // TODO
        Map<String, Object> rmap = new HashMap<String, Object>();
        Map<String, Object> knowledge = chamberActivitiesService.getOne(reqId);
        rmap.put("academic", knowledge);
        Map<String, Object> advisorys = advisorysService.getAdvisory(6);
        rmap.put("experienceSharing", advisorys.get("experienceSharing"));//经验分享
        rmap.put("industryDynamics", advisorys.get("industryDynamics"));//行业动态
        rmap.put("knowledgeList", chamberActivitiesService.getLimit("6"));//商会活动
        return rmap;
    }


    @Override
    public List<RegionVo> getRegion() {
        List<RegionVo> retList = new ArrayList<>();
        List<RegionVo> province = regionSqlDao.getRegion();
        for (RegionVo p : province) {
            if (province.size() > 0) {
                List<RegionVo> citys = regionSqlDao.findChildren(p.getId());
                digui(citys);
                p.setChildren(citys);
            }
            retList.add(p);
        }
        return retList;
    }

    @Override
    public void digui(List<RegionVo> city) {
        List<RegionVo> retList = new ArrayList<>();
        for (RegionVo c : city) {
            retList = regionSqlDao.findChildren(c.getId());
            if (retList.size() > 0) {
                c.setChildren(retList);
                digui(retList);
            }
        }
    }

    @Override
    public List<Map<String, Object>> getParamValue() {
        ArrayList<String> chirldrenList = new ArrayList<>();
        String sql = "select v_sysparamtree.*,sys_param.param_value3 as url,sys_param.param_value1 as info,sys_param.param_value2 as button,sys_param.param_ord as px\n" +
                "from v_sysparamtree\n" +
                "left join sys_param \n" +
                "on v_sysparamtree.param_id = sys_param.param_id and v_sysparamtree.param_mod = sys_param.param_mod and v_sysparamtree.org_id = sys_param.org_id\n" +
                "where v_sysparamtree.param_id <>  'CDLM'  and v_sysparamtree.param_id <>  'PTBK' and v_sysparamtree.parent_id <> 'PTBK' "
                + " and v_sysparamtree.org_id = '" + SessionUser.getDefaultCorp() + "' and param_status='true' "
                + " and (v_sysparamtree.root_param_id = 'CDLM' or v_sysparamtree.root_param_id='PTBK')  AND v_sysparamtree.param_mod = '平台' "
                + " order by px asc";
        List<Map<String, Object>> maps = operatorDao.QueryhumpMap(sql);
        for (int i = 0; i < maps.size(); i++) {
            if (!(maps.get(i).get("level").toString().equals("1"))) {
                String parentName = maps.get(i).get("parentName").toString();
                chirldrenList.add(parentName);
            }
        }
        for (int j = 0; j < maps.size(); j++) {
            if (chirldrenList.contains(maps.get(j).get("paramName"))) {
                maps.get(j).put("isChildren", "1");
            } else {
                maps.get(j).put("isChildren", "0");
            }
        }

        return maps;


    }

    @Override
    public List<Map<String, Object>> getUserOrders() {
        SessionUser sessionUser = SessionUser.SessionUser();
        if (ObjectUtil.isNotNull(sessionUser.getgUserInfo())) {
            String userId = sessionUser.getgUserInfo().getUserId();
            String sql = "select dd.ht_no as cp_id,dd.ht_code as cp_num,dd.ht_mo as dd_id,dd.ht_sl as cp_count,dd.ht_je as total,cp.cpcode_name,cp.cover_picture,dd.data_rq AS cp_time,dd.data_man as owner  from \"e_xs_contract_detailed\" as dd  right JOIN  e_js_cpcode as cp on dd.ht_code=cp.cpcode_id where dd.data_man='" + userId + "' ORDER BY cp_time desc";
            return operatorDao.QueryhumpMap(sql);
        } else {
            return new ArrayList<>(0);
        }

    }

    @Override
    public Result deleteUserOrder(String orderId) {
        eXsContractDetailedDao.deleteById(orderId);
        return Result.resultOkMsg("删除成功");


    }

    @Override
    public Jscpcode getProductById(String productId) {
        //SessionUser sessionUser = SessionUser.SessionUser();
        //String userId = sessionUser.getUserId();
        //String sql = "select * from e_js_cpcode where cpcode_id="+productId+" and data_man="+userId;
        return myJscpcodeDao.findById(productId).orElse(null);

    }

    @Override
    public String getGuserInfoId(String dataMan) {
        String corpId = "JD";
//        if (SessionUser.SessionUser() == null) {
//            corpId = SessionUser.getDefaultCorp();
//        }
//        String guserInfoId = supplyDao.getGuserInfoId(dataMan, SessionUser.SessionUser().getCorpId());
        String guserInfoId = supplyDao.getGuserInfoId(dataMan, corpId);
        return guserInfoId;

    }

    @Override
    public Result getAllLikeSearch(String SearchKeyword) {
        //Result result= new Result();

        //result.put(key, value);

        return OperatorDaoSql.getAllLikeSearch(SearchKeyword);

    }

    /***
     * 获取用户基本信息
     * @return
     */
    @Override
    public Result getUserInfo() {
        Result rs = new Result();
        SessionUser sessionUser = SessionUser.SessionUser();
        SysUser user = sessionUser.getSysUser();
        Map<String, Object> data = new HashMap<String, Object>();
        GUserInfo gUserInfo = gUserInfoDao.findByUserId(user.getUserId());
        rs.put("userinfo", gUserInfo);
        rs.put("user", user);
        return rs;
        //	return  OperatorDaoSql.getUserSupplyid(SearchKeyword);

    }

    @Override
    public List<Map> getSupplyCombobox() {
        Result rs = new Result();
        String sql = "select supply_name combid,supply_note combtext   from g_supply  where  status = '核准'";
        return gUserInfoDao.QueryListMap(sql);


    }

    @Override
    public List<SelectVo> getProvince() {
        String sql = "SELECT    dict_province id,name as text  from   dict_province   where   city='0' ";
        List<SelectVo> list = gUserInfoDao.QueryToVo(SelectVo.class, sql);
        return list;
    }


    @Override
    public List<SelectVo> getCity(String city) {
        String sql = "SELECT  city id ,name \"text\"   from   dict_province   where      dict_province = '" + city + "' and city <> '0' and area = '0' and town = '0' ";
        return gUserInfoDao.QueryToVo(SelectVo.class, sql);
    }

    @Override
    public List<SelectVo> getArea(String city, String area) {
        String sql = "SELECT  area id ,name \"text\"   from   dict_province   where      dict_province = '" + city + "' and city = '" + area + "' and area <> '0' and town = '0'";
        return gUserInfoDao.QueryToVo(SelectVo.class, sql);
    }
}
