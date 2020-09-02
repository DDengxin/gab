package com.tengzhi.business.personnel.personnelTraining.trainingNotice.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.personnel.personnelTraining.trainingNotice.dao.TrainingNoticeDao;
import com.tengzhi.business.personnel.personnelTraining.trainingNotice.model.EHrTrainingNotice;
import com.tengzhi.business.personnel.personnelTraining.trainingNotice.service.TrainingNoticeService;
import com.tengzhi.business.personnel.personnelTraining.trainingNotice.vo.TrainingNoticeVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class TrainingNoticeServiceImpl implements TrainingNoticeService {
    @Autowired
    private TrainingNoticeDao trainingNoticeDao;
    @Autowired
    private SysGenNoteService sysGenNoteService;

    /**
     * 查询
     * @param baseDto
     * @return
     * */
    @Override
    public BasePage<Map<String, Object>> getList(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(c->{
            c.contains("tz_work_id",map.get("tzWorkId"));
            c.contains("tz_work_name",map.get("tzWorkName"));
            c.le("tz_training_time",map.get("srchRq2"));
            c.ge("tz_training_time",map.get("srchRq1"));
        });
        String sql = "select tz_sid,tz_note,to_char(tz_training_time,'yyyy-MM-dd')tz_training_time,tz_training_plan_note,tz_work_id,tz_work_name,tz_work_dept,tz_address,f_getname('GETUSERNAME',data_man,'',data_corp)data_man," +
                "data_corp,to_char(data_date,'yyyy-MM-dd')data_date from e_hr_training_notice "+where;
        return trainingNoticeDao.QueryMapPageList(baseDto, sql, true);
    }


    @Override
    public Result update(TrainingNoticeVo trainingNoticeVo) {
        List<EHrTrainingNotice> addModel=new ArrayList<EHrTrainingNotice>();
        List<EHrTrainingNotice> modifyedModel=new ArrayList<EHrTrainingNotice>();
        List<EHrTrainingNotice> deteleModel=new ArrayList<EHrTrainingNotice>();
        SessionUser securityUser=SessionUser.SessionUser();
        trainingNoticeVo.getHeaddata().setDataMan(securityUser.getUserId());
        trainingNoticeVo.getHeaddata().setDataDate(new Date());
        trainingNoticeVo.getHeaddata().getTzTrainingTime();
        trainingNoticeVo.getHeaddata().getTzAddress();
        trainingNoticeVo.getHeaddata().getTzNote();
        trainingNoticeVo.getHeaddata().setDataCorp(securityUser.getCorpId());
        if(trainingNoticeVo.getEntitydata().size()>0) {
            int sid = Integer.parseInt(trainingNoticeDao.getSingleResult("select COALESCE(max(tz_sid)+1,1)  from  e_hr_training_notice"));
            for(EHrTrainingNotice item: trainingNoticeVo.getEntitydata()) {
                if ("added".equals(item.get_state())) {
                    item.setDataMan(trainingNoticeVo.getHeaddata().getDataMan());
                    item.setDataDate(trainingNoticeVo.getHeaddata().getDataDate());
                    item.setDataCorp(trainingNoticeVo.getHeaddata().getDataCorp());
                    item.setTzTrainingTime(trainingNoticeVo.getHeaddata().getTzTrainingTime());
                    item.setTzNote(trainingNoticeVo.getHeaddata().getTzNote());
                    item.setTzAddress(trainingNoticeVo.getHeaddata().getTzAddress());
                    item.setTzSid(sid);
                    addModel.add(item);
                } else if ("modified".equals(item.get_state())) {
                    modifyedModel.add(item);
                } else if ("removed".equals(item.get_state())) {
                    deteleModel.add(item);
                }
            }
            //});
        }

        //保存到数据库
        if(modifyedModel.size()>0) {
            modifyedModel.forEach( model -> {
                trainingNoticeDao.dynamicSave(model,trainingNoticeDao.QueryListModel(EHrTrainingNotice.class,"select * from e_hr_training_notice where tz_sid=" + model.getTzSid()+ " ").get(0));
            });
        }
        //保存到数据库
        if(addModel.size()>0) {
            addModel.forEach( model -> {
                trainingNoticeDao.save(model);
            });
        }
        trainingNoticeDao.updateDataByNote(trainingNoticeVo.getHeaddata().getTzAddress(),trainingNoticeVo.getHeaddata().getTzNote());
        return Result.resultOk();
    }



    /**,
     * 保存
     * @param trainingNoticeVo
     * */
    @Override
    public Result save(TrainingNoticeVo trainingNoticeVo) throws Exception {
        List<EHrTrainingNotice> addModel=new ArrayList<EHrTrainingNotice>();
        List<EHrTrainingNotice> modifyedModel=new ArrayList<EHrTrainingNotice>();
        List<EHrTrainingNotice> deteleModel=new ArrayList<EHrTrainingNotice>();
        SessionUser securityUser=SessionUser.SessionUser();
        trainingNoticeVo.getHeaddata().setDataMan(securityUser.getUserId());
        trainingNoticeVo.getHeaddata().setDataDate(new Date());
        trainingNoticeVo.getHeaddata().getTzTrainingTime();
        trainingNoticeVo.getHeaddata().getTzAddress();
        trainingNoticeVo.getHeaddata().setDataCorp(securityUser.getCorpId());
        trainingNoticeVo.getHeaddata().getTzNote();
        if(trainingNoticeVo.getEntitydata().size()>0) {
            int sid ;
            sid = Integer.parseInt(trainingNoticeDao.getSingleResult("select COALESCE(max(tz_sid)+1,1)  from  e_hr_training_notice"));
            for(EHrTrainingNotice item: trainingNoticeVo.getEntitydata()) {
                if (item.getTzSid() == null) {
                    item.setDataMan(trainingNoticeVo.getHeaddata().getDataMan());
                    item.setDataDate(trainingNoticeVo.getHeaddata().getDataDate());
                    item.setDataCorp(trainingNoticeVo.getHeaddata().getDataCorp());
                    item.setTzTrainingTime(trainingNoticeVo.getHeaddata().getTzTrainingTime());
                    item.setTzNote(trainingNoticeVo.getHeaddata().getTzNote());
                    item.setTzAddress(trainingNoticeVo.getHeaddata().getTzAddress());
                    item.setTzSid(sid);
                    sid++;
                    addModel.add(item);

                } else if ("modified".equals(item.get_state())) {
                    modifyedModel.add(item);
                } else if ("removed".equals(item.get_state())) {
                    deteleModel.add(item);
                }
            }
            //});
        }

        //保存到数据库
        if(addModel.size()>0) {
            addModel.forEach( model -> {
                trainingNoticeDao.save(model);
            });
        }
        return Result.resultOk();
    }
    /**
     * 删除
     * @param tzNote
     * */
    @Override
    public void deleteByNote(String tzNote) {
        trainingNoticeDao.deleteByNote(tzNote);
    }

    /**
     * 刷新gird
     * @param baseDto
     * @return
     * */
    @Override
    public BasePage<Map<String, Object>> getRushGrid(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(c->{
            c.contains("tz_note",map.get("tzNote"));
        });
        String sql="select tz_note,tz_sid,tz_work_dept,tz_work_id ,tz_work_name from e_hr_training_notice "+where;
        return trainingNoticeDao.QueryToMapPage(baseDto,sql);
    }

    /**
     * 获取对象数据
     * @param tzNote
     * @return
     * @auto:PaythonChan
     * */
    @Override
    public Map<String, Object> getByNote(String tzNote) {
        String sql="select tz_sid,tz_note,tz_training_time ,tz_work_id ,tz_work_name ,tz_work_dept , tz_address,  data_man ,data_corp, data_date from e_hr_training_notice  where tz_note ='"+tzNote+"' ";
        return trainingNoticeDao.QueryToFirstMap(sql);
    }
}
