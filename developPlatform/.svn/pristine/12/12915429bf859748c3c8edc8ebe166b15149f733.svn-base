package com.tengzhi.business.system.main.service.impl;

import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.base.tools.ehcache.util.EhcacheTemplate;
import com.tengzhi.business.system.main.dao.SysUserMenuDao;
import com.tengzhi.business.system.main.dao.SysUserTodoDao;
import com.tengzhi.business.system.main.model.SysUserMenu;
import com.tengzhi.business.system.main.model.SysUserTodo;
import com.tengzhi.business.system.main.service.SysMainService;
import com.tengzhi.business.system.right.dao.SysRightDao;
import com.tengzhi.business.system.right.model.SysRight;
import com.tengzhi.business.system.setting.service.Impl.SysSetServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author 王翔
 * @create 2020-04-29
 */

@Service
public class SysMainServiceImpl implements SysMainService {

    @Autowired
    private SysRightDao sysRightDao;

    @Autowired
    private SysUserMenuDao sysUserMenuDao;

    @Autowired
    private SysUserTodoDao sysUserTodoDao;

    @Autowired
    private EhcacheTemplate template;

    @Autowired
    private SysSetServiceImpl sysSetServiceImpl;

    @Override
    public BasePage<SysRight> getUserMenuList(BaseDto dto) {
        Map<String, String> map = dto.getParamsMap();
        SessionUser sessionUser = SessionUser.SessionUser();
        String where = SqlJoint.start()
                .eq("right_module", map.get("rightModule"), true)
                .contains("right_name", map.get("rightName"))
                .getAndSuffixSqlStr();
        String sql = "select * from sys_right "
                + "where right_type='MENU' "
                //排除非末级惨淡
                + "and (length(right_link) > 0 and right_link <> '/' and not exists(select 1 from sys_right tmp where tmp.parent_id = sys_right.right_id and tmp.right_type='MENU'  )) "
                //显示授权
                + "and right_id in(select distinct sys_role_r_right.right_id from sys_role_r_right where data_corp = '" + SessionUser.getCurrentCorpId() + "' and  role_id in(select distinct role_id from sys_user_r_role where user_id='" + sessionUser.getUserId() + "' and data_corp = sys_role_r_right.data_corp )) "
                //排除已经选中的菜单
                + " and right_id not in(select distinct right_id from sys_user_menu where data_corp = '" + SessionUser.getCurrentCorpId() + "' and user_id = '" + SessionUser.SessionUser().getUserId() + "')"
                //前端页面的查询条件
                + where
                + " order by right_ord asc";
        return sysRightDao.QueryNoPageLists(sql);
    }


    @Transactional
    @Override
    public void update(String id) {
        sysUserTodoDao.upadteId(new Timestamp(System.currentTimeMillis()), id);
    }

    @Override
    public List<SysRight> ShowMenu() {
        SessionUser sessionUser = SessionUser.SessionUser();
        return sysRightDao.QueryListModel(SysRight.class,
                "select a.* "
                        + " from  sys_right a,sys_user_menu b "
                        + " where a.right_id=b.right_id and b.user_id='" + sessionUser.getUserId() + "' " +
                        " and a.right_id in(select distinct sys_role_r_right.right_id from sys_role_r_right  where data_corp = '" + SessionUser.getCurrentCorpId() + "' and role_id in(select distinct role_id from sys_user_r_role where user_id='" + sessionUser.getUserId() + "' and  data_corp = sys_role_r_right.data_corp ))  " +
                        "order by b.right_ord asc "
        );
    }


    @Override
    public void sava(List<Map<String, Object>> list) {
        SessionUser sessionUser = SessionUser.SessionUser();
        sysUserMenuDao.deleteAll();
        Map<String, Object> item = null;
        for (int i = 0; i < list.size(); i++) {
            item = list.get(i);
            SysUserMenu u = new SysUserMenu();
            u.setId(UUIDUtil.uuid());
            u.setUserId(sessionUser.getUserId());
            u.setDataCorp(sessionUser.getCorpId());
            u.setGenTime(new Timestamp(System.currentTimeMillis()));
            u.setRightId(item.get("rightId").toString());
            u.setRightOrd(i);
            sysUserMenuDao.save(u);
        }
    }


