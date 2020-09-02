package com.tengzhi.business.project.projectArchives.projectTreams.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.project.projectArchives.projectTreams.dao.ProjectTreamDao;
import com.tengzhi.business.project.projectArchives.projectTreams.model.EXmXmxz;
import com.tengzhi.business.project.projectArchives.projectTreams.service.ProjectTreamService;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.params.model.SysParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectTreamServiceImpl implements ProjectTreamService {
    @Autowired
    private ProjectTreamDao dao;

    @Override
    public BasePage<EXmXmxz> findAll(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();

        StringBuffer sqlWhere = SqlJoint.where(e -> {
            e.and(el->{el.contains("a.xm_id",map.get("xmId"));});
            e.and(el->{el.contains("b.xm_name",map.get("xmName"));});
        });
        String sql = " select a.xz_id,a.xm_id,b.xm_name,a.work_id,f_getname('GETUSERNAME',a.work_id,'',a.data_corp) work_id_name,work_name,a.xm_role,f_get_param_name('项目岗位',a.xm_role,a.data_corp) xm_role_name,a.work_tel,a.work_mail,a.work_sm,a.data_corp,f_getname('GETCORPEXP', '', '', a.data_corp) data_corp_name " +
                " from e_xm_xmxz a, e_xm_xmda b where a.xm_id=b.xm_id " + sqlWhere;
        return dao.QueryPageLists(baseDto,sql);
    }

    @Override
    public EXmXmxz save(EXmXmxz eXmXmxz) {
        SessionUser securityUser=SessionUser.SessionUser();
        eXmXmxz.setDataCorp(securityUser.getCorpId());
        eXmXmxz.setXzId(UUID.randomUUID().toString());
        return dao.save(eXmXmxz);
    }

    @Override
    public EXmXmxz getByXmId(String xzId) {
        return dao.QueryToFirstBean( "select a.xz_id,a.xm_id,b.xm_name,a.work_id,a.work_name,a.xm_role,a.work_tel,a.work_mail,a.work_sm,a.data_corp,f_getname('GETCORPNAME', '', '', a.data_corp) data_corp_name " +
                " from e_xm_xmxz a, e_xm_xmda b where a.xm_id=b.xm_id and a.xz_id='"+ xzId+"' ");
    }

    @Override
    public void update(EXmXmxz eXmXmxz) {
        dao.dynamicSave(eXmXmxz,dao.findById(eXmXmxz.getXzId()).orElse(null));
    }

    @Override
    public void deleteByXmId(String xzId) {
        dao.deleteById(xzId);
    }

    @Override
    public List<SelectVo> getTeamListByXm(String xmId) {
        String sql = " select work_id,f_getname('GETUSERNAME',work_id,'',data_corp) work_id_name " +
                " from e_xm_xmxz where xm_id='"+xmId+"' and data_corp = '"+SessionUser.getCurrentCorpId()+"' ";
        List<EXmXmxz> list = dao.QueryListModel(EXmXmxz.class,sql);
        return list.stream().map(item -> new SelectVo(item.getWorkId(),item.getWorkIdName())).collect(Collectors.toList());
    }


}
