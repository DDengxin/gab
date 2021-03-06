package com.tengzhi.IM.layerIM.controller;

import com.tengzhi.IM.layerIM.service.FriendUserService;
import com.tengzhi.IM.layerIM.vo.*;
import com.tengzhi.base.jpa.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Im")
public class Imcontroller {

	@Autowired
	private FriendUserService friendUserService;

	@GetMapping(value = "/init")
	public ImData init() {
		return friendUserService.findFriendUserAll();
	}

	@PostMapping(value = "/sign")
	public Result sign(@RequestBody SignVo sign) {
		return friendUserService.sign(sign);
	}

	@RequestMapping("/getImg/{userId}")
	public String init(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return friendUserService.getImgIO(userId, request, response);
	}

	@RequestMapping("/gethistroy")
	public List<RMsg> init(String id,String type) throws Exception {
		return friendUserService.gethistroy(id,type);
	}

	@PostMapping(value = "/updateRead")
	public Result updateRead(@RequestBody MsgVo msgVo) {
		return friendUserService.updateMsg(msgVo);
	}


	//////////////////////////////////////////////////////////群操作接口Start
	/**
	* @Param: []
	* @Author: 鱼游浅水
	* @Date: 2020/6/13 12:16
	* @description:设置管理员查询
	*/
	@RequestMapping(value = "/AdminCall")
	public TreeImData AdminCall(String GroupId) {
		return friendUserService.AdminCall(GroupId);
	}
	/**
	 * @Param: []
	 * @Author: 鱼游浅水
	 * @Date: 2020/6/13 12:18
	 * @description:管理员与群主拉人查询
	 */
	@RequestMapping(value = "/inviteUser")
	public TreeImData inviteUser(String GroupId) {
		return friendUserService.inviteUser(GroupId);
	}
	/**
	 * @Param: []
	 * @Author: 鱼游浅水
	 * @Date: 2020/6/13 12:21
	 * @description:群主踢人查询
	 */
	@RequestMapping(value = "/MainDie")
	public TreeImData MainDie(String GroupId) {
		return friendUserService.MainDie(GroupId);
	}
	/**
	 * @Param: []
	 * @Author: 鱼游浅水
	 * @Date: 2020/6/13 12:21
	 * @description:管理员踢人查询
	 */
	@RequestMapping(value = "/AdminDie")
	public TreeImData AdminDie(String GroupId) {
		return friendUserService.AdminDie(GroupId);
	}
	/**
	* @Param: []
	* @Author: 鱼游浅水
	* @Date: 2020/6/13 12:17
	* @description: 设置管理员添加
	 */
	@RequestMapping(value = "/AdminAdd")
	public Result AdminAdd(@RequestBody Map<String,Object> map) throws IOException {
		return friendUserService.AdminAdd(map);
	}
	/**
	 * @Param: []
	 * @Author: 鱼游浅水
	 * @Date: 2020/6/13 12:18
	 * @description:管理员与群主拉人添加
	 */
	@RequestMapping(value = "/inviteAdd")
	public Result inviteAdd(@RequestBody Map<String,Object> map) throws IOException {
		return friendUserService.inviteAdd(map);
	}
	/**
	* @Param: []
	* @Author: 鱼游浅水
	* @Date: 2020/6/13 12:22
	* @description: 管理员与群主踢人删除
	*/
	@RequestMapping(value = "/DelFlockUser")
	public Result DelFlockUser(@RequestBody Map<String,Object> map) throws IOException {
		return friendUserService.DelFlockUser(map);
	}
	//////////////////////////////////////////////////////////群操作接口END



	/**
	* @Param: [map]
	* @return: com.tengzhi.base.jpa.result.Result
	* @Author: 鱼游浅水
	* @Date: 2020/6/10 11:43
	* @description:应用加群消息保存 以及通知对方
	*/
	@RequestMapping(value = "/applyGroup")
	public Result applyGroup(@RequestBody  Map<String,String> map) throws IOException {
		return friendUserService.applyGroupSava(map.get("groupId"), map.get("remark"));
	}

