package com.tengzhi.IM.layerIM.vo;

//群组列表
public class Group {
	public String id;//分组ID
	public String groupname;//分组Name
	public String avatar;//
	public String members;//分组用户数 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getMembers() {
		return members;
	}
	public void setMembers(String members) {
		this.members = members;
	}
	 
	
}
