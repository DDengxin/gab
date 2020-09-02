package com.tengzhi.business.system.library.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.library.model.Library;

import java.util.List;

public interface LibraryService {
    Result findAll(BaseDto dto);

    Result save(Library library) throws Exception;

    Result findByArUuid(String arUuid);

    void update(Library library);

    void delete(String arNote);

    void updateNewEst(boolean arNewest, String arUuid);

    List<SelectVo> getVersion(String arNote, String cropid);
}
