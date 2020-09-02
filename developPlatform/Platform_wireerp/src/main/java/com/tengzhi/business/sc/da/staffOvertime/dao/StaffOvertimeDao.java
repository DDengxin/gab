package com.tengzhi.business.sc.da.staffOvertime.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.da.staffOvertime.model.StaffOvertime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @Auther: huangbiao
 * @Date: 2020/8/4 0004 18:40
 * @Description:
 */

public interface StaffOvertimeDao extends BasedaoRepository<StaffOvertime,String> {

    @Modifying
    @Transactional
    @Query(value="update e_hr_jbqj set qj_flag = ?2 where jbqj_id =?1",nativeQuery=true)
    int updateFlag(String jbqjId, String qjFlag);

}
