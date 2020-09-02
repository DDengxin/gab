package com.tengzhi.business.sc.capacity.specification.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sc.capacity.specification.model.MScGgclVo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: huangbiao
 * @Date: 2020/8/12 0012 14:50
 * @Description:
 */

public interface MScGgclService {

    BasePage<Map<String, Object>> findAll(BaseDto baseDto);

    List<Map<Object,String>> findCjType();

    List<Map<Object,String>> findClType();

    Result findByClId(String clId);

    Result deleteByClId(String clId);

    Result OptionMscGgcl(MScGgclVo vo);

}
