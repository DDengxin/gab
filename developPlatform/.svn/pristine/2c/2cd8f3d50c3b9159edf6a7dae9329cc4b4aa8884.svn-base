package com.tengzhi.business.finance.constituent.service.Impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.impl.BaseServiceImpl;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.constituent.dao.EPzJylistLhDao;
import com.tengzhi.business.finance.constituent.model.EPzJylistLh;
import com.tengzhi.business.finance.constituent.service.EPzJyListLhService;
import com.tengzhi.business.finance.constituent.vo.EPzJyListLhVo;
import com.tengzhi.business.finance.constituent.dao.EPzJyListDao;
import com.tengzhi.business.finance.constituent.model.EPzJyList;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.params.dao.SysParamDao;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.user.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class EPzJyListLhServiceImpl extends BaseServiceImpl implements EPzJyListLhService {

    @Autowired
    private EPzJylistLhDao ePzJylistLhDao;

    @Autowired
    SysParamDao sysParamDao;

    @Autowired
    private EPzJyListDao ePzJyListDao;


    @Override
    public Result findAll(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        BasePage<EPzJyList> ePzJyLists = ePzJyListDao.QueryPageList(baseDto, Specifications.where((c) -> {
            c.eq("codeType", map.get("codeType"));
            c.eq("pch", map.get("pch"));
        }));
        List<String> pchList = ePzJyLists.getContent()
                .stream()
                .map(row -> row.getPch())
                .collect(Collectors.toList());
        List ePzJylistLhList = ePzJylistLhDao.findByPchIn(pchList);


        return Result.resultOk().put("data", ePzJyLists.getContent()).put("total", ePzJyLists.getPageTotal()).put("detail", ePzJylistLhList);
    }


    @Override
    public Result findbyid(String pch) {

        EPzJyList ePzJyList = ePzJyListDao.findById(pch).orElse(null);
        List<EPzJylistLh> ePzJylistLhList =  ePzJylistLhDao.findByPch(pch);


        /*Map<String,List<Map<String,String>>> listMap = new HashMap<>();
        ePzJylistLhList.forEach(row ->{
            if(!listMap.containsKey(row.getJybh())){
                listMap.put(row.getJybh(),getPhTypeList(row.getJybh()));
            }
        });
        listMap.forEach( row ->{

        });*/


        Result result = Result.resultOk(ePzJyList);
        result.put("detail",ePzJylistLhList);
        return result;
    }

    @Override
    public List<Map<String, String>> getPhTypeList(String paramUid) {
        return sysParamDao.findAll(Specifications.where((c) -> {
           c.eq("paramMod", "品质");
            c.eq("parentId", paramUid);
            c.addOrderBy("paramOrd", Sort.Direction.ASC);
        })).stream().map(row -> {
            return new HashMap<String, String>() {{
                put("xmCode", row.getParamId());
                put("xmCodeName", row.getParamName());
                put("jybh", row.getParentId());
                put("xmOrd", String.valueOf(null == row.getParamOrd() ? 0 : row.getParamOrd()));
            }};
        }).collect(Collectors.toList());
    }


    @Override
    public Set<SelectVo> getPH(String codeType) {
        Set<SelectVo> select = new HashSet<>();
        ePzJylistLhDao.findAllParams("PHCS")
                .stream()
                .filter(item -> {
                    if(StringUtils.isNotBlank(codeType)){
                        return codeType.equals(item.getParamValue1());
                    }else{
                        return true;
                    }
                }).forEach(item -> {
                    select.add(new SelectVo(item.getParamId(), item.getParamName()));
                });
        return select;
    }

    @Override
    public List<SysParams> paramsAll(String id) {
        return ePzJylistLhDao.findAllParams(id);
    }


    @Override
    public Result save(EPzJyListLhVo EPzJyListLhVo) throws Exception {
        if (judgeUnique(EPzJyListLhVo.getConstituent().getPch())) {
            SessionUser sessionUser=SessionUser.SessionUser();
            EPzJyListLhVo.getGrid().forEach(item -> {
                item.setDataCorp(sessionUser.getCorpId());
                item.setDataDate(new Date());
                item.setDataMan(sessionUser.getUserId());
                item.setPch(EPzJyListLhVo.getConstituent().getPch());
            });

            ePzJyListDao.save(EPzJyListLhVo.getConstituent());
            ePzJylistLhDao.saveAll(EPzJyListLhVo.getGrid());

            return Result.resultOk();
        } else {
            throw new Exception("批次号已存在");
        }
    }


    @Override
    public void update(EPzJyListLhVo EPzJyListLhVo) {
        EPzJyListLhVo.getGrid().forEach(item -> {
            ePzJylistLhDao.update(item);
        });
    }

    @Override
    public void deleteByPch(String pch) {
        ePzJylistLhDao.deleteByPch(pch);
    }

    @Override
    public boolean judgeUnique(String pch) {
        return ePzJyListDao.findAll(Specifications.where((c) -> {
            c.eq("pch", pch);
        })).size() <= 0;
    }


    @Override
    public BasePage<Map<String, Object>> getProductSelectionList(BaseDto dto) throws IOException {
        Map<String, String> map = dto.getParamsMap();
        String pack = map.get("pack");
        String note = map.get("note");
        String sql = " select * from  f_v_product('" + pack + "','" + note + "') where 1=1";
        if (StringUtils.isNotBlank(map.get("type"))) {
            sql += " and  type = '" + map.get("type") + "' ";
        }
        return ePzJylistLhDao.QueryMapPageList(dto, sql, false);
    }

    @Override
    public List<SelectVo> getNote4Pack(String pack) {
        String sql = " select * from  f_v_product(?1,'') where 1=1";
        return ePzJylistLhDao.QueryListMap(sql, pack)
                .stream()
                .map(row -> new SelectVo(row.get("note"), String.valueOf(row.get("note")), row))
                .collect(Collectors.toList());
    }

    @Override
    public List<Map> getStandardValue(String codeType , String phType, String code) {
        //查询标准值
        String sql = " select lh.jybh,lh.xm_ord \"xmOrd\",lh.xm_code \"xmCode\",lh.xm_tvalue \"xmTvalue\",lh.xm_value \"xmValue\" from e_pz_jylist_lh lh "
                +" join  e_pz_jylist list on lh.pch = list.pch "
                +" where lh.data_corp = '"+ SessionUser.SessionUser().getCorpId() +"' and list.stype = '标准' "
                +"  and list.code_type = ?1 and lh.jybh = ?2 and list.code = ?3  ";
        return ePzJylistLhDao.QueryListMap(sql,codeType,phType,code);

    }
}
