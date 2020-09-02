package com.tengzhi.business.platform.specialist.advisory.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.business.platform.specialist.advisory.dao.AdvisorysDao;
import com.tengzhi.business.platform.specialist.advisory.model.Advisorys;
import com.tengzhi.business.platform.specialist.advisory.service.AdvisorysService;
import com.tengzhi.business.system.upload.dao.SysUploadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @author 王翔
 * @create 2020-04-12
 */
@Service
@Transactional
public class AdvisorysServiceImpl implements AdvisorysService {

    @Autowired
    AdvisorysService advisorysService;

    @Autowired
    private AdvisorysDao AdvisoryDao;
    @Autowired
    private SysUploadDao sysUploadDao;

    @Override
    public BasePage<Advisorys> findAll(BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return AdvisoryDao.QueryPageList(baseDto, Specifications.where((c) -> {
            c.contains("title", map.get("title"));
            c.between_common("releaseDate", map.get("releaseDateStart"), map.get("releaseDateEnd"));
            if (map.get("functionalMark").equals("3")) {
                c.eq("founderSCompany", securityUser.getCorpId());
            }
            if (map.get("functionalMark").equals("1")) {
                c.eq("founderId", securityUser.getUserId());
            }
//            c.eq("functionalMark",map.get("functionalMark"));
        }));
    }

    @Override
    public Advisorys findById(String Id) {
        return AdvisoryDao.findByArticleId(Id);
    }

    @Override
    public Advisorys save(Advisorys advisory) {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        advisory.setArticleId(UUIDUtil.uuid());
        advisory.setFounder(securityUser.getNickName());
        advisory.setFounderId(securityUser.getUserId());
        advisory.setFounderSCompany(securityUser.getCorpId());
        advisory.setReleaseDate(new Date());
        advisory.setStatus("登记");
        return AdvisoryDao.save(advisory);
    }

    @Override
    public void update(Advisorys advisory) {
        AdvisoryDao.dynamicSave(advisory, AdvisoryDao.findByArticleId(advisory.getArticleId()));
    }

    @Override
    public void deleteById(String Id) {
        AdvisoryDao.deleteById(Id);
    }

    @Override
    public boolean judgeUnique(Advisorys advisory) {
        return false;
    }

    /**
     * 首页咨询数据获取
     */
    @Override
    public Map<String, Object> getAdvisory(int limit) {
        Map<String, Object> rmap = new HashMap<String, Object>();
        //分类与返回json key的对照关系
        Map<String,String> keyAndClassifyMap = new HashMap<String,String>(){{
            put("学术论文","academicPaper");
            put("工艺知识","industryKnowledge");
            put("经验分享","experienceSharing");
            put("行业动态","industryDynamics");
            put("技术案例","technicalCase");
        }};
        //提出需要查询的分类
        String[] classifyList = CollectionUtil.sub(keyAndClassifyMap.keySet(),0,keyAndClassifyMap.size())
                .toArray(new String[0]);

        String where = SqlJoint.start().in("classify", classifyList).getAndSuffixSqlStr();
        String sql = " select * from(" +
                " select g_advisory.founder_id supply_id,article_id,title,classify,whether_to_pay,release_date, "
                + " ( '/system/upload/getImage?line_primary=' || image ) image_src, "
                + "  brief_introduction,content,status,founder,founder_id, "
                + " f_getname ( 'GETUSERNAME', founder_id, '', founder_s_company ) user_name,founder_s_company,annex,functional_mark, "
                +"  row_number() over(partition by classify order by release_date desc) row_num "
                + " from g_advisory "
                + " where status = '核准' "+where
                +" )tmp where  row_num <= "+ limit;
        List<Map<String,Object>> list = AdvisoryDao.QueryToMap(sql);

        //初始化返回对象中的key
        keyAndClassifyMap.values().forEach(key ->{
            rmap.put(key, new ArrayList<Map<String,Object>>(limit));
        });
        //遍历将返回对象填充
        list.stream().forEach(row ->{
            String classify = ObjectUtil.defaultIfNull(row.get("classify"),StrUtil.EMPTY).toString();
            if(keyAndClassifyMap.containsKey(classify) && rmap.containsKey(keyAndClassifyMap.get(classify))){
                ((ArrayList<Map<String,Object>>)rmap.get(keyAndClassifyMap.get(classify))).add(row);
            }
        });
        return rmap;
        //以下代码进行了多次查询，速度慢，不可取
        /*String sql = "select (SELECT   supply_id from  sys_user   where  user_id = g_advisory.founder_id) supplyId ,article_id \"articleId\",title,classify,whether_to_pay \"whetherToPay\",release_date \"releaseDate\",('/system/upload/getImage?line_primary='||image) \"imageSrc\",brief_introduction \"briefIntroduction\",content,status,founder,founder_id \"founderId\",f_getname('GETUSERNAME',founder_id,'',founder_s_company) \"userName\",founder_s_company \"founderSCompany\",annex,functional_mark \"functionalMark\" from g_advisory ";
        rmap.put("academicPaper", AdvisoryDao.SelectListMap(sql + " where classify = '学术论文' and status = '核准' order by release_date desc limit " + limit + " "));
        rmap.put("industryKnowledge", AdvisoryDao.SelectListMap(sql + " where classify = '工艺知识' and status = '核准'  order by release_date desc limit " + limit + " "));
        rmap.put("experienceSharing", AdvisoryDao.SelectListMap(sql + " where classify = '经验分享' and status = '核准'  order by release_date desc limit " + limit + " "));
        rmap.put("industryDynamics", AdvisoryDao.SelectListMap(sql + " where classify = '行业动态' and status = '核准'  order by release_date desc limit " + limit + " "));
        rmap.put("technicalCase", AdvisoryDao.SelectListMap(sql + " where classify = '技术案例' and status = '核准'  order by release_date desc limit 4 "));*/
        //return rmap;
    }

