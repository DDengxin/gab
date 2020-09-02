package com.tengzhi.business.system.developarchive.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.developarchive.model.archiveDoc;

import java.util.List;
import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-08-01
 */
public interface archiveService {

    BasePage<Map<String, Object>> archiveDataFindAll(BaseDto baseDto);

    Result archiveDataSava(archiveDoc archiveDoc);

    Result archiveDataUpdate(archiveDoc archiveDoc);

    Result archiveByDataId(String uuid);

    Result archiveDataDelete(String uuid);

    Result archiveparentFindAll(BaseDto baseDto);

    List<SelectVo> Selectsubclass();

    List<SelectVo> Selectlabel();

    Result archiveByMendata(String menuid, String typestr);
}
