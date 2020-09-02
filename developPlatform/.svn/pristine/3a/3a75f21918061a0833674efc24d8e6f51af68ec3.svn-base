package com.tengzhi.business.system.initdata.dao.impl;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.business.system.initdata.dao.SysInitDataDao;
import com.tengzhi.business.system.initdata.dao.SysInitDataDaoBasic;
import com.tengzhi.business.system.initdata.model.SysDbDo;
import com.tengzhi.business.system.initdata.model.SysDbDo.SysDbDoPK;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.Map;

@Repository
public class SysInitDataDaoBasicImpl extends BasicsDaoImpl<SysDbDo, SysDbDoPK> implements SysInitDataDaoBasic {
    @PersistenceContext
    private EntityManager em;

    @Override
    public StoredProcedureQuery callSolveDataProduce(String themeCode,Map<String,Object> map) {
        return super.callStore(themeCode,map);
    }
/**
 *
 * 数据初始化，PG数据库存储过程不能有返回值，这个一定注意，调用的需要使用call关键字
 */
    public void callInitDataProduce(String pname,Map<String,Object> map) {
        Query query = em.createNativeQuery(" call p_sys_datainit(?) ");
        query.setParameter(1, map.get("datacorp"));
        query.executeUpdate();
    }




}


