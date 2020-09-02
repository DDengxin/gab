package com.tengzhi.business.sc.capacity.product.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.sc.capacity.specification.model.MScGgclVo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: huangbiao
 * @Date: 2020/8/13 0013 19:35
 * @Description:
 */

public interface ProductService {

    BasePage<Map<String, Object>> findAll(BaseDto baseDto);

}
