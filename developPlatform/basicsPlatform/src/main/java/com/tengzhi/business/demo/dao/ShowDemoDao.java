package com.tengzhi.business.demo.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.demo.model.Test;

import java.util.List;


public interface ShowDemoDao extends BasedaoRepository<Test, String> {
}
