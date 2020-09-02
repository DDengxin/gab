package com.tengzhi.business.platform.common.dao;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.business.platform.common.model.RegionVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionSqlDao extends BasicsDao<RegionVo,String> {
   List<RegionVo> getRegion();
   List<RegionVo> findChildren(String id);
}
