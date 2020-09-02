package com.tengzhi.business.tooling.toolingstore.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;

import java.io.IOException;
import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-06-28
 */
public interface ToolingStoreDaoService {

    Result findAll(BaseDto baseDto) throws IOException;

    Result querySingleById(BaseDto baseDto);

    Result queryAllOutboundStorage(BaseDto baseDto);

    Result toolingSave(Map<String,Object> map)throws Exception;

    Result toolingUpdate(Map<String,Object> map);

    Result toolingDelete(String Note);

    Result toolingconfirm(Map<String,Object> map);

    Result enquiryRegistration(String Act);

    Result enquiryRegistrationOK(Map<String,String> obj);

}
