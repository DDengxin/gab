package com.tengzhi.app.daily.controller;

import com.tengzhi.app.daily.service.impl.DailyServiceImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.dailyRoutine.wcsq.model.EXtWcsq;
import com.tengzhi.business.xt.dailyRoutine.wcsq.model.EXtWcsqZc;
import com.tengzhi.business.xt.dailyRoutine.wcsq.service.WcsqService;
import com.tengzhi.business.xt.dailyRoutine.wcsq.vo.EXtWcsqVo;
import com.tengzhi.business.xt.dailyRoutine.workPlan.model.EXtWorkplan;
import com.tengzhi.business.xt.dailyRoutine.workPlan.service.WorkplanService;
import com.tengzhi.tools.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/app/daily")
public class DailyController {

    @Autowired
    private DailyServiceImpl dailyService;
    @Autowired
    private WorkplanService workplanService;
    @Autowired
    private WcsqService wcsqService;

    /**
     *  查询今日快讯
     */
    @PostMapping(value = "getTodayNews")
    public Result getTodayNews(BaseDto baseDto) throws IOException {
        return dailyService.getTodayNews(baseDto).getResult();
    }

    /**
     *  查询工作安排
     */
    @PostMapping(value = "getWorkPlan")
    public Result getWorkPlan(BaseDto baseDto) throws IOException {
        return dailyService.getWorkPlan(baseDto).getResult();
    }

    /**
     *  查询外出申请
     */
    @PostMapping(value = "getGoOutApply")
    public Result getGoOutApply(BaseDto baseDto) throws IOException {
        return dailyService.getGoOutApply(baseDto).getResult();
    }

    /**
     * 获取一条工作安排
     * @param note
     * @return
     */
    @PostMapping("getWorkPlanNote")
    public Result getWorkPlanNote(String note){
        return Result.resultOk(workplanService.findByNote(note));
    }

    /**
     * 工作安排删除
     * @param note
     * @return
     */
    @PostMapping("workPlanDelete")
    public  Result workPlanDelete(String note){
        workplanService.deleteByNote(note);
        return Result.resultOkMsg("删除成功");
    }

    /**
     * 工作安排新增
     */
    @PostMapping("workPlanAdd")
    public Result workPlanAdd(String workPlan) throws Exception{
        JSONObject jsonObject=new JSONObject(workPlan);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //起始日期
        String start=jsonObject.optString("start");
        //结束日期
        String end=jsonObject.optString("end");
        //提醒日期
        String date=jsonObject.optString("date");
        //工作内容
        String content=jsonObject.optString("content");
        //工作类型
        String type=jsonObject.optString("type");
        //工作地点
        String address=jsonObject.optString("address");
        EXtWorkplan eXtWorkplan=new EXtWorkplan();
        eXtWorkplan.setGzAddress(address);
        eXtWorkplan.setGzType(type);
        eXtWorkplan.setGzRemarks(content);
        eXtWorkplan.setStartRq(simpleDateFormat.parse(start));
        eXtWorkplan.setEndRq(simpleDateFormat.parse(end));
        eXtWorkplan.setGzRemind(simpleDateFormat.parse(date));
        eXtWorkplan = workplanService.save(eXtWorkplan);
        return Result.resultOk(eXtWorkplan.getNote());
    }

    /**
     * 工作安排编辑
     */
    @PostMapping("workPlanEdit")
    public Result workPlanEdit(String workPlan)  throws Exception{
        JSONObject jsonObject=new JSONObject(workPlan);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //起始日期
        String start=jsonObject.optString("start");
        //结束日期
        String end=jsonObject.optString("end");
        //提醒日期
        String date=jsonObject.optString("date");
        //工作内容
        String content=jsonObject.optString("content");
        //工作类型
        String type=jsonObject.optString("type");
        //工作地点
        String address=jsonObject.optString("address");
        //单号
        String note=jsonObject.optString("note");
        EXtWorkplan eXtWorkplan=new EXtWorkplan();
        eXtWorkplan.setGzAddress(address);
        eXtWorkplan.setGzType(type);
        eXtWorkplan.setGzRemarks(content);
        eXtWorkplan.setStartRq(simpleDateFormat.parse(start));
        eXtWorkplan.setEndRq(simpleDateFormat.parse(end));
        eXtWorkplan.setGzRemind(simpleDateFormat.parse(date));
        eXtWorkplan.setNote(note);
        workplanService.update(eXtWorkplan);
        return Result.resultOkMsg("修改成功");
    }

