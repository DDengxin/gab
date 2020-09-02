package com.tengzhi.business.sc.da.productionStaff.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.sc.da.productionStaff.model.ProductionStaff;

/**
 * @Auther: huangbiao
 * @Date: 2020/8/1 0001 14:53
 * @Description:
 */

public interface ProductionStaffServie {

    String saveProductionStaff(ProductionStaff productionStaff);

    String deleteByUserId(String id);

    String updateByUserId(ProductionStaff productionStaff);

    ProductionStaff findPersonProductionStaffById(String id);

    BasePage<ProductionStaff> findProductionStaffAll(BaseDto baseDto);

//    List<SelectVo> findAllNotion();



}
