package com.tengzhi.business.system.right.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.ehcache.util.EhcacheEnum;
import com.tengzhi.base.tools.ehcache.util.EhcacheTemplate;
import com.tengzhi.business.dynamicrow.cache.rowCache;
import com.tengzhi.business.dynamicrow.dao.rowDao;
import com.tengzhi.business.system.right.constant.RightModuleEnum;
import com.tengzhi.business.system.right.dao.SysRightBasicDao;
import com.tengzhi.business.system.right.dao.SysRightDao;
import com.tengzhi.business.system.right.model.SysRight;
import com.tengzhi.business.system.right.service.SysRightService;
import com.tengzhi.business.system.right.vo.SysRightVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@CacheConfig(cacheNames = "ehCacheConfig")
public class SysRightServiceImpl implements SysRightService {
    @Autowired
    private SysRightDao sysRightDao;

    @Autowired
    private rowDao rowDao;

    @Autowired
    private SysRightBasicDao sysRightBasicDao;

    @Override
    public List<SysRight> findAll() {
        return sysRightDao.findAll();
    }

    @Autowired
    private EhcacheTemplate template;

    @Autowired
    public rowCache rowCache;

    @Override
    public List<SysRight> getMenuRightModule(String rightModule) {
        return sysRightDao.findByRightModuleAndIsForbiddenOrderByRightOrdAsc(rightModule, false);
    }

    @Override
    public List<Map<String, Object>> getMenuRightModuleByKeyWord(String keyword) {
        String sql = " select tmp.*, concat(right_module,'-',(select right_name from sys_right where right_id= tmp.parent_id limit 1),'-',right_name) title "
                + " from( select * "
                + " from sys_right "
                + " where  parent_id <> 'ROOT' and right_type='MENU' and is_forbidden = false and right_name like :1 "
                //超级管理员不控权限
                + (SessionUser.isSuperUser() ? "" : " and right_id in (select right_id from sys_role_r_right rig left join sys_user_r_role rol on rig.role_id=rol.role_id where user_id='" + SessionUser.SessionUser().getUserId() + "' and rig.data_corp = '" + SessionUser.getCurrentCorpId() + "')  and  parent_id not in(select right_id from sys_right where  parent_id='ROOT') ")
                + " )tmp";
        return sysRightDao.QueryToMap(sql, "%" + keyword + "%");

    }

    @Override
    public List<SysRight> getMenuRightModule(RightModuleEnum rightModuleEnum) {
        return getMenuRightModule(rightModuleEnum.name());
    }

    @Override
    public BasePage<SysRight> findAll(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        return sysRightDao.QueryNOPageList(baseDto, Specifications.where((c) -> {
            c.eq(map, new String[]{"rightType", "parentId", "rightModule", "rightResouce"});
            c.contains("rightName", map.get("rightName"));
            c.eqbool("isForbidden", map.get("isForbidden"));
        }));
    }

    @Override
    public SysRight findByRightId(String rightId) {
        return sysRightDao.findByRightId(rightId);
    }

    @Override
    public SysRight save(SysRightVo sysRightVo) throws Exception {
        removeCache();
        if (judgeUnique(sysRightVo.getMenu())) {
            sysRightVo.getMenu().setRightType("MENU");
            if (null != sysRightVo.getAddedList() && !sysRightVo.getAddedList().isEmpty()) {
                sysRightVo.getAddedList().forEach(item -> {
                    item.setParentId(sysRightVo.getMenu().getRightId());
                    item.setRightModule(sysRightVo.getMenu().getRightModule());
                    sysRightDao.save(item);
                });
            }
            if (!sysRightVo.getDeletedList().isEmpty()) {
                sysRightDao.deleteAll(sysRightVo.getDeletedList());
            }
            if (!sysRightVo.getModifyedList().isEmpty()) {
                sysRightVo.getModifyedList().forEach(item -> {
                    item.setRightModule(sysRightVo.getMenu().getRightModule());
                    sysRightDao.dynamicSave(item, sysRightDao.findByRightId(item.getRightId()));
                });
            }
            return sysRightDao.save(sysRightVo.getMenu());
        } else {
            throw new Exception("菜单名称已存在");
        }
    }

    @Override
    public void update(SysRightVo sysRightVo) throws Exception {
        removeCache();
        if (!sysRightVo.getAddedList().isEmpty()) {
            sysRightVo.getAddedList().forEach(item -> {
                item.setParentId(sysRightVo.getMenu().getRightId());
                item.setRightModule(sysRightVo.getMenu().getRightModule());
                sysRightDao.save(item);
            });
        }
        if (!sysRightVo.getDeletedList().isEmpty()) {
            sysRightDao.deleteAll(sysRightVo.getDeletedList());
        }
        if (!sysRightVo.getModifyedList().isEmpty()) {
            sysRightVo.getModifyedList().forEach(item -> {
                item.setRightModule(sysRightVo.getMenu().getRightModule());
                sysRightDao.dynamicSave(item, sysRightDao.findByRightId(item.getRightId()));
            });
        }
        sysRightDao.dynamicSave(sysRightVo.getMenu(), sysRightDao.findByRightId(sysRightVo.getMenu().getRightId()));
    }

    @Override
    public void deleteById(Map<String, Object> map) {
        String oId = ObjectUtil.isEmpty(map.get("rightId")) ? "-1" : map.get("rightId").toString();
        sysRightDao.deleteByRightId(oId);
        rowCache.Delete(oId);
        removeCache();
        Recursive((List<Map<String, Object>>) map.get("list"));
    }

    @Caching(evict = {
        @CacheEvict(keyGenerator = "keyGeneratorMenuObj"),
        @CacheEvict(keyGenerator = "keyGeneratorButtonObj")
    })
    @Override
    public SysRight saveButton(SysRight sysRight) {
        return sysRightDao.dynamicSave(sysRight, sysRightDao.findByRightId(sysRight.getRightId()));
    }


    @Override
    public boolean judgeUnique(SysRight sysRight) {
        return sysRightDao.findAll(Specifications.where((c) -> {
            c.eq("parentId", sysRight.getParentId());
            c.eq("rightName", sysRight.getRightName());
        })).size() <= 0;
    }

    @Override
    public List<SysRight> findByParentIdAndRightType(String rightId, String typeButton) {
        return sysRightDao.findByParentIdAndRightType(rightId, typeButton);
    }

    @Override
    public List<SysRight> findByParentIdAndRightType(String userid, String rightId, String typeButton) {
        return sysRightBasicDao.findParentIdAndRightType(userid, rightId, typeButton);
    }

    //递归删除
    private void Recursive(List<Map<String, Object>> list) {
        if (list != null) {
            list.forEach(item -> {
                sysRightDao.deleteByRightId(ObjectUtil.isEmpty(item.get("rightId")) ? "-1" : item.get("rightId").toString());
                Recursive((List<Map<String, Object>>) item.get("children"));
            });
        }
    }

    @Override
    public List<SysRight> getMenuRightModule(String userid, String rightModule) {
        return sysRightBasicDao.findByUserId(userid, rightModule);
    }

    @Override
    public void removeCache() {
        SessionUser sessionUser = SessionUser.SessionUser();
        if (null != sessionUser) {
            template.removeLike("ehCacheConfig", String.format("%s%s", EhcacheEnum.menu.getGroup(), sessionUser.getCorpId()));
            template.removeLike("ehCacheConfig", String.format("%s%s", EhcacheEnum.button.getGroup(), sessionUser.getCorpId()));
        }
    }


}
