package com.tengzhi.business.finance.constituent.vo;

import com.tengzhi.business.finance.constituent.model.EPzJylistLh;
import com.tengzhi.business.finance.constituent.model.EPzJyList;

import java.io.Serializable;
import java.util.List;

public class EPzJyListLhVo implements Serializable {

    EPzJyList constituent;
    List<EPzJylistLh> grid;

    public EPzJyList getConstituent() {
        return constituent;
    }

    public void setConstituent(EPzJyList constituent) {
        this.constituent = constituent;
    }

    public List<EPzJylistLh> getGrid() {
        return grid;
    }

    public void setGrid(List<EPzJylistLh> grid) {
        this.grid = grid;
    }
}
