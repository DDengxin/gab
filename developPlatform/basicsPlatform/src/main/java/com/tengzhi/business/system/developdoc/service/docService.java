package com.tengzhi.business.system.developdoc.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.developdoc.model.developDoc;

/**
 * @author 鱼游浅水
 * @create 2020-08-01
 */
public interface docService {

    Result docDataFindAll(BaseDto baseDto);

    Result docDataSava(developDoc developDoc) throws Exception;

    Result docDataUpdate(developDoc developDoc);

    Result docByDataId(String doc_id);

    Result docDataDelete(String doc_id);
    
}
