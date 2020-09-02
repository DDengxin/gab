package com.tengzhi.business.base.page.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * DataGrid主表
 */
@Entity
@Table(name = "sys_grid")
public class SysGrid {

    @Id
    private String gridId;

    private String gridMenuId;
    private String gridName;
    private String gridSql;
    private String gridWhere;
    private String gridGroup;
    private String gridOrder;
    private String gridValueCol;


    /*@OneToMany
    @JoinColumn(name="gridId")
    private Set<SysGridCol> sysGridColList;

    public Set<SysGridCol> getSysGridColList() {
        return sysGridColList;
    }

    public void setSysGridColList(Set<SysGridCol> sysGridColList) {
        this.sysGridColList = sysGridColList;
    }*/

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId;
    }

    public String getGridMenuId() {
        return gridMenuId;
    }

    public void setGridMenuId(String gridMenuId) {
        this.gridMenuId = gridMenuId;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public String getGridSql() {
        return gridSql;
    }

    public void setGridSql(String gridSql) {
        this.gridSql = gridSql;
    }

    public String getGridWhere() {
        return gridWhere;
    }

    public void setGridWhere(String gridWhere) {
        this.gridWhere = gridWhere;
    }

    public String getGridGroup() {
        return gridGroup;
    }

    public void setGridGroup(String gridGroup) {
        this.gridGroup = gridGroup;
    }

    public String getGridOrder() {
        return gridOrder;
    }

    public void setGridOrder(String gridOrder) {
        this.gridOrder = gridOrder;
    }

    public String getGridValueCol() {
        return gridValueCol;
    }

    public void setGridValueCol(String gridValueCol) {
        this.gridValueCol = gridValueCol;
    }

}
