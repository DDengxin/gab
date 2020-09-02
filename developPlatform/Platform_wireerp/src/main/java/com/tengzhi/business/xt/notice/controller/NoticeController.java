package com.tengzhi.business.xt.notice.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.notice.model.EXtNotice;
import com.tengzhi.business.xt.notice.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/xt/notice")
public class NoticeController {

    @Autowired
    private NoticeServiceImpl noticeServiceImpl;

    @GetMapping("*.html")
    public ModelAndView pageForwart(ModelAndView mv) {
        return mv;
    }
    /**
     * 区分模块的参数入口路径
     *
     * @param paramMod
     * @param paramType
     * @param request
     * @param mv
     * @return
     */
    @GetMapping(value = "/{paramMod}/qygg.html")
    public ModelAndView toPage(@PathVariable String paramMod, @RequestParam(required = false) String paramType, HttpServletRequest request, RedirectAttributes rt, ModelAndView mv) {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        mv.setView(new RedirectView(String.format("/xt/notice/%s", servletPath)));
        request.getParameterMap().forEach((key, value) -> {
            mv.addObject(key, value);
        });
        mv.addObject("paramMod", paramMod);
        mv.addObject("paramType", paramType);
        return mv;
    }
   

    /**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "sreach")
    public Result search_option(BaseDto baseDto) throws IOException {
        return noticeServiceImpl.getNotice(baseDto).getResult();
    }

    /**
     * 	首页查询数据列表
     *
     * @return
     */
    @PostMapping(value = "sreachEight")
    public List<EXtNotice> sreachEight() throws IOException {
        return noticeServiceImpl.getNoticeEight();
    }

    /**
     * 查询数据列表
     *
     * @return
     *//*
     * @PostMapping(value = "sreach") public Result search_option(BaseDto baseDto)
     * throws IOException{ return noticeServiceImpl.getNotice(baseDto).getResult();
     * }
     */

    /**
     * 新增
     *
     * @param eXtNotice
     * @return
     * @throws Exception
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody EXtNotice eXtNotice) throws Exception {
        eXtNotice = noticeServiceImpl.save(eXtNotice);
        return Result.resultOk(eXtNotice.getNoticeNo());
    }

    /**
     * 修改
     **/
    @PutMapping("add.html")
    public Result modify(@RequestBody EXtNotice eXtNotice) {
    	noticeServiceImpl.update(eXtNotice);
        return Result.resultOk(eXtNotice.getNoticeNo());
    }

    /**
     * 确认
     **/
    @GetMapping("sure")
    public Result modify(String noticeNo) {
        if (noticeServiceImpl.sure(noticeNo)) {
            return Result.resultOk(noticeNo);
        } else {
            return Result.error("该条记录不是登记状态");
        }
    }

    /**
     * 取消
     **/
    @GetMapping("cancel")
    public Result cancel(String noticeNo) {
        if (noticeServiceImpl.cancel(noticeNo)) {
            return Result.resultOk(noticeNo);
        } else {
            return Result.error("该条记录不是确认状态");
        }
    }

    /**
     * 通过id获取对象
     **/
    @GetMapping(value = "add.html/{noticeNo}")
    public Result getById(@PathVariable String noticeNo) {
        return Result.resultOk(noticeServiceImpl.findByNoticeNo(noticeNo));
    }
    
    /**
     * 删除
     **/
    @DeleteMapping(value = "qygg.html/{noticeNo}")
    public Result delete(@PathVariable(value = "noticeNo") String noticeNo) {
        try {
            noticeServiceImpl.deleteByNoticeNo(noticeNo);
            return Result.resultOk();
        } catch (Exception e) {
            return Result.error("删除失败");
        }
    }

    @GetMapping("/readme/getDes")
    public Result getDes(){
        return Result.resultOk(noticeServiceImpl.getDes());
    }

}
