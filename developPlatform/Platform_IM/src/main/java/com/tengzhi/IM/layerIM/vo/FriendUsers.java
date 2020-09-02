package com.tengzhi.IM.layerIM.vo;

import org.apache.commons.lang3.StringUtils;
/**
 * @author lqy
 *分组好友
 */
public class FriendUsers {
	public String id;// 好友ID
	public String username;// 好友昵称
	public String avatar;// 好友头像
	public String sign;// 签名
	public String groupid;//群组ID

	public String status = "offline"; // 若值为offline代表离线，online或者不填为在线

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return StringUtils.isNotEmpty(avatar) ? avatar : "layui/images/0.jpg";// avatar;
	}

	public void setAvatar(String avatar) {

		this.avatar = avatar;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
}
