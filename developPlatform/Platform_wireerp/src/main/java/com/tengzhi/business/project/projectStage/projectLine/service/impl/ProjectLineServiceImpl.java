package com.tengzhi.business.project.projectStage.projectLine.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContractDetailed;
import com.tengzhi.business.project.projectProcess.projectTask.model.EXmXmrw;
import com.tengzhi.business.project.projectStage.projectLine.dao.ProjectLineDao;
import com.tengzhi.business.project.projectStage.projectLine.model.EXmXmlc;
import com.tengzhi.business.project.projectStage.projectLine.service.ProjectLineService;
import com.tengzhi.business.project.projectStage.projectLine.vo.EXmXmlcVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProjectLineServiceImpl implements ProjectLineService {

    @Autowired
    private ProjectLineDao dao;
    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Override
    public EXmXmlc save(EXmXmlcVo vo) throws Exception {
        vo.getHeadData().setLcNote(sysGenNoteService.getyyyyMMddNote4(EXmXmlc.class,"LC"));
        vo.getAddedList().forEach(model ->{
            model.setLcNote(vo.getHeadData().getLcNote());
            dao.save(model);
        });
        return vo.getHeadData();
    }

    @Override
    public BasePage<EXmXmlc> getList(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        String where = " where a.data_corp='"+ SessionUser.SessionUser().getCorpId()+"'  ";
        String andWhere = SqlJoint.whereSuffixAnd(item->{
            item.le("lc_rq",map.get("srchRq2"));
            item.ge("lc_rq",map.get("srchRq1"));
            item.contains("lc_note",map.get("lcNote"));
        });
        String sql = " select a.lc_rq,a.lc_note,a.lc_title,a.lc_xm_id,a.man,a.createtime,(select b.xm_name from e_xm_xmda b where b.xm_id=a.lc_xm_id ) lc_xm_id_name,f_getname('GETUSERNAME',a.man,'',a.data_corp) man_name  " +
                " from e_xm_xmlc a " + where + andWhere + " group by a.lc_rq,a.lc_note,a.lc_title,a.lc_xm_id,a.man,a.createtime,a.data_corp ";
        return dao.QueryPageLists(baseDto,sql);
    }

    @Override
    public BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        String sqlString=" select lc_id,mx_id,lc_xm_stage,lc_xm_node,lc_man,remarks,f_getname('GETUSERNAME',lc_man,'',data_corp) lc_man_name,f_get_param_name('项目阶段',lc_xm_stage,data_corp) lc_xm_stage_name,f_get_param_name('项目节点',lc_xm_node,data_corp) lc_xm_node_name   from  e_xm_xmlc where lc_note='"+map.get("lcNote")+"' ";
        return  dao.QueryMapPageList(baseDto, sqlString, true);
    }

    @Override
    public EXmXmlc findById(String lcNote) {
        String sql = " select a.*,b.xm_name lc_xm_id_name from e_xm_xmlc a,e_xm_xmda b where a.lc_xm_id=b.xm_id and a.lc_note='"+lcNote+"' ";
        return dao.QueryToFirstBean(sql);
    }


    @Override
    public EXmXmlc update(EXmXmlcVo vo) {
        vo.getAddedList().forEach(model->{
            dao.save(model);
        });
        vo.getModifyedList().forEach(model->{
            dao.dynamicSave(model,dao.findById(model.getLcId()).orElse(null));
        });
        dao.deleteAll(vo.getDeletedList());
        return vo.getHeadData();
    }

    @Override
    public void deleteByNote(String lcNote) {
        dao.deleteByNote(lcNote);
    }

    public List<Map<String,Object>> getXmlcByXm(String xmId){
        return dao.QueryToMap(" select lc_xm_stage,lc_xm_node,f_get_param_name('项目阶段',lc_xm_stage,data_corp) lc_xm_stage_name,f_get_param_name('项目节点',lc_xm_node,data_corp)lc_xm_node_name    from e_xm_xmlc where lc_xm_id='"+xmId+"' and data_corp='"+SessionUser.SessionUser().getCorpId()+"' ");
    }

    public List<Map<String,Object>> getXmNodeByJd(String xmId,String xmStage){
        return dao.QueryToMap(" select lc_xm_node,f_get_param_name('项目节点',lc_xm_node,data_corp)lc_xm_node_name    from e_xm_xmlc where lc_xm_id='"+xmId+"' and lc_xm_stage='"+xmStage+"' and data_corp='"+SessionUser.SessionUser().getCorpId()+"' ");
    }


}
