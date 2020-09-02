package com.tengzhi.business.system.setting.controller;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.annotations.Anonymous;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.setting.model.SysSet;
import com.tengzhi.business.system.setting.service.SysSetService;
import com.tengzhi.business.system.upload.service.SysUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/setting/")
public class SysSetController {

    @Autowired
    private SysSetService sysSetService;


    @Autowired
    private SysUploadService sysUploadService;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        mv = sysSetService.modelAndView(mv);
        return mv;
    }

    @GetMapping(value = "getSetting")
    public List<Map> setting(@RequestParam(required = false) String corp) {
        return sysSetService.getSetting(corp);
    }

    @GetMapping(value = "combobox/params")
    public List<SelectVo> ComboboxDept(String content) {
        return sysSetService.findComboboxParams(content);
    }

    /**
     * 通过请求获取图片(通过SysUploadService处理)
     *
     * @param linePrimary 业务表关联主键ID
     * @param uuid        文件唯一标识ID
     * @param response
     * @throws IOException
     */
    @Anonymous
    @GetMapping(value = "image")
    public void image(@RequestParam(value = "line_primary", required = false) String linePrimary,
                      @RequestParam(value = "uuid", required = false) String uuid,
                      HttpServletResponse response) throws IOException {
        sysUploadService.getImage(linePrimary, uuid, response);
    }


    @PostMapping(value = "save")
    public Map<String, Object> add(String data) throws Exception {
        return sysSetService.save(data);
    }

    @GetMapping(value = "getSysset/{sysSecode}")
    public Result getSysset(@PathVariable(value = "sysSecode") String sysSecode) {
        SysSet model = sysSetService.getSysset(sysSecode);
        if (null == model) {
            model = new SysSet();
            model.setSysSecode("DGSQ");
            model.setSysValue("NO");
        }
        return Result.resultOk(model);
    }

    @PostMapping(value = "saveImg")
    public Map<String, Object> saveImg(@RequestParam("file") MultipartFile file, @RequestParam("pid") String pid, HttpServletRequest req) throws Exception {
        return sysSetService.saveImg(file, pid, req);
    }
}
