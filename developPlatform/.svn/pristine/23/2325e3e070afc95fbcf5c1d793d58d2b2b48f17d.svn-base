package com.tengzhi.business.xt.notice.deptmeeting.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.upload.model.SysUpload;
import com.tengzhi.business.xt.notice.deptmeeting.model.DeptMeeting;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeptMeetingDao extends BasedaoRepository<DeptMeeting, String> {
    @Query(value = "  from DeptMeeting where note = :note ")
    DeptMeeting findByNote(@Param("note") String note);
    @Query(value = " from SysUpload where line_primary = :line_primary")
    List<SysUpload> getUuid(@Param("line_primary") String line_primary);
    @Query(value = " select noticeFile from EXtNotice where noticeNo = :noticeNo and deleteLogo=false ")
    String getFileId(@Param("noticeNo") String noticeNo);
    @Modifying
    @Query(value =" update EXtNotice set deleteLogo=true where noticeNo = :noticeNo ")
    int deleteID(@Param("noticeNo") String noticeNo);

}
