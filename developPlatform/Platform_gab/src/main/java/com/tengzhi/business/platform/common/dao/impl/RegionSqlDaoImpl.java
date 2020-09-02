package com.tengzhi.business.platform.common.dao.impl;


import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.business.platform.common.dao.OperatorDao;
import com.tengzhi.business.platform.common.dao.RegionSqlDao;
import com.tengzhi.business.platform.common.model.RegionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionSqlDaoImpl extends BasicsDaoImpl<RegionVo, String> implements RegionSqlDao {

    @Autowired
    OperatorDao operatorDao;


    @Override
    public  List<RegionVo> getRegion() {
        String sql = "select param_id as id,param_name as name,parent_id as pid from  sys_param  where\n" +
                "param_mod='销售' and param_type='区域城市' \n" +
                "and param_status='true' AND parent_id NOT IN('JPO','HW','DJ1') AND param_id NOT IN('DJ1') AND parent_id='GN'";
        List<RegionVo>  regionVo = operatorDao.QueryToVo(RegionVo.class, sql);
        return regionVo;
    }

    @Override
    public List<RegionVo> findChildren(String id) {
        String sql = "select param_id as id,param_name as name,parent_id as pid from  sys_param  where\n" +
                "param_mod='销售' and param_type='区域城市' \n" +
                "and param_status='true' AND parent_id NOT IN('JPO','HW','DJ1') AND param_id NOT IN('DJ1') AND parent_id='"+id+"'";
        List<RegionVo>  regionVo = operatorDao.QueryToVo(RegionVo.class, sql);
        return  regionVo;
    }
}
