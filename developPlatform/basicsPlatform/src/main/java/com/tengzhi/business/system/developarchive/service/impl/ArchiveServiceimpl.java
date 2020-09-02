package com.tengzhi.business.system.developarchive.service.impl;


import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.developarchive.dao.archiveDao;
import com.tengzhi.business.system.developarchive.model.archiveDoc;
import com.tengzhi.business.system.developarchive.service.archiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zs
 * @create 2020-08-24
 */
@Service
@Transactional
public class ArchiveServiceimpl implements archiveService {

    @Autowired
    private archiveDao archiveDao;

    @Override
    public BasePage<Map<String, Object>> archiveDataFindAll(BaseDto baseDto) {
        Map<String,String> map=baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere((c) -> {
            c.ge("to_char(gen_time,'yyyy-MM-dd')", map.get("srchRq1"));
            c.le("to_char(gen_time,'yyyy-MM-dd')", map.get("srchRq2"));
            c.eq("module", map.get("module"));
            c.eq("type", map.get("type"));
            c.contains("title", map.get("title"));
        });
      String sqlString=" select b.right_name,s.* from (" +
                " SELECT a.*,b.title title_name,(select line_primary from com_file f where f.line_primary=a.file_id) file_name " +
                " FROM sys_develop_archive a  left join sys_develop_archive b on a.parent_uuid=b.uuid ) s left join sys_right b on s.bind_id=b.right_id  "+where;
        System.out.println("sqlString = " + sqlString);
        return archiveDao.QueryToMapPage(baseDto, sqlString);
    }

    /**
     * 父级数据展示
     * @param baseDto
     * @return
     */
    @Override
    public Result archiveparentFindAll(BaseDto baseDto) {
        Map<String,String> map=baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixWhere(c -> {
           c.eq("module", map.get("module"));
            c.eq("title", map.get("title"));
        });
        String sql = "select * from sys_develop_archive" + where;
        return archiveDao.QueryMapPageList(baseDto, sql, true).getResult();
    }

    @Override
    public Result archiveDataSava(archiveDoc developDoc) {
        if(archiveDao.findAll(((root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("title"),developDoc.getTitle())
        )).size()<=0){
            SecurityUser securityUser= SessionUser.SessionUser();
            developDoc.setUuid(UUIDUtil.uuid());
            developDoc.setGenTime(new Timestamp(System.currentTimeMillis()));
            developDoc.setPlatformVersion(1L);
            developDoc.setContentType("markdown");
            archiveDao.save(developDoc);
            return Result.resultOk("保存成功!");
        }else{
            //throw new Exception("操作手册标题重复");
            return Result.error("操作手册标题重复");
        }
        //return Result.resultOk("保存成功!");
    }


    @Override
    public Result archiveDataUpdate(archiveDoc developDoc){
            String ctn = archiveDao.QueryFirstString("select count(1) from sys_develop_archive where title='"+developDoc.getTitle()+"' and uuid<> '"+developDoc.getUuid()+"'");
            if(Integer.parseInt(ctn)<1){
            developDoc.setGenTime(new Timestamp(System.currentTimeMillis()));
            archiveDao.dynamicSave(developDoc, archiveDao.findById(developDoc.getUuid()).orElse(null));
            return Result.resultOk("修改成功!");
            }else{
                return Result.error("操作手册标题重复");
            }
    }

    @Override
    public Result archiveByDataId(String uuid) {
        String sql="select a.*,b.right_name,c.title title_name from sys_develop_archive a left join sys_right b on a.bind_id=right_id left join sys_develop_archive c on a.parent_uuid=c.uuid where a.uuid='"+uuid+"'";
        //return Result.resultOk(archiveDao.findById(uuid).orElse(null));
        return Result.resultOk(archiveDao.QueryToFirstMap(sql));
    }

    @Override
    public Result archiveDataDelete(String uuid) {
        archiveDao.deleteById(uuid);
        return Result.resultOk("删除成功!");
    }

    @Override
    public List<SelectVo> Selectsubclass() {
        List<SelectVo> rset = new ArrayList<>();
        String sql = " select subclass \"id\",subclass \"text\" from sys_develop_archive where subclass<>'' ";

        List<Map> sysParamList = archiveDao.QueryListMap(sql);

        sysParamList.forEach(item -> {
            rset.add(new SelectVo(String.valueOf(item.get("id")), String.valueOf(item.get("text"))));
        });
        return rset;
    }


    @Override
    public List<SelectVo> Selectlabel() {
        List<SelectVo> rset = new ArrayList<>();
        String sql = " select  label \"id\",label \"text\" from sys_develop_archive where label<>''";
        List<Map> sysParamList = archiveDao.QueryListMap(sql);
        sysParamList.forEach(item -> {
            rset.add(new SelectVo(String.valueOf(item.get("id")), String.valueOf(item.get("text"))));
        });
        return rset;
    }

    @Override
    public Result archiveByMendata(String menuid, String typestr) {
        String sql = "select a.*,b.right_name from sys_develop_archive a ,sys_right b where a.bind_id=b.right_id and a.state='true' and a.bind_id='" + menuid + "' and a.type='" + typestr + "' order by title ";
            return Result.resultOk(archiveDao.QueryListMap(sql));
    }
}
