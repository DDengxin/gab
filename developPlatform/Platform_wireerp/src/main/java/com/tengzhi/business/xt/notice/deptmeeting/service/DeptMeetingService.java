package com.tengzhi.business.xt.notice.deptmeeting.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.xt.notice.deptmeeting.model.DeptMeeting;

import java.util.Map;

public interface DeptMeetingService extends BaseService {
     BasePage<Map<String, Object>> getNotice(BaseDto baseDto);
     DeptMeeting save(DeptMeeting deptMeeting) throws Exception;
     void update(DeptMeeting DeptMeeting);
     Map<String, Object> findByNote(String note);
     void deleteByNoticeNo(String note);
}
