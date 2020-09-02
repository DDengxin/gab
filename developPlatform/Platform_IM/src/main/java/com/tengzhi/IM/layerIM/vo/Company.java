package com.tengzhi.IM.layerIM.vo;

import java.util.List;

/**
 * @author lgl
 * 组织架构 (组织架构 ->好友用户)
 */
public class Company {
    private String id;
    private String uid;
    private String groupname;
    //分组好友列表
    private List<FriendUsers> list;
    //子部门
    private List<Company> groups;

    public Company(){}

    public Company(String id,String uid,String groupname){
    	setId(id);
    	setUid(uid);
    	setGroupname(groupname);
	}


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

    public List<FriendUsers> getList() {
        return list;
    }

    public void setList(List<FriendUsers> list) {
        this.list = list;
    }

    public List<Company> getGroups() {
        return groups;
    }

    public void setGroups(List<Company> groups) {
        this.groups = groups;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
