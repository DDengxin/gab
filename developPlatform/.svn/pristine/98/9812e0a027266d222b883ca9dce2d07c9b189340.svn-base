package com.tengzhi.business.sc.capacity.product.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.sc.capacity.product.dao.ProductDao;
import com.tengzhi.business.sc.capacity.specification.model.MScGgcl;
import com.tengzhi.business.sc.capacity.specification.model.MScGgclVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/13 0013 19:35
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)//发生异常事务回滚
public class ProductionServiceImpl implements ProductService {

    private ProductDao productDao;

    private SysGenNoteService sysGenNoteService;

    public ProductionServiceImpl(ProductDao productDao, SysGenNoteService sysGenNoteService) {
        this.productDao = productDao;
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
            c.eq("cl_code",map.get("clCode"));
            c.contains("cl_cj",map.get("clCj"));
            c.contains("ggcl_size",map.get("ggclSize"));
            c.contains("cl_type",map.get("cnStype"));
        });
        String sqlString = "select cl_id,f_getparamname('GETBYCPCODEFL',cl_code,'','技术','"+map.get("cpcodeType")+"',data_corp) cpcode_fl_name,f_getparamname('GETTYPEBYRAMNAME',cl_cj,'生产车间','','','"+SessionUser.getCurrentCorpId()+"') clCjName,cl_code,ggcl_size,size_min,size_max,cl_bcl,cl_rbs,cl_rcl,cl_yts,cl_ycl," +
                "data_date,data_man,f_getname('GETUSERNAME',data_man,'',data_corp) data_man_name,(select string_agg( param_name,',') from sys_param c where strpos(a.cl_code,c.param_id)>0 and  c.param_mod='技术'  ) codename" +
                " from m_sc_ggcl a "+whereJudge;
        return productDao.QueryMapPageList(baseDto, sqlString+" order by data_date desc,cl_id desc ", true);
    }
}