    @Override
    public ModelAndView getSetting(ModelAndView mv) {
        SessionUser sessionUser = SessionUser.SessionUser();
        List<Map> setting = sysSetServiceImpl.getSetting(sessionUser.getCorpId());
        for (int i = 0; i < setting.size(); i++) {
            String paramId = StrUtil.nullToDefault((CharSequence) setting.get(i).get("param_id"), null);
            String sysValue = StrUtil.nullToDefault((CharSequence) setting.get(i).get("sys_value"), null);
            String sysFile = StrUtil.nullToDefault((CharSequence) setting.get(i).get("sys_file"), null);


            if ("SYS_SETTING_XTMC".equals(paramId) && StringUtils.isNotBlank(sysValue)) {
                mv.addObject(paramId, sysValue);
            } else if ("SYS_SETTING_XTTB".equals(paramId) && StringUtils.isNotBlank(sysFile)) {
                mv.addObject(paramId, "/system/setting/image?line_primary=" + sysFile);
            } else if ("SYS_SETTING_EM".equals(paramId) && StringUtils.isNotBlank(sysValue)) {
                mv.addObject(paramId, sysValue);
            } else if ("SYS_SETTING_ZYTB".equals(paramId) && StringUtils.isNotBlank(sysFile)) {
                mv.addObject(paramId, "/system/setting/image?line_primary=" + sysFile);
            } else if ("SYS_SETTING_TYPE".equals(paramId) && StringUtils.isNotBlank(sysValue)) {
                String value = sysValue;
                String[] type = value.split(",");
                for (int j = 0; j < type.length; j++) {
                    mv.addObject(type[j], type[j]);
                }
            } else if ("SYS_SETTING_XTJZ".equals(paramId) && StringUtils.isNotBlank(sysValue)) {
                mv.addObject(paramId, sysValue);
            }
        }
        return mv;
    }

    @Transactional
    @Override
    public void up(String id) {
        sysUserTodoDao.up(id);
    }

    @Transactional
    @Override
    public void ap(String id) {
        sysUserTodoDao.ap(id);
    }


    @Override
    public void update(SysUserTodo sysuser) {
        sysUserTodoDao.deleteById(sysuser.getId());
        SessionUser sessionUser = SessionUser.SessionUser();
        sysuser.setGenTime(new Timestamp(System.currentTimeMillis()));
        sysuser.setModifyTime(new Timestamp(System.currentTimeMillis()));
        sysuser.setUserId(sessionUser.getUserId());
        sysuser.setDataCorp(sessionUser.getCorpId());
        sysuser.setId(UUIDUtil.uuid());
        sysuser.setStatus(false);
        sysUserTodoDao.save(sysuser);
    }

    @Override
    public void add(SysUserTodo sysuser) {
        SessionUser sessionUser = SessionUser.SessionUser();
        sysuser.setGenTime(new Timestamp(System.currentTimeMillis()));
        sysuser.setModifyTime(new Timestamp(System.currentTimeMillis()));
        sysuser.setUserId(sessionUser.getUserId());
        sysuser.setDataCorp(sessionUser.getCorpId());
        sysuser.setId(UUIDUtil.uuid());
        sysuser.setPast(0);
        sysuser.setStatus(false);
        sysUserTodoDao.save(sysuser);
    }

    @Override
    public List<Map> getDatachart() {
        SessionUser sessionUser = SessionUser.SessionUser();
        //获取用户的角色，用来判断是否是管理员
        String sql = "select * from (" +
                " select  * ,0 ord from sys_datachart where datachart_flag='true' and corp_id='" + sessionUser.getOriginalCorpId() + "' and datachart_id in ( " +
                "		select b.data_chart from sys_user_r_role  a,sys_role b " +
                "		where a.role_id=b.role_id and b.data_chart <>'' and b.is_forbidden= false and a.user_id='" + sessionUser.getUserId() + "'	GROUP BY b.data_chart " +
                "       )  " +
                " union all " +
                " select  *, 1 ord  from sys_datachart where datachart_flag='true' and corp_id='" + sessionUser.getOriginalCorpId() + "' and datachart_id in ( " +
                "		select b.data_chart_two from sys_user_r_role  a,sys_role b " +
                "		where a.role_id=b.role_id and b.data_chart_two <>''  and b.is_forbidden= false	and a.user_id='" + sessionUser.getUserId() + "' 	GROUP BY b.data_chart_two " +
                "		)  " +
                ") t order by datachart_id desc ,ord asc ";
        List<Map> listmap = sysUserTodoDao.QueryListMap(sql);
        return listmap;
    }

    @Override
    public void EmptyCache() {
        template.clearAll();
    }
}