    /**
     * 外出申请删除
     * @param note
     * @return
     */
    @PostMapping("goOutApplyDelete")
    public  Result goOutApplyDelete(String note){
        wcsqService.deleteByNote(note);
        return Result.resultOkMsg("删除成功");
    }

    /**
     * 外出申请新增
     */
    @PostMapping(value = "goOutApplyAdd")
    public Result goOutApplyAdd(String goOutApply) throws Exception{
        JSONObject jsonObject=new JSONObject(goOutApply);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        EXtWcsq eXtWcsq1=new EXtWcsq();
        //日期
        String date=jsonObject.optString("date");
        eXtWcsq1.setWcRq(simpleDateFormat1.parse(date));
        //外出人
        String people=jsonObject.optString("people");
        eXtWcsq1.setWcMan(people);
        //部门
        String dept=jsonObject.optString("dept");
        eXtWcsq1.setWcDept(dept);
        //类型
        String type=jsonObject.optString("type");
        eXtWcsq1.setWcType(type);
        //地点
        String address=jsonObject.optString("address");
        eXtWcsq1.setWcAdd(address);
        //其他地点
        String otherAddress=jsonObject.optString("otherAddress");
        eXtWcsq1.setWcAddsm(otherAddress);
        //是否租车
        String sfzc=jsonObject.optString("sfzc");
        eXtWcsq1.setWcIszc(sfzc);
        //是否用车
        String sfyc=jsonObject.optString("sfyc");
        eXtWcsq1.setWcYc(sfyc);
        //用车说明
        String ycsm=jsonObject.optString("ycsm");
        eXtWcsq1.setWcYcsm(ycsm);
        //外出事由
        String wcsy=jsonObject.optString("wcsy");
        //出厂日期
        String ccrq=jsonObject.optString("ccrq");
        eXtWcsq1.setWcTcrq(simpleDateFormat.parse(ccrq));
        //回厂日期
        String hcrq=jsonObject.optString("hcrq");
        eXtWcsq1.setWcThrq(simpleDateFormat.parse(hcrq));
        //外出说明
        String wcsc=jsonObject.optString("wcsc");
        eXtWcsq1.setWcSm(wcsc);
        //路程
        String lc=jsonObject.optString("lc");
        eXtWcsq1.setWcLc(lc);
        //外出费用
        String wcfy=jsonObject.optString("wcfy");
        eXtWcsq1.setWcFy(wcfy);
        //费用说明
        String fysm=jsonObject.optString("fysm");
        eXtWcsq1.setWcFysm(fysm);
        //工作安排
        String gzap=jsonObject.optString("gzap");
        eXtWcsq1.setWcGzap(gzap);
        //租车事由
        String zcsy=jsonObject.optString("zcsy");
        EXtWcsqVo eXtWcsqVo=new EXtWcsqVo();
        eXtWcsqVo.seteXtWcsq(eXtWcsq1);
        EXtWcsq eXtWcsq = wcsqService.save(eXtWcsqVo);
        return Result.resultOk(eXtWcsq.getNote());
    }

    /**
     * 外出申请确认
     * @param note
     * */
    @PostMapping(value = "goOutApplyConfirm")
    public Result goOutApplyConfirm(String note) throws Exception {
        return wcsqService.confirm(note);
    }

    /**
     *取消确认状态
     * @param note
     * */
    @PostMapping(value = "goOutApplyCancel")
    public Result goOutApplyCancel(String note) throws Exception {
        return wcsqService.cancle(note);
    }
}
