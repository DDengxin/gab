package com.tengzhi.business.sc.finishedPicking.notice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.finishedPicking.notice.dao.finishedPickingNoticeDao;
import com.tengzhi.business.sc.finishedPicking.notice.service.finishedPickingNoticeService;

@Service
public class finishedPickingNoticeServiceImpl implements finishedPickingNoticeService {
	@Autowired
	private finishedPickingNoticeDao noticeDao;

	@Override
	public List<Map<String, Object>> findOrderNo(String type) {
		return noticeDao.findOrderNo(type);
	}

	@Override
	public Result findSerialNumber(BaseDto dto) {
		return noticeDao.findSerialNumber(dto);
	}

}
