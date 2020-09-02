package com.tengzhi.business.platform.enroll.constant;


/**
 * @author: gaodu
 * @date: 2020/8/19 17:06
 **/
public enum GabUserTypeEnum {  //KHINIT
    供应厂商("供应厂商","G", "2201","R9002","/platform/supplier/supplyedit.html"),
    需求用户("需求用户","X", "2301","R9001","/platform/specialist/need/neededit.html"),
    专家团队("专家团队","Z", "2501","R9003","/platform/expert/expertedit.html");

    private String name;
    private String notePrefix;
    private String initRole;
    private String registerApprovalMenuId;
    private String registerApprovalUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotePrefix() {
        return notePrefix;
    }

    public void setNotePrefix(String notePrefix) {
        this.notePrefix = notePrefix;
    }

    public String getInitRole() {
        return initRole;
    }

    public void setInitRole(String initRole) {
        this.initRole = initRole;
    }

    public String getRegisterApprovalMenuId() {
        return registerApprovalMenuId;
    }

    public void setRegisterApprovalMenuId(String registerApprovalMenuId) {
        this.registerApprovalMenuId = registerApprovalMenuId;
    }

    public String getRegisterApprovalUrl() {
        return registerApprovalUrl;
    }

    public void setRegisterApprovalUrl(String registerApprovalUrl) {
        this.registerApprovalUrl = registerApprovalUrl;
    }

    GabUserTypeEnum(String name, String notePrefix,String initRole, String registerApprovalMenuId,String registerApprovalUrl) {
        setName(name);
        setNotePrefix(notePrefix);
        setInitRole(initRole);
        setRegisterApprovalMenuId(registerApprovalMenuId);
        setRegisterApprovalUrl(registerApprovalUrl);
    }

    public static GabUserTypeEnum valueOfName(String name) {
        for (GabUserTypeEnum gabUserTypeEnum : GabUserTypeEnum.values()) {
            if (gabUserTypeEnum.getName().equals(name)) {
                return gabUserTypeEnum;
            }
        }
        return null;
    }


}