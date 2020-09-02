package com.tengzhi.business.platform.paysdk.buyproduct.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.paysdk.buyproduct.dao.BuyJPADao;
import com.tengzhi.business.platform.paysdk.buyproduct.model.GProductNotes;
import com.tengzhi.business.platform.paysdk.buyproduct.service.BuyService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-07-17
 */
@Transactional
@Service
public class BuyServiceimpl implements BuyService {


    @Autowired
    private BuyJPADao buyJPADao;
    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Override
    public Result BuyProductNoteSava(GProductNotes buyProduct) {
        SecurityUser securityUser=SessionUser.SessionUser();
        buyProduct.setNote(sysGenNoteService.getNote(GProductNotes.class,"CP","", 6));
        buyProduct.setOrderMan(securityUser.getUserId());
        buyProduct.setOrderManName(securityUser.getRealName());
        buyProduct.setOrderTime(new Date());
        buyJPADao.save(buyProduct);
        return Result.resultOk();
    }

    @Override
    public BasePage<GProductNotes> BuyProductFindAll(BaseDto baseDto) {
        Map<String, String> map=baseDto.getParamsMap();
        SecurityUser securityUser=SessionUser.SessionUser();
        List<Map<String,Object>> list=buyJPADao.QueryhumpMap("select * from sys_user a,g_product_notes b where a.supply_id=b.provider_id and a.user_id=?1",securityUser.getUserId());
        return buyJPADao.QueryPageList(baseDto, Specifications.where((e)->{
            e.contains("linkMan",map.get("linkMan"));
            e.contains("phone",map.get("phone"));
            e.between_common("orderTime",map.get("srchRq1"),map.get("srchRq2"));
            if(list.size()>0){
                e.eq("providerId",list.get(0).get("supplyId"));
            }else{
                e.eq("orderMan",securityUser.getUserId());
            }
        }));
    }

}
