package com.tengzhi.business.mSbJt.inspectionRecord.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.mSbJt.inspectionRecord.model.MSbInspection;
import com.tengzhi.business.mSbJt.inspectionRecord.vo.InspectionVo;
import com.tengzhi.business.resouces.vo.SelectVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface InspectionRecordService extends BaseService {
    //查询
    BasePage<Map<String,Object>> findAll(BaseDto baseDto) throws IOException;
   //获取一条记录
    MSbInspection findByInspectionSid(String inspectionSid);
    //新增Grid
    BasePage<Map<String, Object>> getAddGrid(BaseDto baseDto);
    //修改
    Result update(InspectionVo inspectionVo)throws Exception;
    //保存
    Result save(InspectionVo inspectionVo) throws Exception;
    //删除
    void deleteByNote(String inspectionNote);
}