package com.tengzhi.business.system.right.vo;

import cn.hutool.core.util.RandomUtil;
import com.tengzhi.business.system.right.model.SysRight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class SysRightVo implements Serializable {
    SysRight menu;
    List<SysRight> buttons,
    addedList = new ArrayList<>(),
    modifyedList = new ArrayList<>(),
    deletedList = new ArrayList<>();

    public SysRight getMenu() {
        return menu;
    }

    public void setMenu(SysRight menu) {
        this.menu = menu;
    }

    public List<SysRight> getButtons() {
        return buttons;
    }

    public void setButtons(List<SysRight> buttons) {
        this.buttons = buttons;
    }

    public List<SysRight> getAddedList() {
        return addedList;
    }

    public void setAddedList(List<SysRight> addedList) {
        this.addedList = addedList;
    }

    public List<SysRight> getModifyedList() {
        return modifyedList;
    }

    public void setModifyedList(List<SysRight> modifyedList) {
        this.modifyedList = modifyedList;
    }

    public List<SysRight> getDeletedList() {
        return deletedList;
    }

    public void setDeletedList(List<SysRight> deletedList) {
        this.deletedList = deletedList;
    }

    public static SysRightVo initSysRightVo(SysRightVo sysRightVo){
        //创建流低级方法(基于迭代器)
        StreamSupport.stream(sysRightVo.getButtons().spliterator(),false).forEach(sysRight -> {
            switch (sysRight.get_state()) {
                case "added":
                    sysRight.setRightId(String.format("%s_%s",sysRightVo.getMenu().getRightId(), RandomUtil.randomNumbers(4)));
                    sysRight.setRightType("BUTTON");
                    sysRight.setParentName(sysRightVo.getMenu().getRightName());
                    sysRight.setRightModule(sysRightVo.getMenu().getRightModule());
                    sysRightVo.getAddedList().add(sysRight);
                    break;
                case "modified":
                    sysRight.setRightModule(sysRightVo.getMenu().getRightModule());
                    sysRightVo.getModifyedList().add(sysRight);
                    break;
                case "removed":
                    sysRightVo.getDeletedList().add(sysRight);
                    break;
                default:break;
            }
        });
        return sysRightVo;
    }

}