	/**
	 * @Param: [map]
	 * @return: com.tengzhi.workflow.result.Result
	 * @Author: 鱼游浅水
	 * @Date: 2020/6/8 14:42
	 * @description: 当前关于本人的消息加载(需要你验证是否通过 AND 别人是否允许你加入他群消息)
	 * */
	@RequestMapping(value = "/agreeFriend")
	public Map<String, Object> agreeFriend(String page) throws IOException {
		return friendUserService.agreeFriend(page);
	}

	@RequestMapping(value = "/agree")
	public  
	Map<String, Object> agree(String uuId,String uid,String gid) throws IOException {
		return friendUserService.agree(uuId,uid, gid);
	}

	//判断当前用户是群主还是用户
	@RequestMapping(value = "/GroupManagerUser")
	public  
	Map<String, Object> GroupManagerUser(String GroupId) throws IOException {
		return friendUserService.GroupManagerUser(GroupId);
	}


	//解散群
	@RequestMapping(value = "/dissolution")
	public  
	Map<String, Object> dissolution(String GroupId) throws IOException {
		return friendUserService.dissolution(GroupId);
	}

	//退出群
	@RequestMapping(value = "/exit")
	public  
	Map<String, Object> exit(String GroupId) throws IOException {
		return friendUserService.exit(GroupId);
	}

	//查看当前群里的人员
	@RequestMapping(value = "/GroupMembers")
	public  
	Map<String, Object> GroupNumbers(@RequestParam String id) throws IOException {
		return friendUserService.GroupNumbers(id);
	}

	//拒绝用户加群
	@RequestMapping(value = "/refuse")
	public  
	Map<String, Object> refuse(String uuId,String uid,String gid) throws IOException {
		return friendUserService.refuse(uuId,uid, gid);
	}

	/**
	 * @Param: []
	 * @return: com.tengzhi.TIM.layerIM.vo.ImData
	 * @Author: 鱼游浅水
	 * @Date: 2020/6/5 9:33
	 * @description: TIM选择人员树形人员选择
	 */
	@RequestMapping(value = "/TreeUser")
	public TreeImData TreeUser() {
		return friendUserService.TreeUser();
	}

	/**
	 * @Param: []
	 * @return: com.tengzhi.TIM.layerIM.vo.TreeImData
	 * @Author: 鱼游浅水
	 * @Date: 2020/6/5 14:03
	 * @description: TIM群聊多对多保存
	 */
	@RequestMapping(value = "/TreeUserSava")
	public 
	Result TreeUserSava(@RequestBody Map<String,Object> map) throws IOException {
		return friendUserService.TreeUserSava(map);
	}

	@RequestMapping(value = "/Search")
	public 
	Map<String,Object> Search(String value,Integer page,Integer limit) {
		return friendUserService.Search(value,page,limit);
	}

	@RequestMapping(value = "/SearchConut")
	public 
	Map<String,Object> SearchConut(String value) {
		return friendUserService.SearchConut(value);
	}

	@RequestMapping(value = "/remind")
	public 
	Result remind() {
		return friendUserService.remind();
	}

	/**
	* @Param: [GroupName]
	* @return: com.tengzhi.base.jpa.result.Result
	* @Author: 鱼游浅水
	* @Date: 2020/6/12 21:49
	* @description: 创新群名称
	*/
	@RequestMapping(value = "/InnovateFlockName")
	public Result InnovateFlockName(String GroupName,String GroupId){
		return friendUserService.InnovateFlockName(GroupName,GroupId);
	}

	@RequestMapping(value = "/getofflinemsg")
	public @ResponseBody
	List<RMsg> getofflinemsg() {
		return friendUserService.getofflinemsg();
	}


}
