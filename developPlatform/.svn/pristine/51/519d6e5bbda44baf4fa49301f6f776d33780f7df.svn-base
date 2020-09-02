package com.tengzhi.business.finance.checkout.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.checkout.vo.SysVo;
import com.tengzhi.business.system.params.model.SysParams;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CheckoutService extends BaseService  {


    Result findAll(BaseDto baseDto)  throws IOException;


    List<SysParams> paramsAll(String name);

    Result save(SysVo sysVo) throws Exception;

    void update(SysVo sysVo);

    void del(String luhao);

    boolean judgeUnique(String luhao);

    Result findbyid(String luhao);

    List<Map<String,String>> getParamList(String paramUid);

    List<Map> findpch( String key);

}
