package com.tengzhi.business.js.drawing.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.js.drawing.dao.DrawingDao;
import com.tengzhi.business.js.drawing.model.EJsCpcodeTdgl;
import com.tengzhi.business.js.drawing.service.DrawingService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/14 0014 10:39
 * @Description:
 */
@Service
@Transactional//发生异常事务回滚
public class DrawingServiceImpl implements DrawingService {

    private DrawingDao drawingDao;
    private SysGenNoteService sysGenNoteService;

    public DrawingServiceImpl(DrawingDao drawingDao, SysGenNoteService sysGenNoteService) {
        this.drawingDao = drawingDao;
        this.sysGenNoteService = sysGenNoteService;
    }

    @Override
    public  BasePage<Map<String, Object>> findAll(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String whereJudge = SqlJoint.whereSuffixWhere((c) ->{
            c.eq("customer",map.get("customer"));
            c.eq("flag",map.get("flag"));
            c.contains("code",map.get("clCj"));
            c.contains("note",map.get("note"));
            c.contains("tw_name",map.get("twName"));
        });
        String sqlString = "select file_path,uuid, note,rq,tw_name,code,tw_file,tw_sm,flag,data_corp,customer,f_getname('GETDWEXP',customer,'',data_corp)customer_name,data_date," +
                "data_man,f_getname('TransformUser',data_man,'',data_corp) data_man_name " +
                " from e_js_cpcode_tdgl left join com_file on tw_file=line_primary"+whereJudge;
        BasePage<Map<String, Object>> basePage = drawingDao.QueryToMapPage(baseDto, sqlString);
        return  basePage;
    }

    @Override
    public Result findByNote(String note) {
        String sql ="select a.*,f_getname('GETDWEXP',a.customer,'',a.data_corp)customer_name," +
                "f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',b.cpcode_type,a.data_corp) cpcode_name_name," +
                "f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',b.cpcode_type,a.data_corp) cpcode_fl_name," +
                "f_getparamname('GETCPCODESIZE',b.cpcode_size,'','技术',b.cpcode_type,a.data_corp) cpcode_size_name," +
                "f_get_param_name('计量单位',b.cpcode_bz,a.data_corp) cpcodebz" +
                " from e_js_cpcode_tdgl a,e_js_cpcode b  where a.code=b.cpcode_id and a.note='"+note+"'";
       // drawingDao.QueryToFirstMap(sql);
       // EJsCpcodeTdgl eJsCpcodeTdgl = drawingDao.findById(note).orElse(null);
        return Result.resultOk(drawingDao.QueryToFirstMap(sql));
    }

    @Override
    public Result add(EJsCpcodeTdgl eJsCpcodeTdgl) {
        String note = sysGenNoteService.getNote(EJsCpcodeTdgl.class, "", "yyMMdd", 3);
        SessionUser user = SessionUser.SessionUser();
        eJsCpcodeTdgl.setNote(note);
        eJsCpcodeTdgl.setDataMan(user.getUsername());
        eJsCpcodeTdgl.setDataCorp(user.getCorpId());
        eJsCpcodeTdgl.setDataDate(new Date());
        EJsCpcodeTdgl saveData = drawingDao.save(eJsCpcodeTdgl);
        if (saveData != null) {
        return Result.resultOkMsg("保存成功");
        }
        return Result.resultOkMsg("保存失败");
    }

    @Override
    public Result update(EJsCpcodeTdgl newEJsCpcodeTdgl) {
        Result result = new Result();
        EJsCpcodeTdgl oldEJsCpcodeTdgl = drawingDao.findById(newEJsCpcodeTdgl.getNote()).orElse(null);
       // System.out.println("newEJsCpcodeTdgl:"+newEJsCpcodeTdgl+"\noldEJsCpcodeTdgl:"+oldEJsCpcodeTdgl);
        EJsCpcodeTdgl eJsCpcodeTdgl = drawingDao.dynamicSave(newEJsCpcodeTdgl, drawingDao.findById(newEJsCpcodeTdgl.getNote()).orElse(null));
        System.out.println("eJsCpcodeTdgl:"+eJsCpcodeTdgl);
        if (eJsCpcodeTdgl != null) {
            result.putMsg("msg","保存成功");
        }else{
            result.putMsg("msg","保存失败");
        }
        return result;
    }

    @Override
    public Result delete(String note) {
        drawingDao.deleteById(note);
        Result result = new Result();
        return result.putMsg("msg","删除成功");
    }
}
