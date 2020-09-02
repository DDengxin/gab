package com.tengzhi.business.tooling.grantandrecover.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.vo.SelectVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-06-28
 */
public interface dispatcherService {

    Result findAll(BaseDto baseDto) throws IOException;

    Result querySingleById(BaseDto baseDto) throws IOException;

    Result queryAllOutboundStorage(BaseDto baseDto);

    Result toolingSave(Map<String,Object> map)throws Exception;

    Result toolingUpdate(Map<String,Object> map);

    Result toolingDelete(String Note);

    Result toolingconfirm(Map<String,Object> map) throws Exception;

    Result enquiryRegistration(String Act);

    List<SelectVo> SELECT_VOS();
}
