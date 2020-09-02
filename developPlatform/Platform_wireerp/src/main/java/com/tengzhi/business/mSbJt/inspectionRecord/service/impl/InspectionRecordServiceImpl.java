package com.tengzhi.business.mSbJt.inspectionRecord.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;
import com.tengzhi.business.cg.yw.purchaseRequisition.model.EcgRequisition;
import com.tengzhi.business.cg.yw.purchaseRequisition.vo.EcgRequisitiontVo;
import com.tengzhi.business.mSbJt.inspectionRecord.dao.InspectionRecordDao;
import com.tengzhi.business.mSbJt.inspectionRecord.model.MSbInspection;
import com.tengzhi.business.mSbJt.inspectionRecord.service.InspectionRecordService;
import com.tengzhi.business.mSbJt.inspectionRecord.vo.InspectionVo;
import com.tengzhi.business.mSbJt.model.MSbJt;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import com.tengzhi.business.sale.saleProduct.saleContract.vo.EXsContractVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InspectionRecordServiceImpl implements InspectionRecordService {

    @Autowired
    private InspectionRecordDao inspectionRecordDao;
    @Autowired
    private SysGenNoteService sysGenNoteService;

    /**
     * 查询
     * @param baseDto
     * */
    @Override
    public BasePage<Map<String,Object>> findAll(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        String sqlwhere = " where data_corp in ('"+ SessionUser.SessionUser().getSysUser().getOrgIds().replaceAll(",", "','")+"') ";
        sqlwhere += SqlJoint.whereSuffixWhere(c->{
            c.contains("inspection_note",map.get("inspectionNote"));
            /*c.contains("inspection_project",map.get("inspectionProject"));*/
        });
        String sql = "select inspection_note,inspection_sid,inspection_machine,f_get_param_name('巡检项目',inspection_project,data_corp,'')projectname,param_turn( inspection_code) inspection_code,inspection_value,f_getname('GETUSERNAME',data_man,'',data_corp)data_man," +
                "f_getname('GETCORPNAME',data_corp,'',data_corp)data_corp,data_date from m_sb_inspection " + sqlwhere;
        return inspectionRecordDao.QueryMapPageList(baseDto, sql, true);

    }
    /**
     * 保存
     * @param inspectionVo
     * */
    @Override
    public  Result save(InspectionVo inspectionVo)throws Exception {
        List<MSbInspection> addModel=new ArrayList<MSbInspection>();
        List<MSbInspection> modifyedModel=new ArrayList<MSbInspection>();
        List<MSbInspection> deteleModel=new ArrayList<MSbInspection>();
        String note=sysGenNoteService.getyyMMNote4(MSbInspection.class,"CL");
        SessionUser securityUser=SessionUser.SessionUser();
        inspectionVo.getHeaddata().setInspectionNote(note);
        inspectionVo.getHeaddata().setDataMan(securityUser.getUserId());
        inspectionVo.getHeaddata().setDataDate(new Date());
        inspectionVo.getHeaddata().getInspectionMachine();
        inspectionVo.getHeaddata().setDataCorp(securityUser.getCorpId());
        if(inspectionVo.getEntitydata().size()>0) {
            int sid ;
            sid = Integer.parseInt(inspectionRecordDao.getSingleResult("select COALESCE(max(inspection_sid)+1,1)  from  m_sb_inspection"));
            for(MSbInspection item: inspectionVo.getEntitydata()) {
                if (item.getInspectionSid() == null) {
                    item.setInspectionNote(note);
                    item.setDataMan(inspectionVo.getHeaddata().getDataMan());
                    item.setInspectionProject(inspectionVo.getHeaddata().getInspectionProject());
                    item.setDataDate(inspectionVo.getHeaddata().getDataDate());
                    item.setDataCorp(inspectionVo.getHeaddata().getDataCorp());
                    item.setInspectionMachine(inspectionVo.getHeaddata().getInspectionMachine());
                    item.setInspectionSid(sid);
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
                inspectionRecordDao.save(model);
            });
        }
        return Result.resultOk();
    }
    /**
     * 修改
     * @param inspectionVo
     * */
    @Override
    public Result update(InspectionVo inspectionVo)throws Exception {
        List<MSbInspection> addModel=new ArrayList<MSbInspection>();
        List<MSbInspection> modifyedModel=new ArrayList<MSbInspection>();
        List<MSbInspection> deteleModel=new ArrayList<MSbInspection>();
        String note=sysGenNoteService.getyyMMNote4(MSbInspection.class,"CL");
        SessionUser securityUser=SessionUser.SessionUser();
        inspectionVo.getHeaddata().setInspectionNote(note);
        inspectionVo.getHeaddata().setDataMan(securityUser.getUserId());
        inspectionVo.getHeaddata().setDataDate(new Date());
        inspectionVo.getHeaddata().setDataCorp(securityUser.getCorpId());
        if(inspectionVo.getEntitydata().size()>0) {
            int sid ;
            sid = Integer.parseInt(inspectionRecordDao.getSingleResult("select COALESCE(max(inspection_sid)+1,1)  from  m_sb_inspection"));
            for(MSbInspection item: inspectionVo.getEntitydata()) {
                if (item.getInspectionSid() != null) {
                    item.setInspectionNote(note);
                    item.setDataMan(inspectionVo.getHeaddata().getDataMan());
                    item.setInspectionProject(inspectionVo.getHeaddata().getInspectionProject());
                    item.setDataDate(inspectionVo.getHeaddata().getDataDate());
                    item.setDataCorp(inspectionVo.getHeaddata().getDataCorp());
                    item.setInspectionSid(sid);
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
                inspectionRecordDao.save(model);
            });
        }
        return Result.resultOk();
    }

    /**
     * 删除
     * @param inspectionNote
     * */
    @Override
    public void deleteByNote(String inspectionNote) {

        inspectionRecordDao.deleteNote(inspectionNote);
    }


    /**
         * 根据inspectionSid获取对象数据
         * @param inspectionSid
         * */
        @Override
        public MSbInspection findByInspectionSid (String inspectionSid){
            MSbInspection mSbInspection = inspectionRecordDao.QueryListModel(MSbInspection.class, "select inspection_note, f_getname('GETSBNAME',inspection_machine,'',data_corp)machine_name,inspection_project,inspection_code,inspection_value from m_sb_inspection where inspection_sid=" + inspectionSid + " ").get(0);
            return mSbInspection;
        }
    /**
     * 新增grid
     * @param baseDto
     * */
    @Override
    public BasePage<Map<String, Object>> getAddGrid(BaseDto baseDto) {
        Map<String,String> map = baseDto.getParamsMap();
       String sql =" select inspection_sid,param_id inspection_code ,param_name code_name,inspection_value from sys_param left join m_sb_inspection on param_id= inspection_code and  inspection_note='"+map.get("inspectionNote")+"' where parent_id='"+map.get("inspectionProject")+"' ";
        return inspectionRecordDao.QueryToMapPage(baseDto,sql);
    }

}
