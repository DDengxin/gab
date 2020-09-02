package com.tengzhi.business.sc.da.cardAbnormal.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sc.da.cardAbnormal.model.EHrAttendanceAbnormal;
import com.tengzhi.business.sc.da.cardAbnormal.model.EHrAttendanceAbnormalVo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/5 0005 11:33
 * @Description:
 */

public interface EHrAttendanceAbnormalService {

    BasePage<Map<String, Object>> findAll(BaseDto baseDto);

    List<SelectVo> findByKqTypeParam();

    Result getFlag(long sid,String flag);

    Result updateFlag(long sid,String flag);

    Result addEHrAttendanceAbnormal(int i,EHrAttendanceAbnormalVo eHrAttendanceAbnormalVo);

    Result updateEHrAttendanceAbnormal(int i,EHrAttendanceAbnormalVo eHrAttendanceAbnormalVo);

    Result addUpdateEHrAttendanceAbnormal(EHrAttendanceAbnormalVo vo);

    Long getMaxSid();

    Result findEHrAttendanceAbnormalById(long sid);

    List<SelectVo> findByKqyyParam();

    Result deleteByNote(String note);

    void deleteBySid(long sid);

    Result save(EHrAttendanceAbnormal eHrAttendanceAbnormal);

    Result update(EHrAttendanceAbnormal eh);

}
