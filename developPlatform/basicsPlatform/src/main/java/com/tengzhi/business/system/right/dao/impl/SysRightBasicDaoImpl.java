package com.tengzhi.business.system.right.dao.impl;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.right.dao.SysRightBasicDao;
import com.tengzhi.business.system.right.model.SysRight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysRightBasicDaoImpl extends BasicsDaoImpl<SysRight, String> implements SysRightBasicDao {
    @Override
    public List<SysRight> findByUserId(String userid, String rightModule) {
        if (SessionUser.isSuperUser()) {
            String sql = "select * from sys_right where  is_forbidden = false and right_module=?1 ";
            return super.queryBean(sql, rightModule);
        } else {
            String sql = "select * "
                    + " from sys_right "
                    + " where  is_forbidden = false and "
                    + " right_id in (select distinct right_id from sys_role_r_right rig left join sys_user_r_role rol on rig.role_id=rol.role_id where user_id=?1 and rig.data_corp = ?2) "
                    + " and right_module=?3";
            return super.queryBean(sql, userid, SessionUser.getCurrentCorpId(), rightModule);
        }
    }

    @Override
    public List<SysRight> findParentIdAndRightType(String userid, String rightId, String typeButton) {
        if (SessionUser.isSuperUser()) {
            String sql = "select * from sys_right where is_forbidden = false and parent_id=?1  and right_type=?2 order by right_ord";
            return super.queryBean(sql, rightId, typeButton);
        } else {
            String sql = "select * "
                    + " from sys_right "
                    + " where  is_forbidden = false "
                    + " and right_id in (select distinct right_id from sys_role_r_right rig left join sys_user_r_role rol on rig.role_id=rol.role_id where user_id=?1 and rig.data_corp = ?2) "
                    + " and parent_id=?3 and right_type=?4 order by right_ord";
            return super.queryBean(sql, userid, SessionUser.getCurrentCorpId(), rightId, typeButton);
        }
    }

}
