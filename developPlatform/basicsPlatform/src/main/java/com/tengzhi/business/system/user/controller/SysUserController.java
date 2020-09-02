package com.tengzhi.business.system.user.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.impl.BasePageImpl;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.upload.service.impl.SysUploadServiceImpl;
import com.tengzhi.business.system.user.model.SysUser;
import com.tengzhi.business.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/user/")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUploadServiceImpl sysUploadService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "user.html")
    public Result getList(BaseDto baseDto) throws IOException {
        return sysUserService.findAll(baseDto).getResult();
    }

    /**
     * 通过ID获取对象
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "user.html/{userId}")
    public Result getById(@PathVariable String userId) {
        return Result.resultOk(sysUserService.findByUserId(userId));
    }

    /**
     * 获取用户账套信息
     *
     * @return
     */
    @GetMapping(value = "userCorpInfo")
    public Result getUserCorpInfo() {
        return Result.resultOk(sysUserService.getUserCorpInfo());
    }

    /**
     * 修改用户操作账套和默认(登录)账套
     *
     * @return
     */
    @PostMapping(value = "userCorpInfo")
    public Result setUserCorpInfo(String defaultOrgId, String currentOrgId) {
        return Result.resultOk(sysUserService.setUserCorpInfo(defaultOrgId,currentOrgId));
    }


    @GetMapping(value = "bool.html/{userId}")
    public Result getAvatarUrl(@PathVariable String userId) {
        return Result.resultOk(sysUserService.getAvatarUrl(userId));
    }

    @PostMapping(value = "password.html")
    public Result PasswordUpdate(@RequestParam String newPassWord, @RequestParam String oldPassWord) {
        return sysUserService.updatePassword(newPassWord, oldPassWord);
    }

    @RequestMapping("getImg/{i}")
    @ResponseBody
    public String getImg(@PathVariable String i, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = sysUserService.ReadPath(i);
        if (path != null) {
            File file = new File(path);
            // 路径为文件且不为空
            if (file.isFile() && file.exists()) {
                ServletOutputStream out = null;
                FileInputStream ips = null;
                try {
                    // 获取图片存放路径
                    String imgPath = path;
                    ips = new FileInputStream(new File(imgPath));
                    response.setContentType("multipart/form-data");
                    out = response.getOutputStream();
                    // 读取文件流
                    int len = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((len = ips.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    out.close();
                    ips.close();
                }
            }
        }
        return null;
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping("add.html")
    public Result add(HttpServletRequest request, SysUser sysUser, @RequestParam(value = "SYS", required = false) MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            Map<String, Object> map = sysUploadService.uploadFile(file, request);
            sysUser.setFileUuid(map.get("data").toString());
        }
        sysUser = sysUserService.save(sysUser);
        return Result.resultOk(sysUser.getUserId());
    }

    /**
     * 修改
     *
     * @return
     */
    @PostMapping("updata")
    public Result modify(HttpServletRequest request, SysUser sysUser, @RequestParam(value = "SYS", required = false) MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            sysUploadService.deleteBylinePrimary(sysUser.getUserId());
            Map<String, Object> map = sysUploadService.uploadFile(file, request);
            sysUser.setFileUuid(map.get("data").toString());
        }
        sysUserService.update(sysUser);
        return Result.resultOk(sysUser.getUserId());
    }

    /**
     * 删除数据
     *
     * @return
     */
    @DeleteMapping(value = "/user.html")
    public Result delete(@RequestBody List<SysUser> list) {
        return sysUserService.deleteByUserId(list);
    }

    /**
     * @Param: [list]
     * @return: com.tengzhi.base.jpa.result.Result
     * @Author: 王翔
     * @Date: 2020/4/21 11:28
     * @description: 原生Sql语句常规新增
     */
    @PostMapping("userAuth_add.html")
    public Result UserAuthAdd(@RequestBody Map<String, Object> map) {
        sysUserService.saveUserAuth(map);
        return Result.resultOkMsg("授权成功!");
    }

    @PostMapping(value = "user_auth.html")
    public Result getRoleAuthAll(BaseDto baseDto){
        return BasePageImpl.valueOfList(sysUserService.findSysUserRightAll(baseDto)).getResult();
    }

}
