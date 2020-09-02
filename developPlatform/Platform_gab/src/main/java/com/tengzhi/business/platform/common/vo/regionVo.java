package com.tengzhi.business.platform.common.vo;

import java.util.List;

/**
 * @author lqy
 */
public class regionVo {
    private String id;
    private String name;
    private Boolean open;
    private List<regionVo> children;
    private Boolean checked;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<regionVo> getChildren() {
        return children;
    }

    public void setChildren(List<regionVo> children) {
        this.children = children;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

}
