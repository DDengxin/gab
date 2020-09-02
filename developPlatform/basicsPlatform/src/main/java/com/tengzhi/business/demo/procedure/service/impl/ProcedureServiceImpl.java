package com.tengzhi.business.demo.procedure.service.impl;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.demo.procedure.dao.ProcedureDao;
import com.tengzhi.business.demo.procedure.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.StoredProcedureQuery;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-06-15
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class ProcedureServiceImpl implements ProcedureService {

    @Autowired
    private ProcedureDao procedureDao;

    /***
     * @return
     */
    @Override
    public Result ProcedureCall() {
        StoredProcedureQuery storedProcedureQuery= null;
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("DemoName","辽宁金帝科技有限公司");
            storedProcedureQuery = procedureDao.queryInitGeneral("getTable1",map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e);
        }
        return Result.resultOk(storedProcedureQuery.getResultList());
    }

    @Override
    public Result ProcedureCalls() {

        Integer nums = null;
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("num",Integer.valueOf(9));
            StoredProcedureQuery storedProcedureQuery= procedureDao.queryInitGeneral("sunNumber",map);
            nums = (Integer)storedProcedureQuery.getOutputParameterValue("sum");
            System.out.println("=========>>"+nums);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e);
        }
        return Result.resultOk(nums);
    }


}


