package com.tengzhi.business.sc.da.cardAbnormal.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sc.da.cardAbnormal.model.EHrAttendanceAbnormal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangbiao
 * @Date: 2020/8/5 0005 11:33
 * @Description:
 */

@SuppressWarnings("SqlResolve")
public interface EHrAttendanceAbnormalDao extends BasedaoRepository<EHrAttendanceAbnormal,Long> {

    @Query(value="select * from sys_param where parent_id = 'KQLB'",nativeQuery=true)
    List<Map<Object,String>> findByKqTypeParam();

    @Query(value="select * from e_hr_attendance_abnormal where sid = ?1",nativeQuery=true)
    EHrAttendanceAbnormal findFlag(long sid);

    @Modifying
    @Transactional
    @Query(value="update e_hr_attendance_abnormal set flag = ?1 where sid = ?2",nativeQuery=true)
    int updateFlag(String flag,long sid);

    @Query(value="select max(sid) from e_hr_attendance_abnormal")
    Long getMaxSid();

    @Query(value="select * from e_hr_attendance_abnormal where sid = ?1",nativeQuery=true)
    EHrAttendanceAbnormal findEHrAttendanceAbnormalById(long sid);

    //考勤原因参数查询
    @Query(value="select * from sys_param where parent_id = 'KQYY'",nativeQuery=true)
    List<Map<Object,String>> findByKqyyParam();

    @Modifying
    @Transactional
    @Query(value="delete from e_hr_attendance_abnormal where note = ?1",nativeQuery=true)
    int deleteByNote(String note);

}
