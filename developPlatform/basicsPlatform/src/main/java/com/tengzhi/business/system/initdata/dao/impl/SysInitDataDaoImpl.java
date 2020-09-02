package com.tengzhi.business.system.initdata.dao.impl;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.initdata.model.SysDbDo;
import com.tengzhi.business.system.initdata.model.SysDbDo.SysDbDoPK;

import javax.persistence.StoredProcedureQuery;
import java.util.Map;

public interface SysInitDataDaoImpl extends BasedaoRepository<SysDbDo, SysDbDoPK>  {
}
