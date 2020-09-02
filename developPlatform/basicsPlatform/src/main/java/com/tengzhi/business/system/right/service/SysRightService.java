package com.tengzhi.business.system.right.service;

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.system.right.constant.RightModuleEnum;
import com.tengzhi.business.system.right.model.SysRight;
import com.tengzhi.business.system.right.vo.SysRightVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SysRightService extends BaseService {

    /**
     * 获取所有数据
     * @return
     */
    List<SysRight> findAll();

    /**
     * 通过菜单模块获取数据(启用的条目)
     * @param rightModule
     * @return
     */
    List<SysRight> getMenuRightModule(String rightModule);
    
    /**
     * 通过菜单模块获取数据(启用的条目)
     * @param rightModule
     * @return
     */
    List<SysRight> getMenuRightModule(String userid,String rightModule);

    /**
     * 通过菜单模块获取数据(启用的条目)
     * @param rightModuleEnum
     * @return
     */
    List<SysRight> getMenuRightModule(RightModuleEnum rightModuleEnum);

    /**
     * 通过菜单模块获取数据(启用的条目)
     * @return
     */
    List<Map<String,Object>> getMenuRightModuleByKeyWord(String keyword);

    /**
     * 获取所有数据(分页)
     * @param baseDto
     * @return
     * @throws IOException
     */
    BasePage<SysRight> findAll(BaseDto baseDto) throws IOException;

    /**
     * 通过rightId获取对象
     * @param rightId
     * @return
     */
    SysRight findByRightId(String rightId);

    /**
     * 保存对象
     * @param sysRightVo
     * @return
     * @throws Exception
     */
    SysRight save(SysRightVo sysRightVo) throws Exception;

    /**
     * 更新对象
     * @param sysRightVo
     */
    void update(SysRightVo sysRightVo) throws Exception;

    /**
     * 删除对象
     * @param map
     */
    @Log("删除权限节点(连带子节点)")
    void deleteById(Map<String,Object> map);

    /**
     * 保存按钮组
     * @param sysRight
     * @return
     */
    SysRight saveButton(SysRight sysRight);

    /**
     * 判断对象是否存在
     * @param sysRight
     * @return
     */
    boolean judgeUnique(SysRight sysRight);

    /**
     * 通过父级ID和类型获取对象数组
     * @param rightId
     * @param typeButton
     * @return
     */
    List<SysRight> findByParentIdAndRightType(String rightId, String typeButton);
    /**
     * 通过父级ID和类型获取对象数组
     * @param rightId
     * @param typeButton
     * @return
     */
    List<SysRight> findByParentIdAndRightType(String userid,String rightId, String typeButton);

    /**
     * 清除缓存
     */
    void removeCache();
}
