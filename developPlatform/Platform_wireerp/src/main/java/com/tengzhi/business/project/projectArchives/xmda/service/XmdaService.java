package com.tengzhi.business.project.projectArchives.xmda.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.project.projectArchives.xmda.model.EXmXmda;
import java.io.IOException;
import java.util.Map;

public interface XmdaService extends BaseService {

    BasePage<EXmXmda> findAll(BaseDto baseDto) throws IOException;

    EXmXmda save(EXmXmda eXmXmda) throws Exception;

    Map<String,Object> findByXmId(String xmId);

    void update(EXmXmda eXmXmda);

    void deleteByXmId(String xmId);

    Result getXmFlag(String xmId, String xmFlag);

    Result setxmFlag(String xmId, String xmFlag);
}
