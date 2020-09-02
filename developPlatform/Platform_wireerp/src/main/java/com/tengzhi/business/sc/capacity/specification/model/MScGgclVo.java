package com.tengzhi.business.sc.capacity.specification.model;

import java.util.List;

/**
 * @Auther: 黄彪
 * @Date: 2020/8/13 0013 13:42
 * @Description:
 */

public class MScGgclVo {

    private MScGgcl headdata;

    private List<MScGgcl> entitydata;

    public MScGgclVo() {
    }

    public MScGgcl getHeaddata() {
        return headdata;
    }

    public void setHeaddata(MScGgcl headdata) {
        this.headdata = headdata;
    }

    public List<MScGgcl> getEntitydata() {
        return entitydata;
    }

    public void setEntitydata(List<MScGgcl> entitydata) {
        this.entitydata = entitydata;
    }
}
