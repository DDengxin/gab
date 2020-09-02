package com.tengzhi.business.system.main.vo;

import com.tengzhi.business.system.right.model.SysRight;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MenuVo {
    public final static String TYPE_MENU = "MENU";
    public final static String TYPE_BUTTON = "BUTTON";
    private String id;
    private String pid;
    private String text;
    private String iconCls;
    private String url;
    private String resource;
    private String type;
    private Integer ord;
    private String trigger;
    private List<MenuVo> children;

    /**
     * 从权限列表中获取菜单列表(递归)
     *
     * @param sysRightList
     * @param rootValue      父节点
     * @param rightRightType 节点类型
     * @return
     */
    public static List<MenuVo> getTreeSysRight(List<SysRight> sysRightList, final String rootValue, final String rightRightType) {
        //创建流在管道中操作
        List<MenuVo> result = sysRightList.stream()
                //排序
                .sorted(Comparator.comparing(SysRight::getRightOrd,Comparator.nullsLast(Comparator.naturalOrder())))
                //当层节点  (过滤掉不是菜单的元素)
                .filter(row -> (rightRightType.equals(row.getRightType()) && rootValue.equals(row.getParentId())))
                .map(row -> MenuVoSysRight(row, TYPE_MENU.equals(row.getRightType()) ? getTreeSysRight(sysRightList, row.getRightId(), TYPE_MENU) : null))
                .collect(Collectors.toList());
        return result;
    }



    private static MenuVo MenuVoSysRight(SysRight sysRight, List<MenuVo> children) {
        MenuVo menuVo = new MenuVo();
        menuVo.setId(sysRight.getRightId());
        menuVo.setPid(sysRight.getParentId());
        menuVo.setText(sysRight.getRightName());
        menuVo.setIconCls(sysRight.getRightIconCls());
        menuVo.setUrl(sysRight.getRightLink());
        menuVo.setResource(sysRight.getRightResource());
        menuVo.setType(sysRight.getRightType());
        menuVo.setOrd(sysRight.getRightOrd());
        menuVo.setTrigger(sysRight.getRightTrigger());
        menuVo.setChildren(children);
        return menuVo;
    }

    public static List<MenuVo> getListSysRight(List<SysRight> sysRightList) {
        return sysRightList.stream()
                .map(row -> MenuVoSysRight(row, null))
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }
}
