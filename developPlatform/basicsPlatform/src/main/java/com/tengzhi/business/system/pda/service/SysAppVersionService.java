package com.tengzhi.business.system.pda.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.dept.model.SysDept;
import com.tengzhi.business.system.pda.dao.SysAppVersionDao;
import com.tengzhi.business.system.pda.model.SysAppVersion;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface SysAppVersionService extends BaseService {

    Page<SysAppVersion> findAll(BaseDto baseDto) throws IOException;

    Set<SelectVo> getPackageSelectList();

    Set<SelectVo> getPlatformSelectList();

    SysAppVersion save(SysAppVersion sysAppVersion) throws Exception;

    void update(SysAppVersion sysAppVersion) throws Exception;

    SysAppVersion findByUuId(String uuId);

    Result deleteByUuid(String uuid);

    int getCount(String appPackage, String platform, boolean appFlag,Date date);

    Map<String, Object> getAppVersionInfo(String appPackage, String platform);

    Result updateVersionFlag(String appPackage,String platform);
}
