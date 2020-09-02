package com.tengzhi.business.demo.procedure.dao.impl;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.business.demo.procedure.dao.ProcedureDao;
import com.tengzhi.business.demo.procedure.model.ProcedureModel;
import org.springframework.stereotype.Repository;

import javax.persistence.StoredProcedureQuery;
import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-06-15
 */
@Repository
public class ProcedureDaoImpl extends BasicsDaoImpl<ProcedureModel, String> implements ProcedureDao {

    @Override
    public StoredProcedureQuery queryInitGeneral(String themeCode,Map<String,Object> map) {
        return super.callStore(themeCode,map);
    }

}
