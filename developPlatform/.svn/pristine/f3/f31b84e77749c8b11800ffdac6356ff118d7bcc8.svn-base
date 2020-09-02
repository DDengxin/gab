package com.tengzhi.IM.layerIM.dao;

import com.tengzhi.IM.business.upload.model.imFile;
import com.tengzhi.IM.layerIM.vo.*;
import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.result.Result;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 王翔
 * @create 2020-05-16
 */

public interface IMDao extends BasicsDao<FriendUsers,String> {

    /**
     * 获取用户列表
     * @param UserId
     * @param DeptId
     * @return
     */
    List<FriendUsers> friendUsers(String UserId,String DeptId);

    List<imFile>  selectFile30Day();

    List<FriendUsers> friendUsersTree(String userId);
    List<FriendUsers> friendGroupUsersTree(String GroupId);
    Result remind();

    void exeSql(String sql);

    String getSeqBySqlFlockUserID(String GroupId);
    List<Group> friendGroupList();

    void user_m_flockSava(String uid,String gid);

    void user_m_flockDel(String uid,String gid);

    void tim_flock_add_mes(String uid);

    void tim_flock_add_mesExit(String uid);

    Map<String,Object> agreeFriend(String page);
    /**
     * 获取部门列表
     * @return
     */
    List<Friend> getDepartmentList();
    List<Friend> getDepartmentListTith();
    List<OrgVo> getOrgList();

    String ReadPath(String UserId);

    String groupnameBySql(String UserId);

    void updateSign(SignVo sign);

    int updateMsg(MsgVo msgVo);

    /**
     * 拉取离线消息
     * @return
     */
    List<RMsg> getofflinemsg();

	List<RMsg> gethistroy(String userId);

    List<Map<String,Object>> friendGroupAndUserList(String GroupId,String u);
    List<RMsg> gethistroyGroup(String userId);

    List<Map<String,Object>> FlockCount(String gid,String uid);

    List<Map<String,Object>> FlockAdmin(String gid,String uid);

    void dissolutionDel(String GroupId);

    Result exit(String GroupId,String UserId) throws IOException;
    //查询群成员
    Map<String, Object> GroupNumbers(String id);

    Map<String,Object> SearchConut(String value);

    boolean judgeUnique(String name);

    Map<String,Object> Search(String value,Integer page,Integer limit);

    void user_m_flockAdd(String uid,String gid);
    void user_m_flockAdd0(String uid,String gid);
    List<Map<String,Object>> treeUser();

    String SelectGroupName(String GroupId);
    String SelectGroupUserId(String GroupId);

    void DelAll();

    void InnovateFlockName(String GroupName,String GroupId);

    List<Map<String,Object>> AdminCall(String GroupId);

    List<Map<String,Object>> inviteUser(String GroupId);

    List<FriendUsers> friendGroupInviteUser(String GroupId);

    List<Map<String,Object>> MainDie(String GroupId);

    List<FriendUsers> friendGroupMainDie(String GroupId);

    List<Map<String,Object>> AdminDie(String GroupId);

    List<FriendUsers> friendGroupAdminDie(String GroupId);

    void AdminAdd(String GroupId,String UserId);
}
