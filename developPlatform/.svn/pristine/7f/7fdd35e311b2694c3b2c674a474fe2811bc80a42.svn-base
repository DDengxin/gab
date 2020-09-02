package com.tengzhi.business.sc.capacity.specification.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.sc.capacity.specification.dao.MScGgclDao;
import com.tengzhi.business.sc.capacity.specification.model.MScGgcl;
import com.tengzhi.business.sc.capacity.specification.model.MScGgclVo;
import com.tengzhi.business.sc.capacity.specification.service.MScGgclService;
import com.tengzhi.business.sc.da.cardAbnormal.model.EHrAttendanceAbnormal;
import com.tengzhi.business.sc.da.cardAbnormal.model.EHrAttendanceAbnormalVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/12 0012 14:50
 * @Description:
 */

@Service
@Transactional(rollbackFor = Exception.class)//发生异常事务回滚
public class MScGgclServiceImpl implements MScGgclService {

    private MScGgclDao mScGgclDao;

    private SysGenNoteService sysGenNoteService;

    public MScGgclServiceImpl(MScGgclDao mScGgclDao, SysGenNoteService sysGenNoteService) {
        this.mScGgclDao = mScGgclDao;
        this.sysGenNoteService = sysGenNoteService;
    }

    /**
     *@role:
     *@Author:      huangbiao
     *@Date:        2020/8/12 0012 14:53
     *@Param:       [baseDto]
     *@return:      com.tengzhi.base.jpa.result.Result
     *@Description: 查询所有规格产品
     **/  @Override
    public BasePage<Map<String, Object>> findAll(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String whereJudge = SqlJoint.whereSuffixWhere((c) ->{
            c.eq("data_corp", SessionUser.getCurrentCorpId());
            c.contains("cl_id",map.get("clId"));
            c.contains("cl_cj",map.get("clCj"));
            c.contains("ggcl_size",map.get("ggclSize"));
            c.contains("cl_type",map.get("cnStype"));
        });
        String sqlString = "select cl_id,f_getparamname('GETTYPEBYRAMNAME',cl_cj,'生产车间','','','"+SessionUser.getCurrentCorpId()+"') clCjName,cl_code,ggcl_size,size_min,size_max,cl_bcl,cl_rbs,cl_rcl,cl_yts,cl_ycl," +
                "data_date,data_man,f_getname('GETUSERNAME',data_man,'',data_corp) data_man_name,(select string_agg( param_name,',') from sys_param c where strpos(a.cl_code,c.param_id)>0 and  c.param_mod='技术'  ) codename " +
                " from m_sc_ggcl a "+whereJudge;
        return mScGgclDao.QueryMapPageList(baseDto, sqlString+" order by data_date desc,cl_id desc ", true);
    }

    /**
     *@role:
     *@Author:      huangbiao
     *@Date:        2020/8/12 0012 14:54
     *@Param:       []
     *@return:      com.tengzhi.base.jpa.result.Result
     *@Description: 查询所有车间类型
     **/
    @Override
    public List<Map<Object,String>> findCjType() {
        List<Map<Object, String>> cjTypeList = mScGgclDao.findCjType();
        return cjTypeList;
    }

    /**
     *@role:
     *@Author:      huangbiao
     *@Date:        2020/8/13 0013 15:11
     *@Param:       []
     *@return:      java.util.List<java.util.Map<java.lang.Object,java.lang.String>>
     *@Description: 查找所有产能类型
     **/
    @Override
    public List<Map<Object, String>> findClType() {
        List<Map<Object, String>> clTypeList = mScGgclDao.findClType();
        return clTypeList;
    }

    /**
     *@role:
     *@Author:      huangbiao
     *@Date:        2020/8/13 0013 15:11
     *@Param:       [clId]
     *@return:      com.tengzhi.base.jpa.result.Result
     *@Description: 查找指定产能数据
     **/
    @Override
    public Result findByClId(String clId) {
        MScGgcl mScGgcl = mScGgclDao.findById(clId).orElse(null);
        return Result.resultOk(mScGgcl);
    }


    /**
     *@role:
     *@Author:      huangbiao
     *@Date:        2020/8/13 0013 14:47
     *@Param:       [vo]
     *@return:      com.tengzhi.base.jpa.result.Result
     *@Description: 对MscGgcl的增删改
     **/
    @Override
    public Result OptionMscGgcl(MScGgclVo vo){
        int len = vo.getEntitydata().size();
        SessionUser seruser = SessionUser.SessionUser();
        try{
            for (int i = 0; i < len; i++) {
                String state = vo.getEntitydata().get(i).get_state();
                if("added".equals(state)){
                    vo.getEntitydata().get(i).setDataMan(seruser.getUserId());
                    vo.getEntitydata().get(i).setDataDate(new Date());
                    String clId = sysGenNoteService.getNote(MScGgcl.class,  vo.getHeaddata().getClType(), "yyMMdd", 3);
                    vo.getEntitydata().get(i).setClType(vo.getHeaddata().getClType());
                    vo.getEntitydata().get(i).setClCj(vo.getHeaddata().getClCj());
                    vo.getEntitydata().get(i).setClId(clId);
                    vo.getEntitydata().get(i).setDataCorp(seruser.getCorpId());
                    mScGgclDao.save(vo.getEntitydata().get(i));
                }else if("modified".equals(state)){
                    vo.getEntitydata().get(i).setDataDate(new Date());
                    vo.getEntitydata().get(i).setDataMan(seruser.getUsername());
                    vo.getEntitydata().get(i).setClType(vo.getHeaddata().getClType());
                    vo.getEntitydata().get(i).setClCj(vo.getHeaddata().getClCj());
                    mScGgclDao.dynamicSave(vo.getEntitydata().get(i),mScGgclDao.findById(vo.getEntitydata().get(i).getClId()).orElse(null));
                }else if("removed".equals(state)){
                    mScGgclDao.deleteById(vo.getEntitydata().get(i).getClId());
                }
            }
        }catch (Exception e){
            return Result.resultOkMsg("保存失败");
        }
        return Result.resultOkMsg("保存成功");
    }


    @Override
    public Result deleteByClId(String clId){
        mScGgclDao.deleteById(clId);
        Result result = new Result();
        return result.resultOkMsg("删除成功");
    }
}
