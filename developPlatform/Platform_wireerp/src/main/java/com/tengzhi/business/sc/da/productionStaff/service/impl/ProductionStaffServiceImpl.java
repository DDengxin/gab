package com.tengzhi.business.sc.da.productionStaff.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.sc.da.productionStaff.dao.ProductionStaffDao;
import com.tengzhi.business.sc.da.productionStaff.model.ProductionStaff;
import com.tengzhi.business.sc.da.productionStaff.service.ProductionStaffServie;
import com.tengzhi.business.system.user.service.Impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/1 0001 14:52
 * @Description:
 */

@Service
public class ProductionStaffServiceImpl implements ProductionStaffServie {

    @Autowired
    private ProductionStaffDao productionStaffDao;

    @Override
    public String saveProductionStaff(ProductionStaff productionStaff) {

        productionStaff.setUserId(productionStaff.getJobNumber());
        productionStaff.setPassword(String.valueOf(System.currentTimeMillis()));
        try {
            productionStaff= productionStaffDao.save(productionStaff);
            if (ObjectUtil.isNotEmpty(productionStaff)){
                return "保存成功";
            }
            return "保存失败";
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("保存失败");
            return "保存失败";
        }
    }

    @Override
    public String deleteByUserId(String id) {
        //当id为非null并非空值时满足条件
        if(!StrUtil.isNullOrUndefined(id)){
            try{
                productionStaffDao.deleteById(id);
            }catch(EmptyResultDataAccessException e){
                e.printStackTrace();
                return "删除失败";
            }
            return "删除成功";
        }
        return "删除失败";
    }

    @Override
    public String updateByUserId(ProductionStaff productionStaff) {
        ProductionStaff saveResult = productionStaffDao.save(productionStaff);
        if (ObjectUtil.isNotEmpty(saveResult)){
            return "修改成功";
        }
        return "修改失败";
    }

    @Override
    public ProductionStaff findPersonProductionStaffById(String id) {
        return productionStaffDao.findById(id).orElse(null);
    }

    @Override
    public BasePage<ProductionStaff> findProductionStaffAll(BaseDto baseDto){


        Map<String, String> map = baseDto.getParamsMap();
        return productionStaffDao.QueryPageList(baseDto, Specifications.where((c) -> {
            c.contains("realName", map.get("realName"));
            c.contains("jobNumber",map.get("jobNumber"));
            c.contains("positionName","生产人员");
            c.contains("orgId", SessionUser.SessionUser().getCorpId());
            c.eq("isForbidden",false);
        }));




        /*if (jobNumber.isEmpty()){
            jobNumber = "%";
        }else if (realName.isEmpty()){
            realName = "%";
        }
        System.out.println("参数"+jobNumber+","+realName);
        return productionStaffDao.findProductionStaffAll(jobNumber,realName,"生产人员");//生产人员名单*/
    }

/*    @Override
    public List<SelectVo> findAllNotion() {
        List<SelectVo> ResultNotiveList = new ArrayList<>();
        personnelFileDao.findAllNotion().forEach((k)->ResultNotiveList.add(new SelectVo(k,k)));
        return ResultNotiveList;
    }*/

}
