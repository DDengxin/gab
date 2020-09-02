package com.tengzhi.business.system.pda.controller;

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.annotations.Anonymous;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.dept.model.SysDept;
import com.tengzhi.business.system.pda.model.SysAppVersion;
import com.tengzhi.business.system.pda.service.impl.SysAppVersionServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.TextUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/system/pda/")
public class SysAppVersionController {
    @Autowired
    private SysAppVersionServiceImpl sysAppVersionService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "list")
    @Log("查询app版本管理")
    public Result getList(BaseDto baseDto) throws IOException {
        return sysAppVersionService.findAll(baseDto).getResult();
    }

    @GetMapping("/getPackageSelectList")
    public Set<SelectVo> getPackageSelectList() {
        return sysAppVersionService.getPackageSelectList();
    }

    @GetMapping("/getPlatformSelectList")
    public Set<SelectVo> getPlatformSelectList() {
        return sysAppVersionService.getPlatformSelectList();
    }

    /**
     * 通过ID获取对象
     *
     * @param uuid
     * @return
     */
    @GetMapping(value = "pda.html/{uuid}")
    public Result getById(@PathVariable String uuid) {
        return Result.resultOk(sysAppVersionService.findByUuId(uuid));
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("add.html")
    public Result add(@RequestBody SysAppVersion sysAppVersion) throws Exception {
        sysAppVersion = sysAppVersionService.save(sysAppVersion);
        return Result.resultOk(sysAppVersion.getUuid());
    }

    /**
     * 修改和新增
     *
     * @return
     */
    @PutMapping("add.html")
    public Result modify(@RequestBody SysAppVersion sysAppVersion) throws Exception {
        //修改
        if (!StringUtils.isBlank(sysAppVersion.getUuid())) {
            if (sysAppVersion.isAppFlag()==false){
                sysAppVersionService.update(sysAppVersion);
            }else {
                Result updateResult = sysAppVersionService.updateVersionFlag(sysAppVersion.getAppPackage(), sysAppVersion.getPlatform());
                if (Integer.parseInt(updateResult.get("code")+"")==200){
                    sysAppVersionService.update(sysAppVersion);
                }
            }
        //新增
        }else {
            if (sysAppVersion.isAppFlag()==false){
                sysAppVersion = sysAppVersionService.save(sysAppVersion);
            }else {
                Result updateResult = sysAppVersionService.updateVersionFlag(sysAppVersion.getAppPackage(), sysAppVersion.getPlatform());
                if (Integer.parseInt(updateResult.get("code")+"")==200){
                    sysAppVersion = sysAppVersionService.save(sysAppVersion);
                }
            }
        }
        return Result.resultOk(sysAppVersion.getUuid());
    }

    /**
     * 删除数据
     *
     * @return
     */
    @DeleteMapping(value = "pda.html/{uuid}")
    public Result delete(@PathVariable(value = "uuid") String uuid) {
        return sysAppVersionService.deleteByUuid(uuid);
    }

    /**
     * 根据包名和平台获取版本信息
     * @return
     */
    @PostMapping("getAppVersionInfo")
    public Map<String, Object> getAppVersionInfo(String appPackage,String platform) throws Exception {
        return sysAppVersionService.getAppVersionInfo(appPackage,platform);
    }
}
