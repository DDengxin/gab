package com.tengzhi.business.platform.specialist.chamberactivities.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.business.platform.specialist.chamberactivities.dao.chamberDao;
import com.tengzhi.business.platform.specialist.chamberactivities.model.G_chamber;
import com.tengzhi.business.platform.specialist.chamberactivities.service.ChamberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;
@Service
@Transactional
public class ChamberServiceImpl implements ChamberService {
    @Autowired
    private chamberDao chamberDao;
    
	@SuppressWarnings("static-access")
	@Override
	public Result save(G_chamber g_chamber) {
		// TODO Auto-generated method stub
		Result  rs = new  Result();
		g_chamber.setChamberUserid(UUIDUtil.uuid());
		g_chamber.setRegister_time(new  Date());
		 chamberDao.save(g_chamber);
		 rs.resultOk("操作成功");		
		return  rs;
	}
	
	
	

    @Override
    public BasePage<G_chamber> findAll(BaseDto baseDto) throws Exception {
        Map<String, String> map = baseDto.getParamsMap();
       // SecurityUser securityUser= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return chamberDao.QueryPageList(baseDto, Specifications.where((c) -> {
            //c.contains("title", map.get("title"));
            c.eq("chamberNote",map.get("chamberNote"));//securityUser.getUserId()
        }));
    }

}
