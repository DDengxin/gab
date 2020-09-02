package com.tengzhi.IM.layerIM.service;

import com.tengzhi.IM.business.upload.model.imFile;
import com.tengzhi.IM.layerIM.vo.*;
import com.tengzhi.base.jpa.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 王翔
 * @create 2020-05-16
 */
public interface FriendUserService {

	/**
	 * @Param:
	 * @return:
	 * @Author: 王翔
	 * @Date: 2020/5/16 9:50
	 * @description: 查询当前用户所有好友(分组:部门->用户)
	 */
	ImData findFriendUserAll();

	String getImgIO(String userId, HttpServletRequest request, HttpServletResponse response) throws Exception;

	Map<String,Object> agree(String uuId,String uid,String gid) throws IOException;

	Map<String,Object> SearchConut(String value);
	void DropAll();
	List<imFile> FILES();
	/**
	 * 签名修改
	 * 
	 * @param sign
	 * @return
	 */
	Result sign(SignVo sign);

	/**
	 * 修改阅读状态
	 * @param msgVo
	 * @return
	 */
	Result updateMsg(MsgVo msgVo);

	List<RMsg> getofflinemsg();

	Result TreeUserSava(Map<String,Object> map) throws IOException;

	Map<String,Object> Search(String value,Integer page,Integer limit);

	List<RMsg> gethistroy(String userId,String type);

	Result applyGroupSava(String GroupId,String remark) throws IOException;

	Map<String,Object> agreeFriend(String page);

	Map<String,Object> GroupManagerUser(String GroupId);

	Result dissolution(String GroupId) throws IOException;

	Result exit(String GroupId) throws IOException;
	//查询群成员
	Map<String, Object> GroupNumbers(String id);

	Map<String,Object> refuse(String uuId,String uid,String gid) throws IOException;

	Result remind();

	TreeImData TreeUser();

	Result InnovateFlockName(String GroupName,String GroupId);

	TreeImData AdminCall(String GroupId);

	TreeImData inviteUser(String GroupId);

	TreeImData MainDie(String GroupId);

	TreeImData AdminDie(String GroupId);

	Result AdminAdd(Map<String,Object> map)throws IOException;

	Result inviteAdd(Map<String,Object> map)throws IOException;

	Result DelFlockUser(Map<String,Object> map)throws IOException;

}