    /**
     * 公司内咨询数据获取
     */
    @Override
    public List<Map<String, Object>> getAdvisoryClassify(String classify, String limit, String corp) {
        Map<String, Object> rmap = new HashMap<String, Object>();
        String sql = "select article_id \"articleId\",title,classify,whether_to_pay \"whetherToPay\" ,release_date \"releaseDate\",('/system/upload/getImage?line_primary='||image) \"imageSrc\",brief_introduction \"briefIntroduction\",content,status,founder,founder_id \"founderId\",f_getname('GETUSERNAME',founder_id,'',founder_s_company) \"userName\",founder_s_company \"founderSCompany\",annex,functional_mark \"functionalMark\" from g_advisory ";
        return AdvisoryDao.SelectListMap(sql + " where classify = '" + classify + "' and status = '核准'  and founder_s_company = '" + corp + "' order by release_date desc limit " + limit);
    }

    /**
     * 咨询分页数据
     */
    @Override
    public Map<String, Object> getAdvisoryPage(String classify, String pageSize, String pageIndex) {
        Map<String, Object> rmap = new HashMap<String, Object>();
        String sql = " select article_id \"articleId\",title,classify,whether_to_pay \"whetherToPay\",to_char(release_date,'yyyy-MM-dd') \"releaseDate\",('/system/upload/getImage?line_primary='||image) \"imageSrc\",brief_introduction \"briefIntroduction\",content,status,founder,founder_id \"founderId\",founder_s_company \"founderSCompany\",annex,functional_mark \"functionalMark\" from g_advisory ";
        String where = " where classify = '" + classify + "' and status = '核准' limit " + pageSize + " offset (" + pageIndex + " -1 ) * " + pageSize;
        rmap.put("data", AdvisoryDao.SelectListMap(sql + where));
        rmap.put("total", AdvisoryDao.SelectListMap(sql + "where classify = '" + classify + "'").size());
        return rmap;
    }

    /**
     * academic.html数据
     */
    @Override
    public Map<String, Object> getOne(String article_id) {
        String sql = " select article_id \"articleId\",title,classify,whether_to_pay \"whetherToPay\",to_char(release_date,'yyyy-MM-dd') \"releaseDate\",('/system/upload/getImage?line_primary='||image) \"imageSrc\",brief_introduction \"briefIntroduction\",content,status,founder,founder_id \"founderId\",founder_s_company \"founderSCompany\",annex,functional_mark \"functionalMark\" from g_advisory where article_id = '" + article_id + "' ";
        return AdvisoryDao.SelectListMap(sql).get(0);
    }

    /**
     * academic.html推荐数据
     */
    @Override
    public List<Map<String, Object>> getRecommend(String founderId) {
        String sql = " select article_id \"articleId\",title,classify,whether_to_pay \"whetherToPay\",release_date \"releaseDate\",('/system/upload/getImage?line_primary='||image) \"imageSrc\",brief_introduction \"briefIntroduction\",content,status,founder,founder_id \"founderId\",founder_s_company \"founderSCompany\",annex,functional_mark \"functionalMark\" from g_advisory where founder_id = '" + founderId + "' order by release_date desc limit 6 ";
        return AdvisoryDao.SelectListMap(sql);
    }


    @SuppressWarnings("static-access")
    @Override
    public Result expertCaseDetail(String id) {
        Result result = Result.resultOk(AdvisoryDao.findById(id));
        String s = JSONObject.toJSONString(result);
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONObject data = jsonObject.getJSONObject("data");
        String annex = data.getString("annex");
        List<Map<String, Object>> annexName = advisorysService.getAnnexName(annex);
        return Result.resultOk(AdvisoryDao.findById(id)).put("annexName", annexName);

    }

    @Override
    public List<Map<String, Object>> getAnnexName(String annex) {
        return AdvisoryDao.queryFile(annex);
    }


}

