package com.tengzhi.business.system.pda.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.dept.model.SysDept;
import com.tengzhi.business.system.pda.model.SysAppVersion;
import org.springframework.data.repository.query.Param;

public interface SysAppVersionDao extends BasedaoRepository<SysAppVersion,String> {

    SysAppVersion findByUuid(String uuid);

    void deleteByUuid(String uuid);
}
