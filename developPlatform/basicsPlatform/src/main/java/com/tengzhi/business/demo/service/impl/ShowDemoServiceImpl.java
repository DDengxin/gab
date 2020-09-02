package com.tengzhi.business.demo.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.demo.dao.ShowDemoDao;
import com.tengzhi.business.demo.service.ShowDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class ShowDemoServiceImpl implements ShowDemoService {
    @Autowired
    private ShowDemoDao showDemoDao;


    @Override
    public Result find(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere((c) ->{
            c.eq("nick_name",map.get("nickName"));
		});
        String sql = " select * from sys_demo_test " + where;
        return showDemoDao.QueryMapPageList(baseDto, sql, true).getResult();
        //return showDemoDao.QueryToMapPage(baseDto,sql).getResult();

		/*return Result.formPage(showDemoDao.findAll(Specifications.where((c) ->{

		})));*/

    }
}
