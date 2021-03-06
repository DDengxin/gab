package com.tengzhi.business.xt.notice.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.xt.notice.model.EXtNotice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface NoticeService {

	EXtNotice save(EXtNotice eXtNotice) throws Exception;

	boolean judgeUnique(EXtNotice eXtNotice);

	void deleteByNoticeNo(String noticeNo);

	Map<String,Object> findByNoticeNo(String noticeNo);

	void update(EXtNotice eXtNotice);

	boolean sure(String noticeNo);

	boolean cancel(String noticeNo);

	BasePage<Map<String,Object>> getNotice(BaseDto baseDto) throws IOException;

	List<EXtNotice> getNoticeEight();

	Map<String,Object> getDes();
}
