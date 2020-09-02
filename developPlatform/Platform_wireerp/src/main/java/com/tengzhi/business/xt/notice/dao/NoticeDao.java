package com.tengzhi.business.xt.notice.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.upload.model.SysUpload;
import com.tengzhi.business.xt.notice.model.EXtNotice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeDao extends BasedaoRepository<EXtNotice, String> {

	@Query(value = " select noticeFile from EXtNotice where noticeNo = :noticeNo and deleteLogo=false ")
	String getFileId(@Param("noticeNo") String noticeNo);

	@Query(value = " from SysUpload where line_primary = :line_primary")
	List<SysUpload> getUuid(@Param("line_primary") String line_primary);

	
	 @Query(value = "  from EXtNotice where noticeNo = :noticeNo and deleteLogo=false")
	 EXtNotice findByNoticeNo(@Param("noticeNo") String noticeNo);

	@Modifying
	@Query(value =" update EXtNotice set deleteLogo=true where noticeNo = :noticeNo ")
	int deleteID(@Param("noticeNo") String noticeNo);
	 
//	 @Query(value = "SELECT b.* , u.nick_name FROM ( SELECT e.notice_no \"noticeNo\", e.notice_rq \"noticeRq\", e.notice_theme \"noticeTheme\", e.notice_dept \"noticeDept\", e.notice_type \"noticeType\", e.notice_content \"noticeContent\", e.notice_file \"noticeFile\", e.notice_flag \"noticeFlag\", e.data_man \"dataMan\", e.data_date \"dataDate\", e.data_corp \"dataCorp\", d.dept_name FROM e_xt_notice e LEFT JOIN ( SELECT dept_id, dept_name FROM sys_dept ) d ON e.notice_dept = d.dept_id ) b LEFT JOIN ( SELECT user_id, nick_name FROM sys_user ) u ON u.user_id = b.\"dataMan\" WHERE noticeNo = :noticeNo")
//	 EXtNotice findByNoticeNo(@Param("noticeNo") String noticeNo);
	

	
}
