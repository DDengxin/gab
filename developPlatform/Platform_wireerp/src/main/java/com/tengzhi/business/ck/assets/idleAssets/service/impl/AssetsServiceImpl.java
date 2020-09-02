package com.tengzhi.business.ck.assets.idleAssets.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.ck.assets.idleAssets.dao.AssetsDao;
import com.tengzhi.business.ck.assets.idleAssets.model.ECkAssets;
import com.tengzhi.business.ck.assets.idleAssets.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @Author: czf
 * @Date:2020-08-19 9:32
 */
@Service
@Transactional
public class AssetsServiceImpl implements AssetsService {

    @Autowired
    private AssetsDao assetsDao;

    @Override
    public BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws IOException {
        Map<String,String> map = baseDto.getParamsMap();
        String where  = SqlJoint.whereSuffixWhere(c->{
            c.ge("zc_rq",map.get("srchRq1"));
            c.le("zc_rq",map.get("srchRq2"));
            c.eq("zc_name",map.get("srchName"));
            c.eq("zc_type",map.get("srchType"));
            c.eq("zc_flag",map.get("srchFlag"));
        });
        String sql = "select sid,to_char(zc_rq,'yyyy-MM-dd')zc_rq,zc_no,zc_name,zc_type,zc_ksize,zc_sl,zc_price,zc_je,zc_sm,zc_from,zc_flag," +
                "(select line_primary from com_file f where f.line_primary=e.zc_file)zc_file," +
                "data_corp,data_man,to_char(data_date,'yyyy-MM-dd hh24:mi')data_date from e_ck_assets e "+where;
        return assetsDao.QueryMapPageList(baseDto,sql,true);
    }

    @Override
    public ECkAssets save(ECkAssets eCkAssets) throws Exception {
        SessionUser sessionUser = SessionUser.SessionUser();
        eCkAssets.setDataCorp(sessionUser.getCorpId());
        eCkAssets.setDataDate(new Date());
        eCkAssets.setDataMan(sessionUser.getUserId());
        String sid = assetsDao.getSingleResult("select max(sid) from e_ck_assets");
        int num = (sid==""|| sid==null?0:Integer.parseInt(sid))+1;
        eCkAssets.setSid(Integer.toString(num));
        return assetsDao.save(eCkAssets);
    }

    @Override
    public void update(ECkAssets eCkAssets) {
        SessionUser s = SessionUser.SessionUser();
        eCkAssets.setDataCorp(s.getCorpId());
        eCkAssets.setDataDate(new Date());
        eCkAssets.setDataMan(s.getUserId());
        assetsDao.update(eCkAssets);
    }

    @Override
    public Map<String, Object> findById(String sid) {
        return assetsDao.QueryToFirstMap("select sid,to_char(zc_rq,'yyyy-MM-dd')zc_rq,zc_no,zc_name,zc_type,zc_ksize,zc_sl,zc_price,zc_je,zc_sm,zc_from,zc_flag,zc_file,"
                +"data_corp,data_man,to_char(data_date,'yyyy-MM-dd hh24:mi')data_date from e_ck_assets where sid='"+sid+"'");
    }

    @Override
    public void deleteById(String sid) {
        assetsDao.deleteById(sid);
    }

    @Override
    public Result updateFlag(String sid) {
        assetsDao.updateFlag(sid );
        return Result.resultOkMsg("状态修改");
    }
}
