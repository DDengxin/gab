package com.tengzhi.business.system.developdoc.service.impl;


import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.base.tools.expandcollection.PredicateList;
import com.tengzhi.base.tools.expandcollection.impl.PredicateArray;
import com.tengzhi.business.system.developdoc.dao.docDao;
import com.tengzhi.business.system.developdoc.model.developDoc;
import com.tengzhi.business.system.developdoc.service.docService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-08-01
 */
@Service
@Transactional
public class docServiceimpl implements docService{

    @Autowired
    private docDao docDao;

    @Override
    public Result docDataFindAll(BaseDto baseDto) {
        Map<String,String> map=baseDto.getParamsMap();
        //采用QBC查询
        List<developDoc> list=docDao.findAll((root,query,buildcd)->{
            PredicateList<Predicate> conditionList=new PredicateArray();
            conditionList.addNotEmpty(buildcd.equal(root.get("docName"),map.get("docName")),map.get("docName"));
            conditionList.addNotEmpty(buildcd.between(root.get("docTime").as(String.class),map.get("TimeStart"),map.get("TimeEnd")),map.get("TimeStart"),map.get("TimeEnd"));
            query.where(conditionList.toArray(new Predicate[conditionList.size()]));
            query.orderBy(buildcd.desc(root.get("docTime")));
            return query.getRestriction();
        });
        return Result.formPage(list,list.size());
    }

    @Override
    public Result docDataSava(developDoc developDoc) throws Exception {
        if(docDao.findAll(((root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("docName"),developDoc.getDocName())
        )).size()<=0){
            SecurityUser securityUser= SessionUser.SessionUser();
            developDoc.setDocId(UUIDUtil.uuid());
            developDoc.setCreateUser(securityUser.getRealName());
            developDoc.setCreateUserId(securityUser.getUserId());
            developDoc.setOrgId(securityUser.getCorpId());
            developDoc.setDocTime(new Date());
            //developDoc.setOrgIdName(securityUser.getOrgName());
            docDao.save(developDoc);
        }else{
            throw new Exception("操作手册名称重复");
        }
        return Result.resultOk("保存成功!");
    }


    @Override
    public Result docDataUpdate(developDoc developDoc) {
        developDoc.setDocModifyTime(new Date());
        docDao.dynamicSave(developDoc,docDao.findById(developDoc.getDocId()).orElse(null));
        return Result.resultOk("修改成功!");
    }

    @Override
    public Result docByDataId(String doc_id) {
        return Result.resultOk(docDao.findById(doc_id).orElse(null));
    }

    @Override
    public Result docDataDelete(String doc_id) {
        docDao.deleteById(doc_id);
        return Result.resultOk("删除成功!");
    }
}
