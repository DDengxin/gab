package com.tengzhi.business.base.page.model;

import com.tengzhi.base.jpa.model.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * DataGrid列
 */
@Entity
@Table(name = "sys_grid_col")
@IdClass(value = SysGridCol.SysGridCol_PK.class)
public class SysGridCol extends BaseModel {
    @Id
    private String gridId;
    @Id
    private String gridOrd;
    @Id
    private String gridCol;
    private String gridName;
    private String gridWidth;
    private String gridAlign;
    private String gridValueType;

   /* @ManyToOne
    private SysGrid sysGrid;

    public SysGrid getSysGrid() {
        return sysGrid;
    }

    public void setSysGrid(SysGrid sysGrid) {
        this.sysGrid = sysGrid;
    }*/

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId;
    }

    public String getGridOrd() {
        return gridOrd;
    }

    public void setGridOrd(String gridOrd) {
        this.gridOrd = gridOrd;
    }

    public String getGridCol() {
        return gridCol;
    }

    public void setGridCol(String gridCol) {
        this.gridCol = gridCol;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public String getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(String gridWidth) {
        this.gridWidth = gridWidth;
    }

    public String getGridAlign() {
        return gridAlign;
    }

    public void setGridAlign(String gridAlign) {
        this.gridAlign = gridAlign;
    }

    public String getGridValueType() {
        return gridValueType;
    }

    public void setGridValueType(String gridValueType) {
        this.gridValueType = gridValueType;
    }

    public static class SysGridCol_PK implements Serializable {
        private String gridId;
        private String gridOrd;
        private String gridCol;

        public String getGridId() {
            return gridId;
        }

        public void setGridId(String gridId) {
            this.gridId = gridId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SysGridCol_PK that = (SysGridCol_PK) o;
            return Objects.equals(gridId, that.gridId) &&
                    Objects.equals(gridOrd, that.gridOrd) &&
                    Objects.equals(gridCol, that.gridCol);
        }

        @Override
        public int hashCode() {
            return Objects.hash(gridId, gridOrd, gridCol);
        }
    }
}
